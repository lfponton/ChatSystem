package client.network;

import transferobjects.Message;
import transferobjects.MessageList;
import transferobjects.Request;
import util.PropertyChangeSubject;

import java.util.List;

public interface Client extends PropertyChangeSubject
{
  void startClient();
  void listenToServer(Request request);
  void sendMessage(String str);
  void setUsername(String str);
  int getNumberOfConnections();
}
