class PackCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;
    	this._$state = $state;

    	ApiService.getPack($stateParams.id).then(function(data) {
    		self.pack = data.data;
    	});
    }
}

PackCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default PackCtrl;