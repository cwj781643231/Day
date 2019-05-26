(function() {
	                                   
	angular.module("usersApp").controller("usersController",
			UsersController);          
	
	UsersController.$inject = [ 'userService' ];

	function UsersController(userService) {

	var vm = this;

	vm.login = login;
	
	/*vm.Init = Init; // 初始化vm.employees, vm.equipments,
	vm.Init();*/
	
	vm.user = [];
	//登录返回状态
	vm.state = [];
	/*
	 * 返回 login 字符串
	 * */
	function login(user) {alert(1);
		userService.login(user).then(
					function(data) {alert(2);
					alert(data);
						vm.state = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching users');
					}
			);
		}
	
}})();