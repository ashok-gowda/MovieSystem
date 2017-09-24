var app=angular.module("myApp",[])
app.controller('myController', function($scope,$http,$location) {

	$scope.value=0
	$scope.searchValue=""
	$scope.listOfMovies=null
	$scope.clickSearchButton=function(s){
		var url="/movieIndex/search/"+s
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=response.data;
			})
	}

	$scope.getMovies=function(category,type){
		if(type=='GENRE'){
			var url="/movieIndex/find/genre/"+category
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=response.data;
			})
		}
		else if(type=='LANGUAGE'){
			var url="/movieIndex/find/language/"+category
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=response.data;
			})
		}
		else if(type=='YEARRANGE'){
			var url="/movieIndex/find/yearRange/"+category
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=response.data;
			})
		}

	}
	});