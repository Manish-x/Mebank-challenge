package com.mebank.txn.reader;

import com.mebank.txn.bean.Transaction;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionMapper {

    //Below function will convert each csv file row into Transaction Class

    public Transaction createTransaction(String[] dataElements)
    {
        Transaction txn = new Transaction();
        txn.setTransactionId(getString(dataElements[0]));
        txn.setFromAccountId(getString(dataElements[1]));
        txn.setToAccountId(getString(dataElements[2]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(getString(dataElements[3]), formatter);
        txn.setCreatedAt(dateTime);
        txn.setAmount(Double.valueOf(getString(dataElements[4])));
        txn.setTransactionType(getString(dataElements[5]));
        try {
            txn.setRelatedTransaction(getString(dataElements[6]));
        }catch(ArrayIndexOutOfBoundsException e)
        {
            //we could get array index out of bound for which reverse transaction is not present which is a valid scenario
        }

        return txn;
    }

    public String getString(String str)
    {
       return str.trim();
    }
}
