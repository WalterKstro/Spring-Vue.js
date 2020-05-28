package gt.com.umg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:oracle.properties")
@Profile("oracle")
public class ConfigOracle {

}
