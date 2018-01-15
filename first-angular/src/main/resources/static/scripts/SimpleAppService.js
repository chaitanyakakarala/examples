angular.module("mainModule").
service("SimpleAppService",function (MathFactory){
	this.square=function(a){
		return MathFactory.multiply(a,a);
	}
	this.add=function(a,b) {
		return MathFactory.add(a,b);
	}
});