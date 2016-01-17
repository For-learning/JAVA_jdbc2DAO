package com.cll.impl2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cll.jdbc.JDBCUtial;

public abstract class AbstractDao {

	public Object sqlExecute(String sql, Object[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object obj = null;

		try {
			// 获取连接
			conn = JDBCUtial.getConnection();

			// 执行SQL语句
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject((i + 1), args[i]);
			}
			rs = ps.executeQuery();

			// 处理结果
			while (rs.next()) {
				obj = rowMapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtial.free(rs, ps, conn);
		}
		return obj;

	}

	// 抽象方法，只能在其继承的子类中执行
	abstract protected Object rowMapper(ResultSet rs) throws SQLException;

}
