package client.model;

import client.network.Client;
import transferobjects.Message;
import transferobjects.MessageList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MessageModelManager implements MessageModel
{
  private PropertyChangeSupport support;
  private Client client;

  public MessageModelManager(Client client) {
    support = new PropertyChangeSupport(this);
    this.client = client;
    client.startClient();
    client.addPropertyChangeListener("updated", this::updated);
  }

  private void updated(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public String getUsername()
  {
    return client.getUsername();
  }

  @Override public Message getMessage()
  {
    return client.getMessage();
  }

  @Override public void updateMessage(String message)
  {
    client.updateMessage(message);
  }

  public MessageList getMessages() {
    return client.getMessages();
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
