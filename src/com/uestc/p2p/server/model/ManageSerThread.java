package com.uestc.p2p.server.model;
import java.util.HashMap;
import java.util.Iterator;
public class ManageSerThread {
   public static HashMap hm = new HashMap<String ,SerConCliThread>();
   //��hm�����һ���ͻ���ͨѶ�߳�
   public static void addThread(String uid,SerConCliThread scct){
	   hm.put(uid, scct);
   }
   public static SerConCliThread getThread(String uid){
	   return (SerConCliThread)hm.get(uid);
   }
 //���ص�ǰ���ߺ���ƴ�ӵ��ַ���
   public static String getOnlineList(){
	   Iterator it = hm.keySet().iterator();
	   String onLineList = "";
	   while(it.hasNext()){
		   onLineList += it.next().toString()+" ";
	   }
	   return onLineList;
   }
}
