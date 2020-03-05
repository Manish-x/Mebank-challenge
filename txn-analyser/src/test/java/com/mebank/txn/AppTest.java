package com.mebank.txn;

import com.mebank.txn.processor.TransactionProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppTest {

    private TransactionProcessor transactionProcessor;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    String endDate;
    String fromDate;
    String accountId;

    @Before
    public void setup()
    {
        fromDate = "20/10/2018 12:46:55";
        endDate = "20/10/2018 20:00:55";
        accountId = "ACC334455";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        startDateTime = LocalDateTime.parse(fromDate, formatter);
        endDateTime = LocalDateTime.parse(endDate, formatter);
        transactionProcessor = new TransactionProcessor();
        transactionProcessor.getBalance(startDateTime,endDateTime,accountId);
    }

    @Test
    public void testBalanceWithReverse() {
        Assert.assertEquals(Double.valueOf("-25"), transactionProcessor.getBalance(startDateTime,endDateTime,accountId).getBalance());
    }

    @Test
    public void testBalanceWithoutReverse() {
        Assert.assertEquals(Double.valueOf("30"), transactionProcessor.getBalance(startDateTime,endDateTime,"ACC778899").getBalance());
    }


    @Test
    public void testTransactionCount() {
        Assert.assertEquals(1, transactionProcessor.getBalance(startDateTime,endDateTime,accountId).getNumberOfTransactions());
    }



}