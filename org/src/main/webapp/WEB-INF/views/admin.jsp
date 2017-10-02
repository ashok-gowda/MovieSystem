<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Admin</title>  
    <style>
      .title.ng-valid {
          background-color: lightgreen;
      }
      .title.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .title.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .runtime.ng-valid {
          background-color: lightgreen;
      }
      .runtime.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .runtime.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
      
      .poster.ng-valid {
          background-color: lightgreen;
      }
      .poster.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .poster.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
      
      .description.ng-valid {
          background-color: lightgreen;
      }
      .description.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .description.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
      
      .actor.ng-valid {
          background-color: lightgreen;
      }
      .actor.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .actor.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
      
      .genre.ng-valid {
          background-color: lightgreen;
      }
      .genre.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .genre.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="/resources/css/app.css/" rel="stylesheet"></link>
     <link rel="stylesheet" href="/resources/css/ngDatePicker.css"/>
 	 <link rel="stylesheet" href="/resources/css/bootstrap-datepicker.css"/>
 	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-route.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-resource.js"></script>
	  <script src="/resources/js/bootstrap-datepicker.js"></script>
      <script src="/resources/js/app.js"></script>
      <script src="/resources/js/adminpage_service.js"></script>
      <script src="/resources/js/adminpage_controller.js"></script>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="MovieController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Movie Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.movie.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Movie Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.movie.title" name="title" class="title form-control input-sm" placeholder="Enter Movie Name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.title.$error.required">This is a required field</span>
                                      <span ng-show="myForm.title.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.title.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                        
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Rated</label>
                              <div class="col-md-7">
                                  <select ng-model="ctrl.movie.rated">
                                  <option>R</option>
                                  <option>G</option>
                                  <option>PG</option>
                                  <option>PG-13</option>
                                  </select>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Release Date</label>
                              <div class="col-md-7">
                                  <div class="input-group date" data-provide="datepicker" id="datetime1">
    								<input type="text" class="form-control" value="{{ctrl.movie.releaseDate | date:' dd/MM/yyyy'}}" ng-model="ctrl.movie.releaseDate"/>
    										<div class="input-group-addon">
        										<span class="glyphicon glyphicon-th"></span>
    										</div>
								</div>
                              </div>
                          </div>
                      </div>

                     <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Run time</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.movie.runtime" name="runtime" class="runtime form-control input-sm" placeholder="Enter Runtime of Movie" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.runtime.$error.required">This is a required field</span>
                                      <span ng-show="myForm.runtime.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.runtime.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Language</label>
                              <div class="col-md-7">
                                  <select ng-model="ctrl.movie.language">
                                  <option>English</option>
                                  <option>French</option>
                                  <option>Spanish</option>
                                  </select>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Poster</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.movie.poster" name="poster" class="poster form-control input-sm" placeholder="Enter Location On Drive where movie Poster is present such as /resources/NameOfMovie.jpg" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.poster.$error.required">This is a required field</span>
                                      <span ng-show="myForm.poster.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.poster.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Description</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.movie.description" name="title" class="description form-control input-sm" placeholder="Enter Movie Name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.description.$error.required">This is a required field</span>
                                      <span ng-show="myForm.description.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.description.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Actors</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.movie.actorString" name="actor" class="actor form-control input-sm" placeholder="Enter Names of Actors separated by ," required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.actor.$error.required">This is a required field</span>
                                      <span ng-show="myForm.actor.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.actor.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Genres</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.movie.genreString" name="genre" class="genre form-control input-sm" placeholder="Enter Names of Genres separated by ," required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.genre.$error.required">This is a required field</span>
                                      <span ng-show="myForm.genre.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.genre.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List Of Movies </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Title</th>
                              <th>Rating</th>
                              <th>Language</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="movie in ctrl.movies">
                              <td><span ng-bind="movie.id"></span></td>
                              <td><span ng-bind="movie.title"></span></td>
                              <td><span ng-bind="mvoie.rating"></span></td>
                              <td><span ng-bind="movie.language"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(movie.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(movie.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
  </body>
</html>