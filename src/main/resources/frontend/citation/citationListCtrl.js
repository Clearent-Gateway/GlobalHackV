angular
    .module('court')
    .controller('CitationListCtrl', ['$scope', '$modal', '$location',
        function ($scope, $modal, $location) {

            var vm = this;
            vm.goToPayment = goToPayment;
            vm.citations = [
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

            function goToPayment(){

            }

        }]);