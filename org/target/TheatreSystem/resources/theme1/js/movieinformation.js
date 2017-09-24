var app=angular.module("myApp",[])
app.controller('myController', function($scope) {
	$scope.data={
  "movieName":"nameOfMovie",
  "imageLocation":"C:\\Users\\Ashok\\Documents\\GitHub\\TheatreRecommendationSystem\\views\\images\\Avatar.jpg",
  "imageCaption":"Avatar",
  "ratingOfMovie":4.0,
  "comments":[
    {
      "username":"ashok",
      "comment":"Good film",
      "rating":"4.0"
    },
    {
      "username":"ashok",
      "comment":"Good film",
      "rating":"4.0"
    },
     {
      "username":"ashok",
      "comment":"Good film",
      "rating":"4.0"
    }
    ]
}

 	$scope.listOfcomments=$scope.data.comments
 	$scope.starValue=0
	$scope.movieName=$scope.data.movieName
	$scope.websiteName="Ashok's Theatre"
	$scope.imageLocation=$scope.data.imageLocation
	$scope.imageCaption=$scope.data.imageCaption
	$scope.ratingOfMovie=$scope.data.ratingOfMovie
	});