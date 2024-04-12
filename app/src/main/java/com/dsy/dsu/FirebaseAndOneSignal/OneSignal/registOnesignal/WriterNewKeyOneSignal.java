package com.dsy.dsu.FirebaseAndOneSignal.OneSignal.registOnesignal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Settings.Model.BL.GetSettingTableSaves;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import javax.crypto.NoSuchPaddingException;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

class WriterNewKeyOneSignal {
    private SQLiteDatabase sqLiteDatabase;

    private  Context context;
    private  String НовыйIdОТСервтераOneSignal;


    public WriterNewKeyOneSignal(SQLiteDatabase sqLiteDatabase,
                                 Context context,
                                 String новыйIdОТСервтераOneSignal) {
        // TODO: 12.04.2024
        this.sqLiteDatabase = sqLiteDatabase;
        this.context = context;
        this.НовыйIdОТСервтераOneSignal = новыйIdОТСервтераOneSignal;

    }

    void writeingNewOneSingle(){

        try {
            // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
            // TODO: 30.09.2021 МЕТОД ЗАПУСКА СИНХРОНИЗАЦИИ ЧАТА ПО РАСПИСАНИЮ , НЕ ВЗАВИСИМОСТИ ОТ СОЗДАВАЛ ЛИ СООБЩЕНИЕ ИЛИ НЕТ
            Integer PublicId =
                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента  ИЗ ВСЕХ ТАБЕЛЕЙ PublicId "
                    + PublicId);

            /// TODO ########################################################втоаря часть  settings_tabels    ПЕРВАЯ ОБРАБОТКА ТАБЛИЦА  settings_tabels
            Observable observableСменыКлючаИЗАписьНовогоOneSinglal = Observable.fromArray("settings_tabels", "view_onesignal")
                    .doOnNext(new Consumer<String>() {
                        @Override
                        public void accept(String ТаблицаДляПолучениеКлючаONESIGNAL) throws Throwable {
                            // TODO: 13.01.2022 САМА ВСТАВКА НОВОГО КЛЮЧА В ТАБЛИЦУ НАСТРОЙКИ СИСТЕМЫ
                            Long writerNewKeyOneSignals
                                    =new GetSettingTableSaves().getWritingOneSingalSetingTable(context,НовыйIdОТСервтераOneSignal,PublicId);

                            Log.i(this.getClass().getName(), "  writerNewKeyOneSignals   "
                                    + writerNewKeyOneSignals);
                            ////TODO УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ  В ТАБЛИЦЕ MODIFICATION CLIENT
                            /// TODO ########################################################ЧАСТЬ ТРЕТЬЯ УДАЛЕНИЯ ДАННЫХ В ТАБЛИЦАХ настройки системы
                            if (writerNewKeyOneSignals > 0) {
                                // TODO: 29.12.2021 ЧАСТЬ ТЕРТЬЯ УДАЛАЕНИЯ ЛИШНЕХ КЛЮЧЕЙ СТАРЫХ ИЗ ДВУХ ТАБЛЦ ПО ТЕКУЩЕМУ ПОЛЬЗОВАТЕЛ
                                Integer РезультатПосикаИУдалениявТаблицах_settings_tabels =
                                        МетодПослеУспешнойОбновленияКлючаОтOneSignalИщемУдаляемДубли(context,
                                        ТаблицаДляПолучениеКлючаONESIGNAL,
                                        PublicId,
                                        НовыйIdОТСервтераOneSignal);
                                // TODO: 29.12.2021 ПОСЛЕ УСПЕШНОГО ЗАПИСАВАНИЕ НВОГО КЛЮЧА УДАЛЯЕМ ДУБЛИЗЗНАПЧЕНИЙ ЕСЛИ  ОНОИ ИИСТЬ
                                Log.d(this.getClass().getName(), "РезультатПосикаИУдалениявТаблицах_settings_tabels "
                                        + РезультатПосикаИУдалениявТаблицах_settings_tabels + "  ПубличныйIDДляФрагмента " + PublicId +
                                        " НовыйIdОТСервтераOneSignal " + НовыйIdОТСервтераOneSignal +
                                        " ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL_ДЛЯ_ТАБЛИЦЫ_settings_tabels "
                                        + ТаблицаДляПолучениеКлючаONESIGNAL);
                                // TODO: 24.02.2022 увеличение данных после смены ключа
                                if (ТаблицаДляПолучениеКлючаONESIGNAL.equalsIgnoreCase("settings_tabels")) {
                                    // TODO: 24.02.2022
                                    МетодУвеличениеВерсииДанныхПриСменеКлючаOneSingnal(context,
                                            ТаблицаДляПолучениеКлючаONESIGNAL,
                                            writerNewKeyOneSignals.intValue());
                                    // TODO: 29.12.2021 ПОСЛЕ УСПЕШНОГО ЗАПИСАВАНИЕ НВОГО КЛЮЧА УДАЛЯЕМ ДУБЛИЗЗНАПЧЕНИЙ ЕСЛИ  ОНОИ ИИСТЬ
                                    Log.d(this.getClass().getName(), "РезультатПосикаИУдалениявТаблицах_settings_tabels "
                                            + РезультатПосикаИУдалениявТаблицах_settings_tabels + "  ПубличныйIDДляФрагмента " + PublicId +
                                            " НовыйIdОТСервтераOneSignal " + НовыйIdОТСервтераOneSignal +
                                            " ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL_ДЛЯ_ТАБЛИЦЫ_settings_tabels "
                                            + ТаблицаДляПолучениеКлючаONESIGNAL);
                                }
                            }
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :"
                                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            Log.w(this.getClass().getName(), " doOnComplete РезультатПосикаИУдалениявТаблицах_settings_tabels  throwable ");
                        }
                    });
            observableСменыКлючаИЗАписьНовогоOneSinglal.blockingSubscribe(System.out::println);
            Object ФиналРЕзультатКЛЮЧНОВЫЙ = observableСменыКлючаИЗАписьНовогоOneSinglal.blockingStream().findAny().get();


            Log.d(this.getClass().getName(), " ONESIGNAL  КЛЮЧНОВЫЙ  ФиналРЕзультатКЛЮЧНОВЫЙ !!!   "
                    + ФиналРЕзультатКЛЮЧНОВЫЙ + "\n" +
                    " ONESIGNAL  КЛЮЧНОВЫЙ  ФиналРЕзультатКЛЮЧНОВЫЙ !!!   " + ФиналРЕзультатКЛЮЧНОВЫЙ + "\n" +
                    " ONESIGNAL  КЛЮЧНОВЫЙ  ФиналРЕзультатКЛЮЧНОВЫЙ !!!   " + ФиналРЕзультатКЛЮЧНОВЫЙ + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }













    private void МетодУвеличениеВерсииДанныхПриСменеКлючаOneSingnal(Context context
            , String ТаблицаКоторуюнадоИзменитьВерсиюДанныхТАюдицы_VIEW_ONESIGNAL
            , Integer РезультатОбновленияКлючаДляПервойТаблицыsettings_tabelssОбаview_onesignal)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Class_GRUD_SQL_Operations class_grud_sql_operationsПовышаемВерсиюДанныхПриПолученииНовогоКлючаONESINGLE = new Class_GRUD_SQL_Operations(context);
            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE =
                    new SubClassUpVersionDATA().upVersionCurentTable(
                            ТаблицаКоторуюнадоИзменитьВерсиюДанныхТАюдицы_VIEW_ONESIGNAL, context);
            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE  " +
                    РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }









