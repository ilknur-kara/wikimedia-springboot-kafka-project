package com.black.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    //To log the messages ve have created a logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    //We use kafka template to send messages to the Kafka broker.
    private KafkaTemplate<String, String> kafkaTemplate;

    //Spring automatically inject this dependency whenever it finds a single parameterized constructor.
    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Read real-time wikimedia stream data.
    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recentchange";

        //to read real time stream data from wikimedia, we use event source
        EventHandler eventHandler = new WikimediaChangeHandler(kafkaTemplate, topic);
        //define event source url
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        //create an event source that connects to the wikimedia source and reads all of the events data.
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource source = builder.build();
        source.start();

        TimeUnit.MINUTES.sleep(10);

    }
}
