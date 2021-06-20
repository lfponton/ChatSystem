package server.model;

import transferobjects.Message;
import util.PropertyChangeSubject;

public interface ChatDataModel extends PropertyChangeSubject
{
  void sendMessage(Message message);

}
