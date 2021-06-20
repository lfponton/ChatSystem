package server.model;

import transferobjects.Message;
import util.PropertyChangeSubject;

import java.util.List;

public interface ChatDataModel extends PropertyChangeSubject
{
  void sendMessage(Message message);
  void addMessage(Message message);
  List<String> getMessages();
}
