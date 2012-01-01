(function() {
	
	Player = function(settings) {
		settings = $.extend({
			
		}, settings);
	};
	
	Player.prototype = {
			
	};
	
	var RichMap = function(settings) {
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
	};
	
	RichMap.prototype = {
		appendTo: function(el) {
			el = typeof el === 'string' ? document.getElementById(el) : el;
			el.appendChild(this.canvas);
		},
		draw: function() {
			this.ctx.drawImage(this.bgCanvas, 0, 0);
		}
	};
	
	var Game = function(settings) {
		settings = $.extend({
			fps: 25,
			speed: 2
		}, settings);
		
		this.settings = settings;
		this.actions = [];
	};
	Game.prototype = {
		run: function() {
			var me = this,
				t = null,
				interval = 1000 / this.settings.fps;
			setInterval(function() {
				console.log('out');
				if(me.actions.length > 0) {
					while(me.actions.length > 0) {
						t = me.actions.shift();
						t.constructor === Function && t();
					}
				}
			}, interval);
		},
		registerAction: function(action) {
			this.actions.push(action);
		}
	};
	
	var Action = {
		roll: function() {},
		bug: function() {},
		run: function() {}
	}
	
	R301.module.RichMap = RichMap;
	R301.module.Game = Game;
})();

















