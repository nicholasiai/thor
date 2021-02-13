package com.likai.thor.web.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

@RequestMapping("thor")
@Controller
public class LoginContr {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";


    @PostConstruct
    public void init(){
        System.out.println("初始化执行方法");
    }

    @GetMapping("login")
    public void login(){
        System.out.println("访问成功");
    }


    @GetMapping("excelOperation")
    public void excelOperation(){
//        Map<String, String> dataMap=new HashMap<String, String>();
//        dataMap.put("BankName", "BankName");
//        dataMap.put("Addr", "Addr");
//        dataMap.put("Phone", "Phone");
//        List<Map> list=new ArrayList<Map>();
//        list.add(dataMap);
//        writeExcel(list, 3, "D:/writeExcel.xlsx");
        this.excelRead("D:/writeExcel.xlsx");
    }


    private void excelRead(String finalXlsxPath){
        //读

        try {
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            Sheet sheet = workBook.getSheetAt(0);
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 0; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    int cellType = cell.getCellType();
//                    CellType cellTypeEnum = cell.getCellTypeEnum();
                    System.out.println("读1"+cellType);
                    System.out.println("读2"+cell.getStringCellValue());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private   void writeExcel(List<Map> dataList, int cloumnCount, String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                String name = dataMap.get("BankName").toString();
                String address = dataMap.get("Addr").toString();
                String phone = dataMap.get("Phone").toString();
                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环
                    Cell first = row.createCell(0);
                    first.setCellValue(name);

                    Cell second = row.createCell(1);
                    second.setCellValue(address);

                    Cell third = row.createCell(2);
                    third.setCellValue(phone);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }


    /**
     * 判断Excel的版本,获取Workbook
     * @return
     * @throws IOException
     */
    private Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }



    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
//    public List readExcel(File file) {
//        try {
//            // 创建输入流，读取Excel
//            InputStream is = new FileInputStream(file.getAbsolutePath());
//            // jxl提供的Workbook类
//            Workbook wb = Workbook.getWorkbook(is);
//            // Excel的页签数量
//            int sheet_size = wb.getNumberOfSheets();
//            for (int index = 0; index < sheet_size; index++) {
//                List<List> outerList=new ArrayList<List>();
//                // 每个页签创建一个Sheet对象
//                Sheet sheet = wb.getSheet(index);
//                // sheet.getRows()返回该页的总行数
//                for (int i = 0; i < sheet.getRows(); i++) {
//                    List innerList=new ArrayList();
//                    // sheet.getColumns()返回该页的总列数
//                    for (int j = 0; j < sheet.getColumns(); j++) {
//                        String cellinfo = sheet.getCell(j, i).getContents();
//                        if(cellinfo.isEmpty()){
//                            continue;
//                        }
//                        innerList.add(cellinfo);
//                        System.out.print(cellinfo);
//                    }
//                    outerList.add(i, innerList);
//                    System.out.println();
//                }
//                return outerList;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (BiffException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
