package businesslogic.bl_bluetooths.blscanners;

import businesslogic.GetParserDate.GetParsingDate;
import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blscanners.interfaces.BinesslogicGetScannerInterface;
import businesslogic.genertoringjson.GeneratorJsonWriteValue;
import com.sun.istack.NotNull;
import io.reactivex.rxjava3.core.Completable;
import model.*;
import org.hibernate.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.LockModeType;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Named
public class BinesslogicGetScanner implements BinesslogicGetScannerInterface {// extends WITH

    private ServletContext ЛОГ;
    //private	Connection conn; ////// общий коннект для всего севлтера
    private Statement stmt;
    @SuppressWarnings("unused")
    private String ПубличноеHeaderИмя = null;
    @SuppressWarnings("unused")
    private String ОшибкаВМетодеdoPOST = new String();
    private int КоличествоСтрокКоторыеМыОтправимНаКлиент;
    private String ИмяПолученныйИзSQlServerПосик = null;
    @SuppressWarnings("unused")
    private String ПарольПолученныйОтКлиента = null;
    private String ЛогинПолученныйОтКлиента = null;

    private Integer ПараметрТекущийПользователь = 0;  //TODO ТЕКУЩИЙ ПОЛЬЗОВАТЕЛЬ
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StoredProcedureQuery queryprocedure = null;

    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject
    GeneratorJsonWriteValue generatorJsonWriteValue;

    @Inject
    GetParsingDate getParsingDate;

    // TODO: 06.09.2023  ТОЛЬКО JSON JAJKSON
    @Override
    public byte[]   getByteScanner(@NotNull HttpServletRequest request,
                                             @NotNull ServletContext ЛОГ,
                                             @NotNull  HttpServletResponse response ,
                                             @NotNull Session session) {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid=null;
        try   {
            List<?> ЛистДанныеОтHibenide=null;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date()
                    + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1

            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("versionlocal")).stream().mapToLong(m->Long.parseLong(m)).findFirst().orElse(0l);
            /// TODO ПАРАМЕНТ #5
            String     Bremylocal = Optional.ofNullable(request.getParameter("bremylocal")).map(String::new).orElse("");


            Date getBremylocal =getParsingDate. getDateScanner(Bremylocal,ЛОГ);


            ЛОГ.log(" Класс" + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " метод " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "Bremylocal " + Bremylocal);
            
            // TODO: 12.01.2024 определяем какое задание  для Генерации JSON для Android
            switch (JobForServer) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #2
                case "getscanner":
                    ЛОГ.log("Хотим Получить  JSON" + new Date() + " JobForServer "
                            + JobForServer+"  VersionData" + VersionData
                            + " NameTable " + NameTable);

                            // TODO ГЛАВНЫЙ РАСПРЕДЕЛИТЕЛЬ КАКАЯ ТЕКУЩАЯ ТАБЛИЦА ОБРАБАТЫВАЕМСЯ
                    ЛистДанныеОтHibenide           = getReadDataScanner(ЛОГ, session,  NameTable, VersionData,getBremylocal);

                    ЛОГ.log(" Класс" + Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " метод " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "ЛистДанныеОтHibenide " + ЛистДанныеОтHibenide +
                            " JobForServer " + JobForServer);
                            break;

            }


            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            БуферCallsBackДляAndroid=    generatorJsonScanner(  ЛистДанныеОтHibenide,ЛОГ );


            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletScanner.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
    }











    @Override
     public List<?> getReadDataScanner(ServletContext ЛОГ,
                                             Session session,
                                             String NameTable,
                                             Long VersionData,
                                             Date getBremylocal) {
        AtomicReference<List<?>> ЛистДанныеОтHibenide = new AtomicReference<>();
        try{
        // TODO: 27.08.2024
        Completable.fromAction(()->{

            switch (NameTable.trim()) {
                // TODO: 27.08.2024
                case "listmacmasterssous":
                    // TODO: 27.08.2024 САм запросс
                    TypedQuery<ListmacmasterssousEntity> queryДляHiberite =  session.createQuery(
                            "SELECT   DISTINCT f FROM model.ListmacmasterssousEntity  f   " +
                                    "WHERE f.currentTable > :id   AND f.dateUpdate > :getDatelocal ORDER BY f.currentTable    "
                            , ListmacmasterssousEntity.class) ;
                             queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                          queryДляHiberite.setParameter("getDatelocal",  getBremylocal   );//TimestampType.INSTANCE
                    // TODO: 7.08.2024  Результат
                    ЛистДанныеОтHibenide.set(queryДляHiberite.setFirstResult(0).setMaxResults(1000)
                            .setLockMode(LockModeType.PESSIMISTIC_READ).getResultList());

                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ЛистДанныеОтHibenide "
                            + ЛистДанныеОтHibenide.get() );
                    break;

            }

        }).doOnComplete(()->{
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                })
                .blockingSubscribe();
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   );
    } catch (Exception e) {
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }


        return  ЛистДанныеОтHibenide.get();
    }










    // TODO: 30.10.2023 метод изходя из теукущей таблицыв выбираем размер данных для генерации Jakson
    @Override
   public byte[] generatorJsonScanner(@NotNull  List<?> ЛистДанныеОтHibenide
            , @NotNull ServletContext ЛОГ) {
        byte[] БуферCallsBackДляAndroid=null;
        try{
            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            if (ЛистДанныеОтHibenide!=null ) {
                if (ЛистДанныеОтHibenide.size()>0) {
                    БуферCallsBackДляAndroid=        generatorJsonWriteValue.getGeneratorJson(ЛистДанныеОтHibenide ,ЛОГ);
                }else {
                    БуферCallsBackДляAndroid=new byte[0];
                }
                // TODO: 30.10.2023 Когда нет данных
            }else {
                БуферCallsBackДляAndroid=new byte[0];
            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " БуферCallsBackДляAndroid " +БуферCallsBackДляAndroid);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return  БуферCallsBackДляAndroid;
    }



}
