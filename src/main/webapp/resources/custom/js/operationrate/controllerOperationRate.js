(function() {

	angular.module("operationRateApp").controller('operationRateController',
			OperationRateController);
	
	OperationRateController.$inject = [ '$interval', 'operaService' ];
	
	function OperationRateController($interval, operaService){
		var vm = this;
		//alert("bef!");
		today = new Date();
		beginDayTime = new Date(today.getFullYear(), today.getMonth(), today.getDay());
		beginMonthTime = new Date(today.getFullYear(), today.getMonth(), 1);
		beginYearTime = new Date(today.getFullYear(), 1, 1);
		
		vm.beginTime = new Date();
		vm.endTime = new Date();
		
		vm.operationRates = [];
		vm.dayOperationRates = [];
		vm.monthOperationRates = [];
		vm.yearOperationRates = [];
		
		vm.dayOperationRate = {"onTime":0,"offTime":0,"operationTime":0, "waitTime":0};
		vm.monthOperationRate =  {"onTime":0,"offTime":0,"operationTime":0, "waitTime":0};
		vm.yearOperationRate =  {"onTime":0,"offTime":0,"operationTime":0, "waitTime":0};
		
		var myChartYear = echarts.init(document.getElementById('productionYear'));
		var myChartMonth = echarts.init(document.getElementById('productionMonth'));
		var myChartDay = echarts.init(document.getElementById('productionDay'));
		
		var optionYear = initOptionProduction(); 
		var optionMonth = initOptionProduction();
		var optionDay = initOptionProduction();
		
		myChartYear.setOption(optionYear);
		myChartMonth.setOption(optionMonth);
		myChartDay.setOption(optionDay);
		
		$interval(function(){
			//var timenow = new Date();
			
		/*	$http.get('/ii/operationRate/').then(function(response){
				vm.operationRates = response.data;
				console.log(vm.operationRates);
			}, function(errResponse) {
				console.error('Error while fetching operationRates');
			});*/
			
			operaService.getOperation().then(
					function(data) {
						vm.operationRates = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
			
		},2000);
		
		$interval(function(){
			var timenow = new Date();	
			/*$http.get('/ii/operationRate/'+beginDayTime.getTime()+'/'+timenow.getTime()+'/').then(function(response){
				vm.dayOperationRates = response.data;
				vm.dayOperationRate = calculateTime(vm.dayOperationRates);
				myChartDay.setOption(updateOption(optionDay,vm.dayOperationRate,"今日开机"));
				}, function(errResponse) {
					console.error('Error while fetching operationRates');
			});*/
			
			operaService.getOperationRates(beginDayTime.getTime(), timenow.getTime()).then(
					function(data) {
						vm.dayOperationRates = data;
						vm.dayOperationRate = calculateTime(vm.dayOperationRates);
						myChartDay.setOption(updateOption(optionDay,vm.dayOperationRate,"今日开机"));
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
		},2000);
		
		$interval(function(){
			var timenow = new Date();
			
		/*	$http.get('/ii/operationRate/'+beginMonthTime.getTime()+'/'+timenow.getTime()+'/').then(function(response){
				vm.monthOperationRates = response.data;
				vm.monthOperationRate = calculateTime(vm.monthOperationRates);
				myChartMonth.setOption(updateOption(optionMonth,vm.monthOperationRate,"本月开机"));
				}, function(errResponse) {
					console.error('Error while fetching operationRates');
			});*/
			
			operaService.getOperationRates(beginMonthTime.getTime(), timenow.getTime()).then(
					function(data) {
						vm.monthOperationRates = data;
						vm.monthOperationRate = calculateTime(vm.monthOperationRates);
						myChartMonth.setOption(updateOption(optionMonth,vm.monthOperationRate,"本月开机"));
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
		},2000);
		
		$interval(function(){
			var timenow = new Date();	
		/*	$http.get('/ii/operationRate/'+beginYearTime.getTime()+'/'+timenow.getTime()+'/').then(function(response){
				vm.yearOperationRates = response.data;
				vm.yearOperationRate = calculateTime(vm.yearOperationRates);
				myChartYear.setOption(updateOption(optionYear,vm.yearOperationRate,"今年开机"));
				}, function(errResponse) {
					console.error('Error while fetching operationRates');
			});*/
			
			operaService.getOperationRates(beginYearTime.getTime(),timenow.getTime()).then(
					function(data) {
						vm.yearOperationRates = data;
						vm.yearOperationRate = calculateTime(vm.yearOperationRates);
						myChartYear.setOption(updateOption(optionYear,vm.yearOperationRate,"今年开机"));
						
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
		},2000);
		
		/*
		 getDayOperationRate(beginDayTime, new Date()).then(function(response){
				vm.dayOperationRate = calculateTime(vm.dayOperationRates);
				updateOption(optionDay,vm.dayOperationRate,"今日开机");
			}, function(errResponse) {
				console.error('Error while fetching operationRates');
			});
			getMonthOperationRate(beginDayTime, new Date()).then(function(response){
				vm.monthOperationRate = calculateTime(vm.monthOperationRates);
				updateOption(optionMonth,vm.monthOperationRate,"本月开机");
			}, function(errResponse) {
				console.error('Error while fetching operationRates');
			});
			getYearOperationRate(beginDayTime, new Date()).then(function(response){
				vm.yearOperationRate = calculateTime(vm.yearOperationRates);
				updateOption(optionYear,vm.yearOperationRate,"今年开机");
			}, function(errResponse) {
				console.error('Error while fetching operationRates');
			});
		  
		 */	
		
		
		//无参
		function getOperationRate(){
			operaService.getOperation().then(
					function(data) {
						vm.operationRates = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
		}
		
		
		function getDayOperationRate(){
			operaService.getOperationRate(beginTime, endTime).then(
					function(data) {
						vm.dayOperationRates = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
		}
		
		function getMonthOperationRate(){
			operaService.getOperationRate(beginTime, endTime).then(
					function(data) {
						vm.dayOperationRates = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
		}
		
		function getYearOperationRate(){
			operaService.getOperationRate(beginTime, endTime).then(
					function(data) {
						vm.dayOperationRates = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching operationRates');
					}
			);
		}
		
		function calculateTime(operationRates){
			var onTime = 0;
			var offTime = 0;
			var operationTime = 0;
			var waitTime = 0;
			
			var i;
			               
			for(i = 0; i < operationRates.length; i++){
				onTime = onTime + operationRates[i].onTime;
				offTime = offTime + operationRates[i].offTime;
				operationTime = operationTime + operationRates[i].operationTime;
				waitTime = waitTime + operationRates[i].waitTime;
			} 
			//var total = onTime + offTime;
			var operationRate = {"onTime":onTime,
					"offTime":offTime,
					"operationTime":operationTime,
					"waitTime":waitTime
					}
			return operationRate;
		}
		
		function initOptionProduction(){
			var option = {
				    tooltip : {
				        formatter: "{a} <br/>{b} : {c}%"
				    },
				    series: [
				        {
				            name: '业务指标',
				            type: 'gauge',
				            detail: {formatter:'{value}%'},
				            data: [{value: 50, name: '开机率'}],
				            axisLine: {            // 坐标轴线
				                lineStyle: {       // 属性lineStyle控制线条样式
				                    color: [[0.2, 'red'],[0.8, '#337ab7'],[1, 'green']]
				                }
				            }
				        }
				    ]
				};
			return option;
		}
		
		function updateOptionProduction(option,operationRate){
			option.series[0].data = [
			                {value:(operationRate.onTime/(operationRate.onTime + operationRate.offTime)*100).toFixed(2), name:'开机率'}
			                ];
			//alert(option.series[0].data[4].value);
			return option;
		}
		
		function updateOption(option,operationRate,typename){
			option.series[0].data = [
			                {value:(operationRate.onTime/(operationRate.onTime + operationRate.offTime)*100).toFixed(2), name:typename}
			                ];
			//alert(option.series[0].data[4].value);
			return option;
		}

		function updateOptionYear(option,operationRate){
			option.series[0].data = [
			                {value:(operationRate.onTime/(operationRate.onTime + operationRate.offTime)*100).toFixed(2), name:'年开机率'}
			                ];
			//alert(option.series[0].data[4].value);
			return option;
		}

		function updateOptionMonth(option,operationRate){
			option.series[0].data = [
			                {value:(operationRate.onTime/(operationRate.onTime + operationRate.offTime)*100).toFixed(2), name:'月开机率'}
			                ];
			//alert(option.series[0].data[4].value);
			return option;
		}

		function updateOptionDay(option,operationRate){
			option.series[0].data = [
			                {value:(operationRate.onTime/(operationRate.onTime + operationRate.offTime)*100).toFixed(2), name:'日开机率'}
			                ];
			//alert(option.series[0].data[4].value);
			return option;
		}
	}

})();