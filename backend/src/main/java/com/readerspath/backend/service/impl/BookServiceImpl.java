package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.BookAddFailedException;
import com.readerspath.backend.exception.BookNotFoundException;
import com.readerspath.backend.model.*;
import com.readerspath.backend.projection.BookView;
import com.readerspath.backend.repository.BookRepository;
import com.readerspath.backend.repository.LinksToBuyRepository;
import com.readerspath.backend.service.AppUserService;
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
    @Autowired
    private AppUserService appUserService;

    @Override
    public Book addBook(String email, Book book) throws BookAddFailedException {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        Author author = authorService.findAuthorByAppUser(appUser);
        if (author == null && appUser.getRole().equals("ROLE_USER")) {
            Author newAuthor = new Author(appUser.getName(), appUser);
            author = authorService.addAuthor(newAuthor);
        } else {
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
            linksToBuyRepository.saveAll(book.getLinks());
        }
        return newBook;
    }

    @Override
    public List<BookView> getAllBooks() {
        return Convertion.convertToViewList(bookRepository.findAll(), BookView.class);
    }

    @Override
    public Book findBookById(Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Override
    public Category addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    @Override
    public List<Book> findMyBooks(String email) throws BookNotFoundException {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        Author author = authorService.findAuthorByAppUser(appUser);
        if (author == null) {
            throw new BookNotFoundException("You don't have any book");
        }
        return bookRepository.findAllByAuthor(author);

    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void updateOverAllRating(Book book) {
        List<ReviewsAndRating> reviewsAndRatings = book.getReviewsAndRating();
        if (reviewsAndRatings != null && !reviewsAndRatings.isEmpty()) {
            Double avg = 0.0;
            for (ReviewsAndRating r : reviewsAndRatings) {
                avg += Double.parseDouble(r.getRating());
            }
            ;
            avg /= reviewsAndRatings.size();
            book.setOverAllRating(Double.toString(avg));
            bookRepository.save(book);
        }

    }


}
