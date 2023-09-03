package com.phamthaihoang.bookservice.query.controller;


import com.phamthaihoang.bookservice.BookserviceApplication;
import com.phamthaihoang.bookservice.query.model.BookResponseModel;
import com.phamthaihoang.bookservice.query.queries.GetAllBooksQuery;
import com.phamthaihoang.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {

	@Autowired 
	private  QueryGateway queryGateway;

	private Logger logger =org.slf4j.LoggerFactory.getLogger(BookserviceApplication.class);

	@GetMapping("/{bookId}")
    public BookResponseModel getBookDetail(@PathVariable String bookId) {
        GetBookQuery getBooksQuery = new GetBookQuery();
        getBooksQuery.setBookId(bookId);


		// queryGateway.query(getBooksQuery, ResponseTypes.instanceOf(BookResponseModel.class)):
		// Đây là việc gửi một truy vấn đến hệ thống Axon Framework.
		//Xác định kiểu truy vấn (Query Type): Đầu tiên, bạn cần định nghĩa một lớp truy vấn (query) cùng với các thuộc tính cần thiết để xác định truy vấn bạn muốn thực hiện.
		// Trong trường hợp này, bạn đã định nghĩa GetBookQuery với một thuộc tính bookId.
        BookResponseModel bookResponseModel =
        queryGateway.query(getBooksQuery,
                ResponseTypes.instanceOf(BookResponseModel.class))
                .join();

        return bookResponseModel;
    }
	@GetMapping
	public List<BookResponseModel> getAllBooks(){
		GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();
		List<BookResponseModel> list = queryGateway.query(getAllBooksQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class))
				.join();
		logger.info("Danh sach Book "+list.toString());
		return list;
	}
}
