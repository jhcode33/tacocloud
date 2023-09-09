package tacos;

import java.util.Collections;
import java.util.Map;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@EnableMongoAuditing  //요거랑!
@EnableReactiveMongoRepositories  //요거!
@SpringBootApplication(exclude = ServletWebServerFactoryAutoConfiguration.class)
public class TacoCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args);
  }

//  // To avoid 404s when using Angular HTML 5 routing
//  @Bean
//  ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
//      return new ErrorViewResolver() {
//          @Override
//          public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
//              return status == HttpStatus.NOT_FOUND
//                      ? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
//                      : null;
//          }
//      };
//  }
}
