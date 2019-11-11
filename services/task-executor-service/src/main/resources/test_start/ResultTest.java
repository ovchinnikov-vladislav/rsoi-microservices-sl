package rsoi.lab2.teservice.model;

import java.io.Serializable;
import java.util.List;

public class ResultTest implements Serializable {

    private int countAllTests;
    private int countFailedTests;
    private int countSuccessfulTests;
    private int wasSuccessful;
    private List<String> fails;

    public int getCountAllTests() {
        return countAllTests;
    }

    public void setCountAllTests(int countAllTests) {
        this.countAllTests = countAllTests;
    }

    public int getCountFailedTests() {
        return countFailedTests;
    }

    public void setCountFailedTests(int countFailedTests) {
        this.countFailedTests = countFailedTests;
    }

    public int getCountSuccessfulTests() {
        return countSuccessfulTests;
    }

    public void setCountSuccessfulTests(int countSuccessfulTests) {
        this.countSuccessfulTests = countSuccessfulTests;
    }

    public int getWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(int wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    public List<String> getFails() {
        return fails;
    }

    public void setFails(List<String> fails) {
        this.fails = fails;
    }
}
