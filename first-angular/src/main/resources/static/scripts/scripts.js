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
angular.module("loginmodule",[])
	.value("user",{
		username: "Please enter username",
		password: "Please enter password"
	})
	.service("loginService")
	.controller("LoginController",function ($scope,$http,user,loginService) 
	{
		console.log("We are submitting user "+user.username+" and password is "+user.password);
		$scope.user = user;
		$scope.user.username=user.username;
		$scope.user.password=user.password;
		$scope.submitForm = function () {
			loginService.submitUser(user);
		}
	});