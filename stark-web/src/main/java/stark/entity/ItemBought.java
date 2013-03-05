package stark.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ITEM_BOUGHT")
public class ItemBought extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_item_bought")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM_BOUGHT")
	@SequenceGenerator(name = "SEQ_ITEM_BOUGHT", sequenceName = "SEQ_ITEM_BOUGHT", allocationSize=1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="item", referencedColumnName="id_item", nullable=false)
	private Item item;

	@Column(name="quantity", nullable=false)
	private Integer quantity;
	
	@Column(name="day_bought", nullable=false)
	private Date dayBought;

	@Column(name="unity_value", length=5, precision=2, nullable=false)
	private Double value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDayBought() {
		return dayBought;
	}

	public void setDayBought(Date dayBought) {
		this.dayBought = dayBought;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}