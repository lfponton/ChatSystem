package client.network;

import transferobjects.Message;
import transferobjects.MessageList;
import transferobjects.Request;
import util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject
{
  void startClient();
  void listenToServer(Request request);
  String getUsername();
  Message getMessage();
  void updateMessage(String message);
  MessageList getMessages();
}
