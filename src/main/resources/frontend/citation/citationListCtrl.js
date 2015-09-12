angular
    .module('court')
    .controller('CitationListCtrl', ['$scope', '$modal', '$location',
        function ($scope, $modal, $location) {

            var vm = this;
            vm.search = search;
            vm.updateCount = updateCount;
            vm.goToPayment = goToPayment;
            vm.selectAllRows = selectAllRows;
            vm.updateSelectedCount = updateSelectedCount;
            vm.allItemsSelected = false;
            vm.selectedCount = 0;
            vm.citations = [];


            // Get maximum and minimum days for date pickers
            $scope.maxDate = moment().format('YYYY-MM-DD');

            /*date picker logic */
            $scope.pickDate = function ($event, opened) {
                $event.preventDefault();
                $event.stopPropagation();
                $scope[opened] = true;
            };

            function selectAllRows(allSelected){
                vm.selectedCount = 0;
                for(var i = 0;i<vm.citations.length; i++){
                    vm.citations[i].isChecked = allSelected;
                }
                updateSelectedCount();
            }

            function updateSelectedCount(){
                vm.selectedCount = 0;
                for(var i = 0;i<vm.citations.length; i++){
                    if(vm.citations[i].isChecked){
                        vm.selectedCount++;
                    }
                }
                vm.allItemsSelected = (vm.selectedCount==vm.citations.length)&&vm.citations.length>0;
            }

            var model = {};
            $scope.model = model;


            // Fired when an entity in the table is checked
            $scope.selectEntity = function () {
                // If any entity is not checked, then uncheck the "allItemsSelected" checkbox
                for (var i = 0; i < model.entities.length; i++) {
                    if (!model.entities[i].isChecked) {
                        model.allItemsSelected = false;
                        return;
                    }
                }

                // ... otherwise ensure that the "allItemsSelected" checkbox is checked
                model.allItemsSelected = true;
            };

            $scope.selectAll = function () {
                // Loop through all the entities and set their isChecked property
                for (var i = 0; i < model.entities.length; i++) {
                    model.entities[i].isChecked = model.allItemsSelected;
                }
            };

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
                        "courtAddress": "7422 Eunice Avenue"
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
                        "courtAddress": "7422 Eunice Avenue"
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
                        "courtAddress": "7422 Eunice Avenue"
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
                        "courtAddress": "7422 Eunice Avenue"
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
                        "courtAddress": "7422 Eunice Avenue"
                    }
                ];
                vm.citations = newCitations;
                vm.selectedCount = 0;
            }

            function updateCount(el){
            console.log(el)
                if(el.checked){
                    vm.selectedCount++;
                }else{
                    vm.selectedCount--;
                }
            }

            function goToPayment(){

            }

        }]);