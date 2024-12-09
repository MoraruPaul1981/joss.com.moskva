package businesslogic.GetParserDate;

import businesslogic.SubClassWriterErros;
import com.sun.istack.NotNull;
import io.reactivex.rxjava3.core.Completable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

@Named
public class GetParsingDate implements DateParsrIn {

    @Inject
    SubClassWriterErros subClassWriterErros;


    /**
     * @param Bremylocal
     * @param ЛОГ
     * @return
     */
    @Override
    public Date getDateScanner(@NotNull String Bremylocal, @NotNull ServletContext ЛОГ)   {
        // TODO: 27.08.2024
        AtomicReference<Date> getBremylocal = new AtomicReference<>();
        Completable.fromAction(()->{
                    // TODO: 27.08.2024
                    try {
                        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru", "RU"));
                        Date dateEmpty = dateFormat.parse(Bremylocal);
                        getBremylocal.set(dateEmpty);
                    } catch (ParseException e) {
                        // TODO: 29.08.2024
                        DateFormat	dateFormat =   new SimpleDateFormat("yyyy-MM-dd", new Locale("ru", "RU"));
                        Date dateEmpty = dateFormat.parse(Bremylocal);
                        getBremylocal.set(dateEmpty);
                    }

                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " getBremylocal.get() " + getBremylocal.get());
                }).doOnError(e->{
                    Exception exception=new IOException(e);
                    subClassWriterErros.
                            writingCurrentErrors(exception,
                                    Thread.currentThread().
                                            getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");

                })
                .doOnComplete(()->{
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }).blockingSubscribe();
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        return getBremylocal.get();
    }
}
