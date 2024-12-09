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
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Named
public class OperationsHIbernateGattPostServersaveOrUpdate implements OperationsHIbernateGattPost {

// TODO: 07.09.2024
@Inject
private SubClassWriterErros subClassWriterErros;





@Override
public Long persistEntityGattServer(@NotNull List<GattserversuccessEntity> getlistGattServerwtirer,
                                    @NotNull Session session,
                                    @NotNull ServletContext ЛОГ) {
    // TODO: 06.09.2024
   CopyOnWriteArrayList<Long>copyOnWriteArrayListUpdateOrInsertComplete=new CopyOnWriteArrayList<>( );
    try{


        Flowable.fromIterable(getlistGattServerwtirer)
                .onBackpressureBuffer()
                .blockingForEach(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {

                        // TODO: 08.09.2024
                        GattserversuccessEntity gattserversuccessEntity= (GattserversuccessEntity) o;
                        if (gattserversuccessEntity!=null) {

                           Long resuktSuccesFindUUID=  getQuertyGattServerUUD(session,ЛОГ,gattserversuccessEntity.getUuid());

                            // TODO: 08.09.2024 inserting  gattserver
                            if (resuktSuccesFindUUID==0) {
                                // TODO: 08.09.2024 inserting  gattserver
                                Integer  insertingGatt=       insertingGattServer(    gattserversuccessEntity, session, ЛОГ);
                                // TODO: 08.09.2024 updatting  gattserver
                                ///Integer updateting=  updatetingGattServer(getlistGattServerwtirer, session, ЛОГ);
                                if(insertingGatt>0 ) {
                                    // TODO: 08.09.2024 send Version
                                    copyOnWriteArrayListUpdateOrInsertComplete.add(getQuertyGattServerMaxVesrion(session, ЛОГ));
                                    // TODO: 05.10.2024
                                }
                                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                        +"\n"+
                                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +"\n"+
                                        " insertingGatt " +insertingGatt
                                        +"\n"+ " copyOnWriteArrayListUpdateOrInsertComplete " +copyOnWriteArrayListUpdateOrInsertComplete);
                            }else {
                                // TODO: 05.10.2024 UPDATE
                                // TODO: 08.09.2024 updatting  gattserver
                              Integer updateting=  updatetingGattServer(gattserversuccessEntity, session, ЛОГ);

                                if(updateting>0 ) {
                                    // TODO: 08.09.2024 send Version
                                    // TODO: 08.09.2024 send Version
                                    copyOnWriteArrayListUpdateOrInsertComplete.add(getQuertyGattServerMaxVesrion(session, ЛОГ));
                                    // TODO: 05.10.2024
                                }

                                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                        +"\n"+
                                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +"\n"+
                                        " updateting " +updateting
                                        +"\n"+ " copyOnWriteArrayListUpdateOrInsertComplete " +copyOnWriteArrayListUpdateOrInsertComplete);

                            }
                        }

                        ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                                +"\n"+
                                " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                                + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +"\n"
                                +"\n"+ " copyOnWriteArrayListUpdateOrInsertComplete " +copyOnWriteArrayListUpdateOrInsertComplete);
                    }
                });

        // TODO: 08.09.2024

        ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                +"\n"+
                " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                +  " copyOnWriteArrayListUpdateOrInsertComplete.get()  " +copyOnWriteArrayListUpdateOrInsertComplete.size() );

    } catch (Exception e) {
        // TODO: 17.11.2023 ERROR transaction
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    return   copyOnWriteArrayListUpdateOrInsertComplete.stream().mapToLong(Long::longValue).max().getAsLong();
}






    // TODO: 08.09.2024   Insert
    @Override
    public Integer insertingGattServer(@NotNull   GattserversuccessEntity gattserversuccessEntity,
                                        @NotNull Session session,
                                        @NotNull ServletContext ЛОГ) {
        // TODO: 06.09.2024
       Integer  resultGattServer=0;
        try{
                if (gattserversuccessEntity!=null) {
                    ЛОГ.log(" Класс" + Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " метод " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            "  session.getTransaction() " + session.getTransaction().getStatus());

              model.GattserversuccessEntity gattServersuccessPersisit=new GattserversuccessEntity();

                    gattServersuccessPersisit.setOperations( gattserversuccessEntity.getOperations());
                    gattServersuccessPersisit.setCompletedwork(  gattserversuccessEntity.getCompletedwork());
                    gattServersuccessPersisit.setNamedevice( gattserversuccessEntity.getNamedevice());
                    gattServersuccessPersisit.setMacdevice(gattserversuccessEntity.getMacdevice());
                    gattServersuccessPersisit.setGps1(  gattserversuccessEntity.getGps1() );
                    gattServersuccessPersisit.setGps2( gattserversuccessEntity.getGps2()  );
                    gattServersuccessPersisit.setFio(gattserversuccessEntity.getFio() );
                    gattServersuccessPersisit.setAdress( gattserversuccessEntity.getAdress() );
                    gattServersuccessPersisit.setCity( gattserversuccessEntity.getCity()  );
                    gattServersuccessPersisit.setDateUpdate( gattserversuccessEntity.getDateUpdate()  );
                    gattServersuccessPersisit.setUuid( gattserversuccessEntity.getUuid() );
                    gattServersuccessPersisit.setVersion( gattserversuccessEntity.getVersion() );
                    gattServersuccessPersisit.setSim(  gattserversuccessEntity.getSim());
                    gattServersuccessPersisit.setIemi( gattserversuccessEntity.getIemi());
                    gattServersuccessPersisit.setCurrentTable(gattserversuccessEntity.getCurrentTable() );
                    gattServersuccessPersisit.setGetstatusrow(gattserversuccessEntity.getGetstatusrow() );


             //session.persist(gattServersuccessPersisit);
                 resultGattServer    = (Integer) session.save(gattServersuccessPersisit);
                   // session.saveOrUpdate(gattServersuccessPersisit);

                   session.refresh(gattServersuccessPersisit );
                    // TODO: 08.09.2024
                    // TODO: 08.09.2024
                    ЛОГ.log(" Класс" + Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " метод " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "resultGattServer " +resultGattServer);
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
        return resultGattServer;

    }






    // TODO: 08.09.2024  update gatt server
    @Override
    public Integer updatetingGattServer(@NotNull   GattserversuccessEntity gattserversuccessEntity,
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

                    Query gattserverUpdate = session.createNativeQuery(" UPDATE  scannerserversuccess WITH  (XLOCK)  SET " +
                            " operations=:operations "+
                            " ,completedwork=:completedwork ,namedevice=:namedevice ,macdevice=:macdevice,gps1=:gps1 ,gps2=:gps2 " +
                            " ,getstatusrow=:getstatusrow ,adress=:adress ,city=:city  ,date_update=:date_update ,uuid=:uuid  " +
                            ", version=:version,sim=:sim ,iemi=:iemi ,current_table=:current_table "+
                            " WHERE uuid=:dockinguuid  ") ;
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
                            .setParameter("dockinguuid", gattserversuccessEntity.getUuid());
                    // TODO: 08.09.2024 result get gattserver
                    resultGattServer.set(gattserverUpdate.executeUpdate());
                    // TODO: 08.09.2024


                    // TODO: 08.09.2024
                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" resultGattServer.get() "+resultGattServer.get());

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
        Long   FindUUID=0l;
        try{
                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            "  session.getTransaction() " + session.getTransaction().getStatus());


            // TODO: 30.09.2024  получаем максимальную версию данных
            Query  queryUUD =  session.createQuery(
                    " SELECT   DISTINCT  viewone FROM model. GattserversuccessEntity viewone WHERE viewone.uuid=:uuid    ");

            queryUUD.setParameter("uuid",new BigDecimal(getUUID.longValue()));//8641 8625
            List<GattserversuccessEntity>listFindUUID =   queryUUD.setFirstResult(0).setMaxResults(1).setCacheable(true) .setLockOptions(LockOptions.UPGRADE).getResultList();

              FindUUID=listFindUUID.stream().mapToLong(m-> m.getUuid().longValue()).findAny().orElse(0l);
                    // TODO: 08.09.2024
                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" resultUUIDorVesrion.get() "+
                            " listFindUUID "+listFindUUID);
        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return FindUUID;

    }


    public Long getQuertyGattServerMaxVesrion(@NotNull Session session, @NotNull ServletContext ЛОГ) {
        // TODO: 06.09.2024
      Long resultMaxVesrion=0l;
        try{
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                    "  session.getTransaction() " + session.getTransaction().getStatus());

            // TODO: 30.09.2024  получаем максимальную версию данных
            BigDecimal MaxVesrionUUID =         session.createQuery(
                            "SELECT MAX (gatt.currentTable)   FROM model.GattserversuccessEntity AS  gatt ",BigDecimal.class).setLockOptions(LockOptions.UPGRADE).getSingleResult();

            resultMaxVesrion=    Optional.ofNullable(MaxVesrionUUID).stream().mapToLong(m->m.longValue()).findAny().orElse(0l);
            // TODO: 08.09.2024
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                    " MaxVesrionUUID "+MaxVesrionUUID+ " resultMaxVesrion.get() " +resultMaxVesrion);
        } catch (Exception e) {
            // TODO: 17.11.2023 ERROR transaction
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return resultMaxVesrion;

    }



}
