package dsu1glassfishatomic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

// TODO: 09.03.2023  Класс Получение Менеджера для Hibertire



public class MyGetHibernate{
    @Produces @ProducedCard @ApplicationScoped
    public SessionFactory sessionSousJboss() {
        Configuration configuration = null;
        try {
            configuration = new Configuration();
            InetAddress ip = InetAddress.getLocalHost();
            String Name = ip.getHostName();
            switch (Name.trim()) {
                case "PcProgram":
                    configuration.configure("hibernate.cfg.xml");
                    break;
                default:
                    configuration.configure("hibernate_r.cfg.xml");
                    break;
            }
            System.out.println("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " configuration "
                    + configuration + " время" + new Date().toLocaleString() + " Name " + Name);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n",
                    Thread.currentThread().getStackTrace()[2], null, null);
        }
        return configuration.buildSessionFactory();
    }
    }

