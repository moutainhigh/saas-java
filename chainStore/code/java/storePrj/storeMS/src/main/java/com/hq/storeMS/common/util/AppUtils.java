package com.hq.storeMS.common.util;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.common.json.JsonUtil;

public class AppUtils {
    private static final String UnderLine = "_";
    private static final SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 用于深层复制对象
     *
     * @param source
     * @param clazz
     * @return
     */
    public static <T> T copyBeanBySerialize(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        String json = JsonUtil.getInstance().toJson(source);
        return JsonUtil.getInstance().fromJson(json, clazz);
    }

    public static String joinByUnderline(Object... args) {
        StringBuffer target = new StringBuffer();
        boolean flag = true;
        for (Object tmp : args) {
            if (isNull(tmp) || StringUtils.isBlank(String.valueOf(tmp))) {
                continue;
            }
            if (flag) {
                target.append(tmp);
                flag = false;
            } else {
                target.append(UnderLine).append(tmp);
            }
        }
        return target.toString();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 求两个日志相差的月数
     *
     * @param date1
     * @param date2
     * @return
     * @throws Exception
     */
    public static int diffTimeMonth(Date date1, Date date2) {
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String str1 = sdf.format(date1);
            String str2 = sdf.format(date2);
            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();
            bef.setTime(sdf.parse(str1));
            aft.setTime(sdf.parse(str2));
            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
            int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
            return Math.abs(month + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取指定日期的下一天时间  如：传入的date=2018-4-9 16:29:00   则输出的是：2018-4-10 00:00:00
     *
     * @param date
     * @return
     */
    public static long getNextDate(Date date) {
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str1 = sdf.format(date);
            Calendar nextDate = Calendar.getInstance();
            nextDate.setTime(sdf.parse(str1));
            nextDate.add(Calendar.DATE, 1);
            return nextDate.getTime().getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 时间戳转日期字符串
     *
     * @param time
     * @param sdf
     * @return
     */
    public static String timeStamp2Str(long time, DateFormat sdf) {
        try {
            return sdf.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 时间戳转日期字符串
     *
     * @param time
     * @return
     */
    public static String timeStamp2Str(long time) {
        try {
            return SDF_DAY.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 日期字符串转时间戳
     *
     * @param dateStr
     * @param sdf
     * @return
     */
    public static long dateStr2TimeStamp(String dateStr, DateFormat sdf) {
        try {
            return sdf.parse(dateStr).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str) || isTWPhoneLegal(str) || isMOPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^[1][1-9]\\d{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 台湾手机号码10位数，09开头+8位任意数
     */
    public static boolean isTWPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(09)\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 澳门手机号码7位数，66或者68开头+5位任意数
     */
    public static boolean isMOPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(68|66)\\d{5}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static void main(String[] args) throws Exception {
//		DateFormat df = new SimpleDateFormat("yyyy-MM");
//		Date now = df.parse("2017-11");
//		Date before = df.parse("2017-09");
//		System.out.println(diffTimeMonth(now, before));
//		System.out.println(isPhoneLegal("19914562478"));
//		System.out.println(joinByUnderline("19914562478",4,4));

    }

    /**
     * 字符串转换成ID集合
     */
    public static List<Long> getIdList(String idListStr) {
        String[] arr = idListStr.split(",");
        List<Long> idList = new ArrayList<Long>();
        for (String id : arr) {
            if (StringUtils.isNotBlank(id)) {
                idList.add(Long.valueOf(id));
            }
        }
        return idList;
    }

    /**
     * 深拷贝数据
     *
     * @param obj
     * @param <T> T extends Serializable
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj) {
        T clonedObj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clonedObj;
    }

}
