package SessionBeans;

import businesslogic.*;
import businesslogic.bl_sessionshibernate.GetSessionsHibernate;
import businesslogic.bl_sessionshibernate.GetSessionsHibernateGatt;
import businesslogic.bl_transactionHibernate.GetTransactionHibernate;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Session Bean implementation class BeanGET
 */
@Stateless(mappedName = "BeanGET")
@LocalBean
@Transactional
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanGET   {
    /**
     * Default constructor.
     */
    @Inject
    private  GeneratorDataFromAndroid generatorDataFromAndroid;
    @Inject
    private  BEANCallsBack bEANCallsBack;
    @Inject
    private SubClassWriterErros subClassWriterErros;


    @Inject
    private AsyncContextComplete asyncContextComplete;

    @Inject
    private AyntificationDontPasswordAndLogin ayntificationDontPasswordAndLogin;




    @Inject
    public GetTransactionHibernate getTransactionHibernate;




    @Asynchronous
    public void   МетодБинаGET(@NotNull ServletContext ЛОГ,
                               @NotNull HttpServletRequest request,
                               @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {
        // TODO: 05.10.2024
        Session sessionTabelGet=null;
        try {
            // TODO: 10.03.2023  данные от GET метода
            byte[]    БуферРезультатGETByte=null;

            // TODO: 05.10.2024
            sessionTabelGet= getTransactionHibernate.getstartingJdbcBeginTransaction();
            // TODO: 05.10.2024
            if (sessionTabelGet.isOpen() && sessionTabelGet.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                // TODO: 01.11.2023 Получаем Сессию
            // TODO: 01.11.2023 Аунтификайия Имя И Пароль
            Boolean СтатусаАунтификацииПользователя =
                    ayntificationDontPasswordAndLogin
                            .successAyntificationUserForServlets(request, sessionTabelGet, ЛОГ);

            String    JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("").trim();


            if(!JobForServer.isEmpty()){


                // TODO: 02.02.2024 когда логин и пароль правельный Succeess
            if (   СтатусаАунтификацииПользователя == true  ) {
                // TODO: 02.02.2024 когда логин и пароль правельный Succeess
                БуферРезультатGETByte = CallBackSucceessAuntificazions(ЛОГ, request, response, sessionTabelGet, СтатусаАунтификацииПользователя, JobForServer);

                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                        " JobForServer " +JobForServer);






                // TODO: 02.02.2024 когда логин и пароль неправильный
            }else {
                // TODO: 02.02.2024 когда логин и пароль неправильный

                БуферРезультатGETByte = generatorDataFromAndroid.ГлавныйМетод_МетодаGETService(request, ЛОГ,sessionTabelGet);




                // TODO: 29.11.2023 закрывам Сесиою HIbernate
             //   transationSession.commitingTransastion( ЛОГ);

                // TODO: 11.03.2023  нет не имени не пароля
                ayntificationDontPasswordAndLogin.ayntificationDontPasswordAndLogin(request, response, ЛОГ);

                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                        " JobForServer " +JobForServer);
            }


                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                        " JobForServer " +JobForServer);

            }

            }

            // TODO: 05.10.2024

            // TODO: 05.10.2024
            getTransactionHibernate.getstartingJdbcCommitTransaction(sessionTabelGet);

            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
          // TODO: 17.11.2023 ERROR transaction
            getTransactionHibernate.getstartingJdbcRollbackTransaction(sessionTabelGet);
            // TODO: 05.10.2024
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }

}

    private byte[] CallBackSucceessAuntificazions(ServletContext ЛОГ, HttpServletRequest request, HttpServletResponse response,
                                                  Session session, Boolean СтатусаАунтификацииПользователя, String JobForServer) throws SQLException {
        byte[] БуферРезультатGETByte = new byte[0];
        try{
        if (JobForServer.trim().equalsIgnoreCase("Хотим Получить  JSON")) {

            БуферРезультатGETByte = generatorDataFromAndroid.ГлавныйМетод_МетодаGETByte(request, ЛОГ, response, session);

            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                    " JobForServer " + JobForServer +" СтатусаАунтификацииПользователя " + СтатусаАунтификацииПользователя);
            // TODO: 30.10.2023  НЕ сам JSON а сопуствубщие данные для радоты обмена
        } else {
            БуферРезультатGETByte = generatorDataFromAndroid.ГлавныйМетод_МетодаGETService(request, ЛОГ, session);

            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                    " JobForServer " + JobForServer +" СтатусаАунтификацииПользователя " + СтатусаАунтификацииПользователя);
        }


        ///TODO ОТВЕТ КЛИЕНТУ ОТ СЕРВЕРА
        bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатGETByte, ЛОГ);


        // TODO: 29.11.2023 закрывам Сесиою Бызова
        asyncContextComplete.endingContextComplete(request, ЛОГ);


    } catch (Exception e) {
        // TODO: 17.11.2023 ERROR transaction
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
        
        
        return БуферРезультатGETByte;
    }




}
