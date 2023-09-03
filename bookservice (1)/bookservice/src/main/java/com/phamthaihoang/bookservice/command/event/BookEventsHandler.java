package com.phamthaihoang.bookservice.command.event;


import com.phamthaihoang.bookservice.command.data.Book;
import com.phamthaihoang.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {

	@Autowired
	private BookRepository bookRepository;
	
	@EventHandler
    public void on(BookCreatedEvent event) {
		System.out.println("4");
       Book book = new Book();
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
    }
	@EventHandler
    public void on(BookUpdatedEvent event) {
		System.out.println("4U");
		 Book book = bookRepository.getById(event.getBookId());
	       book.setAuthor(event.getAuthor());
	       book.setName(event.getName());
	       book.setIsReady(event.getIsReady());
	        bookRepository.save(book);
    }
	@EventHandler
    public void on(BookDeletedEvent event) {
		
	        bookRepository.deleteById(event.getBookId());;
    }
//	@EventHandler
//	public void on(BookUpdateStatusEvent event) {
//		Book book = bookRepository.getById(event.getBookId());
//		book.setIsReady(event.getIsReady());
//		bookRepository.save(book);
//	}
//	@EventHandler
//	public void on(BookRollBackStatusEvent event) {
//		Book book = bookRepository.getById(event.getBookId());
//		book.setIsReady(event.getIsReady());
//		bookRepository.save(book);
//	}
}
