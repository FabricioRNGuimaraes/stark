package stark.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "doubleConverter")
public class DoubleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null) {

			String[] values = value.toString().split("\\.");

			if(values[0].length() < 2) {
				values[0] = "00" + values[0];	
			}
			
			if(values[0].length() < 3) {
				values[0] = "0" + values[0];
			}
			
			if(values[1].length() < 2) {
				values[1] += "0"; 
			}

			String val = values[0] + "." + values[1];
			return val;
		}
		return null;
	}
}
