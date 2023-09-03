package com.phamthaihoang.bookservice.query.projection;


import com.phamthaihoang.bookservice.command.data.Book;
import com.phamthaihoang.bookservice.command.data.BookRepository;
import com.phamthaihoang.bookservice.query.model.BookResponseModel;
import com.phamthaihoang.bookservice.query.queries.GetAllBooksQuery;
import com.phamthaihoang.bookservice.query.queries.GetBookQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {
	@Autowired
	private BookRepository bookRepository;

	//cơ chế xác định và định tuyến truy vấn trong Axon Framework dựa trên annotation
	// và kiểu dữ liệu truy vấn để tìm và gọi phương thức xử lý truy vấn tương ứng.
	// Điều này giúp bạn tổ chức và xử lý các truy vấn một cách dễ dàng trong kiến trúc CQRS của bạn.


	//Xác định phương thức xử lý truy vấn (Query Handler):
	// ban đã đánh dấucác lớp xử lý truy vấn tương ứng bằng annotation @QueryHandler.
	// Annotation này thông báo cho Axon Framework
	// rằng phương thức đó sẽ được gọi khi một truy vấn kiểu cụ thể
	// (trong trường hợp này là GetBookQuery) được gửi đến hệ thống.
	 @QueryHandler
	    public BookResponseModel handle(GetBookQuery getBooksQuery) {
		 BookResponseModel model = new BookResponseModel();
		 Book book = bookRepository.getById(getBooksQuery.getBookId());
	      BeanUtils.copyProperties(book, model);

	        return model;
	    }
	 @QueryHandler List<BookResponseModel> handle(GetAllBooksQuery getAllBooksQuery){
		 List<Book> listEntity = bookRepository.findAll();
		 List<BookResponseModel> listbook = new ArrayList<>();
		 listEntity.forEach(s -> {
			 BookResponseModel model = new BookResponseModel();
			 BeanUtils.copyProperties(s, model);
			 listbook.add(model);
		 });
		 return listbook;
	 }
//	 @QueryHandler
//	    public BookResponseCommonModel handle(GetDetailsBookQuery getDetailsBookQuery) {
//		 BookResponseCommonModel model = new BookResponseCommonModel();
//		 Book book = bookRepository.getById(getDetailsBookQuery.getBookId());
//	      BeanUtils.copyProperties(book, model);
//
//	        return model;
//	    }
}
