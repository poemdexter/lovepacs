class ContainerCtrl {

    constructor($rootScope, $scope, $stateParams, ApiService){
			$rootScope.$on('$stateChangeSuccess', function(ev, to, toParams, from, fromParams) {
		    $rootScope.previousState = from.name;
		    $rootScope.currentState = to.name;
		});

		ApiService.getLocations().then(function(data) {
    		$scope.locations = data.data;
    	});

    	ApiService.getUser().then(function(data) {
    		$rootScope.user = data.data;
    	});
    }
}

ContainerCtrl.$inject = ['$rootScope', '$scope', '$stateParams', 'ApiService'];

export default ContainerCtrl;