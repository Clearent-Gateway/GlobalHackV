angular
    .module('court')
    .controller('CitationListCtrl', ['CitationService','$scope', '$modal', '$location',
        function (CitationService, $scope, $modal, $location) {

            var vm = this;
            vm.search = search;
            vm.goToPayment = goToPayment;
            vm.selectAllRows = selectAllRows;
            vm.updateSelectedCount = updateSelectedCount;
            vm.allItemsSelected = false;
            vm.selectedCount = 0;
            vm.citations = [];

            function selectAllRows(allSelected){
                vm.selectedCount = 0;
                for(var i = 0;i<vm.citations.length; i++){
                    vm.citations[i].isSelected = allSelected;
                }
                updateSelectedCount();
            }

            function updateSelectedCount(){
                vm.selectedCount = 0;
                for(var i = 0;i<vm.citations.length; i++){
                    if(vm.citations[i].isSelected){
                        vm.selectedCount++;
                    }
                }
                vm.allItemsSelected = (vm.selectedCount==vm.citations.length)&&vm.citations.length>0;
            }

            function search(){
                // run citation query

                // the results
                var newCitations = [
                    {
                        "fName": "NIck",
                        "lName": "Willard",
                        "address": "222 S Central",
                        "city": "Clayton",
                        "state": "MO",
                        "dateOfBirth": "3/9/2015",
                        "driversLicense": "D1002483",
                        "citationNumber": "789674515",
                        "citationDate": "4/29/2015",
                        "municipality": "Florissant",
                        "courtDate": "11/15/2015",
                        "courtAddress": "7422 Eunice Avenue",
                        "amount":"22.50"
                    },
                    {
                        "fName": "John",
                        "lName": "Smith",
                        "address": "222 S Central",
                        "city": "Clayton",
                        "state": "MO",
                        "dateOfBirth": "3/9/2015",
                        "driversLicense": "D1002483",
                        "citationNumber": "789674515",
                        "citationDate": "4/29/2015",
                        "municipality": "Florissant",
                        "courtDate": "11/15/2015",
                        "courtAddress": "7422 Eunice Avenue",
                        "amount":"88.50"
                    },
                    {
                        "fName": "Joe",
                        "lName": "Harkins",
                        "address": "222 S Central",
                        "city": "Clayton",
                        "state": "MO",
                        "dateOfBirth": "3/9/2015",
                        "driversLicense": "D1002483",
                        "citationNumber": "789674515",
                        "citationDate": "4/29/2015",
                        "municipality": "Florissant",
                        "courtDate": "11/15/2015",
                        "courtAddress": "7422 Eunice Avenue",
                        "amount":"55.00"
                    }, {
                        "fName": "Carl",
                        "lName": "Armbruster",
                        "address": "222 S Central",
                        "city": "Clayton",
                        "state": "MO",
                        "dateOfBirth": "3/9/2015",
                        "driversLicense": "D1002483",
                        "citationNumber": "789674515",
                        "citationDate": "4/29/2015",
                        "municipality": "Florissant",
                        "courtDate": "11/15/2015",
                        "courtAddress": "7422 Eunice Avenue",
                        "amount":"122.33"
                    },
                    {
                        "fName": "Gaby",
                        "lName": "Mino",
                        "address": "222 S Central",
                        "city": "Clayton",
                        "state": "MO",
                        "dateOfBirth": "3/9/2015",
                        "driversLicense": "D1002483",
                        "citationNumber": "789674515",
                        "citationDate": "4/29/2015",
                        "municipality": "Florissant",
                        "courtDate": "11/15/2015",
                        "courtAddress": "7422 Eunice Avenue",
                        "amount":"241.77"
                    }
                ];
                vm.citations = newCitations;
                vm.selectedCount = 0;
            }

            function goToPayment(){
                // set up citations to be paid
                var cit = [];
                for(var i = 0;i<vm.citations.length; i++){
                    if(vm.citations[i].isSelected) {
                        cit.push(vm.citations[i]);
                    }
                }
                // send this citations to the payment controllers
                CitationService.setCitations(cit);

                $location.path('payment');


            }

        }]);