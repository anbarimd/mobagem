var app = angular.module('mobagem', []);

app.controller("indexController", function($scope, $http, $element) {

	
	$scope.sliders= [];
	$scope.hotGames = [];
	$scope.services= [];
	$scope.results= [];
	
		ajaxGet(service_getSliders, '', function(data) {
			$scope.$apply(function() {
				$scope.sliders= data;
			});
		}, function(err) {
			alert('bye');
			console.log(err);
		});

		
		ajaxGet(service_getHotGames, '', function(data) {
			$scope.$apply(function() {
				$scope.hotGames= data;
			});
		}, function(err) {
			alert('bye');
			console.log(err);
		});

		ajaxGet(service_getServices, '', function(data) {
			$scope.$apply(function() {
				$scope.services= data;
			});
		}, function(err) {
			alert('bye');
			console.log(err);
		});
		

		ajaxGet(service_getResults, '', function(data) {
			$scope.$apply(function() {
				$scope.results= data;
			});
		}, function(err) {
			alert('bye');
			console.log(err);
		});

		
});
