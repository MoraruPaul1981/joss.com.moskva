package businesslogic.bl_bluetooths.blgattserver.operations;


import businesslogic.SubClassWriterErros;
import businesslogic.bl_bluetooths.blgattserver.interfaces.OperationsHIbernateGattPost;
import com.sun.istack.NotNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import model.GattserversuccessEntity;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Named
public class OperationsHIbernateGattPostServerNative implements OperationsHIbernateGattPost {

// TODO: 07.09.2024
@Inject
private SubClassWriterErros subClassWriterErros;


@Override
  public Long persistEntityGattServer(@NotNull List<GattserversuccessEntity> getlistGattServerwtirer,
                                @NotNull Session session,
                                @NotNull ServletContext ЛОГ) {
       // TODO: 06.09.2024
    AtomicReference<CopyOnWriteArrayList<Long>>atomicReferenceResultGatUpdateorInsert=new AtomicReference<>( );
       try{


           Flowable.fromIterable(getlistGattServerwtirer)
                   .onBackpressureBuffer()
                   .blockingForEach(new Consumer<Object>() {
               @Override
               public void accept(Object o) throws Throwable {

                   // TODO: 08.09.2024
                   GattserversuccessEntity gattserversuccessEntity= (GattserversuccessEntity) o;
                   if (gattserversuccessEntity!=null) {

                      // Boolean resuktSuccesFindUUID=  getQuertyGattServerUUD(session,ЛОГ,gattserversuccessEntity.getUuid());
                       Boolean resuktSuccesFindUUID=  false;

                       // TODO: 08.09.2024 inserting  gattserver
                       if (resuktSuccesFindUUID==false) {
                           // TODO: 08.09.2024 inserting  gattserver
                           Integer  insertingGatt=       insertingGattServer(    gattserversuccessEntity, session, ЛОГ);
                           // TODO: 08.09.2024 updatting  gattserver
                           ///Integer updateting=  updatetingGattServer(getlistGattServerwtirer, session, ЛОГ);
                           if(insertingGatt>0 ) {
                               // TODO: 08.09.2024 send Version
                               CopyOnWriteArrayList copyOnWriteArrayListUpdateInsert=new CopyOnWriteArrayList<>();
                               copyOnWriteArrayListUpdateInsert.add(getQuertyGattServerMaxVesrion(session, ЛОГ));
                               atomicReferenceResultGatUpdateorInsert.set(copyOnWriteArrayListUpdateInsert);
                               // TODO: 05.10.2024
                           }
                           ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                   +"\n"+
                                   " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                   + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +"\n"+
                                   " insertingGatt " +insertingGatt
                                   +"\n"+ " atomicReferenceResultGatUpdateorInsert " +atomicReferenceResultGatUpdateorInsert);
                       }
                   }

                   ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                           +"\n"+
                           " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                           + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +"\n"
                           +"\n"+ " atomicReferenceResultGatUpdateorInsert " +atomicReferenceResultGatUpdateorInsert);
               }
           });

