package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.Address;
import stark.entity.Student;
import stark.service.IStudentService;
import stark.service.impl.StudentServiceImpl;

@ManagedBean(name="studentBean")
@ViewScoped
//@Component
//@Scope("session")
public class StudentBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private List<Student> students = new ArrayList<Student>();
	private Student student = new Student();
	
	private Boolean newEntity = false;
//	@Autowired
//	@ManagedProperty(value="#{studentService}")
	private IStudentService studentService;
	
	public void initialize() {
		studentService = new StudentServiceImpl();
		students = studentService.findAll();
		createStudentAddress();
	}

	public void save(ActionEvent event) {

		if(!newEntity && student.getId() == null) {

			addInfoGrowlMessage("Student " + student.getName() + " saved!");
			studentService.save(student);
			students = studentService.findAll();
		} else {
			
			addInfoGrowlMessage("Student " + student.getName() + " updated!");
			studentService.update(student);
		}
		
		students = studentService.findAll();
	}
	
	public void remove(ActionEvent event) {
		
		studentService.remove(student);
		students = studentService.findAll();
		addInfoGrowlMessage("Student " + student.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	public void prepareEdit() {
		
		newEntity = true;
	}
	
	private void clean() {
		
		student = new Student();
		createStudentAddress();
		newEntity = false;
	}
	
	private void createStudentAddress() {
		student.setAddress(new Address());
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

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

}