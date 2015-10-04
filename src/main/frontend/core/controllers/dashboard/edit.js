class PlanCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        var planPromise = ApiService.getPlan($stateParams.id);
        var packPromise = ApiService.getPacks();
        $scope.title = "Edit Plan";

        $q.all([planPromise,packPromise]).then(function(data) {
            $scope.plan = data[0].data;
            $scope.packs = [];

            var packs = {};
            angular.forEach(data[1].data, function(value, key) {
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

PlanCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default PlanCtrl;