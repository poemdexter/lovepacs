export default (appModule) => {
	appModule.config(['$stateProvider', '$locationProvider', '$urlRouterProvider', function ($stateProvider, $locationProvider, $urlRouterProvider) {
        //$locationProvider.html5Mode(true);
        $locationProvider.hashPrefix('!');

        $urlRouterProvider.otherwise('/dashboard');

        $stateProvider
            .state('dashboard', {
                url: '/:dashboard',
                template: require('../controllers/dashboard/dashboard.html'),
                controller: require('../controllers/dashboard/dashboard'),
                controllerAs: 'controller'
            });
    }]);
};