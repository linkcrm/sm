package com.owen.bazi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Calendar;

/**
 * 完整排盘系统
 *
 * @author luozhuang 大师♂罗莊
 */
public class LuozhuangPaipanClass {

	LuozhuangshenshaHehun myLuozhuangshenshaHehun = new LuozhuangshenshaHehun();
	luozhuanglvhehun myluozhuanglvhehun = new luozhuanglvhehun();
	Luozhuangpaipandayun myLuozhuangpaipandayun = new Luozhuangpaipandayun();
	LuozhuangshengSha myLuozhuangshengSha = new LuozhuangshengSha();
	luozhuangpaipanshisheng myluozhuangpaipanshisheng = new luozhuangpaipanshisheng();
	Luozhuangdizhang myLuozhuangdizhang = new Luozhuangdizhang();

	/**
	 *
	 * @param man
	 *            生日 yyyy-MM-dd HH
	 * @return
	 * @throws ParseException
	 */
	public String paipan(String man, luozhuanglvhehun.sex isman) throws ParseException {

		Calendar mancal;

		try {
			mancal = myLuozhuangshenshaHehun.getCalendarfromString(man, "yyyy-MM-dd HH");
			// 原来的private 方法改了下
		} catch (ParseException ex) {
			return "输入不正确" + ex.getMessage();
		}

		return paipan(mancal, isman);

	}

