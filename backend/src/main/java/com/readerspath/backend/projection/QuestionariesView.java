package com.readerspath.backend.projection;

public interface QuestionariesView {
    Long getId();
    String getQuestion();
    String getAnswer();
    AppUserView getAskedBy();
}
