package client.network;

import transferobjects.Message;
import transferobjects.MessageList;
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
  private Message message;
  private MessageList messages;

  public SocketClient()
  {
    support = new PropertyChangeSupport(this);
    messages = new MessageList();
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
  }


  @Override public String getUsername()
  {
    return username;
  }

  @Override public Message getMessage()
  {
    return message;
  }

  public MessageList getMessages()
  {
    return messages;
  }

  @Override public void updateMessage(String message)
  {
    Message lastMessage = new Message(username, message);
    messages.addMessage(lastMessage);
    this.message = lastMessage;
    support.firePropertyChange("updated", null, this.message);
  }

  public String sendMessage(String str) {
    try
    {
      Request response = handler.request(str, "Message");
      return (String) response.getArgument();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return str;
  }

}