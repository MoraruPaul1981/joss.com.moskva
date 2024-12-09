package businesslogic.bl_bluetooths.blscanners.beans;


import businesslogic.AsyncContextComplete;
import businesslogic.BEANCallsBack;
import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blscanners.BinesslogicGetScanner;
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
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Stateless(mappedName = "BeanScanner")
@LocalBean
@Transactional
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanScanner {
    /**
     * Default constructor.
     */
    @Inject
    private BinesslogicGetScanner binesslogicGetScanner;
    @Inject
    private BEANCallsBack bEANCallsBack;
    @Inject
    private SubClassWriterErros subClassWriterErros;

    @Inject
    private AsyncContextComplete asyncContextComplete;

    @Inject
    public GetTransactionHibernateGatt getstartingGattJtaSession;



    @Asynchronous
    public void   staringScannerBLE(@NotNull ServletContext ЛОГ,
                                    @NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        Session sessionGattScanner=null;
        try {
            // TODO: 10.03.2023  данные от GET метода
            byte[]    БуферРезультатGETByte=null;
            // TODO: 10.03.2023  данные от GET метода
               sessionGattScanner= getstartingGattJtaSession.getstartingJdbcBeginTransaction();
                // TODO: 05.10.2024
                // TODO: 05.10.2024
                if (sessionGattScanner.isOpen() && sessionGattScanner.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                    // TODO: 05.10.2024

                String    JobForServerScaner = Optional.ofNullable(request.getParameter("JobForServer")).orElseGet(()->"");

                if(!JobForServerScaner.isEmpty()){
                    // TODO: 02.02.2024 когда логин и пароль правельный Succeess
                        // TODO: 02.02.2024 когда логин и пароль правельный Succeess
                    if (JobForServerScaner.trim().equalsIgnoreCase("getscanner")) {

                        БуферРезультатGETByte = binesslogicGetScanner.getByteScanner(request, ЛОГ, response, sessionGattScanner);

                        ЛОГ.log(" Класс" + Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " метод " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "БуферРезультатGETByte " + БуферРезультатGETByte +
                                " JobForServerScaner " + JobForServerScaner);
                        // TODO: 30.10.2023  НЕ сам JSON а сопуствубщие данные для радоты обмена
                    }

                    ///TODO ОТВЕТ КЛИЕНТУ ОТ СЕРВЕРА
                    bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатGETByte, ЛОГ);

                    // TODO: 29.11.2023 закрывам Сесиою Бызова
                    asyncContextComplete.endingContextComplete(request, ЛОГ);

                        ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                +"\n"+
                                " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                                " JobForServerScaner " +JobForServerScaner);


                        // TODO: 02.02.2024 когда логин и пароль неправильный

                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
                            " JobForServerScaner " +JobForServerScaner);

                }

            }
            // TODO: 05.10.2024
            getstartingGattJtaSession.getstartingJdbcCommitTransaction(sessionGattScanner);
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            getstartingGattJtaSession.getstartingJdbcRollbackTransaction(sessionGattScanner);
            // TODO: 05.10.2024
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletScanner.txt");
        }

    }
    }