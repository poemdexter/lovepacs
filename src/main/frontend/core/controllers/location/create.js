class LocationCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;

        $scope.location = {
            "enabled": true,
            "id": null,
            "inventory": [],
            "name": null
        };

        $scope.save = function() {
            ApiService.createLocation($scope.location).then(function(data) {
                $state.go("container.location", {'id':data.data.id}, { reload: true });
            });
        };
    }
}

LocationCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default LocationCtrl;