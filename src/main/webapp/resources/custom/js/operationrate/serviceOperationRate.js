(function() {

	angular.module("operationRateApp").factory("operaService", ServiceOperationRate);

	ServiceOperationRate.$inject = [ '$http' ];

	function ServiceOperationRate($http) {

		var operaSvc = {
			msg: "operationRates service",
			/*interval : interval,*/
			getOperationRate : getOperationRate,
			getOperation : getOperation,
			getOperationRates : getOperationRates
			
			/*getDayOperationRate : getDayOperationRate,
			getMonthOperationRate : getMonthOperationRate,
			getYearOperationRate : getYearOperationRate*/
		};

		return operaSvc;

		/* 测试  */
	/*	function interval(beginDayTime,timenow){
			return 
			$http.get('/ii/operationRate/'+beginDayTime.getTime()+'/'+timenow.getTime()+'/').then(function(response){
				vm.operationRates = response.data;
				console.log(vm.operationRates);
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching operationRates');
			});
		}*/
		/*  测试 */
		function getOperationRate(beginTime,endTime) {
			return $http.get('/ii/operationRate/{}'+beginTime+'/'+endTime+'/').then(function(response){
				   return response.data;
			      }, function(errResponse) {
					console.error('Error while fetching operationRates');
				  });
		}
		
		function getOperation() {
			return $http.get('/ii/operationRate/').then(function(response){
				   return response.data;
			      }, function(errResponse) {
					console.error('Error while fetching operationRates');
				  });
		}
		
		function getOperationRates(beginYearTime,timenow) {
			return $http.get('/ii/operationRate/'+beginYearTime+'/'+timenow+'/').then(function(response){
				   return response.data;
			      }, function(errResponse) {
					console.error('Error while fetching operationRates');
				  });
		}
/*		function getDayOperationRate(beginTime, endTime) {
			return 
			$http.get('/ii/operationRate/{}'+beginTime.getTime()+'/'+endTime.getTime()+'/').then(function(response){
				return response.data;
			}, function(errResponse) {
					console.error('Error while fetching operationRates');
				});
		}
		function getMonthOperationRate(beginTime, endTime){
			return 
			$http.get('/ii/operationRate/{}'+beginTime.getTime()+'/'+endTime.getTime()+'/').then(function(response){
				//vm.dayOperationRates = response.data;
				return response.data;
				}, function(errResponse) {
					console.error('Error while fetching operationRates');
				});
		}
		function getYearOperationRate(beginTime, endTime){
			return 
			$http.get('/ii/operationRate/{}'+beginTime.getTime()+'/'+endTime.getTime()+'/').then(function(response){
				//vm.dayOperationRates = response.data;
				return response.data;
				}, function(errResponse) {
					console.error('Error while fetching operationRates');
				});
		}*/
	}

})()