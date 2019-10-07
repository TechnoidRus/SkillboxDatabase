import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

  public static void main(String[] args) {
    SessionFactory factory = new Configuration().
        configure("hibernate.cfg.xml")
        .buildSessionFactory();

    Session session = factory.openSession();

    Course course = session.get(Course.class, 1);
    System.out.println(course.getDescription());
    System.out.println(course.getPrice());

    Student student = session.get(Student.class, 23);
    System.out.println(student.getName());
    System.out.println(student.getRegistrationDate());

    session.close();
  }

}
