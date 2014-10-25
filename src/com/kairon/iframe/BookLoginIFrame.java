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

//登录窗体类
public class BookLoginIFrame extends JFrame {
	private JTextField username;
	private JPasswordField password;
	private JButton login;
	private JButton reset;
	private Operator user;

	public BookLoginIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout(); // 创建布局管理器
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭按钮处理事件
		borderLayout.setVgap(10); // 设置组件之间垂直距离
		getContentPane().setLayout(borderLayout); // 使用布局管理器
		setTitle("图书馆管理系统登录"); // 设置窗体标题
		Toolkit tool = Toolkit.getDefaultToolkit(); // 获得默认的工具箱
		Dimension screenSize = tool.getScreenSize(); // 获得屏幕大小
		setSize(285, 194); // 设置窗体大小
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2); // 设置窗体位置

		final JPanel mainPanel = new JPanel(); // 创建主面板
		mainPanel.setLayout(new BorderLayout()); // 设置边框布局
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0)); // 设置边框为0
		getContentPane().add(mainPanel); // 在主窗体中加入主面板

		// 主面板顶部区域
		final JLabel imageLabel = new JLabel(); // 创建一个标签，用来显示图片
		ImageIcon loginIcon = CreatecdIcon.add("login.jpg"); // 创建一个图像图标
		imageLabel.setIcon(loginIcon); // 设置图片
		imageLabel.setOpaque(true); // 设置绘制其边界内所有像素
		imageLabel.setBackground(Color.GREEN); // 设置背景颜色
		imageLabel.setPreferredSize(new Dimension(260, 60)); // 设置标签大小
		mainPanel.add(imageLabel, BorderLayout.NORTH); // 添加标签到主面板

		// 主面板中心区域
		final JPanel centerPanel = new JPanel(); // 添加一个中心面板
		final GridLayout gridLayout = new GridLayout(2, 2); // 创建网格布局管理器
		gridLayout.setHgap(5); // 设置组件之间平行距离
		gridLayout.setVgap(20); // 设置组件之间垂直距离
		centerPanel.setLayout(gridLayout);// 使用布局管理器
		mainPanel.add(centerPanel); // 添加到主面板
		final JLabel userNameLabel = new JLabel(); // 创建用户标签
		/*userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);// 设置对齐格式
		userNameLabel.setPreferredSize(new Dimension(0, 0)); // 设置组件大小
		userNameLabel.setMinimumSize(new Dimension(0, 0)); // 设置组件大小的最小值
*/		centerPanel.add(userNameLabel); // 添加到中心面板
		userNameLabel.setText("用   户   名： "); // 设置标签文本
		username = new JTextField(20); // 创建用户名文本框
		username.setPreferredSize(new Dimension(0, 0));// 设置组件大小
		centerPanel.add(username); // 添加到中心面板
		final JLabel passwordLabel = new JLabel(); // 创建密码标签
		//passwordLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置对齐方式
		centerPanel.add(passwordLabel); // 添加到中心面板
		passwordLabel.setText(" 密       码：  "); // 设置标签文本
		password = new JPasswordField(20); // 创建密码框
		password.setDocument(new MyDocument(6)); // 设置密码长度为6
		password.setEchoChar('*'); // 设置密码框的回显字符
		password.addKeyListener(new KeyAdapter() { // 监听密码框
			public void keyPressed(final KeyEvent e) { // 监听键盘单击事件
				if (e.getKeyCode() == 10) // 如果按了回车键
					login.doClick(); // 进行登录
			}
		});
		centerPanel.add(password);
		
		//主面板底部区域
		final JPanel southPanel = new JPanel();			//新增一个底面面板
		mainPanel.add(southPanel,BorderLayout.SOUTH);	//添加到主面板
		login =new JButton();
		login.addActionListener(new BookLoginAction()); 	//添加监听器
		login.setText("登录");
		southPanel.add(login);
		reset = new JButton();
		reset.addActionListener(new BookResetAction()); 	//添加监听器
		reset.setText("重置");
		southPanel.add(reset);
		setVisible(true);
		setResizable(false); 						//设置窗体不可改变大小
	}
	
	//监听器类
	private class BookLoginAction implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			user = Dao.check(new String(username.getText()), new String(password.getPassword()));//调用DAO方法
			if(user.getName() != null) {
				try{
					Library frame = new Library(); //创建一个主窗体
					frame.setVisible(true); 
					BookLoginIFrame.this.setVisible(false);//设置登录窗体不显示
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的用户名和密码！"); //弹出提示框
				username.setText("");
				password.setText("");
			}
		}
		
	}
	
	//监听器类
	private class BookResetAction implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent e) {
			username.setText("");
			password.setText("");
		}
		
	}

}
