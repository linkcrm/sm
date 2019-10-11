package com.owen.util;

import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.deepoove.poi.XWPFTemplate;

public class PoiWordUtils {
	public static void output(Map<?, ?> map) {
		XWPFTemplate template = XWPFTemplate.compile("template.docx").render(map);
		FileOutputStream out;
		try {
			out = new FileOutputStream("out_template.docx");
			template.write(out);
			out.flush();
			out.close();
			template.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void download(HttpServletResponse response, Map<?, ?> map, String title) {
		String path = "E:\\myspace\\sm\\template.docx";
		if (java.io.File.separator.equals("/")){
			path="/root/sm/template.docx";
		}
//		path="/Users/butterfly/sm/template.docx";
		XWPFTemplate template = XWPFTemplate.compile(path).render(map);
//		String sname = DateUtil.getYYYYMMDD() + "-" + DateUtil.getHH() + "-" + DateUtil.getMI() + "-" + DateUtil.getSS();
		try {
			response.setContentType("application/msword");
			String fileName = title +"一生批命" + ".docx";
			response.setHeader("Content-Disposition",
					"attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
			ServletOutputStream out = response.getOutputStream();
			template.write(out);
//			FileOutputStream fout = new FileOutputStream(sname);
//			template.write(fout);
//			fout.flush();
//			fout.close();
			
			out.flush();
			out.close();
			
			template.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

}
