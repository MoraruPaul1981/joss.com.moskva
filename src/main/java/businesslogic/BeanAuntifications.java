package businesslogic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Optional;

/**
 * Session Bean implementation class BeanAuntifications
 */
@Stateless(mappedName = "SessionBeanAynt")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BeanAuntifications {
    @Inject
    private	SubClassConnectionsSQLServer subClassConnectionsSQLServer;
    /**
     * Default constructor.
     */
    public BeanAuntifications() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unused")
    public Boolean МетодАунтификация(@NotNull ServletContext ЛОГ,
                                     @NotNull HttpServletRequest request,
                                     @NotNull HttpSession session) {
        int РазрешонныеПрава = 2;
        Integer		IDПолученныйИзSQlServerПосик=0;/// вычисялем
        String	ИмяПолученныйИзSQlServerПосик = null ;/// вычисялем
        String		ПарольПолученныйИзSQlServerПосик = null ;/// вычисялем
        Boolean РезультатАунтификацииПользователя=false;
        try (Connection conn =subClassConnectionsSQLServer.МетодGetConnect(	ЛОГ);){
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ ЛОГ +" request " + request );
            //TODO
            Statement stmt =subClassConnectionsSQLServer.МетодGetSmtr(conn, ЛОГ);
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора   "+ stmt);
            String    ЛогинОтКлиента =new String();
            String	ПарольОтКлиента=new String();
            String	ИдиДевайсаПолученный=new String();


            ЛогинОтКлиента =((HttpServletRequest) request).getHeader("identifier");

            ПарольОтКлиента =((HttpServletRequest) request).getHeader("p_identifier");

            ИдиДевайсаПолученный =((HttpServletRequest) request).getHeader("id_device_androis");
            ЛОГ.log(" ЛогинОтКлиента " +ЛогинОтКлиента+" ЛогинОтКлиента " + ЛогинОтКлиента +  " ИдиДевайсаПолученный " +ИдиДевайсаПолученный);
            ////// TODO полученный нданные от Клиента
            if (ЛогинОтКлиента.length()>0 && ПарольОтКлиента.length()>0 && ИдиДевайсаПолученный.length()>0) {
                /////// ПОЛУЧЕНИИ КОЛИЧЕСТВА
                /////// СТОЛБЦОВ В БАЗЕ
                String	queryСканируемИмяИпароль = "SELECT   id ,login,password  FROM    [storage].[dbo].[users]    "
                        + "          WHERE login  =  '" + ЛогинОтКлиента
                        + "' AND password= '" + ПарольОтКлиента + "'"
                        + " AND rights= '" + РазрешонныеПрава + "'    ;";//// ЗАПРОС
                //////
                System.out.println(" queryСканируемИмяИпароль   " + queryСканируемИмяИпароль);
                // TODO получаем имя и пвроль
                //////// запрос вычисляет имя и пароль и id
                ResultSet РезультатСканированиеИмениИПароль = stmt.executeQuery(queryСканируемИмяИпароль);
                РезультатСканированиеИмениИПароль.last();
                // todo НАЛИЧИЕ ПОЛЬЗОВАТЕЛЯ В БАЕЗ
                int НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть = РезультатСканированиеИмениИПароль
                        .getRow();
                РезультатСканированиеИмениИПароль.beforeFirst();
                System.out.println(" protected void doGet НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть"
                        + НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть);
                // TODO ЕСЛИ У ТЕКУЩЕГО ПОЛЬЗОВАТЕЛЯ НЕТ ПРАВ НАПРИМЕР--2 ТО
                if (НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть > 0) {
                    //// TODO СЮДА ЗАХОДИМ КОГДА ПОЛЬЗОВАТЕЛЬ
                    while (РезультатСканированиеИмениИПароль.next()) { //// КОЛИЧЕСТВО
                        IDПолученныйИзSQlServerПосик = РезультатСканированиеИмениИПароль.getInt(1);/// вычисялем
                        ИмяПолученныйИзSQlServerПосик = РезультатСканированиеИмениИПароль.getString(2);/// вычисялем
                        ПарольПолученныйИзSQlServerПосик = РезультатСканированиеИмениИПароль.getString(3);/// вычисялем

                        ЛОГ.log("  ЛогинПолученныйОтКлиента  "
                                + ЛогинОтКлиента +
                                " ИмяПолученныйИзSQlServerПосик " +ИмяПолученныйИзSQlServerПосик
                                + " ПарольПолученныйИзSQlServerПосик " +ПарольПолученныйИзSQlServerПосик +
                                " ПарольОтКлиента " +ПарольОтКлиента+" СколькСтрокРезультатЕслиТакойПользовательМетод_GET "
                                + НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть);
                        ////
                        if (ЛогинОтКлиента.compareTo(ИмяПолученныйИзSQlServerПосик)==0 &&  ПарольПолученныйИзSQlServerПосик.compareTo(ПарольОтКлиента)==0
                                && IDПолученныйИзSQlServerПосик>0 && ИдиДевайсаПолученный.length()>0) { ///// TODO
                            //TODO меняем статут и пускак клиента на сервер
                            РезультатАунтификацииПользователя=true;

                            //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ ID  ТЕКУЩЕГО ПОЛЗОВАТКЕЛЯ
                            ЛОГ.setAttribute("IDПолученныйИзSQlServerПосик", IDПолученныйИзSQlServerПосик);
                            ЛОГ.setAttribute("ЛогинПолученныйОтКлиента", ЛогинОтКлиента);
                            ЛОГ.setAttribute("ПарольПолученныйОтКлиента", ПарольОтКлиента);
                            ЛОГ.setAttribute("АдуДевайсяКлиента", ИдиДевайсаПолученный);

                            session.setAttribute("IDПолученныйИзSQlServerПосик", IDПолученныйИзSQlServerПосик);
                            session.setAttribute("ЛогинПолученныйОтКлиента", ЛогинОтКлиента);
                            session.setAttribute("ПарольПолученныйОтКлиента", ПарольОтКлиента);
                            session.setAttribute("АдуДевайсяКлиента", ИдиДевайсаПолученный);

                            ЛОГ.log("  ЛогинОтКлиента  "
                                    + ЛогинОтКлиента +
                                    " ИмяПолученныйИзSQlServerПосик " +ИмяПолученныйИзSQlServerПосик
                                    + " ПарольОтКлиента " +ПарольОтКлиента +
                                    " IDПолученныйИзSQlServerПосик " +IDПолученныйИзSQlServerПосик+ " ИдиДевайсаПолученный "+ИдиДевайсаПолученный);
                        }

                        break;
                        ///////////////////////HeaderСодержимоеРасшифрован
                    }
                }
                РезультатСканированиеИмениИПароль.close();
                stmt.close();
                conn.close();
                //TODO
                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "РезультатСканированиеИмениИПароль " +РезультатСканированиеИмениИПароль);
            }else {
                //TODO
                //TODO меняем статут и пускак клиента на сервер
                РезультатАунтификацииПользователя=false;
                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+  "РезультатАунтификацииПользователя " +РезультатАунтификацииПользователя);
            }

        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return РезультатАунтификацииПользователя;
    }

}
