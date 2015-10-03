class PackCtrl {

    constructor($scope, $stateParams, $state, ApiService){
    	var self = this;
        this._$state = $state;

    	ApiService.getPack($stateParams.id).then(function(data) {
    		self.pack = data.data;
    	});
        
        ApiService.getItems().then(function(data) {
            $scope.items = data.data;
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

    }
}

PackCtrl.$inject = ['$scope', '$stateParams', '$state', 'ApiService'];

export default PackCtrl;