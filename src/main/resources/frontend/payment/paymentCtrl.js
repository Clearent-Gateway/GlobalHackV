angular.module('court')
    .controller('PaymentCtrl', ['CitationService','$log','$scope','$location','$modal',
    function(CitationService,$log,$scope,$location,RequestService,$modal) {

        var vm = this;
        vm.result = {};
        vm.post = post;
        vm.isValidTransaction = isValidTransaction;
        vm.modalOpen = false;
        vm.removeAlpha = removeAlpha;
        vm.citations = CitationService.getCitations();
        vm.paymentTotal = 0;
        vm.cancel = cancel;

        function getPaymentTotal(){
            var amount=0;
            for(var i=0;i<vm.citations.length;i++){
                amount+= parseFloat(vm.citations[i].amount);
            console.log("(vm.citations[i].amount=" + vm.citations[i].amount);
            console.log("amount=" + amount);
            }
            vm.paymentTotal = amount;
            if(!amount>0){
                $location.path('search');
            }
        }

        getPaymentTotal();

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

        function post(tran,address,shippingAddress,type){
            var transaction = JSON.parse(JSON.stringify(tran));
            $scope.submitted = true;
            vm.result={};

            //console.dir(transaction);

            // Set the type to SALE if there isn't a type since this is the default behavior
            if (typeof type == 'undefined') {
                type = 'SALE';
            }

            // Confirm form is valid
            if(isValidTransaction(type)){

                // Add type
                transaction.type = type;
                if(transaction.amount!=null && transaction.amount.length >0) {
                    transaction.amount =  stripAlpha(transaction.amount,true);
                }
                if(transaction.card!=null && transaction.card.length >0) {
                    transaction.card =  stripAlpha(transaction.card);
                }
                if(transaction['exp-date']!=null && transaction['exp-date'].length >0) {
                    transaction['exp-date'] =  stripAlpha(transaction['exp-date']);
                }

                if(vm.swiped){
                    delete transaction.card;
                }else{
                    delete transaction['track-format'];
                    delete transaction['encrypted-track-data'];
                }

                // Add address
                transaction.billing = address;
                transaction.shipping = shippingAddress;
                //$log.log("Processing Transaction: " + JSON.stringify(transaction));

                $scope.isSaving = true;
                var request = RequestService.getRequestObject(transaction);
                RequestService.send(request)
                    .success(function(data){
                        $scope.isSaving = false;
                        $scope.transactionComplete = true;
                        vm.tranid = data.payload.transaction.id;
                        if(!vm.hasTouch){
                            var messageText = "Transaction " + data.payload.transaction.id;
                            MessageService.broadcastWithExpiration({type:'success',id:'transactionSuccess',text:messageText});
                            window.removeEventListener('resize', resizeCanvas);
                            $location.path('/receipt').search({id:vm.tranid});
                        }
                        //$location.path('/transactionDisplay').search({id:data.payload.transaction.id});
                    })
                    .error(function(data){
                        $scope.isSaving = false;
                        if(data===null){
                            vm.result.message="FAILED";
                            vm.result.status="error";
                        }else{
                            vm.result.status=data.status;
                            if (typeof data.payload.transaction != 'undefined') {
                                vm.result.result = data.payload.transaction.result;
                                vm.result.message = data.payload.transaction['display-message'];
                                vm.result.resultcode = data.payload.transaction['result-code'];
                            } else {
                                vm.result.result = data.payload.result;
                                vm.result.message = data.payload.error['error-message'];
                                vm.result.resultcode = data.payload.error['result-code'];
                            }
                        }
                    });
            }
            // Makes testing easier :)
            return vm;
        }

        var signatureCanvasWrapper, signatureCanvas, screenRatio, signaturePad;

        function isValidTransaction(type) {
            if(type){
                if(BusinessService.getActiveBusinessAccount()){
                    if ($scope.transactionForm.$valid){
                        return true;
                    }else{
                        createError("Please check your form for errors.");
                    }
                }else{
                    createError("Please select an active account.");
                    return false;
                }
            }else{
                createError("Please select transaction type.");
                return false;
            }
        }


    }]);
