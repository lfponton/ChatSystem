package client.core;

import client.model.MessageModel;
import client.model.MessageModelManager;

public class ModelFactory
{
  private MessageModel model;
  private ClientFactory clientFactory;

  public ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }

  public MessageModel getDataModel() {
    if (model == null) {
      model = new MessageModelManager(clientFactory.getClient());
    }
    return model;
  }
}
