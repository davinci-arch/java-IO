package org.example.layouts;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Locale;

@Plugin(name = "CustomConsoleLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE)
public class CustomConsoleLayout extends AbstractStringLayout {


    protected CustomConsoleLayout(Charset charset) {
        super(charset);
    }

    @Override
    public String toSerializable(LogEvent event) {
        StringBuilder sb = new StringBuilder("");
        LocalDate date = LocalDate.now();

        sb.append(event.getSource().getLineNumber()).append(" ")
                .append(date.getYear()).append("-").append(date.getMonth()).append("-").append(date.getDayOfMonth())
                .append(" ").append(event.getThreadName())
                .append(" ").append(event.getLoggerName())
                .append(" ").append(event.getSource().getMethodName())
                .append(" ").append(event.getMessage().getFormattedMessage());

        return sb.toString();
    }


    @PluginFactory
    public static CustomConsoleLayout getLayoutForConsole(@PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
        return new CustomConsoleLayout(charset);
    }
}
