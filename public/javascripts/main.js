(function() {
	var Game = function(settings) {
		settings = $.extend({
				gridWidth: 64,
				gridHeight: 48,
				background: '',
				map: ''
			}, settings);
		
		if(!settings.background || !settings.map) {
			throw new errors('this map photo is not exist!');
			return;
		}
		
		var	me = this,
			canvas = document.createElement('canvas'),
			bgCanvas = document.createElement('canvas'),
			ctx = canvas.getContext('2d');
		
		this.canvas = canvas;  
		this.bgCanvas = bgCanvas;
		this.ctx = ctx;
		this.queue = [];
		this.actions = [];
		this.settings = settings;
	};
	
	Game.prototype = {
		appendTo: function(el) {
			el = typeof el === 'string' ? document.getElementById(el) : el;
			el.appendChild(this.canvas);
		},
		queuePush: function(fn) {
			this.queue.push(fn);
		},
		registAction: function(fn) {
			this.actions.push(fn);
		},
		run: function() {
			var me = this,
				img = document.createElement('img'),
				canvas = this.canvas,
				bgCanvas = this.bgCanvas,
				ctx = this.ctx,
				bctx = bgCanvas.getContext('2d');
			
			img.addEventListener('load', function() {
				var i = 0, actions = me.actions, len = actions.length;
				bgCanvas.width = this.width;
				bgCanvas.height = this.height;
				canvas.width = this.width;
				canvas.height = this.height;
				ctx.drawImage(this, 0, 0);
				bctx.drawImage(this, 0, 0);
				setTimeout(function() {
					for(i; i < len; i++) {
						actions[i]();
					}
				}, 40);
			});
			
			img.src = this.settings.background;
		},
		draw: function(canvas, dx, dy) {
			this.ctx.drawImage(canvas, dx, dy);
		},
		restore: function() {
			this.draw(this.bgCanvas, 0, 0);
		}
	};
	
	Phase = function() {
		
	};
	Phase.prototype = {
			
	};
	
	Rolled = function() {
		
	};
	Rolled.prototype = {
			
	};
	
	Turn = function() {
		
	};
	Turn.prototype = {
			
	}
	
	Dice = function(el, settings) {
		if(!el) { return; }
		settings = $.extend({
			width: 50,
			height: 50,
		}, settings);
		
		this.container = $('<div class="dice" style="display: none;"></div>').appendTo(el);
		this.settings = settings;
		this.randomTimeId = 0;
	};
	Dice.prototype = {
		setPosition: function() {
			var win = $(window), 
				settings = this.settings;
			this.container.css({
				'left': (win.width() - settings.width) / 2,
				'top': (win.height() - settings.height) / 2
			});
		},
		random: function() {
			var me = this,
				num = 1;
			this.randomTimeId = setTimeout(function() {
				num = Math.floor(Math.random() * 6 + 1);
				me.setNumber(num);
				me.random();
			}, 20);
		},
		stopRandom: function() {
			clearTimeout(this.randomTimeId);
		},
		setNumber: function(num) {
			this.container.html(num);
		},
		show: function() {
			this.container.show();
		},
		hide: function() {
			this.container.hide();
		}
	};
	
	Player = function(settings) {
		settings = $.extend({
			avatar: '',
			height: '',
			width: '',
			x: '',
			y: ''
		}, settings);
		var player = document.createElement('canvas'),
			avatar = document.createElement('img'),
			ctx = player.getContext('2d');
		
		player.height = settings.height;
		player.width = settings.width;
		
		avatar.width = settings.width;
		
		avatar.addEventListener('load', function() {
			ctx.drawImage(avatar, 0, 0);
		});
		
		avatar.src = settings.avatar;
		
		this.player = player;
		this.settings = settings;
		this.position = { x: settings.x, y: settings.y };
	};
	
	Player.prototype = {
		appendTo: function(el) {
			el.appendChild(this.player);
		},
		getPlayer: function() {
			return this.player;
		},
		getPosition: function() {
			return this.position;
		},
		setPosition: function(x, y) {
			this.position = { x: x, y: y };
		},
	};
	
	var Data = {
		playerList: {}
	};
	
	var Action = {
		events: (function() {
			var lastReceived = 0;
			return function() {
				$.ajax({
	                url: '/ajax/games/'+ gameId +'/events?lastReceived=' + lastReceived,
	                contentType: "application/json",
	                success: function(events, textStatus) {
	                	var i = 0, len = events.length;
	            		while(i < len) {
	            			var event = events[i],
	            				data = event.data,
	            				win = $(window);
	            			
	            			if (data.type == "StartEvent") {
	            	            win.trigger('startGame', [data.game]);
	            		    }else if(data.type == "DiceEvent") {
	            		    	win.trigger('rollDice', [data.value]);
	            		    }else if(data.type == 'MoveEvent') {
	            		    	win.trigger('playerMove', [data.cellId]);
	            		    }else if(data.type == 'NextPlayerEvent') {
	            		    	win.trigger('nextPlayer', [data.playerName]);
	            		    };
	            		    
	            			i++;
	            			lastReceived = event.id;
	            		}
	            		Action.events();
	                },
	                error: function(request, textStatus, error) {
	                	
	                }
	            });
			}
		}()),
		roll: function() {
			var dice = new Dice($('body'));
			Data.dice = dice;
			$(window).bind('action:roll', function() {
				dice.random();
				dice.setPosition();
				dice.show();
				console.log(12)
				$.ajax({
					url: '/ajax/games/'+ gameId +'/action/roll',
	                contentType: "application/json",
	                success: function(events, textStatus) {
	                	
	                },
	                error: function(request, textStatus, error) {
	                }
	            });
			});
		},
		bug: function() {},
		run: function() {},
		showDice: function() {
			var timerId = 0;
			$(window).bind('rollDice', function(event, value) {
				clearTimeout(timerId);
				setTimeout(function() {
					var dice = Data.dice,
						pos = Action.parsePosition(Data.moveNum);
						name = Data.currentPlayerName,
						player = Data.playerList[name];
						dice.stopRandom();
						dice.setNumber(value);
					
					player.setPosition(pos.x, pos.y);
					Data.game.restore();
					Action.updatePlayerOnMap();
					timerId = setTimeout(function() {
						dice.hide();
					}, 1000);
				}, 1000);
			});
		},
		playerReady: (function() {
			$(window).bind('startGame', function(event, game) {
				var players = game.players,
					gameMap = game.gameMap,
					i = 0, len = players.length,
					pos = null,
					avatarPath = R301.constants.PUBLIC_PATH + '/data/roles/',
					game = new R301.module.Game({
						background: background,
						map: gameMap.mapCells
					});
				//地图行数
				mapRow = gameMap.height;
				//地图列数
				mapCol = gameMap.width;
				
				for(i; i < len; i++) {
					(function(index) {
						var player = players[index];
						pos = Action.parsePosition(player.currentCellId);
						
						Data.playerList[player.name] = new R301.module.Player({
							avatar: avatarPath + player.role.face,
							width: 64, 
							height: 64,
							x: pos.x,
							y: pos.y
						});
						
						game.registAction(function() {
							var p = Data.playerList[player.name],
								position = p.getPosition();
							game.draw(p.getPlayer(), position.x * cellWidth, position.y * cellHeight - fixHeight);
						});
						
					})(i);
				};
				game.appendTo(document.body);
			    game.run();
			    Data.game = game;
			});
		}()),
		nextPlayer: (function() {
			$(window).bind('nextPlayer', function(event, playerName) {
				Data.currentPlayerName = playerName;
			});
		}()),
		playerMove: (function() {
			$(window).bind('playerMove', function(event, num) {
				Data.moveNum = num;
			});
		}()),
		parsePosition: function(num) {
			return { 
				y: parseInt(num / mapCol),
				x: num % mapCol
			}
		},
		updatePlayerOnMap: function() {
			var playerList = Data.playerList,
				game = Data.game,
				pos = null,
				player = null;
			for(i in playerList) {
				player = playerList[i];
				pos = player.getPosition();
				game.draw(player.getPlayer(), pos.x * cellWidth, pos.y * cellHeight - fixHeight);
			}
		}
	};
	
	R301.module.Game = Game;
	R301.module.Player = Player;
	R301.module.Action = Action;
	R301.module.Dice = Dice;
})();

















