package com.readerspath.backend.projection;

public interface RewardView {
    Long getId();

    AppUserView getAppUser();

    String getCompletedBooksCount();

    String getCoins();
}
