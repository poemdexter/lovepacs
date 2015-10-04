class BrandCtrl {

    constructor($rootScope, $scope, $stateParams, $q, $state, $filter, ApiService){
    	$scope.Math = window.Math;

    	$scope.$watch('user', function(val) {
    		if (val) {
    			var planPromise = ApiService.getPlans($rootScope.user.location);
		        var packPromise =  ApiService.getPacks();
		        var itemPromise =  ApiService.getItems();
		        var locationPromise =  ApiService.getLocation($rootScope.user.location);

		        $q.all([planPromise,packPromise,locationPromise, itemPromise]).then(function(data) {
		            $scope.plans = $filter('filter')($filter('orderBy')(data[0].data, 'packDate'),{enabled: true });
		            $scope.location = data[2].data;
		            $scope.packs = {};
		            $scope.items = {};
		            $scope.inventory = {};

		            angular.forEach(data[1].data, function(value, key) {
		              $scope.packs[value.id] = value;
		            });
		            angular.forEach(data[3].data, function(value, key) {
		              $scope.items[value.id] = value;
		            });
		            angular.forEach($scope.location.inventory, function(value, key) {
		              $scope.inventory[value.itemId] = value;
		            });

		            angular.forEach($scope.plans, function(plan) {
		                plan.packs = [];
		                plan.items = [];
		                plan.minus = false;
		                var items = {};

		                angular.forEach(plan.planBoxes, function(planBox) {
		                	var pack = $scope.packs[planBox.boxId];
		                	pack.quantity = planBox.quantity;
		                	plan.packs.push(angular.copy(pack));

		                	angular.forEach(pack.contents, function(content) {
		                		var item = items[content.itemId];
		                		var inventory_item = $scope.inventory[content.itemId];
		                		if(!inventory_item) {
		                			$scope.inventory[content.itemId] = {quantity: 0};
		                			inventory_item = $scope.inventory[content.itemId];
		                		}
		                		if(!item) {
		                			items[content.itemId] = $scope.items[content.itemId];
		                			item = items[content.itemId];
		                			item.quantity = 0;
		                			item.surplus = inventory_item.quantity;
		                		}
								item.quantity = item.quantity + content.quantity * pack.quantity;
								item.surplus = item.surplus - content.quantity * pack.quantity;
								inventory_item.quantity = inventory_item.quantity - content.quantity * pack.quantity;
							});
		               	});

			            angular.forEach(items, function(item) {
			              plan.items.push(angular.copy(item));
			              if (item.surplus < 0 && item.enabled)
			              	plan.minus = true;
			            });
		            });
		        });
		    }
	    });

		$scope.complete = function(id) {
			ApiService.completePlan(id).then(function(data) {
				$state.go("container.dashboard", null, { reload: true });
			});
		};

    }
}

BrandCtrl.$inject = ['$rootScope', '$scope', '$stateParams', '$q', '$state', '$filter', 'ApiService'];

export default BrandCtrl;