package org.example.pubsub

import com.google.pubsub.v1.PubsubMessage
import io.smallrye.mutiny.Uni
import io.smallrye.reactive.messaging.gcp.pubsub.PubSubMessage
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class MyChannelPubSub {

    private val log: Logger = Logger.getLogger(javaClass)

    @Inject
    @Channel("mychannel-out")
    private lateinit var emitter: Emitter<com.google.pubsub.v1.PubsubMessage>

    fun publish(content: String): Uni<Void> {
        val message = createPubSubMessage(content)
        return Uni.createFrom().item(emitter.send(message)).replaceWithVoid()
    }

    @Incoming(value = "mychannel-in")
    fun subscribe(reactiveMessage: PubSubMessage): Uni<Void> {
        log.info("Received Message: $reactiveMessage")
        return Uni.createFrom().item(reactiveMessage)
            .onItem().invoke { _ -> reactiveMessage.ack() }
            .replaceWithVoid()
    }


    private fun createPubSubMessage(
        content: String, attributes: Map<String, String>? = null
    ): com.google.pubsub.v1.PubsubMessage {
        val builder: PubsubMessage.Builder = PubsubMessage.newBuilder()
        builder.data = com.google.protobuf.ByteString.copyFromUtf8(content)
        if (attributes?.isNotEmpty() == true) {
            builder.putAllAttributes(attributes)
        }
        return builder.build()
    }

}