package khuong.com.ex5_lab6;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")  // down file properties từ resources
})
public class SpringCoreApp {

    @Value("${spring.application.name}")    // inject value từ file properties
    private String appName;


    @PostConstruct
    public void printAppInfo() {
        System.out.println("Application Name: " + appName);
    }

    public static void main(String[] args) {
        org.springframework.context.annotation.AnnotationConfigApplicationContext context =
                new org.springframework.context.annotation.AnnotationConfigApplicationContext(SpringCoreApp.class);

        SpringCoreApp app = context.getBean(SpringCoreApp.class);
        app.printAppInfo();
        context.close();
    }
}
