package dowsloadpojboss;


import businesslogic.*;
import businesslogic.bl_sessionshibernate.GetSessionsHibernateGatt;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.download",asyncSupported = true)
public class DSU1DonwloadsServlet extends HttpServlet {


    @Inject
    private  SubClassWriterErros subClassWriterErros;
    @EJB
    private BeanCallsBackDownloadPO beanCallsBackDownloadPO;
    private  ServletContext   ЛОГ;








    DSU1DonwloadsServlet(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
           ЛОГ = getServletContext();
        // TODO: 23.12.2023
                try {

                    // TODO: 22.05.2023 lister asynccontext
                            // TODO: 24.07.2023 запуск обновение ПО
                            beanCallsBackDownloadPO.МетодЗапускаОбновлениеПО(ЛОГ, (HttpServletRequest) req, (HttpServletResponse) resp);

                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo());
                } catch (Exception e) {
                    // TODO: 02.11.2023 запись ошибку
                    subClassWriterErros.
                            writingCurrentErrors(e,
                                    Thread.currentThread().
                                            getStackTrace(), "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

                }

    }



    public void destroy() {
      try{
          System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
          subClassWriterErros.
                  writingCurrentErrors(e,
                          Thread.currentThread().
                                  getStackTrace(),"ErrorsLogs/ErrorJbossServletUpdatePO.txt");

    }

    }
}

































