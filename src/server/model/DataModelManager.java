package server.model;

import transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModelManager implements DataModel
{
  private String username;
  private Message message;
  private PropertyChangeSupport support;

  public DataModelManager() {
    support = new PropertyChangeSupport(this);
  }
  @Override public String getUsername()
  {
    return username;
  }

  @Override public Message getMessage()
  {
    return message;
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
