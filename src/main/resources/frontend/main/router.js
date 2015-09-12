angular.module('virtermApp')
    .config(['$routeProvider',function($routeProvider) {
        $routeProvider
            .when('/search', {
                templateUrl: '/templates/sale/sale.html',
                controller: 'SaleCtrl as saleCtrl'
            }).when('/receipt', {
                templateUrl: '/templates/receipt/combinedReceipt.html',
                controller: 'ReceiptCtrl as receiptCtrl'
            }).when('/transactions', {
                templateUrl: '/templates/transaction/transactions.html',
                controller: 'TransactionsCtrl as transactionsCtrl',
                crumb: {
                    title: 'Transactions',
                    path: 'transactions'
                }
            }).when('/umrefund', {
                templateUrl: '/templates/umrefund/umrefund.html',
                controller: 'UmrefundCtrl as umrefundCtrl'
            }).when('/refund', {
                templateUrl : '/templates/transaction/refund.html',
                controller : 'RefundCtrl',
                crumb: {
                    title: 'Transactions',
                    path: 'transactions'
                }
            }).when('/void', {
                templateUrl : '/templates/transaction/void.html',
                controller : 'VoidCtrl',
                crumb: {
                    title: 'Transactions',
                    path: 'transactions'
                }
            }).when('/capture', {
                templateUrl : '/templates/transaction/capture.html',
                controller : 'CaptureCtrl',
                crumb: {
                    title: 'Transactions',
                    path: 'transactions'
                }
            }).when('/transactionDisplay', {
                templateUrl : '/templates/transaction/transactionDisplay.html',
                controller : 'TransactionDisplayCtrl as transactionDisplayCtrl',
                crumb: {
                    title: 'Transactions',
                    path: 'transactions'
                }
            }).when('/tipadj', {
                templateUrl : '/templates/transaction/tipadj.html',
                controller : 'TipAdjCtrl',
                crumb: {
                    title: 'Transactions',
                    path: 'transactions'
                }
            }).when('/login', {
                templateUrl : '/templates/login/login.html',
                controller : 'LoginCtrl as loginCtrl'
            }).when('/cardauth', {
                templateUrl : '/templates/cardAuth/cardAuth.html',
                controller : 'CardAuthCtrl as cardAuthCtrl'
            }).when('/forcedsale', {
                templateUrl : '/templates/forcedSale/forcedSale.html',
                controller : 'ForcedSaleCtrl as forcedSaleCtrl'
            }).when('/terminal', {
                templateUrl : '/templates/terminal/terminal.html',
                controller : 'TerminalCtrl as terminalCtrl',
                crumb: {
                    title: 'Take A Payment',
                    path: 'terminal'
                }
            }).when('/batches', {
                templateUrl : '/templates/batches/batches.html',
                controller : 'BatchesCtrl as batchesCtrl',
                crumb: {
                    title: 'Batches',
                    path: 'batches'
                }
            }).when('/batch', {
                templateUrl: '/templates/batches/batch.html',
                controller: 'BatchCtrl as batchCtrl',
                crumb: {
                    title: 'Batches',
                    path: 'batches'
                }
            })
            /* Place new routes above these */
            .when('/', {
                redirectTo: '/terminal'
            })
            .otherwise({
                redirectTo: '/'
           });
}]).run(['$rootScope', '$location', '$window', function($rootScope, $location, $window) {
    $rootScope.$on('$routeChangeSuccess', function(event, current, previous) {
        if (typeof current.$$route.crumb != 'undefined') {
            $rootScope.crumb = current.$$route.crumb;
            if ($window.ga) $window.ga('send', 'pageview', { page: $location.path() });
        }
    });
}]);
