package businesslogic;


import businesslogic.genertoringjson.GeneratorJsonWriteValue;
import com.sun.istack.NotNull;
import model.MateriBinary;
import model.OrderTc;
import model.VidTc;
import org.hibernate.*;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.LockModeType;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Named("generatorDataFromAndroid")
public class GeneratorDataFromAndroid {// extends WITH

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
    GeneratorJsonWriteValue getGeneratorJsonWriteValue;


    @Inject
    BEANCallsBack beanCallsBack;

    Session session;

    public GeneratorDataFromAndroid() {

        System.out.println("Конструктор  GeneratorDataFromAndroid");

    }

    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    public byte[] ГлавныйМетод_МетодаGETService(@NotNull HttpServletRequest request,
                                                @NotNull ServletContext ЛОГ ,@NotNull Session session) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid=null;
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            this.session=session;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1

            Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
            Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(m->Integer.parseInt(m)).findFirst().orElse(0);
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).map(Long::new).orElse(0l);


            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            org.hibernate.Criteria criteriaquery=null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IdUser>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "    session.getTransaction().getStatus() " +   session.getTransaction().getStatus());

                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session  + " session.getTransaction() " + session.getTransaction());
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }

            switch (JobForServer) {
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #1
                case "Хотим Получить Версию Данных Сервера":
                    ЛистДанныеОтHibenide = МетодДляКлиентаMODIFITATION_Server();
                    ЛОГ.log("Хотим Получить Версию Данных Сервера" + new Date() + " ПараметрФильтрЗадааниеДляСервлета "
                            + ЛистДанныеОтHibenide + "  ЛистДанныеОтHibenide ");
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #4
                case "Хотим Получить Статус Блокировки Пользователя по ID":
                    // TODO ОПРЕДЕЛЯЕМ СТАТУС ПОЛЬЗОВАТЕЛЯ
                    ЛистДанныеОтHibenide = Метод_МетодаСтатусЗаблорированогоКлиента( IdUser, session);
                    ЛОГ.log(" Отправили  Хотим Получить Статус Блокировки Пользователя по ID "
                            + JobForServer + " ЛистДанныеОтHibenide " + ЛистДанныеОтHibenide.size() + " IDПолученныйИзSQlServerПосик "+IdUser);
                    break;


                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #3
                case "Хотим Получить ID для Генерации  UUID":
                    ЛистДанныеОтHibenide = Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду();
                    ЛОГ.log(" ЛистДанныеОтHibenide "
                            + ЛистДанныеОтHibenide);
                    break;


                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }




            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            БуферCallsBackДляAndroid=      generatorJsonFinal(  ЛистДанныеОтHibenide,NameTable,ЛОГ );


            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));

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
            ЛОГ.log("\n" + " ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }






    public byte[] ГлавныйМетод_МетодаGETServiceDontAuntificate(@NotNull HttpServletRequest request,
                                                @NotNull ServletContext ЛОГ ,@NotNull Session session) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid=null;
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            this.session=session;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1

            Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
            Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(Integer::new).findFirst().orElse(0);
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).map(Long::new).orElse(0l);


            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            org.hibernate.Criteria criteriaquery=null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IdUser>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "    session.getTransaction().getStatus() " +   session.getTransaction().getStatus());

                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session  + " session.getTransaction() " + session.getTransaction());
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }

            switch (JobForServer) {
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #3
                case "Хотим Получить ID для Генерации  UUID":
                    ЛистДанныеОтHibenide = Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду();
                    ЛОГ.log(" ЛистДанныеОтHibenide "
                            + ЛистДанныеОтHibenide);
                    break;
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }




            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            БуферCallsBackДляAndroid=      generatorJsonFinal(  ЛистДанныеОтHibenide,NameTable,ЛОГ );


            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));

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
            ЛОГ.log("\n" + " ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }




















    // TODO: 06.09.2023  ТОЛЬКО JSON JAJKSON
    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    public byte[] ГлавныйМетод_МетодаGETByte(@NotNull HttpServletRequest request,
                                             @NotNull ServletContext ЛОГ,
                                             @NotNull  HttpServletResponse response ,
                                             @NotNull Session session) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid=null;
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;

            this.session=session;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  session  " +  session
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1

            Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
            Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream().mapToInt(m->Integer.parseInt(m)).findFirst().orElse(0);
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).stream().mapToLong(m->Long.parseLong(m)).findFirst().orElse(0l);

            // TODO: 11.01.2024 ПРАВА
            Object GrantRigntUsersObject =   Optional.ofNullable(ЛОГ.getAttribute("GrantRigntUsers") ).orElse("0");
            Integer GrantRigntUsers = Optional.ofNullable( GrantRigntUsersObject.toString()).stream() .mapToInt(m->Integer.parseInt(m)).findFirst().orElse(0);


            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            org.hibernate.Criteria criteriaquery=null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            // TODO: 12.01.2024 определяем какое задание  для Генерации JSON для Android

            switch (JobForServer) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #2
                case "Хотим Получить  JSON":
                    ЛОГ.log("Хотим Получить  JSON" + new Date() + " JobForServer "
                            + JobForServer+"  VersionData" + VersionData
                            + " NameTable " + NameTable);


                    ////////////// ГЕНЕРАЦИЯ JSON ДЛЯ ВСЕХ  ТАБЛИЦ

                    // TODO: 11.01.2024  данные выдаем по правам
                    switch (GrantRigntUsers){




                        // TODO: 11.01.2024  данные только для Табель 2
                        case  2:
                            // TODO ГЛАВНЫЙ РАСПРЕДЕЛИТЕЛЬ КАКАЯ ТЕКУЩАЯ ТАБЛИЦА ОБРАБАТЫВАЕМСЯ
                            ЛистДанныеОтHibenide = getTableForGrantForTabel(ЛОГ, session, IdUser, NameTable, VersionData,
                                    queryДляHiberite, ЛистДанныеОтHibenide);

                            ЛОГ.log(" КоличествоСтрокКоторыеМыОтправимНаКлиент  " + КоличествоСтрокКоторыеМыОтправимНаКлиент+
                                    " NameTable " +NameTable+ " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide+" GrantRigntUsers " +GrantRigntUsers);
                            break;





                        // TODO: 11.01.2024  данные только для Согласования  6,7
                        case 6:
                        case 7:
                            // TODO ГЛАВНЫЙ РАСПРЕДЕЛИТЕЛЬ КАКАЯ ТЕКУЩАЯ ТАБЛИЦА ОБРАБАТЫВАЕМСЯ
                            ЛистДанныеОтHibenide = getTableForGrantForCommintingPayOrPrices(ЛОГ, session, IdUser, NameTable,
                                    VersionData, queryДляHiberite, ЛистДанныеОтHibenide );

                            ЛОГ.log(" КоличествоСтрокКоторыеМыОтправимНаКлиент  " + КоличествоСтрокКоторыеМыОтправимНаКлиент+
                                    " NameTable " +NameTable+ " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide+" GrantRigntUsers " +GrantRigntUsers);
                            break;

                    }

                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size()+" GrantRigntUsers " +GrantRigntUsers);
                    break;
            }




            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            БуферCallsBackДляAndroid=    generatorJsonFinal(  ЛистДанныеОтHibenide,NameTable,ЛОГ );


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
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
    }
















    private List<?> getTableForGrantForTabel(ServletContext ЛОГ,
                                             Session session, Integer IdUser,
                                             String NameTable, Long VersionData,
                                             Query queryДляHiberite,
                                             List<?> ЛистДанныеОтHibenide) {
        Criteria criteriaquery;
        switch (NameTable.trim()) {
            case "organization":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT DISTINCT o FROM  Organization o WHERE o.currentTable > :id  ORDER BY o.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                ЛистДанныеОтHibenide =( List<model.Organization>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite + " NameTable " + NameTable);//gson Gson
                break;
            case "depatment":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT  DISTINCT  d FROM  Depatment d   WHERE d.currentTable > :id  ORDER BY d.currentTable  ") ;

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));
                ЛистДанныеОтHibenide =( List<model.Depatment>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "fio":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT   DISTINCT f FROM Fio f   WHERE f.currentTable > :id  ORDER BY f.currentTable    ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                ЛистДанныеОтHibenide =( List<model.Fio>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "region":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT  DISTINCT  r  FROM Region r   WHERE r.currentTable > :id ORDER BY r.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                ЛистДанныеОтHibenide =( List<model.Region>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "cfo":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT  DISTINCT c FROM Cfo  c  WHERE c.currentTable > :id  AND c.closed=:closed  ORDER BY c.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                queryДляHiberite.setParameter("closed",   new Boolean(false));//
                ЛистДанныеОтHibenide =( List<model.Cfo>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "settings_tabels":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT  DISTINCT  st FROM Settingtab  st  WHERE st.currentTable > :id  AND  st.userUpdate=:user_update ORDER BY st.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                ЛистДанныеОтHibenide =( List<model.Settingtab>) queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "notifications":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT notif FROM Notification AS notif  WHERE ( notif.currentTable > :id "
                                + " AND   notif.userUpdate=:user_update  AND  notif.dateStart IS NOT NULL   "
                                + "  OR notif.currentTable > :id  AND     notif.idUser=:id_user AND  notif.dateStart IS NOT NULL  " +
                                ")" +
                                " ORDER BY notif.currentTable  ");
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                queryДляHiberite.setParameter("id_user", IdUser);//8641 8625
                ЛистДанныеОтHibenide =( List<model.Notification>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            /// TODO
            case "data_notification":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT da  FROM  DataNotification da WHERE"
                                + "  da. currentTable > :id "
                                + "  AND da.uuidNotifications "
                                + " IN (SELECT     no.uuid FROM" +
                                "    Notification no  WHERE   no.userUpdate=:user_update   OR  no .idUser=:id_user )  ORDER BY  da.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                queryДляHiberite.setParameter("id_user", IdUser);//8641 8625
                ЛистДанныеОтHibenide =( List<model.DataNotification>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "templates":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT te FROM Template  te WHERE te.currentTable > :id  AND te.userUpdate=:user_update ORDER BY te.currentTable   ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                ЛистДанныеОтHibenide =( List<model.Template>) queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ. log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide +
                        " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "fio_template":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT fiot FROM FioTemplate  fiot  WHERE fiot.currentTable > :id   AND fiot.userUpdate=:user_update  ORDER BY fiot.currentTable   ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                ЛистДанныеОтHibenide =( List<model.FioTemplate>) queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ. log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide +
                        " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;

            case "chat_users":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT  ca FROM ChatUser ca  WHERE ca .currentTable > :id  ORDER BY ca.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.ChatUser>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "chats":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT  cat FROM Chat  cat WHERE cat .currentTable > :id "
                                + " AND   cat .userUpdate=:user_update"
                                + " OR "
                                + " cat .currentTable > :id AND   cat .idUser=:id_user   ORDER BY cat.currentTable    ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                queryДляHiberite.setParameter("id_user", IdUser);//8641 8625
                ЛистДанныеОтHibenide =( List<model.Chat>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "data_chat":
                // TODO
                queryДляHiberite =  session.createQuery( "   SELECT  DISTINCT da   FROM DataChat  da  left JOIN Chat ch ON da.chatUuid =  ch.uuid " +
                        " WHERE  da.currentTable > :id AND da.userUpdate=:id_user   " +
                        "ORDER BY da.currentTable    ");
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("id_user", IdUser);//8641 8625

                ЛистДанныеОтHibenide =( List<model.DataChat>)  queryДляHiberite.setFirstResult(0).setMaxResults(3) .setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "tabel":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT  DISTINCT  tab FROM Tabel tab  WHERE tab .currentTable > :id  AND tab.userUpdate=:user_update " +
                                " AND tab.statusSend!=:statusSend  ORDER BY tab.currentTable   ");
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                ЛистДанныеОтHibenide =( List<model.Tabel>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "data_tabels":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT DISTINCT  dat FROM DataTabel dat WHERE dat .currentTable > :id  AND dat.userUpdate=:user_update" +
                                "  AND dat.statusSend!=:statusSend   ORDER BY dat.currentTable   ");
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                ЛистДанныеОтHibenide =( List<model.DataTabel>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "view_onesignal":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT   DISTINCT  viewone FROM ViewOnesignal viewone WHERE viewone .currentTable > :id  ORDER BY viewone.currentTable  ");

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.ViewOnesignal>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "nomen_vesov":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT   DISTINCT nome FROM NomenVesov nome WHERE nome .currentTable > :id   ORDER BY nome.currentTable  ");
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.NomenVesov>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "type_materials":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT typem FROM TypeMaterial typem  WHERE typem .currentTable > :id  ORDER BY typem.currentTable   " );

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.TypeMaterial>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "get_materials_data":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT  getmat FROM  GetMaterialsData  getmat  WHERE getmat .currentTable > :id  AND getmat.userUpdate=:user_update  " +
                                " AND getmat.statusSend!=:statusSend  ORDER BY getmat.currentTable   ");

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                ЛистДанныеОтHibenide =( List<model.GetMaterialsData>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "company":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT  comp FROM  Company  comp  WHERE comp .currentTable > :id  ORDER BY comp.currentTable  ");

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.Company>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;
            case "track":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT   DISTINCT  tr FROM  Track tr  WHERE tr .currentTable > :id   ORDER BY tr.currentTable  ");

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.Track>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;

            case "prof":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT   DISTINCT  pr FROM Prof pr  WHERE pr .currentTable > :id   ORDER BY pr.currentTable  ");

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.Prof>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;

            case "order_tc":
                // TODO
                criteriaquery =  session.createCriteria(OrderTc.class);
                criteriaquery .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                criteriaquery.add(org.hibernate.criterion.Restrictions.eq("userUpdate", IdUser));
                criteriaquery.add(org.hibernate.criterion.Restrictions.ne("status", 5));
                criteriaquery.addOrder(Order.asc("currentTable"));
                ЛистДанныеОтHibenide = ( List<OrderTc>)   criteriaquery.setFirstResult(0).setMaxResults(1000)
                        .setLockMode(LockMode.PESSIMISTIC_READ).setCacheable(true).list();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;

            case "vid_tc":
                // TODO
                criteriaquery=  session.createCriteria(VidTc.class);
                criteriaquery .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                criteriaquery.addOrder(Order.asc("currentTable"));
                ЛистДанныеОтHibenide =  ( List<VidTc>)     criteriaquery.setFirstResult(0).setMaxResults(1000)
                        .setLockMode(LockMode.PESSIMISTIC_READ).setCacheable(true).list();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;

            case "materials_databinary":
                // TODO  byte
                criteriaquery =  session.createCriteria(MateriBinary.class);
                criteriaquery .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                criteriaquery.addOrder(Order.asc("currentTable"));
                criteriaquery.add(org.hibernate.criterion.Restrictions.eq("userUpdate", IdUser));
                ЛистДанныеОтHibenide = ( List<MateriBinary>)   criteriaquery.setFirstResult(0).setMaxResults(1)
                        .setLockMode(LockMode.PESSIMISTIC_READ).setCacheable(true).list();
               // ЛистДанныеОтHibenide = ( List<MateriBinary>)   criteriaquery .setCacheable(true).list();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
        /*        // TODO: 14.07.2023 TEST code
                DecodeByteArray_Image decodeByteArray_image=new DecodeByteArray_Image();
                decodeByteArray_image.методDecodeByteImage(ЛОГ,ЛистДанныеОтHibenide);*/

                break;


            default:
                ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                        "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                break;



        }//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (NameTable.trim()) {
        return ЛистДанныеОтHibenide;
    }

// TODO: 11.01.2024  данные для только Согласования  6,7



    private List<?> getTableForGrantForCommintingPayOrPrices(ServletContext ЛОГ, Session session, Integer IdUser, String NameTable,
                                                             Long VersionData, Query queryДляHiberite,
                                                             List<?> ЛистДанныеОтHibenide ) {
        Criteria criteriaquery;
        switch (NameTable.trim()) {

            case "settings_tabels":
                // TODO
                queryДляHiberite =  session.createQuery(
                        "SELECT  DISTINCT  st FROM Settingtab  st  WHERE st.currentTable > :id  AND  st.userUpdate=:user_update ORDER BY st.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                queryДляHiberite.setParameter("user_update", IdUser);//8641 8625
                ЛистДанныеОтHibenide =( List<model.Settingtab>) queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;

            case "chat_users":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT  DISTINCT  ca FROM ChatUser ca  WHERE ca .currentTable > :id  ORDER BY ca.currentTable  ") ;
                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.ChatUser>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;



            case "view_onesignal":
                // TODO
                queryДляHiberite =  session.createQuery(
                        " SELECT   DISTINCT  viewone FROM ViewOnesignal viewone WHERE viewone .currentTable > :id  ORDER BY viewone.currentTable  ") ;

                queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                ЛистДанныеОтHibenide =( List<model.ViewOnesignal>)  queryДляHiberite.setFirstResult(0).setMaxResults(1000).setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();
                ЛОГ.  log(" ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide + " ЛистДанныеОтHibenide.size() " + ЛистДанныеОтHibenide.size()+
                        "  queryДляHiberite  " + queryДляHiberite);//gson Gson
                break;

            default:
                ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                        "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                break;

        }//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (NameTable.trim()) {
        return ЛистДанныеОтHibenide;
    }

































    // TODO: 30.10.2023 метод изходя из теукущей таблицыв выбираем размер данных для генерации Jakson
    byte[] generatorJsonFinal(@NotNull  List<?> ЛистДанныеОтHibenide
            , @NotNull String NameTable,@NotNull ServletContext ЛОГ) {
        byte[] БуферCallsBackДляAndroid=null;
        try{
            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            if (ЛистДанныеОтHibenide!=null && ЛистДанныеОтHibenide.size()>0) {
               БуферCallsBackДляAndroid=        getGeneratorJsonWriteValue.getGeneratorJson(ЛистДанныеОтHibenide ,ЛОГ);
                // TODO: 30.10.2023 Когда нет данных
            }else {
                БуферCallsBackДляAndroid=new byte[0];
            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " БуферCallsBackДляAndroid " +БуферCallsBackДляAndroid);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return  БуферCallsBackДляAndroid;
    }





















    // todo ЕЩЕ ОДИН КОД ПЕРЕНЕСЛИВ МЕТОД GET()


    protected     List<model.UsersEntitySuccess>  Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду() throws IOException {
        List<model.UsersEntitySuccess> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
            Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
            Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(m->Integer.parseInt(m)).findFirst().orElse(0);



            ЛистДанныеОтHibenide =( List<model.UsersEntitySuccess>)   session.createQuery("SELECT   usersentity. id FROM model.UsersEntitySuccess" +
                    "  as  usersentity WHERE usersentity.id =:id   ")
                    .setCacheable(true)
                    .setParameter("id",IdUser).setMaxResults(1).setLockOptions(LockOptions.UPGRADE).getResultList();

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());

        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletAuntification.txt");
        }
        return ЛистДанныеОтHibenide;
    }


    // todo МЕТОД генерируем для килента MODIFITATIONServer
    protected    List<model.ModificationServerEntity> МетодДляКлиентаMODIFITATION_Server( ) {
        /////// ВЕРСИЮ ДАННЫХ НА СЕРВЕРЕ
        List<model.ModificationServerEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {


           /// session.sessionWithOptions().autoJoinTransactions().autoJoinTransactions(true);

            ЛистДанныеОтHibenide =( List<model.ModificationServerEntity> )     session.createQuery(
                    "SELECT vd FROM model.ModificationServerEntity  vd ").setCacheable(true).setLockOptions(LockOptions.UPGRADE).getResultList();

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ЛистДанныеОтHibenide;

    }
    // TODO еще генерируем заблокирваный статус клиента
    protected List<model.UsersEntity> Метод_МетодаСтатусЗаблорированогоКлиента(@javax.validation.constraints.NotNull   Integer  IDПолученныйИзSQlServerПосик,
                                                                               @javax.validation.constraints.NotNull Session session ) {
        List<model.UsersEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {


            ЛистДанныеОтHibenide =( List<model.UsersEntity>)   session.createQuery("SELECT us  FROM model.UsersEntity  us WHERE us.id  = :id ")
                    .setCacheable(true)
                    .setParameter("id",new Integer(IDПолученныйИзSQlServerПосик)).setLockOptions(LockOptions.UPGRADE).getResultList();//8641 8625

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ЛистДанныеОтHibenide  ;

    }



}
