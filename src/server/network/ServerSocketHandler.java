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
    model.addPropertyChangeListener("NewMessage", this::onNewMessage);
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
      while (true)
      {
        Request request = (Request) inFromClient.readObject();

        if ("NewMessage".equals(request.getType()))
        {
          model.sendMessage((Message) request.getArgument());
        }
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
      outToClient
          .writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
      /*
      Problem: the list was only being sent with the first element
      Java sends references to objects that have already been serialized, to
      preserve the integrity of object graphs. You should call
      ObjectOutputStream.reset() after each writeObject(). Or use
      ObjectOutputStream.writeUnshared(), but note that it still shares
      referenced objects, i.e. if you try to send a list with both added and
      changed element objects, it will send the new list and new element
      objects, but not the element objects which have been changed.
      SOURCE: https://stackoverflow.com/questions/33490947/java-object-linkedlist-attribute-only-receiving-the-first-element-on-server-sid
       */
      outToClient.reset();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
