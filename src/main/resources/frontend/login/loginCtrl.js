angular
    .module('court')
    .controller('LoginCtrl', ['LoginService','$scope','$modal', '$location',
        function(LoginService,$scope,$modal,$location) {

        var vm = this;
        vm.credentials = {
            username: "",
            password: ""
        };
        vm.login = login;
        vm.hasLoginError = false;

        $scope.authenticated = authenticated;
        $scope.isSaving = false;


        // Logs user in with credentials
        function login(){
            //LoginService.login(vm.credentials);
            $location.path('search');
        }

        function authenticated(){
            return LoginService.authenticated();
        }

    }]);