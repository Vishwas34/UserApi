package com.news;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface MyService extends CrudRepository<News , Integer>{
	List<News> findAll();
	void deleteById(int id);
	News findNewsById(int id);
	
	@Query("from News where title LIKE %?1%")
	List<News> findByTitle(String title);
	
	@Query("from News where author LIKE %?1%")
	List<News> findByAuthor(String author);
	
	@Query("from News where category LIKE %?1%")
	List<News> findByCategory(String category);
	
	@Query("from News where language LIKE %?1%")
	List<News> findByLanguage(String language);
	
	@Query("from News where pdate LIKE %?1%")
	List<News> findByPDate(String pdate);
	
	
	

}
