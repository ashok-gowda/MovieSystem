<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular.js"> </script>    
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-resource.js"></script>
  <script src="/resources/js/registration.js"></script>
  <style>
  .center-block1{
    margin-top: 10%
  }
  </style>
</head>
<body style="background-color:white;" ng-app="myApp" ng-controller="myController">
<div class="container-fluid  ">
<div class="row"><center><h1> Register for Theatre System</h1></center></div>
<div class="row">
<div class="col-sm-4"></div>
<div class="col-sm-4">
<form>
    <div class="form-group ">
      <label for="inputEmail4" class="col-form-label">Email</label>
      <input type="email" class="form-control" ng-model="email" id="inputEmail4" placeholder="Email" required>
    </div>
    <div class="form-group ">
      <label for="inputPassword4" class="col-form-label">Password</label>
      <input type="password" class="form-control" ng-model="password" id="inputPassword4" placeholder="Password" required>
    </div>
  <div class="form-group">
    <label for="username" class="col-form-label">UserName</label>
    <input type="text" class="form-control" ng-model="username" id="username" placeholder="A Name you want to be associated with " required>
  </div>
  <div class="form-group ">
      <label for="phoneNumber" class="col-form-label">phoneNumber</label>
      <input type="text" class="form-control" ng-model="phoneNumber" id="phoneNumber" required>
    </div>
  <div class="form-group ">
      <label for="inputAddress" class="col-form-label">Address</label>
      <input type="text" class="form-control" ng-model="address" id="inputCity" required>
    </div>
    <div class="form-group ">
      <label for="inputCity" class="col-form-label">City</label>
      <input type="text" class="form-control" ng-model="city" id="inputCity" required>
    </div>
    <div class="form-group ">
    <label for="inputCountry" class="col-form-label">Country</label>
      <input type="text" class="form-control" ng-model="country" id="inputCountry" required>
    </div>
    <div class="form-group ">
      <label for="inputZip" class="col-form-label">Zip</label>
      <input type="text" class="form-control" ng-model="zip" id="inputZip" required>
    </div>
  <div class="form-group ">  
  <button type="submit" class="btn btn-primary" ng-click="register()">Sign in</button>
  </div>
  <div class="form-group ">
  {{errorMessage}}
  </div>
</form>
</div>
<div class="col-sm-4"></div>
</div>
</div>
</div>
</div>
</body>
</html>