package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "LOG_ENTRY")
@Getter @Setter
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "ENDPOINT")
    private String endpoint;

    @Column(name = "HTTP_METHOD")
    private String httpMethod;

    @Column(name = "CLASS_NAME", nullable = false)
    private String className;

    @Column(name = "METHOD_NAME", nullable = false)
    private String methodName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntry logEntry = (LogEntry) o;

        return logEntry.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
