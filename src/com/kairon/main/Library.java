package com.kairon.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.kairon.iframe.BookLoginIFrame;
import com.kairon.util.CreatecdIcon;

//主窗体类
public class Library extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();// 桌面窗体

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new BookLoginIFrame();// 登录窗口
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 设置界面系统外观
	}

	// 默认构造函数
	public Library() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 关闭按钮处理事件
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screenSize = tool.getScreenSize();
		setSize(800, 600);
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setTitle("图书馆管理系统");
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar); // 设置菜单栏
		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
		getContentPane().add(toolBar, BorderLayout.NORTH); // 设置工具栏
		final JLabel label = new JLabel(); // 创建一个标签，用来显示图片
		label.setBounds(0, 0, 0, 0); // 设置窗体的大小和位置
		label.setIcon(null); // 窗体背景
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize(); // 获得组件大小
				label.setSize(e.getComponent().getSize()); // 设置标签大小
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src="
						+ this.getClass().getResource("/backImg.jpg")
						+ "></html>"); // 设置标签文本
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE)); 	//添加到桌面窗体
		getContentPane().add(DESKTOP_PANE);						//将桌面窗体添加到主窗体中
	}

	// 添加子窗体方法
	public static void addIFame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}

	//创建菜单栏的方法
	private JMenuBar createMenu() {

		JMenuBar menuBar = new JMenuBar();				//创建工具栏
		
		//初始化基础数据维护菜单
		JMenu baseMenu = new JMenu();					
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));//设置菜单图标
		//新增读者信息管理子菜单项
		JMenu readerManagerMItem = new JMenu("读者信息管理");	
		readerManagerMItem.add(MenuActions.READER_ADD);//添加读者信息添加菜单项
		readerManagerMItem.add(MenuActions.READER_MODIFY);//添加读者信息修改菜单项
		//新增图书类别管理子菜单
		JMenu bookTypeManageMItem = new JMenu("图书类别管理");
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);//添加图书类型添加菜单项
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);//添加图书类型修改菜单项
		//新增图书信息管理子菜单
		JMenu menu = new JMenu("图书信息管理");
		menu.add(MenuActions.BOOK_ADD);		//添加图书信息添加菜单项
		menu.add(MenuActions.BOOK_MODIFY);	//添加图书信息修改菜单项
		//添加读者信息管理子菜单
		baseMenu.add(readerManagerMItem);	
		baseMenu.add(bookTypeManageMItem);	//添加图书类别管理子菜单
		baseMenu.add(menu);					//添加图书信息管理子菜单
		baseMenu.addSeparator(); 			//添加分隔线
		baseMenu.add(MenuActions.EXIT);		//添加退出系统菜单项
		
		//初始化新书订购管理菜单
		JMenu bookOrderMenu = new JMenu();	
		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));//设置菜单图标
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);//添加新书订购菜单项
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);//添加验收新书菜单项
		//初始化借阅管理菜单
		JMenu borrowManageMenu = new JMenu();
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		borrowManageMenu.add(MenuActions.BORROW);//添加图书借阅管理菜单项
		borrowManageMenu.add(MenuActions.GIVE_BACK);//添加图书归还菜单项
		borrowManageMenu.add(MenuActions.BOOK_SEARCH);//添加图书搜索菜单项
		
		//初始化系统维护caidan
		JMenu sysManageMenu = new JMenu();
		sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		//新增用户管理子菜单
		JMenu userManageMItem = new JMenu("用户管理");
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);//添加更改口令菜单项
		sysManageMenu.add(userManageMItem);//添加用户管理子菜单
		
		menuBar.add(baseMenu);
		menuBar.add(bookOrderMenu);
		menuBar.add(borrowManageMenu);
		menuBar.add(sysManageMenu);
		
		return menuBar;
	}

	//创建工具栏的方法
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();	//初始化工具栏
		toolBar.setFloatable(false); 		//设置是否可以移动
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));//设置边框
		
		//图书信息添加按钮
		JButton bookAddButton = new JButton(MenuActions.BOOK_ADD);//图书信息添加按钮
		ImageIcon icon = new ImageIcon(Library.class.getResource("/bookAddtb.jpg"));//创建图标
		bookAddButton.setIcon(icon);//设置按钮图标
		bookAddButton.setHideActionText(true);//显示提示文本
		toolBar.add(bookAddButton);//添加到工具栏中
		
		//图书信息修改按钮
		JButton bookModiAndDelButton = new JButton(MenuActions.BOOK_MODIFY);//图书信息管理
		ImageIcon bookModiIcon = CreatecdIcon.add("bookModiAndDeltb.jpg");//创建图标
		bookModiAndDelButton.setIcon(bookModiIcon);
		bookModiAndDelButton.setHideActionText(true);//显示提示文本
		toolBar.add(bookModiAndDelButton);//添加到工具栏中
		
		//图书类别添加按钮
		JButton bookTypeAddButton = new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddIcon = CreatecdIcon.add("bookTypeAddtb.jpg");
		bookTypeAddButton.setHideActionText(true);
		toolBar.add(bookTypeAddButton);
		
		//图书借阅按钮
		JButton bookBorrowButton = new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowIcon = CreatecdIcon.add("bookBorrowtb.jpg");
		bookBorrowButton.setIcon(bookBorrowIcon);
		bookBorrowButton.setHideActionText(true);
		toolBar.add(bookBorrowButton);
		
		//图书借阅按钮
		JButton bookOrderButton = new JButton(MenuActions.NEWBOOK_ORDER);
		ImageIcon bookOrderIcon = CreatecdIcon.add("bookOrdertb.jpg");
		bookOrderButton.setIcon(bookOrderIcon);
		bookOrderButton.setHideActionText(true);
		toolBar.add(bookOrderButton);
		
		//验收新书按钮
		JButton bookCheckButton = new JButton(MenuActions.NEWBOOK_CHECK_ACCEPT);
		ImageIcon bookCheckIcon = CreatecdIcon.add("newbookChecktb.jpg");
		bookCheckButton.setIcon(bookCheckIcon);
		bookCheckButton.setHideActionText(true);
		toolBar.add(bookCheckButton);
		
		//读者信息添加按钮
		JButton readerAddButton = new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddIcon = CreatecdIcon.add("readerAddtb.jpg");
		readerAddButton.setIcon(bookCheckIcon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		//读者信息修改按钮
		JButton readerModiAndDelButton = new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelIcon = CreatecdIcon.add("readerModiAndDeltb.jpg");
		readerModiAndDelButton.setIcon(bookCheckIcon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
				
		//退出系统按钮
		JButton exitButton = new JButton(MenuActions.EXIT);
		ImageIcon exitIcon = CreatecdIcon.add("exittb.jpg");
		exitButton.setIcon(bookCheckIcon);
		exitButton.setHideActionText(true);
		toolBar.add(exitButton);
		
		return toolBar;
	}
}
