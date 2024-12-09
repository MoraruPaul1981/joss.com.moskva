package businesslogic.CallBaksSend;

import businesslogic.ClosedingLOG.ClosetingLOG;
import businesslogic.SubClassWriterErros;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.zip.GZIPOutputStream;


@Named
public   class SuccessSendAndroidBinatyStream implements CallBacksSendsFile {
    @Inject
    private SubClassWriterErros subClassWriterErros;

    @Inject
    ClosetingLOG closetingLOG;
    @Override
    public void successSendAndroidFileStream(HttpServletResponse response, File SendFileToAndroid, ServletContext ЛОГ) {
        try{
            if (  response.isCommitted()==false &&
                    response.getStatus()==HttpServletResponse.SC_OK) {
             GZIPOutputStream БуферДанныеДляОбновлениеПО
                              =new GZIPOutputStream( response.getOutputStream(),2048,true);

                    // TODO: 07.10.2023
                    response.addHeader("stream_size", String.valueOf(SendFileToAndroid.length()));
                    response.addHeader("stream_status", String.valueOf( (  response).getStatus()));
                    response.addHeader("pool", String.valueOf( Thread.currentThread().getName()));
                    response.addHeader("getcharsets", String.valueOf( "8"));
                    response.addHeader("GZIPOutputStream", String.valueOf("true"));


                    if(SendFileToAndroid.isFile() ==true){
                        InputStream targetStreamPO = new FileInputStream(SendFileToAndroid);
                        if (targetStreamPO.available()>0) {
                            // TODO: 19.07.2023  wtiring...
                            БуферДанныеДляОбновлениеПО.write(targetStreamPO.readAllBytes());
                            // TODO: 09.02.2024 exit
                            targetStreamPO.close();
                        }
                        // TODO: 07.10.2023 dont files......
                    }else {
                        byte[] bytesISnull=new String("0").getBytes(StandardCharsets.UTF_8);
                        // TODO: 19.07.2023  wtiring...
                        БуферДанныеДляОбновлениеПО.write(bytesISnull);
                    }
                    // TODO: 06.10.2023 exit clear
                    БуферДанныеДляОбновлениеПО.finish();
                    БуферДанныеДляОбновлениеПО.close();


                    //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В ЛОГ
                    // TODO: 25.09.2023 Clear LOG CONTEXT
                    Object IdUserПред =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");
                    Integer IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(m->Integer.parseInt(m)).findFirst().orElse(0);

                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " Finishing Finishing Finishing Finishing !!!!!!  USER  "+IdUser+"\n"+
                            " SendFileToAndroid " + SendFileToAndroid.length()
                            + "   ((HttpServletResponse) response).getStatus() " +
                            ((HttpServletResponse) response).getStatus()+"POOL CURRENT Thread "+
                            Thread.currentThread().getName()+"response.getBufferSize()"+response.getBufferSize());

                    // TODO: 01.12.2023 clear
                    //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В ЛОГ

                    closetingLOG.startclosetingLOG(ЛОГ);

                    //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В Session



            }else {
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "  SendFileToAndroid.length() " + SendFileToAndroid.length()
                        + "   ((HttpServletResponse) response).getStatus() " +
                        ((HttpServletResponse) response).getStatus());
            }

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " SendFileToAndroid " + SendFileToAndroid.length()
                    + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus()+"POOL CURRENT Thread "+
                    Thread.currentThread().getName()+"response.getBufferSize()"+response.getBufferSize());

        } catch (Exception e) {
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(), "ErrorsLogs/ErrorJbossServletUpdatePO.txt");
    }
    }
}
