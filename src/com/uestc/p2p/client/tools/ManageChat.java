/**
 * ����һ�������û�����Ľ���
 */
package com.uestc.p2p.client.tools;
import java.util.HashMap;
import java.util.Iterator;

import com.uestc.p2p.client.view.Chat;
public class ManageChat {
  
    private static HashMap hm=new HashMap<String, Chat>();
	
	//����
	public static void addChat(String key,Chat chat)
	{
		hm.put(key, chat);
	}
	//ȡ��
	public static Chat getChat(String key )
	{
		return (Chat)hm.get(key);
	}
	public static void show(){
		Iterator iter = hm.keySet().iterator();
		while(iter.hasNext()){
			System.out.println("��"+iter.next().toString());
		}
	}
}
