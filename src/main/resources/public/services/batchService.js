function batchService($http, $route) {
    var batchesToProcess = [];

    this.approve = function approve() {
        $http.put('batches?status=APPROVED', batchesToProcess).success(function () {
            $route.reload();
            batchesToProcess = [];
        });
    };

    this.reject = function reject() {
        $http.put('batches?status=REJECTED', batchesToProcess).success(function () {
            $route.reload();
            batchesToProcess = [];
        });
    };

    this.onCheckBoxClicked = function (batch) {
        if (batch.selected) batchesToProcess.push(batch.batchId);
        else batchesToProcess.pop(batch.batchId);

        console.log(batchesToProcess);
    };

    this.getBatches = function (callback) {
        $http.get('batches').success(callback);
    };

    this.disableApproveButton = function() {
        return !batchesToProcess.length;
    }
}

angular.module('batchModule').service('batchService', batchService);