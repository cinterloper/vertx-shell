package io.vertx.ext.shell.term;

import io.termd.core.tty.IdentifyableTtyConnection;
import io.vertx.ext.auth.User;

/**
 * Created by g on 10/16/16.
 */
public interface VertxIdentifyableTtyConnection extends IdentifyableTtyConnection {
  public User getUser();
}
