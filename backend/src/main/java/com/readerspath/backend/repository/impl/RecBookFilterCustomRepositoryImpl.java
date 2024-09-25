package com.readerspath.backend.repository.impl;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.projection.BookView;
import com.readerspath.backend.repository.RecBookFilterCustomRepository;
import com.readerspath.backend.util.Convertion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RecBookFilterCustomRepositoryImpl implements RecBookFilterCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<BookView> filterBooks(BookFilterReq req, AppUser appUser) {
        Pageable pageable = PageRequest.of(req.pageNumber(), 10);
        StringBuilder query = new StringBuilder();
        String sql = "SELECT b FROM RecommendedBooks r " +
                "INNER JOIN r.books b " +
                "INNER JOIN AppUser a ON r.appUser.id = a.id " +
                "INNER JOIN Category c ON c.id = b.category.id " +
                "INNER JOIN Author au ON au.id = b.author.id " +
                "WHERE a.id = :appUser";

        boolean isCategoryNotNull = req.category() != null && !req.category().isEmpty();
        boolean isSearchNotNull = req.search() != null && !req.search().isEmpty();
        boolean isSortByNotNull = req.sortBy() != null && !req.sortBy().isEmpty();

        String category = " c.name in (:category)";
        String search = " (lower(b.title)) LIKE :search OR (lower(c.name)) LIKE :search OR (lower(a.name)) LIKE :search";

        if (isCategoryNotNull) {
            query.append(" AND").append(category);
        }

        if (isSearchNotNull) {
            query.append(" AND").append(search);

        }

        TypedQuery<Long> totalQuery = em.createQuery(
                "SELECT COUNT(b) FROM RecommendedBooks r INNER JOIN r.books b INNER JOIN AppUser a ON r.appUser.id = a.id INNER JOIN Category c ON c.id = b.category.id INNER JOIN Author au ON au.id = b.author.id WHERE a.id = :appUser" + query, Long.class);
        populateParams(totalQuery, req, isCategoryNotNull, isSearchNotNull, appUser);
        Long total = totalQuery.getSingleResult();

        if (isSortByNotNull && req.sortBy().equals("rating")) {
            query.append(" ORDER BY b.overAllRating DESC, b.title ASC");
        } else {
            query.append(" ORDER BY b.title ASC");
        }

        TypedQuery<Book> typedQuery = em.createQuery(sql + query, Book.class);
//        TypedQuery<Object> typedQuery = em.createQuery(sql + query, Object.class);
        populateParams(typedQuery, req, isCategoryNotNull, isSearchNotNull, appUser);

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
//        List<Object> results = typedQuery.getResultList();
//        List<Book> books = results.stream()
//                .filter(obj -> obj instanceof Book)
//                .map(obj -> (Book) obj)
//                .toList();
        List<Book> books = typedQuery.getResultList();
        List<BookView> bookViews = Convertion.convertToViewList(books, BookView.class);

        return new PageImpl<>(bookViews, pageable, total);
//        return null;
    }

    private void populateParams(TypedQuery<?> typedQuery,
                                BookFilterReq req,
                                Boolean isCategoryNotNull,
                                Boolean isSearchNotNull,
                                AppUser appUser) {
        typedQuery.setParameter("appUser", appUser.getId());

        if (isCategoryNotNull) {
            typedQuery.setParameter("category", req.category());
        }
        if (isSearchNotNull) {
            typedQuery.setParameter("search", "%" + req.search().trim().toLowerCase() + "%");
        }
    }
}
