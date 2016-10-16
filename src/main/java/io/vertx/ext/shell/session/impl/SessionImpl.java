/*
 * Copyright 2015 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 *
 *
 * Copyright (c) 2015 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 *     The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 *     The Apache License v2.0 is available at
 *     http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 */

package io.vertx.ext.shell.session.impl;

import io.vertx.ext.auth.User;
import io.vertx.ext.shell.session.Session;
import io.vertx.ext.shell.impl.GenericUserImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class SessionImpl implements Session {

  private Map<String, Object> data = new HashMap<>();
  private final User user;

  public SessionImpl(User u){    this.user = u;  }
  public SessionImpl(){    this.user = new GenericUserImpl("Anonymous");  }

  @Override
  public Session put(String key, Object obj) {
    if (obj == null) {
      data.remove(key);
    } else {
      data.put(key, obj);
    }
    return this;
  }
  public User getUser() {
    return user;
  }

  @Override
  public <T> T get(String key) {
    return (T) data.get(key);
  }

  @Override
  public <T> T remove(String key) {
    return (T) data.remove(key);
  }


}
