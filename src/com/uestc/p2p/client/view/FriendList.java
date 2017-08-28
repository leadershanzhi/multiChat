/**
 * �ҵĺ����б����������б���������
 */
package com.uestc.p2p.client.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.uestc.p2p.client.tools.ManageChat;
import com.uestc.p2p.client.tools.ManageGroupChat;
import com.uestc.p2p.common.Message;
public class FriendList extends JFrame implements ActionListener,MouseListener {
	//�����һ�ſ�Ƭ
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	String ownerId;
	JLabel []jbls;
	//����ڶ��ſ�Ƭ��İ���ˣ�
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	CardLayout c1;
	int i = 0;
	//������JFrame���ó�CardLayout

	public FriendList(String ownerId){
		//=========�����һ�ſ�Ƭ-�����б�
		this.ownerId = ownerId;
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb2 = new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("����Ⱥ��");
		jphy_jb3.addActionListener(this);
		jphy1 = new JPanel(new BorderLayout());
		//�ٶ���10������
		jphy2 = new JPanel(new GridLayout(10,1,4,4));
		jbls = new JLabel[10];
		jbls[i] = new JLabel(ownerId+"",new ImageIcon("image/tu1.jpg"),JLabel.LEFT);
		jbls[i].setEnabled(true);
		jbls[i].addMouseListener(this);
		jphy2.add(jbls[i]);	
		
		jphy3 = new JPanel(new GridLayout(2, 1));
		//��������ť�ӵ�jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		jsp1 = new JScrollPane(jphy2);	
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//========����ڶ��ſ�Ƭ-������
		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("����Ⱥ��");
		jpmsr1 = new JPanel(new BorderLayout());

		jpmsr2 = new JPanel(new GridLayout(10,1,4,4));
		//��jphy2,��ʼ��10��İ����
		JLabel []jbls2 = new JLabel[10];
		for(int i=0; i<jbls2.length; i++){
			jbls2[i] = new JLabel(i+1+"",new ImageIcon("image/tu2.jpg"),JLabel.LEFT);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3 = new JPanel(new GridLayout(2, 1));
		//��������ť�ӵ�jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		jsp2 = new JScrollPane(jpmsr2);
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		c1 = new CardLayout();
		this.setLayout(c1);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		//�ڴ�����ʾ�Լ��ı��
		this.setSize(200, 400);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.setTitle(ownerId);
	}
	//�������ߺ���
	public void updateFriendList(Message m ){	
		String onlinefriend[] = m.getContent().split(" ");
		for(int j=0; j<onlinefriend.length; j++){
			if(!onlinefriend[j].equals(ownerId)){
	            System.out.println("����ִ������");
				jbls[++i] = new JLabel(onlinefriend[j]+"",new ImageIcon("image/tu1.jpg"),JLabel.LEFT);
			    jbls[i].setEnabled(true);
				jbls[i].addMouseListener(this);
				jphy2.add(jbls[i]);
			}
				
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jphy_jb2){
		   c1.show(this.getContentPane(), "2");
		}else if(e.getSource()==jpmsr_jb1){
		   c1.show(this.getContentPane(), "1");
		}else if(e.getSource()==jphy_jb3){
			if(ManageGroupChat.getGChat(ownerId+" group")==null){
				ManageGroupChat.addChat(ownerId+" group", new GroupChat(ownerId));
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// ��Ӧ�û�˫�����¼������õ����ѵı�š�
		if(e.getClickCount()==2){
		   //�õ����ѵı��
		   String friendNo = ((JLabel)e.getSource()).getText();
		   //System.out.println("��ϣ����"+friendNo+"����");
		   String key =ownerId+"_"+friendNo;
		   if(ManageChat.getChat(key)!=null){
			   JOptionPane.showMessageDialog(this,"��ʾ�����Ѿ�����һ������û��ĻỰ���ڣ������ٴδ򿪣�");
		   }else{
			   Chat chat = new Chat(ownerId,friendNo);
			   //�����������뵽������
			   ManageChat.addChat(key,chat);
		   }     
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.BLUE);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.black);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
