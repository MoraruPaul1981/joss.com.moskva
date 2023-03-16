package businesslogic;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.hibernate.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sun.istack.NotNull;

@RequestScoped
@Produces
public class SubClassSessionBeanМетодаGET {// extends WITH

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
    private Long ПараметрВерсияДанных = 0l;//TOD ВЕРСИЯ ДАННЫХ
    private Integer ПараметрТекущийПользователь = 0;  //TODO ТЕКУЩИЙ ПОЛЬЗОВАТЕЛЬ
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StoredProcedureQuery queryprocedure = null;
    @Inject
    private SubClassConnectionsSQLServer subClassConnectionsSQLServer;


    private    Session session;
    private    Transaction sessionTransaction  ;

    public SubClassSessionBeanМетодаGET() {

        System.out.println("Конструктор  SubClassSessionBeanМетодаGET");

    }

    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    protected StringBuffer ГлавныйМетод_МетодаGET(@NotNull HttpServletRequest request,
                                                  @NotNull ServletContext ЛОГ,
                                                  @NotNull SessionFactory sessionSousJbossRuntime) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        StringBuffer БуферCallsBackДляAndroid = null;
        try (Connection conn = subClassConnectionsSQLServer.МетодGetConnect(ЛОГ);) {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;
            // TODO Коннектимся к Базе SQl Server
            ЛОГ.log("ЛОГ  GET() " + ЛОГ + " request " + request + " response " + response);

            // TODO
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзStatement    conn"
                    + conn);
            stmt = subClassConnectionsSQLServer.МетодGetSmtr(conn, ЛОГ);
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора   "
                    + stmt);
            ////
            БуферCallsBackДляAndroid = new StringBuffer();
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            String JobsFroServerЗаданиеДляСервера = null;
            String ПараметрФильтрНаДанныеСервлета = null;
            String JobsServerСазаданиеДляСервера = null;
            String ПараметрКонкретнаяТаблицаВПотокеВнутриПотока = null;


            JsonObjectBuilder builder = null; ////// БИЛДЕР СОЗДАНИЕ JSON
            String результатшифрование;
            String РезультатОбновлениеДляОтправкиВАндройд;//// для понимания
            String пароль;
            int КоличествоСтрокВБАзеSQLSERVER = 0;
            JsonObjectBuilder JsonПоля = Json.createObjectBuilder();
            // JsonArrayBuilder МассивБилдер=Json.createArrayBuilder();

            int ДляПосикаКоличествоСтолбцовВБАзеSQLSERVER;
            String queryJSON = new String(); /// ПОЛУЧЕННЫЙ JSON САМО ТЕЛО ОТВЕТА
            String ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать;//// ;//////указываем
            int РазрешонныеПрава = 2;// TODO права для табельного учёта 2 два только
            // Количество колонок в результирующем запросе
            int СколькСтрокРезультатЕслиТакойПользовательМетод_GET = 0;
            ЛОГ.log("СТАРТ/START МЕТОД/METOD  protected void doGet  logger  ::: " + new Date() + "\n");
            /// TODO logginf info
            ЛОГ.log("СТАРТ/START МЕТОД/METOD  protected void doGet  logger  ::: " + "\n");
            ///// TODO создание клуча
            System.out.println(" protected void doGet");
            ///
            StringWriter stringWriterМассивВерсия = null;
            ///////
            JsonWriter jsonWriterВерсия;
            /////// асинхронный код запускаем
            String ТолькоДляАунтификацииИмяПолученныйИзSQlServerПосик = new String();
            //////
            String ПараметрИмяТаблицыОтАндройдаGET = new String();/// ОПРЕДЕЛЯЕМ
            /////// НАЧАЛО КОД ДОСТУПА К СЕРВЛЕТУ
            String HeaderСодержимое = new String();
            // Количество колонок в результирующем запросе
            СколькСтрокРезультатЕслиТакойПользовательМетод_GET = 0;
            ИмяПолученныйИзSQlServerПосик = new String();/// вычисялем
            String ПарольПолученныйИзSQlServerПосик = null;

            ResultSet РезультатСканированиеИмениИПароль = null;// ОЧИЩАЕМ
            String queryСканируемИмяИпароль;
            String HeaderСодержимоеРасшифрован = null;



            Integer IDПолученныйИзSQlServerПосик = Optional
                    .ofNullable(ЛОГ.getAttribute("IDПолученныйИзSQlServerПосик").toString()).map(Integer::new)
                    .orElse(0);
            System.out.println("  IDПолученныйИзSQlServerПосик " + IDПолученныйИзSQlServerПосик);
            org.hibernate.Query queryДляHiberite = null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IDПолученныйИзSQlServerПосик>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                session=   sessionSousJbossRuntime.openSession();
                sessionTransaction = session.getTransaction();
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session);
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }


            /// TODO ПАРАМЕНТ #1
            ПараметрИмяТаблицыОтАндройдаGET = Optional.ofNullable(request.getParameter("ИмяТаблицыОтАндройда"))
                    .map(String::trim).orElse("");
            System.out.println("  ПараметрИмяТаблицыОтАндройдаGET " + ПараметрИмяТаблицыОтАндройдаGET);
            /// TODO ПАРАМЕНТ #2
            ПараметрФильтрНаДанныеСервлета = Optional.ofNullable(request.getParameter("ФильтрДляДанныхСервлета"))
                    .map(String::trim).orElse("");
            System.out.println("  ПараметрФильтрНаДанныеСервлета  " + ПараметрФильтрНаДанныеСервлета);
            /// TODO ПАРАМЕНТ #3
            ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать = Optional
                    .ofNullable(request.getParameter("КонкретнаяТАблицаКоторойНУжноВерсиюУзнать")).map(String::trim)
                    .orElse("");
            System.out.println("  ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать  "
                    + ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать);
            /// TODO ПАРАМЕНТ #4
            JobsServerСазаданиеДляСервера = Optional.ofNullable(request.getParameter("ЗаданиеДляСервлетаВнутриПотока"))
                    .map(String::trim).orElse("");
            System.out.println("  ПараметрФильтрПолучаемыхТаблицДляАндройда  " + JobsServerСазаданиеДляСервера);
            /// TODO ПАРАМЕНТ #5
            ПараметрКонкретнаяТаблицаВПотокеВнутриПотока = Optional
                    .ofNullable(request.getParameter("КонкретнаяТаблицаВПотоке")).map(String::trim).orElse("");
            System.out.println(
                    "  ПараметрКонкретнаяТаблицаВПотокеВнутриПотока  " + ПараметрКонкретнаяТаблицаВПотокеВнутриПотока);

            /// TODO ПАРАМЕНТ #7
            String ПараметрПользовательФильтр=Optional.ofNullable(request.getParameter("IDДляПолучениеКонткртнойНабораТаблиц")).map(String::new).orElse("");
            if(ПараметрПользовательФильтр.length()>0) {
                ПараметрТекущийПользователь = Optional.ofNullable(ПараметрПользовательФильтр).map(Integer::new).orElse(0);
                ЛОГ.log("  ПараметрФильтрПолучаемыхТаблицДляАндройда  "+ ПараметрТекущийПользователь); //setParameter
            }


            /// TODO ПАРАМЕНТ #8
            String ПараметрВерсияДанныхФильтр=	 Optional.ofNullable(request.getParameter("РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер")).map(String::new).orElse("");
            if (ПараметрВерсияДанныхФильтр.length()>0) {
                ПараметрВерсияДанных= Optional.ofNullable(ПараметрВерсияДанныхФильтр).map(Long::new).orElse(0l);
                ЛОГ.log("  РезультатОтАндройдаЕгоЛокальнаяВерсияЧата  "	+ ПараметрВерсияДанных); //setParameter
            }

            // TODO ТРЕТИЙ ДЕЙСТВИЕ САМА РАБОТА КОТОРУЮ НУЖНО СЕРВЕРУ ВЫПОЛНИТЬ

            ЛОГ.log("request.getParameter(\"ЗаданиеДляСервлетаВнутриПотока\")+ "
                    + request.getParameter("ЗаданиеДляСервлетаВнутриПотока"));

            JobsFroServerЗаданиеДляСервера = Optional.ofNullable(request.getParameter("ЗаданиеДляСервлетаВнутриПотока"))
                    .orElse("");

            switch (JobsFroServerЗаданиеДляСервера) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #1
                case "Хотим Получить Версию Данных Сервера":
                    БуферCallsBackДляAndroid = МетодПолучениеВерсиюДанныхДляАndroid(
                            response, JobsServerСазаданиеДляСервера, ПараметрИмяТаблицыОтАндройдаGET);
                    ЛОГ.log("Хотим Получить Версию Данных Сервера" + new Date() + " ПараметрФильтрЗадааниеДляСервлета "
                            + JobsServerСазаданиеДляСервера + "  БуферCallsBackДляAndroid "
                            + БуферCallsBackДляAndroid.toString());
                    break;




                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #2
                case "Хотим Получить  JSON":

                    ЛОГ.log("Хотим Получить  JSON" + new Date() + " JobsServerСазаданиеДляСервера "
                            + JobsServerСазаданиеДляСервера+"  ПараметрВерсияДанных" + ПараметрВерсияДанных
                            + " ПараметрИмяТаблицыОтАндройдаGET " + ПараметрИмяТаблицыОтАндройдаGET);
                    ////////////// ГЕНЕРАЦИЯ JSON ДЛЯ ВСЕХ  ТАБЛИЦ
                    sessionTransaction.begin();
                    // TODO ГЛАВНЫЙ РАСПРЕДЕЛИТЕЛЬ КАКАЯ ТЕКУЩАЯ ТАБЛИЦА ОБРАБАТЫВАЕМСЯ
                    switch (ПараметрИмяТаблицыОтАндройдаGET.trim()) {
                        case "organization":
                            // TODO
                            queryДляHiberite  = session.createQuery(
                                    " SELECT o FROM Organization o WHERE o.currentTable > :id ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Organization>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite+ " ПараметрИмяТаблицыОтАндройдаGET " +ПараметрИмяТаблицыОтАндройдаGET);//gson Gson
                            break;
                        case "depatment":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT d FROM Depatment d   WHERE d.currentTable > :id ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));
                            ЛистДанныеОтHibenide =( List<model.Depatment>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT f FROM Fio f   WHERE f.currentTable > :id ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Fio>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "region":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT r  FROM Region r   WHERE r.currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Region>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "cfo":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT c FROM Cfo  c  WHERE c.currentTable > :id ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Cfo>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "settings_tabels":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT st FROM Settingtab  st  WHERE st.currentTable > :id  AND  st.userUpdate=:user_update ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Settingtab>) queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "notifications":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT notif FROM Notification  notif  WHERE notif.currentTable > :id "
                                            + " AND   notif.userUpdate=:user_update   "
                                            + "  OR notif.currentTable > :id  AND     notif.idUser=:id_user ");
                            //query4.setLockOptions( new LockOptions(  LockMode.PESSIMISTIC_READ)); //
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Notification>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        /// TODO
                        case "data_notification":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT da  FROM  DataNotification da WHERE"
                                            + "  da. currentTable > :id "
                                            + "  AND da.uuidNotifications "
                                            + " IN (SELECT     no.uuid FROM    Notification no  WHERE   no.userUpdate=:user_update   OR  no .idUser=:id_user ) ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataNotification>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "templates":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT te FROM Template  te WHERE te.currentTable > :id  AND te.userUpdate=:user_update  ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Template>) queryДляHiberite.getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio_template":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT fiot FROM FioTemplate  fiot  WHERE fiot.currentTable > :id   AND fiot.userUpdate=:user_update ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.FioTemplate>) queryДляHiberite.getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "chat_users":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  ca FROM ChatUser ca  WHERE ca .currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.ChatUser>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "chats":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT    cat FROM Chat  cat WHERE cat .currentTable > :id "
                                            + " AND   cat .userUpdate=:user_update"
                                            + " OR "
                                            + " cat .currentTable > :id AND   cat .idUser=:id_user  ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Chat>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "data_chat":
                            // TODO
                            queryДляHiberite = session.createQuery( " SELECT    da FROM  DataChat da WHERE  da. currentTable > :id "
                                    + "							  AND da.chatUuid "
                                    + "							 IN (SELECT    ch.uuid FROM    Chat  ch"
                                    + "  WHERE  ch.userUpdate=:user_update  OR ch.idUser=:id_user )   ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataChat>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "tabel":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT  tab FROM Tabel tab  WHERE tab .currentTable > :id  AND tab.userUpdate=:user_update ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Tabel>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "data_tabels":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT  dat FROM DataTabel dat WHERE dat .currentTable > :id  AND dat.userUpdate=:user_update  ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataTabel>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "view_onesignal":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  viewone FROM ViewOnesignal viewone WHERE viewone .currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.ViewOnesignal>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "nomen_vesov":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  nome FROM NomenVesov nome WHERE nome .currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.NomenVesov>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "type_materials":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  typem FROM TypeMaterial typem  WHERE typem .currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.TypeMaterial>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "get_materials_data":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  getmat FROM GetMaterialsData  getmat  WHERE getmat .currentTable > :id  AND getmat.userUpdate=:user_update ");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.GetMaterialsData>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "company":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  comp FROM Company  comp  WHERE comp .currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Company>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "track":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  tr FROM Track tr  WHERE tr .currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ).setTimeOut( LockOptions.WAIT_FOREVER ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Track>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;



                    }//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (ПараметрИмяТаблицыОтАндройдаGET.trim()) {

                    ЛОГ.log("запуск ..  Метод_ГлавногоМетодаGETВыполнемЗаданиеВтороеГенерацияИОтправкаJSONПотокаНаАндройд"
                            + " КоличествоСтрокКоторыеМыОтправимНаКлиент  " + КоличествоСтрокКоторыеМыОтправимНаКлиент+
                            " ПараметрИмяТаблицыОтАндройдаGET " +ПараметрИмяТаблицыОтАндройдаGET);
                    //TODO ФИНАЛЬЯ СТАДИЯ ГЕНЕРИРУЕМ САМ JSON
                    ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                            "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                    //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ
                    БуферCallsBackДляAndroid =МетодГенерацияJSONJackson(  ЛистДанныеОтHibenide);

                    ЛОГ. log( "   БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString());

                    // TODO конец МЕНЕДЖЕН ПОТОКА ДАННЫХ ПРИ
                    // ОТПРАВЛЕНИЕ ДАННЫ
                    // TODO ВЫХОД ИЗ КОНКРЕТНОГО УСЛОВИЯ
                    // ВЫПОЛЕННИЯ
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #3
                case "Хотим Получить ID для Генерации  UUID":
                    БуферCallsBackДляAndroid = Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду(
                            response, IDПолученныйИзSQlServerПосик);
                    ЛОГ.log(" БуферCallsBackДляAndroid "
                            + БуферCallsBackДляAndroid.toString());
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #4
                case "Хотим Получить Статус Блокировки Пользователя по ID":
                    // TODO ОПРЕДЕЛЯЕМ СТАТУС ПОЛЬЗОВАТЕЛЯ
                    БуферCallsBackДляAndroid = Метод_МетодаGETОтправляемБлокировкуПользователюID(
                            response, JobsServerСазаданиеДляСервера, IDПолученныйИзSQlServerПосик, conn);
                    ЛОГ.log(" Отправили  Хотим Получить Статус Блокировки Пользователя по ID "
                            + JobsServerСазаданиеДляСервера + " БуферCallsBackДляAndroid "
                            + БуферCallsBackДляAndroid.toString());
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #5
                case "Хотим Получить Статус Реальной Работы SQL SERVER":
                    // TODO РЕАЛЬНЫЙ СТАТУС РАБОТЫ SQL SERVER
                    БуферCallsBackДляAndroid = Метод_МетодаGETЗаданиеХотимПолучитьРеальныйСтатусРаботыSQLSeever(
                            response, JobsServerСазаданиеДляСервера, conn);
                    ЛОГ.log(" Отправили Хотим Получить Статус Реальной Работы SQL SERVER " + JobsServerСазаданиеДляСервера
                            + " БуферCallsBackДляAndroid "
                            + БуферCallsBackДляAndroid.toString());
                    break;

                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                default:
                    break;
            }

            // TODO КОГДА ЛОГИН И ПАРОЛЬ НЕТ ДОСТУПА
              МетодЗакрываемСессиюHibernate(session);
            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("БуферCallsBackДляAndroid.toString() " + "" + БуферCallsBackДляAndroid.toString());
            /////// ошибки метода doGET
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                            + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ,
                    БуферCallsBackДляAndroid.toString());
        }
        return БуферCallsBackДляAndroid; // TODO return new
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }

    private void МетодЗакрываемСессиюHibernate(Session session) {
        try{
            if (    sessionTransaction.isActive()) {
                sessionTransaction.commit();
            }
            if (session.isDirty()) {
                session.flush();
            }
            if (session.isOpen() || session.isConnected()) {
                session.clear();
                session.close();
            }
            ЛОГ.log("\n МетодЗакрываемСессиюHibernate "+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" +  "session " +session);
        /////// ошибки метода doGET
    } catch (Exception e) {
            // TODO: 12.03.2023
            sessionTransaction.rollback();
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                        + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                        + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                Thread.currentThread().getStackTrace()[2], ЛОГ,null);
    }
    }

    // todo МЕТОД GET А ПРИНАДЛЕЖИТЬ

    /**
     * @param response
     * @param ПараметрФильтрЗадааниеДляСервлета
     * @param ПараметрИмяТаблицыОтАндройдаGET
     */
    protected StringBuffer МетодПолучениеВерсиюДанныхДляАndroid(
            HttpServletResponse response, String ПараметрФильтрЗадааниеДляСервлета,
            String ПараметрИмяТаблицыОтАндройдаGET) {
        /////// ВЕРСИЮ ДАННЫХ НА СЕРВЕРЕ
        StringBuffer БуферСозданогоJSONВерсияБазыSQLserver = new StringBuffer();
        try {
            System.out.println(
                    "Аутентификация Пользователя Прошла Успешна  (ВЫ ВНУТРИ СЕРВЛЕТА) ПараметрФильтрЗадааниеДляСервлета"
                            + ПараметрФильтрЗадааниеДляСервлета + " finalПараметрИмяТаблицыОтАндройдаGET "
                            + ПараметрИмяТаблицыОтАндройдаGET);
            БуферСозданогоJSONВерсияБазыSQLserver = МетодСозданиеJSONТаблицКоторыеНадоОтправитьКлиенту(
                    ПараметрФильтрЗадааниеДляСервлета, ПараметрИмяТаблицыОтАндройдаGET);
            /// ПЕРВАЯ ПОСЫЛКА ДАННЫХ ИЗ SQL SERVER
            /// НА АНДРОЙД
            System.out.println(
                    "Аутентификация Пользователя Прошла Успешна  (ВЫ ВНУТРИ СЕРВЛЕТА) ПараметрФильтрЗадааниеДляСервлета БуферСозданогоJSONВерсияБазыSQLserver"
                            + БуферСозданогоJSONВерсияБазыSQLserver.toString());

        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                            + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ, ПараметрИмяТаблицыОтАндройдаGET);
        }
        return БуферСозданогоJSONВерсияБазыSQLserver;

    }

    // TODO еще один перенесенный в метод GEt метод


    protected StringBuffer Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду(HttpServletResponse response,
                                                                                     Integer IDПолученныйИзSQlServerПосик) throws IOException {
        StringBuffer ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд = new StringBuffer();
        try {
            System.out.println("ИмяПолученныйИзSQlServerПосик			 " + ИмяПолученныйИзSQlServerПосик);
            /// TODO проверяем если мся и пароль н
            ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд.append(IDПолученныйИзSQlServerПосик);
            ЛОГ.log("ИмяПолученныйИзSQlServerПосик			 " + ИмяПолученныйИзSQlServerПосик
                    + " ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд "
                    + ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд.toString()
                    + " finalIDПолученныйИзSQlServerПосик " + IDПолученныйИзSQlServerПосик);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                            + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ, null);
        }
        return ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд;
    }

    // TODO еще ОДНИ метод перенесенный в метод GET


    protected StringBuffer Метод_МетодаGETОтправляемБлокировкуПользователюID(HttpServletResponse response,
                                                                             String ПараметрФильтрЗадааниеДляСервлета, Integer IDПолученныйИзSQlServerПосик, Connection conn) {
        StringBuffer ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн = new StringBuffer();
        // TODO ЗАПУСКАЕМ МЕНЕДЖЕР ПОТОКОВ ДЯЛ ПЕРВОГО ЗАДАНИЕ ВЕРСИЮ ДАННЫХ
        boolean РезультатЗаблокированПользовательИлиНЕТ = false;
        try {
            ЛОГ.log(" Хотим Получить Статус Блокировки Пользователя по ID " + ПараметрФильтрЗадааниеДляСервлета
                    + " IDПолученныйИзSQlServerПосик " + IDПолученныйИзSQlServerПосик);
            String queryJSON = null;
            String ФиналРезультатЗаблокированПользовательИлиНЕТ = null;
            queryJSON = " SELECT locked FROM     [storage].[dbo].[users]        WHERE id = "
                    + IDПолученныйИзSQlServerПосик + "   AND date_update IS NOT NULL   ;"; // цифра
            PreparedStatement РезультатПолученияСтатусаЗаблокированогоПользователя = conn.prepareStatement(queryJSON,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSetРезультатПолученияСтатусаЗаблокированогоПользователя = РезультатПолученияСтатусаЗаблокированогоПользователя
                    .executeQuery();
            resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.last();
            int ФлагХотеЕстьОднаСтрокаДляЦиклаДва = resultSetРезультатПолученияСтатусаЗаблокированогоПользователя
                    .getRow();
            resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.beforeFirst();
            if (ФлагХотеЕстьОднаСтрокаДляЦиклаДва > 0) {
                while (resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.next()) {
                    @SuppressWarnings("unused")
                    int id = resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.getInt("locked");
                    РезультатЗаблокированПользовательИлиНЕТ = resultSetРезультатПолученияСтатусаЗаблокированогоПользователя
                            .getBoolean("locked");
                    /////////////////////////////
                    ФиналРезультатЗаблокированПользовательИлиНЕТ = String
                            .valueOf(РезультатЗаблокированПользовательИлиНЕТ);
                }
            }
            ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн
                    .append(ФиналРезультатЗаблокированПользовательИлиНЕТ);
            ////
            if (РезультатПолученияСтатусаЗаблокированогоПользователя != null) {
                if (!РезультатПолученияСтатусаЗаблокированогоПользователя.isClosed()) {
                    РезультатПолученияСтатусаЗаблокированогоПользователя.close();
                }
            }
            ЛОГ.log(" ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн"
                    + ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ,
                    ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн.toString());
        }
        return ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн;

    }

    // TODO еще один перенесенный код в метод GET()

    /**
     * @param response
     * @param ПараметрФильтрЗадааниеДляСервлета
     */
    protected StringBuffer Метод_МетодаGETЗаданиеХотимПолучитьРеальныйСтатусРаботыSQLSeever(
            HttpServletResponse response, String ПараметрФильтрЗадааниеДляСервлета, Connection conn) {
        StringBuffer БуферПолучаемРЕальныйСтатусРаботыРАботаеЛИСервр = new StringBuffer();
        try {
            System.out.println("ТУТ ОПРАВЛЯЕМ только СТАТУС Хотим Получить Статус Реальной Работы SQL SERVER");
            @SuppressWarnings("unused")
            Long НаличиефиналуюВерсиюДанныхТолькоМетодаHead = 0l;
            ///////////////////////// TODO
            PreparedStatement preparedStatementПингуемЕслиХотьОднаСтрочкаВБАзеЕслиЕстьТОБАзаАработаетРЕально = conn
                    .prepareStatement(
                            "SELECT TOP 1  id ,login,password  FROM [storage].[dbo].[users]     "
                                    + "     WHERE    rights  = ?      ;  ",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); // id_user
            preparedStatementПингуемЕслиХотьОднаСтрочкаВБАзеЕслиЕстьТОБАзаАработаетРЕально.setInt(1, 2);
            ResultSet resultSetПолученныйРЕзультатРабоатетЛиБазаРЕальноКогдаЕстЬХотьОДнаСТрочка = preparedStatementПингуемЕслиХотьОднаСтрочкаВБАзеЕслиЕстьТОБАзаАработаетРЕально
                    .executeQuery();
            resultSetПолученныйРЕзультатРабоатетЛиБазаРЕальноКогдаЕстЬХотьОДнаСТрочка.last();
            Integer ПолученаяСтрочкаГоворящаяОРеальнойРаботеБАзы = resultSetПолученныйРЕзультатРабоатетЛиБазаРЕальноКогдаЕстЬХотьОДнаСТрочка
                    .getRow();
            System.out.println(
                    "ПолученаяСтрочкаГоворящаяОРеальнойРаботеБАзы" + ПолученаяСтрочкаГоворящаяОРеальнойРаботеБАзы);
            // TODO ПРИСВАИВАЕМ СТАТУС которы будт отправлен НА КЛИЕНТ
            БуферПолучаемРЕальныйСтатусРаботыРАботаеЛИСервр.append(ПолученаяСтрочкаГоворящаяОРеальнойРаботеБАзы);
            System.out.println("ПолученаяСтрочкаГоворящаяОРеальнойРаботеБАзы"
                    + ПолученаяСтрочкаГоворящаяОРеальнойРаботеБАзы + " БуферПолучаемРЕальныйСтатусРаботыРАботаеЛИСервр "
                    + БуферПолучаемРЕальныйСтатусРаботыРАботаеЛИСервр.toString());
            ////
            if (resultSetПолученныйРЕзультатРабоатетЛиБазаРЕальноКогдаЕстЬХотьОДнаСТрочка != null) {
                if (!resultSetПолученныйРЕзультатРабоатетЛиБазаРЕальноКогдаЕстЬХотьОДнаСТрочка.isClosed()) {
                    resultSetПолученныйРЕзультатРабоатетЛиБазаРЕальноКогдаЕстЬХотьОДнаСТрочка.close();
                }
            }
            ЛОГ.log(" оТПРАВИЛИ Хотим Получить Статус Реальной Работы SQL SERVER " + ПараметрФильтрЗадааниеДляСервлета);
        } catch (Exception e) {
            // TODO: handle exception
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ,
                    БуферПолучаемРЕальныйСтатусРаботыРАботаеЛИСервр.toString());
        }
        return БуферПолучаемРЕальныйСтатусРаботыРАботаеЛИСервр;
    }




    // TODO ГЕНЕРАЦИЯ JSON ПО СТАРОМУ
    StringBuffer Метод_ГлавногоМетодаGETВыполнемЗаданиеВтороеГенерацияИОтправкаJSONПотокаНаАндройд(
            HttpServletResponse response, JsonObjectBuilder JsonПоля, String ПараметрИмяТаблицыОтАндройдаGET,
            int КоличествоСтолбцовВБАзеSQLSERVERРАзное, ResultSet РезультатГлавногоЗапросаМетодаGETПосылаемНаКлиентJSON)
            throws SQLException, SecurityException {
        StringBuffer БуферСозданогоJSON = new StringBuffer();
        JsonObjectBuilder СгенерированныйJSONДЛяКлиента = Json.createObjectBuilder();
        try {
            System.out.println(" еСТЬ ЗАПУСКАМЕ СОЗДАНИЕ JSON КоличествоСтрокКоторыеМыОтправимНаКлиент"
                    + КоличествоСтрокКоторыеМыОтправимНаКлиент);
            КоличествоСтрокКоторыеМыОтправимНаКлиент = 0;
            РезультатГлавногоЗапросаМетодаGETПосылаемНаКлиентJSON.last();
            int ФлагХотеЕстьОднаСтрокаДляЦиклаJSON = РезультатГлавногоЗапросаМетодаGETПосылаемНаКлиентJSON.getRow();
            РезультатГлавногоЗапросаМетодаGETПосылаемНаКлиентJSON.beforeFirst();
            ////// //todo ЕСЛИ ЕСТЬ ХОТТЬ ОДНА СТРОКА 0 ТО ФОРМИНИУРЕМ JSON
            ЛОГ.log(" еСТЬ ЗАПУСКАМЕ СОЗДАНИЕ JSON ФлагХотеЕстьОднаСтрокаДляЦиклаJSON "
                    + ФлагХотеЕстьОднаСтрокаДляЦиклаJSON);
            ////// Сгенированный JSON
            СгенерированныйJSONДЛяКлиента = МетодГенерацииJSONПОтокаДЛяОтправкиКлиенту(
                    КоличествоСтолбцовВБАзеSQLSERVERРАзное, РезультатГлавногоЗапросаМетодаGETПосылаемНаКлиентJSON);
            ЛОГ.log(" ОТРАБОТАЛ ...  МетодГенерацииJSONПОтокаДЛяОтправкиКлиенту ---> " + СгенерированныйJSONДЛяКлиента);
            ////// Преобразовавываем в Буфер <----- JSON
            БуферСозданогоJSON = МетодВторойПослеГенрацииJSONСтрочкеДляФорматируемЕгоВБуфер(
                    СгенерированныйJSONДЛяКлиента);
            // TODO результа второго метод а УЖЕ ПОЛУЧАЕТЬСЯ БУФЕР
            ЛОГ.log(" БуферСозданогоJSON " + БуферСозданогоJSON);
            СгенерированныйJSONДЛяКлиента.build().clear();
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                            + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ, ПараметрИмяТаблицыОтАндройдаGET);
        }
        return БуферСозданогоJSON;
    }




    // TODO ГЕНЕРАЦИЯ JSON ПО  НОВОМУ Jackson
    StringBuffer МетодГенерацияJSONJackson(@javax.validation.constraints.NotNull List<?> listОтHiberideДляГенерации)
            throws SQLException, SecurityException {
        StringBuffer БуферСозданогоJSONJackson = new StringBuffer();
        try {
            ЛОГ.log(" listОтHiberideДляГенерации" + listОтHiberideДляГенерации );
            //TODO Jacson парсинг JSON
            JsonFactory factory = new JsonFactory();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
            final ObjectMapper mapperJackson = new ObjectMapper(factory);
            mapperJackson.setDateFormat(df);
            mapperJackson.setLocale(new Locale("ru"));
            mapperJackson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperJackson.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            ObjectWriter writer = mapperJackson.writerWithDefaultPrettyPrinter();
            String Сгенерированыйjson = 	  writer.writeValueAsString(listОтHiberideДляГенерации);
            ЛОГ.  log(" Сгенерированыйjson "+Сгенерированыйjson.length());//gson
            БуферСозданогоJSONJackson.append(Сгенерированыйjson);
            ЛОГ.log(" заработал  Jackson ...  МетодГенерацияJSONJackson --->  БуферСозданогоJSONJackson " + БуферСозданогоJSONJackson.toString() );
            /*
             * // Create custom configuration JsonbConfig nillableConfig = new
             * JsonbConfig().withNullValues(true);
             * nillableConfig.withDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
             * Jsonb jsonb = JsonbBuilder.create(nillableConfig); String resultjsonb =
             * jsonb.toJson(listОтHiberideДляГенерации); ЛОГ.
             * log(" resultjsonb "+resultjsonb.length());//gson
             */

        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                            + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ, БуферСозданогоJSONJackson.toString());
        }
        return БуферСозданогоJSONJackson;
    }




    StringBuffer МетодВторойПослеГенрацииJSONСтрочкеДляФорматируемЕгоВБуфер(
            @NotNull JsonObjectBuilder СгенерированныйJSONДЛяКонфертациивБуфер) {
        StringWriter stringWriterМассив = new StringWriter();
        StringBuffer БуферJSONДляКлиента = new StringBuffer();
        try {
            ЛОГ.log("СгенерированныйJSONДЛяКонфертациивБуфер " + СгенерированныйJSONДЛяКонфертациивБуфер.toString());
            JsonWriter jsonWriter = Json.createWriter(stringWriterМассив);/// ОТКРЫВАЕМ
            jsonWriter.writeObject(СгенерированныйJSONДЛяКонфертациивБуфер.build());// САМО
            БуферJSONДляКлиента.append(stringWriterМассив.getBuffer().toString()).append("\n");//// ПЕРЕВОДИТ
            ЛОГ.log(" БуферJSONДляКлиента  для Отправки Клиенту " + БуферJSONДляКлиента);

        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                            + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ, БуферJSONДляКлиента.toString());
        }
        return БуферJSONДляКлиента;
    }

    // todo ЕЩЕ ОДИН КОД ПЕРЕНЕСЛИВ МЕТОД GET()


    JsonObjectBuilder МетодГенерацииJSONПОтокаДЛяОтправкиКлиенту(@NotNull int КоличествоСтолбцовВБАзеSQLSERVERРАзное,
                                                                 @NotNull ResultSet КурсорГенерацияJSONДляКлиента) throws SQLException, SecurityException {
        JsonObjectBuilder СгенерированныйJSONДляКлиента = Json.createObjectBuilder(); // TODO
        try {

            System.out.println("  ВЫПОЕЛНИЯ СтрокаКлиентJSON " + КурсорГенерацияJSONДляКлиента);
            while (КурсорГенерацияJSONДляКлиента.next()) {
                JsonObjectBuilder JsonгенерацияТекущейСтроки = Json.createObjectBuilder();// TODO стока одна
                String СодержимоеКолонкиВSqlServer = null;
                System.out.println(" количество стобцов  " + КоличествоСтолбцовВБАзеSQLSERVERРАзное);
                int ИндексПоКолонкам;
                String ИдиДляJSONПоле = null;
                for (ИндексПоКолонкам = 1; ИндексПоКолонкам <= КоличествоСтолбцовВБАзеSQLSERVERРАзное; ИндексПоКолонкам++) {
                    String НазваниеСтлбикаДляГенерацииJSON = КурсорГенерацияJSONДляКлиента.getMetaData()
                            .getColumnName(ИндексПоКолонкам);
                    СодержимоеКолонкиВSqlServer = КурсорГенерацияJSONДляКлиента.getString(ИндексПоКолонкам);
                    ИдиДляJSONПоле = String.valueOf(КурсорГенерацияJSONДляКлиента.getString(1)); /// данное

                    ЛОГ.log(" НазваниеСтлбикаДляГенерацииJSON " + НазваниеСтлбикаДляГенерацииJSON
                            + " СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer + " ИдиДляJSONПоле "
                            + ИдиДляJSONПоле + " СтрочкагерериацииJSON " + КурсорГенерацияJSONДляКлиента);
                    if (СодержимоеКолонкиВSqlServer != null && НазваниеСтлбикаДляГенерацииJSON != null) {
                        ЛОГ.log(" НазваниеСтлбикаДляГенерацииJSON " + НазваниеСтлбикаДляГенерацииJSON
                                + " СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer + " ИдиДляJSONПоле "
                                + ИдиДляJSONПоле);
                        JsonгенерацияТекущейСтроки.add(НазваниеСтлбикаДляГенерацииJSON, СодержимоеКолонкиВSqlServer);//// заполение
                        ЛОГ.log(" JsonгенерацияТекущейСтроки " + JsonгенерацияТекущейСтроки.toString()
                                + "   СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer);
                    }
                    ЛОГ.log(" JsonгенерацияТекущейСтроки " + JsonгенерацияТекущейСтроки.toString()
                            + "   СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer);
                }
                СгенерированныйJSONДляКлиента.add(ИдиДляJSONПоле, JsonгенерацияТекущейСтроки.build());
            }
            ;
            // TODO конец генерации полей JSON для отпарви
            if (!КурсорГенерацияJSONДляКлиента.isClosed()) {
                КурсорГенерацияJSONДляКлиента.close();
            }
            // TODO
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                            + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ, СгенерированныйJSONДляКлиента.toString());
        }
        ЛОГ.log(" РЕЗУЛЬТАТ ОБРАБОКИ ГЕНЕРАЦИИ JSON-ПОЛЕЙ   ОТПРАВКИ НА АНДРОЙД  JSONВерхнийКлюч ");
        return СгенерированныйJSONДляКлиента;
    }
    // TODO Еще ОДИН КОД ПЕРЕНЕСЛИ В МЕТОД GET() МОЖЕТ ПОСЛЕДНИЙ А МОЖЕТ И НЕТ

    //// ТУТ---ГЕНРИРУЕМ JSON СПИСОК ТАБЛИЦ КОТОРЫХ НАДО ОТПАРВМТЬ КЛИЕНТУ

    /**
     * @param ПараметрФильтрПолучаемыхТаблицДляАндройда
     * @param ПараметрИмяТаблицыОтАндройдаGET
     * @return
     * @throws SQLException
     */
    protected StringBuffer МетодСозданиеJSONТаблицКоторыеНадоОтправитьКлиенту(
            String ПараметрФильтрПолучаемыхТаблицДляАндройда, String ПараметрИмяТаблицыОтАндройдаGET)
            throws SQLException {
        StringWriter stringWriterМассивВерсия;
        JsonWriter jsonWriterВерсия;
        StringBuffer БуферСозданогоJSONВерсияБазыSQLserver = null;
        try {
            System.out.println("старт метода doGet создание Посылаем версию данных на сервере Version Data SQLServer  "
                    + ПараметрИмяТаблицыОтАндройдаGET);
  /*          String queryДляПолучениеСпискаТаблицДляАндройда = "  SELECT *   FROM   [storage].[dbo].["
                    + ПараметрИмяТаблицыОтАндройдаGET.trim() + "]       ; ";/// ГЛАВНЫЙ,READPAST*/

            String queryДляПолучениеСпискаТаблицДляАндройда = "  SELECT *   FROM  " + ПараметрИмяТаблицыОтАндройдаGET.trim() + "";/// ГЛАВНЫЙ,READPAST
            //////// запрос вычисляет имя и пароль и id
            ResultSet РезультатПолучениеВерсииТаблицSQlServera = stmt
                    .executeQuery(queryДляПолучениеСпискаТаблицДляАндройда);
            JsonObjectBuilder JsonПоляВерсияБазы = Json.createObjectBuilder();
            JsonObjectBuilder JSONВерхнийКлючВерсияБазы = Json.createObjectBuilder();
            String ИдиДляJSONПолеВерсия = null;
            int КоличествоСтолбцовВБАзеSQLSERVERДляВерсии = РезультатПолучениеВерсииТаблицSQlServera.getMetaData()
                    .getColumnCount(); /// вычисялем сколько столбцов
            РезультатПолучениеВерсииТаблицSQlServera.last();
            @SuppressWarnings("unused")
            int КоличествоСтрочекВБАзеSQLSERVERДляВерсии = РезультатПолучениеВерсииТаблицSQlServera.getRow();
            РезультатПолучениеВерсииТаблицSQlServera.beforeFirst();
            РезультатПолучениеВерсииТаблицSQlServera.last();
            int ФлагХотеЕстьОднаСтрокаДляЦикла = РезультатПолучениеВерсииТаблицSQlServera.getRow();
            РезультатПолучениеВерсииТаблицSQlServera.beforeFirst();
            if (ФлагХотеЕстьОднаСтрокаДляЦикла > 0) {
                while (РезультатПолучениеВерсииТаблицSQlServera.next()) {
                    JsonПоляВерсияБазы = Json.createObjectBuilder();
                    // process data
                    for (int ИндексПоКолонкам = 1; ИндексПоКолонкам <= КоличествоСтолбцовВБАзеSQLSERVERДляВерсии; ИндексПоКолонкам++) {
                        РезультатПолучениеВерсииТаблицSQlServera.getMetaData().getColumnName(ИндексПоКолонкам);
                        /////////////// TODO ПЕРВОЕ дейсвтие
                        String НазваниеКолонкиКотроеНУжновставить = РезультатПолучениеВерсииТаблицSQlServera
                                .getMetaData().getColumnName(ИндексПоКолонкам);
                        String СодержимоеКолонкиВSqlServer = РезультатПолучениеВерсииТаблицSQlServera
                                .getString(ИндексПоКолонкам);
                        System.out.println("НазваниеКолонкиКотроеНУжновставить " + НазваниеКолонкиКотроеНУжновставить
                                + " СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer);
                        ИдиДляJSONПолеВерсия = String.valueOf(РезультатПолучениеВерсииТаблицSQlServera.getString(3)); /// данное
                        if (СодержимоеКолонкиВSqlServer != null && НазваниеКолонкиКотроеНУжновставить != null) {////// ЕСЛИ
                            ///// todo САМА ВСТАВКА ДАННЫХ В JSON
                            JsonПоляВерсияБазы.add(НазваниеКолонкиКотроеНУжновставить, СодержимоеКолонкиВSqlServer);//// заполение
                            System.out
                                    .println("НазваниеКолонкиКотроеНУжновставить " + НазваниеКолонкиКотроеНУжновставить
                                            + " СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer);
                        }
                    }
                    System.out.println("ИдиДляJSONПолеВерсия " + ИдиДляJSONПолеВерсия);
                    JSONВерхнийКлючВерсияБазы.add(ИдиДляJSONПолеВерсия, JsonПоляВерсияБазы.build());///// ИНИЗАЛИЦАСИЯ
                    System.out.println(" JSONВерхнийКлючВерсияБазы " + JSONВерхнийКлючВерсияБазы.toString());
                    ///////////////////////
                } // TODO end loop
            }
            stringWriterМассивВерсия = new StringWriter();
            jsonWriterВерсия = Json.createWriter(stringWriterМассивВерсия);/// ОТКРЫВАЕМ
            jsonWriterВерсия.writeObject(JSONВерхнийКлючВерсияБазы.build());// САМО
            БуферСозданогоJSONВерсияБазыSQLserver = new StringBuffer();/// СОЗАДАНИЕ
            БуферСозданогоJSONВерсияБазыSQLserver.append(stringWriterМассивВерсия.getBuffer().toString()).append("\n");//// ПЕРЕВОДИТ
            System.out.println(" СозданныйJSONМассив Для версии" + БуферСозданогоJSONВерсияБазыSQLserver.toString());
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛогинПолученныйОтКлиента,
                    "\n" + " Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                            + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n",
                    Thread.currentThread().getStackTrace()[2], ЛОГ, ПараметрИмяТаблицыОтАндройдаGET);
        }
        return БуферСозданогоJSONВерсияБазыSQLserver;
    }

}
