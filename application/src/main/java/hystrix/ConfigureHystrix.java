package hystrix;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.netflix.hystrix.HystrixCommandProperties;

import util.BaseObject;

@Startup
@Singleton
public class ConfigureHystrix extends BaseObject {

	@PostConstruct
	private void configureHystrix() {
		LOG.debug("Configure Hystrix");
		HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000);
	}

}
