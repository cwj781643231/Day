(function() {

	angular.module("productionApp").controller('productionController', ProductionController);
	
	ProductionController.$inject = ['productionService', 'employeeService'];
	
	function ProductionController(productionService, employeeService){
		var vm = this;
		//vm.productionService = productionService;
		vm.hello = productionService.msg;
		vm.correctAnswers = productionService.correctAnswers;
		console.log(vm.correctAnswers);
		vm.employees = [];
		
		vm.Sayhello = Sayhello;
		
		function Sayhello(){
			return productionService.Sayhello();
		}
		
		employeeService.GetEmployees().then(
				function(data){
					vm.employees = data;
				},
				function(){
					console.log("get employees failed.");
				});
		
	}

})();