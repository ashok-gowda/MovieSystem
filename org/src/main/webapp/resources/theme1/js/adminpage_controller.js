'use strict';

angular.module('myApp').controller('MovieController', ['$scope','$route','$http','$window','MovieService', function($scope,$route,$http,$window, MovieService) {
    var self = this;
    self.movie={id:null,title:"",rated:"",releaseDate:null,runtime:'',language:'',poster:'',description:'',actorString:'',genreString:'',releaseDateInString:''};
    self.movies=[];
    self.dateSelected=new Date();
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllMovies();

    function fetchAllMovies(){
        MovieService.fetchAllMovies()
            .then(
            function(d) {
                self.movies = d;
            },
            function(errResponse){
                console.error('Error while fetching Movies');
            }
        );
    }

    function createMovie(movie){
        MovieService.createMovie(movie)
            .then(
            fetchAllMovies,
            function(errResponse){
                console.error('Error while creating Movies');
            }
        );
        $window.location.reload();
    }

    function updateMovie(movie, id){
        MovieService.updateMovie(movie, id)
            .then(
            fetchAllMovies,
            function(errResponse){
                console.error('Error while updating Movie');
            }
        );
        $window.location.reload();
    }

    function deleteMovie(id){
        MovieService.deleteMovie(id)
            .then(
            fetchAllMovies,
            function(errResponse){
                console.error('Error while deleting Movie');
            }
        );
        $window.location.reload();
    }

    function submit() {
        if(self.movie.id===null){
            console.log('Saving New Movie', self.movie);
            createMovie(self.movie);
        }else{
            updateMovie(self.movie, self.movie.id);
            console.log('Movie updated with id ', self.movie.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        MovieService.editMovie(id)
        .then(
        function(d) {
            self.movie = d;
        },
        function(errResponse){
            console.error('Error while fetching Movies');
        }
    );        
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.movie.id === id) {//clean form if the movie to be deleted is shown there.
            reset();
        }
        deleteMovie(id);
    }


    function reset(){
        self.movie={id:null,title:"",rated:"",releaseDate:null,runtime:'',language:'',poster:'',description:'',actorString:'',genreString:'',releaseDateInString:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    
    
    self.logout=function(){
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
	
	self.home=function(){
		window.location="/movieIndex/"+$scope.username
	}
	
	self.initValues=function(u){
		console.log("Function clicked")
		$scope.username=u
		console.log($scope.username)
	}
}]);
