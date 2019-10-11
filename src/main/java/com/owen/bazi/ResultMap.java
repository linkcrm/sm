package com.owen.bazi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.owen.bean.ClientRequest;

public class ResultMap {
	LuozhuangshenshaHehun myLuozhuangshenshaHehun = new LuozhuangshenshaHehun();
	luozhuanglvhehun myluozhuanglvhehun = new luozhuanglvhehun();
	Luozhuangpaipandayun myLuozhuangpaipandayun = new Luozhuangpaipandayun();
	LuozhuangshengSha myLuozhuangshengSha = new LuozhuangshengSha();
	luozhuangpaipanshisheng myluozhuangpaipanshisheng = new luozhuangpaipanshisheng();
	Luozhuangdizhang myLuozhuangdizhang = new Luozhuangdizhang();
	public static HashMap<String, String> tgWuxing = new HashMap<String, String>();
	public static HashMap<String, String> dzWuxing = new HashMap<String, String>();
	private String[] tg = new String[4];
	private String[] dz = new String[4];
	private String name = "";
	/** * 1：男;2：女 */
	private int sex = 1;
	private String solarTime = "";
	private String lunarTime = "";
	private String shengxiao = "";
	private String[] tg5x = new String[4];
	private String[] dz5x = new String[4];
	private int[] num5x = new int[] { 0, 0, 0, 0, 0 };
	private String useGod = "";
	private String loveGod = "";
	private String hateGod = "";
	/** 0:无财,1:只有正财,2:只有偏财,3:都有 * */
	private int caiyun = 3;
	/** 财库 */
	private int caiku = 1;
	/** 子女个数  **/
	private int[] childsNum = new int[] { 1, 1 };
	private String seperate = "";
	private ClientRequest  req = new ClientRequest();
	private int jijie = 1;
	private String nayin = "";
	private int tzmNum = 0;
	private String[] zhuxing = new String[4];
	/**注意可能为空*/
	private String[][] zhuanggan = new String[4][3];
	/**注意可能为空*/
	private String[][] fuxing  = new String[4][3];
	
	/**神煞列表,具体结果又出入 ，需再对下,to do**/
	private List<ArrayList<String>> shensha = null;
	
	public static HashMap<String, String> longDexing = new HashMap<String, String>();
	public static HashMap<String, String> wuFu = new HashMap<String, String>();
	public static HashMap<String, String> tianXixing = new HashMap<String, String>();
	public static HashMap<String, String>  yiMaxing = new HashMap<String, String>();
	public static HashMap<String, String> huaGaixing = new HashMap<String, String>();
	public static HashMap<String, String> hongLanxing = new HashMap<String, String>();
	public static HashMap<String, String> sxMap = new HashMap<String, String>();
	
	public static HashMap<String,HashMap<String,String>> TAISUIMap = new  HashMap<String,HashMap<String,String>>();
	public static HashMap<String,String> luckyNumberMap = new HashMap<String,String>();
	public static HashMap<String,String> luckyColorMap = new HashMap<String,String>();
	public static HashMap<String,String> luckyPositionMap = new HashMap<String,String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean isHtml = false;
	static {
		tgWuxing = new HashMap<String, String>();
		tgWuxing.put("甲", "木");
		tgWuxing.put("乙", "木");
		tgWuxing.put("丙", "火");
		tgWuxing.put("丁", "火");
		tgWuxing.put("戊", "土");
		tgWuxing.put("己", "土");
		tgWuxing.put("庚", "金");
		tgWuxing.put("辛", "金");
		tgWuxing.put("壬", "水");
		tgWuxing.put("癸", "水");
		dzWuxing = new HashMap<String, String>();
		dzWuxing.put("寅", "木");
		dzWuxing.put("卯", "木");
		dzWuxing.put("巳", "火");
		dzWuxing.put("午", "火");
		dzWuxing.put("辰", "土");
		dzWuxing.put("戌", "土");
		dzWuxing.put("丑", "土");
		dzWuxing.put("未", "土");
		dzWuxing.put("申", "金");
		dzWuxing.put("酉", "金");
		dzWuxing.put("亥", "水");
		dzWuxing.put("子", "水");
		
		longDexing.clear();
		longDexing.put("子","未");
		longDexing.put("丑", "申");
		longDexing.put("寅", "酉");
		longDexing.put("卯", "戌");
		longDexing.put("辰", "亥");
		longDexing.put("巳", "子");
		longDexing.put("午", "丑");
		longDexing.put("未", "寅");
		longDexing.put("申", "卯");
		longDexing.put("酉", "辰");
		longDexing.put("戌", "巳");
		longDexing.put("亥", "午");
		
//		String[] lunarshichen = new String[] {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
		wuFu.put("子","巳");
		wuFu.put("丑", "申");
		wuFu.put("寅", "亥");
		wuFu.put("卯", "寅");
		wuFu.put("辰", "巳");
		wuFu.put("巳", "申");
		wuFu.put("午", "亥");
		wuFu.put("未", "寅");
		wuFu.put("申", "巳");
		wuFu.put("酉", "申");
		wuFu.put("戌", "亥");
		wuFu.put("亥", "寅");
		
		tianXixing.put("子","酉");
		tianXixing.put("丑", "申");
		tianXixing.put("寅", "未");
		tianXixing.put("卯", "午");
		tianXixing.put("辰", "巳");
		tianXixing.put("巳", "辰");
		tianXixing.put("午", "卯");
		tianXixing.put("未", "寅");
		tianXixing.put("申", "丑");
		tianXixing.put("酉", "子");
		tianXixing.put("戌", "亥");
		tianXixing.put("亥", "戌");
		
		yiMaxing.put("子","寅");
		yiMaxing.put("丑", "亥");
		yiMaxing.put("寅", "申");
		yiMaxing.put("卯", "巳");
		yiMaxing.put("辰", "寅");
		yiMaxing.put("巳", "亥");
		yiMaxing.put("午", "申");
		yiMaxing.put("未", "巳");
		yiMaxing.put("申", "寅");
		yiMaxing.put("酉", "亥");
		yiMaxing.put("戌", "申");
		yiMaxing.put("亥", "巳");
		
		huaGaixing.put("子","辰");
		huaGaixing.put("丑", "丑");
		huaGaixing.put("寅", "戌");
		huaGaixing.put("卯", "未");
		huaGaixing.put("辰", "辰");
		huaGaixing.put("巳", "丑");
		huaGaixing.put("午", "戌");
		huaGaixing.put("未", "未");
		huaGaixing.put("申", "辰");
		huaGaixing.put("酉", "丑");
		huaGaixing.put("戌", "戌");
		huaGaixing.put("亥", "未");
		
		hongLanxing.put("子","卯");
		hongLanxing.put("丑", "寅");
		hongLanxing.put("寅", "丑");
		hongLanxing.put("卯", "子");
		hongLanxing.put("辰", "亥");
		hongLanxing.put("巳", "戌");
		hongLanxing.put("午", "酉");
		hongLanxing.put("未", "申");
		hongLanxing.put("申", "未");
		hongLanxing.put("酉", "午");
		hongLanxing.put("戌", "巳");
		hongLanxing.put("亥", "辰");
		
		final String[] Animals = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
		
		sxMap.put("子",Animals[0]);
		sxMap.put("丑", Animals[1]);
		sxMap.put("寅", Animals[2]);
		sxMap.put("卯", Animals[3]);
		sxMap.put("辰", Animals[4]);
		sxMap.put("巳", Animals[5]);
		sxMap.put("午", Animals[6]);
		sxMap.put("未", Animals[7]);
		sxMap.put("申", Animals[8]);
		sxMap.put("酉", Animals[9]);
		sxMap.put("戌", Animals[10]);
		sxMap.put("亥", Animals[11]);
		//final String[] Animals = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
		HashMap<String, String> TAISUI0 = new HashMap<String,String>();
		TAISUI0.put("鼠", "值");TAISUI0.put("马", "冲");TAISUI0.put("兔", "刑");TAISUI0.put("羊", "破");TAISUI0.put("鸡", "害");
		TAISUIMap.put("鼠", TAISUI0);
		HashMap<String, String> TAISUI1 = new HashMap<String,String>();
		TAISUI1.put("牛", "值");TAISUI1.put("养", "冲");TAISUI1.put("狗", "刑");TAISUI1.put("马", "破");TAISUI1.put("龙", "害");
		TAISUIMap.put("牛", TAISUI1);
		HashMap<String, String> TAISUI2 = new HashMap<String,String>();
		TAISUI2.put("虎", "值");TAISUI2.put("瘊", "冲");TAISUI2.put("蛇", "刑");
//		TAISUI2.put("", "破");
		TAISUI2.put("猪", "害");
		TAISUIMap.put("虎", TAISUI2);
		
		HashMap<String, String> TAISUI3 = new HashMap<String,String>();
		TAISUI3.put("兔", "值");TAISUI3.put("鸡", "冲");TAISUI3.put("鼠", "刑");TAISUI3.put("龙", "破");TAISUI3.put("马", "害");
		TAISUIMap.put("兔", TAISUI3);
		
		HashMap<String, String> TAISUI4 = new HashMap<String,String>();
		TAISUI4.put("龙", "值");TAISUI4.put("狗", "冲");//TAISUI4.put("兔", "刑");
		TAISUI4.put("兔", "破");TAISUI4.put("牛", "害");
		TAISUIMap.put("龙", TAISUI4);
		

		HashMap<String, String> TAISUI5 = new HashMap<String,String>();
		TAISUI5.put("蛇", "值");TAISUI5.put("猪", "冲");//TAISUI5.put("兔", "刑");
		TAISUI5.put("虎", "破");TAISUI5.put("猴", "害");
		TAISUIMap.put("蛇", TAISUI5);
		
		HashMap<String, String> TAISUI6 = new HashMap<String,String>();
		TAISUI6.put("马", "值");TAISUI6.put("鼠", "冲");//TAISUI6.put("兔", "刑");
		TAISUI6.put("牛", "破");TAISUI6.put("兔", "害");
		TAISUIMap.put("马", TAISUI6);
		
		
		HashMap<String, String> TAISUI7 = new HashMap<String,String>();
		TAISUI7.put("羊", "值");TAISUI7.put("牛", "冲");TAISUI7.put("狗", "刑");
//		TAISUI7.put("牛", "破");
		TAISUI7.put("鼠", "害");
		TAISUIMap.put("羊", TAISUI7);
		
		HashMap<String, String> TAISUI8 = new HashMap<String,String>();
		TAISUI8.put("猴", "值");TAISUI8.put("虎", "冲");TAISUI8.put("蛇", "刑");
//		TAISUI8.put("牛", "破");
		TAISUI8.put("猪", "害");
		TAISUIMap.put("猴", TAISUI8);
		
		HashMap<String, String> TAISUI9 = new HashMap<String,String>();
		TAISUI9.put("鸡", "值");TAISUI9.put("兔", "冲");//TAISUI9.put("鼠", "刑");
		TAISUI9.put("鼠", "破");
		TAISUI9.put("狗", "害");
		TAISUIMap.put("鸡", TAISUI9);
	
		HashMap<String, String> TAISUI10 = new HashMap<String,String>();
		TAISUI10.put("狗", "值");TAISUI10.put("龙", "冲");TAISUI10.put("牛", "刑");
		TAISUI10.put("羊", "破");
		TAISUI10.put("鸡", "害");
		TAISUIMap.put("狗", TAISUI10);
	
		HashMap<String, String> TAISUI11 = new HashMap<String,String>();
		TAISUI11.put("猪", "值");TAISUI11.put("蛇", "冲");//TAISUI11.put("牛", "刑");
		TAISUI11.put("虎", "破");
		TAISUI11.put("猴", "害");
		TAISUIMap.put("猪", TAISUI11);
		
		luckyNumberMap.put("木", "1,2");
		luckyNumberMap.put("火", "3,4");
		luckyNumberMap.put("土", "5,6");
		luckyNumberMap.put("金", "7,8");
		luckyNumberMap.put("水", "9,0");
		
		luckyColorMap.put("木", "绿色");
		luckyColorMap.put("火", "红色,粉色,紫色,橙色");
		luckyColorMap.put("土", "灰色,棕色");
		luckyColorMap.put("金", "银色,白色");
		luckyColorMap.put("水", "蓝色,黑色");
		
		luckyPositionMap.put("火", "南方");
		luckyPositionMap.put("木", "东方");
		luckyPositionMap.put("水", "北方");
		luckyPositionMap.put("金", "西方");
		luckyPositionMap.put("土", "中部");
	}
	
	public ResultMap(ClientRequest req,boolean isHtml) {
		this(req.getName(), req.getSex(), req.getYear()+"-"+req.getMonth()+"-"+req.getDay(), req.getTime(), req.getUsegod(), req.getLovegod(), req.getHategod());
		if(isHtml) {
			this.seperate = "<br>";
		}else {
			this.seperate = "\n";
		}
		this.req = req;
		this.tzmNum = tongzimingNum();
		this.isHtml = isHtml;
		
		logger.info(String.format("第%d类童子命",this.tzmNum));
		this.caiku = caikuNum();
	}
	
	ResultMap(String name, int sex, String solarBornTime, int hour, int usegod, int lovegod, int hategod) {
		this.name = name;
		this.sex = sex;

		String[] timeDesc = new String[] {"23:00-00:59", "01:00-02:59", "03:00-04:59", "05:00-06:59", "07:00-08:59", "09:00-10:59", "11:00-12:59", "13:00-14:59", "15:00-16:59", "17:00-18:59", "19:00-20:59", "21:00-22:59"};
		this.solarTime = solarBornTime + " "+timeDesc[hour] ;

		String[] w5 = new String[] { "金", "木", "水", "火", "土" };
		this.useGod = w5[usegod];
		this.loveGod = w5[lovegod];
		this.hateGod = w5[hategod];

		Calendar cal;
		try {
			cal = myLuozhuangshenshaHehun.getCalendarfromString(solarBornTime+" "+2*hour, "yyyy-MM-dd HH");
			// 原来的private 方法改了下
		} catch (ParseException ex) {
//            return "输入不正确" + ex.getMessage();
			return;
		}
		logger.info("开始占卜\n\n\n");
		logger.info("此人阳历日期【"+solarTime+"】");
		BaZi lunar = new BaZi(cal);
		
		String[] lunarshichen = new String[] {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
		this.lunarTime = lunar.toString() + lunarshichen[hour] +"时";
		logger.info("此人农历的日期【" + lunarTime + "】");
		Lunar lunaryue = new Lunar(cal.getTime());
		
		int time = cal.get(Calendar.HOUR_OF_DAY) / 2;
	        //获取生肖
	    logger.info("此人的农历生肖【" + lunar.animalsYear() + "】");
	    this.shengxiao = lunar.animalsYear();
	    
	    
	    String GanZhi = lunar.getYearGanZhi(time);//取八字
        String[] tempchar = GanZhi.split(",");
        //我修改原来的，用,分割
        String ganziyear = lunaryue.getCyclicaYear();//年柱
        String ganzimonth = lunaryue.getCyclicaMonth();//月柱
        String ganziday = lunaryue.getCyclicaDay();//日柱
        String ganzitime = tempchar[3];//时柱
 
        logger.info("此人八字【" + ganziyear + " " + ganzimonth + " " + ganziday + " " + ganzitime + "】");
        
        String[] arrayOfString = new String[9];
        
        arrayOfString[0] = "";
        arrayOfString[1] = ganziyear.substring(0, 1);//年干
        arrayOfString[2] = ganziyear.substring(1, 2);//年支
        arrayOfString[3] = ganzimonth.substring(0, 1);//月干
        arrayOfString[4] = ganzimonth.substring(1, 2);//月支
        arrayOfString[5] = ganziday.substring(0, 1);//日干
        arrayOfString[6] = ganziday.substring(1, 2);//日支
        arrayOfString[7] = ganzitime.substring(0, 1);//时干
        arrayOfString[8] = ganzitime.substring(1, 2);//时支
        
        this.tg = new String[] {ganziyear.substring(0, 1),ganzimonth.substring(0, 1),ganziday.substring(0, 1),ganzitime.substring(0, 1)};
        this.dz = new String[] {ganziyear.substring(1, 2),ganzimonth.substring(1, 2),ganziday.substring(1, 2),ganzitime.substring(1, 2)};
        
        logger.info("年柱  月柱  日柱 时柱");
        logger.info("{} {} {} {}", tg[0],tg[1],tg[2],tg[3]);
        logger.info("{} {} {} {}\n", dz[0],dz[1],dz[2],dz[3]);
        
        num5x = new int[] { 0, 0, 0, 0, 0 };
		for (int i = 0; i < 4; i++) {
			tg5x[i] = tgWuxing.get(tg[i]);
			dz5x[i] = dzWuxing.get(dz[i]);

			if ("金".equals(tg5x[i])) {
				num5x[0]++;
			} else if ("木".equals(tg5x[i])) {
				num5x[1]++;
			} else if ("水".equals(tg5x[i])) {
				num5x[2]++;
			} else if ("火".equals(tg5x[i])) {
				num5x[3]++;
			} else {
				num5x[4]++;
			}

			if ("金".equals(dz5x[i])) {
				num5x[0]++;
			} else if ("木".equals(dz5x[i])) {
				num5x[1]++;
			} else if ("水".equals(dz5x[i])) {
				num5x[2]++;
			} else if ("火".equals(dz5x[i])) {
				num5x[3]++;
			} else {
				num5x[4]++;
			}
		}
		
		logger.info("五行");
		logger.info("天干五行:{} {} {} {}",tg5x[0],tg5x[1],tg5x[2],tg5x[3]);
		logger.info("地支五行:{} {} {} {}",dz5x[0],dz5x[1],dz5x[2],dz5x[3]);
		
		this.nayin = lunar.getNayin();
		
		logger.info("年纳音:"+this.nayin+"\n");
		logger.info("神煞:");
  ///      logger.info(myLuozhuangshengSha.shengsha(arrayOfString, sex==1?luozhuanglvhehun.sex.man:luozhuanglvhehun.sex.woman));
		shensha = myLuozhuangshengSha.shengsha(this.tg, this.dz,  sex==1?luozhuanglvhehun.sex.man:luozhuanglvhehun.sex.woman);
        
        
        String[] shengsibiao = {"甲", "丙", "戊", "庚", "壬", "乙", "丁", "己", "辛", "癸"};//十天干生旺死绝表顺序
        //十天干生旺死绝表 用日干查表
 
   //     int shengsibiaoshunxu = myLuozhuangpaipandayun.getyuezhuStart(shengsibiao, ganziday.substring(0, 1));
        //十神表按顺序
        int shishengbiaoshunxu = myLuozhuangpaipandayun.getyuezhuStart(BaZi.Gan, ganziday.substring(0, 1));
 
   //     shengsibiaoshunxu++;
        shishengbiaoshunxu++;
        
        String zxNianZhu = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], ganziyear.substring(0, 1))];
        String zxYuezhu = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], ganzimonth.substring(0, 1))];
        String zxRizhu = "日主";
        String zxShiZhu = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], ganzitime.substring(0, 1))];
        logger.info("\n");
        logger.info("此人四柱干支十神");
