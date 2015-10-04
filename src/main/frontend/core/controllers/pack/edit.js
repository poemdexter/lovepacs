class PackCtrl {

    constructor($scope, $stateParams, $state, $q, ApiService){
    	var self = this;
        this._$state = $state;
        this._ApiService = ApiService;
        $scope.title = "Edit Box";

    	var packPromise = ApiService.getPack($stateParams.id);
        var itemsPromise =  ApiService.getItems();

        $q.all([packPromise,itemsPromise]).then(function(data) {
            $scope.pack = data[0].data;
            $scope.items = [];
            var items = {};

            angular.forEach(data[1].data, function(value, key) {
              items[value.id] = value;
            });

            angular.forEach($scope.pack.contents, function(value, key) {
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