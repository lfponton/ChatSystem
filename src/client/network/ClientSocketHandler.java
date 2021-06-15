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
  }

  @Override public void run()
  {
    try
    {
      inFromServer = new ObjectInputStream(socket.getInputStream());
      outToServer = new ObjectOutputStream(socket.getOutputStream());


      outToServer.writeObject(new Request("Update", null));

      while(true)
      {
        client.listenToServer(receivedRequest());
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public Request receivedRequest()
  {
    Request request = null;
    try
    {
      request = (Request) inFromServer.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return request;
  }

  public Request request(Message arg, String type)
      throws IOException, ClassNotFoundException
  {
    outToServer.writeObject(new Request(type, arg));
    return (Request) inFromServer.readObject();
  }
}
