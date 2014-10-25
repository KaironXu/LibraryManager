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
 * ͼ����Ϣ�޸Ĵ���
 * 
 * @author kairon
 *
 */
public class BookModiAndDelIFrame extends JInternalFrame {
	String[] columnNames = { "ͼ����", "ͼ�����", "ͼ������", "����", "����", "������", "��������",
			"�۸�" };
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

	private Object[][] getFileStates(List<BookInfo> list) {// ȡ���ݿ��������Ϣ��������
		Object[][] results = new Object[list.size()][columnNames.length];// �������м�¼
		for (int i = 0; i < list.size(); i++) {
			BookInfo bookInfo = list.get(i);// ȡ��ͼ���¼
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
		setIconifiable(true);// ���ô������С��
		setClosable(true);// ���ô��ڿɹر�
		setTitle("ͼ����Ϣ�޸�");// ���ô������
		setBounds(100, 100, 593, 406);// ���ô���λ�úʹ�С
		setVisible(true);

		// ��������ͼƬ��ǩ
		final JLabel imageLabel = new JLabel();
		ImageIcon bookModiAndDelIcon = CreatecdIcon.add("bookmodify.jpg");
		imageLabel.setIcon(bookModiAndDelIcon);
		imageLabel.setPreferredSize(new Dimension(400, 80));
		imageLabel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1,
				false));
		getContentPane().add(imageLabel, BorderLayout.NORTH);
		imageLabel.setText("ͼ����Ϣ��ӣ�LOGOͼƬ��");

