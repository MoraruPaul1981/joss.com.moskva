package businesslogic.bl_bluetooths.blgattserver.binesslogic;


import businesslogic.SetClosingIntutSteam;
import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blgattserver.operations.OperationsHIbernateGattPostServersaveOrUpdate;
import businesslogic.blgeneratorjackson.ProducedJacson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import model.GattserversuccessEntity;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;

@Named
public class BinesslogicPostGattServer {



    @Inject
    private SubClassWriterErros subClassWriterErros;


    @Inject
    @ProducedJacson
    private ObjectMapper getGeneratorJackson;
    @Inject
    SetClosingIntutSteam setClosingIntutSteam;

  /*  @Inject
    OperationsHIbernateGattPostServerNative operationsHIbernateGattServerNative;*/

    @Inject
    OperationsHIbernateGattPostServersaveOrUpdate operationsHIbernateGattServersaveOrUpdate;


    public  byte[]  proccestingbinesslogicGattServer (@NotNull ServletContext ЛОГ,
                                                           @NotNull HttpServletRequest request,
                                                           @NotNull HttpServletResponse response,
                                                           @NotNull Session session ) {
// TODO: 06.09.2024
        byte[] БуферРезультатPOSTGattServer = new byte[0];
try{

    String   NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
    // TODO: 06.09.2024
            // TODO: 06.09.2024
            GZIPInputStream requestInputStream =       new GZIPInputStream(request.getInputStream() );
            InputStream inputStream=    new ByteArrayInputStream(requestInputStream.readAllBytes());
            if (inputStream.available()>0) {

                БуферРезультатPOSTGattServer =    writeringJaksonJsonOtClentGATT(  inputStream,session,ЛОГ );

                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
            }


        ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                +"\n"+
                " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " БуферРезультатPOSTGattServer " +БуферРезультатPOSTGattServer );

    } catch (Exception e) {
        // TODO: 17.11.2023 ERROR transaction
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }

        return  БуферРезультатPOSTGattServer;
    }








// TODO: 06.09.2024  

   byte[] writeringJaksonJsonOtClentGATT(@NotNull InputStream requestInputStream,
                                         @NotNull Session session,
                                         @NotNull ServletContext ЛОГ) {
       // TODO: 06.09.2024
       AtomicReference<byte[]>   geversionAnSqlServer=new AtomicReference();

       // TODO: 06.09.2024  непосредственого Парсинга от Андройда
       try {
           // TODO: 07.09.2024 Данные от Клиента для Вствки на Сервер  Сканер
            CopyOnWriteArrayList<GattserversuccessEntity> getlistGattServerwtirer= getGeneratorJackson.readValue(requestInputStream,
                    new TypeReference<CopyOnWriteArrayList<GattserversuccessEntity>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
           // TODO: 07.09.2024 Код по вставке Новых ДАнных
           Long resultMaxVesrion =    operationsHIbernateGattServersaveOrUpdate.persistEntityGattServer(getlistGattServerwtirer,session,ЛОГ);


           // TODO: 06.09.2024  ЗАКРЫВАЕМ ПОТОК
           setClosingIntutSteam.   clossingImputSreatm(requestInputStream,ЛОГ);

           // TODO: 06.09.2024 FINAL VERSION CAll BACK CLIENT Android SCANNER
           geversionAnSqlServer.set(resultLastVersionAnSqlServer(  resultMaxVesrion  ,ЛОГ ));    ;

           ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                   +"\n"+
                   " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                   + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +
                   "  geversionAnSqlServer " +geversionAnSqlServer);

       } catch (IOException e) {
           // TODO: 17.11.2023 ERROR transaction
           subClassWriterErros.
                   writingCurrentErrors(e,
                           Thread.currentThread().
                                   getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
       }
       // TODO: 06.09.2024  
        return geversionAnSqlServer.get()  ;
   }







    private byte[] resultLastVersionAnSqlServer( @NotNull  Long resultMaxVesrion , @NotNull ServletContext ЛОГ) {
        byte[] bufferCallsBack= new byte[0];
        try{
            if (resultMaxVesrion!=null && resultMaxVesrion>0 ) {
                bufferCallsBack   =resultMaxVesrion.toString().getBytes(StandardCharsets.UTF_8);
            } else {
                bufferCallsBack   =new StringBuffer().toString().getBytes(StandardCharsets.UTF_8);
            }
            // TODO: 06.09.2024  
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception   e) {
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.writingCurrentErrors(e,
                    Thread.currentThread().
                            getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return  bufferCallsBack;
    }






















    // TODO: 06.09.2024  END CLASS 
}
