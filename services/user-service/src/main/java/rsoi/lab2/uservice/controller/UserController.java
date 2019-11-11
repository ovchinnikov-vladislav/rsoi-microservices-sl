package rsoi.lab2.uservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rsoi.lab2.uservice.entity.User;
import rsoi.lab2.uservice.model.CheckUserRequest;
import rsoi.lab2.uservice.model.SomeUsersModel;
import rsoi.lab2.uservice.service.UserService;

import javax.validation.Valid;
import java.net.InetSocketAddress;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<SomeUsersModel> getAll(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestHeader HttpHeaders headers) {
        logger.info("GET http://{}/users: getAll() method called.", headers.getHost());
        return (page != null && size != null) ? userService.getAll(PageRequest.of(page, size)) : userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getById(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        InetSocketAddress host = headers.getHost();
        logger.info("GET http://{}/users/{}: getById() method called.", headers.getHost(), id);
        return userService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/users/check", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getByRequest(@RequestBody CheckUserRequest request, @RequestHeader HttpHeaders headers) {
        logger.info("POST http://{}/users/check: getByRequest() method called: ", headers.getHost());
        logger.info("\trequest: " + request);
        if (request.getUserName() != null && request.getEmail() == null && request.getPassword() == null) {
            logger.info("\trequest.getUserName() != null && request.getEmail() == null && request.getPassword() == null");
            return userService.getByUserName(request.getUserName());
        }
        if (request.getEmail() != null && request.getUserName() == null && request.getPassword() == null) {
            logger.info("\trequest.getEmail() != null && request.getUserName() == null && request.getPassword() == null");
            return userService.getByEmail(request.getEmail());
        }
        if (request.getUserName() != null && request.getPassword() != null) {
            logger.info("\trequest.getUserName() != null && request.getPassword() != null");
            User userByLogin = userService.getByUserName(request.getUserName());
            if (userByLogin != null && userByLogin.getPassword().equals(request.getPassword())) {
                logger.info("\t\tuserByLogin != null && userByLogin.getPassword().equals(request.getPassword())");
                return userByLogin;
            }
        }
        if (request.getEmail() != null && request.getPassword() != null) {
            logger.info("\trequest.getEmail() != null && request.getPassword() != null");
            User userByEmail = userService.getByEmail(request.getEmail());
            if (userByEmail != null && userByEmail.getPassword().equals(request.getPassword())) {
                logger.info("\t\tuserByEmail != null && userByEmail.getPassword().equals(request.getPassword())");
                return userByEmail;
            }
        }
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User create(@Valid @RequestBody User user, @RequestHeader HttpHeaders headers) {
        logger.info("POST http://{}/users: create() method called.", headers.getHost());
        return userService.add(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User update(@PathVariable Long id, @Valid @RequestBody User user, @RequestHeader HttpHeaders headers) {
        logger.info("PUT http://{}/users/{}: update() method called.", headers.getHost(), id);
        return userService.update(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        logger.info("DELETE {}/users/{}: delete() method called.", headers.getHost(), id);
        userService.delete(id);
    }
}
