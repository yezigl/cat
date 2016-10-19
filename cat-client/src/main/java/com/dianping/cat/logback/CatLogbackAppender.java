package com.dianping.cat.logback;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Trace;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.AppenderBase;

/**
 * logback cat
 *
 * @author lidehua
 */
public class CatLogbackAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent event) {
        boolean isTraceMode = Cat.getManager().isTraceMode();
        Level level = event.getLevel();

        if (level.isGreaterOrEqual(Level.ERROR)) {
            logError(event);
        } else if (isTraceMode) {
            logTrace(event);
        }
    }

    private String buildExceptionStack(Throwable exception) {
        if (exception != null) {
            StringWriter writer = new StringWriter(2048);

            exception.printStackTrace(new PrintWriter(writer));
            return writer.toString();
        } else {
            return "";
        }
    }

    private void logError(ILoggingEvent event) {
        ThrowableProxy info = (ThrowableProxy) event.getThrowableProxy();

        if (info != null) {
            Throwable exception = info.getThrowable();
            String message = event.getFormattedMessage();

            if (message != null) {
                Cat.logError(message, exception);
            } else {
                Cat.logError(exception);
            }
        }
    }

    private void logTrace(ILoggingEvent event) {
        String type = "Log4j";
        String name = event.getLevel().toString();
        String data = event.getFormattedMessage();

        ThrowableProxy info = (ThrowableProxy) event.getThrowableProxy();

        if (info != null) {
            data = data + '\n' + buildExceptionStack(info.getThrowable());
        }
        Cat.logTrace(type, name, Trace.SUCCESS, data);
    }
}