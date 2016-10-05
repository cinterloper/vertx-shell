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

package io.vertx.ext.shell.term.impl;

import io.termd.core.readline.Keymap;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.User;
import io.vertx.ext.shell.term.SockJSTermHandler;
import io.vertx.ext.shell.term.Term;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;

import java.nio.charset.Charset;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class SockJSTermHandlerImpl implements SockJSTermHandler {

  final Charset charset;
  final Vertx vertx;
  private Handler<Term> termHandler;
  private final Keymap keymap;

  public SockJSTermHandlerImpl(Vertx vertx, Charset charset, Keymap keymap) {
    this.charset = charset;
    this.vertx = vertx;
    this.keymap = keymap;
  }

  @Override
  public SockJSTermHandler termHandler(Handler<Term> handler) {
    termHandler = handler;
    return this;
  }

  @Override
  public void handle(SockJSSocket socket) {
    User user = socket.webUser();
    if (termHandler != null) {
      SockJSTtyConnection conn = new SockJSTtyConnection(charset, vertx.getOrCreateContext(), socket);
      socket.handler(buf -> conn.writeToDecoder(buf.toString()));
      socket.endHandler(v -> {
        Consumer<Void> closeHandler = conn.getCloseHandler();
        if (closeHandler != null) {
          closeHandler.accept(null);
        }
      });
      termHandler.handle(new TermImpl(vertx, keymap, conn,user));
    } else {
      socket.close();
    }
  }
}
