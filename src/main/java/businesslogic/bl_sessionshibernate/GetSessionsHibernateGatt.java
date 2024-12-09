package businesslogic.bl_sessionshibernate;

import businesslogic.SubClassWriterErros;
import businesslogic.bl_sessionfactory.InSessionFactoryGatt;
import businesslogic.bl_sessionshibernate.intarfaces.*;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


@Named
@RequestScoped
public class GetSessionsHibernateGatt implements SessinsHiberIGattnterface {
    private Logger LOGGER = LoggerFactory.getLogger(GetSessionsHibernateGatt.class);
    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject
    @InSessionFactoryGatt
     SessionFactory getsessionHibernateGatt;


    @Override
    public Session getstartingGattJtaSession( ) {
        Session session = null;
        try {
            if (getsessionHibernateGatt.isOpen()) {
                session = getsessionHibernateGatt.getCurrentSession();
            }

            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "session  " + session + " getsessionHibernateGatt.isOpen() " + getsessionHibernateGatt.isOpen()+
                    " session.isOpen() " +session.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletGattServer.txt");
        }
        return session;
    }


    @Override
    public Session getstartingGattJdbcSession() {
        Session session = null;
        try {
            if (getsessionHibernateGatt.isOpen()) {

                session= getstartingGattJtaSession( );

                if (session.getTransaction().getStatus()!= TransactionStatus.ACTIVE) {

                    session = getsessionHibernateGatt.openSession();
                }
            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "session  " + session + " getsessionHibernateGatt.isOpen() " + getsessionHibernateGatt.isOpen()+
                    " session.isOpen() " +session.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletGattServer.txt");
        }
        return session;
    }


    //TODO end class
}
