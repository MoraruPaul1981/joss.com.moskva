package businesslogic.bl_transactionHibernate;

import businesslogic.SubClassWriterErros;
import businesslogic.bl_sessionshibernate.GetSessionsHibernate;
import businesslogic.bl_sessionshibernate.GetSessionsHibernateGatt;
import businesslogic.bl_sessionshibernate.intarfaces.GetSessionJdbcGattIntarface;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class GetTransactionHibernateGatt {

    @Inject
    GetSessionsHibernateGatt getSessionsHibernateGatt;




    private Logger LOGGER = LoggerFactory.getLogger(GetTransactionHibernateGatt.class);
    @Inject
    SubClassWriterErros subClassWriterErros;



    public Session getstartingJdbcBeginTransaction( ) {
        Session getstartingGattSession=null;
        try {
                getstartingGattSession=  getSessionsHibernateGatt.getstartingGattJdbcSession();
                if (getstartingGattSession.isOpen()) {
                    if (getstartingGattSession.getTransaction().getStatus()!= TransactionStatus.ACTIVE) {
                        getstartingGattSession.getTransaction().begin();
                    }

                }

            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "session  " + getstartingGattSession + " getsessionHibernateGatt.isOpen() " + getstartingGattSession.isOpen()+
                    " session.isOpen() " +getstartingGattSession.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletGattServer.txt");
        }
        return  getstartingGattSession;

    }
    public void getstartingJdbcCommitTransaction( @NotNull  Session getstartingGattJtaSession ) {

        try {
            if (getstartingGattJtaSession.isOpen()) {
                if (getstartingGattJtaSession.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                    getstartingGattJtaSession.getTransaction().commit();
                }

            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "getstartingGattJtaSession +" + getstartingGattJtaSession
             + " getstartingGattJtaSession .isOpen() " + getstartingGattJtaSession.isOpen()+
                    " getstartingGattJtaSession .isOpen() " +getstartingGattJtaSession.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletGattServer.txt");
        }

    }



    public void getstartingJdbcRollbackTransaction(  @NotNull Session getstartingGattJtaSession) {

        try {
            if (getstartingGattJtaSession.isOpen()) {
                if (getstartingGattJtaSession.getTransaction().getStatus()!= TransactionStatus.ROLLED_BACK) {
                    getstartingGattJtaSession.getTransaction().rollback();
                }

            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "getstartingGattJtaSession " + getstartingGattJtaSession + " getstartingGattJtaSession .isOpen() " + getstartingGattJtaSession.isOpen()+
                    " getstartingGattJtaSessionisOpen() " +getstartingGattJtaSession.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletGattServer.txt");
        }

    }


    // TODO: 05.10.2024 end class
}
