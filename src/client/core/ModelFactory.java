package client.core;

import client.model.ChatModel;
import client.model.ChatModelManager;

public class ModelFactory
{
  private ChatModel model;
  private ClientFactory clientFactory;

  public ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }

  public ChatModel getModel() {
    if (model == null) {
      model = new ChatModelManager(clientFactory.getClient());
    }
    return model;
  }
}
