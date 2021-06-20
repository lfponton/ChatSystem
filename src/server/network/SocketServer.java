package server.network;

import server.model.ChatDataModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
  private ChatDataModel model;

  public SocketServer(ChatDataModel model)
  {
    this.model = model;
  }

  public void startServer()
  {
    try
    {
      ServerSocket serverSocket = new ServerSocket(1234);
      Pool pool = new Pool();

      while (true)
      {
        Socket socket = serverSocket.accept();

        ServerSocketHandler handler = new ServerSocketHandler(socket, model,
            pool);

        pool.addConnection(handler);

        new Thread(handler).start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    // TODO Fix SocketException
  }
}
