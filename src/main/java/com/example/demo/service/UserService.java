package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.User;

@Service
public class UserService implements BaseService<User> {
	@Autowired
	private BaseDao<User> dao;
	
	@Override
	public List<User> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findAll();
	}

	@Override
	public User findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return dao.findById(id);
	}

	@Override
	public void save(User user) {
		// TODO 自動生成されたメソッド・スタブ
		dao.save(user);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		dao.deleteById(id);
	}

}
