package test.emery.emery.nsq.nsq.callbacks;


import test.emery.emery.nsq.nsq.NSQMessage;

@FunctionalInterface
public interface NSQMessageCallback {

	public void message(NSQMessage message);
}
