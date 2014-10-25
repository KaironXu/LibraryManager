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
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 数据库连接驱动类
	protected static String dbUrl = "jdbc:sqlserver://localhost:1433;"					//1433为SQL Server的默认端口
			+ "DatabaseName=db_library";// 数据库连接URL
	protected static String dbUser = "sa"; // 数据库用户名
	protected static String dbPwd = "xky940113"; // 数据库密码
	private static Connection conn = null; // 数据库连接对象

	private Dao() { // 默认构造函数					// 默认构造函数为private，单例模型
		try {
			if (conn == null) { // 如果连接对象为空
				Class.forName(dbClassName); // 加载驱动类
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd); // 获得连接对象
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ResultSet executeQuery(String sql) {		//查询方法
		try{
			if(conn==null) new Dao();	//连接对象为空，重新调用构造方法
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(sql);	//执行查询
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			
		}
	}
	
	private static int executeUpdate(String sql) {			//更新方法
		try{
			if(conn==null) new Dao();	//如果连接对象为空，则重新调用构造函数方法
			return conn.createStatement().executeUpdate(sql);//执行更新
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			
		}
	}
	
	public static void close() {	//关闭方法
		try{
			conn.close();//关闭连接对象
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			conn=null;	//设置连接对象为null值
		}
	}
	
	//检测用户信息是否合法
	public static Operator check(String name,String password) {
		Operator operator = new Operator();//操作员信息对象
		String sql = "select* from tb_operator where name='"+name
				+"' and password='"+password+"' and admin=1";//查询字符串
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {//如果查询到了记录
				operator.setId(rs.getString("id"));//设置操作员编号
				operator.setName(rs.getString("name"));//设置操作员用户名
				operator.setGrade(rs.getString("admin"));//设置操作员等级
				operator.setPassword(rs.getString("password"));//设置管理员密码
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dao.close();//关闭连接对象
		return operator;
	}

	/**
	 * 查询类别方法
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

	//根据图书编号查询图书
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
	
	//查询所有图书信息
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
	
	//保存图书信息
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
	
	//修改图书信息
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
