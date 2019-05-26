(function() {

	angular.module('eventsApp').factory('eventsService', EventsService);

	EventsService.$inject = [ '$http' ];

	function EventsService($http) {
		
		var eventsSvc = {
			msg: "events Service",
			GetEvents: GetEvents,	
			GetEventsBytime: GetEventsBytime,
			GetEventsById: GetEventsById,
			GetCountBytime: GetCountBytime,
			GetCountBytimeId: GetCountBytimeId,
			GetEventsBytimeId: GetEventsBytimeId
		};
		
		return eventsSvc;

		function GetEventsBytime(beginLongTime, endLongTime, currentPage) {
			return $http.get(
					'/ii/events/' + beginLongTime + "/"
							+ endLongTime + "/"
							+ currentPage ).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetEventsById(beginLongTime, endLongTime, equipmentId, currentPage) {
			return $http.get(
					'/ii/events/' + beginLongTime + "/"
							+ endLongTime + "/"
							+ equipmentId + "/"
							+ currentPage ).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetEvents(beginLongTime, endLongTime) {
			return $http.get(
					'/ii/listevents/' + beginLongTime + "/"
					+ endLongTime).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetCountBytime(beginLongTime, endLongTime) {
			return $http.get(
					'/ii/countBytime/' + beginLongTime + "/"
							+ endLongTime ).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetCountBytimeId(beginLongTime, endLongTime, equipmentId) {
			return $http.get(
					'/ii/countBytime/' + beginLongTime + "/"
							+ endLongTime + "/"
							+ equipmentId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetEventsBytimeId(beginLongTime, endLongTime, equipmentId) {
			return $http.get(
					'/ii/listevents/' + beginLongTime + "/"
							+ endLongTime + "/"
							+ equipmentId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}

	}

})()