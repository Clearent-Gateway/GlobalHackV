angular
    .module('court')
    .service('CitationService', ['$http', '$log', 'RequestService',
        function ConstantsService($http, $log, RequestService) {

        var vm = this;
        vm.getCitations = getCitations;
        vm.setCitations = setCitations;
        vm.citations = [];

        function getCitations(){
            return vm.citations;
        }

        function setCitations(citations){
            vm.citations = citations;
        }


    }]);