    // TODO: 29.12.2021
    protected Integer МетодПослеУспешнойОбновленияКлючаОтOneSignalИщемУдаляемДубли(Context context,
                                                                                   String ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL,
                                                                                   Integer ПубличныйIDДляФрагмента
            , String НовыйIdОТСервтераOneSignal) {

        Integer РЕзультаПосикаИУдаления = 0;
        try {

            // TODO: 15.12.2021  увеличиваем версию данных в таблице обшей модификацион клиенв
            Log.i(this.getClass().getName(), "  пОИСК ИУДАЛЕНИЯ ДУБЛЕЙ В ТАБЛИЦАХ КЛЮЧЕЙ" +
                    "ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL  " + ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL + " ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента +
                    "  НовыйIdОТСервтераOneSignal " + НовыйIdОТСервтераOneSignal);
            ////TODO УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ  В ТАБЛИЦЕ MODIFICATION CLIENT


            Class_GRUD_SQL_Operations class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц = new Class_GRUD_SQL_Operations(context);

            // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ удаление данных

            class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL);
            //

            class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                    concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеУдаление", "onesignal <> ? AND  user_update =?");
            ///

            class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц
                    .concurrentHashMapНабор.put("ЗначениеФлагУдаление", НовыйIdОТСервтераOneSignal);
            class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц
                    .concurrentHashMapНабор.put("ЗначениеФлагУдалениеВторой", ПубличныйIDДляФрагмента);


            ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ


            РЕзультаПосикаИУдаления = (Integer) class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                    new DeleteData(context).deletedata(class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                            concurrentHashMapНабор,
                    new PUBLIC_CONTENT(context).МенеджерПотоков, sqLiteDatabase);


            Log.i(this.getClass().getName(), "  РЕзультаПосикаИУдаления" +
                    РЕзультаПосикаИУдаления);


        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

            // TODO: 11.05.2021 запись ошибок


        }
        return РЕзультаПосикаИУдаления;
    }


}
