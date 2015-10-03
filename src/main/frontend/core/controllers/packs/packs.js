class PacksCtrl {

    constructor($scope, $stateParams, ApiService){
    	var self = this;
    	
    	ApiService.getPacks().then(function(data) {
    		self.packs = data.data;
    	});
    }
}

PacksCtrl.$inject = ['$scope', '$stateParams', 'ApiService'];

export default PacksCtrl;