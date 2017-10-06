/**
 * 
 */
var app=angular.module("myApp",[])
app.controller('myController', function($scope,$http,$location) {
	$scope.register=function(){
	 var url="/user/register"
	 var data={
		"email":$scope.email,
		"username":$scope.username,
		"password":$scope.password,
		"address":$scope.address,
		"city":$scope.city,
		"country":$scope.country,
		"zip":$scope.zip,
		"phoneNumber":$scope.phoneNumber
	 }
	 $http.post(url,JSON.stringify(data))
		.then(function(response){
			var messageData=response.data
			if(messageData.statusCode=="200"){
				var relocationUrl="/movieIndex/"+$scope.username
				window.location=relocationUrl
			}
			else{
				$scope.errorMessage=messageData.message;
			}
		}, function errorCallback(response) {
		    console.log(response)
		  });
		
	}
	
	
});