'use strict';

angular.module('myApp').factory('MovieService', ['$http', '$q', function($http, $q){


    var factory = {
        fetchAllMovies: fetchAllMovies,
        createMovie: createMovie,
        updateMovie:updateMovie,
        deleteMovie:deleteMovie
    };

    return factory;

    function fetchAllMovies() {
    	var listOfMovies=[]
        $http.post("/movieInformation/fetchAllMovies",{headers:{'Content-Type': 'application/json'}})
            .then(
            function (response) {
            	listOfMovies=response.data;
            },
            function(errResponse){
                console.error('Error while fetching Movies');
            }
        );
        return listOfMovies;
    }

    function createMovie(movie) {
        movie=JSON.stringify(movie);
        $http.post("/movieInformation/insertMovie",movie)
            .then(
            function (response) {
                if(response.statusCode=="200"){
                	console.log("Movie created")
                }
            },
            function(errResponse){
                console.error('Error while creating Movie');
            }
        );
    }


    function updateMovie(movie, id) {
    	movie=JSON.stringify(movie);
        $http.post("/movieInformation/updateMovie",movie)
            .then(
            function (response) {
            	  if(response.statusCode=="200"){
                  	console.log("Movie Updated")
                  }
            },
            function(errResponse){
                console.error('Error while updating Movie');
            }
        );
    }

    function deleteMovie(id) {
    	var request={
    			"id":id
    	}
        $http.post("/movieInformation/deleteMovie",JSON.stringify(request))
            .then(
            function (response) {
            	if(response.statusCode=="200"){
                  	console.log("Movie Deleted")
                  }
            },
            function(errResponse){
                console.error('Error while deleting Movie');
            }
        );
    }

}]);
