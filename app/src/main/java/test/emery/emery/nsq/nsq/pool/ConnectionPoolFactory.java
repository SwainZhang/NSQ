package test.emery.emery.nsq.nsq.pool;


import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import io.netty.channel.ChannelFuture;
import test.emery.emery.nsq.nsq.Connection;
import test.emery.emery.nsq.nsq.NSQCommand;
import test.emery.emery.nsq.nsq.NSQConfig;
import test.emery.emery.nsq.nsq.ServerAddress;

public class ConnectionPoolFactory extends BaseKeyedPooledObjectFactory<ServerAddress, Connection> {
    private NSQConfig config;


    public ConnectionPoolFactory(NSQConfig config) {
        this.config = config;
    }

    @Override
    public Connection create(final ServerAddress serverAddress) throws Exception {
        return new Connection(serverAddress, config);
    }


    @Override
    public PooledObject<Connection> wrap(final Connection con) {
        return new DefaultPooledObject<>(con);
    }

    @Override
    public boolean validateObject(final ServerAddress key, final PooledObject<Connection> p) {
        ChannelFuture command = p.getObject().command(NSQCommand.nop());
        return command.awaitUninterruptibly().isSuccess();
    }

    @Override
    public void destroyObject(final ServerAddress key, final PooledObject<Connection> p) throws Exception {
        p.getObject().close();
    }
}
