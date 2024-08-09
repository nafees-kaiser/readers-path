package com.readerspath.backend.projection;

import com.readerspath.backend.model.Category;
import com.readerspath.backend.model.LinksToBuy;
import com.readerspath.backend.model.Questionaries;
import com.readerspath.backend.model.ReviewsAndRating;

import java.util.List;

public interface BookView {
    Long getId();
    String getTitle();
    AuthorView getAuthor();
    String getPublisher();
    String getIsbn();
    String getPageCount();
    String getEdition();
    Category getCategory();
    List<LinksToBuy> getLinks();
    List<ReviewsAndRatingView> getReviewsAndRating();
    List<QuestionariesView> getQuestionaries();
    String getOverAllRating();
}
