import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by RLuchinsky on 02.04.2018.
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml");

    }
}
