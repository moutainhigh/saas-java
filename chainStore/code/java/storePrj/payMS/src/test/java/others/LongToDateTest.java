package others;

import java.text.SimpleDateFormat;

import com.hq.payms.common.utils.AppUtils;

public class LongToDateTest {
     
     public static void main(String[] args) {
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 String str = AppUtils.timeStamp2Str(1534756872634L, sdf);
    	 System.out.println(str);
     }
}
