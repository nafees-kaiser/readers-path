package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Reward;
import com.readerspath.backend.repository.RewardRepository;
import com.readerspath.backend.service.AppUserService;
import com.readerspath.backend.service.BookService;
import com.readerspath.backend.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AppUserService appUserService;

    @Override
    public Reward completeBook(Book book) {
        AppUser appUser = appUserService.getAppUserFromSession();
        Reward reward = this.findRewardByAppUser(appUser);
        if (reward == null) {
            reward = new Reward();
            reward.setAppUser(appUser);
        }
        book = bookService.findBookById(book.getId());
        reward.setCompletedBooks(book);
        reward.updateCoins();
        return rewardRepository.save(reward);
    }

    @Override
    public Reward findRewardByAppUser(AppUser appUser) {
        return rewardRepository.findByAppUser(appUser)
                .orElse(null);
    }

    @Override
    public Reward getReward() {
        AppUser appUser = appUserService.getAppUserFromSession();
        return this.findRewardByAppUser(appUser);
    }
}
