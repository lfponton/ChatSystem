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

      while (true)
      {
      Pool pool = new Pool();
      System.out.println("Server ready.");

      Socket socket = serverSocket.accept();

      System.out.println("Connected to server.");

      ServerSocketHandler handler = new ServerSocketHandler(socket, model, pool);

      new Thread(handler).start();
      pool.addConnection(handler);
    }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    // TODO Fix SocketException
  }
}
