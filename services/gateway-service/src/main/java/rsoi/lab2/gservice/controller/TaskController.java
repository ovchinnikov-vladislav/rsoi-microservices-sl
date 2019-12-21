package rsoi.lab2.gservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rsoi.lab2.gservice.entity.Task;
import rsoi.lab2.gservice.entity.Test;
import rsoi.lab2.gservice.exception.HttpNotValueOfParameterException;
import rsoi.lab2.gservice.model.PageCustom;
import rsoi.lab2.gservice.service.TaskService;
import rsoi.lab2.gservice.service.TestService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/gate")
@Validated
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Task findById(@PathVariable UUID id, @RequestHeader HttpHeaders headers) {
        logger.info("GET http://{}/gate/tasks/{}: findById() method called.", headers.getHost(), id);
        return taskService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{idUser}/tasks/{idTask}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Task findByUserIdAndTaskId(@PathVariable UUID idUser, @PathVariable UUID idTask,
                                     @RequestHeader HttpHeaders headers) {
        logger.info("GET http://{}/gate/users/{}/tasks/{}: findByUserIdAndTaskId() method called.", headers.getHost(), idUser, idTask);
        return taskService.findByUserIdAndTaskId(idUser, idTask);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageCustom<Task> findAll(@NotNull @RequestParam(value = "page") Integer page, @NotNull @RequestParam(value = "size") Integer size,
                                    @RequestHeader HttpHeaders headers) {
        logger.info("GET http://{}/gate/tasks: findAll() method called.", headers.getHost());
        return taskService.findAll(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{id}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageCustom<Task> findByUserId(@PathVariable UUID id, @NotNull @RequestParam(value = "page") Integer page,
                               @NotNull @RequestParam(value = "size") Integer size, @RequestHeader HttpHeaders headers) {
        logger.info("GET http://{}/gate/users/{}/tasks: findByUserId() method called.", headers.getHost(), id);
        return taskService.findByUserId(id, page, size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users/{id}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Task create(@PathVariable UUID id, @Valid @RequestBody Task task, @RequestHeader HttpHeaders headers) throws IOException {
        logger.info("POST http://{}/gate/users/{}/tasks: create() method called.", headers.getHost(), id);
        return taskService.create(id, task);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{idUser}/tasks/{idTask}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void update(@PathVariable UUID idUser, @PathVariable UUID idTask,
                       @Valid @RequestBody Task task, @RequestHeader HttpHeaders headers) throws IOException {
        logger.info("PUT http://{}/gate/users/{}/tasks/{}: update() method called.", headers.getHost(), idUser, idTask);
        taskService.update(idUser, idTask, task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/users/{idUser}/tasks/{idTask}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable UUID idUser, @PathVariable UUID idTask, @RequestHeader HttpHeaders headers) throws IOException {
        logger.info("DELETE http://{}/gate/users/{}/tasks/{}: delete() method called.", headers.getHost(), idUser, idTask);
        taskService.delete(idTask);
    }
}
