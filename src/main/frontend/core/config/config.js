export default (appModule) => {
	appModule.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', function ($stateProvider, $locationProvider, $urlRouterProvider) {
        //$locationProvider.html5Mode(true);
        $locationProvider.hashPrefix('!');

        $urlRouterProvider.otherwise('/dashboard');

        $stateProvider.state("modal", {
            views:{
                "modal": {
                    template: require("../controllers/modal/modal.html")
                }
            },
            onEnter: function($state){
                // TODO: Make this a rootScope variable
                // TODO: Tie rootScope variable with black overlay + animation
                $("body").addClass("modal-open");

                // Hitting the ESC key closes the modal
                $(document).on('keyup', function(e){
                    if(e.keyCode == 27){
                        $(document).off('keyup');
                        $state.go('templates');
                    }
                });
            },
            onExit: function($state){
                $("body").removeClass("modal-open");
            },
            abstract: true
        });

        $stateProvider
            .state('container', {
                abstract: true,
                sticky: true,
                dsr: true,
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
            .state('container.items', {
                url: '/items',
                template: require('../controllers/items/items.html'),
                controller: require('../controllers/items/items'),
                controllerAs: 'controller'
            })
            .state('modal.item', {
                url: '/item',
                parent: 'modal',
                views:{
                    "modal": {
                        template: require('../controllers/item/item.html'),
                        controller: require('../controllers/item/item'),
                        controllerAs: 'controller'
                    }
                }
            })
            /*(.state('item', {
                url: 'item/:itemId',
                template: require('../controllers/item/item.html'),
                controller: require('../controllers/item/item'),
                controllerAs: 'controller'
            })*/;
    }]);
};