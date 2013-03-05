package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.dao.TeacherDAO;
import stark.entity.Address;
import stark.entity.Teacher;

@ManagedBean(name="teacherBean")
@ViewScoped
public class TeacherBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<Teacher> teachers = new ArrayList<Teacher>();
	private Teacher teacher = new Teacher();
	private Teacher teacherViewRemove;
	
	private TeacherDAO dao;
	
	private Boolean isEditEntity = false;
	
	public void initialize() {
		
		dao = new TeacherDAO();
		teachers = dao.findAll();
		createTeacherAddress();
	}

	public void save(ActionEvent event) {

		teacher.setActive(Boolean.TRUE);
		if(!isEditEntity && teacher.getId() == null) {

			addInfoGrowlMessage("Teacher " + teacher.getName() + " saved!");
			dao.save(teacher);
			teachers = dao.findAll();
		} else {
			
			addInfoGrowlMessage("Teacher " + teacher.getName() + " updated!");
			dao.update(teacher);
		}
	}
	
	public void remove(ActionEvent event) {
		
		teacherViewRemove.setActive(Boolean.FALSE);
		dao.update(teacherViewRemove);
		teachers = dao.findAll();
		addInfoGrowlMessage("Teacher " + teacherViewRemove.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		teacher = new Teacher();
		createTeacherAddress();
		isEditEntity = false;
	}
	
	private void createTeacherAddress() {
		teacher.setAddress(new Address());
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
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

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}

	public Teacher getTeacherViewRemove() {
		return teacherViewRemove;
	}

	public void setTeacherViewRemove(Teacher teacherViewRemove) {
		this.teacherViewRemove = teacherViewRemove;
	}
}