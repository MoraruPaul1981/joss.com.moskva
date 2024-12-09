package businesslogic;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/**
 * @author moraru_pi
 *
 */
public class SubClassGeneratorDate {
    public StringBuffer ДатаВремяОперациисБезКовычекЗаписямиСервлета() {
        // таблица для Записи Ошибок в приложении
        StringBuffer БилдерДляДатыБезКовычек = null;
        try {
            Date Дата = Calendar.getInstance().getTime();
            DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-dd-MM HH:mm:ss", new Locale("ru"));// "yyyy-MM-dd'T'HH:mm:ss'Z'
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-03:00"));
            String СтрокаИзДаты = dateFormat.format(Дата);
            System.out.println(" СтрокаИзДаты " + СтрокаИзДаты);
            БилдерДляДатыБезКовычек = new StringBuffer(СтрокаИзДаты); // создаем
            БилдерДляДатыБезКовычек.substring(0, 19);
            System.out.println("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                    + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " БилдерДляДатыБезКовычек "+БилдерДляДатыБезКовычек.toString());
        } catch (Exception e) {
            System.out.println("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n"
                    + " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " line "
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + "e.getMessage() "+e.getMessage().toString());
        }
        return БилдерДляДатыБезКовычек;
    }
}

