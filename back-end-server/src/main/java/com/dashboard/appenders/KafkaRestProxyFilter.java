package com.dashboard.appenders;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Hamza.Ouni
 */
public class KafkaRestProxyFilter extends Filter<ILoggingEvent> {

    private String basePackage;
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel().levelStr.equals(Level.ERROR.levelStr) && event.getThreadName()!=null && event.getLoggerName().contains(basePackage))
            return FilterReply.ACCEPT;
        else
            return FilterReply.DENY;
        }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}