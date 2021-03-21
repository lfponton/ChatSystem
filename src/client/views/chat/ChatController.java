package client.views.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController
{
  @FXML private TextField messageField;
  @FXML private TextArea messagesArea;
  private ChatViewModel viewModel;
  public void init(ChatViewModel viewModel)
  {
    this.viewModel = viewModel;

    messagesArea.textProperty().bind(viewModel.messageProperty());

  }

  public void sendMessageButton(ActionEvent evt)
  {
    viewModel.updateMessage(messageField.getText());
  }
}
