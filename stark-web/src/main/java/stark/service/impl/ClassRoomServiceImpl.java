package stark.service.impl;

import java.io.Serializable;
import java.util.List;

import stark.dao.ClassRoomDAO;
import stark.entity.ClassRoom;
import stark.service.IClassRoomService;

public class ClassRoomServiceImpl implements IClassRoomService, Serializable {

	private static final long serialVersionUID = 1L;
	ClassRoomDAO dao = new ClassRoomDAO();
	
	@Override
	public List<ClassRoom> findAll() {
		return dao.findAllActive();
	}

	@Override
	public boolean save(ClassRoom classRoom) {
		
		classRoom.setActive(true);
		return dao.save(classRoom);
	}

	@Override
	public boolean remove(ClassRoom classRoom) {

		classRoom.setActive(false);
		return dao.update(classRoom);
	}

	@Override
	public boolean update(ClassRoom classRoom) {
		return dao.update(classRoom);
	}

}
