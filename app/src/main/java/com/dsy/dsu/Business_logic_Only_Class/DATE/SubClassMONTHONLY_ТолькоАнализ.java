package com.dsy.dsu.Business_logic_Only_Class.DATE;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SubClassMONTHONLY_ТолькоАнализ extends Class_Generation_Data {
    public SubClassMONTHONLY_ТолькоАнализ(Context context) {
        super(context);
    }
    @Override
    public String ГлавнаяДатаИВремяОперацийСБазойДанных() {
        super.ГлавнаяДатаИВремяОперацийСБазойДанных();
       // new DateTime().minusMonths(1).toDate();
        Calendar cal = Calendar.getInstance();
        Date Дата = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM", new Locale("ru"));
        Log.d(this.getClass().getName(), "dateFormat "  +dateFormat);
        return  dateFormat.format(Дата);
    }

}
