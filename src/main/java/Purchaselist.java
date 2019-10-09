import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "purchaselist")
public class Purchaselist {

  @EmbeddedId
  Id id;

  @Column(name = "student_name", updatable = false, insertable = false)
  private String studentName;
  @Column(name = "course_name", updatable = false, insertable = false)
  private String courseName;
  @Column(name = "price")
  private int price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Purchaselist() {
  }

  public Purchaselist(Id id, String studentName, String courseName, int price,
      Date subscriptionDate) {
    this.id = id;
    this.studentName = studentName;
    this.courseName = courseName;
    this.price = price;
    this.subscriptionDate = subscriptionDate;
  }

  public Id getId() {
    return id;
  }

  public void setId(Id id) {
    this.id = id;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }

  @Embeddable
  private static class Id implements Serializable {

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    public String getStudentName() {
      return studentName;
    }

    public void setStudentName(String studentName) {
      this.studentName = studentName;
    }

    public String getCourseName() {
      return courseName;
    }

    public void setCourseName(String courseName) {
      this.courseName = courseName;
    }

    public Id() {
    }

    public Id(String studentName, String courseName) {
      this.studentName = studentName;
      this.courseName = courseName;
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
      return Objects.equals(studentName, that.studentName) &&
          Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
      return Objects.hash(studentName, courseName);
    }
  }
}
