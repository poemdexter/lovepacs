class InventoryCtrl {

    constructor($scope, $stateParams, ApiService){
    	var self = this;
    	
    	ApiService.getItems().then(function(data) {
    		self.items = data.data;
    	});
    }
}

InventoryCtrl.$inject = ['$scope', '$stateParams', 'ApiService'];

export default InventoryCtrl;