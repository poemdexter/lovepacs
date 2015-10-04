class LocationCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        $scope.title = "Create Location";

        $scope.location = {
            "enabled": true,
            "id": null,
            "inventory": [],
            "name": null
        };

        $scope.save = function() {
            ApiService.createLocation($scope.location).then(function(data) {
                $state.go("container.locations", {'id':data.data.id}, { reload: true });
            });
        };
    }
}

LocationCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default LocationCtrl;