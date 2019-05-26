package com.hzmsc.scada.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.entity.Event;
import com.hzmsc.scada.entity.PageBean;
import com.hzmsc.scada.entity.QueryInfo;
import com.hzmsc.scada.service.EventService;

@RestController
public class EventRestController {
	
	private static Logger logger = LoggerFactory.getLogger(EventRestController.class);

	@Autowired
	private EventService eventService;
	
	//QueryInfo queryInfo = new QueryInfo(); 
	PageBean pageBean = new PageBean();
	
	/*
	 * 拥有  进入事件信息页面  和  条件查询两个功能  
	 * */
	@RequestMapping(value = "/listevents/{beginTime}/{endTime}", method = RequestMethod.GET)
	public List<Event> listevent(@PathVariable String beginTime, @PathVariable String endTime){
		
		//System.out.println("listevents+beginTime:"+beginTime);
		//System.out.println("listevents+endTime:"+endTime);
		//List<Event> listevent= eventService.findAll();
		//List<Event> listevent= eventService.pageQuery(beginTime, endTime, queryInfo.getStartIndex(), queryInfo.getPageSize());
		//List<Event> listevent= eventService.findBetweenTime(beginTime, endTime);
		
		//改时间段内总记录数
		int countByTime = eventService.countByTime(beginTime, endTime);
		pageBean.setTotalRecords(countByTime);
		
		/*
		 * startIndex 从第几个开始开始     pageSize 返回记录行的最大数目
		 * */
		List<Event> listevent = eventService.pageQuery(beginTime, endTime, pageBean.getCurrentPage()-1, pageBean.getPageSize());
		
		for (Iterator iterator = listevent.iterator(); iterator.hasNext();) {
			Event event = (Event) iterator.next();
			
			EquipmentStatus  ss= event.getEquipmentStatus();
			event.setStatus(ss.getEquipmentStatus());
			
		}
		
		return listevent;
	}
	
	/*
	 * 当进行页面跳转   无设备ID
	 * */
	@RequestMapping(value = "/events/{beginTime}/{endTime}/{currentPage}", method = RequestMethod.GET)
	public List<Event> equipmentBetweenTime(@PathVariable String beginTime, @PathVariable String endTime, @PathVariable int currentPage){
		//logger.debug("beginTime:{}--endTime:{}", new Timestamp(beginTime), new Timestamp(endTime));
		/*System.out.println("beginTime:"+beginTime);
		System.out.println("endTime:"+endTime);*/
		//当前页数  currentPage
		if(currentPage -1 != 0){
			
			int a = (currentPage-1)*20+1;
			//pageBean.setCurrentPage(a);
			
			List<Event> listevent = eventService.pageQuery(beginTime, endTime, 
					a, pageBean.getPageSize());	
			for (Iterator iterator = listevent.iterator(); iterator.hasNext();) {
				Event event = (Event) iterator.next();
				
				EquipmentStatus  ss= event.getEquipmentStatus();
				event.setStatus(ss.getEquipmentStatus());
				
			}
			return listevent;
		}else{
			
			 List<Event> listevent = eventService.pageQuery(beginTime, endTime,
					 currentPage-1, pageBean.getPageSize());
			 
			 for (Iterator iterator = listevent.iterator(); iterator.hasNext();) {
					Event event = (Event) iterator.next();
					
					EquipmentStatus  ss= event.getEquipmentStatus();
					event.setStatus(ss.getEquipmentStatus());
					
				}
			 
			return listevent;
			
		}
	}
	
	/*
	 * 当进行页面跳转   有设备ID
	 * */
	@RequestMapping(value = "/events/{beginTime}/{endTime}/{equipmentId}/{currentPage}", method = RequestMethod.GET)
	public List<Event> equipmentBetweenTimeId(@PathVariable String beginTime, @PathVariable String endTime,@PathVariable int equipmentId, @PathVariable int currentPage){
		//System.out.println("进来了: ");
		//当前页数  currentPage
		if(currentPage -1 != 0){
			
			int a = (currentPage-1)*10+1;
			//pageBean.setCurrentPage(a);
			/*System.out.println("equipmentId: "+equipmentId);
			System.out.println("beginTime: "+beginTime);
			System.out.println("endTime: "+endTime);
			System.out.println("a: "+a);
			System.out.println("pageBean.getPageSize(): "+pageBean.getPageSize());*/
			List<Event> listevent = eventService.pageQueryById(equipmentId, beginTime, endTime, 
					a, pageBean.getPageSize());	
			for (Iterator iterator = listevent.iterator(); iterator.hasNext();) {
				Event event = (Event) iterator.next();
				
				EquipmentStatus  ss= event.getEquipmentStatus();
				event.setStatus(ss.getEquipmentStatus());
				
			}
			return listevent;
		}else{
			
			 List<Event> listevent = eventService.pageQueryById(equipmentId, beginTime, endTime,
					 currentPage-1, pageBean.getPageSize());
			 
			 for (Iterator iterator = listevent.iterator(); iterator.hasNext();) {
					Event event = (Event) iterator.next();
					
					EquipmentStatus  ss= event.getEquipmentStatus();
					event.setStatus(ss.getEquipmentStatus());
					
				}
			 
			return listevent;
			
		}
	}
	
	
	@RequestMapping(value = "/countBytime/{beginTime}/{endTime}", method = RequestMethod.GET)
	public PageBean countBytime(@PathVariable String beginTime, @PathVariable String endTime){
		
		int countByTime = eventService.countByTime(beginTime, endTime);
		pageBean.setTotalRecords(countByTime);
		pageBean.getTotalPages();
		return pageBean;
	}
	
	@RequestMapping(value = "/countBytime/{beginTime}/{endTime}/{equipmentId}", method = RequestMethod.GET)
	public PageBean countBytimeId(@PathVariable String beginTime, @PathVariable String endTime, @PathVariable int equipmentId){
		
		int countByTime = eventService.countByTimeId(equipmentId, beginTime, endTime);
		pageBean.setTotalRecords(countByTime);
		pageBean.setTotalRecords(countByTime);
		pageBean.getTotalPages();
		return pageBean;
	}

	@RequestMapping(value = "/listevents/{beginTime}/{endTime}/{equipmentId}", method = RequestMethod.GET)
	public List<Event> listeventId(@PathVariable String beginTime, @PathVariable String endTime, @PathVariable int equipmentId){
		
/*		//改时间段内总记录数
		int countByTime = eventService.countByTime(beginTime, endTime);
		pageBean.setTotalRecords(countByTime);
*/		
		/*
		 * startIndex 从第几个开始开始     pageSize 返回记录行的最大数目
		 * */
		List<Event> listevent = eventService.pageQueryById(equipmentId,beginTime, endTime, pageBean.getCurrentPage()-1, pageBean.getPageSize());
		
		for (Iterator iterator = listevent.iterator(); iterator.hasNext();) {
			Event event = (Event) iterator.next();
			
			EquipmentStatus  ss= event.getEquipmentStatus();
			event.setStatus(ss.getEquipmentStatus());
			
		}
		
		return listevent;
	}
}
