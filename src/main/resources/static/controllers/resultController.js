angular.module('app').controller('ResultController', ['$scope', '$state', '$resource', 'NgTableParams', 'AdService',
    function ($scope, $state, $resource, NgTableParams, AdService) {
        'use strict';

        $scope.init = function () {
            $scope.statistics = {};

            AdService.getAvailableCities().then(function (result) {
                $scope.availableCities = _.map(result.data, function (val) {
                    return {filter: {value: val, type: 'match'}, displayValue: val};
                });
            });

            AdService.getAvailableMaterials().then(function (result) {
                $scope.materialTypes = _.map(result.data, function (val) {
                    return {filter: {value: val, type: 'match'}, displayValue: val};
                });
            });

            $scope.numberOfRooms = [
                {filter: {value: 1, type: 'match'}, displayValue: '1-я'},
                {filter: {value: 2, type: 'match'}, displayValue: '2-я'},
                {filter: {value: 3, type: 'match'}, displayValue: '3-я'},
                {filter: {value: 4, type: 'match'}, displayValue: '4-я'},
                {filter: {value: 5, type: 'match'}, displayValue: '5-я'}
            ];

            $scope.areaIntervals = [
                {filter: {from: 0, to: 20, type: 'range'}, displayValue: 'до 20'},
                {filter: {from: 20, to: 30, type: 'range'}, displayValue: '20 - 30'},
                {filter: {from: 30, to: 40, type: 'range'}, displayValue: '30 - 40'},
                {filter: {from: 40, to: 50, type: 'range'}, displayValue: '40 - 50'},
                {filter: {from: 50, to: 60, type: 'range'}, displayValue: '50 - 60'},
                {filter: {from: 60, to: 70, type: 'range'}, displayValue: '60 - 70'}
            ];
        };

        var Api = $resource('/api/data');

        $scope.tableParams = new NgTableParams({
            page: 1,
            count: 5,
            sorting: {price: 'asc'}
        }, {
            total: 0,
            counts: [],
            getData: function ($defer, params) {
                var keys = Object.keys(params.sorting());
                var object = {
                    page: params.page() - 1,
                    sort: keys[0] + "," + params.sorting()[keys[0]]
                };
                object['filterQuery'] = params.filter();
                // Ajax call to update the table contents
                AdService.getData(object).then(function (result) {
                    var data = result.data;
                    if (data.content.length == 0 && data.number > 0) {
                        params.page(data.number);
                    }
                    else {
                        params.page(data.number + 1);
                        params.total(data.totalElements);
                        $defer.resolve(data.content);

                        _.forEach(data.aggregations, function (aggregation) {
                            $scope.statistics[aggregation.name] = aggregation.value;
                        });
                    }
                });
            }
        });
    }]);


