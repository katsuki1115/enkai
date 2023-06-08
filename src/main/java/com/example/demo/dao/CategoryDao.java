package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Repository
public class CategoryDao implements BaseDao<Category> {
	@Autowired
	CategoryRepository repository;
	
	@Override
	public List<Category> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public Category findById(Integer id) throws DataNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Category category) {
		// TODO 自動生成されたメソッド・スタブ
		this.repository.save(category);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Category category = this.findById(id);
			this.repository.deleteById(category.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}

}
