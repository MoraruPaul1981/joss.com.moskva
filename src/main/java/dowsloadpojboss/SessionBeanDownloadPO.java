package dowsloadpojboss;




import com.sun.istack.NotNull;
import dsu1glassfishatomic.SubClassWriterErros;

import javax.ejb.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Session Bean implementation class SessionBeanDownloadPO
 */
@Stateless(mappedName = "SessionBeanDownloadPO")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SessionBeanDownloadPO {
    public SessionBeanDownloadPO() {
        // TODO Auto-generated constructor stub
        try {
            System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.print("\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+e.getMessage().toString());
        }
    }

    public void  МетодЗапускаДляФайлаJSON(@NotNull ServletContext ЛОГ,
                                          @NotNull HttpServletRequest request,
                                          @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от JSON ANALIZE
            Future<File> ПолучаемJSONФайл= 	 МетодДляJSONФайла(ЛОГ,request,response);
            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемJSONФайл.get(),ЛОГ);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемJSONФайл " +ПолучаемJSONФайл);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }

    public void  МетодЗапускаДляФайлаAPK(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от .APK Download
            Future<File> ПолучаемAPKФайл= 	 МетодДляAPKФайла(ЛОГ,request,response);
            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемAPKФайл.get(),ЛОГ);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемAPKФайл " +ПолучаемAPKФайл);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }

    @Asynchronous
    private Future<File> МетодДляJSONФайла(@NotNull ServletContext ЛОГ, @NotNull HttpServletRequest request,  @NotNull HttpServletResponse response){
        File fileJson = null;
        try{
            String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\output-metadata.json";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileJson = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileJson " +fileJson);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return new AsyncResult<File>(fileJson);
    }
    @Asynchronous
    private Future<File> МетодДляAPKФайла(@NotNull ServletContext ЛОГ,
                                          @NotNull HttpServletRequest request,@NotNull HttpServletResponse response){
        File fileApk = null;
        try {
            String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\app-release.apk";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileApk = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileApk " +fileApk);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return new AsyncResult<File>(fileApk);
    }




































    // TODO МетодКласса отправки данных андройду
    public void МетодBackДанныеКлиентуНовоеПО(@NotNull ServletResponse response,
                                              @NotNull File ОтправкаФайлаJsonAPK,
                                              @NotNull ServletContext ЛОГ) throws IOException, ServletException {

        if (  response.isCommitted()==false) {
            try  (ServletOutputStream БуферДанныеДляОбновлениеПО = response.getOutputStream();
                  InputStream fis = new FileInputStream(ОтправкаФайлаJsonAPK);) {
                ((HttpServletResponse) response).addHeader("stream_size", String.valueOf(ОтправкаФайлаJsonAPK.length()));
                byte[] bufferData = new byte[1024];
                int read=0;
                while((read = fis.read(bufferData))!= -1){
                    БуферДанныеДляОбновлениеПО.write(bufferData, 0, read);
                }
                БуферДанныеДляОбновлениеПО.flush();
                response.flushBuffer();
                while (!response.isCommitted()) ;
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ОтправкаФайлаJsonAPK " + ОтправкаФайлаJsonAPK + "  response.isCommitted() "
                        + response.isCommitted() + "   ((HttpServletResponse) response).getStatus() " +
                        ((HttpServletResponse) response).getStatus());

            } catch (IOException e) {
                new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛОГ.getServerInfo(),
                        this.getClass().getMethods().toString() + " " + this.getClass().getCanonicalName().toString() + " "
                                + this.getClass().getDeclaredMethods().toString(),
                        Thread.currentThread().getStackTrace()[2], ЛОГ, ОтправкаФайлаJsonAPK.toString());
            }
        }else {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "  ОтправкаФайлаJsonAPK.length() " + ОтправкаФайлаJsonAPK.length() + "  response.isCommitted() " + response.isCommitted()
                    + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus());
        }
    }

}

