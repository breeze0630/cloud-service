package com.cloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ElasticsearchApplication {
    public static void main(String[] args) {

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

        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}