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
             * Root URL to store role face.
             * @type string
             */
            faceRoot: null,
            
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
                        var event = events[0], room = event.data, roles;
                        me.onPlayerUpdated(room.players);
                        me.onStatusUpdated(room.status);
                        roles = me._getUnselectedRoles(room.players);
                        me.onRoleUpdated(roles);
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
         * Fire on role has been updated.
         * @param {array} roles Unselected roles
         */
        onRoleUpdated: function(roles) {
            var me = this, list = this.options.roleSelector.find("ul"), 
                htmls, i, n, role, faceRoot = this.options.faceRoot;
            list.empty();
            for (i = 0, n = roles.length; i < n; i++) {
                role = roles[i];
                htmls = [];
                htmls.push('<li>');
                htmls.push('<img class="role" src="' + (faceRoot + role.face) + '" />');
                htmls.push('<em>' + role.name + '</em>');
                htmls.push('</li>');
                $(htmls.join("")).bind("click", role, function(event) {
                    me.onRoleSelected(event.data);
                }).appendTo(list);
            }
        },
        
        /**
         * Fire on role selected.
         * @param {object} role Selected role
         */
        onRoleSelected: function(role) {
            var me = this, faceRoot = this.options.faceRoot,
                changeRoleAction = this.options.changeRoleAction,
                playersContainer = this.options.playersContainer;
            $.ajax({
                url: changeRoleAction({ roleId: role.id }),
                type: "PUT",
                contentType: "application/json",
                success: function() {
                    playersContainer.find(".me img").attr("src", (faceRoot + role.face));
                    playersContainer.find(".me .role-name").text(role.name);
                    me.options.roleSelector.hide("fast");
                },
                error: function(request, textStatus, error) {
                    console.log(textStatus, error);
                }
            });
        },

        /**
         * Fire on players state changed, such as join or leave room.
         * @param {array} players All players that in the room
         */
        onPlayerUpdated: function(players) {
            var htmls, i, n, player, self, el, me = this,
                faceRoot = this.options.faceRoot, 
                container = this.options.playersContainer;
            if (!players) {
                return;
            }
            container.empty();
            for (i = 0, n = players.length; i < n; i++) {
                player = players[i];
                self = player.name == R301.constants.CURRENT_USER;
                htmls = [];
                htmls.push('<li' + (self ? ' class="me"' : '') + '>');
                htmls.push('<img class="role" src="' + (faceRoot + player.role.face) + '" />');
                htmls.push('<div class="details">')
                htmls.push('<em class="name">' + player.name + '</em>'); 
                htmls.push('<em class="role-name">' + player.role.name + '</em>'); 
                htmls.push('</div>')
                htmls.push("</li>");
                el = $(htmls.join("")).appendTo(container);
                if (self) {
                    el.click(function() {
                        me.options.roleSelector.toggle("fast");
                    });
                }
            }
        },

        /**
         * Fire on room status updated.
         * @param {string} status Status to be
         */
        onStatusUpdated: function(status) {
            $("#room-status").text(status);
        },
        
        /**
         * Get unselected roles from specified players.
         * @param {array} players Players to filter
         * @return {array} Unselected roles
         * @private
         */
        _getUnselectedRoles: function(players) {
            var i, n, role, roles = this.options.roles, selected = [];
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
                return $.inArray(role.name, selected);
            });
        }

    }

})(jQuery, window);
