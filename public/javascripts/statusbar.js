/**
 * Status bar class.
 * @namespace R301.module
 * @author GuoLin
 */
(function($, window) {

    var $window = $(window);

    /**
     * Construct status bar.
     * @param {object} options Property settings
     * @constructor
     */
    R301.module.Statusbar = function(options) {
        var me = this, container;
        this.options = $.extend({}, this.options, options);
        container = this.options.container;
        this.options.playersContainer = container.find("#players");
        this.options.detailsContainer = container.find("#details");
        this.options.actionsContainer = container.find("#actions");

        // Bind global events
        $window.bind("startGame", function(event, game) {
            me.onGameStart(game);
        });
    };

    R301.module.Statusbar.prototype = {

        options: {

            /**
             * Container of statusbar.
             * @type jQuery
             */
            container: null,

            /**
             * Players details container.
             * @type jQuery
             */
            playersContainer: null,

            /**
             * Game details container.
             * @type jQuery
             */
            detailsContainer: null,

            /**
             * Actions container.
             * @type jQuery
             */
            actionsContainer: null

        },

        /**
         * Game that the statusbar belongs to.
         * @type object
         */
        game: {},

        /**
         * Render status bar.
         */
        render: function() {
            this._renderDetails();
            this._renderAction();
            this._renderPlayers();
        },

        /**
         * Render details.
         */
        _renderDetails: function() {
            var game = this.game, container = this.options.detailsContainer;
            container.find("h1").text(game.name);
            container.find("em").text(game.round);
        },

        /**
         * Render actions.
         */
        _renderAction: function() {
            var me = this, container = this.options.actionsContainer,
                actionRoll = container.find("#action-roll");
            actionRoll.click(function() {
                $window.trigger("action:roll");
            });
        },

        /**
         * Render players.
         */
        _renderPlayers: function() {
            var game = this.game, container = this.options.playersContainer,
                players = game.players;
            $.each(players, function(i, player) {
                var htmls = [], role = player.role,
                    face = R301.constants.ROLE_FACE_ROOT + role.face;
                htmls.push('<li class="box' + (player.active ? ' active' : '') + '">');
                htmls.push('<img src="' + face + '" />');
                htmls.push('<div class="details">');
                htmls.push('<h3>' + player.name + '</h3>');
                htmls.push('<em>$' + player.cash + '</em>');
                htmls.push('</div>')
                htmls.push('</li>');
                $(htmls.join("")).appendTo(container);
            });
        },

        /**
         * Fire on game start.
         * @param {array} game Game information
         */
        onGameStart: function(game) {
            this.game.id = game.id;
            this.game.name = game.name;
            this.game.round = game.round;
            this.game.players = game.players;
            $.each(game.players, function(i, player) {
                if (player.id == game.currentPlayer.id) {
                    player.active = true;
                }
            });
            this.render();
        }

    };

})(jQuery, window);