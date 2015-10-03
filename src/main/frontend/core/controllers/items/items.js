class ItemsCtrl {

    constructor($scope, $stateParams, ApiService){
    	ApiService.getItems().then(function(data) {
    		$scope.items = data.data;
    	});
    }
}

ItemsCtrl.$inject = ['$scope', '$stateParams', 'ApiService'];

export default ItemsCtrl;