package Filters;

import businesslogic.SubClassWriterErros;
import businesslogic.bl_sessionfactory.InSessionFactoryGatt;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.naming.ldap.LdapName;
import javax.security.auth.x500.X500Principal;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.security.cert.X509Certificate;


@WebFilter(value={ "/sous.jboss.runtimejboss",
        "/sous.jboss.download"
        ,"/sous.jboss.scanner"
        ,"/sous.jboss.gattserver"},
        filterName="FilterRuntime",asyncSupported = true)
public class FilterRuntime implements Filter {


    //TODO commit 19.02.20424----10.32
    //TODO commit 19.02.20424----10.35

    private ServletContext ЛОГ;
    @Inject
    private  SubClassWriterErros subClassWriterErros;
    @Inject
    private BusinessLogicFilterRuntime businessLogicFilterRuntime;


    @Inject
    @InSessionFactoryGatt
    SessionFactory getsessionHibernateGatt;



//TODO фильтр commit 19.02.2024--10.26

    public void init(FilterConfig fConfig) throws ServletException {
        ЛОГ = fConfig.getServletContext();
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                " getsessionHibernateGatt " +getsessionHibernateGatt.getStatistics());
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        try {
            X509Certificate[] getx509CertificateAndroid=
                    (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");

           // TODO: 03.12.2024 состыковка Сертификатов
            String commonName = new LdapName(getx509CertificateAndroid[0].getSubjectX500Principal().getName()).getRdns().stream()
                    .filter(i -> i.getType().equalsIgnoreCase("C")).findFirst().get().getValue().toString();

            // TODO: 02.11.2023 ЗАпускаем Код Фиильра
            businessLogicFilterRuntime.startFilterRuntime(request,response,chain,ЛОГ);


            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
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
    }





    public void destroy() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        System.out.println(	"\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }


    // TODO: 28.11.2023 end filter
}
