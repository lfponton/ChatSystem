package client.network;

import transferobjects.Message;
import transferobjects.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private Socket socket;
  private Client client;
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;

  public ClientSocketHandler(Socket socket, Client client)
  {
    this.socket = socket;
    this.client = client;

    try {
      outToServer = new ObjectOutputStream(socket.getOutputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    try
    {
      while(true)
      {
        Request request = (Request) inFromServer.readObject();
        client.listenToServer(request);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }


  public void request(Message arg, String type)
  {
    try
    {
      outToServer.writeObject(new Request(type, arg));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
