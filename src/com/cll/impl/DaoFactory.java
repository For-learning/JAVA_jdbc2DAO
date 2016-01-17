package com.cll.impl;

import java.io.FileInputStream;
import java.util.Properties;

import com.cll.inte.UserDao;

public class DaoFactory {
	// 只需要一个静态工厂就可以了因此用单例模式
	private static DaoFactory instance = new DaoFactory();
	private static UserDao userDao;

	// 构造方法私有化
	private DaoFactory() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/daoConfig.properties"));
			String userDaoClass = prop.getProperty("userDaoClass");
			Class clazz = Class.forName(userDaoClass);
			userDao = (UserDaoImpl) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 返回静态工厂
	public static DaoFactory getInstance() {
		return instance;
	}

	// 取得UserDao对象
	public UserDao getUserDao() {
		return userDao;
	}
}
