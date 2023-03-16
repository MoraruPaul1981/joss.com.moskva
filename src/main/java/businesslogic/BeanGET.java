package businesslogic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.sun.istack.NotNull;
import dsu1glassfishatomic.SubClassWriterErros;
import org.hibernate.SessionFactory;

import static org.hibernate.type.StandardBasicTypes.SERIALIZABLE;

/**
 * Session Bean implementation class BeanGET
 */
@Stateless(mappedName = "SessionBeanForGET")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BeanGET {
    /**
     * Default constructor.
     */
    @Inject
    SubClassSessionBeanМетодаGET subClassSessionBeanМетодаGET;
    @Inject
    BEANCallsBack bEANCallsBack;
    public BeanGET(	  ) {
        // TODO Auto-generated constructor stub
        try {
            System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.print("\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+e.getMessage().toString());
        }
    }
    @SuppressWarnings("unused")
    public void  МетодБинаGET(@NotNull ServletContext ЛОГ,
                              @NotNull HttpServletRequest request,
                              @NotNull  HttpServletResponse response,
                              @NotNull SessionFactory sessionSousJbossRuntime) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от GET метода
            Future<StringBuffer>       БуферРезультатGET= 	 АсинхронныйЗапускGET(ЛОГ,request,sessionSousJbossRuntime);
           // ЛОГ.log( "  БуферРезультатGET  " + БуферРезультатGET.get());
            ///Todo отправляем  клиенту ответ от серверац
                bEANCallsBack.МетодBackДанныеКлиенту(response, БуферРезультатGET.get(), ЛОГ);
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+"  БуферРезультатGET  " + БуферРезультатGET.get());
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }




    @SuppressWarnings("unused")
    @Asynchronous
    private Future<StringBuffer> АсинхронныйЗапускGET(@NotNull ServletContext ЛОГ,
                                                       @NotNull HttpServletRequest request,
                                                      @NotNull SessionFactory sessionSousJbossRuntime){
        StringBuffer БуферРезультатGET=null;
        try {
            БуферРезультатGET=		subClassSessionBeanМетодаGET.ГлавныйМетод_МетодаGET(request,  ЛОГ,sessionSousJbossRuntime);
            if(БуферРезультатGET==null) {
                БуферРезультатGET=new StringBuffer();
            }
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  БуферРезультатGET " +БуферРезультатGET);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return new AsyncResult<StringBuffer>(БуферРезультатGET);
    }


}
