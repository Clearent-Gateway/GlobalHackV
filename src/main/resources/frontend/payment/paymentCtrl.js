angular.module('virtermApp')
    .controller('TerminalCtrl', ['$log','$scope','$location','RequestService','BusinessService','$modal', 'BroadcastService', 'MessageService','AuthService',
    function($log,$scope,$location,RequestService,BusinessService,$modal, BroadcastService, MessageService, AuthService) {

        var vm = this;
        vm.result = {};
        vm.post = post;
        vm.postsig = postsig;
        vm.isValidTransaction = isValidTransaction;
        vm.openSwipe = openSwipe;
        vm.modalOpen = false;
        vm.removeAlpha = removeAlpha;
        vm.swiped=false;
        vm.clearMag = clearMag;
        vm.hasTouch=false;

        $scope.billingCollapsed  = true;
        $scope.shippingCollapsed = true;
        $scope.orderCollapsed = true;
        $scope.swiped = false;
        $scope.reading = false;
        $scope.transactionComplete = false;
        $scope.signaturePadVisible = false;

        var ua = navigator.userAgent;
        $scope.iOS = /(iPad|iPhone|iPod)/gi.test( ua );
        var webkit = /(WebKit)/gi.test( ua );
        $scope.mobileSafari = $scope.iOS && webkit && !ua.match(/CriOS/i);

        $scope.$on('cardSwipeComplete', function() {
            var message = BroadcastService.message;            
            if(!$scope.sale) $scope.sale = {};
            $scope.sale.card = message.maskedPan;
            $scope.sale['exp-date'] = message.expDate;
            $scope.sale['track-format'] = 'MAGTEK';
            $scope.sale['encrypted-track-data'] = message.magData;
            vm.swiped = true;
            $scope.swiped = true;
        });

        function openSwipe($event) {
            var target = $event.target;
            target.blur();
            if (vm.modalOpen){
                return;
            }

            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'swipeReady.html',
                controller: 'TransactionSwipeCtrl',
                size: 'sm',
                windowClass: 'center-modal'
            });

            modalInstance.opened.then(function () {
                vm.modalOpen = true;
            });

            modalInstance.result.then(function () {
                vm.modalOpen = false;
            }, function () {
                vm.modalOpen = false;
            });
        }

        function clearMag() {
            vm.swiped = false;
            $scope.swiped = false;
            $scope.sale.card = "";
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

        $scope.noSignature = function(){
            var messageText = "Transaction " + vm.tranid;
            MessageService.broadcastWithExpiration({type:'success',id:'transactionSuccess',text:messageText});
            window.removeEventListener('resize', resizeCanvas);
            $location.path('/receipt').search({id:vm.tranid});
        };

        $scope.showSignaturePad = function(){
            init();
            $scope.signaturePadVisible = true;
            setTimeout(resizeCanvas,5);
            setTimeout(resizeCanvas,50);
            setTimeout(resizeCanvas,500);
        };

        $scope.clear = function () {
            signaturePad.clear();
        };

        $scope.save = function () {
            if (signaturePad.isEmpty()) {
                vm.result.status = 'sigRequired';
                vm.result.message = "Please provide signature.";
            } else {
                //window.open(signaturePad.toDataURL());
                vm.result.status = '';
                postsig(signaturePad.toDataURL());
            }
        };

        function init(){
            signatureCanvasWrapper = document.getElementById("signature-pad");
            signatureCanvas = signatureCanvasWrapper.querySelector("canvas");
            // When zoomed out to less than 100%, for some very strange reason, some browsers report
            // devicePixelRatio as less than 1 and only part of the canvas is cleared then.
            screenRatio =  Math.max(window.devicePixelRatio || 1, 1);
            window.onresize = resizeCanvas;
            window.addEventListener('resize', resizeCanvas, false);
            resizeCanvas();
            signaturePad = new SignaturePad(signatureCanvas);
        }

        function resizeCanvas() {
            // Adjust canvas coordinate space taking into account pixel ratio to make
            // it look crisp on mobile devices. This also causes canvas to be cleared.
            if(!signatureCanvasWrapper) {
                return;
            }
            console.log('screenRatio=' + screenRatio);
            console.log('signatureCanvas.offsetWidth=' + signatureCanvas.offsetWidth);
            console.log('signatureCanvas.offsetHeight=' + signatureCanvas.offsetHeight);
            screenRatio =  Math.max(window.devicePixelRatio || 1, 1);
            signatureCanvas.width = Math.max(signatureCanvas.offsetWidth,500) * screenRatio;
            signatureCanvas.height = signatureCanvas.offsetWidth / 5  * screenRatio;
            signatureCanvas.getContext("2d").scale(screenRatio, screenRatio);
            if(signaturePad) { signaturePad.clear(); }
        }

        function postsig(base64data){
            $scope.submitted = true;
            vm.result={};

            var sigRequest = {};
            sigRequest['transaction-id'] = vm.tranid;
            sigRequest['base-64-image'] = base64data;
            sigRequest['created-by'] = AuthService.getAuth().name;

            var request = RequestService.getSigObject(sigRequest);

            $scope.isSaving = true;
            RequestService.send(request)
                .success(function(data){
                    console.log(data);
                    var messageText = "Transaction " + vm.tranid;
                    MessageService.broadcastWithExpiration({type:'success',id:'transactionSuccess',text:messageText});
                    window.removeEventListener('resize', resizeCanvas);
                    $location.path('/receipt').search({id:vm.tranid});
                })
                .error(function(data){
                    var messageText = "Transaction " + vm.tranid;
                    MessageService.broadcastWithExpiration({type:'success',id:'transactionSuccess',text:messageText});
                    messageText = "Transaction was successful, but unable to save signature. Please print and sign receipt.";
                    MessageService.broadcastError({id:'signatureSaveError',text:messageText});
                    window.removeEventListener('resize', resizeCanvas);
                    $location.path('/receipt').search({id:vm.tranid});
                });

            return vm;
        }

        function createError(message) {
            vm.result.status = "error";
            vm.result.message = message;
        }

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

        //init();

        /*
        // for testing or if we decide to enable on desktop
        window.addEventListener('mousemove', function setHasTouch () {
            vm.hasTouch = true;
            window.removeEventListener('mousemove', setHasTouch);
        }, false);
         */

        window.addEventListener('touchstart', function setHasTouch () {
            vm.hasTouch = true;
            window.removeEventListener('touchstart', setHasTouch);
        }, false);

    }])
    .controller('TransactionSwipeCtrl',['$scope', '$modalInstance', 'UtilService', 'BroadcastService',
        function ($scope, $modalInstance, UtilService, BroadcastService) {

            var vm = this;
            vm.message = {
                notReady: "Click or touch here to begin swipe . . .",
                isReady: "Ready for card swipe . . .",
                reading: "Reading card data . . .",
                readError: "Error reading card data, please try again . . ."
            };

            $scope.cancelReady = false;
            $scope.message = vm.message;
            $scope.reading = false;
            $scope.status = $scope.message.notReady;
            $scope.iOS = /(iPad|iPhone|iPod)/gi.test( navigator.userAgent );
            if(!$scope.iOS){
                $scope.af = "true";
            }

            $scope.setCancelReady = function(b) {
                $scope.cancelReady = b;
            };
            $scope.cancel = function(e) {
                $modalInstance.dismiss('cancel');
            };
            $scope.showLoading = function(){
                if (!$scope.reading){
                    $scope.reading = true;
                    $scope.status = $scope.message.reading;
                }
            };

            $scope.parseMag = function() {
                var data = $scope.magdata;
                var cardInfo = UtilService.parseMag(data);
                //console.dir(cardInfo);
                $scope.reading = false;
                if (cardInfo.hasTrack1 || cardInfo.hasTrack2){
                	BroadcastService.broadcast(cardInfo,'cardSwipeComplete');
                    $modalInstance.close();
                }else{
                    $scope.magdata = "";
                    $scope.status = $scope.message.readError;
                }
            };

            $scope.focusMag = function(){
                $scope.swipeReady = document.activeElement == document.magForm.magdata;
                document.magForm.magdata.focus();
            };

            $scope.magGotFocus = function(){
                if(!$scope.cancelReady) {
                    $scope.status = $scope.message.isReady;
                }
            };
            $scope.magLostFocus = function(){
                if(!$scope.cancelReady) {
                    try {
                        $scope.status = $scope.message.notReady;
                    } catch (e) {
                        // ignore if popup closing
                    }
                }
            };

    }]);
