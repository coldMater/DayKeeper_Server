package com.dao;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.Connection;


public class DAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pst;
	private int cnt;

	public void getConn() throws Exception {

		InputStream in = (this.getClass().getResourceAsStream("db.properties"));

		Properties p = new Properties();
		p.load(in);

		String url = p.getProperty("dburl");
		String dbid = p.getProperty("dbid");
		String dbpw = p.getProperty("dbpw");
		Class.forName(p.getProperty("dbclass"));

		// 동적로딩

		conn = DriverManager.getConnection(url, dbid, dbpw);
		System.out.println("DB연결완료");

	}

	public void close() throws Exception {
		if (rs != null)
			rs.close();
		if (pst != null)
			pst.close();
		if (conn != null)
			conn.close();
	}
	
	
	
	
	
	
	
}
