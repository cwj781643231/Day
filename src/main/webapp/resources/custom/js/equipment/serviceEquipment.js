(function() {

	angular.module("equipmentApp").factory("equipmentService", EquipmentService);

	EquipmentService.$inject = [ '$http' ];

	function EquipmentService($http) {

		var equipmentSvc = {
			msg: "equipment service",
			GetEquipments : GetEquipments,
			CreateEquipment : CreateEquipment,
			UpdateEquipment : UpdateEquipment,
			DeleteEquipment : DeleteEquipment
		};

		return equipmentSvc;

		function GetEquipments() {
			return $http.get('/ii/equipment').then(
					function(response) {
						//vm.equipments = response.data;
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching equipments');
					});
		}
		
		function CreateEquipment(equipment) {
			return $http.post('/ii/equipmentCreate', equipment).then(
				function(response) {
					return response.data;
				}, 
				function(errResponse) {
					console.error('Error while fetching equipments');
				}
			);
		}

		function UpdateEquipment(equipment) {
			return $http.put('/ii/equipmentUpdate', equipment).then(
					function(response) {
						return response.data;
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
				);
		}

		function DeleteEquipment(equipment) {
			return $http.post('/ii/equipmentDelete', equipment).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);
		}


	}

})()