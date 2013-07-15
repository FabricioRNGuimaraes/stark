package stark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ITEM")
public class Item extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_item")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM")
	@SequenceGenerator(name = "SEQ_ITEM", sequenceName = "SEQ_ITEM", allocationSize=1)
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