package com.cll.impl2;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cll.domain.User;
import com.cll.inte.UserDao;

public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public void addUser(User user) {

		String sql = "insert into user(id,name,birthday,money) values(?,?,?,?)";
		Object[] objs = new Object[] { user.getId(), user.getName(),
				new java.sql.Date(user.getBirthday().getTime()),
				user.getMoney() };
		super.sqlExecute(sql, objs);
	}

	@Override
	public void delete(User user) {

		String sql = "delete from user where id ='" + user.getId() + "'";
		Object[] objs = new Object[] {};
		super.sqlExecute(sql, objs);
	}

	@Override
	public void update(User user) {

		String sql = "insert into user(id,name,birthday,money) values(?,?,?,?)";
		Object[] objs = new Object[] { user.getName(),
				new java.sql.Date(user.getBirthday().getTime()),
				user.getMoney(), user.getId() };
		super.sqlExecute(sql, objs);
	}

	@Override
	public User findUser(String id) {

		String sql = "select * from user where id=?";
		Object[] objs = new Object[] { id };
		return (User) super.sqlExecute(sql, objs);
	}

	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setBirthday(rs.getDate("birthday"));
		user.setMoney(rs.getFloat("money"));
		return user;
	}
}
