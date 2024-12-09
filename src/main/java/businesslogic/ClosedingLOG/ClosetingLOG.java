package businesslogic.ClosedingLOG;

import businesslogic.SubClassWriterErros;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;


@Named("closetingLOG")
public class ClosetingLOG {
    @Inject
    private SubClassWriterErros subClassWriterErros;

  public   void startclosetingLOG(ServletContext ЛОГ){

try{
    ЛОГ.removeAttribute("IdUser" );
    ЛОГ.removeAttribute("getsslattrib" );
    ЛОГ.removeAttribute("АдуДевайсяКлиента" );
    ЛОГ.removeAttribute("GrantRigntUsers" );

    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
            +""+Thread.currentThread().getName() );

    } catch (Exception e) {
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(), "ErrorsLogs/ErrorJbossServletUpdatePO.txt");
    }
    }
}
