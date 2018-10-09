package com.hq.storeMS.service.download.bs.handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.download.apiData.DownLoadDataForm;
import com.hq.storeMS.service.download.bs.ExcelUtils;
import com.hq.storeMS.service.download.data.DownLoadDataResult;
import com.hq.storeMS.service.download.data.ExcelData;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 导出店铺客户列表信息
 * 
 * @author kevin
 *
 */
public class StoreLeaguerDownLoadMgr {
	public static StoreLeaguerDownLoadMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreLeaguerDownLoadMgr.class);
	}

	// 从excel批量添加项目
	public DownLoadDataResult downloadLeaguerData(DownLoadDataForm formInfo) {
		DownLoadDataResult result = DownLoadDataResult.newInstance();
		try {
			LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance();
			queryForm.setStoreId(21L);
			PageResp<LeaguerDetail> pageInfo = LeaguerDetailMgr.getInstance().getLeaguerDetailPageInfo(queryForm);
			List<LeaguerDetail> list = pageInfo.getList();
			List<List<Object>> rows = new ArrayList<List<Object>>();
			for (LeaguerDetail LeaguerDetail : list) {
				List<Object> row = new ArrayList<Object>();
				row.add(LeaguerDetail.getName());
				row.add(LeaguerDetail.getPhone());
				row.add(LeaguerDetail.getSex());
				rows.add(row);
			}
			List<String> titles = new ArrayList<String>();
			titles.add("名称");
			titles.add("手机号");
			titles.add("性别");
			ExcelData excelData = ExcelData.newInstance();
			excelData.setRows(rows);
			
			excelData.setTitles(titles);

			XSSFWorkbook xssfWorkbook = ExcelUtils.getInstance().genExcel(excelData);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			xssfWorkbook.write(os);
			byte[] b = os.toByteArray();
			os.close();
			result.setFileName("leaguer.xlsx");
			result.setInputStream(new ByteArrayInputStream(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
