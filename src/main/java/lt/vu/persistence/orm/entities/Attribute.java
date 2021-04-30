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
    @NamedQuery(name = "Attribute.findAllByIds", query = "select a from Attribute a where a.id in (:ids)"),
    @NamedQuery(name = "Attribute.findOneByType", query = "select a from Attribute a where a.type = :type"),
})
@Getter @Setter
public class Attribute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "TYPE", nullable = false, unique = true)
    private AttributeType type;

    @Column(name = "ADDITIONAL_PRICE", nullable = false)
    private int additionalPrice = 0;

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
