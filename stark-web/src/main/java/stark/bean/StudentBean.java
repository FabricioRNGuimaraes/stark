package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import stark.entity.Student;
import stark.service.IStudentService;

@Named(value="studentBean")
@Scope("view")
public class StudentBean extends GenericBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IStudentService studentService; 
	
	private List<Student> students = new ArrayList<Student>();
	private Student student = new Student();
	
	private Boolean newEntity = false;
	
	@PostConstruct
	public void initialize() {
		
		students = studentService.findAllActive();
	}

	public void save(ActionEvent event) {
		
		if(validate()) {
			
			if(!newEntity && student.getId() == null) {
	
				addInfoGrowlMessage("Student " + student.getName() + " saved!");
				studentService.save(student);
				students.add(student);
			} else {
	
				studentService.update(student);
				addInfoGrowlMessage("Student " + student.getName() + " updated!");
			}
			
			clean(null);
		}
	}
	
	public void remove(ActionEvent event) {
		studentService.remove(student);
		students.remove(student);
		addInfoGrowlMessage("Student " + student.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		
		student = new Student();
		newEntity = false;
	}
	
	public void prepareEdit() {
		newEntity = true;
	}
	
	private boolean validate() {
		
		if(student == null || student.getName() == null || student.getName().isEmpty()) {
			addErrorGrowlMessage("Check Fields with *");
			return false;
		}
		
//		if(!newEntity && studentService.findByName(student.getName()) != null) {
//			addErrorGrowlMessage("Student already registred");
//			return false;			
//		}
		
		return true;
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