(function(window) {
	var RichMap = function(settings) {
		var settings = $.extend({
				gridWidth: 64,
				gridHeight: 48,
				background: ''
			}, settings);
		if(!settings.background) {
			throw new errors('this map photo is not exist!');
			return;
		}
		var	me = this,
			main = document.createElement('canvas'),
			ctx = main.getContext('2d'),
			img = document.createElement('img');
		
		img.addEventListener('load', function() {
			main.width = this.width;
			main.height = this.height;
			ctx.drawImage(this, 0, 0);
		});
		
		img.src = settings.background;
		this.main = main;
	};
	RichMap.prototype = {
		appendTo: function(el) {
			el = typeof el === 'string' ? document.getElementById(el) : el;
			el.appendChild(this.main);
		}
	};

	window.RichMap = RichMap;
	
})(window);