	public String paipan(Calendar cal, luozhuanglvhehun.sex isman) throws ParseException {

		BaZi lunar = new BaZi(cal);
		Lunar lunaryue = new Lunar(cal.getTime());
		System.out.println("此人农历的日期【" + lunar.toString() + "】");
		/**
		 * 很多地方都是按照23：00-1：00为子时这是不对的。
		 * 子时24.00－2.00,丑时2.00－4.00,寅时4.00－6.00,卯时6.00－8.00,
		 * 辰时8.00－10.00,巳时10.00－12.00,午时12.00－14.00,未时14.00－16.00
		 * 申时16.00－18.00,酉时18.00－20.00,戌时20.00－22.00,亥时22.00－24.00
		 *
		 */
		int time = cal.get(Calendar.HOUR_OF_DAY) / 2;

		// 获取生肖
		System.out.println("此人的农历生肖【" + lunar.animalsYear() + "】");

		String GanZhi = lunar.getYearGanZhi(time);// 取八字
		String[] tempchar = GanZhi.split(",");
		// 我修改原来的，用,分割
		String ganziyear = lunaryue.getCyclicaYear();// 年柱
		String ganzimonth = lunaryue.getCyclicaMonth();// 月柱
		String ganziday = lunaryue.getCyclicaDay();// 日柱
		String ganzitime = tempchar[3];// 时柱

		System.out.println("此人八字【" + ganziyear + " " + ganzimonth + " " + ganziday + " " + ganzitime + "】");

		String[] arrayOfString = new String[9];

		arrayOfString[0] = "";
		arrayOfString[1] = ganziyear.substring(0, 1);// 年干
		arrayOfString[2] = ganziyear.substring(1, 2);// 年支
		arrayOfString[3] = ganzimonth.substring(0, 1);// 月干
		arrayOfString[4] = ganzimonth.substring(1, 2);// 月支
		arrayOfString[5] = ganziday.substring(0, 1);// 日干
		arrayOfString[6] = ganziday.substring(1, 2);// 日支
		arrayOfString[7] = ganzitime.substring(0, 1);// 时干
		arrayOfString[8] = ganzitime.substring(1, 2);// 时支
		System.out.println(myLuozhuangshengSha.shengsha(arrayOfString, isman));

		// 排食神生旺死绝
		// 用日干 日支分别查找位于表的对应值
		// 修改原文的类方法字段属性为public
		// 我的表格均是按照顺序比如
		// 十天干生旺死绝表 十神概念按顺序排列
		// 查找某一行就可以查到对应值
		String[] shengsibiao = { "甲", "丙", "戊", "庚", "壬", "乙", "丁", "己", "辛", "癸" };// 十天干生旺死绝表顺序
		// 十天干生旺死绝表 用日干查表

		int shengsibiaoshunxu = myLuozhuangpaipandayun.getyuezhuStart(shengsibiao, ganziday.substring(0, 1));
		// 十神表按顺序
		int shishengbiaoshunxu = myLuozhuangpaipandayun.getyuezhuStart(BaZi.Gan, ganziday.substring(0, 1));

		shengsibiaoshunxu++;
		shishengbiaoshunxu++;

		String[] DayunArray = myLuozhuangpaipandayun.Dayun(ganziyear, ganzimonth, isman);

		// 排此人四柱十神生旺死绝

		// 比 印 日元 劫 <- 这里直接用四柱干支计算
		// 乾造 庚 己 庚 辛
		// 辰 卯 午 巳
		// 藏干 乙戊癸 乙 己丁 庚丙戊 <- 这里直接用藏干计算
		// 才枭伤 才 印官 比杀枭 <- 这里直接用藏干计算
		// 地势 养 胎 沐浴 长生 <- 藏干不算生旺死绝
		// 纳音 白蜡金 城墙土 路旁土 白蜡金

		System.out.println("此人四柱干支十神");
		System.out.print(myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
				.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], ganziyear.substring(0, 1))]); // 十神表
																										// 用支查表
		System.out.print(" ");
		System.out.print(myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
				.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], ganzimonth.substring(0, 1))]); // 十神表
																										// 用支查表
		System.out.print(" ");
		System.out.print("日主"); // 日柱不计算！
		System.out.print(" ");
		System.out.print(myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
				.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], ganzitime.substring(0, 1))]); // 十神表
																										// 用支查表

		System.out.println("");

		System.out.println("此人年藏干");
		String[] zhanggan = LuozhuangcommonClass.dizhuang(ganziyear.substring(1, 2));
		for (int i = 0; i < zhanggan.length; i++) {

			if (zhanggan[i] == null) {
				continue;
			}
			System.out.print(zhanggan[i]);
			System.out.print(" ");
			System.out.print(myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
					.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])]); // 十神表
																							// 用支查表
			System.out.print(" ");

		}
		System.out.println("");
		zhanggan = LuozhuangcommonClass.dizhuang(ganzimonth.substring(1, 2));
		System.out.println("此人月藏干");

		for (int i = 0; i < zhanggan.length; i++) {
			if (zhanggan[i] == null) {
				continue;
			}
			System.out.print(zhanggan[i]);
			System.out.print(" ");
			System.out.print(myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
					.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])]); // 十神表
																							// 用支查表
			System.out.print(" ");

		}
		System.out.println("");
		System.out.println("此人日藏干");
		zhanggan = LuozhuangcommonClass.dizhuang(ganziday.substring(1, 2));
		for (int i = 0; i < zhanggan.length; i++) {
			if (zhanggan[i] == null) {
				continue;
			}
			System.out.print(zhanggan[i]);
			System.out.print(" ");
			System.out.print(myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
					.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])]); // 十神表
																							// 用支查表
			System.out.print(" ");

		}

		System.out.println("");
		System.out.println("此人时藏干");
		zhanggan = LuozhuangcommonClass.dizhuang(ganzitime.substring(1, 2));
		for (int i = 0; i < zhanggan.length; i++) {
			if (zhanggan[i] == null) {
				continue;
			}
			System.out.print(zhanggan[i]);
			System.out.print(" ");
			System.out.print(myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
					.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])]); // 十神表
																							// 用支查表
			System.out.print(" ");

		}

		String[] DayunArrayshengsi = new String[DayunArray.length];// 大运十天干生旺死绝表
		String[] DayunArrayshisheng = new String[DayunArray.length];// 大运十神表

		for (int i = 0; i < DayunArray.length; i++) {
			DayunArrayshengsi[i] = myluozhuangpaipanshisheng.shengwang[0][myLuozhuangpaipandayun.getyuezhuStart(
					myluozhuangpaipanshisheng.shengwang[shengsibiaoshunxu], DayunArray[i].substring(1, 2))]; // 十天干生旺死绝表
																												// 用干查表

			DayunArrayshisheng[i] = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun
					.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], DayunArray[i].substring(0, 1))]; // 十神表
																											// 用支查表

		}
		System.out.println("\n此人大运");
		myLuozhuangpaipandayun.pringst(DayunArray);

		System.out.println("此人起大运周岁：");
		System.out.println(myLuozhuangdizhang.dayunkaishi(cal, ganziyear.substring(0, 1), isman) + "岁");

		System.out.println("此人大运生旺死绝");
		myLuozhuangpaipandayun.pringst(DayunArrayshengsi);
		System.out.println("此人大运十神");
		myLuozhuangpaipandayun.pringst(DayunArrayshisheng);

		String[] DayunArrayshengsha = new String[DayunArray.length];

		for (int i = 0; i < DayunArray.length; i++) {

			DayunArrayshengsha[i] = myLuozhuangshengSha.shengshadayun(DayunArray[i], arrayOfString, isman);
		}

		System.out.println("此人大运神煞");
		myLuozhuangpaipandayun.pringst(DayunArrayshengsha);

		System.out.println("此人流年");

		int[] liunianarray = new int[80];
		int start = lunar.getnumberYear();
		start++;
		for (int i = 0; i < liunianarray.length; i++) {

			liunianarray[i] = start + i;
		}
		myLuozhuangpaipandayun.pringst(myLuozhuangshengSha.liunianshensha(liunianarray, arrayOfString, isman));

		System.out.println("此人婚姻神煞:");
		LuozhuangshenshaHehun myLuozhuangshenshaHehun = new LuozhuangshenshaHehun();
		System.out.println(myLuozhuangshenshaHehun.shensha(cal));

		System.out.println("此人纳音:");

		System.out.println(String.format("出生于：%s,纳音%s", lunar.getYear(), lunar.getNayin()

		));
		
		
		System.out.println("此人八字【" + ganziyear + " " + ganzimonth + " " + ganziday + " " + ganzitime + "】");

		myLuozhuangshengSha.shengsha(
				new String[] { arrayOfString[1], arrayOfString[3], arrayOfString[5], arrayOfString[7] },
				new String[] { arrayOfString[2], arrayOfString[4], arrayOfString[6], arrayOfString[8] }, isman);
		
		  //得到季节
        int jijie  = lunaryue.getJiJie();
        
        String[] jjDesc = new String[]{"春季","夏季","秋季","冬季"};
        System.out.println(String.format("\n季节: %s",jjDesc[jijie-1]));
        
		return null;

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws ParseException, IOException {
		LuozhuangPaipanClass my = new LuozhuangPaipanClass();

		System.out.println("请输入你的年月日时间类似 2013-3-14 20");
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
		String a = strin.readLine();

		while (a != null) {
			System.out.println("输入的是：" + a);
			String b = strin.readLine();
			if ("1".equals(b)) {
				my.paipan(a, luozhuanglvhehun.sex.woman);
			} else {
				my.paipan(a, luozhuanglvhehun.sex.man);
			}

			a = strin.readLine();
		}
		// 1992-11-28 8
		// 1996-2-1 19

		calHour();
	}

	public static void calHour() {
		for (int h = 23, i = 0; i < 24; h = (h + 1) % 24, i++) {

			Calendar mancal = null;
			LuozhuangshenshaHehun myLuozhuangshenshaHehun = new LuozhuangshenshaHehun();

			try {
				mancal = myLuozhuangshenshaHehun.getCalendarfromString("1996-03-01 " + h, "yyyy-MM-dd HH");
				// 原来的private 方法改了下
			} catch (ParseException ex) {
				// return "输入不正确" + ex.getMessage();
			}
			int time = mancal.get(Calendar.HOUR_OF_DAY) / 2;

			System.out.println(String.format("%d,%d", h, time));
		}
	}
}
