package com.readerspath.backend.repository.impl;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.projection.BookView;
import com.readerspath.backend.repository.BookFilterCustomRepository;
import com.readerspath.backend.util.Convertion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BookFilterCustomRepositoryImpl implements BookFilterCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<BookView> filterBooks(BookFilterReq req) {
        Pageable pageable = PageRequest.of(req.pageNumber(), 10);
        StringBuilder query = new StringBuilder();
        String sql = "SELECT b FROM Book b INNER JOIN Category c ON c.id = b.category.id INNER JOIN Author a ON a.id = b.author.id";

        boolean isCategoryNotNull = req.category() != null && !req.category().isEmpty();
        boolean isSearchNotNull = req.search() != null && !req.search().isEmpty();
        boolean isSortByNotNull = req.sortBy() != null && !req.sortBy().isEmpty();

        String category = " c.name in (:category)";
        String search = " (lower(b.title)) LIKE :search OR (lower(c.name)) LIKE :search OR (lower(a.name)) LIKE :search";

        if (isCategoryNotNull) {
            query.append(" WHERE").append(category);
        }

        if (isSearchNotNull) {
            if (isCategoryNotNull) {
                query.append(" AND").append(search);
            } else {
                query.append(" WHERE").append(search);
            }
        }

        TypedQuery<Long> totalQuery = em.createQuery("SELECT COUNT(b) FROM Book b INNER JOIN Category c ON c.id = b.category.id  INNER JOIN Author a ON a.id = b.author.id" + query, Long.class);
        populateParams(totalQuery, req, isCategoryNotNull, isSearchNotNull);
        Long total = totalQuery.getSingleResult();

        if (isSortByNotNull && req.sortBy().equals("rating")) {
            query.append(" ORDER BY b.overAllRating DESC, b.title ASC");
        } else {
            query.append(" ORDER BY b.title ASC");
        }

        TypedQuery<Book> typedQuery = em.createQuery(sql + query, Book.class);
        populateParams(typedQuery, req, isCategoryNotNull, isSearchNotNull);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<Book> books = typedQuery.getResultList();
        List<BookView> bookViews = Convertion.convertToViewList(books, BookView.class);

        return new PageImpl<>(bookViews, pageable, total);
    }

    private void populateParams(TypedQuery<?> typedQuery, BookFilterReq req, Boolean isCategoryNotNull, Boolean isSearchNotNull) {

        if (isCategoryNotNull) {
            typedQuery.setParameter("category", req.category());
        }
        if (isSearchNotNull) {
            typedQuery.setParameter("search", "%" + req.search().trim().toLowerCase() + "%");
        }
    }

}
/*
category,
search
sortBy
 */

/*
select b from
book b inner join category c on c.id = b.category.id
where c.name = :category and
b.title like :search
order by b.overAllRating asc, b.title asc
[order by b.title asc]

SELECT b FROM
book b INNER JOIN category c ON c.id = b.category.id
WHERE c.name = :category
AND b.title LIKE :search
ORDER BY b.overAllRating ASC, b.title ASC
[ORDER BY b.title ASC]

 */
