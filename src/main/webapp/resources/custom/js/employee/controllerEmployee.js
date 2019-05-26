(function() {

	angular.module("employeeApp").controller("employeeController",
			EmployeeController);

	EmployeeController.$inject = [ '$filter','equipmentService','operaService','reportService','scheduleTypeService','scheduleService','employeeService'];

	function EmployeeController($filter,equipmentService,operaService,reportService,scheduleTypeService,scheduleService,employeeService) {

		var vm = this
		vm.employees = [];
		vm.index = 0;
		vm.newEmployee = {};
		vm.selectedEmployee = {};

		vm.GetEmployees = GetEmployees;
		vm.CreateEmployee = CreateEmployee;
		vm.UpdateEmployee = UpdateEmployee;
		vm.DeleteEmployee = DeleteEmployee;

		vm.EditEmployee = EditEmployee;
		vm.AddEmployee = AddEmployee;

		vm.Init = Init; // 初始化vm.employees, vm.equipments,
		vm.scheduleTypes = [];
		
		vm.newScheduleType = {};
		vm.selectedScheduleType = {};

		vm.GetScheduleTypes = GetScheduleTypes;
		
		vm.GetEquipments = GetEquipments;
		
		vm.GetScheduleTypesByCode = GetScheduleTypesByCode;
		
		vm.GetSchedulesByDay = GetSchedulesByDay;
		
		vm.EditScheduleType = EditScheduleType;
		
		vm.CreateDateSchedule = CreateDateSchedule;
		
		vm.UpdateScheduleType = UpdateScheduleType;
		
		vm.NewSchedule = NewSchedule;
		
		vm.RadomSchedule = RadomSchedule;
		
		vm.SaveSchedule = SaveSchedule;
		vm.today = new Date();
		vm.beginDate = new Date(vm.today.getFullYear(), vm.today.getMonth(),
				vm.today.getDate() + 1);
		vm.endDate = new Date(vm.beginDate.getFullYear(), vm.beginDate
				.getMonth() + 1, 0);

		vm.scheduleTypeCode = "三班制";

		vm.schedules = [];
	
		vm.employeeOptions = [];
		vm.equipments = [];
	
		vm.curschedules = [];
		vm.myArr= [];
		
		vm.alldaily = [];
		
	
		
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
		//vm.GetEmployees();

		function CreateEmployee(employee) {
			employeeService.CreateEmployee(employee).then(
					function(response) {
						// vm.employees = response.data;
						alert("新用户：'" + employee.name + "'信息已创建!");
						$('#employee-new').modal('hide');
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);
		}

		function UpdateEmployee(employee) {
			employeeService.UpdateEmployee(employee).then(
					function(response) {
						// vm.employees = response.data;
						alert("用户：'" + employee.name + "'信息已保存!");
						$('#employee-info').modal('hide');
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);

		}

		function DeleteEmployee(employee) {
			if (confirm("确实要删除用户 '" + employee.name + "' 吗？")) {
				employeeService.DeleteEmployee(employee).then(
					function(response) {
						// vm.employees = response.data;
						alert("用户：'" + employee.name + "'信息已删除!");
						$('#employee-info').modal('hide');
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
				);

			}

		}

		function EditEmployee(employee) {
			employee.birthday = new Date(employee.birthday);
			vm.selectedEmployee = employee;
			// alert(vm.selectedEmployee.name);
		}

		function AddEmployee() {

			vm.newEmployee = {};
			// alert(vm.selectedEmployee.name);
		}

		
		/*
		 * 排班配置
		 * */
		function GetScheduleTypes() {
			scheduleTypeService.GetScheduleTypes().then(
					function(data) {
						vm.scheduleTypes = data;
						console.log(vm.scheduleTypes);
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);
		}

		function CreateScheduleType(scheduleType) {
			scheduleTypeService.CreateScheduleType(scheduleType).then(
					function(data) {
						alert("新班产：'" + scheduleType.scheduleTypeName + "'信息已创建!");
						$('#scheduleType-new').modal('hide');
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);
		}

		function UpdateScheduleType(scheduleType) {
			
			if(scheduleType.state == 'Y'){
				scheduleType.state = 'N';
			}else if(scheduleType.state == 'N'){
				scheduleType.state = 'Y'
			}
			scheduleTypeService.UpdateScheduleType(scheduleType).then(
					function(data) {
						alert("班产：'" + scheduleType.scheduleTypeName + "'信息已保存!");
						//$('#scheduleType-info').modal('hide');
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);

		}

		function DeleteScheduleType(scheduleType) {
			if (confirm("确实要删除班产 '" + scheduleType.scheduleTypeName + "' 吗？")) {
				scheduleTypeService.UpdateScheduleType(scheduleType).then(
						function(data) {
						alert("班产：'" + scheduleType.scheduleTypeName + "'信息已删除!");
						}, 
						function(errResponse) {
							console.error('Error while fetching scheduleTypes');
						}
				);

			}

		}

		function EditScheduleType(scheduleType) {
			vm.selectedScheduleType = scheduleType;
		}

		function AddScheduleType() {

			vm.newScheduleType = {};
		}
      
		function Export() {
			scheduleTypeService.Export().then(
	 					function(data) {
	 						
	 					}, 
	 					function(errResponse) {
	 						console.error('Error while fetching schedules');
	 					}
	 			);

	 	}
		
		/*
		 * 排班计划
		 * */
		function GetScheduleTypesByCode(scheduleTypeCode) {
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
			vm.selectedSchedule = schedule;
		}

		function AddSchedule() {

			vm.newSchedule = {};
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
			var scheduleDayString = $filter('date')(scheduleDay, "yyyy-MM-dd");
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
				CreateSchedule(currentSchedule);
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
		
		
		  function Init() {
				vm.GetEmployees();
				vm.GetScheduleTypes();
				vm.equipments = vm.GetEquipments();
				//vm.scheduleTypes = vm.GetScheduleTypesByCode(vm.scheduleTypeCode);
				vm.schedules = vm.GetSchedulesByDay(vm.beginDate);
			}	
	}

})();