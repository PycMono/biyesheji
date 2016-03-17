package cn.edu.cdtu.utils;

import java.util.UUID;
/**
 * UUId
 * @author Lee
 *
 */
public class UuId {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		uuId();
	}
	public static String uuId(){
		UUID uId=UUID.randomUUID();
		System.out.println(uId.toString().replace("-", ""));
		return uId.toString().replace("-", "");
	}
}
