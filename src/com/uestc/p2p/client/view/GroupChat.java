/*
 * ������������
 */
package com.uestc.p2p.client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uestc.p2p.client.tools.ManageCliThread;
import com.uestc.p2p.common.Message;
import com.uestc.p2p.common.MessageType;
public class GroupChat extends JFrame implements ActionListener {

	/**
	 * @param args
	 */
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String sender;

	public GroupChat(String ownerId){
		this.sender = ownerId;
		jta = new JTextArea();
		jta.setForeground(Color.BLUE);
		jtf = new JTextField(14);
		jtf.setBackground(Color.pink);
		jb = new JButton("����");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setIconImage((new ImageIcon("image/tu4.jpg")).getImage());
		this.setTitle("chat:'"+ownerId+"'���ں�'����Ⱥ��'����");
		this.setSize(300,400);
		this.setLocation(360, 100);
		this.setVisible(true);
	}
    public String GetNowTime(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == jb){
			//�������
			Message m = new Message();
			m.setSender(sender);
			m.setReceiver("group");
			m.setType(MessageType.m_group_send);
			m.setContent(jtf.getText());
			m.setTime(GetNowTime().toString());
			//���͸�������
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageCliThread.getThread(sender).getS().getOutputStream());
				String info = "-------------------\n�ң�"+"\t"+m.getTime()+"\n"+m.getContent()+"\n";
			    this.jta.append(info);
			    this.jtf.setText("");
				oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}
	}
	
	public void showMessage(Message m){
		String info = "-------------------\n"+m.getSender()+":\t"+m.getTime()+"\n:"+m.getContent()+"\n";
		System.out.println("���ڷ�����"+m.getReceiver()+info);
	    this.jta.append(info);
	}
}
