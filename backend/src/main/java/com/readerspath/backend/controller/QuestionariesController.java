package com.readerspath.backend.controller;

import com.readerspath.backend.model.Questionaries;
import com.readerspath.backend.projection.QuestionariesView;
import com.readerspath.backend.service.QuestionariesService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class QuestionariesController {
    @Autowired
    private QuestionariesService questionariesService;

    @PostMapping("/add-ques")
    public ResponseEntity<?> addQuestionaries(@RequestBody Questionaries questionaries) {
        try {
            questionaries = questionariesService.addQuestionaries(questionaries);
            QuestionariesView questionariesView = Convertion.covertToView(questionaries, QuestionariesView.class);
            return new ResponseEntity<>(questionariesView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-ans")
    public ResponseEntity<?> addAnswers(@RequestBody Questionaries questionaries) {
        try {
            questionaries = questionariesService.addAnswers(questionaries);
            QuestionariesView questionariesView = Convertion.covertToView(questionaries, QuestionariesView.class);
            return new ResponseEntity<>(questionariesView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
