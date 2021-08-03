package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Henday", 30);
      Car car2 = new Car("VAZ", 2101);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));

      List<User> users = userService.listUsers();
      users.forEach(System.out::println);

      User userWithCar = userService.findOneByCarParams(car1.getModel(), car1.getSeries());
      System.out.println("userWithCar:" + userWithCar);

      context.close();
   }
}
