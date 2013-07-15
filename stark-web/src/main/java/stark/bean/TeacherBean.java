package stark.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.Address;
import stark.entity.Teacher;
import stark.service.ITeacherService;
import stark.service.impl.TeacherServiceImpl;

@ManagedBean(name="teacherBean")
@ViewScoped
public class TeacherBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<Teacher> teachers;
	private Teacher teacher = new Teacher();
	
	private Boolean newEntity = false;
	
//	@ManagedProperty(value="#{teacherService}")
	private ITeacherService teacherService;
	
	public void initialize() {
		
		createTeacherAddress();
		teacherService = new TeacherServiceImpl(); 
		teachers = teacherService.findAll();
	}

	public void save(ActionEvent event) {

		if(!newEntity && teacher.getId() == null) {

			teacherService.save(teacher);
			addInfoGrowlMessage("Teacher " + teacher.getName() + " saved!");
		} else {
			teacherService.update(teacher);
			addInfoGrowlMessage("Teacher " + teacher.getName() + " updated!");
		}

		teachers = teacherService.findAll();
	}
	
	public void remove(ActionEvent event) {
		
		teacherService.remove(teacher);
		teachers = teacherService.findAll();

		addInfoGrowlMessage("Teacher " + teacher.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		teacher = new Teacher();
		createTeacherAddress();
		newEntity = false;
	}
	
	private void createTeacherAddress() {
		teacher.setAddress(new Address());
	}
	
	public void prepareEdit() {
		
		newEntity = true;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

}