package com.github.mpawelek;

import javax.persistence.EntityManager;

public interface TxRunnable {
    void run(EntityManager em) throws Exception;
}
