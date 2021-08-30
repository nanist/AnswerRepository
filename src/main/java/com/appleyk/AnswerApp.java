package com.appleyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration// @ComponentScan
public class AnswerApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AnswerApp.class, args);

		System.out.println("\n" +
				" * ┌─────────────────────────────────────────────────────────────┐\n" +
				" * │┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐│\n" +
				" * ││Esc│!1 │@2 │#3 │$4 │%5 │^6 │&7 │*8 │(9 │)0 │_- │+= │|\\ │`~ ││\n" +
				" * │├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴───┤│\n" +
				" * ││ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{[ │}] │ BS  ││\n" +
				" * │├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤│\n" +
				" * ││ Ctrl │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│\" '│ Enter  ││\n" +
				" * │├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────┬───┤│\n" +
				" * ││ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│Shift │Fn ││\n" +
				" * │└─────┬──┴┬──┴──┬┴───┴───┴───┴───┴───┴──┬┴───┴┬──┴┬─────┴───┘│\n" +
				" * │      │Fn │ Alt │         Space         │ Alt │Win│   HHKB   │\n" +
				" * │      └───┴─────┴───────────────────────┴─────┴───┘          │\n" +
				" * └─────────────────────────────────────────────────────────────┘\n" +
				" *                Happy Hacking       auto coding\n");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AnswerApp.class);
	}
}
