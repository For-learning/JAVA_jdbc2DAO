package com.cll.inte;

import com.cll.domain.User;

public interface UserDao {
	public void addUser(User user);

	public void delete(User user);

	public void update(User user);

	public User findUser(String name);

}
