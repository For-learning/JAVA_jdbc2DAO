package com.cll.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cll.domain.User;
import com.cll.inte.UserDao;
import com.cll.jdbc.JDBCUtial;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtial.getConnection();

			String sql = "insert into user(id,name,birthday,money) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(4, user.getMoney());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtial.free(rs, ps, conn);
		}

	}

	@Override
	public void delete(User user) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtial.getConnection();
			ps = conn.createStatement();
			String sql = "delete from user where id ='" + user.getId() + "'";

			ps.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtial.free(rs, ps, conn);
		}

	}

	@Override
	public void update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtial.getConnection();

			String sql = "update user set name=?,birthday=?,money=? where id=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getName());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.setString(4, user.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtial.free(rs, ps, conn);
		}

	}

	@Override
	public User findUser(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JDBCUtial.getConnection();

			String sql = "select * from user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				user = mappingUser(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtial.free(rs, ps, conn);
		}
		return user;
	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setBirthday(rs.getDate("birthday"));
		user.setMoney(rs.getFloat("money"));
		return user;
	}
}
