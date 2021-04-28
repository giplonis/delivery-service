package lt.vu.persistence.orm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ATTRIBUTE")
@NamedQueries({
    @NamedQuery(name = "Attribute.findAll", query = "select a from Attribute a"),
})
@Getter @Setter
public class Attribute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.label);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Attribute attribute = (Attribute) obj;

        return attribute.id == this.id
            && attribute.label.equalsIgnoreCase(this.label);
    }
}
