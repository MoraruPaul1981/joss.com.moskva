package scanners;


import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blscanners.beans.BeanScanner;


import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.scanner",asyncSupported = true)
public class ScannerServlet extends HttpServlet {
    private      ServletContext    ЛОГ;
    @Inject
    SubClassWriterErros subClassWriterErros;






    @EJB
    private BeanScanner beanScanner;

    ScannerServlet() {
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        ЛОГ = getServletContext();
                try {
                        // TODO: 01.11.2023 Получаем Сессию
                         //transationSession.startingTransastion( ЛОГ,getstartingSession);

                        // TODO: 27.08.2024   данные получаем для Сканера BLE
                    beanScanner.staringScannerBLE(ЛОГ,req,resp);

                        
                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo());
                } catch (Exception e) {
                    // TODO: 08.02.2024  rollback
                   /// transationSession.erroringTransastion(ЛОГ,getstartingSession);
                    // TODO: 02.11.2023 запись ошибку
                    subClassWriterErros.
                            writingCurrentErrors(e,
                                    Thread.currentThread().
                                            getStackTrace(), "ErrorsLogs/ErrorJbossServletScanner.txt");

                }




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void destroy() {
        try{
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletScanner.txt");

        }

    }
}

































