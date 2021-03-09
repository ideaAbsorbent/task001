package com.idea.absorbent.task001.customers.persistence.models;

import com.idea.absorbent.task001.customers.web.dto.CreateCustomerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Table(name = "Customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    //TODO ADD CONSTRAIN UNIQUE CONSTRAIN TO CreditID + Pesel
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Customers_ID_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CreditID")
    private Long creditId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Pesel")
    private String pesel;

    public Customer(CreateCustomerDto dto) {
        this.setCreditId(dto.getCreditId());
        this.setFirstName(dto.getFirstName());
        this.setSurname(dto.getSurname());
        this.setPesel(dto.getPesel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (!creditId.equals(customer.creditId)) return false;
        return pesel.equals(customer.pesel);
    }

    @Override
    public int hashCode() {
        int result = creditId.hashCode();
        result = 31 * result + pesel.hashCode();
        return result;
    }
}
