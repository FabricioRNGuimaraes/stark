package stark.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TEACHER_COURSE")
public class TeacherCourse extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_teacher_course")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEACHER_COURSE")
	@SequenceGenerator(name = "SEQ_TEACHER_COURSE", sequenceName = "SEQ_TEACHER_COURSE", allocationSize=1)
	private Integer id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="teacher", referencedColumnName="id_teacher")
	private Teacher teacher;

	@Column(name="value_course", length=5, precision=2)
	private Double valueCourse;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="course", referencedColumnName="id_course")
	private Course course;

	@Column(name="payment_day", length=2, nullable=false)
	private String paymentDay;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_registration", nullable=false)
	private Date registration;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Double getValueCourse() {
		return valueCourse;
	}

	public void setValueCourse(Double valueCourse) {
		this.valueCourse = valueCourse;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(String paymentDay) {
		this.paymentDay = paymentDay;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}
	
}