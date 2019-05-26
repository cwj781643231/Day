var statusApp = angular.module('statusApp', []);

statusApp.controller('equipmentControl', ['$interval','$http', function($interval, $http) {
	var self = this;
	var myChart = echarts.init(document.getElementById('main'));
	var myOption = initOptionStatus(); 
	myChart.setOption(myOption);
	self.equipments = [];
	self.equipment = {};
	self.ViewEquipment = ViewEquipment;
	//var i = 0;
	$interval(function(){$http.get('/ii/equipment/').then(function(response){
		self.equipments = response.data;
		//i++;
		//var temstatus = self.equipments[0].status;
		//self.equipments[0].status = temstatus + i;
		
		//alert(self.equipments.length);
		var myStatus = status(self.equipments);
		//self.option = initOption();
		myChart.setOption(updateOptionStatus(myOption, myStatus));		
		}, function(errResponse) {
			console.error('Error while fetching equipments');
		})},2000);
	
	function ViewEquipment(EquipmentId){
		console.log(EquipmentId);
		
		$http.get('/ii/equipment/'+EquipmentId).then(function(response){
			self.equipment = response.data;
			console.log(self.equipment);
		},function(errorResponse){});
	
	}
	}]);

function status(equipments){
	var running = 0;
	var canNotConnect = 0;
	var stopped = 0;
	/*var waiting = 0;
	var powerOff = 0;*/
	var unknown = 0;
	var i;
	//alert(equipments.length);
	for(i = 0; i < equipments.length; i++){
		//alert(equipments[i].equipmentStatus);
		switch(equipments[i].equipmentStatus){
		/*case -1:
			canNotConnect++;
			break;
		case 0:
			powerOff++;
			break;
		case 1:
			running++;
			break;
		case 2:
			running++;
			break;
		case 3:
			stopped++;
			break;
		case 4:
			stopped++;
			break;
		case 5:
			waiting++;
			break;
		default :
			unknown++;*/
		case -1:
			canNotConnect++;
			break;
		case 0:
			stopped++;
			break;
		case 1:
			stopped++;
			break;
		case 2:
			running++;
			break;
		case 3:
			running++;
			break;
		default :
			unknown++;
			//break;
		}
	} 
/*	var total = running + canNotConnect + stopped + waiting + powerOff + unknown;
	var status = {"running":running,
			"canNotConnect":canNotConnect,
			"stopped":stopped,
			"waiting":waiting,
			"powerOff":powerOff,
			"unknown":unknown,
			"total":total			
			}*/
	var total = running + canNotConnect + stopped + unknown;
	var status = {"running":running,
			"canNotConnect":canNotConnect,
			"stopped":stopped,
			"unknown":unknown,
			"total":total			
			}
	return status;
}

/*function initOptionStatus(){
	var option = {
	    title : {
	        text: '设备运行状态',
	        //subtext: '纯属虚构',
	        x:'center'
	    },
	    // color: default, primary, success, info, error
	    color : ['#777','#337ab7', '#5cb85c', '#5bc0de', '#d9534f','#CCCCCC'],
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        //data: ['停机','上电停机','运行','等待','无法连接','未知状态']
	        data: ['停机','运行','故障','其它']
	    },
	    series : [
	        {
	            name: '设备数量',
	            type: 'pie',
	            radius : '75%',
	            center: ['50%', '50%'],
	            data:[
	                {value:1, name:'停机'},
	                {value:1, name:'上电停机'},
	                {value:1, name:'运行'},
	                {value:1, name:'等待'},
	                {value:1, name:'无法连接'},
	                {value:1, name:'未知状态'}
	            ],
	            data:[
	                {value:1, name:'停机'},
	                {value:1, name:'运行'},
	                {value:1, name:'故障'},
	                {value:1, name:'其它'},
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	return option;
}*/
function initOptionStatus(){
	
	option = {
	        tooltip : {
	            trigger: 'item'
	        },
	        toolbox: {
	            show : true,
	            feature : {
	                mark : {show: true},
	                dataView : {show: true, readOnly: false},
	                magicType: {show: true, type: ['line', 'bar']},
	                restore : {show: true},
	                saveAsImage : {show: true}
	            }
	        },
	        calculable : true,
	        legend: {
	            data:['停机', '运行', '故障', '其它'],
	            itemGap: 5
	        },
	        grid: {
	            top: '12%',
	            left: '1%',
	            right: '10%',
	            containLabel: true
	        },
	        xAxis: [
	            {
	                type : 'category',
	                data : ['停机', '运行', '故障', '其它'],
	                axisTick: {
	                    alignWithLabel: true
	                }
	            }
	        ],
	        yAxis: [
	            {
	                type : 'value',
	                name : '运行状态 (%)',
	                axisLabel: {
	                  
	                     data : ['停机', '运行', '故障', '其它']
	                }
	            }
	        ],
	     
	        series : [
	            {
	                name: '设备状态 :',
	                type: 'bar',
	                data: ['停机', '运行', '故障', '其它']
	            },
	      
	        ]
	    };
	 return option;
}

/*function initOptionStatus(){
	
	option = {
		    color: ['#3398DB'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['停机', '运行', '故障', '其它'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'直接访问',
		            type:'bar',
		            barWidth: '60%',
		        }
		    ]
		};
	 return option;
}*/

function updateOptionStatus(option,status){
	/*option.series[0].data = [
	                {value:status.stopped, name:'停机'},
	                {value:status.powerOff, name:'上电停机'},
	                {value:status.running, name:'运行'},
	                {value:status.waiting, name:'等待'},
	                {value:status.canNotConnect, name:'无法连接'},
	                {value:status.unknown, name:'未知状态'}
	                ];*/
	option.series[0].data = [
        {value:status.stopped, name:'停机'},
        {value:status.running, name:'运行'},
        {value:status.canNotConnect, name:'故障'},
        {value:status.unknown, name:'其他'}
        ];
	//alert(option.series[0].data[4].value);
	return option;
}