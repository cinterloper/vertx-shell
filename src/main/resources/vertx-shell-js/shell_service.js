/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/** @module vertx-shell-js/shell_service */
var utils = require('vertx-js/util/utils');
var ShellServer = require('vertx-shell-js/shell_server');
var Vertx = require('vertx-js/vertx');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JShellService = io.vertx.ext.shell.ShellService;
var ShellServiceOptions = io.vertx.ext.shell.ShellServiceOptions;

/**
 The shell service, provides a remotely accessible shell available via Telnet or SSH according to the
 @class
*/
var ShellService = function(j_val) {

  var j_shellService = j_val;
  var that = this;

  /**
   Start the shell service, this is an asynchronous start.

   @public
   @param startHandler {function} handler for getting notified when service is started 
   */
  this.start = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_shellService["start()"]();
    }  else if (__args.length === 1 && typeof __args[0] === 'function') {
      j_shellService["start(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        __args[0](null, null);
      } else {
        __args[0](null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {ShellServer} the shell server
   */
  this.server = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return utils.convReturnVertxGen(j_shellService["server()"](), ShellServer);
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Stop the shell service, this is an asynchronous start.

   @public
   @param stopHandler {function} handler for getting notified when service is stopped 
   */
  this.stop = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_shellService["stop()"]();
    }  else if (__args.length === 1 && typeof __args[0] === 'function') {
      j_shellService["stop(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        __args[0](null, null);
      } else {
        __args[0](null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_shellService;
};

/**
 Create a new shell service.

 @memberof module:vertx-shell-js/shell_service
 @param vertx {Vertx} the Vert.x instance 
 @param options {Object} the service config options 
 @return {ShellService} the shell service
 */
ShellService.create = function() {
  var __args = arguments;
  if (__args.length === 1 && typeof __args[0] === 'object' && __args[0]._jdel) {
    return utils.convReturnVertxGen(JShellService["create(io.vertx.core.Vertx)"](__args[0]._jdel), ShellService);
  }else if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && (typeof __args[1] === 'object' && __args[1] != null)) {
    return utils.convReturnVertxGen(JShellService["create(io.vertx.core.Vertx,io.vertx.ext.shell.ShellServiceOptions)"](__args[0]._jdel, __args[1] != null ? new ShellServiceOptions(new JsonObject(JSON.stringify(__args[1]))) : null), ShellService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = ShellService;