package com.ducnd;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.protocol.Packet;
import com.corundumstudio.socketio.protocol.PacketType;
import com.ducnd.model.ChatObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

@Component
public class SocketManager {
    private static final Logger LOG = LoggerFactory.getLogger(SocketManager.class);
    private SocketIOServer socketIOServer;
    @PostConstruct
    public void inits() {
        LOG.info("Start socket");
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("192.168.100.4");
        config.setPort(9092);
        socketIOServer = new SocketIOServer(config);
        socketIOServer.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                LOG.info("onConnect "+socketIOClient.getRemoteAddress().toString());
            }
        });
        socketIOServer.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                LOG.info("onDisconnect "+((InetSocketAddress)socketIOClient.getRemoteAddress()).getHostName());
                LOG.info("onDisconnect port"+((InetSocketAddress)socketIOClient.getRemoteAddress()).getPort());
            }
        });
        socketIOServer.addEventListener("events", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String contentJson, AckRequest ackRequest) throws Exception {
                LOG.info("message "+contentJson);
                socketIOClient.sendEvent("rep", "Success");
            }
        });
        socketIOServer.start();
    }

    public SocketIOServer getSocketIOServer() {
        return socketIOServer;
    }

}
