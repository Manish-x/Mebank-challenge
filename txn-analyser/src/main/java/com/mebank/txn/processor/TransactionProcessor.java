package com.mebank.txn.processor;
import com.mebank.txn.bean.Balance;
import com.mebank.txn.bean.Transaction;
import com.mebank.txn.reader.CSVReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionProcessor {

    private List<Transaction> txnList;
    private CSVReader csvReader;
    public static final String PAYMENT = "PAYMENT";
    public static final String REVERSAL = "REVERSAL";
    public TransactionProcessor()
    {
        csvReader = new CSVReader();
        txnList = csvReader.read();
    }

    /*
     getBalance function will take fromDate, toDate and accountNo as parameters
     it will calculate balance for the defined time period and return balance object which holds balance as well as number of Transactions
     */
    public Balance getBalance(LocalDateTime fromDate, LocalDateTime endDate, String accountNo)
    {
          Map<String, Double> reverseMap = getReverseMap();
            Balance balance = new Balance();

        /*
         Here if given account number is from account no then those transactions are considered as Debit transactions
         we will consider only those transactions which are of type PAYMENT.
         also if any payment transaction id found in reverse transaction map then that transaction would be ignored
         */
        List<Transaction> debitTransactions = txnList.stream()
                .filter(txn ->  (accountNo.equalsIgnoreCase(txn.getFromAccountId()) ))
                .filter(txn ->  ( endDate.isAfter(txn.getCreatedAt()) && fromDate.isBefore(txn.getCreatedAt()))  )
                .filter(txn -> (txn.getTransactionType().equalsIgnoreCase(PAYMENT)))
                .filter(txn -> !(reverseMap.containsKey(txn.getTransactionId())) ).collect(Collectors.toList());



          Double debitBalance = debitTransactions.stream()
                        .mapToDouble(Transaction::getAmount).sum();

         /*
          Like Debit transactions , below code prepares list of credit transactions
          */
        List<Transaction> creditTransactions = txnList.stream()
                .filter(txn ->  ( accountNo.equalsIgnoreCase(txn.getToAccountId())))
                .filter(txn ->  ( endDate.isAfter(txn.getCreatedAt()) && fromDate.isBefore(txn.getCreatedAt()))  )
                .filter(txn -> (txn.getTransactionType().equalsIgnoreCase(PAYMENT)))
                .filter(txn -> !(reverseMap.containsKey(txn.getTransactionId())) ).collect(Collectors.toList());

        Double creditBalance = creditTransactions.stream()
                .mapToDouble(Transaction::getAmount).sum();

        //total balance is credit balance - Debit Balance
        Double totalBalance = creditBalance + debitBalance*(-1);
        balance.setStartDate(fromDate);
        balance.setEndDate(endDate);
        balance.setAccountNo(accountNo);
        balance.setBalance(totalBalance);
        balance.setNumberOfTransactions(debitTransactions.size() + creditTransactions.size());

        return balance;
    }

    /*
      getReverseMap function will hold map of reverse transaction and its value.
      so in case if any transaction which is reversed , we can just check if it exist in this map

     */
    public Map<String, Double> getReverseMap()
    {
        Map<String, Double> reverseMap =
        txnList.stream()
                .filter(txn -> (txn.getTransactionType().equalsIgnoreCase(REVERSAL)))
                .collect(
                Collectors.toMap(Transaction::getRelatedTransaction, Transaction::getAmount));

        return reverseMap;


    }



}
