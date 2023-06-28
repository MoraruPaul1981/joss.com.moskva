package com.dsy.dsu.AllDatabases.modelORM;


import com.orm.SugarRecord;

public class Person extends SugarRecord {
    Long id;
    String string;



    public Person(Long id, String string) {
        this.id = id;
        this.string = string;
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}

