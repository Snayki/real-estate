angular.module('app', [
    'ui.router',
    'ngResource',
    'angular-growl',
    'angular-loading-bar',
    'ngAnimate',
    'ui.select',
    'ngSanitize',
    'ngTable',
    'mgcrea.ngStrap'
]);

angular.module('app').config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
    $urlRouterProvider.otherwise('/home');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    $stateProvider.state('home', {
        url: '/home',
        templateUrl: 'templates/home.html',
        controller: 'HomeController'
    });

    $stateProvider.state('indexing', {
        url: '/indexing',
        templateUrl: 'templates/indexing.html',
        controller: 'IndexingController'
    });

    $stateProvider.state('result', {
        url: '/result',
        templateUrl: 'templates/result.html',
        controller: 'ResultController'
    });

});