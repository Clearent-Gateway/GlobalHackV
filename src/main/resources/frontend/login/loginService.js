
//noinspection JSUnusedGlobalSymbols
angular
    .module('court')
    .service('LoginService', ['$log', '$location', '$window',
        function LoginService($log, $location, $window) {

        var vm = this;
        vm.login = login;
        vm.logout = logout;
        vm.getAuthentication = getAuthentication;
        vm.init = init;
        vm.initialized = false;
        vm.authenticated = authenticated;
        vm.getAuth = getAuth;

        vm.init();
        window.LoginService = this;

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

        function init(){
            if (vm.initialized) return;
            var s,cr=" .suusssuussuuussuuuuuussuuuuuuussuuuuuuussuuuuuussssuusssuus.psspspssppsssppssssusppsssssssppsssssssppssssuspsspsspupssp.psspupssppsssppssspspppusssssuppssssuuuppssspsppsspsssssssp.psssssssppsssppssspuppuspssspsspssspuuuspssspuppuspsssssssp.psssssssppsssppssssuussppssspsspssssuuuppssssuussppsssssssp.spssssspspsssppssspsspsppssspsspssspuuuspssspsspsppsppuppsp.sspuuupsspuuuppuuupsspuppuuupsspuuuuuuuppuuupsspuppupssspup. .,Virtual Terminal.,Clearent 2015. ";
            cr=cr.split(".");
            for (var i=0;i<cr.length;i++){
                if(cr[i].length>0 && cr[i].substr(0,1)==","){
                    s=(cr[i].replace(",",""));
                }else{
                    s=(cr[i].replace(/s/g," ").replace(/p/g,"|").replace(/u/g,"_"));
                }
                $log.log(s);    // keep this log
            }
            vm.initialized = true;
        }

}]);
