package stark.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT_COURSE")
public class StudentCourse extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_student_course")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT_COURSE")
	@SequenceGenerator(name = "SEQ_STUDENT_COURSE", sequenceName = "SEQ_STUDENT_COURSE", allocationSize=1)
	private Integer id;

//	@DateTimeFormat(style = "S-")
//	@Temporal(TemporalType.DATE)
	@Column(name="dt_register", nullable=false)
	private Date dtRegister;
	
	@Column(name="value_class", length=11)
	private Double valueClass;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="student", referencedColumnName="id_student")
	private Student student;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course", referencedColumnName="id_course")
	private Course course;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtRegister() {
		return dtRegister;
	}

	public void setDtRegister(Date dtRegister) {
		this.dtRegister = dtRegister;
	}

	public Double getValueClass() {
		return valueClass;
	}

	public void setValueClass(Double valueClass) {
		this.valueClass = valueClass;
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

	@Override
	public String toString() {
		return course.getDescription() + " R$" + valueClass;
	}

}