import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

  public static void main(String[] args) {
    try (SessionFactory factory = new Configuration().
        configure("hibernate.cfg.xml")
        .buildSessionFactory();
        Session session = factory.getCurrentSession()) {

      session.beginTransaction();

      Student student = session.get(Student.class, 12);
      List<Course> courses = student.getCourses();
      for (Course cours : courses) {
        System.out.println(cours.getName());
      }

      System.out.println("---------------------------------------------------");

      Course course = session.get(Course.class, 5);
      List<Student> students = course.getStudents();
      for (Student s : students) {
        System.out.println(s.getName());
      }
      System.out.println("---------------------------------------------------");

      Teacher teacher = course.getTeacher();
      System.out.println(teacher.getName());
      System.out.println("---------------------------------------------------");

      Subscription subscription = new Subscription(
          new Subscription.Id(student.getId(), course.getId()), student, course, new Date());
      session.save(subscription);

      session.getTransaction().commit();
    }
  }

}
