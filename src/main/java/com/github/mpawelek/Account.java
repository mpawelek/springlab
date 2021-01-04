package com.github.mpawelek;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity @Table(name="account")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private int amount;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override public String toString() {
        return String.valueOf("{ id: "+id+", amount: "+amount+" }");
    }
}

