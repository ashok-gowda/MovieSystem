<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page isELIgnored="false" %>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/movieindex.css">
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular.js"> </script>    
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-resource.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/movieindex.js"></script>
  </head>
  <body ng-app="myApp" ng-controller="myController" ng-init="initValues('${username}')" >
  <nav class="navbar navbar-default">
  <div class="container-fluid">
  <div class="navbar-header">
      <a class="navbar-brand" href="#">Hello ${username}</a>
    </div>
    <ul class="nav navbar-nav">
    	<li><a href="#" ng-click="value=getMovies('TOP_RATED','RATING')">Top Rated Movies</a></li>
    	<li><a href="#" ng-click="value=getMovies('RECOMMENDED','RECOMMENDATIONS')">Your Recommended Movies</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Movies By Genre
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#" ng-click="value=getMovies('ADVENTURE','GENRE')">Adventure</a></li>
          <li><a href="#" ng-click="value=getMovies('ACTION','GENRE')">Action</a></li>
          <li><a href="#" ng-click="value=getMovies('Fantasy','GENRE')">Fantasy</a></li>
          <li><a href="#" ng-click="value=getMovies('Thriller','GENRE')">Thriller</a></li>
          <li><a href="#" ng-click="value=getMovies('Documentary','GENRE')">Documentary</a></li>
          <li><a href="#" ng-click="value=getMovies('Sci-Fi','GENRE')">Sci-Fi</a></li>
          <li><a href="#" ng-click="value=getMovies('Animation','GENRE')">Animation</a></li>
          <li><a href="#" ng-click="value=getMovies('Comedy','GENRE')">Comedy</a></li>
          <li><a href="#" ng-click="value=getMovies('Family','GENRE')">Family</a></li>
          <li><a href="#" ng-click="value=getMovies('Western','GENRE')">Western</a></li>
          <li><a href="#" ng-click="value=getMovies('Drama','GENRE')">Drama</a></li>
          <li><a href="#" ng-click="value=getMovies('Romance','GENRE')">Romance</a></li>
          <li><a href="#" ng-click="value=getMovies('Crime','GENRE')">Crime</a></li>
          <li><a href="#" ng-click="value=getMovies('Horror','GENRE')">Horror</a></li>
          <li><a href="#" ng-click="value=getMovies('History','GENRE')">History</a></li>
          <li><a href="#" ng-click="value=getMovies('Biography','GENRE')">Biography</a></li>
          <li><a href="#" ng-click="value=getMovies('Mystery','GENRE')">Mystery</a></li>
          <li><a href="#" ng-click="value=getMovies('Fantasy','GENRE')">Fantasy</a></li>
          <li><a href="#" ng-click="value=getMovies('War','GENRE')">War</a></li>
          <li><a href="#" ng-click="value=getMovies('Crime','GENRE')">Crime</a></li>
          <li><a href="#" ng-click="value=getMovies('Sport','GENRE')">Sport</a></li>
          <li><a href="#" ng-click="value=getMovies('Music','GENRE')">Music</a></li>
        </ul>
        </li>

        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Movies By Language
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#" ng-click="getMovies('ENGLISH','LANGUAGE')">English</a></li>
          <li><a href="#" ng-click="getMovies('SPANISH','LANGUAGE')">Spanish</a></li>
           <li><a href="#" ng-click="getMovies('FRENCH','LANGUAGE')">Spanish</a></li>
        </ul>
        </li>


        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Movies By Year Released
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#" ng-click="getMovies('1970-1980','YEARRANGE')">1970-1980</a></li>
          <li><a href="#" ng-click="getMovies('1980-1990','YEARRANGE')">1980-1990</a></li>
          <li><a href="#" ng-click="getMovies('1990-2000','YEARRANGE')">1990-2000</a></li>
          <li><a href="#" ng-click="getMovies('2000-2010','YEARRANGE')">2000-2010</a></li>
          <li><a href="#" ng-click="getMovies('After2010','YEARRANGE')"> After 2010</a></li>
        </ul>
        </li>
      <li> 
      <form class="navbar-form navbar-left">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search Title or Artist" ng-model="searchValue">
        <div class="input-group-btn">
          <button class="btn btn-default" type="submit" ng-click="clickSearchButton(searchValue)">
            <i class="glyphicon glyphicon-search"></i>
          </button>
        </div>
      </div>
    </form>
      </li>
      <li>
      <button class="btn btn-danger navbar-btn"><a href="javascript:close_window();">LOG OUT</a></button>
      </li>
      </ul>
  </nav>
  </div>
  <div class="col-sm-12">
  <div ng-repeat="movie in listOfMovies" ng-if="$index % 3 == 0" class="row">
    <div class="col-sm-4">
      <div class="thumbnail">
      <a href={{listOfMovies[$index].url}}>
        <img src={{listOfMovies[$index].poster}} alt={{listOfMovies[$index].poster}} >
        <div class="caption">
          <p>{{listOfMovies[$index].title}}</p>
        </div>
      </a>
    </div>
    </div>
    <div class="col-sm-4" ng-if="listOfMovies.length > ($index + 1)">
      <div class="thumbnail">
      <a href={{listOfMovies[$index+1].url}}>
        <img src={{listOfMovies[$index+1].poster}} alt={{listOfMovies[$index+1].poster}} >
        <div class="caption">
          <p>{{listOfMovies[$index+1].title}}</p>
        </div>
      </a>
    </div>
    </div>
    <div class="col-sm-4" ng-if="listOfMovies.length > ($index + 2)">
      <div class="thumbnail">
      <a href={{listOfMovies[$index+2].url}}>
        <img src={{listOfMovies[$index+2].poster}} alt={{listOfMovies[$index+2].poster}} >
        <div class="caption">
          <p>{{listOfMovies[$index+2].title}}</p>
        </div>
      </a>
    </div>
    </div>
  </div>
  </div>
  </body>
  </html>