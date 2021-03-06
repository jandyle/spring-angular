angular.module('demo', ['ngAnimate', 'ngSanitize','ui.bootstrap'])
    .controller('Hello', function($scope, $http, $sce, $uibModal, $log, $document) {
        $scope.sample="wow";
        var current;

        $http.get('http://localhost:8080/getallemployees').
        then(function(response) {
            $scope.employeeList = response.data;
        });

        $scope.search=function(){
            var dataObject ={
                name : $scope.yourName,
                address: '',
                department: '',
                skills: ''
            };

            $http({
                method: 'POST',
                url: 'http://localhost:8080/getemployeebyname',
                data: dataObject,
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).then(function (response){
                $scope.employeeList = response.data;
            })

        };


        $scope.delete=function(i){
            $http.delete('http://localhost:8080/removeemployee?name='+i.name).then(function(response){
                $http.get('http://localhost:8080/getallemployees').
                then(function(response) {
                    $scope.employeeList = response.data;
                });
            });
        };

        //samples



        var $ctrl = this;
        $ctrl.animationsEnabled = true;

        $ctrl.open = function (i,size, parentSelector) {
            var parentElem = parentSelector ?
                angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
            var modalInstance = $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'myModalContent.html',
                controller: 'ModalInstanceCtrl',
                controllerAs: '$ctrl',
                size: size,
                appendTo: parentElem,
                resolve: {
                    current: angular.copy(i),
                    items: function () {
                        return i;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $ctrl.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $ctrl.openComponentModal = function () {
            var modalInstance = $uibModal.open({
                animation: $ctrl.animationsEnabled,
                component: 'modalComponent',
                resolve: {
                    items: function () {
                        return $ctrl.items;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $ctrl.selected = selectedItem;
            }, function () {
                $log.info('modal-component dismissed at: ' + new Date());
            });
        };

        $ctrl.openMultipleModals = function () {
            $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title-bottom',
                ariaDescribedBy: 'modal-body-bottom',
                templateUrl: 'stackedModal.html',
                size: 'sm',
                controller: function($scope) {
                    $scope.name = 'bottom';
                }
            });

            $uibModal.open({
                animation: $ctrl.animationsEnabled,
                ariaLabelledBy: 'modal-title-top',
                ariaDescribedBy: 'modal-body-top',
                templateUrl: 'stackedModal.html',
                size: 'sm',
                controller: function($scope) {
                    $scope.name = 'top';
                }
            });
        };

        $ctrl.toggleAnimation = function () {
            $ctrl.animationsEnabled = !$ctrl.animationsEnabled;
        };
    });

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

angular.module('demo').controller('ModalInstanceCtrl', function ($uibModalInstance, items, $http) {
    var $ctrl = this;
    $ctrl.items = items;
    $ctrl.selected = {
        item: $ctrl.items[0]
    };

    $ctrl.ok = function () {
        var dataObject ={
            name : $ctrl.items.name,
            address: $ctrl.items.address,
            department: $ctrl.items.department,
            skills: $ctrl.items.skills
        };

        $http({
            method: 'POST',
            url: 'http://localhost:8080/updateemployee',
            data: dataObject,
            headers: {'Content-Type': 'application/json; charset=utf-8'}
        }).then(function (response){
        })




        console.log(dataObject);
        $uibModalInstance.close($ctrl.selected.item);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

// Please note that the close and dismiss bindings are from $uibModalInstance.

angular.module('demo').component('modalComponent', {
    templateUrl: 'myModalContent.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: function () {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            $ctrl.items = $ctrl.resolve.items;
            $ctrl.selected = {
                item: $ctrl.items[0]
            };
        };

        $ctrl.ok = function () {
            $ctrl.close({$value: $ctrl.selected.item});
        };

        $ctrl.cancel = function () {
            $ctrl.dismiss({$value: 'cancel'});
        };
    }
});