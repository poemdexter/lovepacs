class PackCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        this._$state = $state;
        this._ApiService = ApiService;

        var itemsPromise =  ApiService.getItems();

        $q.all([itemsPromise]).then(function(data) {
            $scope.pack = {
                "contents": [],
                "enabled": true,
                "id": null,
                "name": null
            };
            $scope.items = {};

            angular.forEach(data[0].data, function(value, key) {
              $scope.items[value.id] = value;
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
            $scope.pack.contents = [];

            angular.forEach($scope.items, function(value, key) {
                if ($scope.items[key].amount)
                    $scope.pack.contents.push({
                        "id": value.contentId ? value.contentId : null,
                        "itemId": value.id,
                        "quantity": parseInt(value.amount)
                    });
            });

            ApiService.updatePack($scope.pack).then(function(data) {
                $state.go("container.packs", null, { reload: true });
            });
        };

    }
}

PackCtrl.$inject = ['$scope', '$stateParams', '$state', '$q', 'ApiService'];

export default PackCtrl;