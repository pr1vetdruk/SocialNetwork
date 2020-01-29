package ru.privetdruk.socialnetwork.service.authentication;

public interface SecurityService {
    String findLoggedInLogin();
    void autoLogin(String login, String password);
}
