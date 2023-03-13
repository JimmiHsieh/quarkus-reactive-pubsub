package org.example

import io.smallrye.mutiny.Uni
import org.example.pubsub.MyChannelPubSub
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource(private val myChannelPubSub: MyChannelPubSub) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun publish(): Uni<String> {
        val content = "Hello from RESTEasy Reactive"
        return myChannelPubSub.publish(content).replaceWith(content)
    }
}