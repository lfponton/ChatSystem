package server.network;

import transferobjects.Message;

import java.util.ArrayList;
import java.util.List;

public class Pool
{
  private List<ServerSocketHandler> connections = new ArrayList<>();

  public synchronized void addConnection(ServerSocketHandler connection)
  {
    connections.add(connection);
  }

  public synchronized void broadcast(List<String> messages) {
    for (ServerSocketHandler connection : connections)
    {
      connection.broadcastMessage(messages);
    }

  }

  public void removeConnection(ServerSocketHandler connection)
  {
    connections.remove(connection);
  }
}
