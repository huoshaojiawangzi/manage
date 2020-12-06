package com.lizc.manage.common.utils;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public abstract class CommonTemplateXlsxView extends AbstractXlsxView {

    public static void moveTail(XSSFSheet sheet, int s, int e, int to) {
        CellCopyPolicy p = new CellCopyPolicy();
        p.setCopyCellFormula(true);
        p.setCopyCellValue(true);
        p.setCopyCellStyle(true);
        p.setCopyRowHeight(true);
        p.setCopyMergedRegions(false);
        sheet.copyRows(s, e, to, p);
        for (int i = s; i <= e; i++) {
            sheet.removeRow(sheet.getRow(i));
        }
    }

    public static void copyRow(XSSFSheet sheet, int from, int to) {
        CellCopyPolicy p = new CellCopyPolicy();
        p.setCopyCellFormula(true);
        p.setCopyCellValue(true);
        p.setCopyCellStyle(true);
        p.setCopyRowHeight(true);
        p.setCopyMergedRegions(false);
        sheet.copyRows(from, from, to, p);
    }

    public void setFormula(XSSFSheet sheet, int r, int c, String value) {
        XSSFRow xr = sheet.getRow(r);
        XSSFCell xc = xr.getCell(c);
        xc.setCellFormula(value);
    }

    public void setValue(XSSFSheet sheet, int r, int c, String value) {
        XSSFRow xr = sheet.getRow(r);
        XSSFCell xc = xr.getCell(c);
        xc.setCellValue(value);
    }

    public void setValue(XSSFSheet sheet, int r, int c, Long value) {
        XSSFRow xr = sheet.getRow(r);
        XSSFCell xc = xr.getCell(c);
        xc.setCellValue(value);
    }

    public void setValue(XSSFSheet sheet, int r, int c, Integer value) {
        XSSFRow xr = sheet.getRow(r);
        XSSFCell xc = xr.getCell(c);
        xc.setCellValue(value);
    }

    public void setValue(XSSFSheet sheet, int r, int c, Double value) {
        XSSFRow xr = sheet.getRow(r);
        XSSFCell xc = xr.getCell(c);
        xc.setCellValue(value);
    }

}