package businesslogic;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Named("listerAsyncContext")
public class ListerAsyncContext {
    @Inject
    private   SubClassWriterErros subClassWriterErros;

    // TODO: 29.11.2023
    public  void runnigAsyncContext   (@NotNull AsyncContext asyncContext,@NotNull ServletContext ЛОГ){

try{
    asyncContext.setTimeout(1500000);
    asyncContext.addListener(new AsyncListener() {
        @Override
        public void onComplete(AsyncEvent asyncEvent) throws IOException {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +" Listener  onComplete onComplete onComplete onComplete onComplete onComplete  "+Thread.currentThread().getName() );
        }

        @Override
        public void onTimeout(AsyncEvent asyncEvent) throws IOException {
            // TODO: 01.12.2023 clear
                asyncEvent.getAsyncContext().complete();

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +"  Listener onTimeout onTimeout onTimeout  onTimeout onTimeout onTimeout "+Thread.currentThread().getName()+
                    " getTimeout " +asyncEvent.getAsyncContext().getTimeout());
        }

        @Override
        public void onError(AsyncEvent asyncEvent) throws IOException {
            // TODO: 01.12.2023 clear
            asyncEvent.getAsyncContext().complete();
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +" Listener  onError onError onError onError onError onError onError onError onError  "+Thread.currentThread().getName());

            Exception exception=new Exception(asyncEvent.getThrowable());
            // TODO: 02.11.2023 запись ошибку
            subClassWriterErros.
                    writingCurrentErrors(exception,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
            // TODO: 01.12.2023  clear
                asyncEvent.getAsyncContext().complete();

        }

        @Override
        public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +"  Listener onStartAsync onStartAsync onStartAsync  onStartAsync onStartAsync onStartAsync onStartAsync onStartAsync onStartAsync"
                    +Thread.currentThread().getName() );
        }
    },asyncContext.getRequest(), asyncContext.getResponse());


      ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
              " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
              " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
              +"  Listener END  "+Thread.currentThread().getName() );
  } catch (Exception e) {
        // TODO: 02.11.2023 запись ошибку
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    }
}
