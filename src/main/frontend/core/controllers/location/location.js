class LocationCtrl {

    constructor($scope, $stateParams, ApiService){
    	var self = this;
    	
    	ApiService.getLocation($stateParams.id).then(function(data) {
    		$scope.location = data.data;
    	});
    	
    	ApiService.getItems($stateParams.id).then(function(data) {
    		$scope.items = {};
    		angular.forEach(data.data, function(value, key) {
			  $scope.items[value.id] = value;
			});
    	});
    }
}

LocationCtrl.$inject = ['$scope', '$stateParams', 'ApiService'];

export default LocationCtrl;