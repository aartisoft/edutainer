package com.edutainer.in.Model;

public class TransactionalModel
{
    private String txn_id;
    private String course_name;
    private String txn_date;
    private String price;
    private String txn_status;

    public TransactionalModel()
    {

    }

    public TransactionalModel(String txn_id, String course_name, String txn_date, String price, String txn_status) {
        this.txn_id = txn_id;
        this.course_name = course_name;
        this.txn_date = txn_date;
        this.price = price;
        this.txn_status = txn_status;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTxn_date() {
        return txn_date;
    }

    public void setTxn_date(String txn_date) {
        this.txn_date = txn_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTxn_status() {
        return txn_status;
    }

    public void setTxn_status(String txn_status) {
        this.txn_status = txn_status;
    }
}
