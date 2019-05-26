package com.hzmsc.scada.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.view.OperationRateView;
import com.hzmsc.scada.service.view.OperationRateViewService;

@RestController
public class OperationRateRestController {
	private static Logger logger = LoggerFactory.getLogger(OperationRateRestController.class);
	@Autowired
	private OperationRateViewService operationRateViewService;

	@RequestMapping(value = "/operationRate", method = RequestMethod.GET)
	public List<OperationRateView> operationRateAll(){
		/*List<OperationRateView> operationRateViewList = operationRateViewService.findAll();
		for(OperationRateView orv : operationRateViewList){
			logger.info("orv:{}", orv);
		}*/
		return operationRateViewService.findAll();
	}
	
	@RequestMapping(value = "/operationRate/{begin}/{end}", method = RequestMethod.GET)
	public List<OperationRateView> operationRateBetweenTime(@PathVariable String begin, @PathVariable String end){
		/*System.out.println(begin);
		System.out.println(end);*/
		String begins = " 00:00:00";
		begin = begin + begins;
		end = end + begins;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//begin = begin + begins;
		try {
			Date date = sdf.parse(begin.toString());
			Date dates = sdf.parse(end.toString());
			
			begin = sdf.format(date);
			end = sdf.format(dates);
			//System.out.println("begin:"+begin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp beginTime = Timestamp.valueOf(begin);
		Timestamp endTime = Timestamp.valueOf(end);
		logger.debug("begin:{}-end:{}", beginTime, endTime);
		return operationRateViewService.findBewteenTime(beginTime, endTime);
	}
	
}
