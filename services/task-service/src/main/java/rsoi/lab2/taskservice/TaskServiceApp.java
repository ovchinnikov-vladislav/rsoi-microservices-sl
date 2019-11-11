package rsoi.lab2.taskservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "rsoi.lab2.taskservice.*")
@PropertySource("classpath:application-h2.properties")
public class TaskServiceApp {
    public static void main(String... args) {
        SpringApplication.run(TaskServiceApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> System.out.println("TaskService");
    }
}