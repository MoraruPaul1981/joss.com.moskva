package businesslogic;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author moraru_pi
 *
 */

@javax.ejb.Singleton
public class SubClassWriterErros {
    private Logger LOGGER = LoggerFactory.getLogger(SubClassWriterErros.class);
    public void writingCurrentErrors(@NotNull  Exception e,
                                     @NotNull StackTraceElement[] ТекущийПотокВмоментВыполения,
                                     @NotNull String ПутьНАхожденияФайлаЛогами) {
        // TODO
        String САМАОШИБКАДЛЯЗАПИСИ = " Класс/CLASS :"
                + ТекущийПотокВмоментВыполения[2].getClassName() + "\n" + " Class/Класс :" + "\n"
                + ТекущийПотокВмоментВыполения[2].getMethodName() + "\n" + " Metod/Метод  :"
                + ТекущийПотокВмоментВыполения[2].getLineNumber() + "\n" + " Line/Линия  :"
                + ТекущийПотокВмоментВыполения[2].getFileName() + "\n" + " САМА ОШИБКА /GET ERROR ::: " + e.toString() + " Файл  :"
                + Thread.currentThread().getStackTrace()[2].getFileName() + "\n" + " САМА ОШИБКА/GET ERROR ::: " + e.toString()
                + "\n" + "  Класс  ОШИБКЕ   " + "\n" + e.getClass().getName() + "\n" + "  Класс  ОШИБКЕ /CLASS ERROR  " + "\n"
                + e.toString().toUpperCase().toString() + "\n" + "  метод  ОШИБКЕ /METOD ERROR  " + "\n"
                + e.fillInStackTrace().getMessage() + "\n" + "e.fillInStackTrace().getLocalizedMessage() "
                + e.fillInStackTrace().getLocalizedMessage() + "\n" + "ПутьНАхожденияФайлаЛогами "
                + ПутьНАхожденияФайлаЛогами;


        // TODO: 21.09.2024
        LOGGER.debug(
                "\n" + " Inside Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " САМАОШИБКАДЛЯЗАПИСИ " + САМАОШИБКАДЛЯЗАПИСИ);

        if (!САМАОШИБКАДЛЯЗАПИСИ.trim().matches("(.*)разорвала(.*)")) {
            if (!e.getMessage().equalsIgnoreCase("Программа на вашем хост-компьютере разорвала установленное подключение")) {
                try (PrintWriter pw =
                             new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(ПутьНАхожденияФайлаЛогами),
                                     true), StandardCharsets.UTF_8));) {
                    //
                    // перевод строки в байты
                    pw.append("\n");
                    pw.append("Bremy->");
                    pw.append("\n");
                    pw.append(" " + new SubClassGeneratorDate().ДатаВремяОперациисБезКовычекЗаписямиСервлета() + "  ");
                    pw.append("\n");
                    pw.append("\n");
                    pw.append("\n");
                    pw.append("ERROR->");
                    pw.append(САМАОШИБКАДЛЯЗАПИСИ);
                    pw.append("\n");
                    pw.append("#####################error end ############################ " + new Date());
                    pw.append("\n");
                    pw.append("\n");
                    pw.append("\n");
                    /// todo close message send
                    pw.flush();
                    // TODO: 21.09.2024
                    // TODO: 21.09.2024
                    LOGGER.debug(
                            "\n" + " Inside Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " САМАОШИБКАДЛЯЗАПИСИ " + САМАОШИБКАДЛЯЗАПИСИ);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    // TODO: 21.09.2024
                    LOGGER.error(
                            "\n" + " Inside Error.... class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " ex " + ex.getMessage());
                }
            }

        }
    }

}
