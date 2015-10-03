class ContainerCtrl {

    constructor($rootScope, $scope, $stateParams){
			$rootScope.$on('$stateChangeSuccess', function(ev, to, toParams, from, fromParams) {
		    $rootScope.previousState = from.name;
		    $rootScope.currentState = to.name;
		});
    }
}

ContainerCtrl.$inject = ['$rootScope', '$scope', '$stateParams'];

export default ContainerCtrl;