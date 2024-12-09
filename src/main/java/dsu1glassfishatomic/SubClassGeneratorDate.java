package dsu1glassfishatomic;

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
        //// СОЗДАНИЕ ДАТЫ ДЛЯ ТРИГЕРА
        Date Дата = Calendar.getInstance().getTime();
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss",
        // Locale.ROOT);
        DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-dd-MM HH:mm:ss", new Locale("ru"));// "yyyy-MM-dd'T'HH:mm:ss'Z'
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-03:00"));
        String СтрокаИзДаты = dateFormat.format(Дата);
        System.out.println(" СтрокаИзДаты " + СтрокаИзДаты);
        StringBuffer БилдерДляДатыБезКовычек = new StringBuffer(СтрокаИзДаты); // создаем
        БилдерДляДатыБезКовычек.substring(0, 19);
        System.out.println(" БилдерДляДаты " + БилдерДляДатыБезКовычек.toString());

        return БилдерДляДатыБезКовычек;

    }
}