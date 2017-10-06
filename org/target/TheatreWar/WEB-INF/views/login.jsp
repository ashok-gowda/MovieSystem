<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page isELIgnored="false" %>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular.js"> </script>    
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-resource.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="/resources/js/login.js"></script>
  <style>
  .center-block1{
    margin-top: 30%
  }
  </style>
</head>
<body style="background-color:white;" ng-app="myApp" ng-controller="myController" ng-init="displayErrorMessage(${responseMessage})">
<div class="container">
<div class="col-sm-4"></div>
<div class="col-sm-4 ">
<form>
  <div class="form-group center-block1">
    <label for="exampleInputEmail1">Username</label>
    <input type="text" class="form-control"   placeholder="Enter Username" ng-model="username" required>
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" ng-model="password" required>
  </div>
  <button type="submit" class="btn btn-primary" ng-click="validateLogin()">Submit</button>
  <a href="/user/register" class="btn btn-primary"> New Registration </a>
</form>
<div >{{errorMessage}}</div>
</div>
</div>
</div>

</body>
</html>