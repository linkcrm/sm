package com.owen.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class DateUtil {
	private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static ThreadLocal<SimpleDateFormat> formatThreadLocal = new ThreadLocal<SimpleDateFormat>();

	/**
	 * 浠嶵hreadLocal閲岃幏鍙朣impleFormat瀵硅薄
	 * 
	 * @return
	 */
	private static SimpleDateFormat getSimpleFormatInstance() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat format = formatThreadLocal.get();
		if (format != null) {
			return format;
		}
		format = new SimpleDateFormat(pattern);
		formatThreadLocal.set(format);
		return format;
	}

	public static Date getDate(String fullTime) {
		try {
			return fullFormat.parse(fullTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 鑾疯窛绂讳粖澶╁墠鍚巒涓湀鐨勬棩鏈� yyyy-MM-dd
	 * 
	 * @param month
	 *            鏈堟暟 鍓� -month
	 * @return
	 */
	public static String getXMonthYYYYMMDD(int month) {
		SimpleDateFormat format = getSimpleFormatInstance();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		Date d = c.getTime();
		return format.format(d);
	}

	private static String sysChar;// 鏃ユ湡鍒嗛殧绗�
	static {
		sysChar = "-";
	}

	public static synchronized long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public static String getDayAdd(String date, int number) {
		String currDate = null;
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date1 = format2.parse(date);
			calendar.setTime(date1);
			calendar.add(Calendar.DATE, number);
			currDate = format2.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currDate;
	}

	public static String getDate() {
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getDateMMDD() {
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	public static String getDateYYMMDD() {
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		return sdf.format(new Date());
	}

	/**
	 * 鏃ユ湡杞崲鎴愬瓧绗︿覆
	 * 
	 * @param date
	 * @return str
	 */
	public static String dateToStr(java.util.Date date) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 瀛楃涓茶浆鎹㈡垚鏃ユ湡
	 * 
	 * @param str
	 * @return date
	 */
	public static java.util.Date strToDate(String str) throws Exception {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = format.parse(str);
		return date;
	}

	public static Timestamp getStatBeginDate(Timestamp workDate) {
		return getFirstDayInMonth(workDate);
	}

	public static Timestamp getStatEndDate(Timestamp workDate) {
		Timestamp endDate = getLastDayInMonth(workDate);
		if (endDate.after(getCurrTime())) {
			return getCurrTime();
		} else {
			return getLastDayInMonth(workDate);
		}
	}

	public static Timestamp getFirstDayInMonth(Timestamp currDate) {
		String yyyy = getYYYY(currDate);
		String mm = getMM(currDate);
		return getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 1);
	}

	public static Timestamp getLastDayInMonth(Timestamp currDate) {
		String yyyy = getYYYY(currDate);
		String mm = getMM(currDate);
		Timestamp t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 31);
		if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
			t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 30);
			if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
				t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 29);
				if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
					t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 28);
					if (Integer.parseInt(getMM(currDate)) != Integer.parseInt(getMM(t))) {
						t = getTimestamp(Integer.parseInt(yyyy), Integer.parseInt(mm), 27);
					}
				}
			}
		}
		return t;
	}

	/** **********************鍙栫壒瀹氭椂闂村嚱鏁�*********************** */
	public static Timestamp getSomeDate(int space) {// 鏍规嵁鏃堕棿闂撮殧鏉ュ彇寰楁煇涓椂闂�,渚嬪锛氬弬鏁颁负1鏃讹紝寰楀埌鏄庡ぉ鐨勬棩鏈燂紝鍙傛暟涓�30鏃讹紝寰楀埌涓�涓湀鍚庣殑鏃ユ湡锛屽弬鏁颁负0鏃讹紝寰楀埌浠婂ぉ鐨勬棩鏈燂紝鍙傛暟涓�-1鏃讹紝寰楀埌鏄ㄥぉ鐨勬棩鏈燂紝鍙傛暟涓�-30鏃讹紝寰楀埌涓�涓湀鍓嶇殑鏃ユ湡锛�
		return getSomeDate(getCurrTime(), space);
	}

	public static Timestamp getSomeDate(Timestamp t, int space) {// 姣斾笂闈㈢殑鏂规硶澶氫竴涓棩鏈熷弬鏁帮紝涓婇潰鏂规硶榛樿涓哄綋澶╋紝鎵�浠ユ病姝ゅ弬鏁�
		java.util.Date someDate = (java.util.Date) t;
		int sign = space < 0 ? -1 : 1;
		space = space < 0 ? -space : space;
		int s = space / 10;
		int y = space % 10;
		for (int i = 0; i < s; i++) {
			someDate = new java.util.Date(someDate.getTime() + 3600 * 24 * 1000 * 10 * sign);
		}
		someDate = new java.util.Date(someDate.getTime() + 3600 * 24 * 1000 * y * sign);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy", Locale.getDefault());
		int someDateYear = Integer.parseInt(formatter.format(someDate));// 姹備竴涓湀鍓嶆槸浠�涔堝勾
		formatter = new java.text.SimpleDateFormat("M", Locale.getDefault());
		int someDateMonth = Integer.parseInt(formatter.format(someDate));// 姹備竴涓湀鍓嶆槸鍑犳湀
		formatter = new java.text.SimpleDateFormat("dd", Locale.getDefault());
		int someDateDay = Integer.parseInt(formatter.format(someDate));// 姹備竴涓湀鍓嶆槸鍑犲彿
		return getTimestamp(someDateYear, someDateMonth, someDateDay);
	}

	public static String getYYYY(Timestamp t) {// 鍙栫壒瀹氭椂闂寸殑骞翠唤锛屾瘮濡�2003骞�
		if (isEmptyTime(t))
			return "";
		String yyyy = getFM("yyyy", t);
		yyyy = "0000" + yyyy;
		return yyyy.substring(yyyy.length() - 4);
	}

	public static String getYYYY(String date) {// 鍙栫壒瀹氭棩鏈熷瓧绗︿覆(濡�2003-12-11)鐨勫勾浠�
		StringTokenizer st = new StringTokenizer(date, "-/");
		return st.nextToken();
	}

	public static String getMM(Timestamp t) {// 鍙栫壒瀹氭椂闂寸殑鏈堜唤锛屾瘮濡�4鏈�
		if (isEmptyTime(t))
			return "";
		String mm = getFM("M", t);
		mm = "00" + mm;
		return mm.substring(mm.length() - 2);
	}

	public static String getMM(String date) {// 鍙栫壒瀹氭棩鏈熷瓧绗︿覆(濡�2003-12-11)鐨勬湀浠�
		StringTokenizer st = new StringTokenizer(date, "-/");
		st.nextToken();
		String mm = st.nextToken();
		if (mm.length() == 1)
			mm = "0" + mm;
		return mm;
	}

	public static String getDD(Timestamp t) {// 鍙栫壒瀹氭椂闂寸殑鏃ワ紝姣斿3鍙�
		if (isEmptyTime(t))
			return "";
		String dd = getFM("dd", t);
		dd = "00" + dd;
		return dd.substring(dd.length() - 2);
	}

	public static String getDD(String date) {// 鍙栫壒瀹氭棩鏈熷瓧绗︿覆(濡�2003-12-11)鐨勫彿鏁�
		StringTokenizer st = new StringTokenizer(date, "-/");
		st.nextToken();
		st.nextToken();
		String dd = st.nextToken();
		if (dd.length() == 1)
			dd = "0" + dd;
		return dd;
	}

	public static String getDD2(String date) {// 鍙栫壒瀹氭棩鏈熷瓧绗︿覆(濡�2003-12-11)鐨勫彿鏁�
		StringTokenizer st = new StringTokenizer(date, "-/");
		st.nextToken();
		st.nextToken();
		String dd = st.nextToken();
		if (dd.length() == 1)
			dd = "0" + dd;
		return dd.substring(0, 2);
	}

	public static String getYYYYMM(Timestamp t) {// 鍙栫壒瀹氭椂闂寸殑骞存湀浠斤紝姣斿2003-04
		if (isEmptyTime(t))
			return "";
		return getYYYY(t) + sysChar + getMM(t);
	}

	public static String getYYYYMMDD(Timestamp t) {// 鍙栫壒瀹氭椂闂寸殑骞存湀鏃ワ紝姣斿2003-04-03
		if (isEmptyTime(t))
			return "";
		return getYYYYMM(t) + sysChar + getDD(t);
	}

	public static String getHH(Timestamp t) {
		if (isEmptyTime(t))
			return "";
		String hh = getFM("H", t);
		hh = "00" + hh;
		return hh.substring(hh.length() - 2);
	}

	public static String getMI(Timestamp t) {
		if (isEmptyTime(t))
			return "";
		String mi = getFM("m", t);
		mi = "00" + mi;
		return mi.substring(mi.length() - 2);
	}

	public static String getSS(Timestamp t) {
		if (isEmptyTime(t))
			return "";
		String ss = getFM("s", t);
		ss = "00" + ss;
		return ss.substring(ss.length() - 2);
	}

	public static String getHHMISS(Timestamp t) {// 鍙栫壒瀹氭椂闂寸殑鏃跺垎绉掞紝姣斿12锛�45锛�30
		if (isEmptyTime(t))
			return "";
		return getHH(t) + ":" + getMI(t) + ":" + getSS(t);
	}

	public static String getYYYYMMDDHHMISS(Timestamp t) {// 鍙栫壒瀹氭椂闂寸殑骞存湀鏃ユ椂鍒嗙锛屾瘮濡�2003-04-03
		// 12:45:30
		if (isEmptyTime(t))
			return "";
		return getYYYYMMDD(t) + " " + getHHMISS(t);
	}

	public static Timestamp getTimestamp(int year, int month, int day, int hour, int minute, int second) {// 閫氳繃浠ｅ叆骞存湀鏃ユ椂鍒嗙鏋勯�燭imestamp鏃堕棿瀵硅薄
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.set(year, month - 1, day, hour, minute, second);
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp getTimestamp(int year, int month, int day) {// 閫氳繃浠ｅ叆骞存湀鏃ユ瀯閫燭imestamp鏃堕棿瀵硅薄
		return getTimestamp(year, month, day, 0, 0, 0);
	}

	public static Timestamp getTimestamp(String date) {
		return getTimestamp(Integer.parseInt(getYYYY(date)), Integer.parseInt(getMM(date)),
				Integer.parseInt(getDD(date)), 0, 0, 0);
	}

	public static String DateToString(Timestamp t) {
		if (t == null) {
			return "1970" + sysChar + "01" + sysChar + "01" + " " + "00" + ":" + "00" + ":" + "00";
		} else {
			return getYYYYMMDDHHMISS(t);
		}
	}

	/** **********************鍙栧綋鍓嶆椂闂村嚱鏁�*********************** */
	public static Timestamp getCurrTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getYYYY() {// 鍙栧綋鍓嶅勾浠斤紝姣斿2003骞�
		return getYYYY(getCurrTime());
	}

	public static String getMM() {// 鍙栧綋鍓嶆湀浠斤紝姣斿4鏈�
		return getMM(getCurrTime());
	}

	public static String getDD() {// 鍙栧綋鍓嶆棩锛屾瘮濡�03鍙�
		return getDD(getCurrTime());
	}

	public static String getYYYYMM() {// 鍙栧綋鍓嶅勾鏈堜唤锛屾瘮濡�2003-04
		return getYYYYMM(getCurrTime());
	}

	public static String getYYYYMMDD() {// 鍙栧綋鍓嶅勾鏈堟棩锛屾瘮濡�2003-04-03
		return getYYYYMMDD(getCurrTime());
	}

	public static String getHH() {
		return getHH(getCurrTime());
	}

	public static String getMI() {
		return getMI(getCurrTime());
	}

	public static String getSS() {
		return getSS(getCurrTime());
	}

	public static String getHHMISS() {// 鍙栧綋鍓嶆椂鍒嗙锛屾瘮濡�12锛�45锛�30
		return getHHMISS(getCurrTime());
	}

	public static String getYYYYMMDDHHMISS() {// 鍙栧綋鍓嶅勾鏈堟棩鏃跺垎绉掞紝姣斿2003-04-03
		// 12:45:30
		return getYYYYMMDDHHMISS(getCurrTime());
	}

	public static boolean isEmptyTime(Timestamp t) {
		return getTimestamp(1970, 01, 01, 00, 00, 00).getTime() == t.getTime();
	}

	/** **********************鍏跺畠鏂规硶*********************** */
	public static boolean isDate(String s) {// 妫�鏌ユ棩鏈熸牸寮忔槸鍚︽纭�
		StringTokenizer st = new StringTokenizer(s, "-");
		try {
			if (st.hasMoreElements()) {
				String strYear = (String) st.nextElement();
				if (strYear.length() == 4) {
					int year = Integer.parseInt(strYear);
					if (year >= 1970 && year <= 2470) {
						if (st.hasMoreElements()) {
							String strMonth = (String) st.nextElement();
							if (strMonth.length() == 1 || strMonth.length() == 2) {
								int month = Integer.parseInt(strMonth);
								if (month > 0 && month <= 12) {
									if (st.hasMoreElements()) {
										String strDay = (String) st.nextElement();
										if (strDay.length() == 1 || strDay.length() == 2) {
											int day = Integer.parseInt(strDay);
											if (day > 0 && day <= 31) {
												if (!st.hasMoreElements()) {
													return true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isTime(String s) {// 妫�鏌ユ椂闂存牸寮忔槸鍚︽纭�
		StringTokenizer st = new StringTokenizer(s, ":");
		try {
			if (st.hasMoreElements()) {
				String strHour = (String) st.nextElement();
				if (strHour.length() == 2) {
					int hour = Integer.parseInt(strHour);
					if (hour >= 0 && hour < 24) {
						if (st.hasMoreElements()) {
							String strMinute = (String) st.nextElement();
							if (strMinute.length() == 2) {
								int minute = Integer.parseInt(strMinute);
								if (minute >= 0 && minute < 60) {
									if (st.hasMoreElements()) {
										String strSecond = (String) st.nextElement();
										if (strSecond.length() == 2) {
											int second = Integer.parseInt(strSecond);
											if (second >= 0 && second < 60) {
												if (!st.hasMoreElements()) {
													return true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static Timestamp getLastMonthFirstDay() {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(Integer.parseInt(DateUtil.getYYYY()), Integer.parseInt(DateUtil.getMM()), 1, 0, 0, 0);
		calendar.add(java.util.Calendar.MONTH, -2);
		return new Timestamp(calendar.getTime().getTime());
	}

	public static Timestamp getCurrMonthFirstDay() {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(Integer.parseInt(DateUtil.getYYYY()), Integer.parseInt(DateUtil.getMM()), 1, 0, 0, 0);
		calendar.add(java.util.Calendar.MONTH, -1);
		return new Timestamp(calendar.getTime().getTime());
	}

	public static Timestamp getNextMonthFirstDay() {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(Integer.parseInt(DateUtil.getYYYY()), Integer.parseInt(DateUtil.getMM()), 1, 0, 0, 0);
		calendar.add(java.util.Calendar.MONTH, 0);
		return new Timestamp(calendar.getTime().getTime());
	}

	/** **********************绉佹湁鏂规硶*********************** */
	private static String getFM(String flag, java.util.Date date) {
		java.util.Date currentDate = date;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(flag, Locale.getDefault());
		String result = formatter.format(currentDate);
		return result;
	}
	/*
	 * 鑾峰彇缁欏畾鏃ユ湡瀛楃涓茬殑灏忔椂鏁�
	 */
	public static int getHourOfDay(String giveDateStr){
		Calendar c = Calendar.getInstance();
		try {
			Date date = DateUtil.strToDate(giveDateStr);
			c.setTime(date);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.get(Calendar.HOUR_OF_DAY);		
	}
	/*
	 * 鍒ゆ柇缁欏畾鏃堕棿瀛楃涓蹭笉瓒呰繃褰撳墠鏃堕棿涓�涓湀鍐�
	 */
	public static boolean withinMonth(String giveDateStr) {
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 1);
			if (DateUtil.strToDate(giveDateStr).compareTo(c.getTime()) > 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 鏃ユ湡鐩稿噺YYY
	 */
	public static long getDaySub2(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);

			if ((endDate.getTime() - beginDate.getTime()) % (24 * 3600 * 1000) > 30000) {// 瓒呰繃30绉掍互鍐�
				day = day + 1;
			}
			// System.out.println("鐩搁殧鐨勫ぉ鏁�="+day);
		} catch (ParseException e) {
			// TODO 鑷姩鐢熸垚 catch 鍧�
			e.printStackTrace();
		}
		return day;
	}

	/*
	 * 鏃ユ湡鐩稿噺
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
			// System.out.println("鐩搁殧鐨勫ぉ鏁�="+day);
		} catch (ParseException e) {
			// TODO 鑷姩鐢熸垚 catch 鍧�
			e.printStackTrace();
		}
		return day;
	}

	public static Date getExpireByHour(int hour) {
		Date d = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(d);
		rightNow.add(Calendar.HOUR, hour);
		return rightNow.getTime();
	}

	public static String getMonthAdd(String month, int number) {
		String currDate = null;
		DateFormat format2 = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date1 = format2.parse(month);
			calendar.setTime(date1);
			calendar.add(Calendar.MONTH, number);
			currDate = format2.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currDate;
	}

	public static String getTodayStartDatetime() {
		return simpleFormat.format(new Date()) + " 00:00:00";
	}

	public static String getTodayEndDatetime() {
		return simpleFormat.format(new Date()) + " 23:59:59";
	}

	public static String getYesterdayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return simpleFormat.format(calendar.getTime());
	}

	public static String getYesterdayStartDateTime() {
		return getYesterdayDate() + " 00:00:00";
	}

	public static String getYesterdayEndDateTime() {
		return getYesterdayDate() + " 23:59:59";
	}

	public static String getYesterdayDate(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(simpleFormat.parse(date));
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return simpleFormat.format(calendar.getTime());
	}

	public static String getYesterdayStartDateTime(String date) throws ParseException {
		return getYesterdayDate(date) + " 00:00:00";
	}

	public static String getYesterdayEndDateTime(String date) throws ParseException {
		return getYesterdayDate(date) + " 23:59:59";
	}

	// 鑾峰彇鏈懆鐨勭涓�澶╋紙浠庡懆涓�寮�濮嬶級
	public static String getCurrWeekFirstDate() {
		Calendar cal = Calendar.getInstance();
		int weekday = cal.get(Calendar.DAY_OF_WEEK);// 鑾峰緱褰撳墠鏃ユ湡鏄竴涓槦鏈熺殑绗嚑澶�
		if (weekday == 1) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return simpleFormat.format(cal.getTime());
	}

	// 鑾峰彇鏈懆鐨勬渶鍚庝竴澶╋紙浠庡懆涓�寮�濮嬶級
	public static String getCurrWeekLastDate() {
		return DateUtil.getDayAdd(getCurrWeekFirstDate(), 6);
	}

	public static String getCurrMonthFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return simpleFormat.format(calendar.getTime());
	}

	public static String getCurrMonthFirstDateTime() {
		return getCurrMonthFirstDate() + " 00:00:00";
	}

	public static String getCurrMonthLastDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return simpleFormat.format(calendar.getTime());
	}

	public static String getCurrMonthLastDateTime() {
		return getCurrMonthLastDate() + " 23:59:59";
	}

	public static String getLastMonthFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return simpleFormat.format(calendar.getTime());
	}

	public static String getLastMonthFirstDateTime() {
		return getLastMonthFirstDate() + " 00:00:00";
	}

	public static String getLastMonthLastDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return simpleFormat.format(calendar.getTime());
	}

	public static String getLastMonthLastDateTime() {
		return getLastMonthLastDate() + " 23:59:59";
	}

	public static boolean between(String curentTime, String startTime, String endTime) {
		if (startTime.compareTo(endTime) > 0) {
			endTime = String.format("%d%s", Integer.valueOf(endTime.substring(0, 2)) + 24, endTime.substring(2));
		}

		return (curentTime.compareTo(startTime) >= 0 && curentTime.compareTo(endTime) <= 0);
	}

	public static Date parseDate(String dateStr, String dateFormat) throws ParseException {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(dateFormat);
		return format.parse(dateStr);
	}

	public static String getChineseMMDD(String date) { // 杩斿洖鐗瑰畾鏃ユ湡瀛楃涓�(濡�2003-12-11)鐨勫嚑鏈堝嚑鍙�
		StringTokenizer st = new StringTokenizer(date, "-/");
		st.nextToken();
		String mm = st.nextToken();
		if (mm.startsWith("0")) {
			mm = mm.substring(1);
		}

		String dd = st.nextToken();
		if (dd.startsWith("0")) {
			dd = dd.substring(1);
		}

		return mm + "鏈�" + dd + "鏃�";
	}

	public static String getCurrYearFirst() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return simpleDateFormat.format(getYearFirst(currentYear));
	}

	public static String getCurrYearLast() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return simpleDateFormat.format(getYearLast(currentYear));
	}

	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	// public static void main(String[] args) throws ParseException {
	//// SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//// String t0Time = "9:00|22:00|0";
	//// String t0Time = "22:00|09:00|1";
	//// String[] tmp = t0Time.split("\\|");
	//// String startTime = time.format(time.parse(DateUtil.getYYYYMMDD() + " "
	// + tmp[0] + ":00"));
	//// String endTime = time.format(time.parse(DateUtil.getYYYYMMDD() + " " +
	// tmp[1] + ":00"));
	//// if("1".equals(tmp[2])) {
	//// endTime =
	// time.format(time.parse(DateUtil.getDayAdd(DateUtil.getYYYYMMDD(), 1) + "
	// " + tmp[1] + ":00"));
	//// }
	//// System.out.println(startTime);
	//// System.out.println(endTime);
	//// System.out.println(time.format(time.parse("2016-11-29 9:43:00")));
	//// System.out.println(between(DateUtil.getYYYYMMDDHHMISS(), startTime,
	// endTime));
	//// System.out.println("=============");
	//// System.out.println(getXMonthYYYYMMDD(-6));
	// System.out.println(getCurrYearFirst());
	// System.out.println(getCurrYearLast());
	// System.out.println(getDate("2016-11-29 09:43:00"));
	// String bgnTm = DateUtil.getYYYYMMDDHHMISS();
	// Date issureTime = DateUtil.getDate(bgnTm);
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(issureTime);
	// calendar.add(Calendar.HOUR, 24);
	// calendar.add(Calendar.SECOND, -1);
	// System.out.println(DateUtil.dateToStr(issureTime));
	// System.out.println(DateUtil.dateToStr(calendar.getTime()));
	// }

	/**
	 * 姣旇緝鐩搁殧澶╂暟
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(fDate);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

		aCalendar.setTime(oDate);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day2 - day1;

	}

	public static int compareDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1>year2){
			return -1;
		}
		if (year1 != year2)    {
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闂板勾
				{
					timeDistance += 366;
				} else    //涓嶆槸闂板勾
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else
		{
			System.out.println("鍒ゆ柇day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}
	
	//鑾峰彇鏃ユ湡涓烘槦鏈熷嚑
	public static String getWeekOfDate(Date dt) {
	    String[] weekDays = {"鏄熸湡鏃�", "鏄熸湡涓�", "鏄熸湡浜�", "鏄熸湡涓�", "鏄熸湡鍥�", "鏄熸湡浜�", "鏄熸湡鍏�"};
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    if (w < 0)
	       w = 0;
	    return weekDays[w];
	}

	public static Date add(Date date, int calendarField, int amount)
	{
		if(date == null)
		{
			throw new IllegalArgumentException("The date must not be null");
		} else
		{
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(calendarField, amount);
			return c.getTime();
		}
	}
	
	public static String getMinuteAdd(String dateTime, int minute) {
		String currDate = null;
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date1 = format2.parse(dateTime);
			calendar.setTime(date1);
			calendar.add(Calendar.MINUTE, minute);
			currDate = format2.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currDate;
	}
	
	public static String getHourAdd(String dateTime, int hour) {
		String currDate = null;
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date1 = format2.parse(dateTime);
			calendar.setTime(date1);
			calendar.add(Calendar.HOUR_OF_DAY, hour);
			currDate = format2.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currDate;
	}
	
	/*
	 * 鏃ユ湡鐩稿噺
	 */
	public static long getMinuteSub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / (60 * 1000);
			// System.out.println("鐩搁殧鐨勫ぉ鏁�="+day);
		} catch (ParseException e) {
			// TODO 鑷姩鐢熸垚 catch 鍧�
			e.printStackTrace();
		}
		return day;
	}
	
	public static int getSecondsToNextDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date nextMonthDate = calendar.getTime();
		return (int) ((nextMonthDate.getTime() - new Date().getTime())/1000);
	}
}
