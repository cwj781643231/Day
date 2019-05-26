(function() {

	angular.module("employeeApp").factory("employeeService", EmployeeService);

	EmployeeService.$inject = [ '$http' ];

	function EmployeeService($http) {

		var employeeSvc = {
			msg: "employee service",
			GetEmployees : GetEmployees,
			CreateEmployee : CreateEmployee,
			UpdateEmployee : UpdateEmployee,
			DeleteEmployee : DeleteEmployee
		};

		return employeeSvc;

		function GetEmployees() {
			return $http.get('/ii/employee').then(
				function(response) {
					return response.data;
				}, 
				function(errResponse) {
					console.error('Error while fetching employees');
				}
			);
		}

		function CreateEmployee(employee) {
			return $http.post('/ii/employee', employee).then(
				function(response) {
					return response.data;
				}, 
				function(errResponse) {
					console.error('Error while fetching employees');
				}
			);
		}

		function UpdateEmployee(employee) {
			return $http.put('/ii/employee', employee).then(
				function(response) {
					return response.data;
				}, 
				function(errResponse) {
					console.error('Error while fetching employees');
				}
			);
		}

		function DeleteEmployee(employee) {
			return $http.post('/ii/employeeDelete', employee).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching employees');
					}
			);
		}

	}

})()