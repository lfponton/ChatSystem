package server;

import server.model.DataModelManager;
import server.network.SocketServer;

public class RunServer
{
  public static void main(String[] args)
  {
    server.model.DataModel model = new DataModelManager();
    SocketServer server = new SocketServer(model);
    server.startServer();
  }
}
