/*
 * ����һ������ͻ��˺ͷ�����ͨѶ���߳�
 */
package com.uestc.p2p.client.tools;
import java.util.HashMap;
public class ManageCliThread {
   private static HashMap hm = new HashMap<String, CliConSerThread>();
   //�Ѵ����õ�CliConSerThread���뵽hashmap��
   public static void addCliThread(String uid,CliConSerThread ccst){
	   hm.put(uid, ccst);
   }
   //����ͨ��uid,ȡ�ø��߳�
   public static CliConSerThread getThread(String uid){
	   return (CliConSerThread)hm.get(uid);
   }
   
}
