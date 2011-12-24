(function() {
	
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
		this.queue = [];
	};
	
	RichMap.prototype = {
		appendTo: function(el) {
			el = typeof el === 'string' ? document.getElementById(el) : el;
			el.appendChild(this.canvas);
		},
		draw: function() {
			this.ctx.drawImage(this.bgCanvas, 0, 0);
		},
		queuePush: function(fn) {
			this.queue.push(fn);
		},
		run: function() {
			var me = this;
			setInterval(function() {
				var t;
				me.draw();
				if(me.queue.length > 0) {
					while(me.queue.length > 0) {
						t = me.queue.shift();
						t.constructor === Function && t();
					}
				}
			}, 40);
		}
	};
	
	R301.module.RichMap = RichMap;
})();