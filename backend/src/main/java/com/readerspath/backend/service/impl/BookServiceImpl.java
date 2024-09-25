package com.readerspath.backend.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readerspath.backend.exception.BookAddFailedException;
import com.readerspath.backend.exception.BookNotFoundException;
import com.readerspath.backend.model.*;
import com.readerspath.backend.projection.BookView;
import com.readerspath.backend.repository.BookRepository;
import com.readerspath.backend.repository.LinksToBuyRepository;
import com.readerspath.backend.repository.RecommendedBooksRepository;
import com.readerspath.backend.service.*;
import com.readerspath.backend.util.Convertion;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final WebClient mLWebClient;
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

    @Autowired
    private ImageService imageService;

    @Autowired
    private RecommendedBooksRepository recommendedBooksRepository;

    public BookServiceImpl(WebClient mLWebClient) {
        this.mLWebClient = mLWebClient;
    }

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
    public Book addBook(Book book, MultipartFile file) throws BookAddFailedException, IOException {
        AppUser appUser = appUserService.getAppUserFromSession();
        Author author;
        if (appUser.getRole().equals("ROLE_USER")) {
            author = authorService.findAuthorByAppUser(appUser);
            if (author == null) {
                Author newAuthor = new Author(appUser.getName(), appUser);
                author = authorService.addAuthor(newAuthor);
            }
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
        book.setOverAllRating("0");
        if (file != null) {
            Image image = imageService.saveImage(file);
            book.setCoverImage(image);
        }
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Page<BookView> getAllBooks(BookFilterReq req) {
        return bookRepository.filterBooks(req);

//        return Convertion.convertToViewList(books, BookView.class);
    }

    @Transactional
    @Override
    public Book findBookById(Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Override
    public Category addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    @Transactional
    @Override
    public List<Book> findMyBooks() throws BookNotFoundException {
        AppUser appUser = appUserService.getAppUserFromSession();
        Author author = authorService.findAuthorByAppUser(appUser);
        if (author == null) {
            throw new BookNotFoundException("You don't have any book");
        }
        return bookRepository.findAllByAuthor(author);

    }

    @Override
    public void deleteBookById(Long bookId) {
        Book book = findBookById(bookId);
        book.setLinks(null);
        Image image = book.getCoverImage();
        book.setCoverImage(null);
        imageService.deleteImage(image);
        bookRepository.save(book);
        bookRepository.delete(book);
    }

    @Override
    public void updateOverAllRating(Book book) {
        List<ReviewsAndRating> reviewsAndRatings = book.getReviewsAndRating();
        if (reviewsAndRatings != null && !reviewsAndRatings.isEmpty()) {
            Double avg = 0.0;
            for (ReviewsAndRating r : reviewsAndRatings) {
                avg += Double.parseDouble(r.getRating());
            }
            avg /= reviewsAndRatings.size();
            book.setOverAllRating(Double.toString(avg));
            bookRepository.save(book);
        }

    }

    @Override
    public Book editBook(Map<String, Object> updates, MultipartFile file) throws IOException {
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
        if (file != null) imageService.editImage(book.getCoverImage(), file);
        return bookRepository.save(book);
    }

    @Override
    public Boolean isMyBook(Long id) {
        Book book = this.findBookById(id);
        AppUser appUser = appUserService.getAppUserFromSession();

        return book.getAuthor().getAppUser() != null && book.getAuthor().getAppUser().getEmail().equals(appUser.getEmail());
    }

    private Author getAuthor(Map<String, Object> authorObj) {
        Author author = authorService.findAuthorByName((String) authorObj.get("name"));
        if (author == null) {
            Author author1 = new Author();
            author1.setName((String) authorObj.get("name"));
            author = authorService.addAuthor(author1);
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

    @Override
    public void addRecommendation(String title, int numOfBooks) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("num_of_books", numOfBooks);

        String responseString = mLWebClient
                .post()
                .uri("/ml/get-recommendations")
                .bodyValue(data)
                .exchangeToMono(r -> {
                    if (r.statusCode().equals(HttpStatus.OK)) {
                        return r.bodyToMono(String.class);
                    } else if (r.statusCode().is4xxClientError()) {
                        return Mono.just("Error response");
                    } else {
                        return r.createException()
                                .flatMap(Mono::error);
                    }
                })
                .block();

        List<Long> bookIds = new ArrayList<>();
        try {
            bookIds = mapper.readValue(responseString, new TypeReference<List<Long>>() {
            });
            this.addToRecommendations(bookIds);
//            System.out.println(bookIds);
            // Now bookIds contains the list of Longs
        } catch (JsonProcessingException e) {
            throw new BookNotFoundException("Recommended books not found");
        }

    }

    @Override
    public Page<BookView> getRecBooks(BookFilterReq req) {
        AppUser appUser = appUserService.getAppUserFromSession();

        Page<BookView> books = recommendedBooksRepository.filterBooks(req, appUser);
        if (books.getTotalElements() == 0) {
            books = this.getAllBooks(req);
        }
        return books;
    }

    @Override
    public List<BookView> getAdminBooks() {
        List<Book> books = bookRepository.findAll();
        List<Book> newBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getAppUser() == null) {
                newBooks.add(book);
            }
        }
        return Convertion.convertToViewList(newBooks, BookView.class);
    }

    @Transactional
    @Override
    public List<BookView> getPopularBooks() {
        List<Book> books = bookRepository.findAllByOrderByOverAllRatingAsc();
        if (books.size() > 4) {
            books = books.subList(0, 4);
        }
        return Convertion.convertToViewList(books, BookView.class);
    }

    private void addToRecommendations(List<Long> bookIds) {
        if (bookIds != null && !bookIds.isEmpty()) {
            AppUser appUser = appUserService.getAppUserFromSession();
            RecommendedBooks rec = recommendedBooksRepository.findByAppUser(appUser);
            if (rec == null) {
                rec = new RecommendedBooks();
                rec.setAppUser(appUser);
            }
            List<Book> recBooks = rec.getBooks();
            if (recBooks == null) {
                recBooks = new ArrayList<>();
            }
            for (Long bookId : bookIds) {
                Book book = this.findBookById(bookId);
                if (!recBooks.contains(book)) {
                    recBooks.add(book);
                }

            }
            rec.setBooks(recBooks);
            recommendedBooksRepository.save(rec);

        }
    }

}
