var app=angular.module("myApp",[])
app.controller('myController', function($scope) {
	$scope.date=new Date();
	alert($scope.date)
	});


$('.datepicker').datepicker();