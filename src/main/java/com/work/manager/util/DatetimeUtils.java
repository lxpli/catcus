package com.work.manager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.druid.util.StringUtils;

/**
 * 时间处理工具
 * @author Jat
 *
 */
public class DatetimeUtils {
	
	private static final String DATE_FMT = "yyyy-MM-dd HH:mm:ss";
	
	public static String fmt(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT);
		return sdf.format(date);
	}
	
	public static String fmt(Date date,String fmt){
		if(StringUtils.isEmpty(fmt)){
			return fmt(date);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}
	
	public static String fmt(){
		return fmt(new Date());
	}
	
	
	public static String fmt(String dateStr,String oldfmt,String newfmt){
		if(StringUtils.isEmpty(dateStr) || "null".equals(dateStr)){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(oldfmt);
		try {
			return fmt(sdf.parse(dateStr),newfmt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String longToString(long dateLong){
		Date date = new Date(dateLong);
		return fmt(date);
	}
	public static String longToString(long dateLong,String fmt){
		Date date = new Date(dateLong);
		return fmt(date,fmt);
	}

	public static Date pre(String dateStr,String fmt){
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取本周一的日期
	 * @return
	 */
    public static Date getTimesWeekmorning() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return cal.getTime();  
    }  
  
    /**
     * 获取本周日的日期
     * @return
     */
    public static Date getTimesWeeknight() {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(getTimesWeekmorning());  
        cal.add(Calendar.DAY_OF_WEEK, 7);  
        return cal.getTime();  
    }  
  
    /**
     * 获取本月第一天的日期
     * @return
     */
    public static Date getTimesMonthmorning() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
        return cal.getTime();  
    }  
  
    /**
     * 获取本月最后一天的日期
     * @return
     */
    public static Date getTimesMonthnight() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
        cal.set(Calendar.HOUR_OF_DAY, 24);  
        return cal.getTime();  
    }  
    
    /**
     * 获取本年第一天的日期
     * @return
     */
    public static Date getTimesYearmoning() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), 0, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_YEAR));
        return cal.getTime();  
    }  
  
    /**
     * 获取本年最后一天的日期
     * @return
     */
    public static Date getTimesYearnight() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR),cal.getActualMaximum(Calendar.MONTH),0, 0, 0, 0);  
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return cal.getTime();  
    }  
    
    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean checkTime(String date){
    	return checkTime(date, System.currentTimeMillis());
    }
    public static boolean checkTime(String date,long sysTime){
    	Date d = pre(date,"yyyy-MM-dd");
    	//Date d1 = pre(longToString(System.currentTimeMillis(),"yyyy-MM-dd"), "yyyy-MM-dd");
        Date d1 = new Date(sysTime);
    	
        if(d1.getTime() - d.getTime() >= 1000*60*60*33.5){
        	//return belongCalendar(now, beginTime, endTime);
        	return false;
        }else{
        	return true;
        }
    }
	
	public static void main(String[] args) {
		//System.out.println("10:38:36,10:44:09,10:58:24,11:56:42,10:48:12,10:56:04".replaceAll(",","\r\n"));
		//System.out.println(compare("2017-09-17 08:38:05", "2017-09-17 08:38:04"));
		//System.out.println(checkTime("2018-02-09"));
		System.out.println(fmt(getTimesYearmoning()));
		System.out.println(fmt(getTimesYearnight()));
	}
	
	
	
}
