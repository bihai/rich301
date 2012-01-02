(function() {
	var Game = function(settings) {
		settings = $.extend({
				gridWidth: 64,
				gridHeight: 48,
				background: '',
				map: '',
				fps: 25
			}, settings);
		
		if(!settings.background || !settings.map) {
			throw new errors('this map photo is not exist!');
			return;
		}
		
		var	me = this,
			canvas = document.createElement('canvas'),
			ctx = canvas.getContext('2d');
		
		this.canvas = canvas;              
		this.ctx = ctx;
		this.queue = [];
		this.actions = [];
		this.settings = settings;
		this.interval = 1000 / settings.fps;
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
				ctx = this.ctx;
			
			img.addEventListener('load', function() {
				var i = 0, actions = me.actions, len = actions.length;
				canvas.width = this.width;
				canvas.height = this.height;
				ctx.drawImage(this, 0, 0);
				setTimeout(function() {
					for(i; i < len; i++) {
						actions[i]();
					}
				}, 40);
			});
			
			img.src = this.settings.background;
			
			setInterval(function() {
				var t;
				if(me.queue.length > 0) {
					while(me.queue.length > 0) {
						t = me.queue.shift();
						t.constructor === Function && t();
					}
				}
			}, this.interval);
		},
		draw: function(canvas, dx, dy) {
			this.ctx.drawImage(canvas, dx, dy);
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
		roll: function() {
			
		},
		buy: function() {
			
		},
		run: function() {
			
		
		getPosition: function() {
			return this.position;
		},
		setPosition: function(opt) {
			this.position = opt;
		}
	};
	
	var Action = {
		startGame: function(fn) {
			$.ajax({
                url: '/ajax/games/'+ gameId +'/events?lastReceived=0',
                contentType: "application/json",
                success: function(events, textStatus) {
                	fn && fn(events, textStatus);
                },
                error: function(request, textStatus, error) {
                	
                }
            });
		},
		roll: function() {},
		bug: function() {},
		run: function() {},
		born: function(players) {
			
		},
		playerReady: function() {
			
		}
	}
	
	R301.module.Game = Game;
	R301.module.Player = Player;
	R301.module.Action = Action;
})();

















