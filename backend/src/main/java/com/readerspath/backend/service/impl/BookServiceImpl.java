package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.BookAddFailedException;
import com.readerspath.backend.model.Author;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.LinksToBuy;
import com.readerspath.backend.projection.BookView;
import com.readerspath.backend.repository.BookRepository;
import com.readerspath.backend.repository.LinksToBuyRepository;
import com.readerspath.backend.service.AuthorService;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.service.CategoryService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LinksToBuyRepository linksToBuyRepository;

    @Override
    public Book addBook(Book book) throws BookAddFailedException {
        Author author = authorService.findAuthorByName(book.getAuthor().getName());
        if (author == null) {
            author = authorService.addAuthor(book.getAuthor());
        }
        Category category = categoryService.findCategoryByName(book.getCategory().getName());
        if (category == null) {
            throw new BookAddFailedException("Unable to add book");
        }
        book.setAuthor(author);
        book.setCategory(category);
        Book newBook = bookRepository.save(book);
        if (book.getLinks() != null && !book.getLinks().isEmpty()) {
            List<LinksToBuy> links = book.getLinks();
            links.forEach(link -> link.setBook(newBook));
            links = linksToBuyRepository.saveAll(book.getLinks());
        }
        return newBook;
    }

    @Override
    public List<BookView> getAllBooks() {
        return Convertion.convertToViewList(bookRepository.findAll(), BookView.class);
    }
}
