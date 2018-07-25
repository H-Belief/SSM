package com.belief.Utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReportExcel {

    public static HSSFWorkbook buildExcel(Map<String,String> map, Model model){
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("体质辨识选项结果表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("体质辨识选项结果表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));

        HSSFRow row2=sheet.createRow(1);
        HSSFCell cell1 = row2.createCell(0);
        cell1.setCellValue("\t体制周期："+"2018-01-01~2018-12-31\t"+"患者："+"滴答滴\t"+"性别："+"女\t"+"年龄："+"36");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,2));
        //在sheet里创建第二行
        HSSFRow row3=sheet.createRow(2);

        //创建单元格并设置单元格内容
        row3.createCell(0).setCellValue("序号");
        row3.createCell(1).setCellValue("测试题");
        row3.createCell(2).setCellValue("选项结果");



        int index = 2;

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        int i = 1;
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            //设置数据
            HSSFRow row=sheet.createRow(index++);
            row.createCell(0).setCellValue(i++);
            row.createCell(1).setCellValue(next.getKey());
            row.createCell(2).setCellValue(next.getValue());
        }

        HSSFRow row =sheet.createRow(index);
        System.out.println(index);
        row.createCell(0).setCellValue("测试时间：");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(index,index,0,1));
        row.createCell(2).setCellValue(model.getCreateTime());

        HSSFRow row5 =sheet.createRow(++index);
        row5.createCell(0).setCellValue("体质结果：");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(index,index,0,1));
        row5.createCell(2).setCellValue(model.getTizhi());


        HSSFRow row6 =sheet.createRow(++index);
        row6.createCell(0).setCellValue("操作医生：");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(index,index,0,1));
        row6.createCell(2).setCellValue(model.getOpertiDoc());

        return wb;
    }

    public static void response(HttpServletResponse response,HSSFWorkbook wb){
        //输出Excel文件
        OutputStream output= null;
        try {
            output = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        try {
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        String excelPath="d:/test.xls";//excel输出路径
        File excel=new File(excelPath);
        try {
            OutputStream out=new FileOutputStream(excel);

            Map<String,String> map = new HashMap();
            map.put("题目1","答案1");
            map.put("题目2","答案2");
            map.put("题目3","答案3");
            map.put("题目4","答案4");
            map.put("题目5","答案5");


            Model model = new Model();
            model.setCreateTime("2018-07-23");
            model.setTizhi("平和质");
            model.setOpertiDoc("沙尘暴");
            HSSFWorkbook wb = ReportExcel.buildExcel(map, model);

            wb.write(out);
        out.flush();
        out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class Model{
        private String tizhi;

        private String opertiDoc;

        private String createTime;

        public String getTizhi() {
            return tizhi;
        }

        public void setTizhi(String tizhi) {
            this.tizhi = tizhi;
        }

        public String getOpertiDoc() {
            return opertiDoc;
        }

        public void setOpertiDoc(String opertiDoc) {
            this.opertiDoc = opertiDoc;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
