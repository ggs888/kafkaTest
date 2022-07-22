package examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringBootWithKafkaApplication {

    private final Producer producer;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootWithKafkaApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            for (String arg : args) {
                if ("--producer".equals(arg)) {
                    this.producer.sendMessage("1", "t-shirts");
                    this.producer.sendMessage("2", "t-shirts");
                    this.producer.sendMessage("3", "batteries");
                    this.producer.sendMessage("4", "t-shirts");
                    this.producer.sendMessage("5", "t-shirts");
                    this.producer.sendMessage("6", "book");
                    this.producer.sendMessage("7", "t-shirts");
                    this.producer.sendMessage("8", "batteries");
                    this.producer.sendMessage("9", "gift card");
                    this.producer.sendMessage("10", "t-shirts");
                    Thread.sleep(1000);
                    this.producer.sendMessage("Gyan", "Hello World !");
                } else if ("--consumer".equals(arg)) {
                    MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer");
                    listenerContainer.start();
                    MessageListenerContainer listenerContainer1 = kafkaListenerEndpointRegistry.getListenerContainer("myConsumer2");
                    listenerContainer1.start();
                }
            }
        };
    }

    @Autowired
    SpringBootWithKafkaApplication(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

}