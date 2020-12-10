package org.example.aerien.domain.common.Entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @NotNull
    private Integer id = (int) (Math.random() * 100);

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