		// �����
		final JPanel mainPanel = new JPanel();
		final BorderLayout borderLayout_1 = new BorderLayout();// �߿򲼾ֹ�����
		borderLayout.setVgap(5);// �������֮�䴹ֱ����
		mainPanel.setLayout(borderLayout_1);
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));// ����͸���߿�
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		// ͼ����Ϣ�б�
		final JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		Object[][] results = getFileStates(Dao.selectBookInfo());
		table = new JTable(results, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ����Ӧ���壨AUTO_RESIZE_OFF
														// ���Զ������еĿ�ȣ�ʹ�ù�������
		table.addMouseListener(new TableListener());// ������¼������������֣�
		scrollPane.setViewportView(table);

		// ͼ����Ϣ�޸����
		final JPanel bookPanel = new JPanel();
		mainPanel.add(bookPanel, BorderLayout.SOUTH);
		final GridLayout gridLayout = new GridLayout(0, 6);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		bookPanel.setLayout(gridLayout);
		// ����ͼ���ű�ǩ
		final JLabel ISBNLabel = new JLabel();
		ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ISBNLabel.setText("ͼ���ţ�");
		bookPanel.add(ISBNLabel);

		// ��������ı���
		ISBN = new JTextField("������13λ���", 13);
		ISBN.setDocument(new MyDocument(13));// ��������ı����������ֵΪ13
		ISBN.setColumns(13);// �����ı��򳤶�
		ISBN.addKeyListener(new ISBNKeyListener());
		ISBN.addFocusListener(new ISBNFocusListener());
		bookPanel.add(ISBN);

		// �����鼮����ǩ
		final JLabel bookTypeLabel = new JLabel();
		bookTypeLabel.setText("��  ��");
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���뷽ʽ��ˮƽ���У�
		bookPanel.add(bookTypeLabel);

		// �����鼮���������
		bookType = new JComboBox<Item>();
//		bookTypeModel = (DefaultComboBoxModel<Item>)bookType.getModel();//���ģ��
		List<BookType> list = Dao.selectBookCategory();// �����ݿ���ȡ��ͼ�����
		for (int i = 0; i < list.size(); i++) { // ����ͼ�����
			BookType bt = (BookType) list.get(i);
			Item item = new Item();// ʵ����ͼ�����ѡ��
			item.setId((String) bt.getId());// ����ͼ�������
			item.setName((String) bt.getTypeName());// ����ͼ���������
			bookType.addItem(item);
//			bookTypeModel.addElement(item);
		}
		bookPanel.add(bookType);

		// ����������ǩ
		final JLabel bookNameLabel = new JLabel();
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameLabel.setText("������");
		bookPanel.add(bookNameLabel);

		// ���������ı���
		bookName = new JTextField();
		bookPanel.add(bookName);

		// �������߱�ǩ
		final JLabel writerLabel = new JLabel();
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���뷽ʽ��ˮƽ���У�
		writerLabel.setText("��    �ߣ�");
		bookPanel.add(writerLabel);

		// ���������ı���
		writer = new JTextField();
		writer.setDocument(new MyDocument(10));// �����ı����������ֵΪ10
		bookPanel.add(writer);

		// �����������ǩ
		final JLabel publisherLabel = new JLabel();
		publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���뷽ʽ��ˮƽ���У�
		publisherLabel.setText("�����磺");
		bookPanel.add(publisherLabel);

		// �����������ı���
		publisher = new JTextField();
		bookPanel.add(publisher);

		// �������߱�ǩ
		final JLabel translatorLabel = new JLabel();
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		translatorLabel.setText("���ߣ�");
		bookPanel.add(translatorLabel);

		// ���������ı���
		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		bookPanel.add(translator);

		// �����������ڱ�ǩ
		final JLabel pubDateLabel = new JLabel();
		pubDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pubDateLabel.setText("�������ڣ�");
		bookPanel.add(pubDateLabel);

		// �����������������
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");// ���ڸ�ʽ
		pubDate = new JFormattedTextField(myfmt.getDateInstance());
//		pubDate.setValue(new java.util.Date());// ����Ϊ��ǰʵ������
		bookPanel.add(pubDate);

		// �����۸��ǩ
		final JLabel priceLabel = new JLabel();
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setText("��  �ۣ�");
		bookPanel.add(priceLabel);

		// �����۸��ı������
		price = new JTextField();
		price.setDocument(new MyDocument(5));
		price.addKeyListener(new NumberListener());
		bookPanel.add(price);

		// �����ײ���ť���
		final JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder,
				1, false));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();// �����ֹ�����
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);// ���ö��뷽ʽ
		bottomPanel.setLayout(flowLayout);
		final JButton buttonUpdate = new JButton();
		buttonUpdate.addActionListener(new UpdateBookActionListener());
		buttonUpdate.setText("�޸�");
		bottomPanel.add(buttonUpdate);
		final JButton buttonClose = new JButton();
		buttonClose.addActionListener(new CloseActionListener());
		buttonClose.setText("�ر�");
		bottomPanel.add(buttonClose);
	}

	// ����¼�������
	private class TableListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			String ISBNs,bookTypes, bookNames, writers, translators, publishers,pubDates,prices;
			
			//��ȡͼ����Ϣ
			int selRow = table.getSelectedRow();//�����ѡ�к�
			ISBNs = table.getValueAt(selRow, 0).toString().trim();
			bookTypes = table.getValueAt(selRow, 1).toString().trim();
			bookNames = table.getValueAt(selRow, 2).toString().trim();
			writers = table.getValueAt(selRow, 3).toString().trim();
			translators = table.getValueAt(selRow, 4).toString().trim();
			publishers = table.getValueAt(selRow, 5).toString().trim();
			pubDates = table.getValueAt(selRow, 6).toString().trim();
			prices = table.getValueAt(selRow, 7).toString().trim();
			
			//����ͼ����Ϣ
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

	// ISBNKeyListener������
	private class ISBNKeyListener extends KeyAdapter {

	}

	// ISBNFocusListener������
	private class ISBNFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "�������ظ�");
				return;
			}
		}
	}

	// �۸��ı��������
	private class NumberListener extends KeyAdapter {

	}

	// �޸İ�ť������
	private class UpdateBookActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String ISBNs, bookTypes, translators, bookNames, writers, publishers, pubDates, prices;

			if ((ISBNs = ISBN.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			if (ISBNs.length() != 13) {
				JOptionPane.showMessageDialog(null, "����ı�������λ������Ϊ13λ");
				return;
			}
			if ((bookNames = bookName.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "ͼ���ı��򲻿���Ϊ��");
				return;
			}
			if ((writers = writer.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if ((pubDates = pubDate.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "���������ı��򲻿���Ϊ��");
				return;
			}
			if ((prices = price.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			publishers = (String) publisher.getText().trim();
			pubDates = pubDate.getText().trim();

			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
			{
				JOptionPane.showMessageDialog(null, "δѡ��ͼ�����");
				return;
			}
			Item item = (Item)selectedItem; // ������ѡ��
			bookTypes = item.getId();
			translators = translator.getText().trim();

			int i = Dao.UpdateBook(ISBNs, bookTypes, bookNames, writers,
					translators, publishers, java.sql.Date.valueOf(pubDates),
					Double.parseDouble(prices));
			doDefaultCloseAction();
		}
	}

	// �رհ�ť������
	private class CloseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

	}
}
