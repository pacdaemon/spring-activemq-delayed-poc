package poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import poc.models.Lote;

import javax.jms.JMSException;
import javax.jms.Message;

@SpringBootApplication
@EnableJms
@ComponentScan
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        System.out.println("Encolando lote para proceso");
        jmsTemplate.convertAndSend("lotes", new Lote(379), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setIntProperty("AMQ_SCHEDULED_DELAY", 100000);
                return message;
            }
        });

    }
}
