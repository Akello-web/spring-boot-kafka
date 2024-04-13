package kz.akkl.kafkaproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.akkl.kafkaproject.config.KafkaConfigProps;
import kz.akkl.kafkaproject.domain.CustomerVisitEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class KafkaProjectApplication {

	@Autowired
	public ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProjectApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(final KafkaTemplate<String, String> kafkaTemplate,
									final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
		final CustomerVisitEvent event = CustomerVisitEvent.builder()
						.customerId(UUID.randomUUID().toString())
						.dateTime(LocalDateTime.now())
						.build();

		final String payload = objectMapper.writeValueAsString(event);

		return args -> {
			kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
		};
	}

}
