package businesslogic.bl_bluetooths.blgattserver.beans;


import businesslogic.AsyncContextComplete;
import businesslogic.BEANCallsBack;
import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blgattserver.binesslogic.BinesslogicGetGattServer;
import businesslogic.bl_transactionHibernate.GetTransactionHibernateGatt;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Stateless(mappedName = "BeanGetGattServer")
@LocalBean
@Transactional
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanGetGattServer {
    // TODO: 06.09.2024   GATT SERVER
    @Inject
    private BEANCallsBack bEANCallsBack;
    @Inject
    private SubClassWriterErros subClassWriterErros;

    @Inject
    private AsyncContextComplete asyncContextComplete;


    @Inject
    private BinesslogicGetGattServer binesslogicGetGattServer;


    @Inject
    public GetTransactionHibernateGatt getstartingGattJtaSession;




    @Asynchronous
    public void staringGetGattServer(@NotNull ServletContext ЛОГ,
                                      @NotNull HttpServletRequest request,
                                      @NotNull HttpServletResponse response)
            throws InterruptedException, ExecutionException {
        // TODO: 05.10.2024
        Session sessionGattServer=null;
        try {
            // TODO: 10.03.2023  данные от GET метода
            sessionGattServer= getstartingGattJtaSession.getstartingJdbcBeginTransaction();
            // TODO: 05.10.2024
            // TODO: 05.10.2024
            if (sessionGattServer.isOpen() && sessionGattServer.getTransaction().getStatus()==TransactionStatus.ACTIVE) {
                // TODO: 01.11.2023 Получаем Сессию
                String    JobForGattServerGET = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElseGet(()->"");

                if(!JobForGattServerGET.isEmpty()){

                    switch (JobForGattServerGET.trim()) {
                        // TODO: 16.10.2024
                        case "wegetgattserver":

                            // TODO: 07.09.2024 Отправляем данные Клиенту GATT
                    byte[]    БуферРезультатGetGattServer =    binesslogicGetGattServer.generatingJsontoSendtoGattClient(ЛОГ,request,response,sessionGattServer);




                            // TODO: 07.09.2024 Otvet Client Success
                            bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатGetGattServer, ЛОГ  );

                            // TODO: 29.11.2023 closing Session
                            asyncContextComplete.endingContextComplete(request, ЛОГ);


                            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                    +"\n"+
                                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                                    " JobForGattServerGET " +JobForGattServerGET
                                    + " БуферРезультатGetGattServer " +БуферРезультатGetGattServer);
                            break;
                    }
                }
            }
            // TODO: 05.10.2024
            getstartingGattJtaSession.getstartingJdbcCommitTransaction(sessionGattServer);
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            getstartingGattJtaSession.getstartingJdbcRollbackTransaction(sessionGattServer);
            // TODO: 05.10.2024
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletGattServer.txt");
        }

    }





}
