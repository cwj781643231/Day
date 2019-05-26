(function() {

	angular.module("scheduleApp").factory("scheduleService",ScheduleService);
	ScheduleService.$inject = [ '$http' ];
	function ScheduleService($http){

	     var scheduleSvc = {
			msg : "schedule service",
			GetScheduleTypesByCode : GetScheduleTypesByCode,
			GetSchedulesByDay : GetSchedulesByDay,
			CreateSchedule : CreateSchedule,
			UpdateSchedule : UpdateSchedule,
			DeleteSchedule : DeleteSchedule
		};

		return scheduleSvc;
		
		function GetScheduleTypesByCode(scheduleTypeCode) {
			return $http.get(
					'/ii/scheduleType/'
							+ scheduleTypeCode).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching scheduleTypes');
			});
		}
		
        function GetSchedulesByDay(scheduleDayString) {
			return $http.get(
					'/ii/dailySchedule/'
							+ scheduleDayString).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});
		}
		
    	function CreateSchedule(schedule) {
    		return $http.post('/ii/dailySchedule',
					schedule).then(function(response) {
				return response.data;		
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});
		}
    	
    	function UpdateSchedule(schedule) {
			return $http.put('/ii/dailySchedule',
							schedule).then(function(response) {
					return response.data;		
					}, function(errResponse) {
						console.error('Error while fetching schedules');
					});
		}
    	
    	function DeleteSchedule(beginDateString, endDateString) {			
    		 return	$http.post(
						'/ii/dailyScheduleDelete/'
								+ beginDateString + '/' + endDateString).then(
						function(response) {
							return response.data;	
						}, function(errResponse) {
							console.error('Error while fetching schedules');
						});
		}
    	
	}
	
})()