           // TODO: 08.09.2024

           ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
               +"\n"+
               " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
               + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                   +  " atomicReferenceResultGatUpdateorInsert.get()  " +atomicReferenceResultGatUpdateorInsert.get() );

   } catch (Exception e) {
        // TODO: 17.11.2023 ERROR transaction
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
       return   atomicReferenceResultGatUpdateorInsert.get().stream().mapToLong(Long::longValue).max().getAsLong();
   }






    // TODO: 08.09.2024   Insert
    @Override
    public Integer insertingGattServer(@NotNull   GattserversuccessEntity gattserversuccessEntity,
                                        @NotNull Session session,
                                        @NotNull ServletContext ЛОГ) {
        // TODO: 06.09.2024
        AtomicReference<Integer> resultGattServer=new AtomicReference<>();
        try{
                if (gattserversuccessEntity!=null) {
                    ЛОГ.log(" Класс" + Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " метод " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            "  session.getTransaction() " + session.getTransaction().getStatus());
                    Query gattserverInsert = session.createNativeQuery("INSERT INTO scannerserversuccess   (operations,completedwork," +
                            "namedevice,macdevice,gps1,gps2,fio,adress,city,date_update,uuid  , version,sim,iemi,current_table,getstatusrow  ) " +
                            " VALUES (?,?,?,? ,?,?,?,?,?,?,?  , ?,?,?,? ,?)    ");
                            gattserverInsert.setParameter(1, gattserversuccessEntity.getOperations())
                            .setParameter(2, gattserversuccessEntity.getCompletedwork())
                            .setParameter(3, gattserversuccessEntity.getNamedevice())
                            .setParameter(4, gattserversuccessEntity.getMacdevice())
                            .setParameter(5, gattserversuccessEntity.getGps1())
                            .setParameter(6, gattserversuccessEntity.getGps2())
                            .setParameter(7, gattserversuccessEntity.getFio())
                            .setParameter(8, gattserversuccessEntity.getAdress())
                            .setParameter(9, gattserversuccessEntity.getCity())
                            .setParameter(10, gattserversuccessEntity.getDateUpdate())
                            .setParameter(11, gattserversuccessEntity.getUuid().longValue())
                            .setParameter(12, gattserversuccessEntity.getVersion())
                            .setParameter(13, gattserversuccessEntity.getSim())
                            .setParameter(14, gattserversuccessEntity.getIemi())
                            .setParameter(15, gattserversuccessEntity.getCurrentTable().longValue())
                            .setParameter(16, gattserversuccessEntity.getGetstatusrow());
                    // TODO: 08.09.2024 result get gattserver
                    resultGattServer.set(gattserverInsert.executeUpdate());
                    // TODO: 08.09.2024
                    // TODO: 08.09.2024
                    ЛОГ.log(" Класс" + Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " метод " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " resultGattServer.get() " + resultGattServer.get());
                }

            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return resultGattServer.get();

    }


    // TODO: 08.09.2024  update gatt server
    @Override
    public Integer updatetingGattServer(@NotNull GattserversuccessEntity gattserversuccessEntity,
                                       @NotNull Session session,
                                       @NotNull ServletContext ЛОГ) {
        // TODO: 06.09.2024
        AtomicReference<Integer> resultGattServer=new AtomicReference<>();
        try{

                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            "  session.getTransaction() " + session.getTransaction().getStatus());

                    Query gattserverUpdate = session.createNativeQuery(" UPDATE  scannerserversuccess  SET " +
                            " operations=:operations "+
                            " ,completedwork=:completedwork ,namedevice=:namedevice ,macdevice=:macdevice,gps1=:gps1 ,gps2=:gps2 " +
                            " ,getstatusrow=:getstatusrow ,adress=:adress ,city=:city  ,date_update=:date_update ,uuid=:uuid  " +
                            ", version=:version,sim=:sim ,iemi=:iemi ,current_table=:current_table "+
                            " WHERE current_table=:wherecurrent_table  ");
                    gattserverUpdate.setParameter("operations", gattserversuccessEntity.getOperations())
                            .setParameter("completedwork", gattserversuccessEntity.getCompletedwork())
                            .setParameter("namedevice", gattserversuccessEntity.getNamedevice())
                            .setParameter("macdevice", gattserversuccessEntity.getMacdevice())

                            .setParameter("gps1", gattserversuccessEntity.getGps1())
                            .setParameter("gps2", gattserversuccessEntity.getGps2())
                            .setParameter("getstatusrow", gattserversuccessEntity.getGetstatusrow())
                            .setParameter("adress", gattserversuccessEntity.getAdress())
                            .setParameter("city", gattserversuccessEntity.getCity())
                            .setParameter("date_update", gattserversuccessEntity.getDateUpdate())
                            .setParameter("uuid", gattserversuccessEntity.getUuid())
                            .setParameter("version", gattserversuccessEntity.getVersion())
                            .setParameter("sim", gattserversuccessEntity.getSim())
                            .setParameter("iemi",gattserversuccessEntity.getIemi())
                            .setParameter("current_table", gattserversuccessEntity.getCurrentTable())
                            .setParameter("wherecurrent_table", gattserversuccessEntity.getUuid());
                    // TODO: 08.09.2024 result get gattserver
                    resultGattServer.set(gattserverUpdate.executeUpdate());
                    // TODO: 08.09.2024
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return resultGattServer.get();

    }
// TODO: 08.09.2024

    @Override
    public Long getQuertyGattServerUUD(@NotNull Session session,
                                        @NotNull ServletContext ЛОГ,@NotNull BigDecimal getUUID) {
        // TODO: 06.09.2024
        AtomicReference<Boolean> resultUUIDorVesrion=new AtomicReference<>(false);
        try{
                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            "  session.getTransaction() " + session.getTransaction().getStatus());


            Query  queryUUD =  session.createNativeQuery(
                    " SELECT   *  FROM dbo.scannerserversuccess AS gatt  WHERE gatt.uuid = :lc ").setParameter("lc", getUUID.longValue());;

            Object listFindUUID =   queryUUD.uniqueResultOptional().orElseThrow();

            if (listFindUUID!=null) {
                // TODO: 08.09.2024 result get gattserver
               resultUUIDorVesrion.set(true);
            }
                    // TODO: 08.09.2024
                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" resultUUIDorVesrion.get() "+resultUUIDorVesrion.get()+
                            " listFindUUID "+listFindUUID);
        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return 0l;

    }


    public Long getQuertyGattServerMaxVesrion(@NotNull Session session, @NotNull ServletContext ЛОГ) {
        // TODO: 06.09.2024
        AtomicReference<Long> resultMaxVesrion=new AtomicReference<>();
        try{
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                    "  session.getTransaction() " + session.getTransaction().getStatus());

            // TODO: 30.09.2024  получаем максимальную версию данных
            BigDecimal MaxVesrionUUID =         session.createQuery(
                            "SELECT MAX (gatt.currentTable)   FROM model.GattserversuccessEntity AS  gatt ",BigDecimal.class).setLockOptions(LockOptions.UPGRADE).getSingleResult();

            resultMaxVesrion.set(MaxVesrionUUID.longValue());

            // TODO: 08.09.2024
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                    " MaxVesrionUUID "+MaxVesrionUUID+ " resultMaxVesrion.get() " +resultMaxVesrion.get());
        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return resultMaxVesrion.get();

    }



}
