package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Example.findAll", query = "select e from Example as e")
})
@Table(name = "EXAMPLE")
@Getter @Setter
public class Example implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;

    @Size(max = 4096)
    @Column(name = "DESCRIPTION")
    private String description;

    public Example() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Example example = (Example) o;

        return Objects.equals(id, example.id) &&
                Objects.equals(title, example.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
