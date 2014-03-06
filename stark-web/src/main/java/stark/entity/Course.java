package stark.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COURSE")
public class Course extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_course")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COURSE")
	@SequenceGenerator(name = "SEQ_COURSE", sequenceName = "SEQ_COURSE", allocationSize=1)
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