angular.module("loginmodule").
service("loginService",function ($http){
	this.submitUser=function(user){
		var responsePromise = $http.post("/login/"+user.username, user, {});
	       responsePromise.then(function (response){
	    	   console.log(response);
	       },function (error){
	    	   console.log(error);
	       })
	}
});