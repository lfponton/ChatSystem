package client.views.username;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UsernameController
{
  @FXML private TextField usernameField;
  private UsernameViewModel usernameViewModel;
  private ViewHandler viewHandler;

  public void init(UsernameViewModel usernameViewModel, ViewHandler viewHandler)
  {
    this.usernameViewModel = usernameViewModel;
    this.viewHandler = viewHandler;
    usernameViewModel.createUser(usernameField.getText());
  }

  public void enterUsernameButton(ActionEvent evt) {
    usernameViewModel.createUser(usernameField.getText());
    try
    {
      viewHandler.openView("chat/Chat");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
