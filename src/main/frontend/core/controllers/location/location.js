class LocationCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        var locationPromise = ApiService.getLocation($stateParams.id);
        var itemsPromise =  ApiService.getItems();

        $q.all([locationPromise,itemsPromise]).then(function(data) {
            $scope.location = data[0].data;
            $scope.items = {};

            angular.forEach(data[1].data, function(value, key) {
                value.amount = 0;
                $scope.items[value.id] = value;
            });

            angular.forEach($scope.location.inventory, function(value, key) {
                $scope.items[value.itemId].amount = value.quantity;
                $scope.items[value.itemId].contentId = value.id;
            });
        });

        $scope.getTotalAmount = function() {
            var total = 0;
            angular.forEach($scope.items, function(value, key) {
                if ($scope.items[key].amount)
                    total = total + parseInt($scope.items[key].amount);
            });
            return total;
        };

        $scope.getTotalValue = function() {
            var total = 0;
            angular.forEach($scope.items, function(value, key) {
                if ($scope.items[key].amount)
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
                $state.go("container.location", {'id':$stateParams.id}, { reload: true });
            });
        };
    }
}

LocationCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default LocationCtrl;