angular.module('app').service('AdService', ['$http', function ($http) {
    this.getAvailableCities = function () {
        return $http.get('/api/getAvailableCities');
    };

    this.getAvailableMaterials = function () {
        return $http.get('/api/getAvailableMaterials');
    };

    this.index = function (selectedCity) {
        return $http({
            url: '/api/index',
            method: "GET",
            params: {city: selectedCity}
        });
    };

    this.getData = function (params) {
        return $http({
            url: '/api/data',
            method: "GET",
            params: params
        });
    }
}]);
