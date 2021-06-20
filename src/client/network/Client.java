package client.network;

import transferobjects.Request;
import util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  void startClient();
  void listenToServer(Request request);
  void sendMessage(String str);
  void setUsername(String str);
  int getNumberOfConnections();
}
