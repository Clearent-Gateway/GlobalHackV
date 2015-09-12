angular.module('court')
    .directive('ngEnter', function () {
        return function (scope, element, attrs) {
            element.bind("keydown keypress", function (event) {
                if(event.which === 13) {
                    scope.$apply(function (){
                        scope.$eval(attrs.ngEnter);
                    });

                    event.preventDefault();
                }
            });
        };
    })
    .directive('ngFormat', function () {
        return {
            link: function (scope, element, attrs) {
                element.bind("keyup", function ($event) {
                    var v,el = $event.target;
                    // get raw numeric value
                    v = el.value.replace(/[^0-9]+/g, '');

                    switch(attrs.ngFormat) {
                        case 'card':
                            v = v.substring(0, 19);
                            v = v.split(/(....)/).filter(String).join('  ');
                            break;
                        case 'dateMMYY':
                            v = v.substring(0, 4);
                            v = v.split(/(..)/).filter(String).join('/');
                            break;
                        default:
                            break;
                    }
                    el.value = v;

                });
            }
        };
    })
    .directive('ngSetfocus', ['$timeout', function ($timeout) {
        return {
            link: function (scope, element, attrs, model) {
                $timeout(function () {
                    element[0].focus();
                });
            }
        };
    }])
    .directive('ngNoselect', function () {
        return function (scope, element, attrs) {
            element.bind("selectstart", function (event) {
                event.preventDefault();
                return false;
            });
        };
    })
    .directive('onFinishRender', ['$timeout', function ($timeout) {
        return {
            restrict: 'A',
            link: function (scope, element, attr) {
                if (scope.$last === true) {
                    $timeout(function () {
                        scope.$emit('ngRepeatFinished');
                    });
                }
            }
        }
    }])
    .directive('match', function() {
        return {
            restrict: 'A', // only activate on element attribute
            require: '?ngModel', // get a hold of NgModelController
            link: function(scope, elem, attrs, ngModel) {
                if(!ngModel) return; // do nothing if no ng-model

                // watch own value and re-validate on change
                scope.$watch(attrs.ngModel, function() {
                    validate();
                });

                // observe the other value and re-validate on change
                attrs.$observe('match', function () {
                    validate();
                });

                var validate = function() {
                    // values
                    var val1 = ngModel.$viewValue;
                    var val2 = attrs.match;

                    // set validity
                    ngModel.$setValidity('mismatch', ! val1 || ! val2 || val1 === val2);
                };
            }
        }
    })
    .directive('ngPeek', function() {
        return {
            //restrict: 'A', // only activate on element attribute
            link: function(scope, elem, attrs, ngModel) {

                elem.on('mouseenter touchstart', function($event) {
                    var el = $event.target;
                    var f = document.getElementById(el.getAttribute('ng-peek'));
                    elem.removeClass("glyphicon-eye-close");
                    elem.addClass("glyphicon-eye-open");
                    f.type = 'text';
                });

                elem.on('mouseleave touchend touchcancel', function($event) {
                    var el = $event.target;
                    var f = document.getElementById(el.getAttribute('ng-peek'));
                    elem.removeClass("glyphicon-eye-open");
                    elem.addClass("glyphicon-eye-close");
                    f.type = 'password';
                });
            }
        }
    })

    ;
