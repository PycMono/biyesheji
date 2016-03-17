package cn.edu.cdtu.utils;

import java.security.MessageDigest;
/**
 * Md5加密
 * @author Lee
 *
 */
public class MD5Util {
	
	public static String Md5(String src) {
		try {
			char[] number = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'A', 'B', 'C', 'D', 'E', 'F' };
			StringBuffer newbuf = new StringBuffer();
			byte[] s = src.getBytes(); // 加密前的数组
			MessageDigest message = MessageDigest.getInstance("MD5");
			byte[] ed = message.digest(s); // 加密后的数组
			for (byte b : ed) {
				newbuf.append(number[(b >> 4) & 0x0F]); // 高位后移
				newbuf.append(number[b & 0x0F]); // 低位不做处理
			}
			return newbuf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 public static void main(String[] args) {
		System.out.println(Md5("123456"));
	}
}
