/**
 * 
 */
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.kairon.JComPz.Item;
import com.kairon.JComPz.MapPz;
import com.kairon.dao.Dao;
import com.kairon.model.BookInfo;
import com.kairon.model.BookType;
import com.kairon.util.CreatecdIcon;
import com.kairon.util.MyDocument;

/**
 * 图书信息修改窗体
 * 
 * @author kairon
 *
 */
public class BookModiAndDelIFrame extends JInternalFrame {
	String[] columnNames = { "图书编号", "图书类别", "图书名称", "作者", "译者", "出版商", "出版日期",
			"价格" };
	private JTable table;
	private JTextField ISBN;
	private JTextField bookName;
	private JTextField writer;
	private JTextField translator;
	private JTextField price;
	private JTextField publisher;
	private JFormattedTextField pubDate;
	private JComboBox<Item> bookType;
	private DefaultComboBoxModel<Item> bookTypeModel;

	private Object[][] getFileStates(List<BookInfo> list) {// 取数据库中相关信息放入表格中
		Object[][] results = new Object[list.size()][columnNames.length];// 保存所有记录
		for (int i = 0; i < list.size(); i++) {
			BookInfo bookInfo = list.get(i);// 取出图书记录
			results[i][0] = bookInfo.getISBN();
			String bookTypeName = String.valueOf(MapPz.getMap().get(
					bookInfo.getTypeid()));
			results[i][1] = bookTypeName;
			results[i][2] = bookInfo.getBookname();
			results[i][3] = bookInfo.getWriter();
			results[i][4] = bookInfo.getTranslator();
			results[i][5] = bookInfo.getPublisher();
			results[i][6] = bookInfo.getDate();
			results[i][7] = bookInfo.getPrice();
		}
		return results;
	}

	public BookModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);// 设置窗体可最小化
		setClosable(true);// 设置窗口可关闭
		setTitle("图书信息修改");// 设置窗体标题
		setBounds(100, 100, 593, 406);// 设置窗体位置和大小
		setVisible(true);

		// 创建顶部图片标签
		final JLabel imageLabel = new JLabel();
		ImageIcon bookModiAndDelIcon = CreatecdIcon.add("bookmodify.jpg");
		imageLabel.setIcon(bookModiAndDelIcon);
		imageLabel.setPreferredSize(new Dimension(400, 80));
		imageLabel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1,
				false));
		getContentPane().add(imageLabel, BorderLayout.NORTH);
		imageLabel.setText("图书信息添加（LOGO图片）");

		// 主面板
		final JPanel mainPanel = new JPanel();
		final BorderLayout borderLayout_1 = new BorderLayout();// 边框布局管理器
		borderLayout.setVgap(5);// 设置组件之间垂直距离
		mainPanel.setLayout(borderLayout_1);
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));// 设置透明边框
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		// 图书信息列表
		final JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		Object[][] results = getFileStates(Dao.selectBookInfo());
		table = new JTable(results, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 自适应窗体（AUTO_RESIZE_OFF
														// 不自动调整列的宽度；使用滚动条）
		table.addMouseListener(new TableListener());// 绑定鼠标事件监听器（滚轮）
		scrollPane.setViewportView(table);

		// 图书信息修改面板
		final JPanel bookPanel = new JPanel();
		mainPanel.add(bookPanel, BorderLayout.SOUTH);
		final GridLayout gridLayout = new GridLayout(0, 6);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		bookPanel.setLayout(gridLayout);
		// 创建图书编号标签
		final JLabel ISBNLabel = new JLabel();
		ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ISBNLabel.setText("图书编号：");
		bookPanel.add(ISBNLabel);

		// 创建书号文本框
		ISBN = new JTextField("请输入13位书号", 13);
		ISBN.setDocument(new MyDocument(13));// 书号输入文本框最大输入值为13
		ISBN.setColumns(13);// 设置文本框长度
		ISBN.addKeyListener(new ISBNKeyListener());
		ISBN.addFocusListener(new ISBNFocusListener());
		bookPanel.add(ISBN);

		// 创建书籍类别标签
		final JLabel bookTypeLabel = new JLabel();
		bookTypeLabel.setText("类  别：");
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);// 对齐方式（水平居中）
		bookPanel.add(bookTypeLabel);

		// 创建书籍类别下拉框
		bookType = new JComboBox<Item>();
