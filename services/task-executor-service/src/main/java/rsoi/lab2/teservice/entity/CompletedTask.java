package rsoi.lab2.teservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "completed_task")
public class CompletedTask implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id_completed_task", updatable = false, nullable = false)
    private UUID idCompletedTask;

    @NotEmpty
    @Column(name = "source_code", nullable = false)
    @Size(max=10000)
    private String sourceCode;

    @NotNull
    @Column(name = "count_successful_tests", nullable = false)
    @Value("${some.key:0}")
    private Integer countSuccessfulTests;

    @NotNull
    @Column(name = "count_failed_tests", nullable = false)
    @Value("${some.key:0}")
    private Integer countFailedTests;

    @NotNull
    @Column(name = "count_all_tests", nullable = false)
    @Value("${some.key:0}")
    private Integer countAllTests;

    @NotNull
    @Column(name = "was_successful", nullable = false)
    private Byte wasSuccessful;

    @NotNull
    @Column(name = "id_task", nullable = false)
    private UUID idTask;

    @NotNull
    @Column(name = "id_test", nullable = false)
    private UUID idTest;

    @NotNull
    @Column(name = "id_user", nullable = false)
    private UUID idUser;

    public UUID getIdCompletedTask() {
        return idCompletedTask;
    }

    public void setIdCompletedTask(UUID idCompletedTask) {
        this.idCompletedTask = idCompletedTask;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Byte getWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(Byte wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    public Integer getCountSuccessfulTests() {
        return countSuccessfulTests;
    }

    public void setCountSuccessfulTests(Integer countSuccessfulTests) {
        this.countSuccessfulTests = countSuccessfulTests;
    }

    public Integer getCountFailedTests() {
        return countFailedTests;
    }

    public void setCountFailedTests(Integer countFailedTests) {
        this.countFailedTests = countFailedTests;
    }

    public Integer getCountAllTests() {
        return countAllTests;
    }

    public void setCountAllTests(Integer countAllTests) {
        this.countAllTests = countAllTests;
    }

    public UUID getIdTask() {
        return idTask;
    }

    public void setIdTask(UUID idTask) {
        this.idTask = idTask;
    }

    public UUID getIdTest() {
        return idTest;
    }

    public void setIdTest(UUID idTest) {
        this.idTest = idTest;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedTask that = (CompletedTask) o;
        return Objects.equals(idCompletedTask, that.idCompletedTask) &&
                Objects.equals(sourceCode, that.sourceCode) &&
                Objects.equals(wasSuccessful, that.wasSuccessful) &&
                Objects.equals(countSuccessfulTests, that.countSuccessfulTests) &&
                Objects.equals(countFailedTests, that.countFailedTests) &&
                Objects.equals(countAllTests, that.countAllTests) &&
                Objects.equals(idTask, that.idTask) &&
                Objects.equals(idTest, that.idTest) &&
                Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompletedTask, sourceCode, wasSuccessful, countSuccessfulTests, countFailedTests, countAllTests, idTask, idTest, idUser);
    }

    @Override
    public String toString() {
        return "CompletedTask{" +
                "idCompletedTask=" + idCompletedTask +
                ", sourceCode='" + sourceCode + '\'' +
                ", wasSuccessful='" + wasSuccessful + '\'' +
                ", countSuccessfulTests=" + countSuccessfulTests +
                ", countFailedTests=" + countFailedTests +
                ", countAllTests=" + countAllTests +
                ", idTask=" + idTask +
                ", idTest=" + idTest +
                ", idUser=" + idUser +
                '}';
    }
}
