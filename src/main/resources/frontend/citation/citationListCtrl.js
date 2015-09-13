angular
    .module('court')
    .controller('CitationListCtrl', ['RequestService','CitationService','$scope', '$modal', '$location',
        function (RequestService, CitationService, $scope, $modal, $location) {

            var vm = this;
            vm.searchCitations = searchCitations;
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

            function searchCitations(search){
                // run citation query
                var searchCriteria = "";
                var joiner = "?";
                if(search.firstName&&search.firstName.length>0){
                    searchCriteria += joiner + "firstName=" + search.firstName;
                    joiner = "&";
                }
                if(search.lastName&&search.lastName.length>0){
                    searchCriteria += joiner + "lastName=" + search.lastName;
                    joiner = "&";
                }
                if(search.dateOfBirth&&search.dateOfBirth.length>0){
                    searchCriteria += joiner + "dateOfBirth=" + search.dateOfBirth;
                    joiner = "&";
                }
                if(search.driversLicense&&search.driversLicense.length>0){
                    searchCriteria += joiner + "driversLicesnse=" + search.driversLicense;
                    joiner = "&";
                }
                if(search.citationNumber&&search.citationNumber.length>0){
                    searchCriteria += joiner + "citationNumber=" + search.citationNumber;
                    joiner = "&";
                }

                var req = RequestService.getSearchObject(searchCriteria);
                console.log(search);
                console.log(searchCriteria);
                console.log(req);

                RequestService.send(req).then(
                    function (result) {
                        console.log (JSON.stringify(result));
                        if (result.status == 200) {
                            console.log (JSON.stringify(result));
                            vm.citations = result.data;
                            vm.selectedCount = 0;
                        }

                    },
                    function (reason) {
                        // need to show error
                        console.log ("bad----" + JSON.stringify(reason));
                    });

                // the results
                /*
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
                */
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