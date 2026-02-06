package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

    @Autowired
    TransactionService transactionService;

    @Test
    void simpleTransation(){
        transactionService.execute();
    }

    @Test
    void transactionStateManaged(){
        transactionService.updateWithoutUpdating();
    }
}
