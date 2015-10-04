class ItemCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;
        $scope.title = "Edit Item";

    	ApiService.getItem($stateParams.id).then(function(data) {
    		$scope.item = data.data;
    	});

        $scope.save = function() {
            ApiService.updateItem($scope.item).then(function(data) {
                $state.go("container.items", null, { reload: true });
            });
        };
    }
}

ItemCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default ItemCtrl;