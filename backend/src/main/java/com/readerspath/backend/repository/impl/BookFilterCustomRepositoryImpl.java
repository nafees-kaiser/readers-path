package com.readerspath.backend.repository.impl;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.BookFilterReq;
import com.readerspath.backend.repository.BookFilterCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookFilterCustomRepositoryImpl implements BookFilterCustomRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> filterBooks(BookFilterReq req) {
        StringBuilder query = new StringBuilder();
        String sql = "SELECT b FROM Book b INNER JOIN Category c ON c.id = b.category.id";

        boolean isCategoryNotNull = req.category() != null && !req.category().isEmpty();
        boolean isSearchNotNull = req.search() != null && !req.search().isEmpty();
        boolean isSortByNotNull = req.sortBy() != null && !req.sortBy().isEmpty();

        String category = " c.name = :category";
        String search = " (lower(b.title)) LIKE :search";

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

        if (isSortByNotNull && req.sortBy().equals("rating")) {
            query.append(" ORDER BY b.overAllRating DESC, b.title ASC");
        } else {
            query.append(" ORDER BY b.title ASC");
        }

        TypedQuery<Book> typedQuery = em.createQuery(sql + query, Book.class);
        if (isCategoryNotNull) {
            typedQuery.setParameter("category", req.category());
        }
        if (isSearchNotNull) {
            typedQuery.setParameter("search", "%" + req.search().trim().toLowerCase() + "%");
        }
        return typedQuery.getResultList();
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
