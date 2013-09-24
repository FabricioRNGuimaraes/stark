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
@Table(name="teacher_course")
public class TeacherCourse extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_teacher_course")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEACHER_COURSE")
	@SequenceGenerator(name = "SEQ_TEACHER_COURSE", sequenceName = "SEQ_TEACHER_COURSE", allocationSize=1)
	private Integer id;

//	@DateTimeFormat(style = "S-")
//	@Temporal(TemporalType.DATE)
	@Column(name="dt_register", nullable=false)
	private Date dtRegister;
	
	@Column(name="value_class", length=11)
	private Double valueClass;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teacher", referencedColumnName="id_teacher")
	private Teacher teacher;

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String toString() {
		return course.getDescription() + " R$" + valueClass;
	}
}