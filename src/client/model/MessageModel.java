package client.model;

import transferobjects.Message;
import transferobjects.MessageList;
import util.PropertyChangeSubject;

public interface MessageModel extends PropertyChangeSubject
{
  String getUsername();
  Message getMessage();
  void updateMessage(String message);
  MessageList getMessages();
}
