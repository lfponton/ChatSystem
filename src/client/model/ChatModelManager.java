package client.model;

import client.network.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel
{
  private PropertyChangeSupport support;
  private Client client;

  public ChatModelManager(Client client) {
    support = new PropertyChangeSupport(this);
    this.client = client;
    client.startClient();
    client.addPropertyChangeListener("NewMessage", this::onNewMessage);
    client.addPropertyChangeListener("Broadcast", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public void sendMessage(String message)
  {
    client.sendMessage(message);
  }

  @Override public void setUsername(String username)
  {
    client.setUsername(username);
  }

  @Override public void broadcastMessage(String message)
  {
    client.broadcastMessage(message);
  }

  @Override public int getNumberOfConnections()
  {
    return client.getNumberOfConnections();
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
