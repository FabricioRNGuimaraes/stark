package stark.dao;

import java.util.List;

import stark.entity.AbstractEntity;
import stark.entity.Teacher;

public interface ITeacherDAO extends IGenericDAO{

	List<Teacher> findAll();
}
