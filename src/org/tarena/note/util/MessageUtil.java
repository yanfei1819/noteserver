package org.tarena.note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class MessageUtil {
	
	/**
	 * MD5ժҪ�㷨
	 * @param msg
	 * @return
	 */
	public static String md5(String msg){
		try{
			MessageDigest md = 
					MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();//Ҫ���ܵ��ֽ�
			byte[] output = md.digest(input);//���ܺ����ɵȳ�output�ֽ�
			//����Base64�㷨���ȳ��ֽ�����ת���ַ���(û������)
			String ret_msg = Base64.encodeBase64String(output);
			return ret_msg;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ���ַ�����Ϣ���н���
	 * @param msg
	 * @return
	 */
	public static String base64Decode(String base64String){
		try{
			byte[] output = Base64.decodeBase64(base64String);
			return new String(output,"UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * ��¼�ɹ�����һ��Ψһ���Ʊ��
	 * @return
	 */
	public static String createToken(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * ��������ID
	 * @return
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args){
		System.out.println(md5("123456"));
		System.out.println(md5("abdddddddddddddddddddddddddddddddddcd"));
		System.out.println(md5("a"));
		System.out.println(createToken());
	}
	
	
}
