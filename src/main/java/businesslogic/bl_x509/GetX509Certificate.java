package businesslogic.bl_x509;



import businesslogic.SubClassWriterErros;
import businesslogic.bl_sessionshibernate.GetSessionsHibernateGatt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.ldap.LdapName;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.security.cert.X509Certificate;


@Named
public class GetX509Certificate {


    private Logger LOGGER = LoggerFactory.getLogger(GetSessionsHibernateGatt.class);
    @Inject
    SubClassWriterErros subClassWriterErros;


    public String getX509Certificate(ServletRequest request) throws IOException, ServletException {
        // TODO Auto-generated method stub
        String commonName = null;
        try {

            X509Certificate[] getx509CertificateAndroid=
                    (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");


            if (getx509CertificateAndroid!=null) {
                // TODO: 03.12.2024 состыковка Сертификатов
                commonName = new LdapName(getx509CertificateAndroid[0].getSubjectX500Principal().getName()).getRdns().stream()
                        .filter(i -> i.getType().equalsIgnoreCase("C")).findFirst().get().getValue().toString();
                // TODO: 02.11.2023 ЗАпускаем Код Фиильра
            }


            LOGGER.debug("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " Success    doFilter doFilter doFilter request.isSecure() "+ request.isSecure()+
                    "\n" + " getx509CertificateAndroid " +getx509CertificateAndroid  + "commonName  "+commonName);

        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletRuntime.txt");

        }
        return  commonName;
    }

}





