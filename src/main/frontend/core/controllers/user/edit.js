class UserCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        $scope.title = "Edit User";

        var userPromise = ApiService.getUser($stateParams.id);
        var locationPromise = ApiService.getLocations();

        $q.all([userPromise,locationPromise]).then(function(data) {
            $scope.editUser = data[0].data;
            $scope.locations = data[1].data;

            console.log($scope.editUser);
            console.log($scope.locations);
        });

        $scope.save = function() {
            ApiService.updateUser($scope.editUser).then(function(data) {
                $state.go("container.users", null, { reload: true });
            });
        };
    }
}

UserCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default UserCtrl;