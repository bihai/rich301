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
			bgCanvas = document.createElement('canvas'),
			canvas = document.createElement('canvas'),
			bctx = bgCanvas.getContext('2d'),
			ctx = canvas.getContext('2d'),
			img = document.createElement('img');
		
		img.addEventListener('load', function() {
			bgCanvas.width = this.width;
			bgCanvas.height = this.height;
			canvas.width = this.width;
			canvas.height = this.height;
			bctx.drawImage(this, 0, 0);
		});
		
		img.src = settings.background;
		this.bgCanvas = bgCanvas;
		this.canvas = canvas;              
		this.ctx = ctx;
		this.queue = [];
		this.settings = settings;
	};
	
	Game.prototype = {
		appendTo: function(el) {
			el = typeof el === 'string' ? document.getElementById(el) : el;
			el.appendChild(this.canvas);
		},
		refresh: function() {
			this.ctx.drawImage(this.bgCanvas, 0, 0);
		},
		queuePush: function(fn) {
			this.queue.push(fn);
		},
		run: function() {
			var me = this,
				interval = 1000 / me.settings.fps;
			setInterval(function() {
				var t;
				if(me.queue.length > 0) {
					while(me.queue.length > 0) {
						t = me.queue.shift();
						t.constructor === Function && t();
					}
				}
			}, interval);
		}
	};
	
	Player = function(settings) {
		settings = $.extend({
			avatar: '',
			height: '',
			width: ''
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
		
		avatar = settings.avatar;
		
		this.player = player;
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
			
		}
	};
	
	var Action = {
		init: function() {
			$.ajax({
                url: '/ajax/games/'+ gameId +'/events?lastReceived=0',
                contentType: "application/json",
                success: function(events, textStatus) {
                   console.log(events, textStatus);
                },
                error: function(request, textStatus, error) {
                    console.log(123123);
                }
            });
		},
		roll: function() {},
		bug: function() {},
		run: function() {}
	}
	
	R301.module.Game = Game;
	R301.module.Player = Player;
	R301.module.Action = Action;
})();

















