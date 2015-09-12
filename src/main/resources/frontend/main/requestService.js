angular
    .module('court')
    .service('RequestService', ['$http', '$log', '$cookies',
        function RequestService($http, $log, $cookies) {

        var baseUrl = "localhost:8080";

        this.setBaseUrl = function(url){
            baseUrl = url;
        };

        this.getBaseUrl = function(){
            return baseUrl;
        };


        this.getRequestObject = function (saleRequest) {
            return {
                method: 'POST',
                url: 'https://'+this.getBaseUrl()+'/rest/v2/transactions',
                headers: {
                    'Content-Type': 'application/json',
                    'accept': 'application/json'
                },
                data: saleRequest
            };
        };

         this.getCitation = function (urlString) {
            var url = 'http://'+this.getBaseUrl()+'/citation';
            if (urlString != null) {
                url = url + urlString;
            }
            var getReq = {
                method: 'GET',
                url: url,
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            };
            return this.send(getReq);
        };

        this.send = function (req){
            return $http(req);
        };

    }]);
