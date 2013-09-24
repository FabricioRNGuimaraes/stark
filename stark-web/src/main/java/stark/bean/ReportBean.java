package stark.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import stark.entity.Student;
import stark.service.IStudentService;
import stark.service.impl.StudentServiceImpl;

@ManagedBean(name="reportBean")
@ViewScoped
public class ReportBean extends GenericBean {

	private static final long serialVersionUID = 1L;
	
	private List<Student> students = new ArrayList<Student>();
	private Student student = new Student();

    private IStudentService studentService;
    
    private Date finalDate;
    private Date startDate;
    
    @Override
	public void initialize() {
	
		studentService = new StudentServiceImpl();
		students = studentService.findAll();
	}
	
	public void onChangeOneMenuStudents(AjaxBehaviorEvent event) {

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

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}