package com.cll.impl;

import java.io.FileInputStream;
import java.util.Properties;

import com.cll.inte.UserDao;

public class DaoFactory {
	// ֻ��Ҫһ����̬�����Ϳ���������õ���ģʽ
	private static DaoFactory instance = new DaoFactory();
	private static UserDao userDao;

	// ���췽��˽�л�
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

	// ���ؾ�̬����
	public static DaoFactory getInstance() {
		return instance;
	}

	// ȡ��UserDao����
	public UserDao getUserDao() {
		return userDao;
	}
}
