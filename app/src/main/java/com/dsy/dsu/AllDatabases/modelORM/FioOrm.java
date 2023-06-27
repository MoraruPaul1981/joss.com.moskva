package com.dsy.dsu.AllDatabases.modelORM;


import android.util.Log;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;




@DatabaseTable(tableName = "fio")
public class FioOrm implements Serializable {
    @DatabaseField(generatedId = true, dataType = DataType.INTEGER, columnName = "_id")
    private Integer _id;

    @DatabaseField(  dataType = DataType.STRING, columnName = "name")
    private String name;
    @DatabaseField(  dataType = DataType.STRING, columnName = "f")
    private String f;
    @DatabaseField(  dataType = DataType.STRING, columnName = "n")
    private String n;
    @DatabaseField(  dataType = DataType.STRING, columnName = "o")

    private String o;

    @DatabaseField(  dataType = DataType.DATE, columnName = "BirthDate")
    private Date BirthDate;


    @DatabaseField(  dataType = DataType.STRING, columnName = "snils")
    private String snils;

    @DatabaseField(  dataType = DataType.DATE, columnName = "date_update")
    private Date date_update;

    @DatabaseField(  dataType = DataType.INTEGER, columnName = "user_update")
    private int user_update;


    @DatabaseField(  dataType = DataType.BIG_DECIMAL, columnName = "uuid")
    private BigDecimal uuid;

    @DatabaseField(  dataType = DataType.INTEGER, columnName = "current_organization")
    private Integer current_organization;

    @DatabaseField(  dataType = DataType.BIG_DECIMAL, columnName = "current_table")
    private BigDecimal current_table;

    @DatabaseField(  dataType = DataType.INTEGER, columnName = "prof")
    private Integer prof;


    public FioOrm(int _id, Date birthDate, Integer current_organization,
               BigDecimal current_table, Date date_update, String f,
               String n, String name, String o, String snils, int user_update, BigDecimal uuid, Integer prof) {
        this._id = _id;
        BirthDate = birthDate;
        this.current_organization = current_organization;
        this.current_table = current_table;
        this.date_update = date_update;
        this.f = f;
        this.n = n;
        this.name = name;
        this.o = o;
        this.snils = snils;
        this.user_update = user_update;
        this.uuid = uuid;
        this.prof = prof;
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }
}