package org.apache.coyote.http11.dispatcher;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.coyote.http11.controller.AbstractController;
import org.apache.coyote.http11.controller.Controller;
import org.apache.coyote.http11.controller.LoginController;
import org.apache.coyote.http11.controller.RegisterController;

import com.techcourse.service.UserService;

public class RequestMapping {
    private final ConcurrentHashMap<String, Controller> controllerMapper = new ConcurrentHashMap<>();

    private final UserService userService = new UserService();

    public RequestMapping() {
        controllerMapper.put("/login", new LoginController(userService));
        controllerMapper.put("/register", new RegisterController(userService));
    }

    public Controller getController(String requestUrl) {
        return Optional.ofNullable(controllerMapper.get(requestUrl))
                .orElse(new AbstractController());
    }
}
