package com.kairon.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.kairon.dao.Dao;
import com.kairon.main.Library;
import com.kairon.model.Operator;
import com.kairon.util.CreatecdIcon;
import com.kairon.util.MyDocument;

//��¼������
public class BookLoginIFrame extends JFrame {
	private JTextField username;
	private JPasswordField password;
	private JButton login;
	private JButton reset;
	private Operator user;

	public BookLoginIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout(); // �������ֹ�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رհ�ť�����¼�
		borderLayout.setVgap(10); // �������֮�䴹ֱ����
		getContentPane().setLayout(borderLayout); // ʹ�ò��ֹ�����
		setTitle("ͼ��ݹ���ϵͳ��¼"); // ���ô������
		Toolkit tool = Toolkit.getDefaultToolkit(); // ���Ĭ�ϵĹ�����
		Dimension screenSize = tool.getScreenSize(); // �����Ļ��С
		setSize(285, 194); // ���ô����С
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2); // ���ô���λ��

		final JPanel mainPanel = new JPanel(); // ���������
		mainPanel.setLayout(new BorderLayout()); // ���ñ߿򲼾�
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0)); // ���ñ߿�Ϊ0
		getContentPane().add(mainPanel); // ���������м��������

		// ����嶥������
		final JLabel imageLabel = new JLabel(); // ����һ����ǩ��������ʾͼƬ
		ImageIcon loginIcon = CreatecdIcon.add("login.jpg"); // ����һ��ͼ��ͼ��
		imageLabel.setIcon(loginIcon); // ����ͼƬ
		imageLabel.setOpaque(true); // ���û�����߽�����������
		imageLabel.setBackground(Color.GREEN); // ���ñ�����ɫ
		imageLabel.setPreferredSize(new Dimension(260, 60)); // ���ñ�ǩ��С
		mainPanel.add(imageLabel, BorderLayout.NORTH); // ��ӱ�ǩ�������

		// �������������
		final JPanel centerPanel = new JPanel(); // ���һ���������
		final GridLayout gridLayout = new GridLayout(2, 2); // �������񲼾ֹ�����
		gridLayout.setHgap(5); // �������֮��ƽ�о���
		gridLayout.setVgap(20); // �������֮�䴹ֱ����
		centerPanel.setLayout(gridLayout);// ʹ�ò��ֹ�����
		mainPanel.add(centerPanel); // ��ӵ������
		final JLabel userNameLabel = new JLabel(); // �����û���ǩ
		/*userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���ö����ʽ
		userNameLabel.setPreferredSize(new Dimension(0, 0)); // ���������С
		userNameLabel.setMinimumSize(new Dimension(0, 0)); // ���������С����Сֵ
*/		centerPanel.add(userNameLabel); // ��ӵ��������
		userNameLabel.setText("��   ��   ���� "); // ���ñ�ǩ�ı�
		username = new JTextField(20); // �����û����ı���
		username.setPreferredSize(new Dimension(0, 0));// ���������С
		centerPanel.add(username); // ��ӵ��������
		final JLabel passwordLabel = new JLabel(); // ���������ǩ
		//passwordLabel.setHorizontalAlignment(SwingConstants.CENTER); // ���ö��뷽ʽ
		centerPanel.add(passwordLabel); // ��ӵ��������
		passwordLabel.setText(" ��       �룺  "); // ���ñ�ǩ�ı�
		password = new JPasswordField(20); // ���������
		password.setDocument(new MyDocument(6)); // �������볤��Ϊ6
		password.setEchoChar('*'); // ���������Ļ����ַ�
		password.addKeyListener(new KeyAdapter() { // ���������
			public void keyPressed(final KeyEvent e) { // �������̵����¼�
				if (e.getKeyCode() == 10) // ������˻س���
					login.doClick(); // ���е�¼
			}
		});
		centerPanel.add(password);
		
		//�����ײ�����
		final JPanel southPanel = new JPanel();			//����һ���������
		mainPanel.add(southPanel,BorderLayout.SOUTH);	//��ӵ������
		login =new JButton();
		login.addActionListener(new BookLoginAction()); 	//��Ӽ�����
		login.setText("��¼");
		southPanel.add(login);
		reset = new JButton();
		reset.addActionListener(new BookResetAction()); 	//��Ӽ�����
		reset.setText("����");
		southPanel.add(reset);
		setVisible(true);
		setResizable(false); 						//���ô��岻�ɸı��С
	}
	
	//��������
	private class BookLoginAction implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			user = Dao.check(new String(username.getText()), new String(password.getPassword()));//����DAO����
			if(user.getName() != null) {
				try{
					Library frame = new Library(); //����һ��������
					frame.setVisible(true); 
					BookLoginIFrame.this.setVisible(false);//���õ�¼���岻��ʾ
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "��������ȷ���û��������룡"); //������ʾ��
				username.setText("");
				password.setText("");
			}
		}
		
	}
	
	//��������
	private class BookResetAction implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			username.setText("");
			password.setText("");
		}
		
	}

}
