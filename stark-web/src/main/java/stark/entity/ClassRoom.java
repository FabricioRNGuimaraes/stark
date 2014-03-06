package stark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CLASS_ROOM")
public class ClassRoom extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_class_room")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASS_ROOM")
	@SequenceGenerator(name = "SEQ_CLASS_ROOM", sequenceName = "SEQ_CLASS_ROOM", allocationSize=1)
	private Integer id;
	
	@Column(name="description", nullable=false, length=50)
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}