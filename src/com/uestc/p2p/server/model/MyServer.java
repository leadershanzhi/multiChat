/**
 * ���Ƿ����������ڼ������ȴ�ĳ���ͻ��˽���
 */
package com.uestc.p2p.server.model;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.uestc.p2p.common.Message;
import com.uestc.p2p.common.MessageType;
import com.uestc.p2p.common.User;
public class MyServer {
   
	public MyServer(){
		try{
			//�ڸö˿��¼���
			System.out.println("���Ƿ���������9999����");
			ServerSocket ss = new ServerSocket(9999);
			//�������ȴ�����
		    while(true){
		    	Socket s = ss.accept();
				//���ܿͻ��˷�������Ϣ
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				System.out.println("���������յ����û��������룺"+u.getUserId()+"��"+u.getPw());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPw().equals("shan")){
					//����һ���ɹ���½����Ϣ��
					if(ManageSerThread.getThread(u.getUserId())==null){
						m.setType(MessageType.m_login_succeed);
						oos.writeObject(m);
						//����һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ
						SerConCliThread scct = new SerConCliThread(s);
					    //������ÿͻ���ͨѶ���߳�
						ManageSerThread.addThread(u.getUserId(), scct);
						scct.start();
					}else{
						m.setType(MessageType.m_login_duplicate);
						oos.writeObject(m);
						s.close();
					}
					

				}else{
					m.setType("2");
					oos.writeObject(m);
					s.close();
				}
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}
