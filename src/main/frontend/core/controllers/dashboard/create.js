class PlanCtrl {

    constructor($rootScope, $scope, $stateParams, $state, ApiService){
    	var self = this;
        $scope.title = "Create Plan";
        var packPromise = ApiService.getPacks();

        $scope.$watch('user', function(val) {
            if (val) {
            	$scope.plan = {
                    "id":null,
                    "location":$rootScope.user.location,
                    "packDate":new Date(),
                    "enabled":true
                };
            }
        });

        packPromise.then(function(data) {
            $scope.packs = [];

            var packs = {};
            angular.forEach(data.data, function(value, key) {
              packs[value.id] = value;
            });
            angular.forEach($scope.plan.planBoxes, function(value, key) {
              packs[value.boxId].save = true;
              packs[value.boxId].quantity = value.quantity;
            });
            angular.forEach(packs, function(value, key) {
              $scope.packs.push(value);
            });
        });

        $scope.disable = function(val) {
            if (!val.save) {
                val.quantity = '';
            }
        };
        $scope.enable = function(val) {
            if (val.quantity) {
                val.save = true;
            } else {
                val.save = false;
            }
        };

        $scope.save = function() {
            $scope.plan.planBoxes = [];

            angular.forEach($scope.packs, function(pack) {
              if(pack.save)
                  $scope.plan.planBoxes.push({
                    "id":null,
                    "quantity":pack.quantity,
                    "boxId":pack.id,
                    "planId":null
                  });
            });

            ApiService.updatePlan($scope.plan).then(function(data) {
                $state.go("container.dashboard", null, { reload: true });
            });
        };
    }
}

PlanCtrl.$inject = ['$rootScope', '$scope', '$stateParams', '$state', 'ApiService'];

export default PlanCtrl;