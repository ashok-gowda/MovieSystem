var app=angular.module("myApp",[])
app.controller('myController', function($scope,$http,$location) {

	$scope.initValues=function(username){
		$scope.username=username
	}
	
	$scope.value=0
	$scope.searchValue=""
	$scope.listOfMovies=null
	
	var url="/movieIndex/getTopRecommendedMovies";
	$http.get(url,{headers:{'Content-Type': 'application/json'}})
		.then(function(response){
			$scope.listOfMovies=$scope.generateRelativeUrls(response.data);
			var myEl = angular.element( document.querySelector( '#id_TOP_RATED' ) );
			myEl.addClass('active');	
		})
		
	
	
	
	
	
	
	$scope.clickSearchButton=function(s){
		var url="/movieIndex/search/"+s
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=$scope.generateRelativeUrls(response.data);
			})
	}

	$scope.getMovies=function(category,type){
		if(type=='GENRE'){
			var url="/movieIndex/find/genre/"+category
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=$scope.generateRelativeUrls(response.data);
			})
		}
		else if(type=='LANGUAGE'){
			var url="/movieIndex/find/language/"+category
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=$scope.generateRelativeUrls(response.data);
			})
		}
		else if(type=='YEARRANGE'){
			var url="/movieIndex/find/yearRange/"+category
			$http.get(url)
			.then(function(response){
				$scope.listOfMovies=$scope.generateRelativeUrls(response.data);
			})
		}
		else if(type=="RATING"){
			var url="/movieIndex/getTopRecommendedMovies";
			$http.get(url,{headers:{'Content-Type': 'application/json'}})
			.then(function(response){
				$scope.listOfMovies=$scope.generateRelativeUrls(response.data);
				var myEl = angular.element( document.querySelector( '#id_TOP_RATED' ) );
				myEl.addClass('active');	
			})
		}
		else if(type=='RECOMMENDATIONS'){
			var url="/movieIndex/getRecommendedMovies/"+$scope.username
			$http.get(url,{headers:{'Content-Type': 'application/json'}})
			.then(function(response){
				$scope.listOfMovies=$scope.generateRelativeUrls(response.data);
				var myEl = angular.element( document.querySelector( '#id_RECOMMENDED' ) );
				myEl.addClass('active');	
			})
		}

	}
	
	$scope.generateRelativeUrls=function(listOfdata){
		for (i=0;i<listOfdata.length;i++){
			listOfdata[i].url="/movieInformation/"+$scope.username+"/"+listOfdata[i].id
		}
		return listOfdata;
	}
	
	$scope.logout=function(s){
		var url="/user/logout";
		var data={
			"username":$scope.username	
		}
		$http.post(url,JSON.stringify(data))
		.then(function(response){
			var messageData=response.data
			if(messageData.statusCode=="200"){
				window.location="/user/"
			}
		}, function errorCallback(response) {
		    console.log(response)
		  });
		
		}
	
	
	
	});