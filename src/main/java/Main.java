import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

  public static void main(String[] args) {
    SessionFactory factory = new Configuration().
        configure("hibernate.cfg.xml")
        .buildSessionFactory();

    Session session;

    try {
      session = factory.getCurrentSession();
      session.beginTransaction();

      List<Purchaselist> resultList = session.createQuery("from Purchaselist").getResultList();
      for (Purchaselist purchaselist : resultList) {
        System.out.println(purchaselist.getCourseName());
        System.out.println(purchaselist.getId());
        System.out.println(purchaselist.getStudentName());
      }

      Student student = session.get(Student.class, 1);
      Course course = session.get(Course.class, 30);
      Subscription subscription = new Subscription(
          new Subscription.Id(student.getId(), course.getId()),
          student,
          course,
          new Date());
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
