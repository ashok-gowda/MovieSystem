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
			var url="/user/getDetailsOfUsers"
			var data={
					"username":$scope.username
			}	
				$http.post(url,JSON.stringify(data),{headers:{'Content-Type': 'application/json'}})
				.then(function(response){
					var dataMessage=response.data
					console.log(dataMessage)
					if(dataMessage.isAdmin==true){
						var redirectUrl="/admin/adminPanel/"+$scope.username
						window.location=redirectUrl
					}
					else{
					var redirectUrl="/movieIndex/"+$scope.username;
					window.location=redirectUrl
					}
				})
			
		}
	})

}
});