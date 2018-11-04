angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs : 'controller'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	$httpProvider.defaults.headers.common['Accept'] = 'application/json';

}).controller('navigation', function($rootScope, $http, $location, $route) {

	var self = this;

	self.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	$http.get('user').then(function(response) {
		if (response.data.name) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}
	}, function() {
		$rootScope.authenticated = false;
	});

	self.credentials = {};

	self.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        });
	}

}).controller('home', function($scope,$http,$window) {
	var self = this;
    $http.get("user").then(function (response) {
        self.userinfo = response;
        self.token = response.data.details.tokenValue;
        $window.sessionStorage.authHeader = 'Bearer ' + self.token;
    });
    $http.get("token").then(function(response){
        console.log("token from ui: ");
        console.log(response);
    });
    self.httpInvoke = function(){
        console.log($scope);
        if($scope.httpMethod == 'get'){
            $http({
                method: 'get',
                url: $scope.invokeUrl,
                headers: {'Authorization': $window.sessionStorage.authHeader}
            }).success(function (response) {
                console.log($window.sessionStorage.authHeader);
                console.log("call service by rest: ");
                console.log(response);
                $scope.httpResponse = JSON.stringify(response, undefined, 2);
            })
        }else if ($scope.httpMethod == 'post'){
            $http({
                method: 'post',
                url: $scope.invokeUrl,
                data: JSON.parse($scope.httpBody),
                headers: {'Authorization': $window.sessionStorage.authHeader}
            }).success(function (response) {
                console.log($window.sessionStorage.authHeader);
                console.log("call service by rest: ");
                console.log(response);
                $scope.httpResponse = JSON.stringify(response, undefined, 2);
            })
        }

    };
});
