package com.gerson.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.statemachine.StateMachine;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class StatemachineApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<OrderStates, OrderEvents> stateMachine;
	public static void main(String[] args) {
		SpringApplication.run(StatemachineApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//		System.out.println("Iniciando máquina de estados...");
//		stateMachine.sendEvent(OrderEvents.CONFIRMED_PAYMENT);
//		stateMachine.sendEvent(OrderEvents.INVOICE_ISSUED);
//		stateMachine.sendEvent(OrderEvents.SHIP);
////		stateMachine.sendEvent(OrderEvents.DELIVER);
//		System.out.println("Máquina de estados finalizada");
//	}
	@Override
	public void run(String... args) {
		System.out.println("Iniciando máquina de estados...");
		stateMachine.sendEvent(OrderEvents.CONFIRMED_PAYMENT);
		stateMachine.sendEvent(new Message<OrderEvents>() {
			@Override
			public OrderEvents getPayload() {
				return OrderEvents.INVOICE_ISSUED;
			}
			@Override
			public MessageHeaders getHeaders() {
				final Map<String, Object> params = new HashMap<>();
				final LocalDate saturday = LocalDate.of(2019, 2, 2);
				params.put("day", saturday);
				return new MessageHeaders(params);
			}
		});
		System.out.println("Máquina de estados finalizada");
	}
}
