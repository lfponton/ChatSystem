package client.core;

import client.views.chat.ChatViewModel;
import client.views.username.UsernameViewModel;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private UsernameViewModel usernameViewModel;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    chatViewModel = new ChatViewModel(modelFactory.getModel());
    usernameViewModel = new UsernameViewModel(modelFactory.getModel());
  }

  public ChatViewModel getChatViewModel() {
    return chatViewModel;
  }

  public UsernameViewModel getUsernameViewModel()
  {
    return usernameViewModel;
  }
}
