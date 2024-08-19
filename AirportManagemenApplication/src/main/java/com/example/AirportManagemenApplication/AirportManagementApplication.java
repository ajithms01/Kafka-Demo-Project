package com.example.AirportManagemenApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class AirportManagementApplication {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public AirportManagementApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@KafkaListener(topics = "survey-topic")
	public void handleSurveyMessage(String message) {
		String notificationMessage = "Notification: " + message;
		kafkaTemplate.send("notification-topic", notificationMessage);
	}

	public static void main(String[] args) {
		SpringApplication.run(AirportManagementApplication.class, args);
	}
}
