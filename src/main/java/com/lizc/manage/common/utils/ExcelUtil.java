package com.lizc.manage.common.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ExcelUtil {

    public static void forEachRow(String excl, int sheetAt, Consumer<Map<String, String>> func) throws IOException {
        String extString = excl.substring(excl.lastIndexOf(".")).toLowerCase();
        FileInputStream is = new FileInputStream(excl);
        forEachRow(is, extString, sheetAt, func);
        is.close();
    }

    /**
     * 获取excel中的键值对集合
     * @param execlFile excel表的流
     * @param extString excel表的后缀名
     * @param sheetAt sheet页index
     * @param initialCapacity 初始化返回的list的数量
     * @return
     */
    public static List<Map<String,String>> getExcelMap(InputStream execlFile, String extString, int sheetAt, int initialCapacity) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        wb = readExcel(execlFile, extString);
        List<Map<String,String>> excelList = new ArrayList<>(initialCapacity);
        if (wb != null) {
            sheet = wb.getSheetAt(sheetAt);
            int rownum = sheet.getPhysicalNumberOfRows();
            row = sheet.getRow(0);
            Row firstRow = sheet.getRow(0);
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        String cellName = (String) getCellFormatValue(firstRow.getCell(j));
                        Cell cell = row.getCell(j);
                        if(cell != null && "NUMERIC".equals(cell.getCellType().toString())) {
                            cell.setCellType(CellType.STRING);
                        }
                        cellData = (String) getCellFormatValue(cell);
                        map.put(cellName, cellData);
                    }
                } else {
                    break;
                }
                excelList.add(map);
            }
        }
        return excelList;
    }

    /**
     * 获取excel中的键值对集合，默认sheet页index为0
     * @param execlFile excel表的流
     * @param extString excel后缀名
     * @return
     */
    public static List<Map<String,String>> getExcelMap(InputStream execlFile, String extString) {
        return getExcelMap(execlFile, extString, 0, 10);
    }

    /**
     * 获取excel中的键值对集合
     * @param execlFile excel表的流
     * @param extString excel后缀名
     * @param sheetAt sheet页index
     * @return
     */
    public static List<Map<String,String>> getExcelMap(InputStream execlFile, String extString, int sheetAt) {
        return getExcelMap(execlFile, extString, sheetAt, 10);
    }

    public static void forEachRow(InputStream execlFile, String extString, int sheetAt,
                                  Consumer<Map<String, String>> func) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        wb = readExcel(execlFile, extString);
        if (wb != null) {
            sheet = wb.getSheetAt(sheetAt);
            int rownum = sheet.getPhysicalNumberOfRows();
            row = sheet.getRow(0);
            Row firstRow = sheet.getRow(0);
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        String cellName = (String) getCellFormatValue(firstRow.getCell(j));
                        Cell cell = row.getCell(j);
                        if(cell != null && "NUMERIC".equals(cell.getCellType().toString())) {
                            cell.setCellType(CellType.STRING);
                        }
                        cellData = (String) getCellFormatValue(cell);
                        map.put(cellName, cellData);
                    }
                } else {
                    break;
                }
                func.accept(map);
            }
        }
    }

    // 读取excel
    private static Workbook readExcel(InputStream is, String extString) {
        Workbook wb = null;
        try {
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = cell.getDateCellValue();
                    } else {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

}