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
	 * ����excel����
	 * @param header
	 *            ͷ��������
	 * @param colleges
	 *            excel������
	 * @param sheetPath
	 *            �������ļ�
	 * @return 
	 * 			  �Ƿ񵼳��ɹ�
	 * @throws ioException 
	 */
	public static boolean exportExcel(List<String> header, List<String[]> colleges,
			File sheetPath) throws IOException {
			// HSSFWorkbook wb = new HSSFWorkbook();
			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFSheet sheet1 = wb.createSheet("ѧԺ��Ϣ��");// ����������

			HSSFCellStyle cellstyle = wb.createCellStyle();// ��Ԫ����ʽ

			/*--------------------������ͷ����ʽ��-------------*/
			HSSFFont font = wb.createFont();

			font.setBoldweight((short) 20);

			font.setFontName("����");

			font.setFontHeightInPoints((short) 10);

			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellstyle.setFont(font);
			/****************** ����ͷ������ *************************/
			HSSFRow row = sheet1.createRow(0);// �����������е�һ��---------ÿһ�е���ͷ

			for (int i = 0; i < header.size(); i++) {

				HSSFCell cell1 = row.createCell(i);

				cell1.setCellStyle(cellstyle);// ���õ�Ԫ����ʽ

				cell1.setCellValue(header.get(i));

				sheet1.setColumnWidth(i, (int) 3000);

			}

			/****************** �����ݿ�õ������ݷŵ�xls�� *******************/
			for (int j = 0; j < colleges.size(); j++) {

				String[] s = (String[]) colleges.get(j);
				row = sheet1.createRow(j + 1);

				for (int k = 0; k < s.length; k++) {
					row.createCell(k).setCellValue(s[k]);
				}
			}
			/***************** �����ļ�·���������� ********************/

			FileOutputStream output = new FileOutputStream(sheetPath);
			output.flush();

			wb.write(output);

			output.close();
			return true;
		
	}
}
