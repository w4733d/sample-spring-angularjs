function BatchCtrl($scope, batchService) {

    batchService.getBatches(function (batches) {
        $scope.batches = batches;
    });

    $scope.onCheckBoxClicked = function (batch) {
        batchService.onCheckBoxClicked(batch);
    };

    $scope.approve = function () {
        batchService.approve();
    };

    $scope.reject = function () {
        batchService.reject();
    };

    $scope.disableApproveButton = function() {
        return batchService.disableApproveButton();
    }
}

angular.module('batchModule').controller('BatchCtrl', BatchCtrl);