class ItemCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;
    	this._$state = $state;

    	ApiService.getItem($stateParams.id).then(function(data) {
    		self.item = data.data;
    	});
    }
}

ItemCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default ItemCtrl;