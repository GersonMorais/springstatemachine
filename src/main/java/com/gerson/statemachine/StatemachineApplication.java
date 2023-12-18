package com.gerson.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StatemachineApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<OrderStates, OrderEvents> stateMachine;
	public static void main(String[] args) {
		SpringApplication.run(StatemachineApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Iniciando máquina de estados...");
		stateMachine.sendEvent(OrderEvents.CONFIRMED_PAYMENT);
		stateMachine.sendEvent(OrderEvents.INVOICE_ISSUED);
		stateMachine.sendEvent(OrderEvents.SHIP);
		stateMachine.sendEvent(OrderEvents.DELIVER);
		System.out.println("Máquina de estados finalizada");
	}
}
