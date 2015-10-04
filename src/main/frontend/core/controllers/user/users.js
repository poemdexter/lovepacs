class UsersCtrl {

    constructor($scope, $stateParams, $q, ApiService){
    	var self = this;
    	
    	var userPromise = ApiService.getUsers();
    	var locationPromise = ApiService.getLocations();

    	$q.all([userPromise,locationPromise]).then(function(data) {
            $scope.users = data[0].data;

            var locations = {};
            angular.forEach(data[1].data, function(value, key) {
              locations[value.id] = value;
            });
            angular.forEach($scope.users, function(value, key) {
              value.locationObj = locations[value.location];
            });
        });
    }
}

UsersCtrl.$inject = ['$scope', '$stateParams', '$q', 'ApiService'];

export default UsersCtrl;