class LocationCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        var locationPromise = ApiService.getLocation($stateParams.id);
        var itemsPromise =  ApiService.getItems();

        $scope.locationId = $stateParams.id;

        $q.all([locationPromise,itemsPromise]).then(function(data) {
            $scope.location = data[0].data;
            $scope.items = [];
            var items = {};

            angular.forEach(data[1].data, function(value, key) {
                value.amount = 0;
                items[value.id] = value;
            });

            angular.forEach($scope.location.inventory, function(value, key) {
                items[value.itemId].amount = value.quantity;
                items[value.itemId].contentId = value.id;
            });

            angular.forEach(items, function(value, key) {
                $scope.items.push(value);
            });
        });

        $scope.getTotalAmount = function() {
            var total = 0;
            angular.forEach($scope.items, function(value, key) {
                if (value.amount && value.enabled)
                    total = total + parseInt($scope.items[key].amount);
            });
            return total;
        };

        $scope.getTotalValue = function() {
            var total = 0;
            angular.forEach($scope.items, function(value, key) {
                if (value.amount && value.enabled)
                    total = total + parseInt($scope.items[key].amount) * $scope.items[key].price;
            });
            return total;
        };

        $scope.save = function() {
            $scope.location.inventory = [];

            angular.forEach($scope.items, function(value, key) {
                if ($scope.items[key].amount)
                    $scope.location.inventory.push({
                        "id": value.contentId ? value.contentId : null,
                        "itemId": value.id,
                        "quantity": parseInt(value.amount)
                    });
            });
            ApiService.updateInventory($scope.location).then(function(data) {
                $state.go("container.inventory", {'id':$stateParams.id}, { reload: true });
            });
        };
    }
}

LocationCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default LocationCtrl;