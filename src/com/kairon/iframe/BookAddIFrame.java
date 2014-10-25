package com.kairon.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;

import com.kairon.JComPz.Item;
import com.kairon.dao.Dao;
import com.kairon.model.BookType;
import com.kairon.util.CreatecdIcon;
import com.kairon.util.MyDocument;

/**
 * 图书信息添加窗体
 * 
 * @author kairon
 *
 */

public class BookAddIFrame extends JInternalFrame {
	private JTextField ISBN;
	private JTextField bookName;
	private JTextField writer;
	private JTextField translator;
	private JTextField price;
	private JFormattedTextField pubDate;
	private JComboBox bookType;
	private JComboBox publisher;
	// private DefaultComboBoxModel bookTypeModel;
	private JButton buttonAdd;
	private JButton buttonClose;

	public BookAddIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout(); // 边框布局管理器
		getContentPane().setLayout(borderLayout);// 设置布局
		setIconifiable(true);// 设置窗体可最小化
		setClosable(true);// 设置窗体可关闭
		setTitle("图书信息添加");
		setBounds(100, 100, 396, 260);// 设置窗体的位置和大小
		setVisible(true);

		// 创建顶部图片标签
		final JLabel imageLabel = new JLabel();
		ImageIcon bookAddIcon = CreatecdIcon.add("bookAdd.jpg");
		imageLabel.setIcon(bookAddIcon);
		imageLabel.setPreferredSize(new Dimension(400, 80));
		imageLabel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1,
				false));
		getContentPane().add(imageLabel, BorderLayout.NORTH);
		imageLabel.setText("图书信息添加（LOGO图片）");

		// 创建中心面板
		final JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));// 设置透明边框
		final GridLayout gridLayout = new GridLayout(0, 4);// 创建表格布局管理器,0表示行数可以为任意数
		gridLayout.setVgap(5);// 设置组件之间垂直距离
		gridLayout.setHgap(5);// 设置组件之间水平距离
		mainPanel.setLayout(gridLayout);// 设置布局
		getContentPane().add(mainPanel, BorderLayout.CENTER);// 将中心面板加入到窗体

		// 创建图书编号标签
		final JLabel ISBNLabel = new JLabel();
		ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		ISBNLabel.setAlignmentY(SwingConstants.CENTER);
		ISBNLabel.setText("图书编号：");
		mainPanel.add(ISBNLabel);

		// 创建书号文本框
		ISBN = new JTextField("请输入13位书号", 13);
		ISBN.setDocument(new MyDocument(13));// 书号输入文本框最大输入值为13
		ISBN.setColumns(13);// 设置文本框长度
		ISBN.addKeyListener(new ISBNKeyListener());
		ISBN.addFocusListener(new ISBNFocusListener());
		mainPanel.add(ISBN);

		// 创建书籍类别标签
		final JLabel bookTypeLabel = new JLabel();
		bookTypeLabel.setText("类别：");
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);// 对齐方式（水平居中）
		mainPanel.add(bookTypeLabel);

		// 创建书籍类别下拉框
		bookType = new JComboBox();
		// bookTypeModel = (DefaultComboBoxModel)bookType.getModel();//类别模型
		List<BookType> list = Dao.selectBookCategory();// 从数据库中取出图书类别
		for (int i = 0; i < list.size(); i++) { // 遍历图书类别
			BookType bt = (BookType) list.get(i);
			Item item = new Item();// 实例化图书类别选项
			item.setId((String) bt.getId());// 设置图书类别编号
			item.setName((String) bt.getTypeName());// 设置图书类别名称
			bookType.addItem(item);
			// bookTypeModel.addElement(item);
		}
		mainPanel.add(bookType);

		// 设置书名标签
		final JLabel bookNameLabel = new JLabel();
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);// 对齐方式（水平居中）
		bookNameLabel.setText("书    名：");
		mainPanel.add(bookNameLabel);

		// 创建书名文本框
		bookName = new JTextField();
		mainPanel.add(bookName);

		// 创建作者标签
		final JLabel writerLabel = new JLabel();
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);// 对齐方式（水平居中）
		writerLabel.setText("作者：");
		mainPanel.add(writerLabel);

		// 创建作者文本框
		writer = new JTextField();
		writer.setDocument(new MyDocument(10));// 作者文本框最大输入值为10
		mainPanel.add(writer);

		// 创建出版社标签
		final JLabel publisherLabel = new JLabel();
		publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);// 对齐方式（水平居中）
		publisherLabel.setText("出 版 社：");
		mainPanel.add(publisherLabel);

		// 创建出版社下拉框
		publisher = new JComboBox();
		String[] array = new String[] { "北京出版社", "天津出版社", "上海出版社", "重庆出版社" };
		publisher.setModel(new DefaultComboBoxModel(array));
		mainPanel.add(publisher);

		// 创建译者标签
		final JLabel translatorLabel = new JLabel();
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		translatorLabel.setText("译者：");
		mainPanel.add(translatorLabel);

		// 创建译者文本框
		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		mainPanel.add(translator);

		// 创建出版日期标签
		final JLabel pubDateLabel = new JLabel();
		pubDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pubDateLabel.setText("出版日期：");
		mainPanel.add(pubDateLabel);

		// 创建出版日期输入框
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");// 日期格式
		pubDate = new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());// 日期为当前实际日期
		mainPanel.add(pubDate);

		// 创建价格标签
		final JLabel priceLabel = new JLabel();
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setText("单价：");
		mainPanel.add(priceLabel);

		// 创建价格文本输入框
		price = new JTextField();
		price.setDocument(new MyDocument(5));
		price.addKeyListener(new NumberListener());
		mainPanel.add(price);

		// 创建底部按钮面板
		final JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder,
				1, false));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();// 流布局管理器
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);// 设置对齐方式
		bottomPanel.setLayout(flowLayout);
		buttonAdd = new JButton();
		buttonAdd.addActionListener(new AddBookActionListener());
		buttonAdd.setText("添加");
		bottomPanel.add(buttonAdd);
		buttonClose = new JButton();
		buttonClose.addActionListener(new CloseActionListener());
		buttonClose.setText("关闭");
		bottomPanel.add(buttonClose);
	}

	// ISBNKeyListener监听器
	private class ISBNKeyListener extends KeyAdapter {

	}

	// ISBNFocusListener监听器
	private class ISBNFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "添加书号重复");
				return;
			}
		}
	}

	// 价格文本框监听器
	private class NumberListener extends KeyAdapter {

	}

	// 添加关闭按钮的事件监听器
	private class CloseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}

	}

	// 添加按钮的单击事件监听器
	private class AddBookActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String ISBNs, bookTypes, translators, bookNames, writers, publishers, pubDates, prices;

			if ((ISBNs = ISBN.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
				return;
			}
			if (ISBNs.length() != 13) {
				JOptionPane.showMessageDialog(null, "书号文本框输入位数必须为13位");
				return;
			}
			if ((bookNames = bookName.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "图书文本框不可以为空");
				return;
			}
			if ((writers = writer.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "作者文本框不可以为空");
				return;
			}
			if ((pubDates = pubDate.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "出版日期文本框不可以为空");
				return;
			}
			if ((prices = price.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "单价文本框不可以为空");
				return;
			}
			publishers = (String) publisher.getSelectedItem();
			pubDates = pubDate.getText().trim();

			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem; // 获得类别选项
			bookTypes = item.getId();
			translators = translator.getText().trim();

			int i = Dao.InsertBook(ISBNs, bookTypes, bookNames, writers,
					translators, publishers, java.sql.Date.valueOf(pubDates),
					Double.parseDouble(prices));
			doDefaultCloseAction();
		}
	}

}
