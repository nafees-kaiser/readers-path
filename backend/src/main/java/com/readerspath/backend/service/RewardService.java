package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Reward;

public interface RewardService {
    Reward completeBook(Book book);

    Reward findRewardByAppUser(AppUser appUser);

    Reward getReward();
}
