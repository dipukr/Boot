package com.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;

@Component
public class BookDAO {

	@Autowired
	private EntityManager entityManager;

	public void save(Book book) {
		entityManager.persist(book);
	}

	public void update(Book book) {
		entityManager.persist(book);
	}

	public Book getById(Integer id) {
		return entityManager.find(Book.class, id);
	}

	public void delete(Integer id) {
		Book book = entityManager.find(Book.class, id);
		entityManager.remove(book);
	}
}
