import java.util.List;
import models.Course;
import models.Purchaselist;
import models.Student;
import models.StudentsCourses;
import models.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class Main {

  public static void main(String[] args) {
    try (SessionFactory factory = new Configuration().
        configure("hibernate.cfg.xml")
        .buildSessionFactory();
        Session session = factory.getCurrentSession()) {

      session.beginTransaction();
      Student student = session.get(Student.class, 33);
      List<Subscription> subscriptions = student.getSubscriptions();
      for (Subscription subscription : subscriptions) {
        System.out.println(subscription.getStudent().getName());
        System.out.println(subscription.getCourse().getName());
        System.out.println(subscription.getSubscriptionDate());
      }

      fillTable(session);

      session.getTransaction().commit();
    }
  }

  //метод заполнения таблицы student_courses
  private static void fillTable(Session session) {
    List<Purchaselist> purchaselist = session.createQuery("from models.Purchaselist").getResultList();
    for (Purchaselist var : purchaselist) {

      DetachedCriteria studentsCriteria = DetachedCriteria.forClass(Student.class)
          .add(Restrictions.eq("name", var.getStudentName()));
      Student student = (Student) studentsCriteria.getExecutableCriteria(session).list().stream()
          .findFirst().get();

      DetachedCriteria coursesCriteria = DetachedCriteria.forClass(Course.class)
          .add(Restrictions.eq("name", var.getCourseName()));
      Course course = (Course) coursesCriteria.getExecutableCriteria(session).list().stream()
          .findFirst().get();
      System.out.println(course.getName());

      StudentsCourses sc = new StudentsCourses(
          new StudentsCourses.Id(student.getId(), course.getId()), student, course);
      session.save(sc);
    }


  }

}