//		bookTypeModel = (DefaultComboBoxModel<Item>)bookType.getModel();//类别模型
		List<BookType> list = Dao.selectBookCategory();// 从数据库中取出图书类别
		for (int i = 0; i < list.size(); i++) { // 遍历图书类别
			BookType bt = (BookType) list.get(i);
			Item item = new Item();// 实例化图书类别选项
			item.setId((String) bt.getId());// 设置图书类别编号
			item.setName((String) bt.getTypeName());// 设置图书类别名称
			bookType.addItem(item);
//			bookTypeModel.addElement(item);
		}
		bookPanel.add(bookType);

		// 设置书名标签
		final JLabel bookNameLabel = new JLabel();
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setText("书名：");
		bookPanel.add(bookNameLabel);

		// 创建书名文本框
		bookName = new JTextField();
		bookPanel.add(bookName);

		// 创建作者标签
		final JLabel writerLabel = new JLabel();
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);// 对齐方式（水平居中）
		writerLabel.setText("作    者：");
		bookPanel.add(writerLabel);

		// 创建作者文本框
		writer = new JTextField();
		writer.setDocument(new MyDocument(10));// 作者文本框最大输入值为10
		bookPanel.add(writer);

		// 创建出版社标签
		final JLabel publisherLabel = new JLabel();
		publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);// 对齐方式（水平居中）
		publisherLabel.setText("出版社：");
		bookPanel.add(publisherLabel);

		// 创建出版社文本框
		publisher = new JTextField();
		bookPanel.add(publisher);

		// 创建译者标签
		final JLabel translatorLabel = new JLabel();
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		translatorLabel.setText("译者：");
		bookPanel.add(translatorLabel);

		// 创建译者文本框
		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		bookPanel.add(translator);

		// 创建出版日期标签
		final JLabel pubDateLabel = new JLabel();
		pubDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pubDateLabel.setText("出版日期：");
		bookPanel.add(pubDateLabel);

		// 创建出版日期输入框
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");// 日期格式
		pubDate = new JFormattedTextField(myfmt.getDateInstance());
//		pubDate.setValue(new java.util.Date());// 日期为当前实际日期
		bookPanel.add(pubDate);

		// 创建价格标签
		final JLabel priceLabel = new JLabel();
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setText("单  价：");
		bookPanel.add(priceLabel);

		// 创建价格文本输入框
		price = new JTextField();
		price.setDocument(new MyDocument(5));
		price.addKeyListener(new NumberListener());
		bookPanel.add(price);

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
		final JButton buttonUpdate = new JButton();
		buttonUpdate.addActionListener(new UpdateBookActionListener());
		buttonUpdate.setText("修改");
		bottomPanel.add(buttonUpdate);
		final JButton buttonClose = new JButton();
		buttonClose.addActionListener(new CloseActionListener());
		buttonClose.setText("关闭");
		bottomPanel.add(buttonClose);
	}

	// 鼠标事件监听器
	private class TableListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			String ISBNs,bookTypes, bookNames, writers, translators, publishers,pubDates,prices;
			
			//获取图书信息
			int selRow = table.getSelectedRow();//获得所选行号
			ISBNs = table.getValueAt(selRow, 0).toString().trim();
			bookTypes = table.getValueAt(selRow, 1).toString().trim();
			bookNames = table.getValueAt(selRow, 2).toString().trim();
			writers = table.getValueAt(selRow, 3).toString().trim();
			translators = table.getValueAt(selRow, 4).toString().trim();
			publishers = table.getValueAt(selRow, 5).toString().trim();
			pubDates = table.getValueAt(selRow, 6).toString().trim();
			prices = table.getValueAt(selRow, 7).toString().trim();
			
			//设置图书信息
			ISBN.setText(ISBNs);
//			bookTypeModel.setSelectedItem(bookTypes);
			bookType.setSelectedItem(bookTypes);
			bookName.setText(bookNames);
			writer.setText(writers);
			translator.setText(translators);
			publisher.setText(publishers);
			pubDate.setText(pubDates);
			price.setText(prices);
			
		}

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

	// 修改按钮监听器
	private class UpdateBookActionListener implements ActionListener {

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
			publishers = (String) publisher.getText().trim();
			pubDates = pubDate.getText().trim();

			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
			{
				JOptionPane.showMessageDialog(null, "未选择图书类别");
				return;
			}
			Item item = (Item)selectedItem; // 获得类别选项
			bookTypes = item.getId();
			translators = translator.getText().trim();

			int i = Dao.UpdateBook(ISBNs, bookTypes, bookNames, writers,
					translators, publishers, java.sql.Date.valueOf(pubDates),
					Double.parseDouble(prices));
			doDefaultCloseAction();
		}
	}

	// 关闭按钮监听器
	private class CloseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

	}
}
