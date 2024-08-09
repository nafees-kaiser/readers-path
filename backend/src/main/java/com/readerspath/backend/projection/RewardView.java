package com.readerspath.backend.projection;

public interface RewardView {
    Long getId();

    AppUserView getAppUser();

    Long getCompletedBooks();

    Long getCoins();
}
