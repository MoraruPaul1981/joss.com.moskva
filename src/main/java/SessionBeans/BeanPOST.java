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
import java.util.concurrent.ExecutionException;

/**
 * Session Bean implementation class BeanPOST
 */
@Stateless(mappedName = "BeanPOST")
@LocalBean
@Transactional
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanPOST  {
    @Inject
    private SubClassSessionBeanPOST subClassSessionBeanPOST;
    @Inject
    private  BEANCallsBack bEANCallsBack;
    @Inject
    private    SubClassWriterErros subClassWriterErros;





    @Inject
    private AsyncContextComplete asyncContextComplete;

    @Inject
    private AyntificationDontPasswordAndLogin ayntificationDontPasswordAndLogin;

    @Inject
    public GetTransactionHibernate getTransactionHibernate;

    public BeanPOST() {
        // TODO Auto-generated constructor stub
        System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }





    @Asynchronous
    public void МетодБинаPOST(@NotNull ServletContext ЛОГ,
                                      @NotNull HttpServletRequest request,
                                      @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {;

        Session sessionTabelPost=null;
        try {
            // TODO: 05.10.2024
            sessionTabelPost= getTransactionHibernate.getstartingJdbcBeginTransaction();
            // TODO: 05.10.2024
            if (sessionTabelPost.isOpen() && sessionTabelPost.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                // TODO: 01.11.2023 Получаем Сессию
            // TODO: 01.11.2023 Аунтификайия Имя И Пароль
            Boolean СтатусаАунтификацииПользователя =
                    ayntificationDontPasswordAndLogin.successAyntificationUserForServlets(request, sessionTabelPost, ЛОГ);

            if (СтатусаАунтификацииПользователя == true) {
            ///Todo  получаем данные от клиента
          byte[]  БуферРезультатPOST=		subClassSessionBeanPOST.МетодЗапускаPOST(request, response, ЛОГ,sessionTabelPost);
            ///Todo получаем данные от Клиента на Сервер
                bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатPOST, ЛОГ  );

                // TODO: 29.11.2023 закрывам Сесиою Бызова
                asyncContextComplete.endingContextComplete(request, ЛОГ);

            }
            }
            // TODO: 05.10.2024
            // TODO: 05.10.2024
            getTransactionHibernate.getstartingJdbcCommitTransaction(sessionTabelPost);

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            // TODO: 02.11.2023 ROLLBACK
// TODO: 17.11.2023 ERROR transaction
            getTransactionHibernate.getstartingJdbcRollbackTransaction(sessionTabelPost);
            // TODO: 05.10.2024
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }



}

