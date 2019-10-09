import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {

  @EmbeddedId
  private Id id;

  @ManyToOne
  @JoinColumn(name = "student_id", updatable = false, insertable = false)
  private Student student;
  @ManyToOne
  @JoinColumn(name = "course_id", updatable = false, insertable = false)
  private Course course;
  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Subscription() {
  }

  public Subscription(Id id, Student student, Course course, Date subscriptionDate) {
    this.id = id;
    this.student = student;
    this.course = course;
    this.subscriptionDate = subscriptionDate;
  }

  public Id getId() {
    return id;
  }

  public void setId(Id id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }

  @Embeddable
  public static class Id implements Serializable {


    @Column(name = "student_id")
    private int studentid;


    @Column(name = "course_id")
    private int courseid;

    public Id() {
    }

    public Id(int studentid, int courseid) {
      this.studentid = studentid;
      this.courseid = courseid;
    }

    public int getStudentid() {
      return studentid;
    }

    public void setStudentid(int studentid) {
      this.studentid = studentid;
    }

    public int getCourseid() {
      return courseid;
    }

    public void setCourseid(int courseid) {
      this.courseid = courseid;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Id that = (Id) o;
      return studentid == that.studentid &&
          courseid == that.courseid;
    }

    @Override
    public int hashCode() {
      return Objects.hash(studentid, courseid);
    }
  }
}
