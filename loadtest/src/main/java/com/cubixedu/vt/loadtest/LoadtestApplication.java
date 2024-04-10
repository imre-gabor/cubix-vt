package com.cubixedu.vt.loadtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class LoadtestApplication implements CommandLineRunner {

	private final RestClient.Builder restClientBuilder;
	private RestClient restClient;

    public LoadtestApplication(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
    }

    public static void main(String[] args) {
		SpringApplication.run(LoadtestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int numThreads = Integer.parseInt(args[0]);
		int numRequests = Integer.parseInt(args[1]);
		String uri = args[2];
		restClient = restClientBuilder.baseUrl(uri).build();
	}
}
