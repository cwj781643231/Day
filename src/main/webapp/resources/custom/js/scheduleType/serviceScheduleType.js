(function() {

	angular.module("scheduleTypeApp").factory("scheduleTypeService", ScheduleTypeService);

	ScheduleTypeService.$inject = [ '$http' ];

	function ScheduleTypeService($http) {

		var scheduletypeSvc = {
			msg: "scheduletype service",
			GetScheduleTypes : GetScheduleTypes,			
			CreateScheduleType : CreateScheduleType,		
			UpdateScheduleType : UpdateScheduleType,			
			DeleteScheduleType : DeleteScheduleType,
			Export : Export
		};

		return scheduletypeSvc;

		function GetScheduleTypes() {
			return $http.get('/ii/scheduleType').then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching scheduleTypes');
					});
		}
		
		function CreateScheduleType(scheduleType) {
			return $http.post('/ii/scheduleType', scheduleType)
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching scheduleTypes');
					});
		}
		
		function UpdateScheduleType(scheduleType) {
			return $http.put('/ii/scheduleType', scheduleType)
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching scheduleTypes');
					});
		}
		
		function DeleteScheduleType(scheduleType) {
				 return $http.post('/ii/scheduleTypeDelete',
						scheduleType).then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while fetching scheduleTypes');
				});

		}
		
		 function Export() {
	        	return $http.get('/ii/download_project.do').then(
						function(response) {
							//vm.schedules = response.data;
							return response.data;
						}, function(errResponse) {
							console.error('Error while fetching schedules');
						});

			}
		
	}

})()