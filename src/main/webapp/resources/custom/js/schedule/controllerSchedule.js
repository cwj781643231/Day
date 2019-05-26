(function() {
	                                
	angular.module("scheduleApp").controller("scheduleController",
			ScheduleController);
	
	ScheduleController.$inject = [ '$filter','employeeService','equipmentService','operaService','reportService','scheduleTypeService','scheduleService'];

	function ScheduleController($filter,employeeService,equipmentService,operaService,reportService,scheduleTypeService,scheduleService) {

		var vm = this;

		vm.today = new Date();
		vm.beginDate = new Date(vm.today.getFullYear(), vm.today.getMonth(),
				vm.today.getDate() + 1);
		vm.endDate = new Date(vm.beginDate.getFullYear(), vm.beginDate
				.getMonth() + 1, 0);

		vm.scheduleTypeCode = "三班制";

		vm.schedules = [];
		vm.employees = [];
		vm.employeeOptions = [];
		vm.equipments = [];
		vm.scheduleTypes = [];

		vm.GetEmployees = GetEmployees;
		vm.GetEquipments = GetEquipments;
		// vm.GetScheduleTypes = GetScheduleTypes;
		vm.GetScheduleTypesByCode = GetScheduleTypesByCode;

		// vm.GetSchedules = GetSchedules;
		vm.GetSchedulesByDay = GetSchedulesByDay;
		vm.CreateSchedule = CreateSchedule;
		vm.UpdateSchedule = UpdateSchedule;
		// vm.DeleteSchedule = DeleteSchedule; //改为内部函数，不需要页面引用

		/*
		 * // vm.EditSchedule = EditSchedule; // vm.AddSchedule = AddSchedule;
		 */
		vm.Init = Init; // 初始化vm.employees, vm.equipments, vm.scheduleTypes,
						// vm.schedules
		// vm.SearchEmployeeName = SearchEmployeeName; //改为内部函数，不需要页面引用

		vm.NewSchedule = NewSchedule;
		vm.ChangeScheduleType = ChangeScheduleType;
		vm.CreateDateSchedule = CreateDateSchedule;
		vm.RadomSchedule = RadomSchedule;
		vm.ChangeEmployee = ChangeEmployee;
		// vm.SaveDailySchedule = SaveDailySchedule; //改为内部函数，不需要页面引用
		vm.SaveSchedule = SaveSchedule;

		// call init function
		vm.Init();

		function GetEmployees() {
			employeeService.GetEmployees().then(
					function(data) {
						vm.employees = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);
		}
		// vm.GetEmployees();

		function GetEquipments() {
			equipmentService.GetEquipments().then(
					function(data) {
						vm.equipments = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);
		}

		function GetScheduleTypes() {
			scheduleTypeService.GetScheduleTypes().then(
					function(data) {
						vm.scheduleTypes = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching scheduleTypes');
					}
			);
		}

		function GetScheduleTypesByCode(scheduleTypeCode) {
		/*	return $http.get(
					'/ii/scheduleType/'
							+ scheduleTypeCode).then(function(response) {
				vm.scheduleTypes = response.data;
				return vm.scheduleTypes;
			}, function(errResponse) {
				console.error('Error while fetching scheduleTypes');
			});*/
			scheduleService.GetScheduleTypesByCode(scheduleTypeCode).then(
					function(data) {
						vm.scheduleTypes = data;
						return vm.scheduleTypes;
					}, 
					function(errResponse) {
						console.error('Error while fetching scheduleTypes');
					}
			);
		}

		function GetSchedules() {
			reportService.GetSchedules().then(
					function(data) {
						vm.schedules = data;			
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}

		function GetSchedulesByDay(scheduleDay) {
			
			var scheduleDayString = $filter('date')(scheduleDay, "yyyy-MM-dd");
			console.log("search schedules for day:"+scheduleDayString);			
			scheduleService.GetSchedulesByDay(scheduleDayString).then(
					function(data) {
						vm.schedules = data;			
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}

		function CreateSchedule(schedule) {
			/*$http.post('/ii/dailySchedule',
					schedule).then(function(response) {
				// vm.schedules = response.data;
				console.log("新排班信息已创建!");
				// $('#schedule-new').modal('hide');
			}, function(errResponse) {
				console.error('Error while fetching schedules');
			});*/
			scheduleService.CreateSchedule(schedule).then(
					function(data) {
						console.log("新排班信息已创建!");
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}

		function UpdateSchedule(schedule) {
		/*	$http
					.put('/ii/dailySchedule',
							schedule).then(function(response) {
						// vm.schedules = response.data;
						console.log("排班信息已保存!");
						// $('#schedule-info').modal('hide');
					}, function(errResponse) {
						console.error('Error while fetching schedules');
					});*/
			scheduleService.UpdateSchedule(schedule).then(
					function(data) {
						console.log("新排班信息已创建!");
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
           
		}

		function DeleteSchedule(beginDateString, endDateString) {
			if (confirm("确实要删除原有排班吗？")) {

				/*$http.post(
						'/ii/dailyScheduleDelete/'
								+ beginDateString + '/' + endDateString).then(
						function(response) {
							// vm.schedules = response.data;
							console.log("排班信息已删除!");
							// $('#schedule-info').modal('hide');
						}, function(errResponse) {
							console.error('Error while fetching schedules');
						});*/
				scheduleService.DeleteSchedule(beginDateString,endDateString).then(
						function(data) {
							console.log("排班信息已删除!");
						}, 
						function(errResponse) {
							console.error('Error while fetching schedules');
						}
				);
				return true;
			}
			return false;

		}

		function EditSchedule(schedule) {
			// schedule.birthday = new Date(schedule.birthday);
			vm.selectedSchedule = schedule;
			// console.log(vm.selectedSchedule.name);
		}

		function AddSchedule() {

			vm.newSchedule = {};
			// console.log(vm.selectedSchedule.name);
		}

		function Init() {
			vm.employees = vm.GetEmployees();
			vm.equipments = vm.GetEquipments();
			vm.scheduleTypes = vm.GetScheduleTypesByCode(vm.scheduleTypeCode);
			vm.schedules = vm.GetSchedulesByDay(vm.beginDate);
		}

		function SearchEmployeeName(employeeId) {
			var employeeName = "";
			for (i = 0; i < vm.employees.length; i++) {

				if (vm.employees[i].employeeId == employeeId) {
					employeeName = vm.employees[i].name;
					// console.log("SearchEmployeeName:"+employeeName);
				}
			}

			return employeeName;

		}

		function NewSchedule() {
			return {
				"dailyScheduleId" : 0,
				"dailyScheduleName" : null,
				"scheduleDay" : null,
				"scheduleTypeId" : 0,
				"scheduleTypeName" : null,
				"beginTime" : null,
				"endTime" : null,
				"employeeId" : 0,
				"employeeName" : null,
				"equipmentId" : 0,
				"equipmentName" : null,
				"production" : 0,
				"createdTime" : null,
				"modifiedTime" : null,
				"createdUser" : 0,
				"modifiedUser" : 0
			};
		}

		function ChangeScheduleType() {
			var beginDateString = $filter('date')(vm.beginDate, "yyyy-MM-dd");
			console.log("vm.scheduleTypeCode:"+vm.scheduleTypeCode);
			vm.scheduleTypes = vm.GetScheduleTypesByCode(vm.scheduleTypeCode);
			vm.schedules = vm.GetSchedulesByDay(beginDateString);	
			
		}

		function CreateDateSchedule(scheduleDay) {
			//alert("s");
			var scheduleDayString = $filter('date')(scheduleDay, "yyyy-MM-dd");
			//console.log(scheduleDayString);
			//console.log(vm.equipments);
			//console.log(vm.scheduleTypes);
			var schedule = vm.NewSchedule();
			var schedules = [];
			

			var k = 0;
			for (i = 0; i < vm.equipments.length; i++) {
				for (j = 0; j < vm.scheduleTypes.length; j++) {

					
					k++;

					schedule.dailyScheduleId = k;
					schedule.scheduleDay = scheduleDayString;
					schedule.scheduleTypeId = vm.scheduleTypes[j].scheduleTypeId;
					schedule.scheduleTypeName = vm.scheduleTypes[j].scheduleTypeName;
					schedule.beginTime = vm.scheduleTypes[j].beginTime;
					schedule.endTime = vm.scheduleTypes[j].endTime;
					schedule.equipmentId = vm.equipments[i].equipmentId;
					schedule.equipmentName = vm.equipments[i].equipmentName;

					schedules.push(schedule);
					//console.log(schedule);
					
					schedule = vm.NewSchedule();


				}
			}
			
			vm.schedules = JSON.parse(JSON.stringify(schedules));

		}

		function RadomSchedule(schedules, employees) {
			console.log(schedules);
			console.log(employees);
			for (i = 0; i < schedules.length; i++) {
				if ((schedules[i].employeeId == null || schedules[i].employeeId == 0)
						&& employees.length > 0) {
					schedules[i].employeeId = employees[i % employees.length].employeeId;
					schedules[i].employeeName = employees[i % employees.length].name;
				}

			}
		}

		function ChangeEmployee(employeeId, index) {			
			if (employeeId == null || employeeId == 0) {				
				vm.schedules[index].employeeName = null;
			} else {				
				vm.schedules[index].employeeName = SearchEmployeeName(employeeId);
			}			

		}

		function SaveDailySchedule(schedules, scheduleDay) {
			var scheduleDayString = $filter('date')(scheduleDay,
						"yyyy-MM-dd");
			var currentSchedule = vm.NewSchedule();
			for (i = 0; i < schedules.length; i++) {
				
				currentSchedule = JSON.parse(JSON.stringify(schedules[i]));
				console.log(currentSchedule);
				currentSchedule.scheduleDay = scheduleDayString;
				console.log(currentSchedule.scheduleDay);
				
				vm.CreateSchedule(currentSchedule);
				
				currentSchedule = vm.NewSchedule();
				
			}
		}

		function SaveSchedule(schedules, beginDate, endDate) {
			var beginDateString = $filter('date')(beginDate, "yyyy-MM-dd");
			var endDateString = $filter('date')(endDate, "yyyy-MM-dd");
			if (confirm("确认要重新安排" + beginDateString + "至" + endDateString
					+ "的排班吗？")) {
				DeleteSchedule(beginDateString, endDateString);
				var i = 0;
				while (beginDate <= endDate && i++ <= 360) {
					SaveDailySchedule(schedules, beginDate);
					console.log(beginDate + endDate);
					beginDate = new Date(beginDate.getFullYear(), beginDate
							.getMonth(), beginDate.getDate() + 1);
					
				}
			}
		}

	}

})();