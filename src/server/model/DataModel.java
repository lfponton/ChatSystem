package server.model;

import transferobjects.Message;
import util.PropertyChangeSubject;

public interface DataModel extends PropertyChangeSubject
{
  String getUsername();
  Message getMessage();
}
