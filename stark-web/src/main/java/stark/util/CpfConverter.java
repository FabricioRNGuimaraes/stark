package stark.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "cpfConverter")
public class CpfConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value != null && !value.isEmpty()) {
			value = value.replace(".", "").replace("-", "");
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null && !value.toString().trim().isEmpty()) {
			
			try {
				
				StringBuilder builder = new StringBuilder();
				builder.append(value.toString().substring(0, 3));
				builder.append(".");
				builder.append(value.toString().substring(3, 6));
				builder.append(".");
				builder.append(value.toString().substring(6, 9));
				builder.append("-");
				builder.append(value.toString().substring(9, 11));
				return builder.toString();
			} catch(NullPointerException nullPointerException) {
				return value.toString();
			}
		}
		return null;
	}
}
