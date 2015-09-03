var app = angular.module('app', [
    'ngRoute',
    'batchModule'
]);

angular.module('batchModule', []);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/batches', {
                templateUrl: 'views/batchView.html',
                controller: 'BatchCtrl'
            })
            .when('/template', {
                templateUrl: 'views/dummyView.html'
            })
            .otherwise({
                redirectTo: '/batches'
            });
    }
]);

