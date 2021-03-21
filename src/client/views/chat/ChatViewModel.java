package client.views.chat;

import client.model.MessageModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;

public class ChatViewModel
{
  private StringProperty username;
  private StringProperty messages;
  private MessageModel model;

  public ChatViewModel(MessageModel model)
  {
    this.model = model;
    username = new SimpleStringProperty();
    messages = new SimpleStringProperty();
    ((PropertyChangeSubject) model).addPropertyChangeListener((PropertyChangeEvent evt) -> this.updated());
  }

  public void updated()
  {
    Platform.runLater(() -> {
      username.setValue(model.getUsername());
      messages.setValue(model.getMessages().toString());
    });
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public StringProperty messageProperty()
  {
    return messages;
  }

  public void updateMessage(String message)
  {
    model.updateMessage(message);
  }
}
