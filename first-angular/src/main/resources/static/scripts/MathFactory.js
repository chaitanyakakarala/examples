angular.module("mainModule").factory('MathFactory',function () {
	var factory = {};
	
	factory.multiply = function (a,b) {
		return a*b;
	}
	
	factory.add = function (a,b) {
		return a+b;
	}
	
	factory.sub = function (a,b) {
		return a-b;
	}
	
	return factory;
}); 
