angular.module('court')
    .controller('PaymentCtrl', ['CitationService','RequestService','$log','$scope','$location','$modal',
    function(CitationService,RequestService,$log,$scope,$location,$modal) {

        var vm = this;
        vm.result = {};
        vm.makePayment = makePayment;
        vm.test= test;
        vm.removeAlpha = removeAlpha;
        vm.citations = CitationService.getCitations();
        vm.paymentTotal = 0;
        vm.cancel = cancel;
        vm.result = "";
        $scope.payment = {};

        function getPaymentTotal(){
            var amount=0;
            for(var i=0;i<vm.citations.length;i++){
                amount+= parseFloat(vm.citations[i].totalAmount);
                console.log("(vm.citations[i].amount=" + vm.citations[i].totalAmount);
                console.log("amount=" + amount);
            }
            vm.paymentTotal = amount.toFixed(2);
            $scope.payment.amount = vm.paymentTotal;
            if(!amount>0){
                //$location.path('search');
            }
        }

        getPaymentTotal();

        function test(payment) {
            console.log(payment);

            var paymentData = {};
            paymentData.amountToPay = stripAlpha(payment.amount, true);
            paymentData.cardNumber = stripAlpha(payment.card, false);
            paymentData.expDate = stripAlpha(payment.expDate, false);
            console.log(paymentData);

            var violations = [];
            for (var i = 0; i < vm.citations.length; i++) {
                for (var j = 0; j < vm.citations[i].violations.length; j++) {
                    violations.push(vm.citations[i].violations[j]);
                }
            }
            paymentData.violations = violations;
            var req = RequestService.getPaymentRequest(paymentData);
            console.log(req);
            console.log('-------------------------------------------------------');
            console.log(JSON.stringify(req));
            console.log('-------------------------------------------------------');
        }
        function makePayment(payment){
                console.log(payment);

                var paymentData = {};
                paymentData.amountToPay = stripAlpha(payment.amount,true);
                paymentData.cardNumber = stripAlpha(payment.card,false);
                paymentData.expDate = stripAlpha(payment.expDate,false);
                console.log(paymentData);

                var violations = [];
                for (var i=0;i<vm.citations.length;i++){
                    for(var j=0;j<vm.citations[i].violations.length;j++) {
                        violations.push(vm.citations[i].violations[j]);
                    }
                }
                paymentData.violations = violations;
                var req = RequestService.getPaymentRequest(paymentData);
                console.log(req);
                console.log('-------------------------------------------------------');
                console.log (JSON.stringify(req));
                console.log('-------------------------------------------------------');

            RequestService.send(req).then(
                function (result) {
                    console.log (JSON.stringify(result));
                    if (result.status == 200) {
                        vm.result = "ok";
                    }

                },
                function (reason) {
                    // need to show error
                    console.log ("bad----" + JSON.stringify(reason));
                    vm.result = "fail";
                });

        }

        function cancel(){
            $location.path('search');
        }

        function removeAlpha(prop,allowDecimal){
            // called on ng-blur of certain fields
            if(!$scope.sale) $scope.sale = {};
            var s = $scope.transactionForm[prop].$viewValue;
            if(angular.isDefined(s) && s.length>0){
                $scope.sale[prop] = stripAlpha(s,allowDecimal);
            }
        }

        function stripAlpha(s,allowDecimal){
            if(allowDecimal){
                return s.replace(/[^0-9\.]+/g, '');
            } else {
                return s.replace(/[^0-9]+/g, '');
            }
        }

    }]);
