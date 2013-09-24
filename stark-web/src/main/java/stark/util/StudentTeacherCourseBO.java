package stark.util;


public class StudentTeacherCourseBO {

	private int id;
	
	private String description;
	
	private boolean visibleValue = false;
	
	private Double valueClass;
	
	private boolean selected = false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isVisibleValue() {
		return visibleValue;
	}

	public void setVisibleValue(boolean visibleValue) {
		this.visibleValue = visibleValue;
	}

	public Double getValueClass() {
		return valueClass;
	}

	public void setValueClass(Double valueClass) {
		this.valueClass = valueClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
