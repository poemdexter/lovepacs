class ItemCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;
    	this._$state = $state;
    	this._$stateParams = $stateParams;
    	this._ApiService = ApiService;

    	ApiService.getItem($stateParams.id).then(function(data) {
    		self.item = data.data;
    	});
    }

    save() {
    	var self = this;
    	this._ApiService.updateItem(this._$stateParams.id, self.item).then(function(data) {
    		self._$state.go("container.items");
    	});
    }
}

ItemCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default ItemCtrl;