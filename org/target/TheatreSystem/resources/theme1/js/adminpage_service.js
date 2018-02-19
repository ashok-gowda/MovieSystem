'use strict';

angular.module('myApp').factory('MovieService', ['$http', '$q', function($http, $q){


    var factory = {
        fetchAllMovies: fetchAllMovies,
        createMovie: createMovie,
        updateMovie:updateMovie,
        deleteMovie:deleteMovie,
        editMovie:editMovie
    };

    return factory;

    function fetchAllMovies() {
    	var deferred = $q.defer();
        $http.post("/movieInformation/fetchAllMovies",{headers:{'Content-Type': 'application/json'}})
            .then(
            function (response) {
            	 deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Movies');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createMovie(movie) {
    	var deferred = $q.defer();
        movie=JSON.stringify(movie);
        $http.post("/movieInformation/insertMovie",movie)
            .then(
            function (response) {
                if(response.statusCode=="200"){
                	console.log("Movie created")
                	deferred.resolve(response.data);
                }
            },
            function(errResponse){
                console.error('Error while creating Movie');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateMovie(movie, id) {
    	var deferred = $q.defer();
    	movie=JSON.stringify(movie);
        $http.post("/movieInformation/updateMovie",movie)
            .then(
            function (response) {
            	  if(response.statusCode=="200"){
                  	console.log("Movie Updated")
                  	deferred.resolve(response.data);
                  }
            },
            function(errResponse){
                console.error('Error while updating Movie');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteMovie(id) {
    	var deferred = $q.defer();
    	var request={
    			"id":id
    	}
        $http.post("/movieInformation/deleteMovie",JSON.stringify(request))
            .then(
            function (response) {
            	if(response.statusCode=="200"){
                  	console.log("Movie Deleted")
                  	deferred.resolve(response.data);
                  }
            },
            function(errResponse){
                console.error('Error while deleting Movie');
                deferred.reject(errResponse);
            }
        );
    	return deferred.promise;
    }
    
    
    function editMovie(id){
    	var deferred = $q.defer();
    	var request={
    			"id":id
    	}
    	var url="/movieInformation/getCompleteInformationOfOneMovie";
        $http.post("/movieInformation/getCompleteInformationOfOneMovie",JSON.stringify(request))
            .then(
            function (response) {
                  	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Movie');
                deferred.reject(errResponse);
            }
        );
    	return deferred.promise;
    }

}]);
