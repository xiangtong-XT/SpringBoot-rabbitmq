package com.example.demo.ToolUtil;

import java.nio.charset.Charset;
import java.security.MessageDigest;

public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("utf-8"));
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	public static void main(String[] args) {
//		System.out.println(Charset.defaultCharset().name());
//		String md5laststr = "dc2017MT_MOB_00025{\"account\":\"15928841761\",\"birthday\":\"1989-09-02\",\"campusId\":\"12\",\"campusName\":\"成师温江校区\",\"enterSchoolDate\":\"2016-09-01\",\"gender\":\"Male\",\"idBar\":\"200015015928841761167500\",\"rechargeStatus\":\"1\",\"sbaccount\":\"\",\"schoolId\":\"6\",\"schoolName\":\"成都师范学院\",\"studentNo\":\"102009\",\"token\":\"18ca7f23ab934c12bde8892575801cfe\",\"userId\":\"52306\",\"userType\":\"stu\",\"username\":\"杨远江\",\"version\":\"1\"}";//KEY_STORE+sid+jo.toJSONString();
//		System.out.println("加密校验：传入"+123+" 后台加密"+(MD5.md5(md5laststr).toUpperCase()));
//		ServiceKeyUtil.isValidation("00ECA9CD4FD0FDF3AE3BAA0FAAF8C232","{\"chinese_tag\":\"视频\",\"pageNo\":3,\"showCount\":6}","MT_MOB_00001");
		System.out.println(MD5.md5("2"));
	}
}
