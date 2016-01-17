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
			// ��ȡ����
			conn = JDBCUtial.getConnection();

			// ִ��SQL���
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject((i + 1), args[i]);
			}
			rs = ps.executeQuery();

			// ������
			while (rs.next()) {
				obj = rowMapper(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtial.free(rs, ps, conn);
		}
		return obj;

	}

	// ���󷽷���ֻ������̳е�������ִ��
	abstract protected Object rowMapper(ResultSet rs) throws SQLException;

}
