package client.network;

import transferobjects.Message;
import transferobjects.Request;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;

public class SocketClient implements Client
{
  private PropertyChangeSupport support;
  private ClientSocketHandler handler;
  private String username;

  public SocketClient()
  {
    support = new PropertyChangeSupport(this);
  }


  @Override public void startClient()
  {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      handler = new ClientSocketHandler(socket, this);
      new Thread(handler).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void listenToServer(Request request)
  {
    support.firePropertyChange(request.getType(), null, request.getArgument());
    System.out.println("Property fired to model: Socket client: " + request.getArgument() + " " + request.getType() + " " + request.getArgument().getClass());
  }

  public int getNumberOfConnections() {

    return 0;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public void sendMessage(String str) {
      handler.request(new Message(username, str), "NewMessage");
  }


  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
