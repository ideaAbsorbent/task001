package com.idea.absorbent.task001.credits.persistence.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
