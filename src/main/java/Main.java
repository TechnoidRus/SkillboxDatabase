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

      fillTable(session);

      session.getTransaction().commit();
    }
  }
  //метод заполнения таблицы student_courses
  private static void fillTable(Session session){ {
    session.createSQLQuery("insert into student_courses\n"
        + "select students.id as student_id, courses.id as course_id FROM purchaselist\n"
        + "join students ON students.name = purchaselist.student_name\n"
        + "join courses on courses.name = purchaselist.course_name").executeUpdate();
  }


  }

}
