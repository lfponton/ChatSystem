package server.model;

import transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ChatDataModelManager implements ChatDataModel
{
  private PropertyChangeSupport support;
  private List<String> messages;

  public ChatDataModelManager() {
    support = new PropertyChangeSupport(this);
    messages = new ArrayList<>();
  }

  @Override public void sendMessage(Message message)
  {
    messages.add(message.toString());
    support.firePropertyChange("NewMessage", null, messages);
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
