package dowsloadpojboss;


import businesslogic.AsyncContextComplete;
import businesslogic.AyntificationDontPasswordAndLogin;
import businesslogic.CallBaksSend.SuccessSendAndroidBinatyStream;
import businesslogic.SubClassWriterErros;
import businesslogic.bl_sessionshibernate.GetSessionsHibernate;
import businesslogic.bl_sessionshibernate.GetSessionsHibernateGatt;

import businesslogic.bl_transactionHibernate.GetTransactionHibernate;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;


/**
 * Session Bean implementation class BeanCallsBackDownloadPO
 */
@Singleton(mappedName = "beanCallsBackDownloadPO")
@LocalBean
@Transactional
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanCallsBackDownloadPO   {

   @Inject
   private   SubClassWriterErros subClassWriterErros;





    @Inject
    private AsyncContextComplete asyncContextComplete;

    @Inject
    private  SuccessSendAndroidBinatyStream staringSendFileAndJsonAndroidOrBinatyStream;



    @Inject
    private AyntificationDontPasswordAndLogin ayntificationDontPasswordAndLogin;



    @Inject
    public GetTransactionHibernate getTransactionHibernate;



    public BeanCallsBackDownloadPO() {
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






    @Asynchronous
    public void МетодЗапускаОбновлениеПО(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        // TODO: 05.10.2024
        Session sessionUpdatePO=null;
        try {
            // TODO: 05.10.2024
            sessionUpdatePO= getTransactionHibernate.getstartingJdbcBeginTransaction();
            // TODO: 05.10.2024
            if (sessionUpdatePO.isOpen() && sessionUpdatePO.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                // TODO: 01.11.2023 Получаем Сессию
                Object ЗаданиеДляСервераЗагрузкиНовогоПо = request.getHeaders("task_downlonupdatepo").nextElement();
                // TODO: 01.11.2023 Получаем Сессию
            // TODO: 01.11.2023 Аунтификайия Имя И Пароль
            Boolean СтатусаАунтификацииПользователя =
                    ayntificationDontPasswordAndLogin.successAyntificationUserForServlets(  request, sessionUpdatePO, ЛОГ);


            if (СтатусаАунтификацииПользователя == true) {

                switch (ЗаданиеДляСервераЗагрузкиНовогоПо.toString()) {
                    case "FileJsonUpdatePO":
                        // TODO: 13.03.2023  запуск Кода пополучениею File JSON Для Обнолвенеи ПО
                        response.setContentType("application/json");
                        МетодЗапускаДляФайлаJSON(ЛОГ, request, response,sessionUpdatePO);
                        break;
                    case "FileAPKUpdatePO":
                        // TODO: 13.03.2023  запуск Кода пополучениею File .APK Для Обнолвенеи ПО
                        response.setContentType("application/octet-stream");
                        МетодЗапускаДляФайлаAPK(ЛОГ, request, response,sessionUpdatePO);
                        break;


                }

            }

            }

            // TODO: 05.10.2024
            getTransactionHibernate.getstartingJdbcCommitTransaction(sessionUpdatePO);
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) request).getPathInfo());
        } catch (Exception e) {
            // TODO: 02.11.2023 ROLLBACK
            // TODO: 17.11.2023 ERROR transaction
            getTransactionHibernate.getstartingJdbcRollbackTransaction(sessionUpdatePO);
// TODO: 17.11.2023 ERROR joiner
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

        }
    }



    private void МетодЗапускаДляФайлаJSON(@NotNull ServletContext ЛОГ,
                                                 @NotNull HttpServletRequest request,
                                                 @NotNull HttpServletResponse response,
                                             @NotNull Session session) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от JSON ANALIZE
          File ПолучаемJSONФайл= 	 МетодДляJSONФайла(ЛОГ,request,response);

            // TODO: 01.12.2023 Send File Cliknt

            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемJSONФайл,ЛОГ   );
            // TODO: 29.11.2023
            // TODO: 29.11.2023 закрывам Сесиою Бызова
            asyncContextComplete.endingContextComplete(request, ЛОГ);

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемJSONФайл " +ПолучаемJSONФайл);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
    }

    private  void  МетодЗапускаДляФайлаAPK(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull HttpServletResponse response,
                                         @NotNull Session session) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от .APK Download
           File ПолучаемAPKФайл= 	 МетодДляAPKФайла(ЛОГ,request,response);


            // TODO: 29.11.2023 ответ клиенту
            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемAPKФайл,ЛОГ );

            // TODO: 29.11.2023 закрывам Сесиою Бызова
            asyncContextComplete.endingContextComplete(request, ЛОГ);


            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемAPKФайл " +ПолучаемAPKФайл);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
    }


    private   File МетодДляJSONФайла(@NotNull ServletContext ЛОГ, @NotNull HttpServletRequest request,  @NotNull HttpServletResponse response){
        File fileJson = null;
        try{
            //String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\output-metadata.json";
            String filepath ="update_android_dsu1/output-metadata.json";
           // String filepath ="output-metadata.json";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileJson = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileJson " +fileJson);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
        return fileJson;
    }

    private File МетодДляAPKФайла(@NotNull ServletContext ЛОГ,
                                          @NotNull HttpServletRequest request,@NotNull HttpServletResponse response){
        File fileApk = null;
        try {
           // String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\app-release.apk";
           // String filepath ="src\\main\\webapp\\update_android_dsu1\\app-release.apk";
            String filepath ="update_android_dsu1/app-release.apk";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileApk = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileApk " +fileApk);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
        return fileApk;
    }




































    // TODO МетодКласса отправки данных андройду
    private void МетодBackДанныеКлиентуНовоеПО(@NotNull HttpServletResponse response,
                                              @NotNull File ОтправкаФайлаJsonAPK,
                                              @NotNull ServletContext ЛОГ) throws IOException, ServletException {


        try{
            // TODO: 01.12.2023 Запускам класс Отправки Данных НА пользователя File
            staringSendFileAndJsonAndroidOrBinatyStream.successSendAndroidFileStream( response, ОтправкаФайлаJsonAPK, ЛОГ);

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(),"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
    }
}



}




