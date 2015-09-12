
//noinspection JSUnusedGlobalSymbols
angular
    .module('court')
    .service('LoginService', ['$log', '$location', '$window',
        function LoginService($log, $location, $window) {

        var vm = this;
        vm.login = login;
        vm.logout = logout;
        vm.getAuthentication = getAuthentication;
        vm.initialized = false;
        vm.authenticated = authenticated;
        vm.getAuth = getAuth;

        function authenticated(){
            return true;
        }

        function getAuth(){
            return {'authenticated':true};
        }

        function login(){
            return true;
        }

        function logout(){
            return false;
        }

        function getAuthentication(){
            return true;
        }


}]);
