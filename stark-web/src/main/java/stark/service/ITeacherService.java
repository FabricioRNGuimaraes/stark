package stark.service;

import java.util.List;

import stark.entity.AbstractEntity;
import stark.entity.Teacher;

public interface ITeacherService {

	List<Teacher> findAll();
	boolean save(Teacher teacher);
	boolean remove(Teacher teacher);
	boolean update(Teacher teacher);
	Teacher findById(Integer id);
}
