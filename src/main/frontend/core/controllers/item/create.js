class ItemCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;

    	$scope.item = {
            "id":null,
            "name": null,
            "value": null,
            "enabled": true
        };

        $scope.save = function() {
            ApiService.saveItem($scope.item).then(function(data) {
                $state.go("container.items", null, { reload: true });
            });
        };
    }
}

ItemCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default ItemCtrl;