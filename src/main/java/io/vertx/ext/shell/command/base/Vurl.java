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

package io.vertx.ext.shell.command.base;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.cli.annotations.Argument;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.shell.command.AnnotatedCommand;
import io.vertx.ext.shell.command.CommandProcess;

import java.util.List;

/**
 * @author <a href="mailto:grant@iowntheinter.net">Grant Haywood</a>
 */
@Name("vurl")
@Summary("perform an http request")
public class vurl extends AnnotatedCommand {

    private String method;
    private String host;
    private List<String> keys;
    private String url;

    @Argument(index = 0, argName = "HOST")
    @Description("the name of the http method")
    public void setHost(String host) {
        this.host = host;
    }

    @Argument(index = 1, argName = "METHOD")
    @Description("the name of the http method")
    public void setMethod(String method) {
        this.method = method;
    }

    @Argument(index = 2, argName = "URL", required = true)
    @Description("the URL to get")
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void process(CommandProcess process) {

        Vertx vertx = process.vertx();
        HttpClient client = vertx.createHttpClient();

        client.getNow(host, url, resp -> {
            resp.bodyHandler(new Handler<Buffer>() {
                @Override
                public void handle(Buffer buffer) {
                    process.write(buffer.toString());
                    process.end();
                }
            });
        });


    }
}

