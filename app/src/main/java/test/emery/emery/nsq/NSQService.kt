package test.emery.emery.nsq

import android.app.IntentService
import android.content.Intent
import test.emery.emery.nsq.nsq.NSQConsumer
import test.emery.emery.nsq.nsq.lookup.DefaultNSQLookup




class NSQService(val name : String = "NSQService") : IntentService(name){


    override fun onHandleIntent(intent: Intent?) {

        val lookup = DefaultNSQLookup()
        lookup.addLookupAddress("106.15.181.230", 4161)
        val consumer = NSQConsumer(lookup, "test", "android"){ message ->
            val str = String(message.message)
            val id = String(message.id)
            println("消息：$str")
            println("消息ID：$id")
            //now mark the message as finished.
            message.finished()

            //or you could requeue it, which indicates a failure and puts it back on the queue.
            //message.requeue();
        }

        consumer.start()
    }
}
