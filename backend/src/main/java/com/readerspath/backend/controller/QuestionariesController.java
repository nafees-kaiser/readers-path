package com.readerspath.backend.controller;

import com.readerspath.backend.model.Questionaries;
import com.readerspath.backend.projection.QuestionariesView;
import com.readerspath.backend.service.QuestionariesService;
import com.readerspath.backend.util.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class QuestionariesController {
    @Autowired
    private QuestionariesService questionariesService;

    @PostMapping("/add-ques")
    public ResponseEntity<?> addQuestionaries(@RequestBody Questionaries questionaries, @RequestHeader("email") String email) {
        try {
            questionaries = questionariesService.addQuestionaries(questionaries, email);
            QuestionariesView questionariesView = Convertion.covertToView(questionaries, QuestionariesView.class);
            return new ResponseEntity<>(questionariesView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-ans")
    public ResponseEntity<?> addAnswers(@RequestBody Questionaries questionaries, @RequestHeader("email") String email) {
        try {
            questionaries = questionariesService.addAnswers(questionaries, email);
            QuestionariesView questionariesView = Convertion.covertToView(questionaries, QuestionariesView.class);
            return new ResponseEntity<>(questionariesView, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
