(function() {

	angular.module("equipmentApp").controller("equipmentController",
			EquipmentController);

	EquipmentController.$inject = [ 'equipmentService' ];

	function EquipmentController(equipmentService) {

		
		var vm = this;
		vm.equipments = [];
		vm.index = 0;
		vm.newEquipment = {};
		vm.selectedEquipment = {};

		vm.GetEquipments = GetEquipments;
		vm.CreateEquipment = CreateEquipment;
		vm.UpdateEquipment = UpdateEquipment;
		vm.DeleteEquipment = DeleteEquipment;

		vm.EditEquipment = EditEquipment;
		vm.AddEquipment = AddEquipment;
		
		

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
		vm.GetEquipments();

		function CreateEquipment(equipment) {
			equipmentService.CreateEquipment(equipment).then(
					function(data) {
						//alert("data:"+data);
						// vm.employees = response.data;
						if(data == 0){
							alert("已有该设备，添加新设备失败");
						}else{
						alert("新设备：'" + equipment.equipmentName + "'信息已创建!");
						$('#equipment-new').modal('hide');
						vm.GetEquipments();
					    }
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);
		}

		function UpdateEquipment(equipment) {
			equipmentService.UpdateEquipment(equipment).then(
					function(data) {
						if(data == 0){
							alert("该设备信息已存在，请重新修改");
						}
						if(data == -1){
							alert("多条重复记录，系统逻辑有问题。");
						}
						if(data != 0 && data != -1){
							alert("设备：'" + equipment.equipmentName + "'信息已保存!");
							$('#equipment-info').modal('hide');
							vm.GetEquipments();
						}
						
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);

		}

		function DeleteEquipment(equipment) {
			if (confirm("确实要删除设备 '" + equipment.equipmentName + "' 吗？")) {
				equipmentService.DeleteEquipment(equipment).then(
					function(response) {
						// vm.employees = response.data;
						alert("设备：'" + equipment.equipmentName + "'信息已删除!");
						$('#equipment-info').modal('hide');
						vm.GetEquipments();
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
				);

			}

		}

		function EditEquipment(equipment) {
			equipment.createdTime = new Date(equipment.createdTime);
			alert(equipment);
			vm.selectedEquipment = equipment;
			// alert(vm.selectedEmployee.name);
		}

		function AddEquipment() {

			vm.newEquipment = {};
			// alert(vm.selectedEmployee.name);
		}


	}

})();