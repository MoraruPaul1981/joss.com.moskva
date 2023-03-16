package businesslogic;


import java.io.StringReader;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.ws.rs.Produces;

import com.sun.istack.NotNull;
import dsu1glassfishatomic.SubClassWriterErros;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//TODO
@RequestScoped
@Produces
public class SubClassGenerateJson {
    private   ServletContext ЛОГ;
    private Transaction sessionTransaction;

    // TODO: 09.03.2023
    StringBuffer МетодГенерацияJson(
            @NotNull ServletContext ЛОГ,
            @NotNull JsonObject JSONStremОтAndrod
            , @NotNull String ПараметрИмяТаблицыОтАндройдаPost,
            @NotNull  SessionFactory sessionSousJbossRuntime) throws SQLException {

        StringBuffer БуферОтветКлиентуОтСервера=new StringBuffer();
        /// javax.persistence.EntityManager  МенеджерJTA = ФабрикаДляМенеждера.createEntityManager();
        try {
            this.ЛОГ=ЛОГ;
            // TODO: 11.03.2023  Получении Сесии Hiberrnate
           Session    session =sessionSousJbossRuntime.openSession();
            // TODO: 14.03.2023  Запускаем Транзакцию
             sessionTransaction =session.getTransaction() ;
            // TODO: 14.03.2023  Запускает Транзакции
            sessionTransaction.begin();
            ЛОГ.log(" jsonReaderПришеоОтКлиентаJSON_P "+JSONStremОтAndrod.toString()  + " session  " +session + " sessionSousJbossRuntime " +sessionSousJbossRuntime);
            //TODO ГЛАВЕНЫЙ ЦИКЛ ОБРАБОТКИ ДАННЫХ В МЕТОДЕ  POST
            JSONStremОтAndrod.entrySet().forEach(ВнешнаяСтрокаJSON -> {
                //  МенеджерJTA.getTransaction().begin();
                ЛОГ.log(" ВнешнаяСтрокаJSON.getKey() "+ВнешнаяСтрокаJSON.getKey()  + " ВнешнаяСтрокаJSON.getValue() " +ВнешнаяСтрокаJSON.getValue());
                //JsonReader ДляВнутренегоЦиклаjsonReaderJSON = Json.createReader(new StringReader(ВнешнаяСтрокаJSON.getValue().toString()));
                //TODO
                JsonReader ДляВнутренегоЦиклаjsonReaderJSON = Json.createReader(new StringReader(ВнешнаяСтрокаJSON.getValue().toString()));
                //TODO
                ЛОГ.log(" ДляВнутренегоЦиклаjsonReaderJSON "+ДляВнутренегоЦиклаjsonReaderJSON.toString()+"\n"+
                        " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);
                //TODO Внутриний JSON			  //TODO
                JsonObject ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P = ДляВнутренегоЦиклаjsonReaderJSON.readObject();
                //TODO
                ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString());

                //TODO
                ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+
                        "ПараметрИмяТаблицыОтАндройдаPost   " +ПараметрИмяТаблицыОтАндройдаPost);

                //TODO ВНИМАНИЕ второй АРГУМЕНТ FIO #2
                String	ФиналUIDorIdДляСостыковкиЕстьИлиНЕтУжеВБАзе = null ;
                //TODO определем если в таблицы есть поле  UUID или ID
                if(ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.containsKey("uuid")) {
                    //TODO
                    ЛОГ.log("ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.containsKey(\"uuid\")  " +
                            ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.containsKey("uuid"));
                    ФиналUIDorIdДляСостыковкиЕстьИлиНЕтУжеВБАзе=
                            Optional.ofNullable(ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P
                                    .getString("uuid").replaceAll("\"", "")).map(String::new).get();
                }else {
                    //TODO ID
                    if(ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.containsKey("id")) {
                        //TODO
                        ЛОГ.log("ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.containsKey(\"id\")  " +
                                ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.containsKey("id"));

                        ФиналUIDorIdДляСостыковкиЕстьИлиНЕтУжеВБАзе=
                                Optional.ofNullable(ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P
                                        .getString("id").replaceAll("\"", "")).map(String::new).get();
                    }
                }
                //TODO
                ЛОГ.log(" ФиналUIDorIdДляСостыковкиЕстьИлиНЕтУжеВБАзе "+ФиналUIDorIdДляСостыковкиЕстьИлиНЕтУжеВБАзе);
                //TODO ЗАПОЛЕНИЯ ТАБЛИЦ И  ОТПРАВКА ЗНАЧЕНИЙ В УДАЛЕННУЮ ПРОЦЕДУРУ
                StoredProcedureQuery	 queryprocedure = null;
                //TODO ЗАПОЛЯЕМ ДАННЕЫ ДЛЯ ВСТВКИ МЕТОДА POST()
                switch(ПараметрИмяТаблицыОтАндройдаPost) {
                    //TODO
                    case "settings_tabels":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);

                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery("ProcedureExistsMERGEsettings_tabels#2");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"settings_tabels\":	queryprocedure "
                                +queryprocedure);

                        break;
                    //TODO fio

                    case "fio":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEfio#1");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"fio\":	queryprocedure "
                                +queryprocedure);



                        //TODO
                        break;
                    //TODO data_tabels
                    case "data_tabels":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdata_tabels#3");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"data_tabels\":	queryprocedure "
                                +queryprocedure);

