package client.core;

import client.views.chat.ChatController;
import client.views.username.UsernameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private Scene usernameScene;
  private Scene chatScene;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.stage = stage;
    this.viewModelFactory = viewModelFactory;
  }

  public void start() throws IOException
  {
    openView("username/Username");
  }

  public void openView(String viewToOpen) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;

    loader.setLocation(getClass().getResource("../views/" + viewToOpen + "View.fxml"));
    root = loader.load();
    if ("chat/Chat".equals(viewToOpen)) {
      ChatController controller = loader.getController();
      controller.init(viewModelFactory.getChatViewModel(), this);
      stage.setTitle("Chat");
      chatScene = new Scene(root);
      stage.setScene(chatScene);
    }
    if ("username/Username".equals(viewToOpen)) {
      UsernameController controller = loader.getController();
      controller.init(viewModelFactory.getUsernameViewModel(), this);
      stage.setTitle("username/Username");
      usernameScene = new Scene(root);
      stage.setScene(usernameScene);
    }
    stage.show();
  }
}
