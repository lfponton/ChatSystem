package server.network;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class Pool
{
  private List<ServerSocketHandler> connections = new ArrayList<>();

  public synchronized void addConnection(ServerSocketHandler handler)
  {
    connections.add(handler);
  }

  public synchronized void broadcast(PropertyChangeEvent evt) {
    for (ServerSocketHandler connection : connections)
      connection.onUpdated(evt);
  }

  public void removeConnection(ServerSocketHandler handler)
  {
    connections.remove(handler);
  }
}
