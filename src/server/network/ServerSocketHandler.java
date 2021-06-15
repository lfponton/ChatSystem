package server.network;

import server.model.ChatDataModel;
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
  private ChatDataModel model;
  private ObjectInputStream inFromClient;
  private ObjectOutputStream outToClient;
  private Pool pool;

  public ServerSocketHandler(Socket socket, ChatDataModel model, Pool pool)
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

      if ("Update".equals(request.getType()))
      {
        model.addPropertyChangeListener("Update", this::onUpdate);
        model.getMessages();
      }
      else if ("NewMessage".equals(request.getType()))
      {
        model.addPropertyChangeListener("NewMessage", this::onNewMessage);
        model.sendMessage((Message) request.getArgument());
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private void onNewMessage(PropertyChangeEvent evt)
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

  public void onUpdate(PropertyChangeEvent evt)
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
