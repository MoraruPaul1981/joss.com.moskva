package businesslogic.bl_transactionHibernate;


import businesslogic.SubClassWriterErros;
import businesslogic.bl_sessionshibernate.GetSessionsHibernate;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class GetTransactionHibernate {
    private Logger LOGGER = LoggerFactory.getLogger(GetSessionsHibernate.class);
    @Inject
    SubClassWriterErros subClassWriterErros;


    @Inject
    GetSessionsHibernate getSessionsHibernate;



    public Session getstartingJdbcBeginTransaction( ) {
        Session getstartingSession=null;
        try {
            getstartingSession=  getSessionsHibernate.getstartingJdbcSession();

            if (getstartingSession.isOpen()) {
                if (getstartingSession.getTransaction().getStatus()!= TransactionStatus.ACTIVE) {
                    getstartingSession.getTransaction().begin();
                }

            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "session  " + getstartingSession + " getsessionHibernate.isOpen() " + getstartingSession.isOpen()+
                    " session.isOpen() " +getstartingSession.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
     return  getstartingSession;
    }
    public void getstartingJdbcCommitTransaction( @NotNull Session getstartingSession) {

        try {
            if (getstartingSession.isOpen()) {
                if (getstartingSession.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                    getstartingSession.getTransaction().commit();
                }

            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "getstartingSession  " + getstartingSession + " getstartingSession.isOpen() " + getstartingSession.isOpen()+
                    " getstartingSession.isOpen() " +getstartingSession.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }

    }



    public void getstartingJdbcRollbackTransaction(@NotNull Session getstartingSession ) {

        try {
            if (getstartingSession.isOpen()) {
                if (getstartingSession.getTransaction().getStatus()!= TransactionStatus.ROLLED_BACK) {
                    getstartingSession.getTransaction().rollback();
                }

            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "getstartingSession  " + getstartingSession + " getstartingSession.isOpen() " + getstartingSession.isOpen()+
                    " getstartingSession.isOpen() " +getstartingSession.isOpen());

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
