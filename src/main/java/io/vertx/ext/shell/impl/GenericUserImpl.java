package io.vertx.ext.shell.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;

/**
 * Created by g on 10/20/16.
 */
public class GenericUserImpl extends AbstractUser {
  private final String username;
  private AuthProvider provider;
  public GenericUserImpl(String username){
    this.username = username;
  }

  @Override
  protected void doIsPermitted(String s, Handler<AsyncResult<Boolean>> handler) {
    handler.handle(Future.succeededFuture(false));
  }

  @Override
  public JsonObject principal() {
    return new JsonObject().put("username",username);
  }

  @Override
  public void setAuthProvider(AuthProvider authProvider) {
    this.provider = authProvider;
  }
}
