package com.github.mpawelek;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
public class HttpAccess {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private Tx tx;

    public HttpAccess(@Autowired Tx tx) {
        this.tx = tx;
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Account test() throws ErrorException {
        Account a=new Account();
        a.setAmount(100);
        em.persist(a);
        return a;
    }
}
