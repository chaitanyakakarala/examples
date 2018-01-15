angular.module("myapp").
service("loginService",function ($http){
	this.submitUser=function(user){
		var responsePromise = $http.post("/login/"+user.username, user, {});
	       responsePromise.success(function(data, status, headers, config) {
	          console.log(data);
	       });
	        responsePromise.error(function(data, status, headers, config) {
	          alert("Submitting form failed!");
	       });
	}
});