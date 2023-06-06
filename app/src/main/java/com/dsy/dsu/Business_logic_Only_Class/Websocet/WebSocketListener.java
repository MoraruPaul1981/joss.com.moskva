package com.dsy.dsu.Business_logic_Only_Class.Websocet;

public class WebSocketListener {


/////////23.16

  /*  public final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send(message);
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

        }
        public void send(WebSocket webSocket) {
            webSocket.send(finalMessage);
        }
    }

///23.16

///23.16

    client= new OkHttpClient();
    Request request = new Request.Builder().url(Url).build();
    EchoWebSocketListener listener = new EchoWebSocketListener();
    webso = myClient.newWebSocket(request, listener);
client.dispatcher().executorService().shutdown();*/


}
