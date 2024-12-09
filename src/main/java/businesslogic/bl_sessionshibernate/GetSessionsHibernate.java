package businesslogic.bl_sessionshibernate;

import businesslogic.SubClassWriterErros;
import businesslogic.bl_sessionfactory.InSessionFactory;
import businesslogic.bl_sessionshibernate.intarfaces.GetSessionJdbcIntarface;
import businesslogic.bl_sessionshibernate.intarfaces.SessinsHiberInterface;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


@Named
@RequestScoped
public class GetSessionsHibernate implements SessinsHiberInterface {




    private Logger LOGGER = LoggerFactory.getLogger(GetSessionsHibernate.class);
    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject
    @InSessionFactory
     SessionFactory getsessionHibernate;


    @Override
    public Session getstartingJtaSession( ) {
        Session session = null;
        try {
            if (getsessionHibernate.isOpen()) {
                session = getsessionHibernate.getCurrentSession();
            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "session  " + session + " getsessionHibernate.isOpen() " + getsessionHibernate.isOpen()+
                    " session.isOpen() " +session.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return session;
    }


    @Override
    @Produces
    @GetSessionJdbcIntarface
    public Session getstartingJdbcSession() {
        Session session = null;
        try {
            if (getsessionHibernate.isOpen()) {

                session= getstartingJtaSession( );

                if (session.getTransaction().getStatus()!= TransactionStatus.ACTIVE) {

                    session = getsessionHibernate.openSession();
                }
            }
            LOGGER.debug("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n"
                    + "session  " + session + " getsessionHibernateGatt.isOpen() " + getsessionHibernate.isOpen()+
                    " session.isOpen() " +session.isOpen());

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return session;
    }

    //TODO end class
}
