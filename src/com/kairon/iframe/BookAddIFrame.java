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
 * ͼ����Ϣ��Ӵ���
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
		final BorderLayout borderLayout = new BorderLayout(); // �߿򲼾ֹ�����
		getContentPane().setLayout(borderLayout);// ���ò���
		setIconifiable(true);// ���ô������С��
		setClosable(true);// ���ô���ɹر�
		setTitle("ͼ����Ϣ���");
		setBounds(100, 100, 396, 260);// ���ô����λ�úʹ�С
		setVisible(true);

		// ��������ͼƬ��ǩ
		final JLabel imageLabel = new JLabel();
		ImageIcon bookAddIcon = CreatecdIcon.add("bookAdd.jpg");
		imageLabel.setIcon(bookAddIcon);
		imageLabel.setPreferredSize(new Dimension(400, 80));
		imageLabel.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1,
				false));
		getContentPane().add(imageLabel, BorderLayout.NORTH);
		imageLabel.setText("ͼ����Ϣ��ӣ�LOGOͼƬ��");

		// �����������
		final JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));// ����͸���߿�
		final GridLayout gridLayout = new GridLayout(0, 4);// ������񲼾ֹ�����,0��ʾ��������Ϊ������
		gridLayout.setVgap(5);// �������֮�䴹ֱ����
		gridLayout.setHgap(5);// �������֮��ˮƽ����
		mainPanel.setLayout(gridLayout);// ���ò���
		getContentPane().add(mainPanel, BorderLayout.CENTER);// �����������뵽����

		// ����ͼ���ű�ǩ
		final JLabel ISBNLabel = new JLabel();
		ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		ISBNLabel.setAlignmentY(SwingConstants.CENTER);
		ISBNLabel.setText("ͼ���ţ�");
		mainPanel.add(ISBNLabel);

		// ��������ı���
		ISBN = new JTextField("������13λ���", 13);
		ISBN.setDocument(new MyDocument(13));// ��������ı����������ֵΪ13
		ISBN.setColumns(13);// �����ı��򳤶�
		ISBN.addKeyListener(new ISBNKeyListener());
		ISBN.addFocusListener(new ISBNFocusListener());
		mainPanel.add(ISBN);

		// �����鼮����ǩ
		final JLabel bookTypeLabel = new JLabel();
		bookTypeLabel.setText("���");
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���뷽ʽ��ˮƽ���У�
		mainPanel.add(bookTypeLabel);

		// �����鼮���������
		bookType = new JComboBox();
		// bookTypeModel = (DefaultComboBoxModel)bookType.getModel();//���ģ��
		List<BookType> list = Dao.selectBookCategory();// �����ݿ���ȡ��ͼ�����
		for (int i = 0; i < list.size(); i++) { // ����ͼ�����
			BookType bt = (BookType) list.get(i);
			Item item = new Item();// ʵ����ͼ�����ѡ��
			item.setId((String) bt.getId());// ����ͼ�������
			item.setName((String) bt.getTypeName());// ����ͼ���������
			bookType.addItem(item);
			// bookTypeModel.addElement(item);
		}
		mainPanel.add(bookType);

		// ����������ǩ
		final JLabel bookNameLabel = new JLabel();
		bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���뷽ʽ��ˮƽ���У�
		bookNameLabel.setText("��    ����");
		mainPanel.add(bookNameLabel);

		// ���������ı���
		bookName = new JTextField();
		mainPanel.add(bookName);

		// �������߱�ǩ
		final JLabel writerLabel = new JLabel();
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���뷽ʽ��ˮƽ���У�
		writerLabel.setText("���ߣ�");
		mainPanel.add(writerLabel);

		// ���������ı���
		writer = new JTextField();
		writer.setDocument(new MyDocument(10));// �����ı����������ֵΪ10
		mainPanel.add(writer);

		// �����������ǩ
		final JLabel publisherLabel = new JLabel();
		publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���뷽ʽ��ˮƽ���У�
		publisherLabel.setText("�� �� �磺");
		mainPanel.add(publisherLabel);

		// ����������������
		publisher = new JComboBox();
		String[] array = new String[] { "����������", "��������", "�Ϻ�������", "���������" };
		publisher.setModel(new DefaultComboBoxModel(array));
		mainPanel.add(publisher);

		// �������߱�ǩ
		final JLabel translatorLabel = new JLabel();
		translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		translatorLabel.setText("���ߣ�");
		mainPanel.add(translatorLabel);

		// ���������ı���
		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		mainPanel.add(translator);

		// �����������ڱ�ǩ
		final JLabel pubDateLabel = new JLabel();
		pubDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pubDateLabel.setText("�������ڣ�");
		mainPanel.add(pubDateLabel);

		// �����������������
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");// ���ڸ�ʽ
		pubDate = new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());// ����Ϊ��ǰʵ������
		mainPanel.add(pubDate);

		// �����۸��ǩ
		final JLabel priceLabel = new JLabel();
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setText("���ۣ�");
		mainPanel.add(priceLabel);

		// �����۸��ı������
		price = new JTextField();
		price.setDocument(new MyDocument(5));
		price.addKeyListener(new NumberListener());
		mainPanel.add(price);

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
		buttonAdd = new JButton();
		buttonAdd.addActionListener(new AddBookActionListener());
		buttonAdd.setText("���");
		bottomPanel.add(buttonAdd);
		buttonClose = new JButton();
		buttonClose.addActionListener(new CloseActionListener());
		buttonClose.setText("�ر�");
		bottomPanel.add(buttonClose);
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

	// ��ӹرհ�ť���¼�������
	private class CloseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}

	}

	// ��Ӱ�ť�ĵ����¼�������
	private class AddBookActionListener implements ActionListener {

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
			publishers = (String) publisher.getSelectedItem();
			pubDates = pubDate.getText().trim();

			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem; // ������ѡ��
			bookTypes = item.getId();
			translators = translator.getText().trim();

			int i = Dao.InsertBook(ISBNs, bookTypes, bookNames, writers,
					translators, publishers, java.sql.Date.valueOf(pubDates),
					Double.parseDouble(prices));
			doDefaultCloseAction();
		}
	}

}
