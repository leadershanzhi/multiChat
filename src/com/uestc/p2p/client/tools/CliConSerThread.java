/*
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�
 */
package com.uestc.p2p.client.tools;

import java.io.ObjectInputStream;
import java.net.Socket;

import com.uestc.p2p.client.view.Chat;
import com.uestc.p2p.client.view.FriendList;
import com.uestc.p2p.client.view.GroupChat;
import com.uestc.p2p.common.Message;
import com.uestc.p2p.common.MessageType;
public class CliConSerThread extends Thread{
   private Socket s;
  
   public CliConSerThread(Socket s){
	   this.s=s;
   }
   public void run(){
	   while(true){
		   try {
			  ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		      Message m = (Message)ois.readObject();
		      
		      //�ѷ�������õ���Ϣ��ʾ���������
              if(m.getType().equals(MessageType.m_info)){
            	  System.out.println("��ȡ���ӷ�������������Ϣ"+m.getSender()+"��"+m.getReceiver()+"˵��"+m.getContent());
            	  String key =m.getReceiver()+"_"+m.getSender();
            	  Chat c =ManageChat.getChat(key);
            	  c.showMessage(m);
              }else if(m.getType().equals(MessageType.m_return_onLine)){
            	  String receiver = m.getReceiver();
            	  //�޸���Ӧ�ĺ����б�
            	  System.out.println("��Ҫ����_"+receiver+"_������Ϣ��"+m.getContent());
            	  FriendList fl = ManageFriends.getFriendList(receiver);
            	  if(fl!=null)
            		  fl.dispose();
            	  fl = new FriendList(receiver);
                  fl.updateFriendList(m);
                  ManageFriends.addFriendList(receiver, fl);
            	 
              }else if(m.getType().equals(MessageType.m_group_receive)){
            	  System.out.println("��������������ϢȺ��Ϣ"+m.getSender()+"˵��"+m.getContent());
            	  String key =m.getReceiver()+" group";
            	  GroupChat gc = ManageGroupChat.getGChat(key);
            	  gc.showMessage(m);
              }
		   }catch(Exception e) {
			  e.printStackTrace();
		   }
	   }
   }
   public Socket getS() {
		return s;
	}
}
