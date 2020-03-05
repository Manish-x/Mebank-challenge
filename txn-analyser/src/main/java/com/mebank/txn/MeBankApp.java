package com.mebank.txn;

import com.mebank.txn.bean.Balance;
import com.mebank.txn.processor.TransactionProcessor;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeBankApp {

    public static void main(String args[])
    {
        TransactionProcessor tpr = new TransactionProcessor();
        String str = "20/10/2018 12:46:55";
        String st2 = "20/10/2018 20:00:55";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(str, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(st2, formatter);
        Balance balance = tpr.getBalance(startDateTime,endDateTime,"ACC334455");
        System.out.println(balance);
    }



}
