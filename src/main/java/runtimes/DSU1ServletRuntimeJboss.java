package runtimes;


import businesslogic.SubClassWriterErros;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.runtimejboss",asyncSupported = true)
public class DSU1ServletRuntimeJboss extends HttpServlet {
    @EJB
    private SessionBeanGETRuntimeJboss sessionBeanGETRuntimeJboss;
    @Inject
    private SubClassWriterErros subClassWriterErros;



    private  ServletContext   ЛОГ;









    DSU1ServletRuntimeJboss(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
           ЛОГ = getServletContext();
                // TODO: 29.11.2023 async
                try {
                            //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                            sessionBeanGETRuntimeJboss.МетодГлавныйRuntimeJboss(ЛОГ, req,  resp );


                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo()
                            + "  POOL  THREAD " + Thread.currentThread().getName());
                    // TODO: 23.04.2023 clears Async
                } catch (Exception e) {
                    // TODO: 02.11.2023 запись ошибку
                    subClassWriterErros.
                            writingCurrentErrors(e,
                                    Thread.currentThread().
                                            getStackTrace(), "ErrorsLogs/ErrorJbossServletRuntime.txt");
                }





        }

                // TODO: 23.04.2023 exit



    public void destroy() {
      try{
          System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
          subClassWriterErros.
                  writingCurrentErrors(e,
                          Thread.currentThread().
                                  getStackTrace(),"ErrorsLogs/ErrorJbossServletRuntime.txt");

    }

    }
}

































