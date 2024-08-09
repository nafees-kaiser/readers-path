package com.readerspath.backend.projection;

public interface ReviewsAndRatingView {
    Long getId();

    String getReview();

    String getRating();

    AppUserView getAppUser();
}
