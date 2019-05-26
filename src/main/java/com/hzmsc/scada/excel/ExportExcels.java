package com.hzmsc.scada.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzmsc.scada.controller.EquipmentRestController;



public class ExportExcels<T> {
	 /**
     * 利用了JAVA的反射，将javabean的集合以一定的格式输出到指定IO设备上
     * @param sheetName
     *             工作表标题
     * @param headerMap
     *             表格列属性及属性名
     * @param dataset
     *             需要显示的数据集合
     * @param out
     *             与输出设备相关的流对象，可以将Excel文档导出到本地或者网络中
     * @throws IOException 
     */
	
	private static Logger logger = LoggerFactory.getLogger(EquipmentRestController.class);

	
    public void exportExcel(String sheetName, Map<String, String> headerMap, Collection<T> dataset, OutputStream out) throws IOException{
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个工作表
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //设置表格默认列宽
        //有些资料在设置宽度的时候用的是short(20)，但是在实际使用的过程中，short(20)已不被推荐使用，所以直接使用int(20)
        sheet.setDefaultColumnWidth(20);
        //获取属性Map的keySet
        Set<String> keySet = headerMap.keySet();
        //获取属性的数组
        String[] fields = keySet.toArray(new String[0]);
        //对keySet进行遍历，获得属性对应的属性名称来构成标题行
        Iterator<String> key = keySet.iterator();
        //第一行
        HSSFRow row = sheet.createRow(0);
        for(int i = 0; key.hasNext(); i++){
            //产生单元格
            HSSFCell cell = row.createCell(i);
            //产生标题行
            HSSFRichTextString title = new HSSFRichTextString(headerMap.get(key.next()));
            //为单元格赋值
            cell.setCellValue(title);
        }
        /*
         * 遍历数据集合，产生数据行
         * 利用反射，通过属性数组来拼接getXXX的方法去获得属性的值
         */
        //属性数组的长度
        int size = fields.length;
        //属性的get方法的数组
        String[] methodNames = new String[size];
        for(int i = 0; i < size; i++){
        //拼凑属性的getter方法
            methodNames[i] = "get"
                            + fields[i].substring(0, 1).toUpperCase()
                            + fields[i].substring(1);
        }
        Iterator<T> ite = dataset.iterator();
        for(int index = 1; ite.hasNext(); index++){
            row = sheet.createRow(index);
            T t = (T)ite.next();
            for(int i = 0; i < size; i++){
                HSSFCell cell = row.createCell(i);
                try{
                    Class<?> cla = t.getClass();
                    Method getMethod = cla.getMethod(methodNames[i], new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    String textValue = null;
                    //如果属性的类型是String，则直接输出字符串
                    if(value instanceof String){
                        textValue = value.toString();
                    }
                    //如果属性的类型是Date，则需要格式转换
                    if(value instanceof Date){
                        Date date = (Date)value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                        textValue = sdf.format(date);
                    }
                    //如果是字符串，则利用正则表达式判断textValue是否全部由数字组成
                    if(textValue != null){
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if(matcher.matches()){
                            //是数字则当做double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        }else{
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);
                            cell.setCellValue(richString);
                        }
                    }
                }catch(Exception ex){
                	logger.debug("ex received:{}", ex);
                }
                
             /*   catch(Exception e){
                    e.printStackTrace();
                }*/
            }
        }
       //将workbook输出到输出设备中
        workbook.write(out);
       //关闭资源
        out.flush();
        workbook.close();
        out.close();
    }
}
