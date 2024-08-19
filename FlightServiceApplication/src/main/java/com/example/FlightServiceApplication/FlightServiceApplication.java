package com.example.FlightServiceApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/flights")
public class FlightServiceApplication {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public FlightServiceApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping
	public void postFlight(@RequestParam String from, @RequestParam String to, @RequestParam String time) {
		String message = String.format("Flight from %s to %s at %s", from, to, time);
		kafkaTemplate.send("flight-topic", message);
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightServiceApplication.class, args);
	}
}