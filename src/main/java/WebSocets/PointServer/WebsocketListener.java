package WebSocets.PointServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.Future;

@ApplicationScoped
@ServerEndpoint("/sousavtodorwebsocket")
public class WebsocketListener {

    @Resource
    ManagedScheduledExecutorService managedScheduledExecutorService;

    String PublicID=new String();

    private   Logger LOGGER = LoggerFactory.getLogger(WebsocketListener.class);

    @OnOpen
    public void onOpenServer(Session session) {
        try{
        byte[] bytes= session.getId().toString().getBytes(StandardCharsets.UTF_8);
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        session.getAsyncRemote().sendBinary(byteBuffer);
        LOGGER.info("Message from {} is: {}", session.getId()  );

        LOGGER.info("Message from {} is: {} managedScheduledExecutorService  PublicID "+"\n"
                +new Date().toLocaleString(), session.getId(), PublicID);

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }
    }

    @OnMessage
    public void onMessageBufferServer(ByteBuffer byteBuffer, Session session) throws IOException {
                try{
                byte[] bytes;
                if(byteBuffer.hasArray()) {
                    bytes = byteBuffer.array();
                } else {
                    bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                }
                PublicID = new String(bytes, StandardCharsets.UTF_8);
                LOGGER.info("Message from {} is: {} managedScheduledExecutorService  PublicID "+"\n"
                        +new Date().toLocaleString(), session.getId(), PublicID);

                // TODO: 13.12.2023 Отправляем Обратно ПУбличный ID
                ByteBuffer byteBufferSend = ByteBuffer.wrap(PublicID.getBytes(StandardCharsets.UTF_8));

                Future<Void> futureSendAndroidSocet=session.getAsyncRemote().sendBinary(byteBufferSend);

                LOGGER.info("Message from {} is: {} managedScheduledExecutorService  PublicID "+"\n"
                        +new Date().toLocaleString(), session.getId(), PublicID+ " futureSendAndroidSocet " +futureSendAndroidSocet.isDone());

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            }

    }


    @OnClose
    public void onCloseServer(Session session) {
        try{
        LOGGER.info("Message from {} is: {}", session.getId()  );
        managedScheduledExecutorService.shutdown();

        LOGGER.info("Message from {} is: {} managedScheduledExecutorService  PublicID "+"\n"
                +new Date().toLocaleString(), session.getId(), PublicID);

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }

    }

    @OnError
    public void onErrorServer(Session session, Throwable throwable) {
        try{
        throwable.printStackTrace();
        managedScheduledExecutorService.shutdown();
        LOGGER.error("Error on open web socket", throwable.getMessage());
        LOGGER.info("Message from {} is: {} managedScheduledExecutorService  PublicID "+"\n"
                +new Date().toLocaleString(), session.getId(), PublicID);

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }
    }




}