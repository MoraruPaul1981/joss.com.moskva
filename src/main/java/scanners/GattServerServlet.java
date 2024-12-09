package scanners;


import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blgattserver.beans.BeanGetGattServer;
import businesslogic.bl_bluetooths.blgattserver.beans.BeanPostGattServer;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.gattserver",asyncSupported = true)
public class GattServerServlet extends HttpServlet {
    private      ServletContext    ЛОГ;

    @Inject
    SubClassWriterErros subClassWriterErros;


    @Inject
    BeanPostGattServer beanPostGattServer;

    @Inject
    BeanGetGattServer beanGetGattServer;
    GattServerServlet() {
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //  super.doGet(req, resp);
        ЛОГ = getServletContext();
        //TODO ПОТОК ДЛЯ МЕТОДА POST
        try {
            // TODO: 01.11.2023 Получаем Сессию
            // TODO: 27.08.2024   данные получаем от Клиента Android
            beanGetGattServer.staringGetGattServer(ЛОГ,req,resp);


            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ((HttpServletRequest) req).getPathInfo() +
                    " POOL CURRENT  " + Thread.currentThread().getName() + " req.isAsyncStarted() " + req.isAsyncStarted());
        } catch (Exception e) {
            // TODO: 08.02.2024  rollback
            // TODO: 02.11.2023 запись ошибку
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletGattServer.txt");

        }


    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        ЛОГ = getServletContext();
                //TODO ПОТОК ДЛЯ МЕТОДА POST
                try {

                        // TODO: 01.11.2023 Получаем Сессию
                        // TODO: 27.08.2024   данные получаем от Клиента Android
                        beanPostGattServer.staringPostGattServer(ЛОГ,req,resp);


                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo());


                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ((HttpServletRequest) req).getPathInfo() +
                            " POOL CURRENT  " + Thread.currentThread().getName() + " req.isAsyncStarted() " + req.isAsyncStarted());
                } catch (Exception e) {
                    // TODO: 08.02.2024  rollback
                    // TODO: 02.11.2023 запись ошибку
                    subClassWriterErros.
                            writingCurrentErrors(e,
                                    Thread.currentThread().
                                            getStackTrace(), "ErrorsLogs/ErrorJbossServletGattServer.txt");

                }


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
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletGattServer.txt");

        }

    }
}

































