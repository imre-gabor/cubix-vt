package com.cubixedu.vt.loadtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class LoadtestApplication implements CommandLineRunner {

	private final RestClient.Builder restClientBuilder;
	private RestClient restClient;
	private CountDownLatch latch;

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
		latch = new CountDownLatch(numRequests);

		String uri = args[2];
		this.restClient = restClientBuilder.baseUrl(uri).build();

		ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

		long start = System.currentTimeMillis();
		for(int i=0; i< numThreads; i++) {
			executor.submit(() ->{
				while(true){
					restClient.get().retrieve().toBodilessEntity();
					latch.countDown();
				}
			});
		}
		latch.await();
		long duration = System.currentTimeMillis() - start;
		executor.shutdownNow();
		System.out.println(duration);
	}
}
