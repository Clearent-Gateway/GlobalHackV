angular
    .module('court')
    .service('CitationService', ['$http', '$log', 'RequestService',
    function CitationService($http, $log, RequestService) {

        var vm = this;
        vm.getCitations = getCitations;
        vm.setCitations = setCitations;
        vm.citations = [];
        vm.firstName = "";
        vm.lastName = "";

        function getCitations(){
            return vm.citations;
        }

        function setCitations(citations){
            vm.citations = citations;
        }


    }]);
