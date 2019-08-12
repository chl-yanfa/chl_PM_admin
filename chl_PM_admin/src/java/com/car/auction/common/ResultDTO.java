package com.car.auction.common;

import java.util.ArrayList;
import java.util.List;


public class ResultDTO<T> {

    private String returnCode = RtnMsgConstants.RETURN_CODE_SUCCESS;

    private String returnMsg = RtnMsgConstants.RETURN_MSG_SUCCESS;

    private List<T> rows = new ArrayList<T>();

    private int total;

    private T entity;


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
