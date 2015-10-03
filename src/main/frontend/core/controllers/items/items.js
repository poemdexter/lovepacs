class ItemsCtrl {

    constructor($scope, $stateParams, ApiService){
    	var self = this;
    	
    	ApiService.getItems().then(function(data) {
    		self.items = data.data;
    	});
    }
}

ItemsCtrl.$inject = ['$scope', '$stateParams', 'ApiService'];

export default ItemsCtrl;