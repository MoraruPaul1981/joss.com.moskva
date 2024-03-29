package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;


public class Class_Generations_PUBLIC_CURRENT_ID {
    Context context;
    private SQLiteDatabase sqLiteDatabase ;

    public Class_Generations_PUBLIC_CURRENT_ID( ) {
        sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
    }

    //todo функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Integer ПолучениеПубличногоТекущегоПользователяID(Context context) {
        ///TODO --первая вставка
        this.context =context;
        Integer ПубличныйIDДляФрагмента = 0;
        try{
            if (this.context !=null) {
                SQLiteCursor CurcorGetPublicId = null;
                Class_GRUD_SQL_Operations class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть = new Class_GRUD_SQL_Operations(this.context);
                class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть.concurrentHashMapНабор.put("СамFreeSQLКОд",
                        " SELECT id FROM SuccessLogin ORDER BY date_update DESC LIMIT 1 ");
                CurcorGetPublicId = (SQLiteCursor) class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть.
                        new GetаFreeData(this.context).getfreedata(class_grud_sql_operationsРабоатемВФрагментечитатьПисатьШестаяЧасть.
                                concurrentHashMapНабор,
                        new PUBLIC_CONTENT(this.context).МенеджерПотоков
                        , sqLiteDatabase);
                Log.d(this.getClass().getName(), "GetData " + CurcorGetPublicId);
                // TODO: 09.09.2021 resultat
                if (CurcorGetPublicId.getCount() > 0) {
                    CurcorGetPublicId.moveToFirst();
                    ПубличныйIDДляФрагмента = CurcorGetPublicId.getInt(0);
                    Log.d(this.getClass().getName(), "  ФИНАЛ ФОНОВАЯ  СИНХРОНИЗАЦИИ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ полученный публичный id ПЕРЕД ВСТАВКОЙ"
                            + " ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ПубличныйIDДляФрагмента;
    }
}