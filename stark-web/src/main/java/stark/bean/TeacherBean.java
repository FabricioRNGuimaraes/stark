package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import stark.entity.Teacher;
import stark.service.ITeacherService;

@Named(value="teacherBean")
@Scope("view")
public class TeacherBean extends GenericBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ITeacherService teacherService; 
	
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private Teacher teacher = new Teacher();
	
	private Boolean newEntity = false;
	
	@PostConstruct
	public void initialize() {
		
		teachers = teacherService.findAllActive();
	}

	public void save(ActionEvent event) {
		
		if(validate()) {
			
			if(!newEntity && teacher.getId() == null) {
	
				addInfoGrowlMessage("Teacher " + teacher.getName() + " saved!");
				teacherService.save(teacher);
				teachers.add(teacher);
			} else {
	
				teacherService.update(teacher);
				addInfoGrowlMessage("Teacher " + teacher.getName() + " updated!");
			}
			
			clean(null);
		}
	}
	
	public void remove(ActionEvent event) {
		teacherService.remove(teacher);
		teachers.remove(teacher);
		addInfoGrowlMessage("Teacher " + teacher.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		
		teacher = new Teacher();
		newEntity = false;
	}
	
	public void prepareEdit() {
		newEntity = true;
	}
	
	private boolean validate() {
		
		if(teacher == null || teacher.getName() == null || teacher.getName().isEmpty()) {
			addErrorGrowlMessage("Check Fields with *");
			return false;
		}
		
//		if(!newEntity && teacherService.findByName(teacher.getName()) != null) {
//			addErrorGrowlMessage("Teacher already registred");
//			return false;			
//		}
		
		return true;
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