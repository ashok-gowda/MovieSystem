var app=angular.module("myApp",[])
app.controller('myController', function($scope) {
	$scope.value=0
	$scope.searchValue=""
	$scope.$watch("value", function(newValue, oldValue) {
    if (newValue!=0 ||oldValue!=0) {
     alert($scope.value);
    }
  });
	$scope.clickSearchButton=function(s){
		alert(s)
	}
	});