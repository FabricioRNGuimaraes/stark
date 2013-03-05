package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.dao.ClassRoomDAO;
import stark.dao.CourseDAO;
import stark.entity.ClassRoom;
import stark.entity.Course;

@ManagedBean(name="classRoomBean")
@ViewScoped
public class ClassRoomBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<ClassRoom> classesRoom = new ArrayList<ClassRoom>();
	private ClassRoom classRoom = new ClassRoom();
	private ClassRoom classRoomViewRemove;
	
	private ClassRoomDAO dao;
	
	private Boolean isEditEntity = false;
	
	public void initialize() {

		dao = new ClassRoomDAO();
		classesRoom = dao.findAll();
	}

	public void save(ActionEvent event) {

		classRoom.setActive(Boolean.TRUE);
		if(!isEditEntity && classRoom.getId() == null) {
			addInfoGrowlMessage("Class Room " + classRoom.getDescription() + " saved!");
			dao.save(classRoom);
			classesRoom = dao.findAll();
		} else {
			
			addInfoGrowlMessage("Class Room " + classRoom.getDescription() + " updated!");
			dao.update(classRoom);
		}
	}
	
	public void remove(ActionEvent event) {
		
		classRoomViewRemove.setActive(Boolean.FALSE);
		dao.update(classRoomViewRemove);
		classesRoom = dao.findAll();
		addInfoGrowlMessage("Class Room " + classRoomViewRemove.getDescription() + " deactivated!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		classRoom = new ClassRoom();
		isEditEntity = false;
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
	}

	public List<ClassRoom> getClassesRoom() {
		return classesRoom;
	}

	public void setClassesRoom(List<ClassRoom> classesRoom) {
		this.classesRoom = classesRoom;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public ClassRoom getClassRoomViewRemove() {
		return classRoomViewRemove;
	}

	public void setClassRoomViewRemove(ClassRoom classRoomViewRemove) {
		this.classRoomViewRemove = classRoomViewRemove;
	}

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}

}