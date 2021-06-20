package client.network;

import transferobjects.Request;
import util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  void startClient();
  void listenToServer(Request request);
  void sendMessage(String message);
  void setUsername(String username);
  int getNumberOfConnections();
  void broadcastMessage(String message);
}
