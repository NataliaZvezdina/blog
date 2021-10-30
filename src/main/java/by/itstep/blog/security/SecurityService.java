package by.itstep.blog.security;


import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private boolean loggedIn;


    public void logIn() {
        loggedIn = true;
    }

    public void logOut() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