//        System.out.print("主星: ");
//        System.out.print(zxNianZhu); //十神表 用支查表
//        System.out.print(" ");
//        System.out.print(zxYuezhu); //十神表 用支查表
//        System.out.print(" ");
//        System.out.print("日主"); //日柱不计算！
//        System.out.print(" ");
//        System.out.print(zxShiZhu); //十神表 用支查表
        
        logger.info("主星: {} {} 日主  {}\n",zxNianZhu,zxYuezhu,zxShiZhu);
        
        zhuxing = new String[] {zxNianZhu,zxYuezhu,zxRizhu,zxShiZhu};
        
        
        zhuanggan = new String[4][3];
        fuxing  = new String[4][3];
        
        logger.info("此人年藏干");
        String[] zhanggan = LuozhuangcommonClass.dizhuang(ganziyear.substring(1, 2));
        for (int i = 0; i < zhanggan.length; i++) {
        	zhuanggan[0][i] = zhanggan[i];
        	fuxing[0][i] = "";
            if (zhanggan[i] == null || "".equals(zhanggan[i])) {
            	zhanggan[i] = "";
                continue;
            }
            String fx = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])];
            fuxing[0][i] = fx;
        }
        logger.info("{} {} {}",new Object[] {zhanggan[0],zhanggan[1],zhanggan[2]});
        logger.info("{} {} {}\n",new Object[] {fuxing[0][0],fuxing[0][1],fuxing[0][2]});
        
        zhanggan = LuozhuangcommonClass.dizhuang(ganzimonth.substring(1, 2));
        logger.info("此人月藏干");
 
        for (int i = 0; i < zhanggan.length; i++) {
        	zhuanggan[1][i] = zhanggan[i];
        	fuxing[1][i] = "";
            if (zhanggan[i] == null || "".equals(zhanggan[i]) ){
            	zhanggan[i] = "";
                continue;
            }
            String fx = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])];
            fuxing[1][i] = fx;
          
//            logger.info("%s");
//            System.out.print(zhanggan[i]);
//           System.out.print(" ");
//            System.out.print(fx); //十神表 用支查表
//             System.out.print(" ");
        }
        logger.info("{} {} {}",new Object[] {zhanggan[0],zhanggan[1],zhanggan[2]});
        logger.info("{} {} {}\n",new Object[] {fuxing[1][0],fuxing[1][1],fuxing[1][2]});
        logger.info("此人日藏干");
        zhanggan = LuozhuangcommonClass.dizhuang(ganziday.substring(1, 2));
        for (int i = 0; i < zhanggan.length; i++) {
        	zhuanggan[2][i] = zhanggan[i];
        	fuxing[2][i] = "";
        	 if (zhanggan[i] == null || "".equals(zhanggan[i]) ){
             	zhanggan[i] = "";
                 continue;
             }
            String fx = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])];
            fuxing[2][i] = fx;
         
