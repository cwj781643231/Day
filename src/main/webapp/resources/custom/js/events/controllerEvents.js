(function() {

	angular.module('eventsApp')
			.controller('eventsController', EventsController);

	EventsController.$inject = [ '$filter','$interval','employeeService','equipmentService','operaService','reportService','scheduleTypeService','scheduleService','eventsService'];

	function EventsController($filter,$interval,employeeService,equipmentService,operaService,reportService,scheduleTypeService,scheduleService,eventsService) {
		var vm = this;
		
		var today = new Date();
		vm.todays = new Date();
		vm.beginDate = new Date(vm.todays.getFullYear(), vm.todays.getMonth()-1, 1);
		vm.endDate = new Date(vm.todays.getFullYear(), vm.todays.getMonth(), vm.todays.getDate());

	/*	vm.beginTime = new Date(today.getFullYear(), today.getMonth(), 1);
		
		vm.endTime = new Date();*/
		vm.Init = Init;
		
		vm.events = [];
		
		//分页
	//	vm.Previous = Previous;
	//	vm.isActivePage = isActivePage;
	//	vm.selectPage = selectPage;
//		vm.Next = Next;
//		vm.setData = setData;
		vm.pageList = [];
		vm.pageSize = 5;
		vm.selPage = 1;
		vm.page = [];
		vm.pages = [];
		
		vm.equipments = [];
		vm.GetEquipments = GetEquipments;
		vm.ChangeFilter = ChangeFilter;
		vm.intervalTime = intervalTime;
		vm.myArr= [];
		
        vm.GetEventsBytime = GetEventsBytime;
		
		function Init() {
			var beginDayString = vm.myArr[0];
       	    var endDayString = vm.myArr[1];
       	    
			vm.GetEquipments();
			
			GetEvents ();
			
			GetCountBytime();
			
		}
		
		Init();
		
		/*
		 * 根据时间 查询该时间段内所有事件
		 * */
		function GetEvents (beginDay, endDay){
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			eventsService.GetEvents(beginDayString,endDayString).then(
					function(data) {
					if(data.length == 0){
						vm.events = 'N';
					}else{
						vm.events = data;
					}
						console.log(vm.events);
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
				);
		}
		
		function GetCountBytime(beginDay, endDay) {
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			eventsService.GetCountBytime(beginDayString,endDayString).then(
					function(data) {
					 
						vm.pageList = data;
						
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
			);
		}

		/*$interval(function() {
			var beginLongTime = vm.beginTime.getTime();
			var now = new Date();
			var endLongTime = now.getTime();
			
			eventsService.GetEvents(beginLongTime, endLongTime).then(
				function(data) {
					 vm.pages = Math.ceil(data.length / vm.pageSize); //分页数
			         vm.newPages = vm.pages > 5 ? 5 : vm.pages;
			         //设置表格数据源(分页)
			         vm.setData();
			         vm.events = data.slice(0, vm.pageSize);
					//vm.events = data;
					console.log(vm.events);
				}, 
				function(errResponse) {
					console.error('Error while fetching equipments');
				}
			);

		},2000);*/
		
		function GetEventsBytime(page,equipmentId){
			/*var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");*/
			
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
			eventsService.GetEventsById(beginDayString, endDayString,equipmentId, page).then(
					function(data) {
						if(data.length == 0){
							vm.events = 'N';
						}else{
							vm.events = data;
						}
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
				);
		    }else{
		    	eventsService.GetEventsBytime(beginDayString, endDayString, page).then(
					function(data) {
						if(data.length == 0){
							vm.events = 'N';
						}else{
							vm.events = data;
						}
						}, 
						function(errResponse) {
							console.error('Error while fetching equipments');
						}
					);
		    	
		    }
		}
		
	    function GetCountBytimeId(beginDay, endDay, equipmentId){
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			
			eventsService.GetCountBytimeId(beginDayString, endDayString, equipmentId).then(
					function(data) {
						vm.pageList = data;
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
				);
		}
		
	    function GetEventsBytimeId(beginDay, endDay, equipmentId){
			var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			
			eventsService.GetEventsBytimeId(beginDayString, endDayString, equipmentId).then(
					function(data) {
						if(data.length == 0){
							vm.events = 'N';
						}else{
							vm.events = data;
						}
					}, 
					function(errResponse) {
						console.error('Error while fetching equipments');
					}
				);
		}
	    
		/******       分页              ********/
	   
	 //查询出设备信息   下拉列表用
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
		
		
		function ChangeFilter(equipmentId, beginTime, endTime){
			var beginDayString = $filter('date')(beginTime, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endTime, "yyyy-MM-dd");
			//当信息改变后  生产详情统计列表
			if(equipmentId!=null || typeof(equipmentId)!=="undefined"){
				
				GetCountBytimeId(beginTime, endTime, equipmentId);
				eventsService.GetEventsBytimeId(beginDayString, endDayString, equipmentId).then(
						function(data) {
							if(data.length == 0){
								vm.events = 'N';
							}else{
								vm.events = data;
							}
						}, 
						function(errResponse) {
							console.error('Error while fetching equipments');
						}
					);
				
			}else{
				
				GetCountBytime(beginTime, endTime);
				eventsService.GetEvents(beginDayString, endDayString).then(function(data){
					if(data.length == 0){
						vm.events = 'N';
					}else{
						vm.events = data;
					}
							console.log(vm.events);
								
				}, function(err) {
					console.error('Error while fetching schedules');
				});
				
			}
			
		    intervalTime(beginDayString, endDayString);
		}
		  
	   function intervalTime(beginDay, endDay){
    	
    	 	var beginDayString = $filter('date')(beginDay, "yyyy-MM-dd");
			var endDayString =  $filter('date')(endDay, "yyyy-MM-dd");
			vm.myArr[0] = beginDayString;
			vm.myArr[1] = endDayString;
     }   
	   
	 /*
	  * 上一页
	  * */ 
	  function Previous(){
		  
		  
	  } 
	  
}})();