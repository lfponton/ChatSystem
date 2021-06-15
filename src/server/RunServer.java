package server;

import server.model.ChatDataModel;
import server.model.ChatDataModelManager;
import server.network.SocketServer;

public class RunServer
{
  public static void main(String[] args)
  {
    ChatDataModel model = new ChatDataModelManager();
    SocketServer server = new SocketServer(model);
    server.startServer();
  }
}
