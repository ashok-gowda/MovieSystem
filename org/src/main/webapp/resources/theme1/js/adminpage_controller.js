'use strict';

angular.module('myApp').controller('MovieController', ['$scope', 'MovieService', function($scope, MovieService) {
    var self = this;
    self.movie={id:null,title:"",rated:"",releaseDate:null,runtime:'',language:'',poster:'',description:'',actorString:'',genreString:''};
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
    	movie.releaseDateInString=movie.releaseDate
    	movie.releaseDate=null
        MovieService.createMovie(movie)
            .then(
            fetchAllMovies,
            function(errResponse){
                console.error('Error while creating Movies');
            }
        );
    }

    function updateMovie(movie, id){
    	movie.releaseDateInString=movie.releaseDate
    	movie.releaseDate=null
        MovieService.updateMovie(movie, id)
            .then(
            fetchAllMovies,
            function(errResponse){
                console.error('Error while updating Movie');
            }
        );
    }

    function deleteMovie(id){
        MovieService.deleteMovie(id)
            .then(
            fetchAllMovies,
            function(errResponse){
                console.error('Error while deleting Movie');
            }
        );
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
        for(var i = 0; i < self.movies.length; i++){
            if(self.movies[i].id === id) {
                self.movie = angular.copy(self.movies[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.movie.id === id) {//clean form if the movie to be deleted is shown there.
            reset();
        }
        deleteMovie(id);
    }


    function reset(){
        self.movie={id:null,title:"",rated:"",releaseDate:null,runtime:'',language:'',poster:'',description:''};
        self.actorString="";
        self.genreString=""
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
