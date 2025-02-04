package models;

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
@Table(name = "student_courses")
public class StudentsCourses {


  @EmbeddedId
  private Id id;

  @ManyToOne
  @JoinColumn(name = "student_id", updatable = false, insertable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name = "course_id", updatable = false, insertable = false)
  private Course course;

  @Column(name = "price")
  private Integer price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;


  public Id getId() {
    return id;
  }

  public StudentsCourses(Id id, Student student, Course course, int price,
      Date subscriptionDate) {
    this.id = id;
    this.student = student;
    this.course = course;
    this.price = price;
    this.subscriptionDate = subscriptionDate;
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
  public static class Id implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public Id() {
    }

    public Id(int studentId, int courseId) {
      this.studentId = studentId;
      this.courseId = courseId;
    }

    public int getStudentId() {
      return studentId;
    }

    public void setStudentId(int studentId) {
      this.studentId = studentId;
    }

    public int getCourseId() {
      return courseId;
    }

    public void setCourseId(int courseId) {
      this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Id id = (Id) o;
      return studentId == id.studentId &&
          courseId == id.courseId;
    }

    @Override
    public int hashCode() {
      return Objects.hash(studentId, courseId);
    }
  }
}
