package com.kairon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kairon.model.BookInfo;
import com.kairon.model.BookType;
import com.kairon.model.Operator;

public class Dao {
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ���ݿ�����������
	protected static String dbUrl = "jdbc:sqlserver://localhost:1433;"					//1433ΪSQL Server��Ĭ�϶˿�
			+ "DatabaseName=db_library";// ���ݿ�����URL
	protected static String dbUser = "sa"; // ���ݿ��û���
	protected static String dbPwd = "xky940113"; // ���ݿ�����
	private static Connection conn = null; // ���ݿ����Ӷ���

	private Dao() { // Ĭ�Ϲ��캯��					// Ĭ�Ϲ��캯��Ϊprivate������ģ��
		try {
			if (conn == null) { // ������Ӷ���Ϊ��
				Class.forName(dbClassName); // ����������
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd); // ������Ӷ���
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ResultSet executeQuery(String sql) {		//��ѯ����
		try{
			if(conn==null) new Dao();	//���Ӷ���Ϊ�գ����µ��ù��췽��
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(sql);	//ִ�в�ѯ
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			
		}
	}
	
	private static int executeUpdate(String sql) {			//���·���
		try{
			if(conn==null) new Dao();	//������Ӷ���Ϊ�գ������µ��ù��캯������
			return conn.createStatement().executeUpdate(sql);//ִ�и���
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			
		}
	}
	
	public static void close() {	//�رշ���
		try{
			conn.close();//�ر����Ӷ���
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			conn=null;	//�������Ӷ���Ϊnullֵ
		}
	}
	
	//����û���Ϣ�Ƿ�Ϸ�
	public static Operator check(String name,String password) {
		Operator operator = new Operator();//����Ա��Ϣ����
		String sql = "select* from tb_operator where name='"+name
				+"' and password='"+password+"' and admin=1";//��ѯ�ַ���
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {//�����ѯ���˼�¼
				operator.setId(rs.getString("id"));//���ò���Ա���
				operator.setName(rs.getString("name"));//���ò���Ա�û���
				operator.setGrade(rs.getString("admin"));//���ò���Ա�ȼ�
				operator.setPassword(rs.getString("password"));//���ù���Ա����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();//�ر����Ӷ���
		return operator;
	}

	/**
	 * ��ѯ��𷽷�
	 * @return
	 */
	public static List<BookType> selectBookCategory() {
        List<BookType> list=new ArrayList<BookType>();
        String sql = "select *  from tb_bookType";
        ResultSet rs = Dao.executeQuery(sql);
        try {
            while (rs.next()) {
                BookType bookType=new BookType();
                bookType.setId(rs.getString("id"));
                bookType.setTypeName(rs.getString("typeName"));
                bookType.setDays(rs.getString("days"));
                bookType.setFk(rs.getString("fk"));
                list.add(bookType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Dao.close();
        return list;
        
    }

	//����ͼ���Ų�ѯͼ��
	public static List<BookInfo> selectBookInfo(String ISBN) {
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select* from tb_bookInfo where ISBN='"+ISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try{
			while(rs.next()) {
				BookInfo bookInfo = new BookInfo();
				bookInfo.setISBN(rs.getString("ISBN"));
				bookInfo.setTypeid(rs.getString("typeid"));
				bookInfo.setBookname(rs.getString("bookname"));
				bookInfo.setWriter(rs.getString("writer"));
				bookInfo.setTranslator(rs.getString("translator"));
				bookInfo.setPublisher(rs.getString("publisher"));
				bookInfo.setDate(rs.getDate("date"));
				bookInfo.setPrice(rs.getDouble("price"));
				list.add(bookInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//��ѯ����ͼ����Ϣ
	public static List<BookInfo> selectBookInfo() {
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select* from tb_bookInfo";
		ResultSet rs = Dao.executeQuery(sql);
		try{
			while(rs.next()) {
				BookInfo bookInfo = new BookInfo();
				bookInfo.setISBN(rs.getString("ISBN"));
				bookInfo.setTypeid(rs.getString("typeid"));
				bookInfo.setBookname(rs.getString("bookname"));
				bookInfo.setWriter(rs.getString("writer"));
				bookInfo.setTranslator(rs.getString("translator"));
				bookInfo.setPublisher(rs.getString("publisher"));
				bookInfo.setDate(rs.getDate("date"));
				bookInfo.setPrice(rs.getDouble("price"));
				list.add(bookInfo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//����ͼ����Ϣ
	public static int InsertBook(String ISBN,String typeId,String bookname,
			String writer,String translator,String publisher,Date date,Double price) {
		int i = 0;
		String sql = "insert into tb_bookInfo(ISBN,typeId,bookname,writer,translator,"
				+ "publisher,date,price)values('"+ISBN+"','"+typeId+"','"+bookname+"','"+writer+"','"+
				translator+"','"+publisher+"','"+date+"',"+price+")";
		i = Dao.executeUpdate(sql);
		Dao.close();
		return i;
	}
	
	//�޸�ͼ����Ϣ
	public static int UpdateBook(String ISBN,String typeId,String bookname,
			String writer,String translator,String publisher,Date date,Double price) {
		int i = 0;
		String sql = "update tb_bookInfo set ISBN='"+ISBN+"',typeId='"+typeId+"',bookname='"+bookname+"',writer='"
		+writer+"',translator='"+translator+"',publisher='"+publisher+"',date='"+date+"',price="+price+"where ISBN='"+ISBN+"'";
		i = Dao.executeUpdate(sql);
		Dao.close();
		return i;
	}
	
}
