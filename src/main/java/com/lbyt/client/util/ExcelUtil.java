package com.lbyt.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * used for excel
 * 	
 * @author zhenglianfu
 *
 */
public class ExcelUtil {
	
	public static final int HEAD_ROW_INDEX = 0;
	
	public static final int HEAD_COLUMN_INDEX = 0;
	
	public static List<Sheet> parseExcel(File file) throws IOException{
		FileInputStream in = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(in);
		return bulidExcel(wb);
	}
	
	public static List<Sheet> parseExcel(InputStream in) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook(in);
		return bulidExcel(wb);
	}
	
	public static boolean isEmptySheet(HSSFSheet sheet){
		if (null == sheet) {
			return true;
		}
		if (sheet.getLastRowNum() == sheet.getFirstRowNum()) {
			return true;
		}  
		// 标题为空， 依旧为空
		HSSFRow row = sheet.getRow(HEAD_ROW_INDEX);
		if (null == row) {
			return true;
		}
		HSSFCell cell = row.getCell(HEAD_COLUMN_INDEX);
		if (null == cell || CommUtil.isEmpty(cell.getStringCellValue())) {
			return true;
		}
		return false;
	}
	
	public static List<Sheet> bulidExcel(HSSFWorkbook wb){
		List<Sheet> list = new ArrayList<Sheet>();
		int maxCount = 0,
			count = 0,
			lastRowNum = 0,
			i = 0;
		HSSFSheet sheet = null;
		Cell[] values = null;
		HSSFRow row = null;
		Sheet mySheet = null;
		if (wb != null) {
			maxCount = wb.getNumberOfSheets();
			while (count < maxCount) {
				sheet = wb.getSheetAt(count);
				count ++;
				if (isEmptySheet(sheet)) {
					continue;
				}
				mySheet = new Sheet();
				lastRowNum = sheet.getLastRowNum();
				int titleLength = sheet.getRow(0).getLastCellNum();
				for (i = 0; i < lastRowNum; i++) {
					row = sheet.getRow(i);
					int columnNum = row.getLastCellNum(), j = 0;
					values = new Cell[titleLength];
					columnNum = Math.min(columnNum, titleLength);
					for (; j < columnNum; j ++) {
						HSSFCell cell = row.getCell(j);
						if (null == cell) {
							continue;
						} 
						int cellType = cell.getCellType();
						switch(cellType){
						case HSSFCell.CELL_TYPE_STRING :
							values[j] = new Cell(cell.getStringCellValue(), Cell.CELL_STRING);
							break;
						case HSSFCell.CELL_TYPE_NUMERIC :
							values[j] = new Cell("" + (long)cell.getNumericCellValue(), Cell.CELL_NUMERIC);
							break;
						default :
							values[j] = new Cell(null, Cell.CELL_NULL);
							break;
						}
					}
					mySheet.addRow(values);
				}
				list.add(mySheet);
			} 
		} 
		return list; 
	}
	
	public static class Sheet{
		private List<Cell[]> list = new ArrayList<Cell[]>();
		
		private int lastRowNum = 0;
		
		public void addRow(Cell[] row){
			list.add(row);
			lastRowNum++;
		}

		public List<Cell[]> getRows(){
			return this.list;
		}
		
		public Cell[] getRow(int i){
			i = i < 0 ? (i + this.lastRowNum) : i;
			if (i < this.lastRowNum) {
				return this.list.get(i);
			} 
			return null;
		}
		
		public int getLastNumber() {
			return this.lastRowNum;
		}
	}
	
	public static class Cell{
		public static final int CELL_STRING = 1;
		
		public static final int CELL_NUMERIC = 2;
		
		public static final int CELL_NULL = -1;
		
		private String vlaue;
		
		private int type;
		
		public Cell(String value, int type) {
			this.vlaue = value;
			this.type = type;
		}
		
		public String getValue(){
			return this.vlaue;
		}
		
		public int getType(){
			return this.type;
		}
	}
	
}
