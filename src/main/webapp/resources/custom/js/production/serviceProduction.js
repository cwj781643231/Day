(function() {

	angular.module("productionApp").factory('productionService', ProductionService);
	
	function ProductionService(){
		var msg = ["hello","world!"];
		var correctAnswers = [1, 2, 3, 0, 2, 0, 3, 2, 0, 3];
		
		var serviceObj = {
				msg: msg,
				correctAnswers: correctAnswers,
				Sayhello: Sayhello
		};
		
		return serviceObj;
		
		
		
		function Sayhello(){
			
			return "world!";
		}
		
	}

})();