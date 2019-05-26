package com.hzmsc.scada.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.dic.ScheduleType;
import com.hzmsc.scada.service.dic.ScheduleTypeService;

@RestController
public class ScheduleTypeRestController {

	@Autowired
	private ScheduleTypeService scheduleTypeService;

	@RequestMapping(value = "/scheduleType", method = RequestMethod.GET)
	public List<ScheduleType> scheduleTypeAll() {
		return scheduleTypeService.findAll();
	}

	@RequestMapping(value = "/scheduleType/{scheduleTypeCode}", method = RequestMethod.GET)
	public List<ScheduleType> scheduleTypeByScheduleTypeCode(@PathVariable String scheduleTypeCode) {
		try {
			scheduleTypeCode = new String(scheduleTypeCode.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) { // TODO Auto-generated catch
													// block
			e.printStackTrace();
		}
		//System.out.println("ScheduleTypeRestController:scheduleType.get");
		//System.out.println("scheduleTypeCode:" + scheduleTypeCode);

		return scheduleTypeService.findByScheduleTypeCode(scheduleTypeCode);
	}

	@RequestMapping(value = "/scheduleType", method = RequestMethod.PUT)
	public List<ScheduleType> scheduleTypeUpdate(@RequestBody ScheduleType scheduleType) {
		//System.out.println("ScheduleTypeRestController:/scheduleType.post"+scheduleType);
		scheduleTypeService.updateScheduleType(scheduleType);
		return scheduleTypeService.findAll();
	}

	@RequestMapping(value = "/scheduleType", method = RequestMethod.POST)
	public List<ScheduleType> scheduleTypeCreate(@RequestBody ScheduleType scheduleType) {
		// System.out.println("ScheduleTypeRestController:/scheduleType.post"+scheduleType);
		scheduleTypeService.createScheduleType(scheduleType);
		return scheduleTypeService.findAll();
	}

	@RequestMapping(value = "/scheduleTypeDelete", method = RequestMethod.POST)
	public List<ScheduleType> scheduleTypeDelete(@RequestBody ScheduleType scheduleType) {
		// System.out.println("ScheduleTypeRestController:/scheduleType.post"+scheduleType);
		scheduleTypeService.deleteScheduleType(scheduleType);
		return scheduleTypeService.findAll();
	}
}
