angular.module('app').controller('IndexingController', ['$scope', '$state', 'AdService',
    function ($scope, $state, AdService) {
        'use strict';

        $scope.init = function () {
            $scope.indexingInProgress = false;
            $scope.selectedCity = null;

            AdService.getAvailableCities().then(function (result) {
                $scope.availableCities = result.data;
            });
        };

        $scope.index = function () {
            if (!$scope.selectedCity) {
                return;
            }

            $scope.indexingInProgress = true;
            AdService.index($scope.selectedCity).then(function () {
                $scope.indexingInProgress = false;
            })
        };

    }]);



