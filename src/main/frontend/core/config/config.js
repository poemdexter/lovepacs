export default (appModule) => {
	appModule.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', function ($stateProvider, $locationProvider, $urlRouterProvider) {
        //$locationProvider.html5Mode(true);
        $locationProvider.hashPrefix('!');

        $urlRouterProvider.otherwise('/dashboard');

        $stateProvider
            .state('container', {
                abstract: true,
                views: {
                    'app': {
                        template: require('../controllers/container/container.html'),
                        controller: require('../controllers/container/container')
                    }
                }
            })
            .state('container.dashboard', {
                url: '/dashboard',
                template: require('../controllers/dashboard/dashboard.html'),
                controller: require('../controllers/dashboard/dashboard'),
                controllerAs: 'controller'
            })
            .state('container.location', {
                url: '/location?id',
                template: require('../controllers/location/inventory.html'),
                controller: require('../controllers/location/inventory'),
                controllerAs: 'controller'
            })
            .state('container.location.create', {
                url: '/create',
                views:{
                    "modal": {
                        template: require('../controllers/location/location.html'),
                        controller: require('../controllers/location/create'),
                        controllerAs: 'controller'
                    }
                },
                onEnter: function($state, $previousState){
                    $previousState.memo("modalInvoker");

                    // TODO: Make this a rootScope variable
                    // TODO: Tie rootScope variable with black overlay + animation
                    $("body").addClass("modal-open");

                    // Hitting the ESC key closes the modal
                    $(document).on('keyup', function(e){
                        if(e.keyCode == 27){
                            $(document).off('keyup');
                            $previousState.go("modalInvoker");
                        }
                    });
                },
                onExit: function($state){
                    $("body").removeClass("modal-open");
                }
            })
            .state('container.location.edit', {
                url: '/edit?id',
                views:{
                    "modal": {
                        template: require('../controllers/location/location.html'),
                        controller: require('../controllers/location/edit'),
                        controllerAs: 'controller'
                    }
                },
                onEnter: function($state, $previousState){
                    $previousState.memo("modalInvoker");

                    // TODO: Make this a rootScope variable
                    // TODO: Tie rootScope variable with black overlay + animation
                    $("body").addClass("modal-open");

                    // Hitting the ESC key closes the modal
                    $(document).on('keyup', function(e){
                        if(e.keyCode == 27){
                            $(document).off('keyup');
                            $previousState.go("modalInvoker");
                        }
                    });
                },
                onExit: function($state){
                    $("body").removeClass("modal-open");
                }
            })
            .state('container.items', {
                url: '/items',
                template: require('../controllers/item/items.html'),
                controller: require('../controllers/item/items'),
                controllerAs: 'controller'
            })
            .state('container.items.create', {
                url: '/create',
                views:{
                    "modal": {
                        template: require('../controllers/item/item.html'),
                        controller: require('../controllers/item/create'),
                        controllerAs: 'controller'
                    }
                },
                onEnter: function($state, $previousState){
                    // TODO: Make this a rootScope variable
                    // TODO: Tie rootScope variable with black overlay + animation
                    $("body").addClass("modal-open");

                    // Hitting the ESC key closes the modal
                    $(document).on('keyup', function(e){
                        if(e.keyCode == 27){
                            $(document).off('keyup');
                            $state.go('container.items');
                        }
                    });
                },
                onExit: function($state){
                    $("body").removeClass("modal-open");
                }
            })
            .state('container.items.item', {
                url: '/item?id',
                views:{
                    "modal": {
                        template: require('../controllers/item/item.html'),
                        controller: require('../controllers/item/edit'),
                        controllerAs: 'controller'
                    }
                },
                onEnter: function($state, $previousState){
                    // TODO: Make this a rootScope variable
                    // TODO: Tie rootScope variable with black overlay + animation
                    $("body").addClass("modal-open");

                    // Hitting the ESC key closes the modal
                    $(document).on('keyup', function(e){
                        if(e.keyCode == 27){
                            $(document).off('keyup');
                            $state.go('container.items');
                        }
                    });
                },
                onExit: function($state){
                    $("body").removeClass("modal-open");
                }
            })
            .state('container.packs', {
                url: '/packs',
                template: require('../controllers/pack/packs.html'),
                controller: require('../controllers/pack/packs'),
                controllerAs: 'controller'
            })
            .state('container.packs.pack', {
                url: '/pack?id',
                views:{
                    "modal": {
                        template: require('../controllers/pack/pack.html'),
                        controller: require('../controllers/pack/edit'),
                        controllerAs: 'controller'
                    }
                },
                onEnter: function($state, $previousState){
                    // TODO: Make this a rootScope variable
                    // TODO: Tie rootScope variable with black overlay + animation
                    $("body").addClass("modal-open");

                    // Hitting the ESC key closes the modal
                    $(document).on('keyup', function(e){
                        if(e.keyCode == 27){
                            $(document).off('keyup');
                            $state.go('container.packs');
                        }
                    });
                },
                onExit: function($state){
                    $("body").removeClass("modal-open");
                }
            })
            .state('container.packs.create', {
                url: '/create',
                views:{
                    "modal": {
                        template: require('../controllers/pack/pack.html'),
                        controller: require('../controllers/pack/create'),
                        controllerAs: 'controller'
                    }
                },
                onEnter: function($state, $previousState){
                    // TODO: Make this a rootScope variable
                    // TODO: Tie rootScope variable with black overlay + animation
                    $("body").addClass("modal-open");

                    // Hitting the ESC key closes the modal
                    $(document).on('keyup', function(e){
                        if(e.keyCode == 27){
                            $(document).off('keyup');
                            $state.go('container.packs');
                        }
                    });
                },
                onExit: function($state){
                    $("body").removeClass("modal-open");
                }
            });
    }]);
};