package com.t3h.chatandroid;

import okhttp3.WebSocket;

/**
 * Created by ducnd on 5/10/18.
 */

public class CloseHandler {
    private final WebSocket webSocket;
    public CloseHandler(WebSocket webSocket){
        this.webSocket = webSocket;
    }

    public void close() {
        webSocket.close(1000, "close websocket");
    }
}
