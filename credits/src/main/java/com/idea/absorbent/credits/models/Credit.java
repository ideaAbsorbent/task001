package com.idea.absorbent.credits.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Credits")
@Getter
@Setter
@NoArgsConstructor
public class Credit {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CreditName")
    private String name;

    public Credit(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return name.equals(credit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
