/**
 * Room class.
 * 
 * @namespace R301.module
 * @author GuoLin
 */
(function($, window) {

    /**
     * Construct room.
     * @param {object} options Options to pass settings
     * @constructor
     */
    R301.module.Room = function(options) {
        this.options = $.extend({}, this.options, options);
        this.subscribe();
    }

    R301.module.Room.prototype = {

        options: {

            /**
             * AJAX method to wait events with comet.
             * @type function
             */
            waitEventsAction: null,

            /**
             * AJAX method to change role.
             * @type function
             */
            changeRoleAction: null,

            /**
             * URL to redirect to play game.
             * @type function
             */
            gameAction: null,

            /**
             * Players container element.
             * @type jQuery
             */
            playersContainer: null,
            
            /**
             * Role selector element.
             * @type jQuery
             */
            roleSelector: null,

            /**
             * Start button.
             * @type jQuery
             */
            startButton: null,

            /**
             * Leave button.
             * @type jQuery
             */
            leaveButton: null,

            /**
             * All roles list.
             * @type array
             */
            roles: []

        },
        
        /**
         * Current selected role.
         * @type object
         */
        selectedRole: null,
        
        /**
         * Players in the room.
         * @type array
         */
        players: [],
        
        /**
         * Render players.
         */
        renderPlayers: function() {
            var me = this, container = this.options.playersContainer,
                startButton = this.options.startButton;
            container.empty();
            $.each(this.players, function(i, player) {
                var self = (player.name == R301.constants.CURRENT_USER),
                    htmls = [], el,
                    face = R301.constants.ROLE_FACE_ROOT + player.role.face;
                htmls.push('<li class="' + (self ? 'me' : 'other') + '">');
                htmls.push('<img class="role" src="' + face + '" />');
                htmls.push('<div class="details">')
                htmls.push('<em class="name">' + player.name + '</em>'); 
                htmls.push('<em class="role-name">' + i18n(player.role.name) + '</em>'); 
                htmls.push('</div>')
                htmls.push("</li>");
                el = $(htmls.join("")).appendTo(container);
                if (self) {
                    el.click(function() {
                        me.options.roleSelector.toggle("fast");
                    });
                }
                if (self) {
                    startButton[ (player.master ? "show" : "hide") ]();
                }
            });
        },

        /**
         * Render role selector.
         */
        renderRoleSelector: function() {
            var me = this, list = this.options.roleSelector.find("ul"), 
                htmls, i, n, role, roles = this._getUnselectedRoles(), face;
            list.empty();
            for (i = 0, n = roles.length; i < n; i++) {
                role = roles[i];
                face = R301.constants.ROLE_FACE_ROOT + role.face;
                htmls = [];
                htmls.push('<li>');
                htmls.push('<img class="role" src="' + face + '" />');
                htmls.push('<em>' + role.name + '</em>');
                htmls.push('</li>');
                $(htmls.join("")).bind("click", role, function(event) {
                    me.onRoleSelected(event.data);
                }).appendTo(list);
            }            
        },

        /**
         * Subscribe room state change event in comet.
         */
        subscribe: function() {
            var lastReceived = 0, waitEventsAction = this.options.waitEventsAction,
                me = this;
            var getMessages = function() {
                $.ajax({
                    url: waitEventsAction({ lastReceived: lastReceived }),
                    contentType: "application/json",
                    success: function(events, textStatus) {
                        var event = events[events.length - 1], room = event.data, 
                            type = room.eventType;
                        if (type == "StateChangeEvent") {
                            me.onStatusUpdated(room);
                        }
                        else if (type == "PlayerChangeEvent") {
                            me.onPlayerChanged(room);
                        }
                        lastReceived = event.id;
                        getMessages();
                    },
                    error: function(request, textStatus, error) {
                        console.log(textStatus, error);
                    }
                });
            }
            getMessages();
        },
        
        /**
         * Fire on players on the room has been changed.
         * @param {object} room Room that changed players belongs to
         */
        onPlayerChanged: function(room) {
            var players = room.players, master = room.master,
                current = R301.constants.CURRENT_USER;
            if (!players) {
                return;
            }

            // Parse players
            players.sort(function(l, r) { 
                if (l.name == current) {
                    return -1;
                } else if (r.name == current) {
                    return 1;
                } else {
                    return l.name > r.name ? -1 : 1;
                }
            });
            $.each(players, function() {
                if (this.id == master.id) {
                    this.master = true;
                    return false;
                }
            });
            this.players = players;

            // Render
            this.renderPlayers();
            this.renderRoleSelector();
        },
        
        /**
         * Fire on role selected.
         * @param {object} role Selected role
         */
        onRoleSelected: function(role) {
            var me = this,  changeRoleAction = this.options.changeRoleAction,
                playersContainer = this.options.playersContainer;
            $.ajax({
                url: changeRoleAction({ roleId: role.id }),
                type: "PUT",
                contentType: "application/json",
                success: function() {
                    var face = R301.constants.ROLE_FACE_ROOT + role.face;
                    playersContainer.find(".me img").attr("src", face);
                    playersContainer.find(".me .role-name").text(role.name);
                    me.options.roleSelector.hide("fast");
                },
                error: function(request, textStatus, error) {
                    console.log(textStatus, error);
                }
            });
        },

        /**
         * Fire on room status updated.
         * @param {object} room Room that status be changed
         */
        onStatusUpdated: function(room) {
            var gameAction = this.options.gameAction;
            if (room.status == "PLAYING") {
                location.href = gameAction({ gameId: room.id });
                return;
            }
        },
        
        /**
         * Get unselected roles from specified players.
         * @return {array} Unselected roles
         * @private
         */
        _getUnselectedRoles: function() {
            var i, n, role, roles = this.options.roles, 
                players = this.players, selected = [];
            if (!players) {
                return;
            }
            for (i = 0, n = players.length; i < n; i++) {
                role = players[i].role;
                if (role) {
                    selected.push(role.name);
                }
            }
            return $.grep(roles, function(role) {
                return $.inArray(role.name, selected) == -1;
            });
        }

    }

})(jQuery, window);
