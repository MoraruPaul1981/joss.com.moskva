package runtimes;

import businesslogic.BEANCallsBack;
import businesslogic.SubClassConnectionsSQLServer;
import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sun.istack.NotNull;
import org.hibernate.*;

import javax.ejb.*;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Stateless(mappedName = "SessionBeanGETRuntimeJboss")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SessionBeanGETRuntimeJboss {// extends WITH

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

    @Inject
    BEANCallsBack bEANCallsBack;

    public SessionBeanGETRuntimeJboss() {

        System.out.println("Конструктор  SessionBeanGETRuntimeJboss");

    }


    public void МетодГлавныйRuntimeJboss(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {;
        try {
            ///Todo  получаем данные от клиента
            Future<StringBuffer> БуферРезультатRuntime= 	 МетодЗапускаRuntime(request,ЛОГ,response);
            //  ЛОГ.log("  БуферРезультатGET  " + БуферРезультатPOST.get());
            ///Todo получаем данные от Клиента на Сервер
            bEANCallsBack.МетодBackДанныеКлиенту(response, БуферРезультатRuntime.get(), ЛОГ);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+"  БуферРезультатRuntime  " + БуферРезультатRuntime.get());
        } catch (Exception e) {
            new dsu1glassfishatomic.SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber(),
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }






    @Asynchronous
    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    public Future<StringBuffer> МетодЗапускаRuntime(@NotNull HttpServletRequest request,
                                                    @NotNull ServletContext ЛОГ,
                                                    @NotNull  HttpServletResponse response) throws SecurityException, SQLException {
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
        return new AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
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
