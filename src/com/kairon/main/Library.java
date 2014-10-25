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

//��������
public class Library extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();// ���洰��

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new BookLoginIFrame();// ��¼����
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ���ý���ϵͳ���
	}

	// Ĭ�Ϲ��캯��
	public Library() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // �رհ�ť�����¼�
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screenSize = tool.getScreenSize();
		setSize(800, 600);
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setTitle("ͼ��ݹ���ϵͳ");
		JMenuBar menuBar = createMenu(); // ���ô����˵����ķ���
		setJMenuBar(menuBar); // ���ò˵���
		JToolBar toolBar = createToolBar(); // ���ô����������ķ���
		getContentPane().add(toolBar, BorderLayout.NORTH); // ���ù�����
		final JLabel label = new JLabel(); // ����һ����ǩ��������ʾͼƬ
		label.setBounds(0, 0, 0, 0); // ���ô���Ĵ�С��λ��
		label.setIcon(null); // ���屳��
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize(); // ��������С
				label.setSize(e.getComponent().getSize()); // ���ñ�ǩ��С
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src="
						+ this.getClass().getResource("/backImg.jpg")
						+ "></html>"); // ���ñ�ǩ�ı�
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE)); 	//��ӵ����洰��
		getContentPane().add(DESKTOP_PANE);						//�����洰����ӵ���������
	}

	// ����Ӵ��巽��
	public static void addIFame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}

	//�����˵����ķ���
	private JMenuBar createMenu() {

		JMenuBar menuBar = new JMenuBar();				//����������
		
		//��ʼ����������ά���˵�
		JMenu baseMenu = new JMenu();					
		baseMenu.setIcon(CreatecdIcon.add("jcsjcd.jpg"));//���ò˵�ͼ��
		//����������Ϣ�����Ӳ˵���
		JMenu readerManagerMItem = new JMenu("������Ϣ����");	
		readerManagerMItem.add(MenuActions.READER_ADD);//��Ӷ�����Ϣ��Ӳ˵���
		readerManagerMItem.add(MenuActions.READER_MODIFY);//��Ӷ�����Ϣ�޸Ĳ˵���
		//����ͼ���������Ӳ˵�
		JMenu bookTypeManageMItem = new JMenu("ͼ��������");
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);//���ͼ��������Ӳ˵���
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);//���ͼ�������޸Ĳ˵���
		//����ͼ����Ϣ�����Ӳ˵�
		JMenu menu = new JMenu("ͼ����Ϣ����");
		menu.add(MenuActions.BOOK_ADD);		//���ͼ����Ϣ��Ӳ˵���
		menu.add(MenuActions.BOOK_MODIFY);	//���ͼ����Ϣ�޸Ĳ˵���
		//��Ӷ�����Ϣ�����Ӳ˵�
		baseMenu.add(readerManagerMItem);	
		baseMenu.add(bookTypeManageMItem);	//���ͼ���������Ӳ˵�
		baseMenu.add(menu);					//���ͼ����Ϣ�����Ӳ˵�
		baseMenu.addSeparator(); 			//��ӷָ���
		baseMenu.add(MenuActions.EXIT);		//����˳�ϵͳ�˵���
		
		//��ʼ�����鶩������˵�
		JMenu bookOrderMenu = new JMenu();	
		bookOrderMenu.setIcon(CreatecdIcon.add("xsdgcd.jpg"));//���ò˵�ͼ��
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);//������鶩���˵���
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);//�����������˵���
		//��ʼ�����Ĺ���˵�
		JMenu borrowManageMenu = new JMenu();
		borrowManageMenu.setIcon(CreatecdIcon.add("jyglcd.jpg"));
		borrowManageMenu.add(MenuActions.BORROW);//���ͼ����Ĺ���˵���
		borrowManageMenu.add(MenuActions.GIVE_BACK);//���ͼ��黹�˵���
		borrowManageMenu.add(MenuActions.BOOK_SEARCH);//���ͼ�������˵���
		
		//��ʼ��ϵͳά��caidan
		JMenu sysManageMenu = new JMenu();
		sysManageMenu.setIcon(CreatecdIcon.add("jcwhcd.jpg"));
		//�����û������Ӳ˵�
		JMenu userManageMItem = new JMenu("�û�����");
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);//��Ӹ��Ŀ���˵���
		sysManageMenu.add(userManageMItem);//����û������Ӳ˵�
		
		menuBar.add(baseMenu);
		menuBar.add(bookOrderMenu);
		menuBar.add(borrowManageMenu);
		menuBar.add(sysManageMenu);
		
		return menuBar;
	}

	//�����������ķ���
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();	//��ʼ��������
		toolBar.setFloatable(false); 		//�����Ƿ�����ƶ�
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));//���ñ߿�
		
		//ͼ����Ϣ��Ӱ�ť
		JButton bookAddButton = new JButton(MenuActions.BOOK_ADD);//ͼ����Ϣ��Ӱ�ť
		ImageIcon icon = new ImageIcon(Library.class.getResource("/bookAddtb.jpg"));//����ͼ��
		bookAddButton.setIcon(icon);//���ð�ťͼ��
		bookAddButton.setHideActionText(true);//��ʾ��ʾ�ı�
		toolBar.add(bookAddButton);//��ӵ���������
		
		//ͼ����Ϣ�޸İ�ť
		JButton bookModiAndDelButton = new JButton(MenuActions.BOOK_MODIFY);//ͼ����Ϣ����
		ImageIcon bookModiIcon = CreatecdIcon.add("bookModiAndDeltb.jpg");//����ͼ��
		bookModiAndDelButton.setIcon(bookModiIcon);
		bookModiAndDelButton.setHideActionText(true);//��ʾ��ʾ�ı�
		toolBar.add(bookModiAndDelButton);//��ӵ���������
		
		//ͼ�������Ӱ�ť
		JButton bookTypeAddButton = new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddIcon = CreatecdIcon.add("bookTypeAddtb.jpg");
		bookTypeAddButton.setHideActionText(true);
		toolBar.add(bookTypeAddButton);
		
		//ͼ����İ�ť
		JButton bookBorrowButton = new JButton(MenuActions.BORROW);
		ImageIcon bookBorrowIcon = CreatecdIcon.add("bookBorrowtb.jpg");
		bookBorrowButton.setIcon(bookBorrowIcon);
		bookBorrowButton.setHideActionText(true);
		toolBar.add(bookBorrowButton);
		
		//ͼ����İ�ť
		JButton bookOrderButton = new JButton(MenuActions.NEWBOOK_ORDER);
		ImageIcon bookOrderIcon = CreatecdIcon.add("bookOrdertb.jpg");
		bookOrderButton.setIcon(bookOrderIcon);
		bookOrderButton.setHideActionText(true);
		toolBar.add(bookOrderButton);
		
		//�������鰴ť
		JButton bookCheckButton = new JButton(MenuActions.NEWBOOK_CHECK_ACCEPT);
		ImageIcon bookCheckIcon = CreatecdIcon.add("newbookChecktb.jpg");
		bookCheckButton.setIcon(bookCheckIcon);
		bookCheckButton.setHideActionText(true);
		toolBar.add(bookCheckButton);
		
		//������Ϣ��Ӱ�ť
		JButton readerAddButton = new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddIcon = CreatecdIcon.add("readerAddtb.jpg");
		readerAddButton.setIcon(bookCheckIcon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);
		
		//������Ϣ�޸İ�ť
		JButton readerModiAndDelButton = new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelIcon = CreatecdIcon.add("readerModiAndDeltb.jpg");
		readerModiAndDelButton.setIcon(bookCheckIcon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);
				
		//�˳�ϵͳ��ť
		JButton exitButton = new JButton(MenuActions.EXIT);
		ImageIcon exitIcon = CreatecdIcon.add("exittb.jpg");
		exitButton.setIcon(bookCheckIcon);
		exitButton.setHideActionText(true);
		toolBar.add(exitButton);
		
		return toolBar;
	}
}
