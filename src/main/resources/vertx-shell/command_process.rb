require 'vertx-shell/session'
require 'vertx-shell/tty'
require 'vertx-shell/cli_token'
require 'vertx/util/utils.rb'
# Generated from io.vertx.ext.shell.command.CommandProcess
module VertxShell
  #  @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
  class CommandProcess < ::VertxShell::Tty
    # @private
    # @param j_del [::VertxShell::CommandProcess] the java delegate
    def initialize(j_del)
      super(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::VertxShell::CommandProcess] the underlying java delegate
    def j_del
      @j_del
    end
    #  @return the current Vert.x instance
    # @return [::Vertx::Vertx]
    def vertx
      if !block_given?
        return ::Vertx::Util::Utils.safe_create(@j_del.java_method(:vertx, []).call(),::Vertx::Vertx)
      end
      raise ArgumentError, "Invalid arguments when calling vertx()"
    end
    #  @return the unparsed arguments tokens
    # @return [Array<::VertxShell::CliToken>]
    def args_tokens
      if !block_given?
        return @j_del.java_method(:argsTokens, []).call().to_a.map { |elt| ::Vertx::Util::Utils.safe_create(elt,::VertxShell::CliToken) }
      end
      raise ArgumentError, "Invalid arguments when calling args_tokens()"
    end
    #  @return the actual string arguments of the command
    # @return [Array<String>]
    def args
      if !block_given?
        return @j_del.java_method(:args, []).call().to_a.map { |elt| elt }
      end
      raise ArgumentError, "Invalid arguments when calling args()"
    end
    #  @return the shell session
    # @return [::VertxShell::Session]
    def session
      if !block_given?
        return ::Vertx::Util::Utils.safe_create(@j_del.java_method(:session, []).call(),::VertxShell::Session)
      end
      raise ArgumentError, "Invalid arguments when calling session()"
    end
    # @yield 
    # @return [self]
    def set_stdin
      if block_given?
        @j_del.java_method(:setStdin, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(event) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling set_stdin()"
    end
    # @param [String] event 
    # @yield 
    # @return [self]
    def event_handler(event=nil)
      if event.class == String && block_given?
        @j_del.java_method(:eventHandler, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(event,Proc.new { yield })
        return self
      end
      raise ArgumentError, "Invalid arguments when calling event_handler(event)"
    end
    # @param [String] text 
    # @return [self]
    def write(text=nil)
      if text.class == String && !block_given?
        @j_del.java_method(:write, [Java::java.lang.String.java_class]).call(text)
        return self
      end
      raise ArgumentError, "Invalid arguments when calling write(text)"
    end
    #  End the process.
    # @param [Fixnum] status the exit status.
    # @return [void]
    def end(status=nil)
      if !block_given? && status == nil
        return @j_del.java_method(:end, []).call()
      elsif status.class == Fixnum && !block_given?
        return @j_del.java_method(:end, [Java::int.java_class]).call(status)
      end
      raise ArgumentError, "Invalid arguments when calling end(status)"
    end
  end
end
