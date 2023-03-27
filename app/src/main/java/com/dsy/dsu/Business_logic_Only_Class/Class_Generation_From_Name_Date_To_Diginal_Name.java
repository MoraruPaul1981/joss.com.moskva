package com.dsy.dsu.Business_logic_Only_Class;

import static java.util.Calendar.getInstance;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Class_Generation_From_Name_Date_To_Diginal_Name {

    Context contextДляКлассаПереводимИзНазваниеДатыВЦифруМесяцОтвдельноГодОтдельно;

    public Class_Generation_From_Name_Date_To_Diginal_Name(Context context) {

        contextДляКлассаПереводимИзНазваниеДатыВЦифруМесяцОтвдельноГодОтдельно=context;

    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ


    //TODO метод получени месяа для записи в одну колонку

    public int МетодПолучениниеМесяцПриСозданииНовогоСОтрудника(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        ///////
        System.out.println(" " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " + ДатаКоторуюНадоПеревестиИзТекставЦифру);
        //
        Integer ПолученныйМесяц = 0;
        //TODO
        String СтильПолученияМесяца="LLLL yyyy";
        ///
        try {

            SimpleDateFormat formatмесяц = new SimpleDateFormat(СтильПолученияМесяца,new Locale("ru")); //new Locale("ru")

            // formatмесяц.setTimeZone(TimeZone.getTimeZone("UTC-03:00"));
            formatмесяц.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

            Date date = formatмесяц.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);

            SimpleDateFormat simpleDateFormatДва = new SimpleDateFormat("MMMM", new Locale("ru"));

         String   СамаДАтаЗадачиТекущей = simpleDateFormatДва.format(date);

            Date dateФиналМесяц = simpleDateFormatДва.parse(СамаДАтаЗадачиТекущей);


            Calendar calendar1 = getInstance(new Locale("ru"));

            //TODO
            //TODO
           ПолученныйМесяц = dateФиналМесяц.getMonth();  ;
            // TODO: 29.04.2022
            System.out.println(" ПолученныйМесяц "  +ПолученныйМесяц);
            ПолученныйМесяц++;

            // TODO: 29.04.2022
            System.out.println(" month "  +ПолученныйМесяц);



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(contextДляКлассаПереводимИзНазваниеДатыВЦифруМесяцОтвдельноГодОтдельно).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        ////
        return ПолученныйМесяц;
    }

// TODO: 23.09.2021



    //TODO метод получени месяа для записи в одну колонку

    public int МетодПолучениниеГОдПриСозданииНовогоСОтрудника(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {

        System.out.println("ДатаКоторуюНадоПеревестиИзТекставЦифру " + ДатаКоторуюНадоПеревестиИзТекставЦифру);

        int ПолученныйГод = 0;

        try {

            SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy", new Locale("ru"));

            // formatгод.setTimeZone(TimeZone.getTimeZone("UTC-03:00"));
            formatгод.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

            Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);

            Calendar calendar1 = getInstance(new Locale("ru"));

            calendar1.setTime(date);

            //TODO
            GregorianCalendar calendar = (GregorianCalendar) calendar1;

            calendar.setTime(date);

            TimeZone tz = calendar.getInstance().getTimeZone();
            //TODO
            ПолученныйГод = calendar.get(Calendar.YEAR);
           //// ПолученныйГод++;
            System.out.println("ПолученныйГод " + ПолученныйГод);
            ////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(contextДляКлассаПереводимИзНазваниеДатыВЦифруМесяцОтвдельноГодОтдельно).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПолученныйГод;
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ






}
