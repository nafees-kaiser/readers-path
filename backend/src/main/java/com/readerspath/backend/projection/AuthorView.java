package com.readerspath.backend.projection;

public interface AuthorView {
    Long getId();
    public String getName();
    public AppUserView getAppUser();
}