                        //TODO
                        break;

                    //TODO tabel
                    case "tabel":

                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEtabel#4");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"tabel\":	queryprocedure "
                                +queryprocedure);

                        //TODO
                        break;

                    //todo organization
                    case "organization":

                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEorganization#5");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"organization\":	queryprocedure "
                                +queryprocedure);

                        //TODO
                        break;
                    //TODO

                    case "depatment":

                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdepatment#6");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"depatment\":	queryprocedure "
                                +queryprocedure);
                        //TODO
                        break;


                    //TODO notifications
                    case "notifications":

                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEnotifications#7");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"notifications\":	queryprocedure "
                                +queryprocedure);

                        //TODO
                        break;

                    //TODO data_notification
                    case "data_notification":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdata_notification#8");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"data_notification\":	queryprocedure "
                                +queryprocedure);

                        //TODO
                        break;

                    //TODO chats
                    case "chats":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEchats#9");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"chats\":	queryprocedure "
                                +queryprocedure);


                        //TODO
                        break;

                    //TODO
                    case "data_chat":
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdata_chat#10");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"data_chat\":	queryprocedure "
                                +queryprocedure);



                        //TODO
                        break;

                    //TODO templates
                    case	"templates":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEtemplates#11");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"templates\":	queryprocedure "
                                +queryprocedure);


                        //TODO
                        break;

                    //TODO  fio_template
                    case	"fio_template":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEfio_template#12");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"fio_template\":	queryprocedure "
                                +queryprocedure);


                        //TODO
                        break;



                    //TODO region

                    //TODO  fio_template
                    case	"region":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEregion#13");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"region\":	queryprocedure "
                                +queryprocedure);


                        //TODO
                        break;

                    //TODO cfo
                    case	"cfo":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);


                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEcfo#14");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"cfo\":	queryprocedure "
                                +queryprocedure);




                        //TODO
                        break;
                    //TODO get_materials_data
                    case	"get_materials_data":
                        //TODO
                        ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);
                        //TODO Процедура Удаленная вызова SQL Server
                        queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEget_materials_data#17");//TODO ProcedureExistsMERGE_fio
                        //TODO
                        ЛОГ.log(" case \"cfo\":	queryprocedure " +queryprocedure);
                        //TODO
                        break;
                    //TODO не выбраны таблицы

                    default:
                        //TODO
                        ЛОГ.log(" NE BiBRANA NE ODNA TABLIZA(не выбрана не одна таблицы !!!"
                                + ") default:default:default:default: ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+"\n"
                                +ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);
                        break;
                    ///TODO конец всех таблиц

                }//TODO end switch --выбор таблиц
                //TODO заполеяеним значение для состыковки id or uuid
                ЛОГ.log(" queryprocedure " + queryprocedure);
                //TODO Заполения
                if(queryprocedure!=null) {
                    ЛОГ.log(" NOT NULL queryprocedure " + queryprocedure);
                    //TODO ГЛАВНЫЙ ЦИКЛ ЗАПОЛЕНИЯ ДАННЫМИИ JSONVALUE  ТАБЛИЦЫ
                    //TOOD заполем колонки ВТОРОЙ ЦИКЛ ЗАПОЛНЕНИЯ КОЛОНОК
                    for (Entry<String, JsonValue> ДанныеДляОбработкиКолонокJSON :ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.entrySet())
                    {
                        //TODO

                        String КлючДляЗаполенияJSON=ДанныеДляОбработкиКолонокJSON.getKey();


                        //TODO

                        //TODO иключение  только для двух таблиц , работа по ID  полю "region"  "cfo"
                        ЛОГ.log(" КлючДляЗаполенияJSON "+КлючДляЗаполенияJSON+"\n"
                                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost+ " ДанныеДляОбработкиКолонокJSON "+ДанныеДляОбработкиКолонокJSON);

                        //TODO само заполенения



                        //TODO
                        if(!ДанныеДляОбработкиКолонокJSON.getKey().contentEquals("id")) {

                            ///TODO заполения данными из таблицы улиента менеджер сущностей
                            МетодПерпосредвственноЗаполентДАннымиОтКлиентаМенеджерСущностей(ЛОГ,
                                    ПараметрИмяТаблицыОтАндройдаPost,
                                    ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P, queryprocedure, ДанныеДляОбработкиКолонокJSON,
                                    КлючДляЗаполенияJSON);

                            // TODO ПОСЛЕ ЗАПОЛЕНИЯ
                            ЛОГ.log(" КлючДляЗаполенияJSON "+КлючДляЗаполенияJSON);
                        }

                        //TODO



                        // TODO ПОСЛЕ ЗАПОЛЕНИЯ
                        ЛОГ.log(" queryprocedure "+queryprocedure);


                    }//TODO end FOR


                    //TODO заполения систменыеми постоянными двумя параментырами

                    //TODO ВНИМАНИЕ ВТОРОЕ  ПАРАМЕНТ ДЛЯ ВСЕХ ТАБЛИЦ #1
                    //TODO
                    queryprocedure.registerStoredProcedureParameter("SrabnitUUIDOrID", String.class, ParameterMode.IN)
                            .setParameter("SrabnitUUIDOrID",ФиналUIDorIdДляСостыковкиЕстьИлиНЕтУжеВБАзе);
                    //TODO
                    ЛОГ.log(" 			queryprocedure.registerStoredProcedureParameter(\"SrabnitUUIDOrID\", String.class, ParameterMode.IN) ");

                    //TODO ВНИМАНИЕ ПЕРЫЙ ПАРАМЕНТ ДЛЯ ВСЕХ ТАБЛИЦ #2
                    queryprocedure.registerStoredProcedureParameter( "ResultatMERGE", String.class, ParameterMode.INOUT )
                            .setParameter("ResultatMERGE",  "complete merge");//todo Long.class
                    // TODO
                    ЛОГ.log(" 		queryprocedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.OUT ) ");
                    //TODO

                    // TODO
                    ЛОГ.log(" queryprocedure "+queryprocedure.getParameters().size()  + " ПараметрИмяТаблицыОтАндройдаPost  " +ПараметрИмяТаблицыОтАндройдаPost);

                    //TODO метод саомго выполения удаленной процедуры

                  Integer РезультатОперацииВставкииОбновлениея=  МетодСамогоВыполенияУдаленнойПроцедуры(queryprocedure,ЛОГ,ПараметрИмяТаблицыОтАндройдаPost);
                    String РезультатСовершнойОперации= null;

                    if (РезультатОперацииВставкииОбновлениея>0) {
                        //TODO получаем ответный результат
                        РезультатСовершнойОперации = (String)queryprocedure.getOutputParameterValue("ResultatMERGE");
                    }
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОперацииВставкииОбновлениея "+РезультатОперацииВставкииОбновлениея  +  " РезультатСовершнойОперации " +РезультатСовершнойОперации);
                    БуферОтветКлиентуОтСервера	 .append("\n")
                            .append("result POST()   insert and update from android")
                            .append("\n")
                            .append(РезультатСовершнойОперации)
                            .append("\n")
                            .append(" таблица обработки ").append(ПараметрИмяТаблицыОтАндройдаPost)
                            .append("\n")
                            .append("Пользователь Операциии")
                            .append("\n")
                            .append("\n")
                            .append(new Date().toString())
                            .append("\n");
                    ЛОГ.log(" БуферОтветКлиентуОтСервера " +БуферОтветКлиентуОтСервера);
                }else {
                    //TODO не выбрали ни одну талицу
                    БуферОтветКлиентуОтСервера
                            .append("\n")
                            .append("result POST()   insert and update from android")
                            .append("Нет таблицы для Обработки ,или самой обработки")
                            .append("\n")
                            .append("\n")
                            .append("Пользователь Операциии")
                            .append("\n")
                            .append(" таблица обработки ").append(ПараметрИмяТаблицыОтАндройдаPost)
                            .append("\n")
                            .append("\n")
                            .append(new Date().toString())
                            .append("\n");

                    ЛОГ.log("NOT TABLE for generations БуферОтветКлиентуОтСервера " +БуферОтветКлиентуОтСервера);
                }
                //TODO проталиваемМетодОбработкиJSONPСтиминг КОНЕЦ ОБРАБОТКТ ВСТАВКИ ДАННЫХ В МЕТОД POST()
                //TODO
                ЛОГ.log(" ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P "+ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.toString()+"\n"
                        + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);
            });
            //TODO после цикла всех строк выключаем менеджеры сущностей  ПОСЛЕ ЦИКЛА С ДАННЫМИ
            МетодЗавершенияСеанса(session);
            //TODO
            ///ФабрикаДляМенеждера.close();
            ЛОГ.log("БуферОтветКлиентуОтСервера  "+ БуферОтветКлиентуОтСервера.toString());
            ///TODO
            //TODO
        } catch (Exception   e) {
            sessionTransaction.rollback();
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "{ ЛогинПолученныйОтКлиента "+ " \n"+БуферОтветКлиентуОтСервера.toString()
                            +null
                    ,
                    Thread.currentThread().getStackTrace()[2],null,ЛОГ.getServerInfo().toLowerCase());

        }
        return БуферОтветКлиентуОтСервера;

    }

    // TODO: 09.03.2023  метод очистки Hirenate после операции
    private void МетодЗавершенияСеанса(@NotNull  Session session) {
        try{
            if (    sessionTransaction.isActive()) {
                sessionTransaction.commit();
            }
            if (session.isDirty()) {
                session.flush();
            }
            if (session.isOpen() || session.isConnected()) {
                session.clear();
                session.close();
            }
            ЛОГ.log("\n МетодЗакрываемСессиюHibernate "+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" +  "session " +session);
    } catch (Exception   e) {
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "{ ЛогинПолученныйОтКлиента " +null, Thread.currentThread().getStackTrace()[2],null,ЛОГ.getServerInfo().toLowerCase());

    }
    }


    // TODO: 14.03.2023  Метод Самого Выполениея Операции POST EXE
    private Integer МетодСамогоВыполенияУдаленнойПроцедуры(@NotNull  StoredProcedureQuery queryprocedure,
                                                           @NotNull  ServletContext ЛОГ ,
                                                           @NotNull String ПараметрИмяТаблицыОтАндройдаPost) {
        Integer КоличестоУспешныхОперацийНаСервере=0;
        try {
            ЛОГ.log(" queryprocedure " +queryprocedure + "  ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);
            ////todo выполения .EXE
            queryprocedure.execute();
            // TODO: 14.03.2023  Подсчитиваем КОличество спрешных Операций 
        КоличестоУспешныхОперацийНаСервере=   queryprocedure.getUpdateCount();
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " queryprocedure "+queryprocedure  + " КоличестоУспешныхОперацийНаСервере " +КоличестоУспешныхОперацийНаСервере);
        } catch (Exception   e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "		private void МетодСамогоВыполенияУдаленнойПроцедуры(StoredProcedureQuery queryprocedure,ServletContext ЛОГ ) {",
                    Thread.currentThread().getStackTrace()[2],null,ЛОГ.getServerInfo().toLowerCase());
        }
        return  КоличестоУспешныхОперацийНаСервере;
        //TODO
    }

    /**
     * @param ЛОГ
     * @param ПараметрИмяТаблицыОтАндройдаPost
     * @param ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P
     * @param queryprocedure
     * @param entry
     * @param КлючДляЗаполенияJSON
     */
    void МетодПерпосредвственноЗаполентДАннымиОтКлиентаМенеджерСущностей(ServletContext ЛОГ,
                                                                         String ПараметрИмяТаблицыОтАндройдаPost,
                                                                         JsonObject ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P,
                                                                         StoredProcedureQuery queryprocedure,
                                                                         Entry<String, JsonValue> entry,
                                                                         String КлючДляЗаполенияJSON) {

        ЛОГ.log(" КлючДляЗаполенияJSON "+КлючДляЗаполенияJSON+"\n"
                + " ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);

        try {


            //TODO 2
            Object ЗначениеДляЗаполенияJSON=entry.getValue();



            //TODO
            String	ФиналЗначениеДляЗаполенияJSON=
                    Optional.ofNullable(ЗначениеДляЗаполенияJSON.toString().replaceAll("\"", "")).orElse("");



            //TODO заполеяеним значение для состыковки id or uuid
            ЛОГ.log(" КлючДляЗаполенияJSON " + КлючДляЗаполенияJSON +
                    " ФиналЗначениеДляЗаполенияJSON " +ФиналЗначениеДляЗаполенияJSON);

            // TODO заполенем JSonValue
            queryprocedure.registerStoredProcedureParameter(КлючДляЗаполенияJSON, String.class, ParameterMode.IN)
                    .setParameter(КлючДляЗаполенияJSON,ФиналЗначениеДляЗаполенияJSON);
            //TODO

            // TODO
            ЛОГ.log(" ЗначениеДляЗаполенияJSON.toString() "
                    +ЗначениеДляЗаполенияJSON.toString()+
                    " 	ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.getString(КлючДляЗаполенияJSON)" +
                    ВнутренийОбщийJSONОБьектjsonReaderПришеоОтКлиентаJSON_P.getString(КлючДляЗаполенияJSON)+
                    "  ФиналЗначениеДляЗаполенияJSON " +ФиналЗначениеДляЗаполенияJSON);


            //TODO
        } catch (Exception   e) {
            // TODO: handle exception

            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "		void МетодПерпосредвственноЗаполентДАннымиОтКлиентаМенеджерСущностей(ServletContext ЛОГ,",
                    Thread.currentThread().getStackTrace()[2],null,ЛОГ.getServerInfo().toLowerCase());

        }

    }

}

