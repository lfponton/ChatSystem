package client.model;

import transferobjects.Message;
import util.PropertyChangeSubject;

import java.util.List;

public interface ChatModel extends PropertyChangeSubject
{
  void sendMessage(String message);
  int getNumberOfConnections();
  void setUsername(String username);
}
