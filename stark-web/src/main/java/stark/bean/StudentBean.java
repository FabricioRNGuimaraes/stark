package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.dao.StudentDAO;
import stark.entity.Address;
import stark.entity.Student;

@ManagedBean(name="studentBean")
@ViewScoped
//@Component
//@Scope("session")
public class StudentBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private List<Student> students = new ArrayList<Student>();
	private Student student = new Student();
	private Student studentViewRemove;
	
	private StudentDAO dao;
	
	private Boolean isEditEntity = false;
//	@Autowired
//	@ManagedProperty(value="#{studentService}")
//	IStudentService studentService;
	
	public void initialize() {
		
		dao = new StudentDAO();
		students = dao.findAll();
		createStudentAddress();
	}

	public void save(ActionEvent event) {

//		studentService.save(student);

		student.setActive(Boolean.TRUE);
		if(!isEditEntity && student.getId() == null) {

			addInfoGrowlMessage("Student " + student.getName() + " saved!");
			dao.save(student);
			students = dao.findAll();
		} else {
			
			addInfoGrowlMessage("Student " + student.getName() + " updated!");
			dao.update(student);
		}
	}
	
	public void remove(ActionEvent event) {
		
		studentViewRemove.setActive(Boolean.FALSE);
		dao.update(studentViewRemove);
		students = dao.findAll();
		addInfoGrowlMessage("Student " + studentViewRemove.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		student = new Student();
		createStudentAddress();
		isEditEntity = false;
	}
	
	private void createStudentAddress() {
		student.setAddress(new Address());
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudentViewRemove() {
		return studentViewRemove;
	}

	public void setStudentViewRemove(Student studentViewRemove) {
		this.studentViewRemove = studentViewRemove;
	}

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}


}