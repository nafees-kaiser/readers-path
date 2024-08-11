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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private List<LinksToBuy> addLinksToBuy(List<LinksToBuy> linksToBuyList) {
        return linksToBuyList.stream().map(link -> {
            LinksToBuy newLink = linksToBuyRepository.findByLink(link.getLink());
            if (newLink == null) {
                newLink = linksToBuyRepository.save(link);
            }
            return newLink;
        }).toList();
    }

    @Override
    public Book addBook(String email, Book book) throws BookAddFailedException {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        Author author = authorService.findAuthorByAppUser(appUser);
        if (author == null && appUser.getRole().equals("ROLE_USER")) {
            Author newAuthor = new Author(appUser.getName(), appUser);
            author = authorService.addAuthor(newAuthor);
        } else {
            author = authorService.findAuthorByName(book.getAuthor().getName());
            if (author == null) {
                author = authorService.addAuthor(book.getAuthor());
            }
        }

        Category category = categoryService.findCategoryByName(book.getCategory().getName());
        if (category == null) {
            throw new BookAddFailedException("Unable to add book");
        }
        book.setAuthor(author);
        book.setCategory(category);
        if (book.getLinks() != null && !book.getLinks().isEmpty()) {
            List<LinksToBuy> links = this.addLinksToBuy(book.getLinks());
            book.setLinks(links);
        }
        return bookRepository.save(book);
    }

    @Override
    public List<BookView> getAllBooks(BookFilterReq req) {
        List<Book> books = bookRepository.filterBooks(req);
        return Convertion.convertToViewList(books, BookView.class);
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

    @Override
    public Book editBook(Map<String, Object> updates) {
//        Book book = this.findBookById(id);
        Book book = this.findBookById(Long.valueOf((int) updates.get("id")));

        updates.forEach((key, value) -> {
            if (value != null) {
                switch (key) {
                    case "id":
                        break;
                    case "title":
                        book.setTitle((String) value);
                        break;
                    case "publisher":
                        book.setPublisher((String) value);
                        break;
                    case "pageCount":
                        book.setPageCount((String) value);
                        break;
                    case "isbn":
                        book.setIsbn((String) value);
                        break;
                    case "edition":
                        book.setEdition((String) value);
                        break;
                    case "author":
                        Map<String, Object> authorObj = (Map<String, Object>) value;
                        if (authorObj.get("name") != null) {
                            Author author = getAuthor(authorObj);
                            book.setAuthor(author);
                        }
                        break;
                    case "category":
                        Map<String, Object> categoryObj = (Map<String, Object>) value;
                        if (categoryObj.get("name") != null) {
                            Category category = categoryService.findCategoryByName((String) categoryObj.get("name"));
                            book.setCategory(category);
                        }
                        break;
                    case "links":
                        List<Map<String, Object>> links = (List<Map<String, Object>>) value;
                        if (!links.isEmpty()) {
                            List<LinksToBuy> linksToBuy = this.updateLinksToBuy(links);
                            book.setLinks(linksToBuy);
                        }
                        break;
                    default:
                        throw new BookAddFailedException("Unable to update book");

                }
            }
        });
        return bookRepository.save(book);
    }

    private Author getAuthor(Map<String, Object> authorObj) {
        Author author = authorService.findAuthorByName((String) authorObj.get("name"));
        if (author == null) {
            author = authorService.addAuthor(author);
        }
        return author;
    }

    private List<LinksToBuy> updateLinksToBuy(List<Map<String, Object>> links) {
        List<LinksToBuy> linksToBuy = new ArrayList<>();
        links.forEach(link -> {
            LinksToBuy newLink = linksToBuyRepository.findByLink((String) link.get("link"));
            if (newLink == null) {
                newLink = new LinksToBuy();
                newLink.setLink((String) link.get("link"));
                newLink = linksToBuyRepository.save(newLink);
            }
            linksToBuy.add(newLink);
        });
        return linksToBuy;
    }

}
