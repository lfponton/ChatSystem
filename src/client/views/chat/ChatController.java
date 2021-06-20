package client.views.chat;

import client.core.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ChatController
{
  @FXML private TextField messageField;
  @FXML private Label numberOfConnections;
  @FXML private ListView<String> chatBox;
  private ChatViewModel chatViewModel;
  private ViewHandler viewHandler;
  ObservableList<String> messages;

  public void init(ChatViewModel chatViewModel, ViewHandler viewHandler)
  {
    this.chatViewModel = chatViewModel;
    this.viewHandler = viewHandler;
    messageField.textProperty().bindBidirectional(chatViewModel.getMessage());
    messages = FXCollections.observableArrayList(new ArrayList<>());
    chatBox.setItems(messages);
    chatViewModel.loadMessages().addListener(
        (ListChangeListener<? super String>) observable -> onNewMessage(
            observable.getList()));

  }

  private void onNewMessage(ObservableList<? extends String> list)
  {
    chatBox.setItems((ObservableList<String>) list);
  }

  public void sendMessageButton(ActionEvent evt)
  {
    chatViewModel.sendMessage();
    messageField.clear();
  }

  public void numberOfConnections(ActionEvent evt)
  {
    numberOfConnections
        .setText(String.valueOf(chatViewModel.numberOfConnections()));
  }
}
