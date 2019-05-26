(function() {

angular.module('scheduleApp')
    .controller('ExampleController', ExampleController);

function ExampleController() {
	vm = this;
    vm.counter = 0;
    vm.change = change;
    
    function change() {
        vm.counter++;
      };
    }

})()