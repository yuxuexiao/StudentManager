package com.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

public class ExcelExportCollege {

	/**
	 * 导出excel方法
	 * @param header
	 *            头部的列名
	 * @param colleges
	 *            excel的内容
	 * @param sheetPath
	 *            导出的文件
	 * @return 
	 * 			  是否导出成功
	 * @throws ioException 
	 */
	public static boolean exportExcel(List<String> header, List<String[]> colleges,
			File sheetPath) throws IOException {
			// HSSFWorkbook wb = new HSSFWorkbook();
			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFSheet sheet1 = wb.createSheet("学院信息表");// 创建工作表

			HSSFCellStyle cellstyle = wb.createCellStyle();// 单元格样式

			/*--------------------设置列头得样式表-------------*/
			HSSFFont font = wb.createFont();

			font.setBoldweight((short) 20);

			font.setFontName("宋体");

			font.setFontHeightInPoints((short) 10);

			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellstyle.setFont(font);
			/****************** 设置头部列名 *************************/
			HSSFRow row = sheet1.createRow(0);// 创建工作表中的一行---------每一列的列头

			for (int i = 0; i < header.size(); i++) {

				HSSFCell cell1 = row.createCell(i);

				cell1.setCellStyle(cellstyle);// 设置单元格样式

				cell1.setCellValue(header.get(i));

				sheet1.setColumnWidth(i, (int) 3000);

			}

			/****************** 从数据库得到的数据放到xls中 *******************/
			for (int j = 0; j < colleges.size(); j++) {

				String[] s = (String[]) colleges.get(j);
				row = sheet1.createRow(j + 1);

				for (int k = 0; k < s.length; k++) {
					row.createCell(k).setCellValue(s[k]);
				}
			}
			/***************** 根据文件路径导出内容 ********************/

			FileOutputStream output = new FileOutputStream(sheetPath);
			output.flush();

			wb.write(output);

			output.close();
			return true;
		
	}
}
