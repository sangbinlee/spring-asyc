package topas.service.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import topas.User;

@Slf4j
@Service
public class AsyncService {

//    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

	private final RestTemplate restTemplate;

	public AsyncService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

//	@Async
//	public void onAsync() {
//		// TODO Auto-generated method stub
//		
//	}
//	public void onSync() {
//		// TODO Auto-generated method stub
//
//	}

	@Async
	public CompletableFuture<User> onAsync(String user) throws InterruptedException {
		log.info("[ input ] Execute method asynchronously " + Thread.currentThread().getName());
		log.info("[ input ] Looking up " + user);

		String url = String.format("https://api.github.com/users/%s", user);
		User results = restTemplate.getForObject(url, User.class);
		// Artificial delay of 1s for demonstration purposes
		Thread.sleep(1000L);
		return CompletableFuture.completedFuture(results);
	}

	@Async("taskExecutor100")
	public CompletableFuture<Boolean> taskExecutor100() throws Exception {

		Boolean results = true;
		log.info("[ taskExecutor100 ] called " + 100);
		log.info("Execute method asynchronously. "
	    	      + Thread.currentThread().getName());
		 
		Thread.sleep(5000L);
		return CompletableFuture.completedFuture(results );
	}

	@Async("taskExecutor500")
	public Future<String> taskExecutor500() throws Exception {
		log.info("[ taskExecutor500 ] called " + 500);
		log.info("Execute method asynchronously. "
	    	      + Thread.currentThread().getName());
		Thread.sleep(5000L);
		
		return new AsyncResult<String>("hello world !!!!");
	}

}