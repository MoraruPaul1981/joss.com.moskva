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

import com.sun.istack.NotNull;
import dsu1glassfishatomic.SubClassWriterErros;
import org.hibernate.SessionFactory;

/**
 * Session Bean implementation class BeanPOST
 */
@Stateless(mappedName = "SessionBeanForPOST")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BeanPOST {
    @Inject
    SubClassSessionBeanPOST subClassSessionBeanPOST;
    @Inject
    BEANCallsBack bEANCallsBack;
    /**
     * Default constructor.
     */
    public BeanPOST() {
        // TODO Auto-generated constructor stub
        System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }



    @SuppressWarnings("unused")

    public void МетодБинаPOST(@NotNull ServletContext ЛОГ,
                               @NotNull HttpServletRequest request,
                               @NotNull  HttpServletResponse response,
                              @NotNull SessionFactory sessionSousJbossRuntime) throws InterruptedException, ExecutionException {;
        try {
            ///Todo  получаем данные от клиента
            Future<StringBuffer>       БуферРезультатPOST= 	 АсинхронныйЗапускPOST(ЛОГ,request,response,sessionSousJbossRuntime);
          //  ЛОГ.log("  БуферРезультатGET  " + БуферРезультатPOST.get());
            ///Todo получаем данные от Клиента на Сервер
                bEANCallsBack.МетодBackДанныеКлиенту(response, БуферРезультатPOST.get(), ЛОГ);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+"  БуферРезультатGET  " + БуферРезультатPOST.get());
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber(),
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }





    @SuppressWarnings("unused")
    @Asynchronous
    private Future<StringBuffer> АсинхронныйЗапускPOST( @NotNull ServletContext ЛОГ,
                                                        @NotNull HttpServletRequest request,
                                                        @NotNull  HttpServletResponse response,
                                                        @NotNull SessionFactory sessionSousJbossRuntime){
        StringBuffer БуферРезультатPOST=null;
        try {
            БуферРезультатPOST=		subClassSessionBeanPOST.ГлавныйМетод_МетодаPOST(request, response, ЛОГ,sessionSousJbossRuntime);
            if(БуферРезультатPOST==null) {
                БуферРезультатPOST=new StringBuffer();
            }
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  БуферРезультатPOST " +БуферРезультатPOST);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return new AsyncResult<StringBuffer>(БуферРезультатPOST);
    }




}

