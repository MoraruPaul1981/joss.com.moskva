package businesslogic;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.InputStream;


@Named("closingIntutSteam")
public class SetClosingIntutSteam {

    @Inject
    private SubClassWriterErros subClassWriterErros;

    public void clossingImputSreatm(InputStream requestInputStream, ServletContext ЛОГ) {
        try {
            if (requestInputStream!=null) {
                requestInputStream.close();
            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            ЛОГ.log("ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " e " + e.getMessage());
            subClassWriterErros.writingCurrentErrors(e,
                    Thread.currentThread().
                            getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }

    }



}
