angular
    .module('court')
    .controller('LoginCtrl', ['$scope','$modal', '$location', function($scope,$modal,$location) {

        var vm = this;
        vm.credentials = {
            username: "",
            password: ""
        };
        vm.login = login;

        $scope.authenticated = authenticated;
        $scope.isSaving = false;

        $scope.$on('savingStatusChange', function() {
            $scope.isSaving = BroadcastService.message;
        });

        // Logs user in with credentials
        function login(){
            LoginService.login(vm.credentials);
        }

        function authenticated(){
            return LoginService.authenticated();
        }

        // Open a modal dialog for the forgot
        // password link
        $scope.open  = function() {
            var instance = $modal.open({
                animation: true,
                templateUrl: 'help.html',
                controller: 'LoginModalCtrl',
                backdrop: false,
                forgotPassword: true
            });
        };

        $scope.$watch(
            function(scope) {
                return scope.authenticated();
            },
            function(authenticated) {

                if(authenticated){
                    // user is authenticated so we shouldn't still be on login page

                    var Auth = LoginService.getAuth();
                    if(Auth.jwtRequest && Auth.jwtRequest != '' && Auth.jwtRequest != '/login' && Auth.jwtRequest != '/'){
                        $location.url(Auth.jwtRequest);
                        LoginService.getAuth().jwtRequest = "";
                    }else{
                        $location.url("/terminal");
                    }

                }
            }
        );



    }]).controller('LoginModalCtrl', ['$scope','$modalInstance', function($scope,$modalInstance) {
        $scope.forgotPassword = true;
        $scope.close = function() {
            $modalInstance.dismiss('cancel');
        }
    }]);