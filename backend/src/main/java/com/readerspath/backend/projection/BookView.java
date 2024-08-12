package com.readerspath.backend.projection;

import com.readerspath.backend.model.Category;

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

    List<LinksToBuyView> getLinks();

    List<ReviewsAndRatingView> getReviewsAndRating();

    List<QuestionariesView> getQuestionaries();

    String getOverAllRating();

    ImageView getCoverImage();
}
