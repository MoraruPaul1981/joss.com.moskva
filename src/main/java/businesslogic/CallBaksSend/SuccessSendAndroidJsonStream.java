package businesslogic.CallBaksSend;

import businesslogic.ClosedingLOG.ClosetingLOG;
import businesslogic.SubClassWriterErros;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.zip.GZIPOutputStream;

@Named
public   class SuccessSendAndroidJsonStream implements CallBacksSendsJson {

    @Inject
    private SubClassWriterErros subClassWriterErros;

    @Inject
    ClosetingLOG closetingLOG;


    @Override
    public void successSendAndroidJsonStream(HttpServletResponse response, byte[] JsonStream, ServletContext ЛОГ) {
        try{
            if (  response.isCommitted() ==false &&
                    response.getStatus()==HttpServletResponse.SC_OK ) {
                try  (GZIPOutputStream gzipOutputStreamJsonJakson=      new GZIPOutputStream(response.getOutputStream(),2048,true);) {
                    // TODO: 01.12.2023 strean for send ansroid
                    // TODO: 18.07.2023 send
                    response.addHeader("stream_size", String.valueOf(JsonStream.length));
                    response.addHeader("stream_status", String.valueOf(response.getStatus()));
                    response.addHeader("pool", String.valueOf( Thread.currentThread().getName()));
                    response.addHeader("getcharsets", String.valueOf( "8"));


                    if(JsonStream.length>0){
                            // TODO: 19.07.2023  wtiring...
                        gzipOutputStreamJsonJakson.write(JsonStream);
                        // TODO: 07.10.2023 dont files......
                    }else {
                        byte[] bytesISnull=new String("0").getBytes(StandardCharsets.UTF_8);
                        // TODO: 19.07.2023  wtiring...
                        gzipOutputStreamJsonJakson.write(bytesISnull);
                    }
                    // TODO: 07.10.2023 exit finich
                    gzipOutputStreamJsonJakson.finish();
                    gzipOutputStreamJsonJakson.close();

                    // TODO: 25.09.2023 Clear LOG CONTEXT
                    Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
                    Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(m->Integer.parseInt(m)).findFirst().orElse(0);

                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " Finishing Finishing Finishing Finishing !!!!!!  USER   "+IdUser+"\n"+
                            " JsonStream.length "  + JsonStream.length
                            + "   ((HttpServletResponse) response).getStatus() " +
                            ((HttpServletResponse) response).getStatus()+"POOL CURRENT Thread "
                            +Thread.currentThread().getName()+"response.getBufferSize()"+response.getBufferSize());


                    //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В ЛОГ

                    closetingLOG.startclosetingLOG(ЛОГ);



                    //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В Session



                } catch (IOException e) {
                    // TODO: 27.04.2023
                    subClassWriterErros.
                            writingCurrentErrors(e,
                                    Thread.currentThread().
                                            getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
                }
            }else {
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " JsonStream " + JsonStream.toString()
                        + "   ((HttpServletResponse) response).getStatus() " +
                        ((HttpServletResponse) response).getStatus());
            }

        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
    }
}
