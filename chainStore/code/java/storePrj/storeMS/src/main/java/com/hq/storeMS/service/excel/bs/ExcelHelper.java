package com.hq.storeMS.service.excel.bs;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hq.storeMS.service.excel.data.ExcelHeadMap;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.excel.ExcelMgr;
import com.zenmind.excel.data.ExcelHead;

/**
 * @ClassName: ExcelHelper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年3月30日 下午3:52:03
 * 
 */
public class ExcelHelper {

	public static ExcelHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ExcelHelper.class);
	}

	public boolean checkModule(MultipartFile file, String module) {
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);
		ExcelHead excelHead = ExcelMgr.getInstance().getHead(sheet);
		Map<String, Integer> nameIndexMap1 = excelHead.getNameIndexMap();
		Map<String, Integer> nameIndexMap2 = getMap(module);
		return compareMap(nameIndexMap1, nameIndexMap2);
	}

	private Map<String, Integer> getMap(String module) {
		Map<String, Integer> targetMap = new HashMap<String, Integer>();
		if (module.equalsIgnoreCase("leaguer")) {
			targetMap = ExcelHeadMap.leaguerHeadMap;
		} else if (module.equalsIgnoreCase("product")) {
			targetMap = ExcelHeadMap.productHeadMap;
		} else if (module.equalsIgnoreCase("goods")) {
			targetMap = ExcelHeadMap.goodsHeadMap;
		}
		return targetMap;
	}

	private boolean compareMap(Map<String, Integer> nameIndexMap1,Map<String, Integer> nameIndexMap2) {
		boolean equal = true;
		
		for (Entry<String, Integer> entry1 : nameIndexMap1.entrySet()) {
			Integer value1 = entry1.getValue();
			Integer value2 = nameIndexMap2.get(entry1
					.getKey());
			if (value1 != value2) {
				equal = false;
			}
		}
		return equal;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> getExcelData(MultipartFile file, Class clazz)
			throws Exception {
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);

		List<T> targetList = null;

		if (sheet != null) {
			targetList = ExcelMgr.getInstance().build(sheet, clazz);
		}
		return targetList;
	}

	public static Workbook getWorkBook(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			InputStream is = file.getInputStream();
			if (fileName.endsWith("xls")) {
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return workbook;
	}

}
