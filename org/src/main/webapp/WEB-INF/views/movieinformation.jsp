<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page isELIgnored="false" %>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/movieinformation.css">
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular.js"> </script>    
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-resource.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/movieinformation.js"></script>
  </head>
  <body ng-app="myApp" ng-controller="myController" >
  <nav class="navbar navbar-default">
  <div class="container-fluid">
  <div class="row">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">{{websiteName}}</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">{{movieName}}</a></li>
      <li class="active">
      <a href="javascript:close_window();">Log Out</a>
      </li>
      </ul>
    </ul>
  </div>
  </div>
</nav>

 <div class="container">
 	<div class="row">
  		<div class="col-md-12" ">
  			<img src={{imageLocation}} class="img-responsive"  style='width:50%;'/>
  			<div class="caption">
          		<h1>{{imageCaption}}</h1><br/>
          		<h1>Rating-{{ratingOfMovie}}</h1><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
        	</div>
		</div>
	</div>
 </div>
 <div class="container">
 	<div class="row">
 		<div class="col-sm-6">
 			<form method="post" action="http://">
 				<div class="stars">
        	<input type="radio" name="star" class="star-1" id="star-1" ng-model="starValue" value=1/>
        	<label class="star-1" for="star-1">1</label>
        	<input type="radio" name="star" class="star-2" id="star-2" ng-model="starValue" value=2/>
        	<label class="star-2" for="star-2">2</label>
        	<input type="radio" name="star" class="star-3" id="star-3" ng-model="starValue" value=3/>
        	<label class="star-3" for="star-3">3</label>
        	<input type="radio" name="star" class="star-4" id="star-4" ng-model="starValue" value=4/>
        	<label class="star-4" for="star-4">4</label>
        	<input type="radio" name="star" class="star-5" id="star-5" ng-model="starValue" value=5/>
        	<label class="star-5" for="star-5">5</label>
        	<span></span>
    		</div>
 				<textarea class="form-control" rows="5" id="comment" placeholder="Place your comments here ....">{{starValue}}</textarea>
 				<input type="submit" value="Submit" class="html-text-box"><input type="reset" value="Reset" class="html-text-box">
 			</form>
 		</div>
 	</div>
 </div>
 <div class="container" style="margin-top:5%">
 	<div class="panel-group" ng-repeat="comment in listOfcomments">
 	<div class="panel panel-default">
 	 <div class="panel-heading">{{comment.rating}}</div>
 	 <div class="panel-body">{{comment.comment}}</div>
 	 <div class="panel-footer">{{ comment.username}}
 	 </div>
 	</div>
 	</div>
 </div>
</body>
  </html>