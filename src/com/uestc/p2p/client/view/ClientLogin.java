/**
 * �ͻ��˵�¼����
 */
package com.uestc.p2p.client.view;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.uestc.p2p.client.model.ClientUser;
import com.uestc.p2p.client.tools.ManageCliThread;
import com.uestc.p2p.client.tools.ManageFriends;
import com.uestc.p2p.common.*;
//import java.awt.event.*;
public class ClientLogin extends JFrame implements ActionListener {

	//���������
	JLabel jbll;
	//�����м��
	JLabel jbl21,jbl22,jbl23;
	JTextField jtf;//�˺�
	JPasswordField jpf;//����
	//���������
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientLogin clientLogin = new ClientLogin();	
	}
	public ClientLogin(){
		//�����
		jbll = new JLabel(new ImageIcon("image/shou.gif"));	
		//�м��
		jp2 = new JPanel(new GridLayout(2,3));
		jbl21 = new JLabel("�˺�:",JLabel.CENTER);
		jbl22 = new JLabel("����:",JLabel.CENTER);
		jbl23 = new JLabel("��������",JLabel.CENTER);
		jbl23.setForeground(Color.BLUE);
		jb1 = new JButton(new ImageIcon("image/clear.gif"));
		jtf = new JTextField();
		jpf = new JPasswordField();
		//���ؼ�����˳����뵽jp2
		jp2.add(jbl21);
		jp2.add(jtf);
		jp2.add(jb1);
		jp2.add(jbl22);
		jp2.add(jpf);
		jp2.add(jbl23);
		//����İ�ť
		jp1 = new JPanel();
		jb1 = new JButton(new ImageIcon("image/denglu.gif"));
		//��Ӧ�û���¼
		jb1.addActionListener(this);
		jb2 = new JButton(new ImageIcon("image/quxiao.gif"));
		jp1.add(jb1);
		jp1.add(jb2);
		
		
		//���ϼ���
		this.add(jbll,"North");
		this.add(jp2,"Center");
		this.add(jp1,"South");

		this.setSize(340,200);
		this.setLocation(300, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== jb1){
			ClientUser cu = new ClientUser();
		    User u = new User();
		    u.setUserId(jtf.getText().trim());
		    u.setPw(new String(jpf.getPassword()));
		    String check = cu.checkUser(u);
		    if(check.equals(MessageType.m_login_succeed)){
		    	
		    	try {
		    		//����Ҫ�󷵻����ߺ��ѵİ�
					ObjectOutputStream oos =new ObjectOutputStream
					(ManageCliThread.getThread(u.getUserId()).getS().getOutputStream());
					
					//����message
					Message m = new Message();
					//�����ض��˺ŵĺ����б�
					m.setType(MessageType.m_get_onLine);
					m.setSender(u.getUserId());
					oos.writeObject(m);
		    	} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	
		    	//�رյ�¼����
		    	this.dispose();
		    }else if(check.equals(MessageType.m_login_duplicate)){
		    	JOptionPane.showMessageDialog(this,"�������ظ���¼");
		    }else if(check.equals(MessageType.m_login_failed)){
		    	JOptionPane.showMessageDialog(this,"���������������");
		    }
		}
	}
}
