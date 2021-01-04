package com.github.mpawelek;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Tx {
    private EntityManager em;

    public Tx(@Autowired EntityManager em) {
        this.em = em;
    }

    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = ErrorException.class
    )
    public void required(TxRunnable r) throws Exception {
        r.run(em);
    }

    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = ErrorException.class
    )
    public void required(Runnable r) {
        r.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, 
        rollbackFor = ErrorException.class
    )
    public void requiresNew(TxRunnable r) throws Exception {
        r.run(em);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
        rollbackFor = ErrorException.class
    )
    public void requiresNew(Runnable r) throws Exception {
        r.run();
    }
}
