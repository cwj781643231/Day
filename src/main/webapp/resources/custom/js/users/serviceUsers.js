(function() {
	
	angular.module("usersApp").factory('userService', UserService);
	
	UserService.$inject = [ '$http' ];
	
    function UserService($http) {
		
		var userSvc = {
			msg: "user service",
			login: login
		};
		
		return userSvc;
	
		function login(user) {
			//return $http.get('/ii/login',
			return $http.get('/ii/j_spring_security_check',
					user).then(function(response) {
						//vm.schedules = response.data;
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching users');
					});
		}
}

})();