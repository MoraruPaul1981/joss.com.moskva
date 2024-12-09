package businesslogic;



import org.hibernate.Session;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.zip.GZIPInputStream;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;


public class SubClassSessionBeanPOST {//extends    DSU1JsonServlet

    @Inject
    OperasionsInsertAndUpdates operasionsInsertAndUpdates;

    @SuppressWarnings("unused")
    protected ServletContext ЛОГ;
    //private	Connection conn; ////// общий коннект для всего севлтера
    @SuppressWarnings("unused")
    private String ПубличноеHeaderИмя = null;
    private Statement stmt;
    @SuppressWarnings("unused")
    private String ОшибкаВМетодеdoPOST = new String();
    @SuppressWarnings("unused")
    private int КоличествоСтрокКоторыеМыОтправимНаКлиент;
    @SuppressWarnings("unused")
    private Long РезультатОтАндройдаЕгоЛокальнаяВерсияЧата = 0l;
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    @SuppressWarnings("unused")
    private HttpServletResponse response;
    @SuppressWarnings("unused")

    @Inject
    SubClassWriterErros subClassWriterErros;
    Session session;

    public SubClassSessionBeanPOST() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        System.out.println("Конструктор  SubClassМетодаBeanSessionPOST");
    }

    /**
     * @param request
     * @param response
     * @throws SecurityException
     * @return
     */
    @SuppressWarnings("unused")
    public byte[] МетодЗапускаPOST(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull ServletContext ЛОГ,
            @NotNull Session session) throws SecurityException {

        byte[] БуферГлавныйГенерацииJSONДляAndroid = new byte[0];
        try {
            ЛОГ.log("Конструктор  ЗАПУСК МЕТОДА ИЗ POST ()  ГлавныйМетод_МетодаPOST()");
            this.ЛОГ = ЛОГ;
            this.request = request;
            this.response = response;
            this.session=session;
            ЛОГ.log("ЛОГ  POST() " + ЛОГ + " request " + request + " response "
                    + response);
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора   " +
                    stmt);
            // TODO ПРИШЛИ ПАРАМЕТРЫ В МЕТОДЕ POST
         String   NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            ЛОГ.log("  ПараметрИмяТаблицыОтАндройдаPost " + NameTable);
            ///TODO ПАРАМЕНТ #2
            String    JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            //TODO post paramentes
            ЛОГ.log("  ПараметрФильтрПолучаемыхТаблицДляАндройда  " + JobForServer);
            ///TODO ПАРАМЕНТ #5
            switch (JobForServer.trim()) {
                case "Получение JSON файла от Андройда":

                    GZIPInputStream requestInputStream =       new GZIPInputStream(request.getInputStream() );
                InputStream inputStream=    new ByteArrayInputStream(requestInputStream.readAllBytes());

                    ЛОГ.log("  requestInputStream.isReady() " + inputStream.available());///// ПРИШЕДШИХ
                    if (inputStream.available()>0) {///// ЗАХОДИМ											///// КО
                        БуферГлавныйГенерацииJSONДляAndroid = МетодПарсингаJSONФайлПришелОтКлиента(response, NameTable,   inputStream );
                        ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " БуферГлавныйГенерацииJSONДляAndroid "+БуферГлавныйГенерацииJSONДляAndroid.toString());
                    }
                    break;
            }
            ЛОГ.log("БуферГлавныйГенерацииJSONДляAndroid  " + БуферГлавныйГенерацииJSONДляAndroid);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферГлавныйГенерацииJSONДляAndroid;//TODO return  new AsyncResult<StringBuffer>( БуферГлавныйГенерацииJSONДляAndroid);

    }

    protected byte[] МетодПарсингаJSONФайлПришелОтКлиента(
            @NotNull HttpServletResponse response,
            @NotNull String ТаблицаPOST,
            @NotNull InputStream requestInputStream)
            throws InterruptedException, SQLException, BrokenBarrierException, IOException {
        StringBuffer bufferCallsBackToServer = new StringBuffer();
        try {
            bufferCallsBackToServer = operasionsInsertAndUpdates.методCompleteInsertorUpdateData(ЛОГ, requestInputStream, ТаблицаPOST,session);  //TODO Пришли ДАнные От  Клиента

            ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " БуферJSONJackson " + requestInputStream+ " bufferCallsBackToServer " +bufferCallsBackToServer.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return bufferCallsBackToServer.toString().getBytes(StandardCharsets.UTF_8);
    }


}