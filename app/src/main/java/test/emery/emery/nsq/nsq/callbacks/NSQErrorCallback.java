package test.emery.emery.nsq.nsq.callbacks;


import test.emery.emery.nsq.nsq.exceptions.NSQException;

@FunctionalInterface
public interface NSQErrorCallback {

    void error(NSQException x);
}
