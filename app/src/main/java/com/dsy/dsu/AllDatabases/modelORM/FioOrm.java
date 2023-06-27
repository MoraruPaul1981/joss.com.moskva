package com.dsy.dsu.AllDatabases.modelORM;



import java.io.Serializable;


public class FioOrm implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer _id;

    public FioOrm(Integer _id) {
        this._id = _id;
    }
}

