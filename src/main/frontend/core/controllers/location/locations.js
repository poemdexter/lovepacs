class LocationsCtrl {

    constructor($scope, $stateParams, ApiService){
    	var self = this;
    	
    	ApiService.getLocations().then(function(data) {
    		$scope.locations = data.data;
    	});
    }
}

LocationsCtrl.$inject = ['$scope', '$stateParams', 'ApiService'];

export default LocationsCtrl;