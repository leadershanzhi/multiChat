/**
 * ���ǿͻ������ӷ������ĺ�̨
 */
package com.uestc.p2p.client.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.uestc.p2p.client.tools.CliConSerThread;
import com.uestc.p2p.client.tools.ManageCliThread;
import com.uestc.p2p.common.Message;
import com.uestc.p2p.common.MessageType;
import com.uestc.p2p.common.User;
public class ClientServer {

	public Socket s;
	public ClientServer(){
		
	}
	//���͵�һ������
	public String sendLoginMessage(Object o){
		String b = MessageType.m_login_succeed;
		try {
			s = new Socket("127.0.0.1",9999);
		    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		    oos.writeObject(o);
		    
		    ObjectInputStream ois =new ObjectInputStream(s.getInputStream());
		    Message ms = (Message)ois.readObject();
		    //������֤�û���¼�ĵط�
		    if(ms.getType().equals(MessageType.m_login_succeed)){
		    	//�������˺źͷ������˱������ӵ��߳�
		    	CliConSerThread ccst = new CliConSerThread(s);
		    	//������ͨѶ�߳�
		    	ccst.start();
		    	ManageCliThread.addCliThread(((User)o).getUserId(), ccst);
		    }else if(ms.getType().equals(MessageType.m_login_duplicate)){
		    	b = MessageType.m_login_duplicate;
		    }else if(ms.getType().equals(MessageType.m_login_failed)){
		    	b = MessageType.m_login_failed;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	public void SendMessage(Object o){
		try {
			Socket s= new Socket("127.0.0.1",9999);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
	}

}
