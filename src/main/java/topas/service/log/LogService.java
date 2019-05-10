package topas.service.log;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogService {
//    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
	@Async
	public void log() {
		// TODO Auto-generated method stub
		log.info("" + " [ log  ]");
	}
}