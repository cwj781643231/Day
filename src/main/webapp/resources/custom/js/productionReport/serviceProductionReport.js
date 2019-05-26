(function() {

	angular.module('productionRApp').factory('reportService', ReportService);

	ReportService.$inject = [ '$http' ];

	function ReportService($http) {
		
		var reportSvc = {
			msg: "report Service",
			GetSchedules: GetSchedules,	
			GetSchedulesBetweenDays: GetSchedulesBetweenDays,	
			GetSchedulesBetweenDaysById: GetSchedulesBetweenDaysById,
			GetDailyScheduleSumBetweenDays: GetDailyScheduleSumBetweenDays,	
			GetDailyEmployee: GetDailyEmployee,	
			GetDailyEmployeebyId: GetDailyEmployeebyId,	
			GetAllProduction : GetAllProduction,
			EditEquipbyId : EditEquipbyId,
			UpdateScheduleWeight : UpdateScheduleWeight,
			GetAllEmpDaily : GetAllEmpDaily,
			GetCountBytime: GetCountBytime,
			GetCountBytimeId: GetCountBytimeId,
			GetDailyBytime: GetDailyBytime,
			GetDailyBytimeById: GetDailyBytimeById,
			Export : Export
		};
		
		return reportSvc;

      /*  //查询出设备信息   下拉列表用
		function GetEquipments() {
			return $http.get('/ii/equipment').then(
					function(response) {
						//vm.equipments = response.data;
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching equipments');
					});
		}*/


        //查询出排班信息
		function GetSchedules() {
			return $http.get('/ii/dailySchedule').then(
					function(response) {
						//vm.schedules = response.data;
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching schedules');
					});
		}
		//排班
		function GetSchedulesBetweenDays(beginDayString, endDayString) {
		/*	'/ii/dailySchedule/'*/
			return $http.get(
					'/ii/getdailySchedule/'
							+ beginDayString + "/" + endDayString).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});
		}
		
		function GetSchedulesBetweenDaysById(beginDayString, endDayString, equipmentId) {
				return $http.get(
						'/ii/getdailySchedule/'
								+ beginDayString + "/"
								+ endDayString + "/"
								+ equipmentId).then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while fetching schedules');
				});
			}
		
		//设备总产量
        function GetDailyScheduleSumBetweenDays(beginDayString, endDayString) {
			return $http.get(
					'/ii/sumproduction/'
							+ beginDayString + "/" + endDayString).then(function(response) {
								//console.log(response.data);
								return response.data;
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});
		}
        
        //所有员工设备总产量
        function GetDailyEmployee(beginDayString, endDayString) {
			return $http.get(
					'/ii/sumemployeesta/'
							+ beginDayString + "/" + endDayString).then(function(response) {
				//vm.sumemployee = response.data;
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});
		}
        
        //根据员工ID 查询出设备总产量
        function GetDailyEmployeebyId(beginDayString, endDayString, employeeId) {
    		return $http.get(
    				'/ii/sumemployeestabyid/'
    						+ beginDayString + "/" + endDayString + "/" + employeeId).then(function(response) {
    			//vm.sumemployeebyid = response.data;
    			return response.data;				
    		}, function(errResponse) {
    			console.error('Error while fetching schedules');
    		});
    	}
        
        //所有设备设备总产量
        function GetAllProduction(beginDayString, endDayString) {
			return $http.get(
					'/ii/allproductions/'
							+ beginDayString + "/" + endDayString).then(function(response) {
				//vm.allduction = response.data;
				return response.data;		
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});
		}
        
        //设备产量详情
        function EditEquipbyId(beginDayString, endDayString, equipmentId) {
    		return $http.get(
    				'/ii/sumequipbyid/'
    						+ beginDayString + "/" + endDayString + "/" + equipmentId).then(function(response) {
    			//vm.sumequipid = response.data;
    			//console.log(response.data);
    			return response.data;	
    		}, function(errResponse) {
    			console.error('Error while fetching schedules');
    		});
    	}
        
        function UpdateScheduleWeight(schedule) {
			return $http.post('/ii/calculateWeight',
							schedule).then(function(response) {
					return response.data;	
					//console.log("schedule[" + schedule.scheduleId + "]已更新重量!");
					}, function(errResponse) {
						console.error('Error while fetching schedules');
					});

		}
        
      //所有员工设备总产量
        function GetAllEmpDaily(beginDayString, endDayString) {
			return $http.get(
					'/ii/allempdaily/'
							+ beginDayString + "/" + endDayString).then(function(response) {
				//vm.allduction = response.data;
				return response.data;		
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});
		}
        
        /*function ChangeFilter(employeeId, equipmentId, beginTime, endTime){
   GetDailyEmployee
        	return GetSchedulesBetweenDays(beginTime, endTime).then(function(response){
        		if(!employeeId){
					if(!equipmentId){
					}else{
						//vm.schedules = $filter('filter')(vm.schedules,{'equipmentId':equipmentId});
						data = $filter('filter')(vm.schedules,{'equipmentId':equipmentId});
						return response.data;	
					}
				}else{
					if(!equipmentId){
						//vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId});
					    data = $filter('filter')(vm.schedules,{'employeeId':employeeId});
					    return response.data;	
					}else{
						//vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId, 'equipmentId':equipmentId});
					    data = $filter('filter')(vm.schedules,{'employeeId':employeeId, 'equipmentId':equipmentId});
					    return response.data;	
					}
				}				
			}, function(err) {
				console.error('Error while fetching schedules');
			});
        	
        	//设备总产量
			vm.GetDailyScheduleSumBetweenDays(beginTime, endTime).then(function(){
				if(!employeeId){
					if(!equipmentId){
						//console.log("!-!")
						//vm.schedules = vm.GetSchedulesBetweenDays(vm.beginDate, vm.endDate);						
					}else{
						//console.log("!-+")
						vm.schedules = $filter('filter')(vm.schedules,{'equipmentId':equipmentId});
					}
				}else{
					if(!equipmentId){
						//console.log("+-!")
						vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId});
					}else{
						//console.log("+-+")
						vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId, 'equipmentId':equipmentId});
					
					}
				}				
			}, function(err) {
				console.error('Error while fetching schedules');
			});
		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
			vm.GetSchedulesBetweenDays(beginTime, endTime).then(function(){
				if(!employeeId){
					if(!equipmentId){
						//console.log("!-!")
						//vm.schedules = vm.GetSchedulesBetweenDays(vm.beginDate, vm.endDate);						
					}else{
						//console.log("!-+")
						vm.schedules = $filter('filter')(vm.schedules,{'equipmentId':equipmentId});
					}
				}else{
					if(!equipmentId){
						//console.log("+-!")
						vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId});
					}else{
						//console.log("+-+")
						vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId, 'equipmentId':equipmentId});
					
					}
				}				
			}, function(err) {
				console.error('Error while fetching schedules');
			});
			
			//设备总产量
			vm.GetDailyScheduleSumBetweenDays(beginTime, endTime).then(function(){
				if(!employeeId){
					if(!equipmentId){
						//console.log("!-!")
						//vm.schedules = vm.GetSchedulesBetweenDays(vm.beginDate, vm.endDate);						
					}else{
						//console.log("!-+")
						vm.schedules = $filter('filter')(vm.schedules,{'equipmentId':equipmentId});
					}
				}else{
					if(!equipmentId){
						//console.log("+-!")
						vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId});
					}else{
						//console.log("+-+")
						vm.schedules = $filter('filter')(vm.schedules,{'employeeId':employeeId, 'equipmentId':equipmentId});
					
					}
				}				
			}, function(err) {
				console.error('Error while fetching schedules');
			});
		}*/
        
        function GetCountBytime(beginLongTime, endLongTime) {
			return $http.get(
					'/ii/getcountBytime/' + beginLongTime + "/"
							+ endLongTime ).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetCountBytimeId(beginLongTime, endLongTime, equipmentId) {
			return $http.get(
					'/ii/getcountBytime/' + beginLongTime + "/"
							+ endLongTime + "/"
							+ equipmentId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetDailyBytime(beginLongTime, endLongTime, currentPage) {
			return $http.get(
					'/ii/countdaily/' + beginLongTime + "/"
							+ endLongTime + "/"
							+ currentPage ).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
		
		function GetDailyBytimeById(beginLongTime, endLongTime, equipmentId, currentPage) {
			return $http.get(
					'/ii/countdaily/' + beginLongTime + "/"
							+ endLongTime + "/"
							+ equipmentId + "/"
							+ currentPage ).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching equipments');
			});

		}
        
        /*
         * 导出excel
         * */
        function Export(schedules) {
        	return $http.post('/ii/exportExcel',
					schedules).then(
					function(response) {
						
					}, function(errResponse) {
						console.error('Error while fetching schedules');
					});

		}
		
	}

})()