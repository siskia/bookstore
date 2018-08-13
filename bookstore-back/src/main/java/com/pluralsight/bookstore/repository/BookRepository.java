package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import javax.validation.constraints.NotNull;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS) //add transaction strategy to class itself, read only methods
public class BookRepository {
	
	// ======================================
    // =          Injection Points          =
    // ======================================
	
	@PersistenceContext(unitName="bookStorePU")
	private EntityManager em;
	
	// ======================================
    // =          Business methods          =
    // ======================================

	public Book find(@NotNull Long id) {
		return em.find(Book.class, id);
	}
	
	public List<Book> findAll() {
		TypedQuery<Book> query = em.createQuery("SELECT b from Book b order by b.title", Book.class);
		return query.getResultList();
	}
	
	public Long countAll() {
		TypedQuery<Long> query = em.createQuery("SELECT count(b) from Book b", Long.class);
		return query.getSingleResult();
	}
	
	@Transactional(REQUIRED) //write only method
	public Book create(@NotNull Book book) {
		em.persist(book);
		return book;
	}
	
	@Transactional(REQUIRED) //write only method
	public void delete(@NotNull Long id) {
		em.remove(em.getReference(Book.class, id));
	}	
	
}