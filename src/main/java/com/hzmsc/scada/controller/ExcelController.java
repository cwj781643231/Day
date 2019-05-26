package com.hzmsc.scada.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hzmsc.scada.entity.DailySchedule;
import com.hzmsc.scada.excel.ExportExcel;

@Controller
public class ExcelController {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelController.class);

	@RequestMapping(value="/excelSample", method=RequestMethod.POST)
	public String helloExcel(Model model){
		String msg = "hello";
		model.addAttribute("msg", msg);
		
	    return "excelSample";  
	} 
	
	@RequestMapping(value="/excelProductionReport", method=RequestMethod.POST)
	public String excelProductionReport(Model model,@RequestBody List<DailySchedule> dailySchedule){
		String msg = "hello";
		model.addAttribute("msg", msg);
		//logger.info("dailySchedule{}", dailySchedule);
	    //model.addAttribute("msg", dailySchedule);
		//model.addAllAttributes(dailySchedule)
		
		System.out.println(model);
	    return "excelProductionReport";  
	} 
	
	 @RequestMapping("/exportExcel")
	    public String exportExcel(Model model,HttpServletRequest req, HttpServletResponse resp) {
	        //声明一个工作薄
	        HSSFWorkbook wb = new HSSFWorkbook();
	        //申明一个单子并命名
	        HSSFSheet sheet = wb.createSheet("个人收入表");
	        //给单元格一个默认的长度(原文字符个数)
	        sheet.setDefaultColumnWidth(10);
	        //生成样式
	        HSSFCellStyle style = wb.createCellStyle();
	        //样式字体居中
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        
	        //创建表头，也就是表头
	        HSSFRow header = sheet.createRow(0);
	        //给表头创建单元格
	        HSSFCell cell = header.createCell(0);
	        cell.setCellValue("序号");
	        cell.setCellStyle(style);
	        cell = header.createCell(1);
	        cell.setCellValue("设备ID");
	        cell.setCellStyle(style);
	        cell = header.createCell(2);
	        /*cell.setCellValue("开始日期");
	        cell.setCellStyle(style);
	        cell = header.createCell(3);
	        cell.setCellValue("结束日期");
	        cell.setCellStyle(style);
	        cell = header.createCell(4);
	        cell.setCellValue("产量(kg)");*/
	        cell.setCellStyle(style);
	        
	        //获取数据 TODO 应该从调用serivce得到数据，serivce再调用dao得到数据
	        List<DailySchedule> list = new ArrayList<DailySchedule>();
	        list.add(new DailySchedule(1, "林冲"));
	        list.add(new DailySchedule(2, "宋江"));
	        
	        //向单元格里填充数据
	        for(final DailySchedule person : list) {
	            HSSFRow row = sheet.createRow(person.getDailyScheduleId());
	            row.createCell(0).setCellValue(person.getEquipmentName());
	           // row.createCell(1).setCellValue(person.getStockIncome());
	           /* row.createCell(2).setCellValue(person.getFoundationIncome());
	            row.createCell(3).setCellValue(person.getSalaryIncome());
	            row.createCell(4).setCellValue(
	                    person.getFoundationIncome() + person.getStockIncome() + person.getSalaryIncome()
	                    );*/
	        }
	        
	        //写文件,将生成的表单写入服务器本地文件
	        FileOutputStream os = null;
	        String filePath = null;
	        try {
	            os = new FileOutputStream("E://个人收入表.xls");
	            filePath = os.toString();
	            System.out.println(filePath);
	            wb.write(os);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try{
	                if(os != null) {
	                    os.close();
	                    os = null;
	                }
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        }
	        ExportExcel.download(filePath, resp);   
	        //model.addAttribute("filePath",resp);
	        return null;
	    }
	 
}
