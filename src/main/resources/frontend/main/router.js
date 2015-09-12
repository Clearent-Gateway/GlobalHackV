angular.module('court')
    .config(['$routeProvider',function($routeProvider) {
        $routeProvider
            .when('/login', {
                templateUrl : '/templates/login/login.html',
                controller : 'LoginCtrl as loginCtrl'
            })
            .when('/payment', {
                templateUrl: '/templates/payment/payment.html',
                controller: 'PaymentCtrl as paymentCtrl'
            })
            .when('/search', {
                templateUrl: '/templates/citation/citationList.html',
                controller: 'CitationListCtrl as citationListCtrl'
            })
            /* Place new routes above these */
            .when('/', {
                redirectTo: '/login'
            })
            .otherwise({
                redirectTo: '/'
           });
}]);
