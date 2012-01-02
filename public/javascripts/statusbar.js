/**
 * Status bar class.
 * @namespace R301.module
 * @author GuoLin
 */
(function($, window) {

    /**
     * Construct status bar.
     * @param {object} options Property settings
     * @constructor
     */
    R301.module.Statusbar = function(options) {
        var me = this, $window;
        this.options = $.extend({}, this.options, options);
        $window = $(window);
        $window.bind("gameStart", function(event, players) {
            me.onGameStart(players);
        });
    };

    R301.module.Statusbar.prototype = {

        options: {

            /**
             * Container of statusbar.
             * @type jQuery
             */
            container: null

        },

        /**
         * Render status bar.
         */
        render: function() {
            
        },

        /**
         * Fire on game start.
         * @param {array} game Game information
         */
        onGameStart: function(game) {
            
        }

    };

})(jQuery, window);