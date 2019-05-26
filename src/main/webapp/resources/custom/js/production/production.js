var productionApp = angular.module('productionApp', []);

productionApp.controller('operationRateControl', ['$interval','$http', function($interval, $http) {
	var self = this;
	
	var myChartYear = echarts.init(document.getElementById('productionYear'));
	var myChartMonth = echarts.init(document.getElementById('productionMonth'));
	var myChartDay = echarts.init(document.getElementById('productionDay'));
	
	var optionYear = initOptionProduction(); 
	var optionMonth = initOptionProduction();
	var optionDay = initOptionProduction();
	
	myChartYear.setOption(optionYear);
	myChartMonth.setOption(optionMonth);
	myChartDay.setOption(optionDay);
	
	self.operationRates = [];
	
	$interval(function(){
		$http.get('/ii/operationRate/').then(function(response){
		self.operationRates = response.data;
		
		var operationRateYear = calculateTimeOfYear(self.operationRates);
		var operationRateMonth = calculateTimeOfMonth(self.operationRates);
		var operationRateDay = calculateTimeOfDay(self.operationRates);
		
		myChartYear.setOption(updateOptionYear(optionYear, operationRateYear));	
		myChartMonth.setOption(updateOptionMonth(optionMonth, operationRateMonth));	
		myChartDay.setOption(updateOptionDay(optionDay, operationRateDay));	
		
		}, function(errResponse) {
			console.error('Error while fetching operationRates');
		})},2000);
	}]);

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
	var total = onTime + offTime;
	var operationRate = {"onTime":onTime,
			"offTime":offTime,
			"total":total			
			}
	return operationRate;
}

function calculateTimeOfYear(operationRates){
	var onTimeOfYear = 0;
	var offTimeOfYear = 0;
	var operationTimeOfYear = 0;
	var waitTimeOfYear = 0;
	
	var i;
	
	for(i = 0; i < operationRates.length; i++){
		onTimeOfYear = onTimeOfYear + operationRates[i].onTimeOfYear;
		offTimeOfYear = offTimeOfYear + operationRates[i].offTimeOfYear;
		operationTimeOfYear = operationTimeOfYear + operationRates[i].operationTimeOfYear;
		waitTimeOfYear = waitTimeOfYear + operationRates[i].waitTimeOfYear;
	} 
	var totalOfYear = onTimeOfYear + offTimeOfYear;
	var operationRate = {"onTime":onTimeOfYear,
			"offTime":offTimeOfYear,
			"total":totalOfYear			
			}
	return operationRate;
}

function calculateTimeOfMonth(operationRates){
	var onTimeOfMonth = 0;
	var offTimeOfMonth = 0;
	var operationTimeOfMonth = 0;
	var waitTimeOfMonth = 0;
	
	var i;
	
	for(i = 0; i < operationRates.length; i++){
		onTimeOfMonth = onTimeOfMonth + operationRates[i].onTimeOfMonth;
		offTimeOfMonth = offTimeOfMonth + operationRates[i].offTimeOfMonth;
		operationTimeOfMonth = operationTimeOfMonth + operationRates[i].operationTimeOfMonth;
		waitTimeOfMonth = waitTimeOfMonth + operationRates[i].waitTimeOfMonth;
	} 
	var totalOfMonth = onTimeOfMonth + offTimeOfMonth;
	var operationRate = {"onTime":onTimeOfMonth,
			"offTime":offTimeOfMonth,
			"total":totalOfMonth			
			}
	return operationRate;
}


function calculateTimeOfDay(operationRates){
	var onTimeOfDay = 0;
	var offTimeOfDay = 0;
	var operationTimeOfDay = 0;
	var waitTimeOfDay = 0;
	
	var i;
	
	for(i = 0; i < operationRates.length; i++){
		onTimeOfDay = onTimeOfDay + operationRates[i].onTimeOfDay;
		offTimeOfDay = offTimeOfDay + operationRates[i].offTimeOfDay;
		operationTimeOfDay = operationTimeOfDay + operationRates[i].operationTimeOfDay;
		waitTimeOfDay = waitTimeOfDay + operationRates[i].waitTimeOfDay;
	} 
	var totalOfDay = onTimeOfDay + offTimeOfDay;
	var operationRate = {"onTime":onTimeOfDay,
			"offTime":offTimeOfDay,
			"total":totalOfDay			
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
	                {value:(operationRate.onTime/operationRate.total*100).toFixed(2), name:'开机率'}
	                ];
	//alert(option.series[0].data[4].value);
	return option;
}

function updateOptionYear(option,operationRate){
	option.series[0].data = [
	                {value:(operationRate.onTime/operationRate.total*100).toFixed(2), name:'年开机率'}
	                ];
	//alert(option.series[0].data[4].value);
	return option;
}

function updateOptionMonth(option,operationRate){
	option.series[0].data = [
	                {value:(operationRate.onTime/operationRate.total*100).toFixed(2), name:'月开机率'}
	                ];
	//alert(option.series[0].data[4].value);
	return option;
}

function updateOptionDay(option,operationRate){
	option.series[0].data = [
	                {value:(operationRate.onTime/operationRate.total*100).toFixed(2), name:'日开机率'}
	                ];
	//alert(option.series[0].data[4].value);
	return option;
}