package com.readerspath.backend.service.impl;

import com.readerspath.backend.exception.BookNotFoundException;
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
    public Reward completeBook(Book book, String email) {
        AppUser appUser = appUserService.getAppUserByEmail(email);
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
                .orElseThrow(() -> new BookNotFoundException("Book is not completed"));
    }

    @Override
    public Reward getReward(String email) {
        AppUser appUser = appUserService.getAppUserByEmail(email);
        return this.findRewardByAppUser(appUser);
    }
}
