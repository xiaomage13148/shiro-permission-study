package com.xiaoma.sys.druid;

public class CustomStdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger{

    @Override
    public void logText(String text) {
        System.out.println("sql:" + text);
    }
}