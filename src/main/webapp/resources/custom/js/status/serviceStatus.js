(function() {

	angular.module("statusApp").factory("statusService", StatusService);

	StatusService.$inject = [ '$http' ];

	function StatusService($http) {

		var statusSvc = {
			msg: "status service",
			
		};

		return statusSvc;
		
		
	}

})()