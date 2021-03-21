package server.network;

import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Socket socket;
  private server.model.DataModel model;
  private ObjectInputStream inFromClient;
  private ObjectOutputStream outToClient;
  private Pool pool;

  public ServerSocketHandler(Socket socket, server.model.DataModel model, Pool pool)
  {
    this.socket = socket;
    this.model = model;
    this.pool = pool;

    try
    {
      inFromClient = new ObjectInputStream(socket.getInputStream());
      outToClient = new ObjectOutputStream(socket.getOutputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    try
    {
      Request request = (Request) inFromClient.readObject();

      if ("Listener".equals(request.getType()))
      {
        model.addPropertyChangeListener("updated", this::onUpdated);
      }
      else if ("Message".equals(request.getType()))
      {
        Message message = model.getMessage();
        outToClient.writeObject(new Request("Message", message));
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  public void onUpdated(PropertyChangeEvent evt)
  {
    try
    {
      outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
