package com.phamthaihoang.bookservice.command.controller;

import com.phamthaihoang.bookservice.command.command.CreateBookCommand;
import com.phamthaihoang.bookservice.command.command.DeleteBookCommand;
import com.phamthaihoang.bookservice.command.command.UpdateBookCommand;
import com.phamthaihoang.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel model) {
        CreateBookCommand command =
                new CreateBookCommand(UUID.randomUUID().toString(),model.getName(), model.getAuthor(), true);
        commandGateway.sendAndWait(command);
        System.out.println("1");
        return "added Book";
    }
    @PutMapping
    public String updateBook(@RequestBody BookRequestModel model) {
        System.out.println("model update : "+ model.toString());
        UpdateBookCommand command =
                new UpdateBookCommand(model.getBookId(),model.getName(), model.getAuthor(), model.getIsReady());
        commandGateway.sendAndWait(command);
        System.out.println("1U");
        return "updated Book";
    }
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {

        DeleteBookCommand command =
                new DeleteBookCommand(bookId);
        commandGateway.sendAndWait(command);
        return "deleted Book";
    }
}
