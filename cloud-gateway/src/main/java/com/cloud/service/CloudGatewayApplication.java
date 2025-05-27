package com.cloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class CloudGatewayApplication {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        log.info(".-\"\"\"-.           .-\"\"\"-.\n" +
                "         /       \\         /       \\\n" +
                "        |         |       |         |\n" +
                "        |  .----. |       | .----.  |\n" +
                "        |  |    | |  _    | |    |  |\n" +
                "        |  |佛  | | /.\\   | |永  |  |\n" +
                "        |  |    | |/.-.\\  | |    |  |\n" +
                "        |  |不  | |\\.-./  | |不  |  |\n" +
                "        |  |    | | '-^-' | |    |  |\n" +
                "        |  |宕  | |       | |宕  |  |\n" +
                "        |  |    | |       | |    |  |\n" +
                "        |  |机  | |       | |机  |  |\n" +
                "        |  '----' |       | '----'  |\n" +
                "        |         |       |         |\n" +
                "         \\       /         \\       /\n" +
                "          '-...-'           '-...-'\n" +
                "          /     \\           /     \\\n" +
                "         |       |         |       |\n" +
                "         |  ┌─┐  |         |  ┌─┐  |\n" +
                "         |  │ │  |         |  │ │  |\n" +
                "         |  │ │  |         |  │ │  |\n" +
                "         └──┴─┴──┘         └──┴─┴──┘    ");

        SpringApplication.run(CloudGatewayApplication.class, args);
    }
}