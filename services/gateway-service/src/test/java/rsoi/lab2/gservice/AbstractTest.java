package rsoi.lab2.gservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rsoi.lab2.gservice.client.*;
import rsoi.lab2.gservice.entity.Status;
import rsoi.lab2.gservice.entity.completedtask.CompletedTask;
import rsoi.lab2.gservice.entity.result.Result;
import rsoi.lab2.gservice.entity.task.Task;
import rsoi.lab2.gservice.entity.test.Test;
import rsoi.lab2.gservice.entity.user.Role;
import rsoi.lab2.gservice.entity.user.User;
import rsoi.lab2.gservice.model.executetask.ExecuteTask;
import rsoi.lab2.gservice.model.executetask.ExecuteTaskRequest;
import rsoi.lab2.gservice.model.PageCustom;
import rsoi.lab2.gservice.model.result.ResultTest;

import java.util.*;

import java.io.IOException;

@WebAppConfiguration
public class AbstractTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SessionClient sessionClient;
    @MockBean
    private TaskClient taskClient;
    @MockBean
    private TestClient testClient;
    @MockBean
    private TaskExecutorClient taskExecutorClient;
    @MockBean
    private ResultClient resultClient;

    MockMvc mvc;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        /*createMockTasks();
        createMockCompletedTasks();
        createMockUsers();
        createMockResults();
        createMockExecuteTask();*/
    }

    MvcResult requestGet(String url, Object... param) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(url, param).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
    }

    MvcResult requestPost(String url, String json, Object... param) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(url, param).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json)).andReturn();
    }

    MvcResult requestPut(String url, String json, Object... param) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.put(url, param).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json)).andReturn();
    }

    MvcResult requestDelete(String url, Object... param) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.delete(url, param).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
    }

    String mapToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

    /*private void createMockUsers() {
        User[] users = new User[4];
        for (int i = 0; i < users.length; i++) {
            User user = new User();
            UUID idUser = UUID.randomUUID();
            user.setId(idUser);
            user.setFirstName("FirstName " + (i + 1));
            user.setLastName("LastName " + (i + 1));
            user.setPassword("Password " + (i + 1));
            user.setEmail("Email " + (i + 1));
            List<Role> roles = new ArrayList<>();
            roles.add(new Role() {{setName("ROLE_USER");}});
            user.setRoles(roles);
            user.setStatus(Status.ACTIVE);
            user.setUsername("UserName " + (i + 1));
            users[i] = user;
            Mockito.doReturn(Optional.of(user)).when(sessionClient).findUserById(idUser);
        }
        PageCustom<User> page = new PageCustom<>(List.of(users), PageRequest.of(0, 20), users.length);
        Mockito.doReturn(page).when(sessionClient).findUsersAll(0, 20, );
    }

    private void createMockTasks() {
        Task[] tasks = new Task[10];
        UUID idUser = UUID.randomUUID();
        for (int i = 0; i < tasks.length; i++) {
            Task task = new Task();
            UUID idTask = UUID.randomUUID();
            if (i == 0)
                idTask = UUID.fromString("7c9802de-bbb4-43f8-b0a2-d7d7cac47260");
            task.setNameTask("Testing task " + (i + 1));
            task.setDescription("Testing task " + (i + 1));
            task.setTextTask("Testing task " + (i + 1));
            task.setImage("");
            task.setIdUser(idUser);
            task.setIdTask(idTask);
            task.setTemplateCode("public class App { public static void main(String... args) {}}");
            task.setComplexity((byte) 1);
            tasks[i] = task;
            Test testByTask = new Test();
            UUID idTest = UUID.randomUUID();
            if (i == 0)
                idTest = UUID.fromString("0da1ec1b-7d70-458c-9063-99f9d392d24f");
            testByTask.setIdTest(idTest);
            testByTask.setIdTask(idTask);
            testByTask.setIdUser(idUser);
            testByTask.setSourceCode("import org.junit.Test; public class SourceTest { @Test public void test() {Assert.assertTrue(true);}}");
            testByTask.setDescription("");
            task.setTest(testByTask);
            Mockito.doReturn(Optional.of(task)).when(taskClient).findById(idTask);
            Mockito.doReturn(Optional.of(task)).when(taskClient).findByUserIdAndTaskId(idUser, idTask);
            Mockito.doReturn(Optional.of(testByTask)).when(testClient).findByTaskId(idTask);
            Mockito.doReturn(Optional.of(testByTask)).when(testClient).findByUserIdAndTaskId(idUser, idTask);
        }
        PageCustom<Task> page = new PageCustom<>(List.of(tasks), PageRequest.of(0, 20), tasks.length);
        Mockito.doReturn(page).when(taskClient).findAll(0, 20);
        Mockito.doReturn(page).when(taskClient).findByUserId(idUser, 0, 20);
    }

    private void createMockCompletedTasks() {
        CompletedTask[] completedTasks = new CompletedTask[10];
        UUID idUser = UUID.randomUUID();
        for (int i = 0; i < completedTasks.length; i++) {
            CompletedTask completedTask = new CompletedTask();
            UUID idTask = UUID.randomUUID();
            UUID idTest = UUID.randomUUID();
            UUID idCompletedTask = UUID.randomUUID();
            completedTask.setCountAllTests(1);
            completedTask.setCountFailedTests(0);
            completedTask.setCountSuccessfulTests(1);
            completedTask.setIdTask(idTask);
            completedTask.setIdTest(idTest);
            completedTask.setIdUser(idUser);
            completedTask.setIdCompletedTask(idCompletedTask);
            completedTask.setSourceCode("public class App { public static void main(String... args) {}}");
            completedTask.setWasSuccessful((byte) 1);
            completedTasks[i] = completedTask;
            Mockito.doReturn(Optional.of(completedTask)).when(taskExecutorClient).findById(idCompletedTask);
            Mockito.doReturn(Optional.of(completedTask)).when(taskExecutorClient).findByUserIdAndCompletedTaskId(idUser, idCompletedTask);
        }
        PageCustom<CompletedTask> page = new PageCustom<>(List.of(completedTasks), PageRequest.of(0, 20), completedTasks.length);
        Mockito.doReturn(page).when(taskExecutorClient).findAll(0, 20);
        Mockito.doReturn(page).when(taskExecutorClient).findByUserId(idUser, 0, 20);
    }

    private void createMockResults() {
        Result[] results = new Result[10];
        UUID idUser = UUID.fromString("6c2153f8-f2db-4e41-9379-9df2983c91f3");
        for (int i = 0; i < results.length; i++) {
            Result result = new Result();
            UUID idTask = UUID.randomUUID();
            result.setCountAttempt(1);
            result.setIdTask(idTask);
            result.setIdUser(idUser);
            result.setMark(100.);
            results[i] = result;
            Mockito.doReturn(Optional.of(result)).when(resultClient).findByUserIdAndTaskId(idUser, idTask);
            PageCustom<Result> pageResult = new PageCustom<>(List.of(result), PageRequest.of(0, 20), 1);
            Mockito.doReturn(pageResult).when(resultClient).findByTaskId(idTask, 0, 20);
        }
        PageCustom<Result> page = new PageCustom<>(List.of(results), PageRequest.of(0, 20), results.length);
        Mockito.doReturn(page).when(resultClient).findAll(0, 20);
        Mockito.doReturn(page).when(resultClient).findByUserId(idUser, 0, 20);
    }

    private void createMockExecuteTask() {
        ExecuteTaskRequest executeTaskRequest = new ExecuteTaskRequest();
        UUID idTask = UUID.fromString("7c9802de-bbb4-43f8-b0a2-d7d7cac47260");
        UUID idUser = UUID.fromString("6c2153f8-f2db-4e41-9379-9df2983c91f3");
        UUID idTest = UUID.fromString("0da1ec1b-7d70-458c-9063-99f9d392d24f");
        executeTaskRequest.setIdTask(idTask);
        executeTaskRequest.setIdUser(idUser);
        executeTaskRequest.setSourceTask("public class App { public static void main(String... args) {} }");

        ExecuteTask executeTask = new ExecuteTask();
        executeTask.setIdTask(executeTaskRequest.getIdTask());
        executeTask.setIdUser(executeTaskRequest.getIdUser());
        executeTask.setIdTest(idTest);
        executeTask.setSourceTask(executeTaskRequest.getSourceTask());
        executeTask.setSourceTest("import org.junit.Test; public class SourceTest { @Test public void test() {Assert.assertTrue(true);}}");

        ResultTest resultTest = new ResultTest();
        resultTest.setCountAllTests(1);
        resultTest.setCountFailedTests(0);
        resultTest.setCountSuccessfulTests(1);

        double mark = resultTest.getCountSuccessfulTests() * 100. / resultTest.getCountAllTests()
                - resultTest.getCountFailedTests() * 100. / resultTest.getCountAllTests();
        mark = (mark < 0) ? 0 : mark;
        Result result = new Result();
        result.setIdTask(executeTask.getIdTask());
        result.setIdUser(executeTask.getIdUser());
        result.setCountAttempt(0);
        result.setMark(mark);

        Mockito.doReturn(Optional.of(resultTest)).when(taskExecutorClient).execute(executeTask);
        Mockito.doReturn(Optional.of(result)).when(resultClient).create(result);
    }*/
}
