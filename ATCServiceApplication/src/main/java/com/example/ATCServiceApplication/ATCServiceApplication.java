package com.example.ATCServiceApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class ATCServiceApplication {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public ATCServiceApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@KafkaListener(topics = "flight-topic")
	public void handleFlightMessage(String message) {
		String surveyMessage = message + " - Survey assigned";
		kafkaTemplate.send("survey-topic", surveyMessage);
	}

	public static void main(String[] args) {
		SpringApplication.run(ATCServiceApplication.class, args);
	}
}
