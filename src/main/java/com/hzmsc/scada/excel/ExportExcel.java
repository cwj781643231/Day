package com.hzmsc.scada.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.hzmsc.scada.entity.DailySchedule;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {

	 public void Export(){
	        // 声明一个工作薄
	        HSSFWorkbook wb = new HSSFWorkbook();
	        //声明一个单子并命名
	        HSSFSheet sheet = wb.createSheet("学生表");
	        //给单子名称一个长度
	        sheet.setDefaultColumnWidth(10);
	        // 生成一个样式  
	        HSSFCellStyle style = wb.createCellStyle();
	        //创建第一行（也可以称为表头）
	        HSSFRow row = sheet.createRow(0);
	        //样式字体居中
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        //给表头第一行一次创建单元格
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("学生编号"); 
	        cell.setCellStyle(style);
	        cell = row.createCell(1);  
	                cell.setCellValue("学生姓名");  
	                cell.setCellStyle(style);  
	                cell = row.createCell(2);  
	                cell.setCellValue("学生性别");  
	                cell.setCellStyle(style); 
	        
	               //添加一些数据，这里先写死，大家可以换成自己的集合数据
	               List<DailySchedule> list = new ArrayList<DailySchedule>();
	               list.add( new DailySchedule(111, "设备一") );
	               list.add( new DailySchedule(112, "设备二") );
	              
	               //向单元格里填充数据
	               for (int i = 0; i < list.size(); i++) {
	                row = sheet.createRow(i + 1);
	                row.createCell(0).setCellValue(list.get(i).getDailyScheduleId());
	                row.createCell(1).setCellValue(list.get(i).getEquipmentName());
	            }
	        
	        try {
	            //默认导出到E盘下
	            FileOutputStream out = new FileOutputStream("E://生产详情表.xls");
	            wb.write(out);
	            out.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	  /**
	     * 将服务器生成的excel表下载到客户端
	     * @param path
	     * @param response
	     */
	    public static void download(String path, HttpServletResponse response) {
	        // path是指欲下载的文件的路径。
	        File file = null;
	        OutputStream toClient = null;
	        InputStream fis = null;
	        
	        try {
	            file = new File(path);
	            // 取得文件名。
	            String filename = file.getName();
	            // 以流的形式下载文件。
	            fis = new BufferedInputStream(new FileInputStream(path));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	                        
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
	            response.addHeader("Content-Length", "" + file.length());
	            toClient = new BufferedOutputStream(response.getOutputStream());
	            response.setContentType("application/vnd.ms-excel;charset=utf-8");
	            response.setHeader("Content-Disposition", "attachment;filename="  
	                    + new String(filename.getBytes(),"iso-8859-1")+".xlsx");  
	            toClient.write(buffer);
	            toClient.flush();       
	            
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            if(file != null) {
	                file.delete();
	            }           
	            try {
	                if(fis != null) {
	                    fis.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            try {
	                if(toClient != null) {
	                    toClient.close();
	                    toClient = null;
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        
	    }
}
