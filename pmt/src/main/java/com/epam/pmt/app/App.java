package com.epam.pmt.app;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.pmt.exception.UserException;
import com.epam.pmt.ui.Command;
import com.epam.pmt.ui.ObjectPool;

@Configuration
@ComponentScan(basePackages = { "com.epam.pmt.*" })
public class App {

	private Scanner cin;

	private int choice;

	private ApplicationContext context;

	private ObjectPool objectPool;

	private static final Logger LOGGER = LogManager.getLogger(App.class);


	public App() {
		cin = new Scanner(System.in);
	}

	public void mainMenu() {
		while (choice != 9) {
			LOGGER.info("----------------------------------- ");
			LOGGER.info("          Main Menu                 ");
			LOGGER.info("     1.Create Password              ");
			LOGGER.info("     2.Read Password by Account     ");
			LOGGER.info("     3.Display All Group            ");
			LOGGER.info("     4.List All the Account by Group");
			LOGGER.info("     5.Delete Account               ");
			LOGGER.info("     6.Modify Password by Account   ");
			LOGGER.info("     7.Modify Group                 ");
			LOGGER.info("     8.Delete Group                 ");
			LOGGER.info("     9.Exit                         ");
			choice = cin.nextInt();
			if (choice == 9)
				break;
			cin.nextLine();
			try {
				Command command = (Command) context.getBean(objectPool.getAccountBean(choice));
				command.execute();
			} catch (UserException e) {
				LOGGER.error(e.getMessage());
				mainMenu();
			}
		}
	}

	public void loginMenu() {
		LOGGER.info("Welcome To PMT ");
		LOGGER.info("----------------");
		LOGGER.info("  MENU ");
		LOGGER.info(" 1.Register");
		LOGGER.info(" 2.Login");
		choice = cin.nextInt();
		cin.nextLine();
		try {
			Command command = (Command) context.getBean(objectPool.getUserBean(choice));
			command.execute();
			mainMenu();
		} catch (UserException e) {
			LOGGER.error(e.getMessage());
			loginMenu();
		}
	}

	public static void main(String[] args) {
		
		App app = new App();
		app.context = new AnnotationConfigApplicationContext(App.class);
		app.objectPool = app.context.getBean(ObjectPool.class);
		app.loginMenu();
	}
}
