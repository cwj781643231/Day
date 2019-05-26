(function() {

	angular.module("scheduleTypeApp").controller("scheduleTypeController",
			ScheduleTypeController);

	ScheduleTypeController.$inject = [ 'scheduleTypeService' ];

	function ScheduleTypeController(scheduleTypeService) {

		var vm = this;
		vm.scheduleTypes = [];
		vm.index = 0;
		vm.newScheduleType = {};
		vm.selectedScheduleType = {};

		vm.GetScheduleTypes = GetScheduleTypes;
		vm.CreateScheduleType = CreateScheduleType;
		vm.UpdateScheduleType = UpdateScheduleType;
		vm.DeleteScheduleType = DeleteScheduleType;

		vm.EditScheduleType = EditScheduleType;
		vm.AddScheduleType = AddScheduleType;

		vm.Export = Export;
		function GetScheduleTypes() {
			scheduleTypeService.GetScheduleTypes().then(
					function(data) {
						vm.scheduleTypes = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);
		}
		vm.GetScheduleTypes();

		function CreateScheduleType(scheduleType) {
			/*$http.post('/ii/scheduleType', scheduleType)
					.then(function(response) {
						// vm.schedules = response.data;
						alert("新班产：'" + scheduleType.scheduleTypeName + "'信息已创建!");
						$('#scheduleType-new').modal('hide');
					}, function(errResponse) {
						console.error('Error while fetching scheduleTypes');
					});*/
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
			/*$http.put('/ii/scheduleType', scheduleType)
					.then(function(response) {
						alert("班产：'" + scheduleType.scheduleTypeName + "'信息已保存!");
						$('#scheduleType-info').modal('hide');
					}, function(errResponse) {
						console.error('Error while fetching scheduleTypes');
					});*/
			scheduleTypeService.UpdateScheduleType(scheduleType).then(
					function(data) {
						alert("班产：'" + scheduleType.scheduleTypeName + "'信息已保存!");
						$('#scheduleType-info').modal('hide');
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);

		}

		function DeleteScheduleType(scheduleType) {
			if (confirm("确实要删除班产 '" + scheduleType.scheduleTypeName + "' 吗？")) {
/*				$http.post('/ii/scheduleTypeDelete',
						scheduleType).then(function(response) {
					alert("班产：'" + scheduleType.scheduleTypeName + "'信息已删除!");
				}, function(errResponse) {
					console.error('Error while fetching scheduleTypes');
				});*/
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
	}

})();