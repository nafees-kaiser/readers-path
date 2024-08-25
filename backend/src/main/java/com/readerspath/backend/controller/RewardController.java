package com.readerspath.backend.controller;

import com.readerspath.backend.model.Book;
import com.readerspath.backend.model.Reward;
import com.readerspath.backend.projection.RewardView;
import com.readerspath.backend.service.RewardService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/reward")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @PostMapping("/complete-book")
    public ResponseEntity<?> completeBook(@RequestBody Book book) {
        try {
            Reward reward = rewardService.completeBook(book);
            RewardView rewardView = Convertion.covertToView(reward, RewardView.class);
            return new ResponseEntity<>(rewardView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getReward() {
        try {
            Reward reward = rewardService.getReward();
            RewardView rewardView = Convertion.covertToView(reward, RewardView.class);
            return new ResponseEntity<>(rewardView, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
