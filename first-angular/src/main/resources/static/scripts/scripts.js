angular.module("mainModule", [])
  .value("person",{
	firstName: "firstName",
	lastName: "lastName",
	getFullName: function () 
	{
		return firstName+" "+lastName;
	}
  })
  .factory("MathFactory")
  .service("SimpleAppService")
  .controller("simpleController", function ($scope,person,SimpleAppService,MathFactory)
  {
	$scope.person = person;
	$scope.getFullName = function () {
		return SimpleAppService.add(person.firstName,person.lastName)
	}
  });
angular.module("myapp",[])
	.value("user",{
		username: "Please enter username",
		password: "Please enter password"
	})
	.service("loginService")
	.controller("loginController",function ($scope,$http,user,loginService) 
	{
		console.log("We are submitting user "+user.username+" and password is "+user.password);
		$scope.user = user;
		$scope.submitTheForm = function () {
			var responsePromise = $http.post("/login/"+user.username, user, {});
		       responsePromise.success(function(data, status, headers, config) {
		          console.log(data);
		       });
		        responsePromise.error(function(data, status, headers, config) {
		          alert("Submitting form failed!");
		       });
		}
	});