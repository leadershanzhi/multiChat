/*
 * ��������ĳ���ͻ��˵�ͨ���߳�
 */
package com.uestc.p2p.server.model;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.uestc.p2p.common.Message;
import com.uestc.p2p.common.MessageType;
public class SerConCliThread extends Thread {

	Socket s;
	public SerConCliThread(Socket s){
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.s = s;
	}
	//֪ͨ�����û�
	public void notifyOther(){
		//�õ��������ߵ��˵��߳�
		HashMap hm =ManageSerThread.hm;
		Iterator it = hm.keySet().iterator();
		while(it.hasNext()){
			Message m = new Message();
			String res = ManageSerThread.getOnlineList();
	    	m.setType(MessageType.m_return_onLine);
	    	m.setContent(res);
			String uid_online = it.next().toString();
			System.out.println("����֪ͨһ��"+uid_online+"���º�����Ϣ");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageSerThread.getThread(uid_online).s.getOutputStream());
			    m.setReceiver(uid_online);
			    oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	public void showGroupMessage(Message m){
		//�õ��������ߵ��˵��߳�
		HashMap hm =ManageSerThread.hm;
		Iterator it = hm.keySet().iterator();
		while(it.hasNext()){
			String res = ManageSerThread.getOnlineList();
	    	m.setType(MessageType.m_group_receive);
			String uid_online = it.next().toString();
			try {
				if(!uid_online.equals(m.getSender())){
					ObjectOutputStream oos = new ObjectOutputStream(ManageSerThread.getThread(uid_online).s.getOutputStream());
				    m.setReceiver(uid_online);
				    oos.writeObject(m);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void run(){
		while(true){
			//������߳̾Ϳ��Խ��տͻ��˵���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			    Message m = (Message)ois.readObject();
			    
			    //�Կͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
			    if(m.getType().equals(MessageType.m_info)){
			    	//������ת����ȡ�ý����˵�ͨѶ�߳�
			    	System.out.println("���Ƿ������ģ�"+m.getSender()+"��"+m.getReceiver()+"say:"+m.getContent());
				    SerConCliThread scct = ManageSerThread.getThread(m.getReceiver());
				    ObjectOutputStream oos = new ObjectOutputStream(scct.s.getOutputStream());
				    oos.writeObject(m);
			    }else if(m.getType().equals(MessageType.m_get_onLine)){
			    	//���ڷ������ĺ��ѷ���
				    notifyOther();
			    }else if(m.getType().equals(MessageType.m_group_send)){
			    	System.out.println("���Ƿ�����Ⱥ��ģ�"+m.getSender()+"say:"+m.getContent()+"to us");
			    	showGroupMessage(m);
			    }
			    
			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
