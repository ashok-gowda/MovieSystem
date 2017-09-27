var app=angular.module("myApp",[])
app.controller('myController', function($scope,$http,$location) {

$scope.displayErrorMessage=function(errorMessage){
	console.log(errorMessage)
	if(errorMessage!=null){
	alert(JSON.parse(errorMessage).message)
}
}

$scope.validateLogin=function(){
	var url="/user/login/"+$scope.username+"/"+$scope.password
	$http.get(url)
	.then(function(response){
		if(angular.equals(response.data.message,"Not Authenciated")){
			$scope.errorMessage="Please check whether the details entered is correct"
		}
		else if(angular.equals(response.data.message,"Authenciated")){
			var redirectUrl="/movieIndex/"+$scope.username;
			window.location=redirectUrl
		}
	})

}
});