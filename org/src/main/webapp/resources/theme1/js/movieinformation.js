var app=angular.module("myApp",[])
app.controller('myController', function($scope,$http,$timeout) {
	
	$scope.initValues=function(movieId,username){
		$scope.movieId=movieId
		$scope.username=username
		var url="/movieInformation/getMovieInformationWithComments/"+$scope.movieId
		$http.get(url)
		.then(function(response){
			$scope.data=response.data;
			$scope.listOfcomments=$scope.data.listOfComments
		 	$scope.starValue=0
			$scope.movieName=$scope.data.movie.title
			$scope.websiteName="Hello "+$scope.username
			$scope.imageLocation=$scope.data.movie.poster
			$scope.imageCaption=$scope.data.movie.title
			$scope.description=$scope.data.movie.description
			$scope.ratingOfMovie=$scope.data.movie.rated
			$scope.genres=$scope.data.genres.join()
			$scope.actorsInvolved=$scope.data.actorsInvolved.join()
			$scope.lang=$scope.data.movie.language
			var d=new Date($scope.data.movie.releaseDate)
			var curr_date = d.getDate();
		    var curr_month = d.getMonth() + 1; //Months are zero based
		    var curr_year = d.getFullYear();
		    $scope.yearReleased=curr_date+"-"+curr_month+"-"+curr_year
		})
		
	 	
	}
	
	$scope.clickOnUpload=function(){
		  $timeout(function() {
		    angular.element('#reset').triggerHandler('click');
		  });
		};
	
	$scope.insertComment=function(){
		var url="/movieInformation/insertComments"
		var data={
				'username':$scope.username,
				'movieId':$scope.movieId,
				'rating':$scope.starValue,
				'comment':$scope.commentValue
		}
		$http.post(url,JSON.stringify(data))
		.then(function(response){
			$scope.data=response.data;
			$scope.listOfcomments=$scope.data.listOfComments
			$scope.clickOnUpload()
		})
		
	}
	
	
	});