//            System.out.print(zhanggan[i]);
//            System.out.print(" ");
//            System.out.print(fx); //十神表 用支查表
//             System.out.print(" ");
 
        }
        logger.info("{} {} {}",new Object[] {zhanggan[0],zhanggan[1],zhanggan[2]});
        logger.info("{} {} {}\n",new Object[] {fuxing[2][0],fuxing[2][1],fuxing[2][2]});
        
        logger.info("此人时藏干");
        zhanggan = LuozhuangcommonClass.dizhuang(ganzitime.substring(1, 2));
        for (int i = 0; i < zhanggan.length; i++) {
        	zhuanggan[3][i] = zhanggan[i];
        	fuxing[3][i] = "";
        	 if (zhanggan[i] == null || "".equals(zhanggan[i]) ){
             	zhanggan[i] = "";
                 continue;
             }
            String fx = myluozhuangpaipanshisheng.shisheng[shishengbiaoshunxu][myLuozhuangpaipandayun.getyuezhuStart(myluozhuangpaipanshisheng.shisheng[0], zhanggan[i])];
           fuxing[3][i] = fx;
           
//           System.out.print(zhanggan[i]);
//            System.out.print(" ");
//            System.out.print(fx); //十神表 用支查表
//             System.out.print(" ");
        }
        
        logger.info("{} {} {}",new Object[] {zhanggan[0],zhanggan[1],zhanggan[2]});
        logger.info("{} {} {}\n",new Object[] {fuxing[3][0],fuxing[3][1],fuxing[3][2]});
        
        this.childsNum = this.childNum();
        
        logger.info(String.format("男%d个,女%d个 ",childsNum[0],childsNum[1]));
        
        this.caiyun = caiyunMethod();
        String[] caiyunDesc = new String[] {"无财","正财","偏财","正财偏财都有"};
        logger.info(caiyunDesc[this.caiyun]);
        
        //得到季节
        this.jijie  = lunaryue.getJiJie();
        
        String[] jjDesc = new String[]{"春季","夏季","秋季","冬季"};
        logger.info(String.format("季节: %s",jjDesc[this.jijie-1]));
	}
	
	/**
	 *  0:无财,1:只有正财,2:只有偏财,3:都有 * *
	 * 
	 * @return
	 */
	public int caiyunMethod() {
		boolean hasZhengCai = false;
		boolean hasPianCai = false;
		
		
		for(int i=0;i<4 && !hasZhengCai ;i++) {
			if("正财".equals(this.zhuxing[i])){
				hasZhengCai = true;
				break;
			}
			for(int j=0;j<3;j++) {
				if("正财".equals(this.fuxing[i][j])){
					hasZhengCai = true;
					break;
				}
			}
		}
		
		for(int i=0;i<4 && !hasPianCai;i++) {
			if("偏财".equals(this.zhuxing[i])){
				hasPianCai = true;
				break;
			}
			for(int j=0;j<3;j++) {
				if("偏财".equals(this.fuxing[i][j])){
					hasPianCai = true;
					break;
				}
			}
		}
		
		
		if( hasZhengCai && hasPianCai) {
			return 3;
		}
		else if( hasPianCai && !hasZhengCai) {
			return 2;
		}
		else if( hasZhengCai && !hasPianCai) {
			return 1;
		}
		else {
			return 0;
		}
	}
	/**
	 * 
	 * 女命十神中，伤官为儿子，食神为女儿。 有几个伤官，几个食神，便有几个儿子几个女儿。
	* 男命十神中，七杀为儿子，正官为女儿。有几个七杀，几个正官，便有几个儿子几个女儿
	 * 
	 * @return res[0] 是男孩个数, res[1]是女孩个数;
	 */
	public int[] childNum() {
		int[] res = new int[2];
		res[0] = 0;res[1] = 0;
		if( this.sex == 1) {//男命
			for(int i=0;i<4;i++) {
				if("七杀".equals(this.zhuxing[i])){
					res[0]++;
				}
				if("正官".equals(this.zhuxing[i])) {
					res[1]++;
				}
				for(int j=0;j<3;j++) {
					if("七杀".equals(this.fuxing[i][j])){
						res[0]++;
					}
					if("正官".equals(this.fuxing[i][j])) {
						res[1]++;
					}
				}
			}
		}
		else {//女命
			for(int i=0;i<4;i++) {
				if("伤官".equals(this.zhuxing[i])){
					res[0]++;
				}
				if("食神".equals(this.zhuxing[i])) {
					res[1]++;
				}
				for(int j=0;j<3;j++) {
					if("伤官".equals(this.fuxing[i][j])){
						res[0]++;
					}
					if("食神".equals(this.fuxing[i][j])) {
						res[1]++;
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * 算法：
地支见：辰戌丑未 为有财库，见几个，便有几个财库
	 * @return
	 */
	public int caikuNum() {
		int res = 0;
		
		if("辰戌丑未".contains(dz[0]) ) {
			res++;
		}
		if("辰戌丑未".contains(dz[1])) {
			res++;
		}
		if("辰戌丑未".contains(dz[2])) {
			res++;
		}
		if("辰戌丑未".contains(dz[3])) {
			res++;
		}
		
		return res;
	}
	/**
	 * 童子命算法：
1.	生在冬季或夏季，日支或时支见 卯、未、辰 （此为三点）
2.	生在春季或夏季，日支或时支见 寅、子
3.	年柱纳音为金或木，日支或时支见 午、卯
4.	年柱纳音为水或火，日支或时支见 酉、戌
5.	年柱纳音为土，日支或时支见 辰、巳
	 * @return 符合条件的个数
	 */
	public int tongzimingNum() {
		int res = 0;
		
		if( ( this.jijie == 2 || this.jijie == 4)  ){
			
			if(dz[2].equals("卯")) {
				res++;
			}
			if(dz[2].equals("未")) {
				res++;
			}
			if(dz[2].equals("辰")) {
				res++;
			}

			if(dz[3].equals("卯")) {
				res++;
			}
			if(dz[3].equals("未")) {
				res++;
			}
			if(dz[3].equals("辰")) {
				res++;
			}
			
		}
		
		if((this.jijie ==1 || this.jijie == 3 )  ) {
			
			if(dz[2].equals("寅")) {
				res++;
			}
			if(dz[2].equals("子") ) {
				res++;
			}
			
			if(dz[3].equals("寅")) {
				res++;
			}
			if(dz[3].equals("子") ) {
				res++;
			}
			
		}
		
		if( (nayin.charAt(2) == '金' || nayin.charAt(2) == '木') ){
			
			if(dz[2].equals("午")) {
				res++;
			}
			
			if( dz[2].equals("卯")) {
				res++;
			}
			
			if(dz[3].equals("午")) {
				res++;
			}
			
			if( dz[3].equals("卯")) {
				res++;
			}
			
		}
		
		if( (nayin.charAt(2) == '水' || nayin.charAt(2) == '火')  ){
			
			if( dz[2].equals("酉") ) {
				res++;
			}
			
			if(dz[2].equals("戌")  ) {
				res++;
			}
			
			if(dz[3].equals("酉")) {
				res++;
			}
			
			if(dz[3].equals("戌")) {
				res++;
			}
			
		}
		
		if( nayin.charAt(2) == '土' ){
			
			if(dz[2].equals("辰") ) {
				res++;
			}
			if(dz[2].equals("巳")) {
				res++;
			}
			if(dz[3].equals("辰")) {
				res++;
			}
			if(dz[3].equals("巳")){
				res++;
			}
		}
		
		return res;
	}
	
	
	
	public Map<String,Object> analysis(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("bazi", baziText());
		map.put("basic", getBasicPointAnalysis());
		map.put("kaiyun", luckySuggestion());
		map.put("mingge", mgAnalysis());
		map.put("sizhu", szAnalysis());
		map.put("shensha", shenshaAnalysis());
		map.put("goodevil", goodEvilAnalysis());
		map.put("jixiong", shengxiaoJixiong());
		map.put("taisui", fantaisui());
		map.put("tzm", tzmText());
		map.put("business", businessAnalysis());
		
		return map;
	}
	
	
	public String baziText() {
		StringBuffer sb = new StringBuffer();
		sb.append(getPersonText());
		sb.append(this.isHtml?getBaziTextByHtml():getBaziText());
		return sb.toString();
	}

	public String getPersonText() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%s %s ", sex == 1 ? "男" : "女", name, solarTime, lunarTime,shengxiao));
		sb.append(this.seperate);sb.append(this.seperate);
		sb.append(String.format("阳历:%s生", solarTime));
		sb.append(this.seperate);sb.append(this.seperate);
		sb.append(String.format("阴历:%s生    属【%s】", lunarTime,shengxiao));
		sb.append(this.seperate);sb.append(this.seperate);
		return sb.toString();
	}

	/**
	 * 八字排盘
	 * 
	 * @return
	 */
	public String getBaziText() {
		String s1 = String.format("%-11s %-11s %-11s %-11s", "年柱","月柱","日柱","时柱");
		String s2 = String.format("%s %s %s %s", 
				"【"+tg[0]+"】（"+tg5x[0]+"）", "【"+tg[1]+"】（"+tg5x[1]+"）", "【"+tg[2]+"】（"+tg5x[2]+"）","【"+tg[3]+"】（"+tg5x[3]+"）");
		String s3 = String.format("%s %s %s %s", 
				"【"+dz[0]+"】（"+dz5x[0]+"）", "【"+dz[1]+"】（"+dz5x[1]+"）","【"+dz[2]+"】（"+dz5x[2]+"）", "【"+dz[3]+"】（"+dz5x[3]+"）");

		StringBuffer sb = new StringBuffer(s1);
		sb.append(this.seperate);
		sb.append(s2);
		sb.append(this.seperate);
		sb.append(s3);
		sb.append(this.seperate);

		return sb.toString();
	}
	/**
	 * 八字排盘
	 * 
	 * @return
	 */
	public String getBaziTextByHtml() {
		String s1 = "年柱		月柱		 日柱		时柱";
		String s2 = String.format("【%s】（%s）	【%s】（%s）	【%s】（%s）	【%s】（%s）", tg[0], tg5x[0], tg[1], tg5x[1], tg[2],
				tg5x[2], tg[3], tg5x[3]);
		String s3 = String.format("【%s】（%s）	【%s】（%s）	【%s】（%s）	【%s】（%s）", dz[0], dz5x[0], dz[1], dz5x[1], dz[2],
				dz5x[2], dz[3], dz5x[3]);

		StringBuffer sb = new StringBuffer(s1);
		sb.append(this.seperate);
		sb.append(s2);
		sb.append(this.seperate);
		sb.append(s3);
		sb.append(this.seperate);

		return sb.toString();
	}
	/**
	 * 基本面分析
	 * 
	 * @return
	 */
	public String getBasicPointAnalysis() {
		StringBuffer sb = new StringBuffer();
//		sb.append("基本面分析：");
//		sb.append(this.seperate);
//		sb.append(this.seperate);
		sb.append(String.format("命主五行【%d】个金，【%d】个木，【%d】个水，【%d】个火，【%d】个土", num5x[0], num5x[1], num5x[2], num5x[3],
				num5x[4]));
		sb.append(this.seperate);
		sb.append(this.seperate);
		sb.append(String.format("命主命格属【%s%s】命", tg[2], tg5x[2]));
		sb.append(this.seperate);
		sb.append(this.seperate);
		sb.append(String.format("命主用神为【%s】，喜神为【%s】，命中忌【%s】！"+this.seperate, this.useGod, this.loveGod, this.hateGod));
		sb.append(this.seperate);
		sb.append(this.seperate);
		
		String color = luckyColorMap.get(this.useGod);
		String position = luckyPositionMap.get(this.useGod);
		String luckyNumber = luckyNumberMap.get(this.useGod);
		if(!this.useGod.equals(this.loveGod)) {
			      color+=","+luckyColorMap.get(this.loveGod);
			   position+=","+luckyPositionMap.get(this.loveGod);
			luckyNumber+=","+luckyNumberMap.get(this.loveGod);
		}
		
		
		sb.append(String.format("命主一生幸运色为: %s（多用这类颜色的产品或多穿这类颜色的衣服可提升整体运势）", color));
		sb.append(this.seperate);
		sb.append(this.seperate);
		sb.append(String.format("命主一生幸运方位: %s（在这些方位的城市和国家，或者一个城市内的这些方位生活和发展，有助于整体运势）", position));
		sb.append(this.seperate);
		sb.append(this.seperate);
		sb.append(String.format("命主一生幸运数字: %s（选择这些数字的楼层、电话号码、车牌号、密码等等可交好运）", luckyNumber));
		sb.append(this.seperate);
		sb.append(this.seperate);
		/**
		 * 命主 XX 星的 对应关系。
		 */
		
		sb.append(String.format("命主紫薇龙德星在【%s】，【%s】年助行好运。",longDexing.get(this.dz[0]),sxMap.get(longDexing.get(this.dz[0]))));
		sb.append(this.seperate);sb.append(this.seperate);
		sb.append(String.format("命主五富密码在【%s】，【%s】人及【%s】年能给命主带来财运。",wuFu.get(this.dz[0]),sxMap.get(wuFu.get(this.dz[0])),sxMap.get(wuFu.get(this.dz[0]))));
		sb.append(this.seperate);sb.append(this.seperate);
		sb.append(String.format("命主天喜星在【%s】，【%s】年易有大喜事。",tianXixing.get(dz[0]),sxMap.get(tianXixing.get(this.dz[0]) ) ));
		sb.append(this.seperate);sb.append(this.seperate);sb.append(String.format("命主驿马星在【%s】，【%s】年多动荡、变化。",yiMaxing.get(dz[0]),sxMap.get(yiMaxing.get(dz[0])) ));
		sb.append(this.seperate);sb.append(this.seperate);sb.append(String.format("命主华盖星在【%s】，【%s】年易出名，易升迁，利事业财运。",huaGaixing.get(dz[0]),sxMap.get(huaGaixing.get(dz[0])) ));
		sb.append(this.seperate);sb.append(this.seperate);sb.append(String.format("命主红鸾星在【%s】，【%s】年易有桃花。 ",hongLanxing.get(dz[0]),sxMap.get(hongLanxing.get(dz[0])) ));

		sb.append(this.seperate);
		sb.append(this.seperate);
		
		return sb.toString();
	}

	/**
	 * 开运建议
	 * 
	 * @return
	 */
	public String luckySuggestion() {
		StringBuffer sb = new StringBuffer();
		switch (shengxiao) {
		case "鼠":
			sb.append("子•鼠：目光短小臭千秋，十二生肖却为头。 "+this.seperate +this.seperate+  "乐天知命，对人热情，疏财仗义，人缘非常好。但做事缺乏耐性，而且桃花旺。"+this.seperate+this.seperate
					+ " 诗歌曰：“鼠为子水，遇龙则兴隆，遇猴则封侯”。  "+this.seperate+this.seperate+ "1.可佩饰龙图腾，摆在左手方位。  "+this.seperate+this.seperate+ "2.	寻找属龙的贵人，可得帮扶。  "+this.seperate+this.seperate 
					+ "3.	可摆放猴图腾，与属猴的人合作。 "+this.seperate+this.seperate+ "4.最好排在第二第三，会使公司好运"+this.seperate+this.seperate+ "5.睡觉可以使状态变好"+this.seperate+this.seperate
					+ "6.见鸡旺桃花(可以放只鸡的工艺品在床头)"+this.seperate+this.seperate+ "7.是公关人才，但是意气风发时容易说错话"+this.seperate+this.seperate+ "8.碰到龙可以发挥才能"+this.seperate+this.seperate
					+ "9.鼠和牛六合。鼠牛结婚，在热的地方比如南方或夏天，关系会好。到北方感情不好"+this.seperate+this.seperate+ "10.碰到蛇会发生问题"+this.seperate+this.seperate+ "11.不要吃蛇肉"+this.seperate+this.seperate
					+ "14.碰到虎年会有变动，搬家、旅游、换工作，通常是好的"+this.seperate+this.seperate+ "15.鼠女吃螃蟹会让老公疼，或者出去旅游"+this.seperate+this.seperate);
			break;
		case "牛":
			sb.append("丑•牛：血汗洒田笃实朋，默默耕耘苦一生。  "+this.seperate+this.seperate+ "勤奋忠诚，模范员工，保守固执，脾气较差，做事欠缺灵活"+this.seperate+this.seperate+ "诗歌曰：“牛为丑土，牛遇鼠贵有福，牛遇蛇真有德”  "+this.seperate+this.seperate
					+ "1. 可摆鼠图腾，使之六合富贵。"+this.seperate+this.seperate+ "2．寻找属鼠的贵人，可得帮扶。"+this.seperate+this.seperate+ "3.可佩饰可爱类型的蛇饰品，以增运。"+this.seperate+this.seperate+ "4.吃姜，增旺夫妻运"+this.seperate+this.seperate
					+ "5.吃猴头菇会好运"+this.seperate+this.seperate+ "6.到猴年好运，家中放猴的摆件会好运"+this.seperate+this.seperate+ "7.喝醋，吃鸡肉，放鸡在床头，旺贵人缘"+this.seperate+this.seperate+ "8.吃猪肉可以旅游、搬家、换工作、出国"+this.seperate+this.seperate
					+ "9.晚上3-5点行房，易怀孕"+this.seperate+this.seperate);
			break;
		case "虎":
			sb.append("寅•虎：林深啸起风声远，腾跃山间气势宏。  "+this.seperate+this.seperate+ "思考敏捷，具有领袖才华，喜欢冒险创业，不爱受人指挥，但一生跌宕起伏较多"+this.seperate+this.seperate
					+ "诗歌曰：“虎为寅，虎遇猪更享福，虎遇马事业大”。  "+this.seperate+this.seperate+ "1.可摆放猪饰品，如福猪，位置在西北位。 "+this.seperate+this.seperate+ "2.寻找属猪的贵人，可得帮扶。 "+this.seperate+this.seperate
					+ "3.可佩饰可爱类型的马饰品，以增运。"+this.seperate+this.seperate+ "4.女虎需要找百事百顺的老公"+this.seperate+this.seperate+ "5.肠部易出问题"+this.seperate+this.seperate+ "6.不能吃猪肠、鸡肠，容易破财和身体不健康"+this.seperate+this.seperate
					+ "7.吃羊肉遇贵人，感到开心"+this.seperate+this.seperate+ "      吃披萨，增加权威，威风"+this.seperate+this.seperate+ "      吃韩国烧烤，运气好"+this.seperate+this.seperate+ "8.兔年桃花旺，易结婚，添丁"+this.seperate+this.seperate
					+ "9.吃兔肉旺桃花，生孩子"+this.seperate+this.seperate+ "10.早5-7点容易怀孕"+this.seperate+this.seperate+ "");
			break;
		case "兔":
			sb.append("卯•兔：耳聪尾短有三窟，扑朔迷离怎辨出。 "+this.seperate+this.seperate + "灵巧聪明，悟性特强，但自我中心，一意孤行，处事不专。"+this.seperate+this.seperate + "诗歌曰：“兔为卯，兔遇狗定大吉，兔遇月成大业”。"+this.seperate+this.seperate
					+ "1. 可摆放狗饰品，位置在正西方位。  "+this.seperate+this.seperate + "2. 寻找属狗的贵人，可得帮扶。  "+this.seperate+this.seperate
					+ "3. 可摆放月亮，因为月为肉的偏旁，也可以晚上接月亮之气来增加事业运气。 "+this.seperate+this.seperate + "4. 很多个可以落脚的地方，或者可以做很多不同的工作"+this.seperate+this.seperate + "5.吃兔肉旺财"+this.seperate+this.seperate
					+ "6.吃海带助横财"+this.seperate+this.seperate + "7.吃老婆饼容易找女朋友旺桃花，已婚不要吃"+this.seperate+this.seperate + "8.吃冬瓜旺桃花"+this.seperate+this.seperate + "9.吃螃蟹肚子疼"+this.seperate+this.seperate + "10.吃羊肉惹是非"+this.seperate+this.seperate
					+ "11.中午11-1点，易怀孕"+this.seperate+this.seperate + "12.在车上做易怀孕"+this.seperate+this.seperate + "");
			break;
		case "龙":
			sb.append("辰•龙：体藏东海卧深潭，从不把身现世间。 "+this.seperate+this.seperate + "好胜心强，但善变，行事飘忽，欠缺耐心."+this.seperate+this.seperate + "诗歌曰：“龙为辰，龙遇鸡更大吉，龙遇鼠真有福”。 "+this.seperate+this.seperate
					+ " 1. 可摆放鸡饰品，位置在正西方位。  "+this.seperate+this.seperate + "2. 寻找属鸡的贵人，可得帮扶。  "+this.seperate+this.seperate + "3. 可以摆鼠、金钱袋鼠、鼠来宝，在正北方位。"+this.seperate+this.seperate
					+ "4.吃芝麻财运好，发横财"+this.seperate+this.seperate + "5.吃露笋会生病"+this.seperate+this.seperate + "6.吃黄花菜旺桃花"+this.seperate+this.seperate + "7.放鸡的摆件在家，人缘、桃花旺"+this.seperate+this.seperate
					+ "8.晚上11-1点，灵感多状态好"+this.seperate+this.seperate + "9.子月最旺，阳历11.8-1.8号"+this.seperate+this.seperate + "");
			break;
		case "蛇":
			sb.append(" 巳•蛇：曲折蜿蜒细身长，弓映杯中自惊慌。  "+this.seperate+this.seperate + "灵机敏捷，聪明智慧，耐力强，有贯彻始终的力气，但不听人言，我行我素，坚持己见，至胜方休息"+this.seperate+this.seperate
					+ "诗歌曰：“蛇为巳，蛇遇猴必封侯，蛇遇草安全好”。"+this.seperate+this.seperate + " 1. 摆猴、摆孙悟空，摆在西北方位。  "+this.seperate+this.seperate + "2. 寻找属猴的贵人，可得帮扶。  "+this.seperate+this.seperate
					+ "3. 摆五谷丰登之物稻谷、秋天去农场、多参加农业劳动。  "+this.seperate+this.seperate + "4.吃鸡肉贵人运好"+this.seperate+this.seperate + "5.家里种花草树木运气好"+this.seperate+this.seperate + "6.每年6.10.3月份桃花好"+this.seperate+this.seperate
					+ "7.吃花生酱、花生，旺桃花、旺偏财"+this.seperate+this.seperate + "8.吃燕窝好运"+this.seperate+this.seperate + "9.去泰国好运，拜四面佛好运"+this.seperate+this.seperate + "10.吃西瓜惹是非，吃东坡肉破财，喝玉米汤破财"+this.seperate+this.seperate
					+ "11.吃日本神户牛肉，有财有势"+this.seperate+this.seperate + "12.开车不太好"+this.seperate+this.seperate + "");
			break;
		case "马":
			sb.append("午•马：金鞍蹄奋踏飞云，飒飒英姿似战神。  "+this.seperate+this.seperate + "不服输，但做事效率高，喜欢四处走动。女马缘在远方"+this.seperate+this.seperate + "诗歌曰：“马为午，马遇羊更吉祥，马遇虎更有福”。  "+this.seperate+this.seperate
					+ "1. 摆羊、摆三阳开泰，摆在西北位置。  "+this.seperate+this.seperate + "2. 寻找属羊的贵人，可得帮扶。  "+this.seperate+this.seperate + "3. 摆虎、摆猫，摆在东北方位。"+this.seperate+this.seperate
					+ "4. 吃蛇、东坡肉，惹是非"+this.seperate+this.seperate + "5.吃鸡有桃花"+this.seperate+this.seperate + "6.马在外地运气好，在出生地不得志"+this.seperate+this.seperate + "7.中午11-1点，头脑不清醒，下午3点后状态好"+this.seperate+this.seperate
					+ "8.秋天8月份开始，或遇猴年，有变动"+this.seperate+this.seperate + "9.放猴子在床头能有变动，旅行、换工作等"+this.seperate+this.seperate + "10.吃绿豆会开心"+this.seperate+this.seperate + "11.喝南瓜汤富贵"+this.seperate+this.seperate + "");
			break;
		case "羊":
			sb.append(" 未•羊：咩声唤起三阳泰 ，盛世迎福万利收。  "+this.seperate+this.seperate + "外表温纯，孝顺，不爱与人斗争，忍耐力强，但内心刚强，有巨大生命力，多愁善感，情绪过敏."+this.seperate+this.seperate
					+ "诗歌曰：“羊为未，养遇马更潇洒，羊遇草有草料”。  "+this.seperate+this.seperate + "1. 摆马图腾，摆在正南方位。  "+this.seperate+this.seperate + "2. 寻找属马的贵人，可得帮扶。  "+this.seperate+this.seperate
					+ "3. 摆五谷丰登之物稻谷、秋天去农场、多参加农业劳动。 "+this.seperate+this.seperate + "4. 吃花生糖旺桃花"+this.seperate+this.seperate + "5.吃粉肠、鹅肠、猪大肠，助爱情桃花"+this.seperate+this.seperate
					+ "6.晚上11-1点，易怀孕"+this.seperate+this.seperate + "7.去迪士尼乐园玩遇米老鼠，好运"+this.seperate+this.seperate + "8.羊和马合作机会大"+this.seperate+this.seperate + "9.遇蛇会有变动，旅游、搬家、换工作等等"+this.seperate+this.seperate
					+ "10.吃油菜，多吃素菜财运好，解开灾难"+this.seperate+this.seperate + "11.吃龙眼、红龙果行大运"+this.seperate+this.seperate + "12.吃瓜果好运"+this.seperate+this.seperate + "13.穿毛衣，羊毛衣服容易生病 "+this.seperate+this.seperate
					+ "14.遇兔有贵人"+this.seperate+this.seperate + "");
			break;
		case "猴":
			sb.append("申•猴：龙王玉帝无畏惧，鬼怪妖魔相见愁。"+this.seperate+this.seperate + "机灵敏捷，精力十足，没有安全感，决断力高，仗义，易成领袖，好胜心强，有权利欲，但做事缺乏耐性和毅力."+this.seperate+this.seperate
					+ " 诗歌曰：“猴为申，猴遇蛇有道德，猴遇木有家住”。  "+this.seperate+this.seperate + "1. 摆蛇图腾，摆在东南方位。  "+this.seperate+this.seperate + "2. 寻找属蛇的贵人，可得帮扶。  "+this.seperate+this.seperate
					+ "3. 可以到森林中采气、用木制家具、栽树、种树可以增加能量 "+this.seperate+this.seperate + "4.越老越有风采"+this.seperate+this.seperate + "5.猴遇见羊，会有天喜星非常喜爱，桃花星，添丁星"+this.seperate+this.seperate
					+ "6.遇龙事业好，权威星"+this.seperate+this.seperate + "7.吃牛肉会开心"+this.seperate+this.seperate + "8.吃羊肉会好运"+this.seperate+this.seperate + "9.吃乌鱼子发大财"+this.seperate+this.seperate + "");
			break;
		case "鸡":
			sb.append("酉•鸡：独有引吭催晓曲，迎来旭日壮乾坤。 "+this.seperate+this.seperate + "自得其乐，淡薄名利，只热衷于追求自己的理想，易眼高手低。"+this.seperate+this.seperate
					+ " 诗歌曰：“鸡为酉，鸡遇龙更兴隆，鸡遇米更是喜”。  "+this.seperate+this.seperate + "1. 摆龙，摆在东南方位，登龙山、龙灯。  "+this.seperate+this.seperate + "2.寻找属龙的贵人，可得帮扶。  "+this.seperate+this.seperate
					+ "3. 摆五谷丰登之物稻米、秋天去农场、多参加农业劳动。  "+this.seperate+this.seperate + "4.吃鸡肉旺财，有鸡发财"+this.seperate+this.seperate + "5.吃番茄旺桃花，旺人缘"+this.seperate+this.seperate + "6.喝红枣水催婚，旺人缘"+this.seperate+this.seperate
					+ "7.晚上11-1点灵感才华爆发"+this.seperate+this.seperate + "");
			break;
		case "狗":
			sb.append("戌•狗：防卫盗贼亲守夜，忠于家主品如金。  "+this.seperate+this.seperate + "一生忠诚，要不断工作，做事不会半途而废，易招委屈。"+this.seperate+this.seperate + "诗歌曰：“狗为戌，狗遇人有贵人，狗遇喜有名气”。  "+this.seperate+this.seperate
					+ "1.摆八仙，增加生气、喜气，使事业运气好。  "+this.seperate+this.seperate + "2.摆鲜花、树木，增加生气、喜气，使事业运气好。 "+this.seperate+this.seperate + "3.宜二不宜一，宜劳不宜逸(适合当老二，适合工作)"+this.seperate+this.seperate
					+ "4.每天都要做事情，闲下来容易生病"+this.seperate+this.seperate + "5.葱是天喜星，吃葱会开心"+this.seperate+this.seperate + "6.吃胡萝卜旺财"+this.seperate+this.seperate + "7.吃螃蟹会升官发财"+this.seperate+this.seperate + "8.吃蛇羹会好运"+this.seperate+this.seperate
					+ "9.吃牛肉、猪肉都好运"+this.seperate+this.seperate + "10.早5点容易怀孕"+this.seperate+this.seperate + "11.用水养四支富贵竹能升官，考试好，桃花旺"+this.seperate+this.seperate + "");
			break;
		case "猪":
			sb.append("亥•猪：肠粗肚大懒脏谗，只为口福饱美餐。  "+this.seperate+this.seperate + "聪明，记忆力强，做事效率高，虎头蛇尾，三分钟热度，欠缺报复和斗志，失败容易气馁。"+this.seperate+this.seperate
					+ "诗歌曰：“猪为亥，猪遇豆更长寿，猪遇粮富裕长”。 "+this.seperate+this.seperate + " 1. 摆五谷丰登之物大豆、秋天去农场、多参加农业劳动。  "+this.seperate+this.seperate
					+ "2. 摆宝贝花瓶、多接触与家具有关的物件。"+this.seperate+this.seperate + "3.养鱼有正常的男女关系，夫妻感情，不养鱼对感情冷淡"+this.seperate+this.seperate + "4.吃太多肉不好运，要多吃菜"+this.seperate+this.seperate
					+ "5.吃干贝对大肠不利"+this.seperate+this.seperate + "6.吃日本生鱼肚子不舒服"+this.seperate+this.seperate + "7.吃茄子旺文昌星"+this.seperate+this.seperate + "8.吃红色辣椒。越吃辣，越发财"+this.seperate+this.seperate
					+ "9.养狗或放狗的摆件多贵人"+this.seperate+this.seperate + ""+this.seperate+this.seperate + "");
			break;
		default:
			break;
		}

		return sb.toString();
	}

	/**
	 * 命格分析
	 * 
	 * @return
	 */
	public String mgAnalysis() {
		String title = "";
		StringBuffer sb = new StringBuffer(title);
		sb.append(String.format("命主命格格局属【%s%s】命。"+this.seperate, tg[2], tg5x[2]));
		switch (tg[2]) {
		case "甲":
			sb.append(
					"甲木之人，基本上都是好命之人。甲木是参天大树，很容易出人头地，做任何事情也能得到他人的认同。人缘一定好，受人欢迎，很多人愿意和甲木之人在一起。甲木人做事专一，感情也专一，贯彻始终。财运佳，具备富豪之命。甲木人为人正直，富正义感，对工作和家庭负责。甲木人由于思想比较保守，财富到了一定的位置容易停滞不前，难以找到更大的发展空间。如果要真正赚大钱，要靠火生土，才可以令财富扎实巩固。补火建议多穿红色的衣服，或者养猫狗。婚姻上，甲木眼光不能太高，最好找条件比自己差一点的配偶，婚姻反而幸福，否则容易经历波折。甲木人，赚的钱越多，丈夫越健康长寿。建议掌管家中财物	");
			break;
		case "乙":
			sb.append(
					"乙木之人，运气较迂回曲折，变化较大。故，人生较多曲折。乙木适合从事文化创作行业，便容易成功，可以成名发达。乙木基本旺夫，特别旺丈夫名气和事业。不过往往由于生子女时引发婚姻危机。故乙木之人，子女不宜过多。生女儿还好，生儿子的话，很容易触发婚姻危机。乙木之人，不能太有钱，太有钱反而破坏夫妻关系。夫妻间容易为了财富争执");
			break;
		case "丙":
			sb.append(
					"丙火之人，是不需要争斗偶的幸福人。乐天知命，天掉下来当被子盖。并且乐于助人，为人慷慨。喜欢宗教文化，有信仰。丙火之人，一辈子最怕患心脏病。只要丙火顺从丈夫，财富幸福可兼得，可共白头。若婚后十分强势，则势必婚姻难保。故，丙火之人适合晚婚，等各方面心性都成熟后结婚，对婚姻有利。丙火之人工作能力强，有财格。");
			break;
		case "丁":
			sb.append("丁火之人，非常喜欢钱，而且钱能生旺其自身命格。投资运佳，投资可得大财。丁火之人，最好家中养猫，令自己身强，才可以有好运。丁火之人，财运佳，但难以守住财。不过仍属富豪命格。");
			break;
		case "戊":
			sb.append(
					"戊土之人，平时要多接触阳光，有助于行好运。居住之地，最好光线要充足，办公场所也要如此。戊土之人，主观意识强，性格刚烈秉直，但有时候容易因为自身性格得罪人。为人侠义，不斤斤计较，是成大事的命格。戊土之人，命格具备财格，钱能使戊土之人身强。能赚亦能花，常存不住钱。戊土之人要注意，这一生不宜赚太多偏财，否则容易出现是非，管非，健康也容易出现问题。另外，正财方面也不宜太多，否则难以保全夫妻情感。所以有钱之后，要多做善事，适当散财，积累福报。");
			break;
		case "己":
			sb.append(
					"己土之人，是好命之人。身带名财双格。有生之年当可名利双收。性格上善解人意。一生机遇较平坦。若自己创业，可从事餐饮、地产行业。己土之人，若遇甲木之人，则能扶摇直上。若不创业，则最佳选择是当公务员。亦可有一番作为。");
			break;
		case "庚":
			sb.append(
					"庚金之人，做事有冲劲，不怕受苦。经理锻炼越多，约可以成材。另外，庚金属锋利之金，命中带着煞气。需要注意平时与人相处方式和自己说话方式。尤其是和丈夫的相处之道。庚金之人要从事正当行业，偏财运欠佳，正财旺。财富事业需要投入时间心血，需要较长时间才有所成。故庚金之人需要多点耐心，不要寄希望一步登天或一夜暴富。庚金之人，天生带财，财越多，身体越强，财越多亦月长寿。婚姻方面，庚金之人需要找一个条件不如自己的男人，才可以维系婚姻，婚姻才会幸福美满。");
			break;
		case "辛":
			sb.append(
					"辛金之人，赚钱是一生使命，比较劳碌。辛金之人较聪慧，能利用自身优势，能与人为善，故颇有人缘。能力亦佳。辛金偏软，故要成大器，需要天时地利人和同时配合。另外，命主需要格外注意自身身体健康。肠胃很容易出毛病，老年时，心脏血管亦会出现问题。");
			break;
		case "壬":
			sb.append(
					"壬水之人，善交际，大局观极佳，能做到面面具圆，会拥有广阔的人脉资源，而且桃花运特别好。壬水之人，财运亦旺，赚钱是一生的使命，财运大部分来自正财，只要不停工作，才可财源滚滚，源源不绝。不过要注意的是，壬水之人，若钱财赚太多，则婚运不好，夫妻易失和。故壬水之人，应当做做善事，适当散财，行善积福，则运势更旺！");
			break;
		case "癸":
			sb.append(
					"癸水之人，属于容易成功之大吉命格。财富会自动送上门。癸水之人，子女越多，财运则越旺。故建议一生多认些干女儿，干儿子，对自身命格很有帮助，有助行好运。癸水之人，丈夫是一生最重要之人，但最好丈夫命格属戊土，找到戊土，丈夫会给壬水之人大量财富，用之不尽。但有一点，婚姻中，癸水之人要以丈夫为中心，牺牲自己成全丈夫，可以说，癸水之人需要一个广大的胸怀和奉献精神。这样，家庭婚姻才能顺顺利利，名利双收。");
			break;
		default:
			break;
		}
		return sb.toString();
	}

	/**
	 * 四柱分析 to do ;
	 * 
	 * @return
	 */
	public String szAnalysis() {
		StringBuffer sb = new StringBuffer();
		/** 婚姻分析 */
		String type = this.fuxing[2][0];
//		"比肩", "劫财", "食神", "伤官", "偏财", "正财", "七杀", "正官", "偏印", "正印"
		switch (type) {
		case "正官":
			// 日支正官 4
			sb.append(
					"婚姻宫坐正官，代表约束，名望，礼教，纪律，秩序等，也意味管理能力和约束力，正官为喜用时，主人品性端正，正大光明，心地善良，客观理性，知礼守法，重视纪律法规；正官为忌时，做事按步就班、循序渐进、却流于刻板谨慎与墨守成规，同时也缺乏耐心，做事往往虎头蛇尾，太过于空洞刻板，缺乏先知之明。因此日支正官在择偶时，往往会选择年纪小一点，却也往往是因相亲而结婚。男命：妻子值得信赖，家中大小事妻子皆作好妥善的打理，而自己本身也会努力扮演好丈夫的角色。女命：婚姻运很不错，拥有幸福美满的家庭生活可能性很高。");
			break;
		case "七杀":
			// 日支偏官 8个
			sb.append(
					"婚姻宫坐偏官，代表谋略和权威，为人比较偏激也喜欢冒险，具有高昂的斗志及果断力，济弱扶倾，锄强扶弱，可是也刚愎自用。本身个性太过于急躁如火，做事往往欠缺深思考虑，以女命而言，最好丈夫年龄有差距，可调整自己属性与变动，让自己感情婚姻生活变得较为充实；男命则容易遇到性格脾气较沉不住气的女性。除非时、日柱或月、日柱发挥整合协调功能，改变偏官的质性，一般而言，偏官坐落于夫妻宫的男女不太能融入婚姻的家庭生活。因为七杀具有偏激与冒险，刚愎自用，独断独行的性格，主观重，不会随意听取别人的意见，思想上较为反传统，所以配偶有年龄差距较大的倾向。男命自我本位主义重，恋爱时期浪漫多情调，会将韦小宝的功夫发挥到淋漓尽致，但婚后却可能会有移情别恋而多劈腿机会！女命：婚前婚后，皆能散发吸引异性的丰采，一般有较多接触异性的机会，即使在婚后，亦会有来自感情方面的困扰，而在统计上，与婆婆相处不融洽的比例非常高！");
			break;
		case "比肩":
			// 日支比肩 10个
			sb.append(
					"婚姻宫坐比肩，并肩而行或在于同事地位，代表着对内的感情友谊，也代表着自我意识与自尊，一心渴望与人并驾齐驱，不落人后，就有点独断独行，凡事靠自己，不愿藉助他人之手，很有自知之明，临事果决，光明磊落，表里如一，但如果比肩、劫财过多，则为六亲缘薄，男女皆不宜早婚。自己则实践力旺盛，有勇无谋，固执己见，不易妥协，往往有一种罔顾一切，只管向前猛进的闯劲；命局比肩若过多，如是在配偶宫或八字比肩旺的人，配偶年纪差距不会太大，甚至有同岁的可能。属于晚婚型的结构，除了直率外，尚有点心高气傲。男生：您会守住或绑住你的配偶，而你却并非是个温柔体贴的好丈夫，所以婚姻生活易起冲突！女生：婚姻会有老妻少夫配的可能性，如果婚配对象年纪差不多时，那夫妻间易有争吵、失和的情形发生。女性如果比肩太旺，则做事独断独行，有事情也不愿与丈夫商量，彷佛无视丈夫的存在，夫妻形同陌路。");
			break;
		case "食神":
			// 日支食神 4个
			sb.append(
					"婚姻宫坐食神，所以此人含蓄保守，内向多情，性格开朗，烦恼较不容易留在心中，因而乐观而得人缘，本身有审美之能力，爱好文艺歌舞，注重生活情趣与品味，讨厌被管束的生活和感觉，喜欢无拘无束的生活，缺点是容易沉迷于游乐之中而忽略现实的生活，而失去奋发进取的精神。此乃食神克官、杀星的原因，比较喜欢比自己年纪大的人相处。婚前恋爱期也会比较长，配偶有可能是青梅竹马，或来自远房的亲戚，或是国小同学，甚至是失联多年的旧识好友，这种结构的婚姻，往往因亲友长辈的介入而感受到困扰。");
			break;
		case "伤官":
			// 日支伤官 7
			sb.append(
					"婚姻宫坐伤官，没有拘束地展露自己，表现在外的才华，性格属于开朗又外向多情，活泼乐观，博学多能，多才多艺，聪明睿智，清逸秀丽，领悟力很强。聪明又反应快，很想要超越自我和别人的欲望，也喜欢他人的肯定与掌声，最讨厌世俗礼法的约束，此乃伤官克正官，正官是传统礼法与约束，因而不喜欢长辈的批评，因而我行我素，只要我喜欢有什么不可以，也因为这种强烈的竞争心态，如果另一半与自己年纪相近，只怕吵吵闹闹，永无宁日。一般伤官旺的人，充满真诚，能够舍己从人，但在社会上欠圆通；喜怒哀乐易行于外，心中有话藏不住，作事喜欢高谈阔论，如果经营事业往往或对事物的看法抱持过于乐观的态度而导致失败。其择偶对象，特别喜欢年龄差距大的人。通常都是不被亲友看好的婚姻，甚至周遭人也会有反对的声音。男命：平常是很和颜悦色，还算亲切，但生气时，激烈也容易冲动，当他另一半就得小心了，随时有被老公打之可能。女命：由于情绪起伏过大，所以婚姻感情的困扰亦多，而太早结婚恐怕婚姻很难保住。");
			break;
		case "正印":
			// 日支正印 4
			sb.append(
					"婚姻宫坐正印，代表思想，学识，信誉，名声。此人，气质高雅有品味，有爱心，重视内在与内涵，很有人情味，爱好面子有自尊。只是凡事出一张嘴，不想自已动手，只想别人代劳。因为日支生助日主而克制食神、伤官星的特性，容易找到心地比较好的另一伴，且对方对自己是容易付出的类型。日支正印，配偶仁慈善良，聪颖敦厚，气质高尚优雅，日主弱者尤佳。男性容易逃避现实问题，幸亏太太有母性的味道；女性刚好相反，爱嫁年龄比自己小的男生，有谦让的君子风度。所以命格正印星旺时，是依赖性强因而无主见，显示出来是平安而有福气，因为有生我的缘故，压力减少易流于懒惰，容易找年龄差距比自身小的配偶，且心中永远存在着不能告诉配偶的秘密。本人自尊心很强，在意的是眼前的事情，所以会有斤斤计较之毛病，十分重视颜面及家庭的形象，追求财富的心虽然很强烈，却不形之于色。男命：婚姻生活祥和平淡，缺乏多采多姿的浪漫情调。女命：意志力稍嫌薄弱，与先生之间总觉得有一层难以突破的薄膜，也无法满足先生所给予的一切！");
			break;
		case "偏印":
			// 日支偏印 8
			sb.append(
					"婚姻宫坐偏印，代表知识和技术，具非常特殊的领悟力及理解力，思想超俗也钻牛角尖，容易自相矛盾但又坚持到底，偏印就是爱幻想、不切实际，所以常会挑选让人意想不到的配偶。交流上不易畅通，配偶容易个性偏内向，有时易有不够开朗的现象。丙寅、壬申日的女性，幸福、得优秀的子女；男性妻子贤淑。丁卯、癸酉日生人，幼年时代易患大病，早离双亲，婚姻不美满。癸酉日生的女性，夫缘尤劣。庚辰、辛丑日生者，与生父母缘薄；男性，妻缘不好，女性，可得贤良子女，一生幸福，但庚辰日生者，夫缘不佳。辛未、庚戌日生人，双亲缘薄，婚姻不美满；女性，表面柔顺，内心冷酷，不孝翁姑。结婚后，操心劳形。幼年时体弱多病，而且大都属严重危险的急症。不过日支偏印者，最好钻研发明、创作而容易成功。");
			break;
		case "正财":
			// 日支正财 4
			sb.append(
					"婚姻宫坐正财，真心付出的同时，同样也希望得到相对的回报。夫妻宫内是正财星，另一伴多半是有家庭责任感的人，如果男性是这样的话，那就容易找到有助夫运的老婆，此乃妻宫妻守（指男命正财坐日支夫妻宫本位），配偶贤慧，善理家，但会是一个醋罐子。如果是女命：亦能嫁得贤夫，但气势凌驾夫婿之上，所以除了能获得丈夫的庇荫外，亦有可能变成女主外男主内的反传统倾向。");
			break;
		case "偏财":
			// 日支偏财 8
			sb.append(
					"婚姻宫坐偏财，为人慷慨豪迈，聪明圆滑，机智敏锐，淡泊名利，豪爽侠义，乐于助人，不喜欢虚伪，为人讲义气，但也风流多情，出手也很大方，见义不惜财，所以人缘大都还不错。男命：属于风流倜傥型的机率颇高，颇擅于交际，异性缘佳，即使在婚后，亦有出轨的迹象。女命：在婚后，会为丈夫牺牲所有，以夫为荣为贵的心态，然而却可能与公婆及夫家亲人产生许多磨擦及不愉快。一般而言，偏财在日支为喜者，男性能得配偶之资助而成家立业建立财富。在同柱又有将星或其他大贵神者，往往可以娶到富家之女，即所谓的豪门闰秀。可以减少奋斗三十年，真正的乘龙快婿。但若是忌神，则不验，纵能娶得，恐怕也是花钱如水，一掷千金，爱慕虚荣之妇。");
			break;
		case "劫财":
			sb.append("婚姻宫坐劫财，财难聚、晚婚、婚差、婚变，配偶骄傲、不善理财、喜饰外表、爱虚荣、慷慨、重义。日支劫财，男得女人事业助、但无结果，轻视配偶，对他人大方、对配偶只做表面功夫。妻体弱或夫妻不和，在社会上也少人和。配偶身体不好、婚姻不顺利，如果这个劫财又为忌神，则更加严重。");
			break;
		default:
			break;
		}

		sb.append(this.seperate);
		sb.append(this.seperate);
		
		/** 财运说明 */
		switch (caiyun) {
		case 0:
			sb.append("无财");
			break;
		case 1:
			sb.append("只有正财：命主命中只有正财，没有偏财，正财代表稳定可控的收入，如薪水或产业的稳定收入。命主需付出实际努力，才可以获得回报。切记勿做投机取巧的事情。");
			break;
		case 2:
			sb.append("只有偏财：命主命中只有偏财，没有正财，偏财代表灵活收入，或不可控的财源，如投资等。只有偏财的人不想坐班，或坐班难以满足人生追求。适合做小生意，或在有本职工作的同时经营第二职业。");
			break;
		case 3:
			sb.append(
					"正财偏财都都有：命主命中正财偏财都有，财运极旺。正财代表稳定可控的收入，如薪水或产业的稳定收入。偏财代表灵活收入，或不可控的财源，如投资等。正财偏财都有的人最适合创业，或在有本职工作的同时经营第二职业。");
			break;
		default:
			break;
		}
		sb.append(this.seperate);
		sb.append(this.seperate);

		String[] desc = new String[] { "一", "二", "三", "四" };
		/** 财库说明 */
		switch (caiku) {
		case 0:
			sb.append("命主命中没有财库，所以赚钱辛苦，并且会有留不住财的情况，建议打开财库，终身提升财富能量，便可财运滚滚，万事大吉。");
			break;
		case 1:
		case 2:
		case 3:
		case 4:
			sb.append(String.format("命主命中有%s个财库，但财库未开，所以任然赚钱辛苦，并且会有留不住财的情况，建议打开财库，终身提升财富能量，便可财运滚滚，万事大吉。",
					desc[caiku - 1]));
			break;
		default:
			break;
		}

		sb.append(this.seperate);
		sb.append(this.seperate);

		sb.append(String.format("命主命中规定【%d】子【%d】女。", childsNum[0], childsNum[1]));
		sb.append(this.seperate);
		sb.append("此以古法论，实际情况因考虑计划生育等诸多现实情况。");
		sb.append(this.seperate);
		sb.append(this.seperate);

		return sb.toString();
	}

	/**
	 * 神煞分析; to do
	 * 
	 * @return
	 */
	public String shenshaAnalysis() {
		
		StringBuffer sb = new StringBuffer();
		for(int i=0,size=shensha.size();i<size;i++) {
			if(i==0) {
				sb.append(" 年柱神煞:");
			}
			if(i==1) {
				sb.append(" 月柱神煞:");
			}
			if(i==2) {
				sb.append(" 日柱神煞:");
			}
			if(i==3) {
				sb.append(" 时柱神煞:");
			}
			for(int j=0,jsize=shensha.get(i).size();j<jsize;j++) {
				sb.append(shensha.get(i).get(j));
				sb.append(",");
			}
		}
		
		StringBuffer result = new StringBuffer();
		result.append("命中神煞有:"+sb);
		result.append(this.seperate);result.append(this.seperate);
		
		String allShensha = sb.toString();
		
		result.append("吉神和描述:"+this.seperate);
		result.append(this.seperate);result.append(this.seperate);
		if( allShensha.contains("天乙")) {
			result.append("天乙：为逢凶化吉最有力之贵人星，是命中最吉之神。人命逢天乙贵人，功名早达，官禄易进，遇事有人帮，临难有人解。命遇之主聪明好学，主生活光辉。");
			result.append(this.seperate);
			result.append(this.seperate);
		}
		
		if( allShensha.contains("文昌")) {
			result.append("文昌：命逢文昌星，气质高雅，举止温文，文才出众，风度潇洒。男命逢着内涵，女命逢着仪容。文昌入命，主聪明过人，又主逢凶化吉。气质高雅，举止斯文。具有上进心，求知欲强，喜欢学习。一生近官利贵，不与粗俗之辈乱交。一般都比较聪明，机灵，充满文智慧，领悟性强，能够一点就通，举一反三。同时也让人拥有地位和权利，甚至能够名利双收。");
			result.append(this.seperate);
			result.append(this.seperate);
		}
		
		if( allShensha.contains("金舆")) {
			result.append("金舆：主富贵，得妻财。女命逢之，主性格温柔善良， 容貌美丽，生活富裕无忧，人缘好、财源丰，生活幸福安吉，骨肉安泰；男命遇此，主得贤妻或受女性青睐，子孙康健，相貌慈善，性情温和，举止文雅，可娶贤妻，得享妻之钱财，荣富显贵。古代皇族，多命带此星。平常人家子弟遇此，主娶高贵人家之女。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("禄神")) {
			result.append("禄神：身体健康，自信要强，性情急躁，易得罪人，记忆力好。勤劳致富，一生少闲，需要不断的忙碌，就有钱赚。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("驿马") ) {
			result.append("驿马：一生走动多，要出远门，出国。即使在一个地方，也经常忙个不停。当官的人八字带驿马，表明经常升迁，无钱无势的老百姓八字有驿马，只表示东奔西走而已。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("华盖") ) {
			result.append("华盖：主人头脑聪慧，喜爱艺术，但性情乖僻捉摸不定，一生多忧少乐，即贵亦孤。才华出众，能够与宗教，玄学结缘而带来好运，危难时似有神仙保佑，绝处得救。女性行华盖运，为木兰从军，日坐华盖对婚姻不利，时带华盖对子女不利；男人行华盖运，具有极强说服力，容易在五术（山，医，命，相，卜）上有所建树。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("桃花") ) {
			result.append("桃花：风流之星，主男欢女爱，生辰八字带桃花的人，花心，容易吸引异性，也容易对异性感兴趣。人缘好，异性缘好，但多半是烂桃花。也代表审美水平高。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("将星") ) {
			result.append("将星：将者管理之意，将星为吉星，是一个主权威的神煞，命中逢之能够增强人的领导才能。命带将星之人能文能武，一生有权柄威信，假如命局配合得当，则重权在握。可成为一行业内的杰出人才或领导人。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("天德,") ) {
			result.append("天德：天地德秀之气，逢凶化吉之神，其最大的特点是化解灾厄。命带天德贵人者会遇到很大的福德，其人心地善良，身体健康，人缘好，在生平之中较不会遇到横祸、盗难、灾劫等，纵使逢之也能适时得以化解。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("天德合") ) {
			result.append("天德合：天地德秀之气，逢凶化吉之神，其最大的特点是化解灾厄。命带天德贵人者会遇到很大的福德，其人心地善良，身体健康，人缘好，在生平之中较不会遇到横祸、盗难、灾劫等，纵使逢之也能适时得以化解。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("月德,") ) {
			result.append("月德：主人心地善良，福分深厚，功名显达，光耀门庭，做事有公德心，人聪明有才气，一生很少得病，健康长寿，不犯官刑。月德也是勤勉之星，平时勤勉自助在关键时刻才能获得天助。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("月德合") ) {
			result.append("月德合：主人心地善良，福分深厚，功名显达，光耀门庭，做事有公德心，人聪明有才气，一生很少得病，健康长寿，不犯官刑。月德也是勤勉之星，平时勤勉自助在关键时刻才能获得天助。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("红鸾") ) {
			result.append("红鸾：红鸾桃花是对婚姻、恋爱、感情颇为吉利的一朵桃花，对爱情有逢凶化吉之功能。主富贵，气度雍容华贵，有文采而内敛，不露锋芒。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("天喜") ) {
			result.append("天喜：主人容貌美丽，聪明秀气，性格温和，异性缘好。会使恋爱过程减少曲折，结果美满。为避凶星，能逢凶化吉，正所谓“一喜可挡破三煞”。为人老实耿直，守信用，感情丰富，随遇而安。乐于助人，亦得他人之助。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("天医") ) {
			result.append("天医：天医是掌管疾病之事的星神。 四柱逢天医， 如不旺， 又无贵人吉神相扶， 不是常患疾病就是身弱无力。 若生旺又有贵人相生助， 不仅身体健壮， 而且天医有天生的治愈能力，特别适合医务工作及心理学, 哲学，神秘学等。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("太极") ) {
			result.append("太极：主地位崇高，于事业、学术方面有术出之成就，且能独树一帜，受人景仰。对神秘事物感兴趣，并有着较高悟性的吉星。主其人聪明好学，为人正直，有钻劲，做事有始有终，喜天文、地理、哲学，与佛道、宗教有缘。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("国印") ) {
			result.append("国印：诚实可靠，严守清规，照章行事，办事公道。为人和悦，礼义仁慈，气质轩昂。如国印逢生旺，有其它吉星相助，不逢冲破克害，不仅可以有掌印之能，可亦为官掌实权。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("魁罡") ) {
			result.append("魁罡：命带魁罡的人，性格一般都比较耿直。没有心机，胸无城府，嫉恶如仇。同时又聪明果断，善于使用权利。爱恨分明，喜欢见义勇为。性格刚强不屈。女人若命中有此星，一般心性过度刚强, 对于婚姻有不利的影响。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("十灵日") ) {
			result.append("十灵日：表示有十灵神庇佑，智慧聪敏，逢凶化吉，更有贵人扶持。十灵者，聪颖性灵，好诗书文章、时尚杂论等，男子性灵，女子气质佳长相灵秀。主灵慧聪明，容易洞悉宗教之星，直觉敏锐，善于察言观色、领悟力、领会力，善解人意等心性皆强。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("六秀日") ) {
			result.append("六秀日：命带六秀者，聪明、多才多艺、相貌俊秀、人缘佳。文笔卓越之星，其人秀气聪明，生性聪敏，有才艺，博学多能，求知欲、学习力、思考力等能力皆强，属静态艺术家，如作家、画家、书法家、词曲创作家之类，学艺有成，易获功名成就。");
			result.append(this.seperate);result.append(this.seperate);
		}
		
		result.append(this.seperate);result.append(this.seperate);
		
		result.append("凶煞和描述："+this.seperate);
		result.append(this.seperate);result.append(this.seperate);
		
		if( allShensha.contains("羊刃")) {
			result.append("羊刃：是五行过旺之气，易发生灾难或意外事故，要做手术。情绪易激动，易树敌。一般来说，男人八字命盘中含有\"羊刃\"，十之八九都是固执而不会轻易妥协让步的大男人，但相对也是属于有责任感的男人;而女人命中有羊刃，则是情绪不稳定且感情、婚姻轻易遭遇挫折的。");
			result.append(this.seperate);result.append(this.seperate);
		}
		
		if( allShensha.contains("红艳")) {
			result.append("红艳：主人风流多情，好色，女命犯之尤为不宜，贞操观念淡薄，容易有外遇，或婚前失贞等事发生。命见红艳煞不见得有多漂亮，但男女情爱方面把控的不是太好，容易有桃色纠纷。多烂桃花，是婚煞的一种。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("劫煞")) {
			result.append("劫煞：灾、波折、阻碍、执拗的凶神符号，假如出现在命局中，又为忌神，主人运势波动不稳，易遇突发性灾祸，或者突然意志消沉，不思进取，事业发展不顺等等。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("亡神")) {
			result.append("亡神：亡神处生旺状态，为命中之喜神，且与其他吉神同柱，则主吉，命主面有威仪、足智多谋、处事严谨、断事如神，是一个真人不露相的人。如果亡神处于死绝状态，为命中之忌神，且与凶神同柱则主凶，其人心性难定、气量变小、事难如愿、脾气粗俗，刑妻克子，易遇官讼牢狱之灾。在命宫、身宫主一生贫困，做事不顺利；在日支克妻，在时支克子。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("灾煞")) {
			result.append("灾煞：该煞又名叫做“白虎煞”。白虎主血光之灾，故该煞出现多主血光横死。如在柱中临水火之地，防止火烧水厄之灾;临金木之地，防止棍棒刀剑之灾;临于土支，防止跌打损伤，以及瘟疫之灾。如柱中有天德、月德、天乙贵人等吉星出现，不仅无灾，反主有权威武职，灾煞星喜见官星、印绶，以生旺为吉。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("孤辰")) {
			result.append("孤辰：主独立、孤独、固执、自立之星。夫妇缘浅，或聚少离多，男克妻而女刑夫，祸轻者夫妻不和时有争纷，更遇恶煞主生离死别，男女终身难嫁娶。如果一个八字有\"孤辰\"及\"寡宿\"星，代表孤身独行，喜爱宗教活动，甚至\"出家\"!");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("寡宿")) {
			result.append("寡宿：主孤寡，通常喜欢胡思乱想，个性郁闷，消极悲观，让人不悦。不利婚姻，晚婚或离婚，女命忌寡宿星。如果一个八字有\"孤辰\"及\"寡宿\"星，代表孤身独行，喜爱宗教活动，甚至\"出家\"!");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("十恶大败")) {
			result.append("十恶大败：十恶，意思是不赦重罪，大败，意思是表示精光、消减，是一种极凶的征兆。命中带有十恶大败是属于无禄之人，命主一生工作难于稳定，投资理财也常常是竹篮打水一场空。虽然赚钱不多，但花钱却不手软，捉襟见肘是常有的事，所以经常拆东墙补西墙。多数不会持家，花钱如流水，仓库金银化为尘。犹如无源之水、无本之木，没有资本很难成事。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("阴差阳错")) {
			result.append("阴差阳错：太过与不及、男女不和的意思。一般带阴差阳错者在谈恋爱时就很难遇到正好合适的人，结婚后也容易发生两家不合的事情。做事往往坐失良机或功亏一篑等。事与愿违，是阴差阳错最好的解释。恨很多事情的发生，总是来得莫名其妙，也发生得无能抗拒。此煞影响人命最大的是在于人际关系的变坏以及钱财的虚花耗费，很多人不论如何努力，就是很难达成心中的理想，好的运气变的不怎么好，坏的运气却显得特别坏。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("元辰")) {
			result.append("元辰：也叫“大耗”，各种琐事是非不断消耗命主，内心难以平静。一生最怕情事桃花或酒色之灾，或见无妄之灾，官司、牢狱之灾亦或有之。如物当风，摇动颠倒，不得安宁，没有内忧，必有外患。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("勾绞")) {
			result.append("勾绞：命犯勾绞煞则容易与人起争执，常表现为是非不断，口舌风波。常被人暗地里算计，嚼舌根等。一生之中人际关系很不好，与人相处意见很难偕同，经常会有无礼相向的事情发生。勾绞犯命，对感情婚缘也非常不利，争吵、相害、怨恨等现像是常有的事。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("孤鸾")) {
			result.append("孤鸾：\"孤鸾煞\"主要论男女婚姻不顺之事，所谓\"男主克妻，女主克夫\"。女命犯孤鸾煞，性情桀骜不驯，做事有板有眼，说话有理有据，一副巾帼不让须眉之相，男人见之心存忌惮，唯恐避之不及。女命带孤鸾煞主克夫，主再嫁，主守寡，若不离婚，夫必先亡。");
			result.append(this.seperate);result.append(this.seperate);
		}
		if( allShensha.contains("流霞")) {
			result.append("流霞：轻者皮肉伤折血光之灾，重者会有生死意外之祸。血煞犯命的八字，一生很难逃过血光事件发生；只是有轻重的区别。命犯流霞的人，最怕命中带刃，其灾厄会更加加重。女命容易有产厄、血崩之灾，生产必须注意大量流血的事情发生。");
			result.append(this.seperate);result.append(this.seperate);
		}
		
		result.append(this.seperate);result.append(this.seperate);
		
		return result.toString();
	}

	/**
	 * 贵人，恶人分析
	 * 
	 * @return
	 */
	public String goodEvilAnalysis() {
		StringBuffer sb = new StringBuffer();
		String str = String.format(
				"命主身边人八字【%s】旺，为命主贵人，生旺命主命格，有助命主事业、财运。身边人八字【%s】旺，亦有助命主，可为命主带来好运。此两种命格之人，命主可放心交往，亦可做合作伙伴。若身边人八字【%s】旺，则克命主命格，须远离，此乃天生之敌。"+this.seperate+this.seperate
						+ ""+this.seperate+this.seperate + "另外，与命主生日相差6个月的，为命主命中小贵人，对命主运势有帮助。故，建议命主平时多记住身边人的生日，以便在必要的时候，能找到贵人相助，逢凶化吉。"+this.seperate+this.seperate,
				useGod, loveGod, hateGod);
		return sb.append(str).toString();
	}

	/**
	 * * 生肖流年吉凶：
	 * 
	 * @return
	 */

	public String shengxiaoJixiong() {
		StringBuffer sb = new StringBuffer();
		
		switch (shengxiao) {
		case "鼠":
			sb.append("鼠人逢鼠年，其年财利正旺，事业高就，多有一鸣惊人之举。年中如遇丧葬之事，当有小病。本年之末，有极富刺激之事发生。少出远门，或可防止发生。"+this.seperate+this.seperate
					+ "鼠人逢牛的，其年运平平，然平中有顺。自己多不见利，亲友多有所获，故应敛气聚神，稍安勿躁，一年平顺，光明欣然，却也小的挫折于岁中，阴气临身，宜居家养性，可免晦气降临。"+this.seperate+this.seperate
					+ "鼠人逢虎年，其年事业多方兴起，则多有跃马征途，四方奔忙，多为他乡之客，形单影只，志在利名。如看淡名利，少有所获，适可而止，则大吉大利；若操之过急，只为争名头利，恐有兴尽悲来。画虎不成反类其犬，身体有损，合家不宁。"+this.seperate+this.seperate
					+ "鼠人逢兔年，吉凶互见，未婚男女，多有婚恋之事始起，然而暗结心中，多不言语。已婚男女，有喜临门，添人入口，然有夫妇反目之象。每有烦乱，家内家外，一团茅草乱蓬蓬。不是女扫男，便扫女。恶运不过，可成灾祸。小不忍则乱大谋，口舌是非由此起。其年应多行善缘，定可一帆风顺，只吉无凶。"+this.seperate+this.seperate
					+ "鼠人逢龙年，其年财利顺风，事得其成，事有其新。应有鳌头独占，金榜题名，但适阴气临身，好事只好待来年。其年当有小人后伺，交友千万小心，不慎反目成仇人。要防官府讼事，本年另有头晕之病，但无大碍。"+this.seperate+this.seperate
					+ "鼠人逢蛇年，流年不顺，多有病星暗罩，破财之事时有发生。幸有月德照临，否则灾祸连连，破家亡身，年中多向寺门行，少进名利场，则可逢凶化吉，减祸免灾，祛病保身。所幸亲友多有吉事，连及自身，与觉心意平顺，精气爽身，平心静心胸一年。"+this.seperate+this.seperate
					+ "鼠人逢马年，其年多交桃花运，十八男女结情恋，三十男女情外情。其年必常走花街柳巷，门外纷纷多作风骚子弟。无论男女，均有破财，大有牢狱之灾，小则破财伤身。然而可有逆顺之道，便是多奔事业，进取功名，桃花运下，无往不利，战无不胜。否则泪珠不干，枝节横生。"+this.seperate+this.seperate
					+ "鼠人逢羊年，其年大顺，凡事业成就，凡谋皆吉。偶有不平之事，终成幻景，顿失烟云。年中有小挫折，然而正气浩然，小足为患。本年口舌争夺之事频繁有，多加小心在意。顺势之中须牢记，凡事均有三分阻，切莫得势不饶人。故应见好就收，满足常乐。"+this.seperate+this.seperate
					+ "鼠人逢猴年，其年财势中平，事业平中有稳升。交友切记重信义，切勿断情绝义，以免反止为仇，种下来年祸根。与人为善，破财事小，失友事大。否则感情破裂。双双苦恼。年初有小人窥视，多加小心，岁末近鸡年，凡事多顺，应抛弃顾虑，奋起争夺，多有成功之望。"+this.seperate+this.seperate
					+ "鼠人逢鸡年，其年瑞霭盈门，满堂吉庆。不是多福多寿，便是多子多孙，最为下者，也有小财自上门。可作高户之婿，或成富室之媳。宏图大展，乐而忘忧。其年多应稳中求进，亏勿自得忘形，否则小见口舌词讼，大有脓血之灾，大吉大喜，他为子虚乌有。"+this.seperate+this.seperate
					+ "鼠人逢狗年，其年名势有进，财运平平，多有防不胜防之事，偶有暗中算计之行。当防疯猴，危地高山勿近，岁末有好事相诱，自己尤需把握分寸。心静神佳，可以力争，多有不虞之吉事，大有收获。若气败神虚，精力不济，切勿出门，否则漂零远方，闻报丧门，伤心苦恼，赔了夫人又折兵。"+this.seperate+this.seperate
					+ "鼠人逢猪年，其年天星高照，有进少出，吉多于凶。只是其年有病星影射，身体你佳，进取之时，多加小心，否则得不偿失。其年始可攻，岁末宜守。 ");
			break;
		case "牛":
			sb.append("属牛人逢鼠年其年运行正宫，诸事吉祥，虽有浮现之病，不药自愈，每每逢凶化吉，年中有阴人阻挡，小有波折，此是如以退为攻，一月之后，阻碍自消。"+this.seperate+this.seperate
					+ "属牛者逢牛年其年运势平淡，无功名，少财利，，办事多方受阻。伤心之事恐有，一年事业无聊。虽有敛势之年，但经心经营，毅劳穷追，也许有些收获。"+this.seperate+this.seperate
					+ "属牛人逢虎年其年吉多凶少，家中有迎亲嫁娶之事。岁末晦涩，找事多成泡影。利诱名引之机缘进有，宜择其善者而行之。今年财运一般应加倍努力宜多种经营，薄利多销。"+this.seperate+this.seperate
					+ "属牛人逢兔年其年逆悖之事多，祸起萧墙之内，故攘外必先安内，要点破机关，看破源头，忍气饶人祸自消，静心修养，一年平安。今年财运不理想，切忌大量投资，以守旧为好。"+this.seperate+this.seperate
					+ "属牛人逢龙年祸福平分，吉凶参半，欲动不得，终是无功无绊，只谋不遂。其年要顺应自然，以退为进，该有则有，该无则无，不愁衣食，顺当平安。全年以小心稳重为好，求财应等时机。无事多拜佛行善，最好在空门求一平安符带在身上。"+this.seperate+this.seperate
					+ "属牛人逢蛇年其年财源大旺，但单月出生的人，无福消受；双月出生的人也应善取时机，本年口舌是非多见，多为交友不慎，有口难辨，欲哭难明，若要化解可家里养三只乌龟。"+this.seperate+this.seperate
					+ "属牛人逢马年其年桃花运出现，当招恶病之人，破财代药，无怨无悔。岁末有贵人相助，一帆风顺，合浦还珠，笑口常开。"+this.seperate+this.seperate
					+ "属牛人逢羊年其年多破财库，当奋起自强，不拘小节，害人之心不可有，防人之心不可无。开岁不利，岁月阻碍之中渐呈光明，岁末开运，宜投资创利，属牛人在羊年若身上常戴金色饰物工玉圈，可趋吉避凶。"+this.seperate+this.seperate
					+ "属牛人逢猴年其年大吉，事从新尚，每试多中，不是作福作寿，便是迎亲嫁娶，添子添孙，在赐之喜，无可阻挡。须防兴尽悲来，自得忘形，满足常乐可也。喜庆之年，财运大好，应多求菩萨保佑在家中堂屋上方设一财神菩萨方位天天上一柱香，以求财源广进。"+this.seperate+this.seperate
					+ "属牛人逢鸡年其年凶多吉少，凶在岁中天灾人祸，如广结善缘，可保岁月平安，心平气和度岁末，其年求财不利，忌赌博和人打架，无事多进空门，求菩萨，保佑平安，在家中堂屋上方挂一副观音菩萨，逐日以香供之，使之感应生灵。"+this.seperate+this.seperate
					+ "属牛人逢狗年其年事业顺利，当广觅机缘；财不聚守，面向桃花，但一年吉多凶少，尚称快乐。全年应以精打细算为好，条件好转可再投资创业，但绝勿好色，否则自毁前途；属牛人在狗年佩带龙凤玉佩，可大吉大利。"+this.seperate+this.seperate
					+ "属牛人逢猪年流年不利，办事无成，进退有阻，幸喜岁末有贵人暗助，托平生勤勉之行，也不灾祸，在猪年固然财运无望，但只要勤劳俭朴，也会有积蓄，本年切忌赌博，若不信必定后悔莫及，本年应交良朋益友，多结识贵人。 ");
			break;
		case "虎":
			sb.append(
					"属虎人逢鼠年其年不利，本年犯天狗，灾祸时生，多逆少顺，其年多守家门，专利本业，云灾减祸，多往空门走，少向恶道行，一年也有小得，其年求则无望，应多干实事，切忌赌博和围观斗殴，以免发生意外伤害和破财，在今年你最好带一块有佛图样的玉佩，多交猪年朋友，以显吉祥。平时多求菩萨保佑，可逢凶化吉。"+this.seperate+this.seperate
							+ "属虎人逢牛年其年大吉，自有好事临门，机不可失。岁初有黑牛接道，多加小心，此一过就无大碍，其年虽有头疼之病，天赐平安，可不药而愈。在你的身上今年应常配戴玉或金属圈手镯及与火有关的红色，以击退黑牛，以使你的运势更旺。"+this.seperate+this.seperate
							+ "属虎人逢虎年其年黑虎当道，多凶少吉，应避披麻执孝之事，伤财神散，浮沉不定，临事不如意，烦苦恼，在今年你必须随身携带一块有龙的玉佩，可逢凶化吉，遇难呈祥。"+this.seperate+this.seperate
							+ "属虎人逢兔年其年月德高照，即使有灾难，也能遇救。切记凡事适可而止，多留从余地，有时思无时，吉祥之年，可尽力施为，机不可失，时不再来。今年你运势大吉，应捉住时机，大展鸿图，可投资创业，但切勿自得忘形，应行善事。"+this.seperate+this.seperate
							+ "属虎人逢龙年其年大凶少吉，大起大落，则利欠佳，百事难兴。本年应刻守本业，少进多守，可保一年平稳；亦可奋力外行、择机进出，事业有成，可克凶趋福。在今年你最好在身上戴一块龙凤图样的玉佩。"+this.seperate+this.seperate
							+ "属虎人逢蛇年其年运势稳中上。虽有才能双全，多有支出而无进取，劳而无功，交友谋财有波折；但有利之势仍存，重点进取，可望成功。"+this.seperate+this.seperate
							+ "属虎人逢马年其年万事亨通，有机会高升，则进如水；只是须防小人。求财问名，凡事如意，应留意急流稍缩，否则只恐物极必反，福缘成空，在今年您的运势很好，可向外投资创业，但交朋友一定要小心，切忌好色，若不信劝告，必定后悔莫及，今年在你的命根里亦有阴相，只是财高灾趋吉。"+this.seperate+this.seperate
							+ "属虎人逢羊年其年月德照临，虽有小耗财，小病痛，但并无大碍。其年出马他乡，情利之诱，唯天时即合地利，只欠人和；本年宜息事，今年您的财运不错，应多交良朋益友，少结冤家，多结识贵人，以得他们的匡助，在爱情上最好就地取才，牛郎织女，对你根本不合适，办事以忍和稳为妥。"+this.seperate+this.seperate
							+ "属虎人逢猴年其年寅虎申猴相冲相克，有时意外，年初小人作梗，兴事难成，岁中辛劳，年终虽有财，但找事亦艰难，本年之解，宜在东部录缘，从今年的整个运势看，你只能在艰难中前进，应多干实事。"+this.seperate+this.seperate
							+ "属虎人逢鸡年其年紫徽星高照，凡事逢凶化吉，只是运势太旺，恐有不测之疫，故应见好就收，凡事有分寸，取财亦有道，当无大碍，可佩戴一符以护身。"+this.seperate+this.seperate
							+ "属虎人逢狗年其年万事亨通，无往不利，无事不顺，出走四方，财喜并至；切忌劳心伤神过度，以免虎落平阳被犬欺；出门在外应少管闲事，多结识贵人，办事稳重，保重身体，就可无忧。"+this.seperate+this.seperate
							+ "属虎人逢猪年其年运势稍退，当心疯犬伤身；起伏明显，进退有方，小心稳重，自可岁终。今年在农村的人，要留意疯狗，生意人应经精明起见，进方，可多拜神菩萨。 ");
			break;
		case "兔":
			sb.append(
					"属兔人逢鼠年其年喜气满门，福星临门，诸事吉祥，岁中稍有波折，岁末好事连连；家中时有小口舌，然而命在正宫，过期即泰矣。今年求财大利，下半年更加火红，应抓时机发财，但要留意别人眼红而带来的麻烦。"+this.seperate+this.seperate
							+ "属兔人逢牛年其年诸事不吉，重者拖麻拽布，留意身体，一旦不测，恐留百日之患。岁末恶运稍解，事有所成，抓紧时机，也可喜气洋洋，祸福相抵。今年运势对你不利，意外之事时有发生，切忌冒险和盲目投资。"+this.seperate+this.seperate
							+ "属兔人逢虎年其年病星上照，宜留意健康；官符时有是孝；男女各自扫财，凡事小心，岁中阴气重，不宜远行；冬至之后厄势去、好运来。本年过势还很糟糕、应有耐性，待好运到时再投资创利一定能平安发财，闲时多进庙，求菩萨保佑。"+this.seperate+this.seperate
							+ "属兔人逢兔年其年必高迁。开谋成就，百事顺通；生意兴隆、名利双全；天道公正，不侮不倚；岁初运势平缓，宜琮不进，本年是您发家致富的大好时机，应牢牢把握，可多项或大批投资创业，但平时应多行善谢佛，切忌为富不仁。"+this.seperate+this.seperate
							+ "属兔人逢龙年其年太阴星高照，东跑西行，时有晦卸之感；明哲保身，以退为进，年初择机行事，年终伺机而行，可有意外之小攻。本年求财亦不利，应格外小心，千万不可惹事生非，出门在外，人单力薄，勿管他人闲事，免得节外生枝。"+this.seperate+this.seperate
							+ "属兔人逢蛇年其年事业波折四看书，穷于应付，劳心伤神，有小破财，大暑之后，挫势顿减，渐生起色，意外之吉横空过，事业一风顺，下半年是你发财的仙会，千万不可错过，无论投资什么，只要用人得体，生意还是兴隆。"+this.seperate+this.seperate
							+ "属兔人逢马年其年代喜星将至，年初小挫，三月之后扫出门。女人情缘时生，男人命相桃花；只恐兴尽悲来，财缘有损，一岁之中，难免时有小麻烦。所以今年切忌沾色好赌。否则定难逃过劫数。小则破财消灾，大则有坐牢断颈。"+this.seperate+this.seperate
							+ "属兔人逢羊年一年之中有造就之机，但经营不顺，求财多阻。年终事业可成，切忌莽撞础门，进犯宫鬼小人，一年辛劳获利未几。平心静气，可成一事。本年你与金钱没多少福份，应勤俭持家，平时多结交贵人，以得贵人相助。"+this.seperate+this.seperate
							+ "属兔人逢猴年其年困病重重，虎口余生；一年不顺，幸有月德照临，困中有解。女子多忌口舌，男子留意财色之灾，静虚守意，安身本业，一年也还平安。本年凶多吉少，多进佛庙，多求菩萨保佑。"+this.seperate+this.seperate
							+ "属兔人逢鸡年其年必有一难非财即身也，年中须防小人，岁末自然太平日，本年有劫数，灾难降于你，应有足够的心理预备，意志应坚强，多靠亲朋好友匡助，闲时多拜观音菩萨，救苦救难，并许一心愿，以示诚心，年过还愿方可平安。"+this.seperate+this.seperate
							+ "属兔人逢狗年其年紫徽星商照，经营等措，多有成功，切忌贪而无厌，好大喜功，而至一年福份，无缘收受，今年运势大好，又是一个发财的好机会，应一心一意干事业，在金钱面关也应满足常乐，对手下人员要多加照顾，发财之时应以仁义为宗旨，定能得天时地利人和。"+this.seperate+this.seperate
							+ "属兔人逢猪年其年白虎临门，天灾地福，小人作梗，若向东方求缘，可转悲为喜，化祸为福，成大事也。今年应尤其留意意外事故带来的灾难，或人为的伤害。也门留意安全少管闲事，并且要处理好亲人、朋友、邻居之间的关系，冤家宜解不宜结，千万不要和不三不四的朋友来往。平时多拜东方神圣，以求平安。");
			break;
		case "龙":
			sb.append(
					"属龙人逢鼠年其年财利正旺，岁初即逢喜，岁末有余粮；广通财源，经营顺利；一处之中，终有破财失利一中事；留意立夏前后，当年7月份以后应多加留意防止意外事故的发生，无事应多进空门求化佛保佑，切忌赌博。"+this.seperate+this.seperate
							+ "属龙人逢牛年时其年天福星高悬，诸事皆吉；福德之神临命，虽有阴人小口舌，过期即安，何须自叹有纷争。岁末多向西北求福，可以来年守安宁，适时可投资大生意，但应多了解信息，稳重办事。"+this.seperate+this.seperate
							+ "属龙人逢虎年其年多有波涛起伏，经营筹谋、远走他乡，劳苦奔波，虽有旺盛财禄却辛劳。宜以静制动、以逸待劳；可投资生意，多方发展。"+this.seperate+this.seperate
							+ "属龙人逢兔年小病常有，浮沉不定。其年多变，流离颠沛，精神你爽；岁末事喜有小财，不枉一年辛劳，虽有不利，也算平安。其年示财不利，应安份守已为好，切忌赌博。"+this.seperate+this.seperate
							+ "属龙人逢龙年本命相搏，起伏参差，其年深造有机，学艺聪敏，事半功倍。岁中有碍，多加小心；本命相冲、忿气伤神，最宜留意。多见事喜庆可与之相冲。"+this.seperate+this.seperate
							+ "属龙人逢蛇年太阳星高照，凡事任君行。但时有晦气透出，男有血光之灾或意外伤亡帮，宜多人同路，要多加小心。留意滋补身体，增强体质，多进空门拜菩萨保佑。"+this.seperate+this.seperate
							+ "属龙人逢马年诸事多不顺，多有小挫折，灾祸重起。岁末稍缓，但不可大意。月初拜观音、祈福免灾，方可全年平安。时有倒霉时，今年切忌眼红别人发财，应沉住气，待时机好转再集中精力大干一番事业。"+this.seperate+this.seperate
							+ "属龙人逢羊年其年有喜有忧，喜本年有外财，优伤身退财，小心四邻亲朋中有小人挑拨离间致使你不分青红皂白，鲁莽行事。今年你必须冷静处理问题，是非分明才可保平安，求财应格外小心。"+this.seperate+this.seperate
							+ "属龙人逢猴年其年大孝发动，然有天星在侧，苦乐参半。岁初宜早定全年大事，岁中宜静守，岁末稳中求财，可有意外财喜，忌贪婪，勿躁进。平时多结交贵人，见机行事，定保平安。"+this.seperate+this.seperate
							+ "属龙人逢鸡年其年诸事顺利，满门吉庆，同德照临；但时有微病、破小财；如心平气和，当万事昌顺。庆捉住难得的机会大干一番，事业可向外发展，平时多行善积德，定可平安发财。"+this.seperate+this.seperate
							+ "属龙人逢狗年其年口舌是非难免，凡谋欠利。勿要强取豪夺，定可太太平平度光阴。争取财源广进，但忌歪门邪道，或不择手段。"+this.seperate+this.seperate
							+ "属龙人逢猪年其年紫徽星同挂，诸事顺利，万事如意。岁初有天狼窥视，小心在意。");
			break;
		case "蛇":
			sb.append("属蛇人逢鼠年满门吉庆，喜事临门，喜结良缘，事业有成；但勿意气用事，若不自得忘形，将无优无虑。求财有得，但切忌犯色，凡事应以大局为重，定可平安无事。"+this.seperate+this.seperate
					+ "属蛇人逢牛年运引恶虎，多生意外之事；宜小心谨慎为妙。幸喜只有白日之灾，发生在春末夏尾，立秋之后，危运随暑消除，人生安宁，百病不生。本年运势因恶人当道，恐有意外人身伤亡和大难降临，属蛇人今年必须留意安全，无事少出门，尽可能地不要多管闲事，多行善事，以还应平安。"+this.seperate+this.seperate
					+ "属蛇人逢虎年口舌多起，是非连至，生意无聊，找事受阻；幸有福星照临，可恶消除，有意外之收获，虽在视命，但不可造次。应有宽宏大量的心地，得饶人时且饶人，冤家宜解不宜结。"+this.seperate+this.seperate
					+ "属蛇人逢兔年运走沉沙，东奔西走，只为利禄功名；犯天狗星，晦气重重，喜天中口照，贵人暗助。岁末鸿运渐至，吉事初生。今年求财艰难，理需意志和勇气。"+this.seperate+this.seperate
					+ "属蛇人逢龙年一帆风顺，出门逢源，经营得利，岁杀病符仍不能免；幸有天喜星化解，由凶转吉，今年发财有望，事业有成，但遇事应沉着冷静，对命中凶数应求佛化解。"+this.seperate+this.seperate
					+ "属蛇人逢蛇年运到本命，多有悖逆之事发生，交友留意，切记知人知面不知心，年末犯太岁，头晕不乐，浮沉不定，会有失败之不利，所以岁末应在事业上有所守旧，进退应随机应变，方可平安。"+this.seperate+this.seperate
					+ "属蛇人逢马年晦气沉沉，精神不定，另主他乡危，女主产后疾，乐善好施，转危为安，败中取胜，岁末放胆出击，可有收获。命中凶数多。平时应多留意，凡事小心应付，切忌贪婪。"+this.seperate+this.seperate
					+ "属蛇人逢羊年岁初有阻，岁中始开，岁末平稳，得失兼半。运势有得有失，事业成功了，应多抽时间陪家人，正确处理好朋友与亲人之间的关系，达到天时、地利、人和才能使你大展鸿图。"+this.seperate+this.seperate
					+ "属蛇人逢猴年逢太阴星，时见女贵人，有缘消受，求名求利；无缘得福，室迩人遐；如有歹意，必有克制；人随天意，必须保持冷静，一切由命运解决。命里有时终归有，命里无时莫强求。"+this.seperate+this.seperate
					+ "属蛇人逢鸡年事业顺心，财丰利足，但岁末有小人，暗怀鬼胎，阴气腾腾；但最后云开日出，光明时有，今年财运虽好，但破财之事也常有，切忌犯色戒，平时应多结交贵人。狐朋狗友千万不能共事。"+this.seperate+this.seperate
					+ "属蛇人逢狗年红弯喜动，把握时机定有所获。虽有小病及小耗财，尚有月德照顺成祥，有离家远出之机，应捉住机运向外发展事业，多结交良朋益友，用老实可靠之人，钱财乃身外之物，应以小恩小惠，羁縻人心。"+this.seperate+this.seperate
					+ "属蛇人逢猪年发如猛虎，但应适可而止，见好即收；求求名求利者，多有一失，切忌凡事过分，今年求财易得，切忌贪婪，以及为富不仁。求名求利时应多行善于事，待人以和为贵，方可财源蕃庑。 ");
			break;
		case "马":
			sb.append(" 属马的人逢鼠年其年运行偏穴，天空月空，丧事全来，口舌是非常有，词讼辛难恐有现，幸灾祸仅百日，犯在岁中；事后太阳星高照，得贵人相助，悲极乐来。"+this.seperate+this.seperate
					+ "属马人逢牛年其中紫徽星高照，诸事如愿，龙德在身，逢凶化吉，但年中恕有晦气生，天难地变，家远欠通，宜小心从事。静中应需动，大吉大利，求财应根据情况，见机行事方能平安发财，忌赌博和好色。"+this.seperate+this.seperate
					+ "属马人逢虎年其年财运不顺，多有破财之事，灾难之中，交友应留意。几事不顺守为攻，事业难成命不由人；浮沉不变，逆多顺少，但终无大害上门，全年应以守旧、稳重起见，尽可能地不和狐朋狗友往来，以免受连累。"+this.seperate+this.seperate
					+ "属马人逢兔年运逢天喜星驾临，笑口常开，事顺财足。侧染桃花，寻花问柳，心猿意马。不虞披麻执孝，口舌重重，凡事小心为妙。福星照命，百事可了。却忌沾逆贪婪，留意处好人际关系。"+this.seperate+this.seperate
					+ "属马人逢龙年其年运势起落，浮沉不定，稳中见杀。犯天狗星，吉少逆多。需有税意精力，功在一处，专守一事，意大有所成。为阴谋诡计天狗星相害。"+this.seperate+this.seperate
					+ "属马人逢蛇年其年玉体不康，病患常有。东奔西跑，不得其安。其年交友不得真朋，凡谋不利。岁末宜多向善，多施少取，平时应多向空门行走，求菩萨保佑。"+this.seperate+this.seperate
					+ "属马人逢马年其年事业发达，财源广进，也有提升高就之机。但其年犯太岁，必有精神不爽；宜避丧葬，本命虽无碍，当避者亦不宜强见。应在身上常带一块龙凤玉佩，以避太岁。今年可多方发展，并且生意兴隆。"+this.seperate+this.seperate
					+ "属马人逢羊年其年太阳星高照，时运多通。岁初小有收获。年终可有事成功。然岁中遇事不顺，多有阻难，部分是险中求财，要有分寸，切忌贪婪，不守天职。"+this.seperate+this.seperate
					+ "属马人逢猴年流年化驿马，运势潮起潮落；势力高壮，凡谋有成，失者亦多。只恐过犹不及，力不能全，然大体说来，吉多凶少。今年求财不利应以小本生意为主。"+this.seperate+this.seperate
					+ "属马人逢鸡年其年运势稳顺，时有阴人拜见见，但时过自消。岁初开门见喜，一年可有数次机缘，多为财喜。应捉住时机，发展生意。但必须要求会理财，聘用忠诚能干的人为助手，定可大展宏图。"+this.seperate+this.seperate
					+ "属马人逢狗年其年运交华盖，连年升迁，考试有取，但事业之成，多有波折，却可转财缘。只要把握运势，一年到头，必定有所成就，波折之中应有勇气、意志。"+this.seperate+this.seperate
					+ "属马人逢猪年其年运势中下滑，幸有月德照耀遇困得开，时有名利之事，避之则吉。求财不利，应广开门路，多了解信息，没有80%以上的把握千万不要投资，切忌炒股。本年应结交良朋益友，若有贵人相助定能发达。");
			break;
		case "羊":
			sb.append(
					"属羊的人逢鼠年其年正走财运，生意兴隆，事业有进，但年运沾桃花，不病小耗难免；佳运之下，常思恶运，岁末小心谨慎，勿使一年辛劳化东流。若保全年顺畅如意，可在身上戴一块观音菩萨的一佩，定能逢凶化吉。"+this.seperate+this.seperate
							+ "属羊的人逢牛年其年运势起伏，吉凶参半，岁中诸事欠吉，有可能破财消灾，是非口舌常有，家庭不和，万事应以忍为上，以和为贵。一年之中，也可以平平安安，不愁衣食也，若保平安，心中常记忍字。"+this.seperate+this.seperate
							+ "属羊的人逢虎年其年运势上扬，难有阻挡，但须防小人，若不严加防范，纵有佳运，也难保凶多吉少，若羊入虎口，更是虎口余生，在虎年你更应保持勇气和谨慎，千万不要和不三不四的人来往，切忌好色、赌博，最好在身上带一块金属虎牌。"+this.seperate+this.seperate
							+ "属羊的人逢兔年其年运势趋偏财，事业顺遂。诸凡小顺，但也有波折。破财虽无，功名沉浮不定，有意外之灾；口直仗义，取物勿贪。家中若有不快和令人烦恼的事出现，在兔年应该保持冷静出门，打架头殴不要围观。"+this.seperate+this.seperate
							+ "属羊的逢龙年其年恰是劫财，忙来忙去一场空，当初奔忙，岁中偶有口舌之灾，但凡凶来，神速星将至；善推机缘，绝处逢生，也有意外惊喜之财，切忌赌博，否则将陷入困境。在龙年你最好安守故常，多拜财神菩萨。"+this.seperate+this.seperate
							+ "属羊的人逢蛇年其年正走正印，事得其成；骑马奔命，凡谋必就，诸事如意，应达之目的，但不可守财失义，否则天意不临，也不会有好报，在蛇年你应多结识贵人和巴结上司，定能升官发财。"+this.seperate+this.seperate
							+ "属羊的人逢马年其年正走偏印，是非中只为功名起，口舌仅为财利生；但否极泰来，人之常运，诸谋迪吉，正事可通，一帆风顺，大吉大利。"+this.seperate+this.seperate
							+ "属羊人逢羊年时及本命，应邮深适之机，文思大进，只恐身心不遂，又见病况，需时时提防头晕失兆，可使一年平安，是年应多拜佛求平安，保健康。"+this.seperate+this.seperate
							+ "属羊的人逢猴年其年运走武印，困顿全消，太阳高照四云有利也；必见门庭之喜气，凡事大通，然不可自得忘形，命里有自当有，命中无莫强求。自得之年应多行善谢佛，增加就蓄。"+this.seperate+this.seperate
							+ "属羊人逢鸡年其年外处多财，宜外出来补，但其年凶多吉少，凡事适可而止，一年太平。"+this.seperate+this.seperate
							+ "属羊的人逢狗年其年劫财流年，生意久隆，不宜外出和做生意，以守为攻是上策；应处处谨慎，万事留意，也有云开见日之时。在家的大门口放一只石狮可得平安。"+this.seperate+this.seperate
							+ "属羊的人逢猪年其年运走伤宫，山人官鬼多，升官无望，交友失利；财神阔别，经商艰难，虽有小财但花费九牛二虎之力，在猪年你会有经济和法律方面的麻烦出现，身体也不很好，今年你千万不要冒险，事事应小心谨慎。实事求是，常把老虎图像和在身上也可化凶求吉。多思进取，岁末事有所成。");
			break;
		case "猴":
			sb.append(
					"属猴人逢鼠年，其年经营兴隆，财丰利足，兴力事业，多有所成，本年有诉讼之事，带来是非口舌。职位高尚，有贵人相助，遇事必思，诸事逢吉，放手为之。多结交良朋益友，多投创业，但要留意突发事情，解决问题应以冷静为好。"+this.seperate+this.seperate
							+ "属猴人逢牛年，其年天喜星临，出外营谋，用法照临，多是一帆风顺。岁初有小梗，必塾人内扰；岁末有病，小耗财；但一张一弛，运作之道也。小心衣食，平安无恙也。"+this.seperate+this.seperate
							+ "属猴人逢虎年，其年运带驿马，吉凶参半；浮沉不定，婚姻困多喜少，纷争起于萧墙之内，不见宁静时。耗神损气，恐伤元气，四时之中，秋气肃杀，外出远足多在意。"+this.seperate+this.seperate
							+ "属猴人逢兔年，其年紫徽星高照，营谋多利，凡事多利。虽有到难之事，累失难免，灾难小有，不足为患，本年多宜外出寻缘，春气大盛之时，远景尤好，以逸待劳，可免病灾。"+this.seperate+this.seperate
							+ "属猴人逢龙年，在龙年里，属猴的人会一直顺利，财运也会很好，诸年的努力，都会见到成效，学业上也会颇有小成，但在交友的问题上应该在意，小心被小人陷害。"+this.seperate+this.seperate
							+ "属猴人逢蛇年，在蛇年里，属猴的人容易遭大病，甚至会重病不起，幸好及时得到治疗，终会化险为夷。"+this.seperate+this.seperate
							+ "属猴人逢马年，在马年里，属猴人有福星高照，运势畅顺，家中喜事重重，投资获利，财运极佳，会有多方面收入，但不可贪得无厌。"+this.seperate+this.seperate
							+ "属猴人逢羊年，在羊年里，属猴人的事业上会有所成就，几次的投资都会收到很好的效益，但要小心上当，由于有要想骗你。"+this.seperate+this.seperate
							+ "属猴人逢猴年，其年戏鸾发动，满门喜色，桃共生命，钱财严防，伤神晕沉头疼，时有小疾。"+this.seperate+this.seperate
							+ "属猴人逢鸡年，其年太阳星高照，勤勉得财喜，辛劳见功名，只是色气太重，虽有克制，劳心过重，秋后小有收获。"+this.seperate+this.seperate
							+ "属猴人逢狗年，其年不大吉祥，难于安身立命。万事从头起，十年根基动。时有晦气阴至，凡事小心在意，自会时来运转。"+this.seperate+this.seperate
							+ "属猴人逢猪年，其年太阴星高照，多有官符诉讼，破败当有，麻烦头疼之疾难免。幸有先法，阳有相助，吉凶平冲，亦无大害。 ");
			break;
		case "鸡":
			sb.append(
					"属鸡人逢鼠年，其年恰是食神，财库须动，用于喜色的多。交朋结友，不吝其财，损之耗之，但不是大破财，岁初有损，岁末得补，立秋之后，要伺机多进，因而可有小小节余。"+this.seperate+this.seperate
					+ "属鸡人逢牛年，其年正走偏印，有力率众进取，只是得名少财，虽浮沉不定，可得天主相救，还是浓血之灾须见，但无碍也。飞天胆大，有翼得之勇，若不能登台拜将，也会成人中之人。"+this.seperate+this.seperate
					+ "属鸡人逢虎年，其年运行正财，生意兴隆，财源蕃庑，凡经营求谋，势有过倍之利，时有小病小耗，并无大碍，时有小人忌之，此小破耗也。切忌柔可克刚。"+this.seperate+this.seperate
					+ "属鸡人逢兔年，其年运行偏财，经营，合股，不鸡而散，投契不成反蚀本；无不可示主，惟财不归。幸有祖荫，贵人相助，并承夫妻友人接应，不成大害也；本年应少存侥幸心，千万不可和别人合伙经营，艰苦创业定能平安发财。"+this.seperate+this.seperate
					+ "属鸡人逢龙年，其年运走正印，大权独揽，一鸣惊人，然而登台拜将，主谋带众，伤心劳神，神经难免刺激，可有小患也，宜于衣食住行多加小心；尤其终日运筹，小心劫财。"+this.seperate+this.seperate
					+ "属鸡人逢蛇年，其年运走正宫，流年大顺，可惜白虎破财，有名无利，一年清白，至有小破财，不在其害，总是吉多凶少，岁末成功，固然财喜横空过，本年却也不缺衣食。"+this.seperate+this.seperate
					+ "属鸡人逢马年，本年行七煞之运，外临太阳余蚋。本年凶吉参半，大落大起，时有意外之喜，突如其来之祸，却有喜事连连不绝。"+this.seperate+this.seperate
					+ "属鸡人逢羊年，其年运转正印，仕途坦荡，事业进步，大有权柄。女子悠悠情动，男子命坐桃花，声名既有，财禄出门，年初东风好运，岁中日中天行，岁末慎行。"+this.seperate+this.seperate
					+ "属鸡人逢猴年，其年运势大滑，经营无利，求谋有阻，并见口舌是非，病若流水，身心欠安，杂乱无章，千头万绪，幸月德晚罩，逢凶化吉，慎出远门，也是平平安安一生，今年求财无缘，免得白费力气，全年应以守旧、冷静为好。"+this.seperate+this.seperate
					+ "属鸡人逢鸡年，其年经营谋划不顺，事业有限，权柄可握，虽少财禄，却也以名相抵，官司是非不起，岁末有小获。"+this.seperate+this.seperate
					+ "属鸡人逢狗年，其年运行正印，步步高升，天德神星常照，四方求名利，有归无定日，出马他乡，当有闷心不乐之事。"+this.seperate+this.seperate
					+ "属鸡人逢猪年，其年伤官，难有驿马，出门要小心在意，凡事欠吉，意外这事而多生于天难。岁中有太阳西照，遇难有救，今年求财不利，做工还可，凡事小心为好。");
			break;
		case "狗":
			sb.append("属狗人逢鼠年其年恰逢财运，正因如此，奇离生祸之事时有发生；婚姻也可能出现危机，阴晴相间，时好时坏；故应小心留意，本年之中福祸参半，吉凶平分，平时应多留意突发事情。"+this.seperate+this.seperate
					+ "属狗人逢牛年其年运走失败，经营谋划有小人从中作梗，故岁运不佳时，办事不可急躁，说话多加小心，稍不留意即受非难，幸喜岁末有贵人相助，并多有情缘相合，本年您应少和别人合伙经商，当心受骗，闲时到空门拜拜菩萨、求财、求福。"+this.seperate+this.seperate
					+ "属狗人逢虎年其年偏走七煞之运，举事谋划大多难成。经营有风浪，交友又遇贼。其年只须忠心供职，少出偏激之道，也许平安无事；下半年有小财上门，留意机缘。"+this.seperate+this.seperate
					+ "属狗人逢兔年其年运行正宫，大吉大利；固然本年有盗贼暗算，但吉星高照于大体无。其年应居仁守义，以正道养家供职，切勿逸走偏锋，守恋桃花，而至功亏一篑。本年应多做善事，切忌沾色，平时多留积蓄，以备急用。"+this.seperate+this.seperate
					+ "属狗人逢龙年其年运走比肩，争财夺利之事时有发生，事业经营起伏较大，机缘与失意共存，好坏成败全在自身，运势只为中平。本年应小心守本，则可不少得益。从整年看，你的运势还是较好，把握时机可扩大投资创业。"+this.seperate+this.seperate
					+ "属狗人逢蛇年其年运得偏印，一官半职之人，则应清廉，少取不义之财；下半年左有紫徽高照，右有龙德保身；年中有词讼官司，但终有吉祥，一年洋洋，求财易得，但也易去，少和他人合伙以免受骗。办事稳重，适可而止，定保平安。"+this.seperate+this.seperate
					+ "属狗人逢马年其年运走正财，财利大吉，其年应留意身体，防止操劳过度。本年虽是吉祥之年，年终可有白虎小破，有些小损失，或有亲人、友人危难之事烦恼人，所以今年一定要处理好亲人和友人的关系，当人嫉妒你的财运，切忌贷款给亲朋好友，以免好心不得好报。"+this.seperate+this.seperate
					+ "属狗人逢羊年其年运势财劫，经营忙碌，获利甚微，口舌常起，独立支撑，身心有损；但福星高悬于岁末，事业得以稳定，故本年虽天德施于困顿，但少有横祸，无伤大体。"+this.seperate+this.seperate
					+ "属狗人逢猴年其年运行食神，宽裕自行，食缘不缺，多有小财临门。如欲谋大事，早撞奇站左道，则每每受阻。找事在人，成事在天，否则多方劳累亏损，而致奔波。"+this.seperate+this.seperate
					+ "属狗人逢鸡年其年运犯伤官，尽管事业有成，但损失伤体，而致身心受损。本年应顺其自然，立身守本，与财运无缘，平时应多拜菩萨，可保一年平安。"+this.seperate+this.seperate
					+ "属狗人逢狗年其年命里刚中偏柔，文思大时，华盖立位，如养家谋生，则宜以守为攻，良机自来，但时有小患来，平时应多加锻炼身体，多结交良月益友，定能处处逢东风。"+this.seperate+this.seperate
					+ "属狗人逢猪年其年命正走偏财，故本年不可因循守旧，固本守业，宜到处寻缘，，投资创业，天日有喜，太阳高照，只恐过犹不及。必须捉住大好时机，方能大展鸿图。");
			break;
		case "猪":
			sb.append("属猪人逢鼠年其年运走劫财，经营谋利，财来艰难，工作可做，但太阳星高照，遇难有救星，桃花之事，乐后晦气顿来，要多加小心才是。"+this.seperate+this.seperate
					+ "属猪人逢牛年其年运行正财，时来运转，财库顿盈，生意兴隆，财源蕃庑，夺利争财，机缘不中断，岁中有小阻，虽有小耗财，但无大碍。应多行善好施，忌为富不仁，只有天时、地利、人和才能永久昌盛。"+this.seperate+this.seperate
					+ "属猪人逢虎年其年食神降临，并见官府，意外之事多，凡事多不利，但见东南日照，运势有增，追求功名利禄者，可向此方行，天地轮转，可有意外之收获。"+this.seperate+this.seperate
					+ "属猪人逢兔年其年运行正财，转祸为福，财源足矣，在夏至之间有可能增官晋爵。岁末时防官鬼小人，免遭连累。凶时过后吉也，不妨广结善缘，多烧香拜见佛有利无害。"+this.seperate+this.seperate
					+ "属猪人逢龙年其年运行正官，必得主权；出马他乡，凡事有乐无忧，然有小病小破费，岁逢月德，名旺珍贵，爱虚荣，好豪华，慷慨好施，只怕名存财空。"+this.seperate+this.seperate
					+ "属猪人逢蛇年其年走偏财，驿马奔他乡，事业多思转向，投契取巧，定有所得，而后须防大耗欺其财，披麻服当见，宜小心谨慎。"+this.seperate+this.seperate
					+ "属猪人逢马年其年正行财运，财丰利厚，虽有天难伤害，沿有龙德紫徽二星保驾，遇难有救也。务必居安思危，否则来去空空，损了一年好运。"+this.seperate+this.seperate
					+ "属猪人逢羊年其年运走正官，流年有顺，造就在机，必得其权，多做少言，一年声名平安上进。但宜谨慎，少奔他乡，不谋转业，三思而后行。"+this.seperate+this.seperate
					+ "属猪人逢猴年其年运走偏运，岁动孝服，披麻哭声当见，是非口舌发生，三思而后行，生意平中有折，所幸恶运半载，岁末有获。岁初吉少凶多事万事不宜，应待时机。"+this.seperate+this.seperate
					+ "属猪人逢鸡年其年有动荡不安之事，财库有破，利路欠通，经劳谋划，多半不成，本年在名权不在财，今年求财不利但当官有运，应见机行事，多结交贵人，若得天时、地利、人和定能如愿以尝。"+this.seperate+this.seperate
					+ "属猪人逢狗年运起岁中，只因天时不吉，极难把握，幸而天喜佑临，转眠大醒必有一收获，不枉一年辛劳，吃苦刻苦是你今年求财的独一途径，虽无大由，但收裕不少，应树立信心和勇气，千万不能懒惰。"+this.seperate+this.seperate
					+ "属猪人逢猪年其年儿在岁，交友不利，事业不定，谋划不顺，凡事平常，本命使然也，朱吵在身，趋吉避凶。本年求财不利，切忌投资额大生意，一切以守旧为宜。");
			break;
		default:
			break;
		}

		sb.append(""+this.seperate);

		return sb.toString();
	}
	
	public String tzmText() {
		StringBuffer sb = new StringBuffer();
		switch(this.tzmNum) {
		case 1:
			sb.append("第一类童子命，因犯童子煞，故命中不顺，日常或有长期慢性病，或感情不顺，或是婚姻不佳，或是事业不佳，对人生已经产生了较大的阻碍这些特征，需化解！");
			break;
		case 2:
			sb.append("第二类童子命，如果日常有费尽全力去努力也难以出现改变，人生婚姻感情、事业、健康比较波折艰辛，阻碍重重这些特征，一般可确定此命格为真童子命，需化解！");
			break;
		case 3:
			sb.append("第三类童子命：前世往往是仙官，所以导致被贬为童子，这种注定在人间需要受到苦难与磨炼，此类多因罪责未尽，故今世仍在人世受七情所困。需化解！");
			break;
		case 4:
			sb.append("第四类童子命：此类来源较为隐秘，天赋出众却与人世难以磨合，故在人世便是苦难，只可广结善缘，积修功德，才能有望对今生产生一些影响，仙缘较深，又已历经多世，投入玄门修行较易有成。需化解！");
			break;
		case 5:
			sb.append("第五类童子命：虽仙缘极深，然因缘牵绊，难以自明，多是累世童子刚经人世，罪业未消，必受苦难，来世亦必将是童子命。需化解！");
			break;
		default:
			sb.append("非童子命 ");
			break;
		}
		sb.append(this.seperate);
		return sb.toString();
	}

	public String getAnimalOfCurrentYear() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		String animal = "鼠";
		try {
			Calendar cal = Calendar.getInstance();
			
			BaZi curr = new BaZi(cal);
			
			animal = curr.animalsYear() ;
		}catch(Exception e) {
			e.printStackTrace();
			animal = "鼠";
		}
		return animal;
	}
	
	/**
	 * 范太岁 
	 * 
	 * @return
	 */
	public String fantaisui() {
		String animalOfCurrentTime = this.getAnimalOfCurrentYear();
		HashMap<String, String> map = TAISUIMap.get(animalOfCurrentTime);
		String result = map.get(this.shengxiao);
		StringBuffer sb = new StringBuffer();
		if(result == null || "".equals(result)) {
			result = "Not";
			sb.append("不犯太岁");
			sb.append(this.seperate);
			return sb.toString();
		}
		sb.append("古曰:\"太岁当头坐，无灾也有祸\"。如犯太岁者今年自己或家人有婚嫁、添丁等大喜事，则犯太岁者应吉不应凶。如没有则要小心如下:");
		sb.append(this.seperate);sb.append(this.seperate);
		switch (result) {
		case "值":
			sb.append("值太岁者:即本命年详见(出生时间与命运)，表示该年运程有阻滞，心绪不宁，健康容易出问题，忌展拳脚，一动不如一静，留在原地发展反而会有更佳的运势");
			break;
		case "冲":
			sb.append("冲太岁者:冲即为冲击冲动。今年受冲、必有动向。如搬屋、搬公司、跳槽、转行、亲友反目、大病、破败、出门与人结怨交感出轨等。");
			break;
		case "刑":
			sb.append("刑太岁者:刑即为刑伤刑罚。小心官司、小人、罚款、自己的专业失手、自己或亲人健康问题。");
			break;
		case "害":
			sb.append("害太岁者:害解陷害、危害。今年易陷害、朋友 出卖、合作破财、食物中毒、自身或亲朋有病灾、 被误导损失、投诉等。");
			break;
		case "破":
			sb.append("破太岁者:破即破坏。今年运气易有突然而来的破坏，破败破财破损、破坏好友关系、破坏合作、破坏身体(有病)等。");
			break;
		default:
			break;
		}
		sb.append(this.seperate);sb.append(this.seperate);
		return sb.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String businessAnalysis() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("命主五行用神是【%s】, 喜神是 【%s】, 命中忌 【%s】", this.useGod,this.loveGod,this.hateGod));
		sb.append(this.seperate);sb.append(this.seperate);
		String position1 = luckyPositionMap.get(this.useGod);
		String position2 = "";
		int type = 1;
		if(!this.useGod.equals(this.loveGod)) {
			   position2=luckyPositionMap.get(this.loveGod);
			   type++;
		}
		if(type ==1 ) {
			sb.append(String.format("所以命主适合在 【%s】方发展工作,", position1));
			sb.append(String.format("可使工作顺利。这里包括 【%s】 方的城市和国家，也包括一个城市的  【%s】 方。在 【%s】方更加吃力。",
					position1,position1,luckyPositionMap.get(this.hateGod)));
			sb.append(this.seperate);sb.append(this.seperate);
			sb.append(String.format("命主适合从事和 【%s】有关的工作，可事半功倍。从事 【%s】工作，需要付出更大的努力才能收获结果。", this.useGod,this.hateGod));
			sb.append(this.seperate);
			sb.append(this.seperate);
			switch(this.loveGod.charAt(0)) {
			case '金':
				sb.append("在西方发展有利。跟金属或财务相关的事情。如银行、医院、会计、首饰等等");
				break;
			case '木':
				sb.append("在东方发展有利。文化艺术类，或跟植物相关的事物。如讲师、设计师、植物产品等等");
				break;
			case '水':
				sb.append("在北方发展有利。跟水相关的事物，或一切流动的事物。如贸易、物流、HR、水产、饮料等等");
				break;
			case '火':
				sb.append("在南方发展有利。电子电器电脑类，或一切发光发亮的事物。如电子商务、线上一切产业、娱乐圈、彩妆、跟美相关的事物等等");
				break;
			case '土':
				sb.append("跟土地和人体相关的事物。如地产、工程、健康、美容、瘦身等等）");
				break;
			}
			sb.append(this.seperate);
			sb.append(this.seperate);
		}else{
			sb.append(String.format("所以命主适合在 【%s】方,和【%s】方发展工作,", position1,position2));
			sb.append(String.format("可使工作顺利。这里包括 【%s】 【%s】 方的城市和国家，也包括一个城市的  【%s】 【%s】 方。在 【%s】方更加吃力。",
					position1,position2,position1,position2,luckyPositionMap.get(this.hateGod)));
			sb.append(this.seperate);sb.append(this.seperate);
			sb.append(String.format("命主适合从事和 【%s】【%s】有关的工作，可事半功倍。从事 【%s】工作，需要付出更大的努力才能收获结果。", this.useGod,this.loveGod,this.hateGod));
			sb.append(this.seperate);
			sb.append(this.seperate);
			switch(this.loveGod.charAt(0)) {
			case '金':
				sb.append("金：在西方发展有利。跟金属或财务相关的事情。如银行、医院、会计、首饰等等");
				break;
			case '木':
				sb.append("木：在东方发展有利。文化艺术类，或跟植物相关的事物。如讲师、设计师、植物产品等等");
				break;
			case '水':
				sb.append("水：在北方发展有利。跟水相关的事物，或一切流动的事物。如贸易、物流、HR、水产、饮料等等");
				break;
			case '火':
				sb.append("火：在南方发展有利。电子电器电脑类，或一切发光发亮的事物。如电子商务、线上一切产业、娱乐圈、彩妆、跟美相关的事物等等");
				break;
			case '土':
				sb.append("土：跟土地和人体相关的事物。如地产、工程、健康、美容、瘦身等等）");
				break;
				default:break;
			}
			sb.append(this.seperate);
			sb.append(this.seperate);
			switch(this.useGod.charAt(0)) {
			case '金':
				sb.append("金：在西方发展有利。跟金属或财务相关的事情。如银行、医院、会计、首饰等等");
				break;
			case '木':
				sb.append("木：在东方发展有利。文化艺术类，或跟植物相关的事物。如讲师、设计师、植物产品等等");
				break;
			case '水':
				sb.append("水：在北方发展有利。跟水相关的事物，或一切流动的事物。如贸易、物流、HR、水产、饮料等等");
				break;
			case '火':
				sb.append("火：在南方发展有利。电子电器电脑类，或一切发光发亮的事物。如电子商务、线上一切产业、娱乐圈、彩妆、跟美相关的事物等等");
				break;
			case '土':
				sb.append("土：跟土地和人体相关的事物。如地产、工程、健康、美容、瘦身等等）");
				break;
				default:break;
			}
			sb.append(this.seperate);
			sb.append(this.seperate);
		}
		switch(this.hateGod.charAt(0)) {
		case '金':
			sb.append("金：在西方发展有利。跟金属或财务相关的事情。如银行、医院、会计、首饰等等");
			break;
		case '木':
			sb.append("木：在东方发展有利。文化艺术类，或跟植物相关的事物。如讲师、设计师、植物产品等等");
			break;
		case '水':
			sb.append("水：在北方发展有利。跟水相关的事物，或一切流动的事物。如贸易、物流、HR、水产、饮料等等");
			break;
		case '火':
			sb.append("火：在南方发展有利。电子电器电脑类，或一切发光发亮的事物。如电子商务、线上一切产业、娱乐圈、彩妆、跟美相关的事物等等");
			break;
		case '土':
			sb.append("土：跟土地和人体相关的事物。如地产、工程、健康、美容、瘦身等等）");
			break;
			default:break;
		}
		sb.append(this.seperate);
		sb.append(this.seperate);
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ClientRequest req = new ClientRequest();
		req = req.mock();
	
		ResultMap obj = new ResultMap(req, false);
		
		System.out.println(String.format("%-5s %-5s %-5s", "aa","bbb","c"));
	}
}
