angular
    .module('court')
    .service('RequestService', ['$http', '$log',
    function RequestService($http, $log) {

        var baseUrl = "localhost:8080";
        var searchController = "/citation";
        var paymentController = "/payment";

        this.setBaseUrl = function(url){
            baseUrl = url;
        };

        this.getBaseUrl = function(){
            return baseUrl;
        };

        this.getSearchObject = function(searchCriteria){
            return {
                method: 'GET',
                url: 'http://'+this.getBaseUrl() + searchController + searchCriteria,
                headers: {
                    'Content-Type': 'application/json',
                    'accept': 'application/json'
                }
            };
        };

        this.getPaymentRequest = function(paymentDetails){
            return {
                method: 'GET',
                url: 'http://'+this.getBaseUrl() + paymentController + paymentDetails,
                headers: {
                    'Content-Type': 'application/json',
                    'accept': 'application/json'
                }
            };

        };

        this.send = function (req){
            return $http(req);
        };

    }]);
