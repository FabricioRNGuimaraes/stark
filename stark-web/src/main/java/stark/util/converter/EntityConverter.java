package stark.util.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import stark.entity.AbstractEntity;

@FacesConverter(value="entityConverter")
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {		
		if(value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		
		return null;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {

		if(value != null){// && value instanceof AbstractEntity) {
//			AbstractEntity entity = (AbstractEntity) value;
			Object entity = value;
			this.addAttribute(component, entity.toString(), entity);
			return entity.toString();
		}
		return null;
	}
	
	private void addAttribute(UIComponent component, String key, Object entity) {

		Map<String, Object> attributesFrom = this.getAttributesFrom(component);
		attributesFrom.put(key, entity);
	}

	private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
}