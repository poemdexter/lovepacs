class LocationCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        $scope.title = "Edit Location";

        ApiService.getLocation($stateParams.id).then(function(data) {
            $scope.location = data.data;
        });

        $scope.save = function() {
            ApiService.updateLocation($scope.location).then(function(data) {
                $state.go("container.locations", {'id':data.data.id}, { reload: true });
            });
        };
    }
}

LocationCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default LocationCtrl;