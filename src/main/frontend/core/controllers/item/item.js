class ItemCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;

    	ApiService.getItem($stateParams.id).then(function(data) {
    		$scope.item = data.data;
    	});

        $scope.save = function() {
            ApiService.updateItem($stateParams.id, $scope.item).then(function(data) {
                $state.go("container.items");
            });
        }
    }
}

ItemCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default ItemCtrl;