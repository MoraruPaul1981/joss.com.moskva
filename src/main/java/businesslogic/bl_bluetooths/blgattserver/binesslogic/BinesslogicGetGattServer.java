package businesslogic.bl_bluetooths.blgattserver.binesslogic;


import businesslogic.GetParserDate.GetParsingDate;
import businesslogic.SetClosingIntutSteam;
import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blgattserver.interfaces.OperationsHIbernateGattGet;
import businesslogic.blgeneratorjackson.ProducedJacson;
import businesslogic.genertoringjson.GeneratorJsonWriteValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import io.reactivex.rxjava3.core.Completable;
import model.CompleteallmacadressusersEntity;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Named
public class BinesslogicGetGattServer  implements OperationsHIbernateGattGet {



    @Inject
    private SubClassWriterErros subClassWriterErros;

    @Inject
    GeneratorJsonWriteValue generatorJsonWriteValue;

    @Inject
    GetParsingDate getParsingDate;

    @Override
    public byte[] generatingJsontoSendtoGattClient(ServletContext ЛОГ, HttpServletRequest request, HttpServletResponse response, Session session) {
        // TODO: 16.10.2024
       AtomicReference<byte[]>  bufferJsontoSendtoGattClient = new AtomicReference<>(new byte[0]);
        try{
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::new).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("versionlocal")).stream().mapToLong(m->Long.parseLong(m)).findFirst().orElse(0l);
            /// TODO ПАРАМЕНТ #5
            String     Bremylocal = Optional.ofNullable(request.getParameter("bremylocal")).map(String::new).orElse("");


            Date getBremylocal =getParsingDate. getDateScanner(Bremylocal,ЛОГ);


            List<?>   ЛистДанныеОтHibenideGattServer=       getCompleteAllmacGattServer( ЛОГ, session, NameTable, VersionData,    getBremylocal) ;

            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " ЛистДанныеОтHibenideGattServer " +ЛистДанныеОтHibenideGattServer );



            // TODO: 16.10.2024
            bufferJsontoSendtoGattClient.set( generatorJsonFinal(ЛистДанныеОтHibenideGattServer,ЛОГ));



            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " bufferJsontoSendtoGattClient " +bufferJsontoSendtoGattClient.get() );


        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }

        return bufferJsontoSendtoGattClient.get();
    }







    @Override
    public List<?> getCompleteAllmacGattServer(ServletContext ЛОГ,
                                               Session session,
                                               String NameTable,
                                               Long VersionData,
                                               Date getBremylocal) {
        // TODO: 27.08.2024
        AtomicReference<List<?>> ЛистДанныеОтHibenideGattServer = new AtomicReference<>();
        Completable.fromAction(()->{

                    switch (NameTable.trim()) {
                        // TODO: 27.08.2024
                        case "completeallmacadressusers":
                            // TODO: 27.08.2024 САм запросс
                            TypedQuery<CompleteallmacadressusersEntity> queryДляHiberite =  session.createQuery(
                                    " SELECT   DISTINCT сompleteallmac FROM model.CompleteallmacadressusersEntity AS сompleteallmac   " +
                                            " WHERE  сompleteallmac.currentTable > :id " +
                                            "  AND сompleteallmac.dateUpdate > :getDatelocal " +
                                            " ORDER BY сompleteallmac.currentTable    "
                                    , CompleteallmacadressusersEntity.class) ;
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//
                            queryДляHiberite.setParameter("getDatelocal",  getBremylocal   );//TimestampType.INSTANCE
                            // TODO: 7.08.2024  Результат
                            ЛистДанныеОтHibenideGattServer.set(queryДляHiberite.setFirstResult(0).setMaxResults(1000).setLockMode(LockModeType.PESSIMISTIC_READ).getResultList());

                            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                    +"\n"+
                                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ЛистДанныеОтHibenideGattServer "
                                    + ЛистДанныеОтHibenideGattServer.get() );
                            break;

                        default:
                            ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
                            break;

                    }

                })
                .doOnError(e->{
                    Exception exception=new IOException(e);
                    subClassWriterErros.
                            writingCurrentErrors(exception,
                                    Thread.currentThread().
                                            getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");

                })
                .doOnComplete(()->{
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                })
                .blockingSubscribe();
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   );
//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (NameTable.trim()) {
        return  ЛистДанныеОтHibenideGattServer.get();
    }






    @Override
    public
        // TODO: 30.10.2023 метод изходя из теукущей таблицыв выбираем размер данных для генерации Jakson
    byte[] generatorJsonFinal(@NotNull List<?> ЛистДанныеОтHibenideGattServer, @NotNull ServletContext ЛОГ) {
        byte[] БуферCallsBackДляAndroid=null;
        try{
            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ  byte
            if (ЛистДанныеОтHibenideGattServer!=null) {
                if (ЛистДанныеОтHibenideGattServer.size()>0) {
                    БуферCallsBackДляAndroid=        generatorJsonWriteValue.getGeneratorJson(ЛистДанныеОтHibenideGattServer ,ЛОГ);
                }else {
                    БуферCallsBackДляAndroid=new byte[0];
                }
                // TODO: 30.10.2023 Когда нет данных
            }else {
                БуферCallsBackДляAndroid=new byte[0];
            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " БуферCallsBackДляAndroid " +БуферCallsBackДляAndroid);
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return  БуферCallsBackДляAndroid;
    }































    // TODO: 06.09.2024  END CLASS
}
