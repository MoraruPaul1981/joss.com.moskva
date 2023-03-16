package dsu1glassfishatomic;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author moraru_pi
 *
 */
public class SubClassWriterErros {

    @SuppressWarnings("deprecation")
    public void МетодаЗаписиОшибкиВЛог(Exception e,
                                       String ЛогинПолученныйОтКлиента,
                                       String ГдеВКакомЗаданииПроизошалОшибка,
                                       StackTraceElement ТекущийПотокВмоментВыполения,
                                       ServletContext ЛОГ,
                                       String ПараметрИмяТаблицыОтАндройдаGET) {
        e.printStackTrace();
        // TODO
        //String путьЗаписиЖурналаКудаЗаписовать ="C:\\SERVERS\\ErrorServletDSU1.txt";
        String путьЗаписиЖурналаКудаЗаписовать ="C:\\JBOSS\\ErrorJbossServletDSU1.txt";
        //String путьЗаписиЖурналаКудаЗаписовать ="C:\\SERVERS\\glassfish-4.1.2\\glassfish4\\ErrorServletDSU1.txt";
        //TODO C:\SERVERS\glassfish-4.1.2\glassfish4   C:\\glassfish-4.1.2 dsu1glassfishatomic\\glassfish4\\ErrorServletDSU1.txt";/////ErrorServletDSU1
        String РЕальныйПутьСервера = ЛОГ.getRealPath("/");

        String САМАОШИБКАДЛЯЗАПИСИ = ГдеВКакомЗаданииПроизошалОшибка
                + "\n" + " Класс/CLASS :"
                + ТекущийПотокВмоментВыполения.getClassName() + "\n" + " Метод/METOD :" + "\n"
                + ТекущийПотокВмоментВыполения.getMethodName() + "\n" + " Линия/LINE  :"
                + ТекущийПотокВмоментВыполения.getLineNumber() + "\n" + " Линия/LINE  :"
                + ТекущийПотокВмоментВыполения.getFileName() + "\n" + " САМА ОШИБКА /GET ERROR ::: " + e.toString() + " Файл  :"
                + Thread.currentThread().getStackTrace()[2].getFileName() + "\n" + " САМА ОШИБКА/GET ERROR ::: " + e.toString()
                + "\n" + "  Класс  ОШИБКЕ   " + "\n" + e.getClass().getName() + "\n" + "  Класс  ОШИБКЕ /CLASS ERROR  " + "\n"
                + e.toString().toUpperCase().toString() + "\n" + "  метод  ОШИБКЕ /METOD ERROR  " + "\n"
                + e.fillInStackTrace().getMessage() + "\n" + "e.fillInStackTrace().getLocalizedMessage() "
                + e.fillInStackTrace().getLocalizedMessage() + "\n"+ "ПараметрИмяТаблицыОтАндройдаGET " +ПараметрИмяТаблицыОтАндройдаGET;

        ///////// начало запись в файл
        System.err.println("public class ClassWriterErrorProjectDsu1 {  Метод : ERROR B SAMOM MOTODE ERROR GENERETOR " + e.toString());
        if (ЛОГ!=null) {
            ЛОГ.log("ERROR ???????????????????????????????????????? /САМАОШИБКАДЛЯЗАПИСИ " + "\n"
                    + САМАОШИБКАДЛЯЗАПИСИ + "\n" + "e.fillInStackTrace().getMessage() " + e.fillInStackTrace().getMessage()
                    + "\n" + "###ERROR######" + "\n" + new Date());
        }
        try  (PrintWriter pw =
                      new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(путьЗаписиЖурналаКудаЗаписовать), true), StandardCharsets.UTF_8));) {
            //
            // перевод строки в байты
            pw.append("\n");
            pw.append("\n");
            pw.append("Время");
            pw.append(new Date().toGMTString().toString());
            pw.append("\n");
            pw.append("\n");
            pw.append("######################error start ########################### время " + new Date());
            pw.append("\n");
            pw.println(ГдеВКакомЗаданииПроизошалОшибка);
            pw.append("\n");
            pw.append("Логин чья ошибка");
            pw.append("\n");
            pw.append(ЛогинПолученныйОтКлиента);
            pw.println("НАЗВАНИЕ ТАБЛИЦЫ ГДЕ ПРОИЗОШДА ОШИБКА//CURRENT TABLE ASYNC :: ");
            pw.append("\n");
            pw.println(ПараметрИмяТаблицыОтАндройдаGET);
            pw.append("\n");
            pw.append(" Пользователь ");
            pw.append("\n");
            pw.append(ЛОГ.getAttribute("HeaderСодержимоеРасшифрован").toString());
            pw.append("\n");
            pw.append("Дата ошибки");
            pw.append("\n");
            pw.append(" " + new SubClassGeneratorDate()
                    .ДатаВремяОперациисБезКовычекЗаписямиСервлета() + "  ");
            pw.append("\n");
            pw.append(САМАОШИБКАДЛЯЗАПИСИ);
            pw.append("\n");
            pw.append("#####################error end ############################ " + new Date());
            pw.append("\n");
            pw.append("\n");
            pw.append(	ЛОГ.getContextPath().toString());
            pw.append("\n");
            pw.append("		РЕальныйПутьСервера");
            pw.append(	РЕальныйПутьСервера);
            pw.append("\n");
            pw.append("\n");
            /// todo close message send
            pw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(
                    "\n"+"Inside Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" + e.toString());
            if (ЛОГ!=null) {
                ЛОГ.log(
                        "\n"+" Inside Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" );

            }

        }

    }

}