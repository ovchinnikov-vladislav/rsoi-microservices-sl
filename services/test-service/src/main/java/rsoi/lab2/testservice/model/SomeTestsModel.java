package rsoi.lab2.testservice.model;

import java.util.Date;
import java.util.UUID;

public interface SomeTestsModel {
    UUID getId();
    Date getCreatedAt();
    Date getUpdatedAt();
    UUID getIdTask();
    UUID getIdUser();
}
