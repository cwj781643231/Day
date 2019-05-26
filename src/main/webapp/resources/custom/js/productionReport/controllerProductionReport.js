(function() {
	                                             
	angular.module("productionRApp").controller("productionsRController",
			ProductionsRController);          
	
                                           
	/*ProductionRController.$inject = ['$filter','reportService','employeeService'];
	
	function ProductionRController($filter, reportService, employeeService) {alert();
	*/	
	ProductionsRController.$inject = [ '$filter','$interval','employeeService','equipmentService','operaService','scheduleTypeService','scheduleService','reportService' ];

	function ProductionsRController($filter,$interval,employeeService,equipmentService,operaService,scheduleTypeService,scheduleService,reportService) {

	var vm = this;

		vm.today = new Date();
		vm.beginDate = new Date(vm.today.getFullYear(), vm.today.getMonth(), 1);
		vm.endDate = new Date(vm.today.getFullYear(), vm.today.getMonth(), vm.today.getDate());
        
		//console.log(vm.beginDate);
		//console.log(vm.endDate);

		vm.schedules = [];
		
		vm.page = [];
		vm.pages = [];
		
		vm.curschedules = [];
		vm.employees = [];
		vm.equipments = [];
		vm.operationRates = [];
		
		//设备总产量
		vm.sumschedules = [];
        //单个设备每天总产量
		vm.sumequipid = [];
		vm.schedule = [];
		
		vm.sumemployee = [];
		vm.sumemployeebyid = [];
		
		vm.allduction = [];
		
		vm.myArr= [];
		
		vm.alldaily = [];
		   
		vm.GetEmployees = GetEmployees;
		vm.GetEquipments = GetEquipments;

		vm.GetSchedulesBetweenDays = GetSchedulesBetweenDays;
		
		vm.intervalTime = intervalTime;
		
		vm.Export = Export;
		//总产量
		vm.GetAllProduction = GetAllProduction;
		
		//员工总产量
		vm.GetAllEmpDaily = GetAllEmpDaily;
		
		//设备总产量
		vm.GetDailyScheduleSumBetweenDays = GetDailyScheduleSumBetweenDays;
        //根据ID查询设备每天的总产量
		vm.EditEquipbyId = EditEquipbyId;
		
		//开机率
		vm.getOperationRate = getOperationRate;
		
		//员工
		vm.GetDailyEmployee = GetDailyEmployee;
		
		vm.GetDailyEmployeebyId = GetDailyEmployeebyId;
		
		vm.Init = Init; // 初始化vm.employees, vm.equipments,


		vm.CalculateProduction = CalculateProduction;
		vm.ChangeFilter = ChangeFilter;
		
		//分页
		//vm.Previous = Previous;
		/*vm.isActivePage = isActivePage;
		vm.selectPage = selectPage;
		vm.Next = Next;
		vm.setData = setData;*/
		vm.pageList = [];
		vm.pageSize = 5;
		vm.selPage = 1;
		vm.clickNum = clickNum;
		
		// call init function
		vm.Init();
              //查询出所有员工信息  下拉列表用
		function GetEmployees() {
			/*$http.get('/ii/employee').then(
					function(response) {
						vm.employees = response.data;
						// console.log(vm.employees);
					}, function(errResponse) {
						console.error('Error while fetching employees');
					});*/
			employeeService.GetEmployees().then(
					function(data) {
						vm.employees = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching employees');
					}
			);
		}

		//查询出设备信息   下拉列表用
		function GetEquipments() {
			/*reportService.GetEquipments().then(
					function(data) {
						vm.equipments = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);*/
			equipmentService.GetEquipments().then(
					function(data) {
						vm.equipments = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);
		}
		// vm.GetEquipments();


		
        //查询出排班信息
		function GetSchedules() {
			reportService.GetSchedules().then(
					function(data) {
						vm.schedules = data;			
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}
		// vm.GetSchedules();


		/*
		 * 2017-01-04 做自动查询 修改
		 * */
		//排班
		function GetSchedulesBetweenDays(beginDay, endDay) {
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			reportService.GetSchedulesBetweenDays(beginDayString, endDayString).then(
					function(data) {
					    if(data.length == 0){
					    vm.schedules = 'N';
					    }else{
						vm.schedules = data;
					    }
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}


		function UpdateScheduleWeight(schedule) {
			reportService.UpdateScheduleWeight(schedule).then(
					function(data) {
					console.log("schedule[" + schedule.scheduleId + "]已更新重量!");		
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);

		}
		
		
		function CalculateProduction(schedules){
			for(var i = 0; i < schedules.length; i++){
				
				UpdateScheduleWeight(schedules[i]);
			}
			
		}
		
		function GetCountBytimeId(beginDay, endDay, equipmentId){
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			
			reportService.GetCountBytimeId(beginDayString, endDayString, equipmentId).then(
					function(data) {
						vm.pageList = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
				);
		}
		
		function GetCountBytime(beginDay, endDay) {
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			reportService.GetCountBytime(beginDayString,endDayString).then(
					function(data) {
					 
						vm.pageList = data;
						
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);
		}
		
		function ChangeFilter(employeeId, equipmentId, beginTime, endTime){
			var beginDayString = $filter('date')(beginTime, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endTime, "yyyy-MM-dd");
			//当信息改变后  生产详情统计列表
			
			if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
				
				GetCountBytimeId(beginTime, endTime, equipmentId);
				reportService.GetSchedulesBetweenDaysById(beginDayString, endDayString, equipmentId).then(
						function(data) {
							 if(data.length == 0){
							    vm.schedules = 'N';
							 }else{
							    vm.schedules = data;
							 }
						}, 
						function(errResponse) {
							console.error('Error while fetching equipments');
						}
					);
			}else{
				
				GetCountBytime(beginTime, endTime);
				reportService.GetSchedulesBetweenDays(beginDayString, endDayString).then(
						function(data) {
							 if(data.length == 0){
								vm.schedules = 'N';
						     }else{
							    vm.schedules = data;
							 }
						}, 
						function(errResponse) {
							console.error('Error while fetching equipments');
						}
					);
			}
			
			//当信息改变后  设备生产统计列表
			reportService.GetDailyScheduleSumBetweenDays(beginDayString, endDayString).then(function(data){
				//alert("employeeId:"+employeeId);
				if(employeeId==null || typeof(employeeId)=="undefined"){
					//alert("equipmentId"+equipmentId);
					if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
					    vm.sumschedules = $filter('filter')(data,{'equipmentId':equipmentId});
				    }else{
					  vm.sumschedules = $filter('filter')(data);
				    }
				}else{
					if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
					   vm.sumschedules = $filter('filter')(data,{'employeeId':employeeId, 'equipmentId':equipmentId});
				    }
					else{
					   vm.sumschedules = $filter('filter')(data,{'employeeId':employeeId});
				    }
				}				
			}, function(err) {
				console.error('Error while fetching schedules');
			});
		
			//当信息改变后  员工生产统计列表
			reportService.GetDailyEmployee(beginDayString, endDayString).then(function(data){
				if(employeeId==null || typeof(employeeId)=="undefined"){
					if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
					    vm.sumemployee = $filter('filter')(data,{'equipmentId':equipmentId});
				    }else{
					  vm.sumemployee = $filter('filter')(data);
				    }
				}else{
					if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
					   vm.sumemployee = $filter('filter')(data,{'employeeId':employeeId, 'equipmentId':equipmentId});
				    }
					else{
					   vm.sumemployee = $filter('filter')(data,{'employeeId':employeeId});
				    }
				}				
			}, function(err) {
				console.error('Error while fetching schedules');
			});
			
			//所有的设备的总产量显示
			reportService.GetAllProduction(beginDayString, endDayString).then(
					function(data){
						if(employeeId!=null || typeof(employeeId)!=="undefined"){
							if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
							    vm.allduction = $filter('filter')(data,{'employeeId':employeeId, 'equipmentId':equipmentId});
							//    alert("现在的总产量时："+vm.allduction);
							}else{
								vm.allduction = $filter('filter')(data,{'employeeId':employeeId});
							}
						}else{
							if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
								vm.allduction = data;
							}else{
								vm.allduction = data;
							}
						}				
			}, function(err) {
				console.error('Error while fetching schedules');
			});
			
			reportService.GetAllEmpDaily(beginDayString, endDayString).then(
					function(data) {
						if(data==0){
							//alert("该时间段内没有数据信息");
							vm.alldaily = data;
						}else{
						    vm.alldaily = data;
					        //alert("我在这里和从产量"+vm.allduction);
						}
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);  
			
		    intervalTime(beginDayString, endDayString);
		}
		
        /*function ChangeFilter(employeeId, equipmentId, beginTime, endTime){
        	var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			reportServiceChangeFilter(employeeId, equipmentId, beginTime, endTime).then(
					function(data) {
						vm.schedules = data;			
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
			
		}*/
		
		function Init() {
			vm.GetEmployees();
			vm.GetEquipments();
			vm.GetSchedulesBetweenDays(vm.beginDate, vm.endDate);
			
			//设备总产量
			GetDailyScheduleSumBetweenDays(vm.beginDate, vm.endDate);
			
			GetDailyEmployee(vm.beginDate, vm.endDate);
			
			GetAllProduction(vm.beginDate, vm.endDate);
			
			GetAllEmpDaily(vm.beginDate, vm.endDate);
			
			intervalTime(vm.beginDate, vm.endDate);
			
			getOperationRate(vm.beginDate, vm.endDate);
			
			GetCountBytime(vm.beginDate, vm.endDate);
		}

		/*
		 * 2017-01-04 做自动查询 修改
		 * */
       //设备总产量
         function GetDailyScheduleSumBetweenDays(beginDay, endDay) {
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			reportService.GetDailyScheduleSumBetweenDays(beginDayString, endDayString).then(
					function(data) {
						vm.sumschedules = data;	
					//	alert("sum="+vm.sumschedules);
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}
         
         
         //设备产量详情
         function EditEquipbyId(beginDay, endDay, equipmentId) {
     		var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
     		var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
     		reportService.EditEquipbyId(beginDayString, endDayString,equipmentId).then(
					function(data) {
						vm.sumequipid = data;			
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
     	}
         
      /*
       * 开机率
       * */
        function getOperationRate(beginDay, endDay){
        	var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
     		var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
 			operaService.getOperationRates(beginDayString, endDayString).then(
 					function(data) {
 						vm.operationRates = data;
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
         
      /*--------------------- 员工  -----------------------*/
         
         /*
 		 * 2017-01-04 做自动查询 修改
 		 * */
         //员工总产量
         function GetDailyEmployee(beginDay, endDay) {
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			reportService.GetDailyEmployee(beginDayString, endDayString).then(
					function(data) {
						vm.sumemployee = data;		
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}
         
         //设备总产量
         function GetDailyEmployeebyId(beginDay, endDay, employeeId) {
     		var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
     		var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");  		
     		reportService.GetDailyEmployeebyId(beginDayString, endDayString,employeeId).then(
					function(data) {
						vm.sumemployeebyid = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
     	}
 
         /*
   		 * 2017-01-04 做自动查询 修改
   		 * */
         //所有设备总产量
         function GetAllProduction(beginDay, endDay) {
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			
			reportService.GetAllProduction(beginDayString, endDayString).then(
					function(data) {
						if(data==0){
							//alert("该时间段内没有数据信息");
							vm.allduction = data;
						}else{
							//var aaa = $filter('date')(new Date(), "yyyy-MM-dd HH:mm:ss S ");	
						    
							var myDate = new Date();  
							var year=myDate.getYear();
							var month=myDate.getMonth()+1;
							var day=myDate.getDate();
							var hours=myDate.getHours();
							var minutes=myDate.getMinutes();
							var seconds=myDate.getSeconds();
							var milliseconds = myDate.getMilliseconds(); 
							//7-3
							//alert(year+"/"+month+"/"+day+"   "+hours+":"+minutes+":"+seconds+":"+milliseconds);
							vm.allduction = data;
							console.log(vm.allduction);
					//	alert("我在这里和从产量"+vm.allduction);
						}
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}
         
         /*
    	  * 2017-01-04 做自动查询 修改
    	  * */
         //员工总产量
         function GetAllEmpDaily(beginDay, endDay) {
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			reportService.GetAllEmpDaily(beginDayString, endDayString).then(
					function(data) {
						if(data==0){
							//alert("该时间段内没有数据信息");
							vm.alldaily = data;
						}else{
						vm.alldaily = data;
					//	alert("我在这里和从产量"+vm.allduction);
						}
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
		}
       
         function Export(schedules) {
        	 reportService.Export(schedules).then(
 					function(data) {
 						
 					}, 
 					function(errResponse) {
 						console.error('Error while fetching schedules');
 					}
 			);

 		}
         
     function intervalTime(beginDay, endDay){
    	 
    	 	var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			vm.myArr[0] = beginDayString;
			vm.myArr[1] = endDayString;
     }   
     
     function clickNum(page,equipmentId){
    	 
    	 var beginDayString = vm.myArr[0];
		 var endDayString = vm.myArr[1];
		   //不能小于1大于最大
	     if (page < 1 || page > vm.pageList.totalPages) return;
	     if (page > 5) {
	           //因为只显示5个页数，大于2页开始分页转换
	           var newpageList = [];
	           for (var i = (page - 5) ; i < ((page + 5) > vm.pageList.totalPages ? vm.pageList.totalPages : (page + 5)) ; i++) {
	               newpageList.push(i + 1);
	           }
	           vm.pageList.pageBar = newpageList;
	       }
	     if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
	    	 
	    	 reportService.GetDailyBytimeById(beginDayString, endDayString,equipmentId, page).then(
	    		function(data) {
							vm.schedules = data;
						}, 
						function(errResponse) {
							console.error('Error while fetching equipments');
						}
			);
	     }else{
	    	 reportService.GetDailyBytime(beginDayString, endDayString, page).then(
	 	    		function(data) {
	 							vm.schedules = data;
	 						}, 
	 						function(errResponse) {
	 							console.error('Error while fetching equipments');
	 					}
	 			);
	     }
     }
         
    /*
     * 两秒查询一次
     * */     
       /*  $interval(function() {
        	 
        	 var beginDayString = vm.myArr[0];
        	 //alert(beginDayString);
        	 var endDayString = vm.myArr[1];
               
 			reportService.GetSchedulesBetweenDays(beginDayString, endDayString).then(
					function(data) {
				            vm.pages = Math.ceil(data.length / vm.pageSize); //分页数
				            vm.newPages = vm.pages > 5 ? 5 : vm.pages;
				            //设置表格数据源(分页)
				            vm.setData();
				            //分页要repeat的数组
				            vm.schedules = data.slice(0, vm.pageSize);
				 
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
 			
 			reportService.GetDailyScheduleSumBetweenDays(beginDayString, endDayString).then(
					function(data) {
						vm.sumschedules = data;	
					//	alert("sum="+vm.sumschedules);
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
 			
 			reportService.GetDailyEmployee(beginDayString, endDayString).then(
					function(data) {
						vm.sumemployee = data;		
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
 			
 			reportService.GetAllProduction(beginDayString, endDayString).then(
					function(data) {
						if(data==0){
							vm.allduction = data;
						}else{
						    vm.allduction = data;
						}
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
 			
 			reportService.GetAllEmpDaily(beginDayString, endDayString).then(
					function(data) {
						if(data==0){
							vm.alldaily = data;
						}else{
						    vm.alldaily = data;
						}
					}, 
					function(errResponse) {
						console.error('Error while fetching schedules');
					}
			);
 			
 		  operaService.getOperationRates(beginDayString, endDayString).then(
 					function(data) {
 						vm.operationRates = data;
 					}, 
 					function(errResponse) {
 						console.error('Error while fetching operationRates');
 					}
 			);
 			
 			
 			 * 重新计算产量
 			 * 
 			//CalculateProduction(vm.schedules);
 		},12000);	     
	*/
         
	
	
	/******       分页              ********/
/*	//设置当前选中页样式
    function isActivePage(page){
    	
    	vm.selPage == page;
    	
    	return vm.selPage;
    			
    }*/
 /*  function selectPage(page,pages){
	  
	   //不能小于1大于最大
       if (page < 1 || page > pages) return;
       //最多显示分页数5
       if (page > 2) {
           //因为只显示5个页数，大于2页开始分页转换
           var newpageList = [];
           for (var i = (page - 3) ; i < ((page + 2) > pages ? pages : (page + 2)) ; i++) {
               newpageList.push(i + 1);
           }
           vm.pageList = newpageList;
       }
       vm.selPage = page;
       vm.setData();
       isActivePage(page);
       console.log("选择的页：" + page);
    			
    }*/
/*   //上一页
   function Previous(){
	   
	   vm.selectPage(vm.selPage - 1);
   }
   //下一页
   function Next(){
	   
	   vm.selectPage(vm.selPage + 1);
   }*/
   //设置表格数据源(分页)
 /*  function setData(){
	 
     var beginDayString = vm.myArr[0];
  	 var endDayString = vm.myArr[1];
  	
		reportService.GetSchedulesBetweenDays(beginDayString, endDayString).then(
				function(data) {
					
					 var selPages = vm.selPage;
					 vm.schedules = data.slice((5 * (selPages - 1)), (selPages * 5));//通过当前页数筛选出表格当前显示数据
				}, 
				function(errResponse) {
					console.error('Error while fetching schedules');
				}
		);
   }*/
	}})();