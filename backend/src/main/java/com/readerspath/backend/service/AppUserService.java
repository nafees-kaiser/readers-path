package com.readerspath.backend.service;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Preference;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {

    AppUser addAppUser(AppUser appUser);

    Preference registrationService(Preference preference);

    AppUser loginService(String email, String password);

    AppUser getAppUserByEmail(String email);

    Preference editPreference(Preference preference, String email);

    Preference getPreference(String email);
}
