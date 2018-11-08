package test.emery.emery.nsq.nsq.lookup;


import java.util.Set;

import test.emery.emery.nsq.nsq.ServerAddress;

public interface NSQLookup {
    Set<ServerAddress> lookup(String topic);

    void addLookupAddress(String addr, int port);
}
