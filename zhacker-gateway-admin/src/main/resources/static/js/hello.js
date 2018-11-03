angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs : 'controller'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	$httpProvider.defaults.headers.common['Accept'] = 'application/json';

}).controller('navigation',

function($rootScope, $http, $location, $route) {

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

}).controller('home', function($http,$window) {
	var self = this;
//	$http.get('resource/greeting').then(function(response) {
//		self.greeting = response.data;
//	});
    $http.get("user").then(function (response) {
        self.userinfo = response;
        self.token = response.data.details.tokenValue;
        $window.sessionStorage.authHeader = 'Bearer ' + self.token;
    });
//    $http.get("resource/token").then(function (response) {
//		console.log(response);
//    });
    $http.get("token").then(function(response){
        console.log("token from ui: ");
        console.log(response);
    });
    self.directRestInvoke = function () {
        $http({
                  method: 'post',
                  url: 'http://localhost:8091/v1/stock',
                  data: {"skuId": 1},
                  headers: {'Authorization': $window.sessionStorage.authHeader}
              }).success(function (response) {
            console.log($window.sessionStorage.authHeader);
            console.log("call stock service by rest: ");
            console.log(response.data);
        })
    };

    self.gateRestInvoke = function () {
        $http({
                  method:'post',
                  url:'http://gateway.zhacker.top/stock/v1/stock',
                  data:{"skuId": 1},
                  headers: {'Authorization' : $window.sessionStorage.authHeader}
              }).success(function(response){
            console.log($window.sessionStorage.authHeader);
            console.log("call stock service by rest: ");
            console.log(response.data);
        });
    };

    self.gateDubboInvoke = function () {
        $http({
                  method:'post',
                  url:'http://gateway.zhacker.top/dubbo/top.zhacker.retail.stock.api.StockService/query',
                  data:{"skuId": 1},
                  headers: {'Authorization' : $window.sessionStorage.authHeader}
              }).success(function(response){
            console.log($window.sessionStorage.authHeader);
            console.log("call stock service by dubbo: ");
            console.log(response.data);
        });
    };

    self.clientDubboInvoke = function () {
        $http.post('gw/dubbo/top.zhacker.retail.stock.api.StockService/query', {"skuId": 1}).then(function(response){
            console.log("call stock service by dubbo: ");
            console.log(response.data);
        });
    };

    self.clientRestInvoke = function () {
        $http.post('gw/stock/v1/stock', {"skuId": 1}).then(function(response){
            console.log("call stock service by rest: ");
            console.log(response.data);
        });
    };
    self.fooRead = function () {
    	$http({
			method: 'post',
			url: 'http://localhost:8091/v1/stock',
			data: {"skuId":1},
                  headers: {'Authorization' : $window.sessionStorage.authHeader}
			  }).success(function(response){
            console.log($window.sessionStorage.authHeader);
            console.log("call stock service by rest: ");
            console.log(response.data);})
        // $http.get('resource/foo').then(function(response) {
        //     self.foo = response.data;
        // });
        // $http.get('gw/stock/v1/stock/0').then(function(response){
        //     console.log("call account service: ");
        //     console.log(response.data);
        // });
		console.log($window.sessionStorage.authHeader);
        // $http.post('http://gateway.zhacker.top/stock/v1/stock', {"skuId": 1}, {
        //     headers : {'Authorization' : $window.sessionStorage.authHeader}
        // }).then(function(response){
        //     console.log($window.sessionStorage.authHeader);
        //     console.log("call stock service by rest: ");
        //     console.log(response.data);
        // });
        $http({
                  method:'post',
                  url:'http://gateway.zhacker.top/stock/v1/stock',
                  data:{"skuId": 1},
                  headers: {'Authorization' : $window.sessionStorage.authHeader}
              }).success(function(response){
            console.log($window.sessionStorage.authHeader);
            console.log("call stock service by rest: ");
            console.log(response.data);
        });

        $http({
                  method:'post',
                  url:'http://gateway.zhacker.top/dubbo/top.zhacker.retail.stock.api.StockService/query',
                  data:{"skuId": 1},
                  headers: {'Authorization' : $window.sessionStorage.authHeader}
              }).success(function(response){
            console.log($window.sessionStorage.authHeader);
            console.log("call stock service by dubbo: ");
            console.log(response.data);
        });
    };
	self.fooWrite = function () {
        $http.post('gw/dubbo/top.zhacker.retail.stock.api.StockService/query', {"skuId": 1}).then(function(response){
            console.log("call stock service by dubbo: ");
            console.log(response.data);
        });
        $http.post('gw/stock/v1/stock', {"skuId": 1}).then(function(response){
            console.log("call stock service by rest: ");
            console.log(response.data);
        });
    }
});
