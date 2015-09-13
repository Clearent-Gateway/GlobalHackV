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
                method: 'POST',
                url: 'http://'+this.getBaseUrl()+paymentController,
                headers: {
                    'Content-Type': 'application/json',
                    'accept': 'application/json'
                },
                data: paymentDetails
            };
        };

        this.send = function (req){
            return $http(req);
        };

    }]);
