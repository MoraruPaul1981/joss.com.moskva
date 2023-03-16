package dowsloadpojboss;


import dsu1glassfishatomic.SubClassWriterErros;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet( value="/sous.jboss.download",asyncSupported = true)
public class DSU1DonwloadsServlet extends HttpServlet {
    private      ServletContext    ЛОГ;
    @EJB
    SessionBeanDownloadPO sessionBeanDownloadPO;

    DSU1DonwloadsServlet(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
        try{
          req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
          resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           ЛОГ = getServletContext();
            Object ЗаданиеДляСервераЗагрузкиНовогоПо=      ((HttpServletRequest)req).getHeaders("task_downlonupdatepo").nextElement();
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    "  ЛогинОтAndroid    ЗаданиеДляСервераЗагрузкиНовогоПо " +ЗаданиеДляСервераЗагрузкиНовогоПо);
            switch (ЗаданиеДляСервераЗагрузкиНовогоПо.toString()){
                case "FileJsonUpdatePO"  :
                    // TODO: 13.03.2023  запуск Кода пополучениею File JSON Для Обнолвенеи ПО
                    sessionBeanDownloadPO.   МетодЗапускаДляФайлаJSON(ЛОГ,req,resp);
                    break;
                case "FileAPKUpdatePO" :
                    // TODO: 13.03.2023  запуск Кода пополучениею File .APK Для Обнолвенеи ПО
                    sessionBeanDownloadPO.   МетодЗапускаДляФайлаAPK(ЛОГ,req,resp);
                    break;
            }
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " +((HttpServletRequest) req).getPathInfo());
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());

        }

    }



    public void destroy() {
      try{
          ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());

    }

    }
}

































