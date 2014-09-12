package com.haier.openplatform.hopdeploy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static String user;
	private static String password;
	private static String url;
	private static String driverName;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		try {
			user = "autodeploy";
			password = "autodeploy";
			url = "jdbc:oracle:thin:@10.135.13.34:1521:HOPDB";
			driverName = "oracle.jdbc.OracleDriver";
			Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取数据库配置文件失败！");
		}
	}

	public static Connection getConnection() {
		Connection con = tl.get();
		if (con == null) {
			try {
				con = DriverManager.getConnection(url, user, password);
				tl.set(con);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("获取连接失败！");
			}
		}
		return con;
	}

	public static void closeConnection() {
		Connection con = tl.get();
		if (con != null) {
			try {
				con.close();
				tl.set(null);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭连接失败！");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Connection con = DBUtil.getConnection();
		System.out.println(con);
	}

}