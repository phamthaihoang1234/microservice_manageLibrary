package com.phamthaihoang.bookservice.command.aggregate;


import com.phamthaihoang.bookservice.command.command.CreateBookCommand;
import com.phamthaihoang.bookservice.command.command.DeleteBookCommand;
import com.phamthaihoang.bookservice.command.command.UpdateBookCommand;
import com.phamthaihoang.bookservice.command.event.BookCreatedEvent;
import com.phamthaihoang.bookservice.command.event.BookDeletedEvent;
import com.phamthaihoang.bookservice.command.event.BookUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;


@Aggregate
public class BookAggregate {
	
	@AggregateIdentifier
	private String bookId;
	private String name;
	private String author;
	private Boolean isReady;
	
	 public BookAggregate() {
		 
	    }
	 @CommandHandler
	    public BookAggregate(CreateBookCommand createBookCommand) {
		 System.out.println("2");
	        BookCreatedEvent bookCreatedEvent
	                = new BookCreatedEvent();
	        BeanUtils.copyProperties(createBookCommand,bookCreatedEvent);
	        AggregateLifecycle.apply(bookCreatedEvent);
	    }

	@EventSourcingHandler
	public void on(BookCreatedEvent event) {
		System.out.println("3");
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.isReady = event.getIsReady();
		this.name = event.getName();
	}
	 @CommandHandler
	    public void handle(UpdateBookCommand updateBookCommand) {
		 System.out.println("2U");
	        BookUpdatedEvent bookUpdatedEvent
	                = new BookUpdatedEvent();
	        BeanUtils.copyProperties(updateBookCommand,bookUpdatedEvent);
	        AggregateLifecycle.apply(bookUpdatedEvent);
	    }

	@EventSourcingHandler
	public void on(BookUpdatedEvent event) {
		System.out.println("3U");
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.isReady = event.getIsReady();
		this.name = event.getName();
	}
	 @CommandHandler
	    public void handle(DeleteBookCommand deleteBookCommand) {
	        
	        BookDeletedEvent deletedEvent
	                = new BookDeletedEvent();
	        BeanUtils.copyProperties(deleteBookCommand,deletedEvent);
	        AggregateLifecycle.apply(deletedEvent);
	    }

	@EventSourcingHandler
	public void on(BookDeletedEvent event) {
		this.bookId = event.getBookId();

	}

	//	 @CommandHandler
//	 public void handle(UpdateStatusBookCommand command) {
//		 BookUpdateStatusEvent event = new BookUpdateStatusEvent();
//		 BeanUtils.copyProperties(command, event);
//		 AggregateLifecycle.apply(event);
//	 }
//	 @EventSourcingHandler
//		public void on(BookUpdateStatusEvent event) {
//			this.bookId = event.getBookId();
//			this.isReady = event.getIsReady();
//		}



//	 @CommandHandler
//	 public void handle(RollBackStatusBookCommand command) {
//		 BookRollBackStatusEvent event = new BookRollBackStatusEvent();
//		 BeanUtils.copyProperties(command, event);
//		 AggregateLifecycle.apply(event);
//	 }
//	 @EventSourcingHandler
//		public void on(BookRollBackStatusEvent event) {
//			this.bookId = event.getBookId();
//			this.isReady = event.getIsReady();
//		}
}
