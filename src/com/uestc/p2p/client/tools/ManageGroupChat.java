/**
 * ����һ�������û�����Ľ���
 */
package com.uestc.p2p.client.tools;
import java.util.HashMap;
import java.util.Iterator;

import com.uestc.p2p.client.view.GroupChat;
public class ManageGroupChat {
  
    private static HashMap hm=new HashMap<String, GroupChat>();
	
	//����
	public static void addChat(String key,GroupChat gchat)
	{
		hm.put(key, gchat);
	}
	//ȡ��
	public static GroupChat getGChat(String key )
	{
		return (GroupChat)hm.get(key);
	}
	public static void show(){
		Iterator iter = hm.keySet().iterator();
		while(iter.hasNext()){
			System.out.println("��"+iter.next().toString());
		}
	}
}
