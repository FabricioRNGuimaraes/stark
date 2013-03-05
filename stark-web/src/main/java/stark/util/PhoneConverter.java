package stark.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "phoneConverter")
public class PhoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value != null && !value.isEmpty()) {
			value = value.replace("(", "").replace(")", "").replace("-", "");
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null && !value.toString().trim().isEmpty()) {
			
			try {
				
				StringBuilder builder = new StringBuilder("(");
				builder.append(value.toString().substring(0, 2));
				builder.append(")");
				builder.append(value.toString().substring(2, 6));
				builder.append("-");
				builder.append(value.toString().substring(6, 10));
				return builder.toString();
			} catch(NullPointerException nullPointerException) {
				return value.toString();
			}
			
		}
		return null;
	}
}
