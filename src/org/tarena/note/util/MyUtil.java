package org.tarena.note.util;

public class MyUtil {
	/**
	 * �������ַ���ȥ���ո�,ת�ɴ�д����
	 * @param a ����1
	 * @param b ����2
	 * @return ���ӽ��
	 */
	public static String add(String a,String b){
		if(a==null){
			a="";
		}
		if(b==null){
			b="";
		}
		String a1 = a.trim().toUpperCase().replace(" ", "");
		String b1 = b.trim().toUpperCase().replace(" ", "");
		return a1+b1;
	}

	
	
	
}
