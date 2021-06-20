package client.model;

import util.PropertyChangeSubject;

public interface ChatModel extends PropertyChangeSubject
{
  void sendMessage(String message);
  int getNumberOfConnections();
  void setUsername(String username);
  void broadcastMessage(String message);
}
