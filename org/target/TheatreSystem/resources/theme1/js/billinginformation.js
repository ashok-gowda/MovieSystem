var app=angular.module("myApp",[])
app.controller('myController', function($scope) {
	$scope.dateSelected=new Date();
	});


$(document).ready(function(){
    
    var active_dates = ["9-9-2017","10-9-2017"];
    
    
    $('#datetime1').datepicker({ 
 
    	beforeShowDay: function (date){
    	 var d = date;
         var curr_date = d.getDate();
         var curr_month = d.getMonth() + 1; //Months are zero based
         var curr_year = d.getFullYear();
         var formattedDate = curr_date + "-" + curr_month + "-" + curr_year
         console.log(formattedDate)
         if ($.inArray(formattedDate, active_dates) != -1){
           return {
              classes: 'activeClass'
           };
         }
      return false;
            
                }
            });
})