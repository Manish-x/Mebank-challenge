package com.mebank.txn.reader;
import com.mebank.txn.bean.Transaction;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public  CSVReader()
    {
        transactionMapper = new TransactionMapper();
    }
    private TransactionMapper transactionMapper;
    public static final String cvsSplitBy = ",";


    public List<Transaction> read ()  {
        String csvFile = "src/resource/sample-data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Transaction> txnList = new ArrayList<Transaction>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] dataElements = line.split(cvsSplitBy);
                //below if condition is to ignore header
                if(!dataElements[0].contains("transactionId")) {
                    txnList.add(transactionMapper.createTransaction(dataElements));
                }
            }

        }catch (FileNotFoundException e) {

            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return txnList;
    }



}
