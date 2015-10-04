class UserCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;
        $scope.title = "Create User";

    	$scope.editUser = {
            'username': '',
            'location': null,
            'enabled': true
        };

        $scope.save = function() {
            ApiService.saveUser($scope.editUser).then(function(data) {
                $state.go("container.users", null, { reload: true });
            });
        };
    }
}

UserCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default UserCtrl;