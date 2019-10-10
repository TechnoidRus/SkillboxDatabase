import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student_courses")
public class StudentsCourses {

  @EmbeddedId
  private Id id;


  public Id getId() {
    return id;
  }

  public void setId(Id id) {
    this.id = id;
  }


  @Embeddable
  private static class Id implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

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
