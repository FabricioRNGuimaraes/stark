package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

import stark.enums.PrimefacesThemeEnum;

@ManagedBean(name="applicationBean")
@ApplicationScoped
public class ApplicationBean  extends GenericBean {

	private static final long serialVersionUID = 1L;

	private List<String> primefacesThemes = new ArrayList<String>();

	private String primefacesTheme = PrimefacesThemeEnum.BLUESKY.getName();

	@Override
	public void initialize() {

		for (PrimefacesThemeEnum theme : PrimefacesThemeEnum.values()) {

			primefacesThemes.add(theme.getName());
		}
	}

	public void onChangeOneMenuPrimefacesTheme(AjaxBehaviorEvent event) {

		addInfoGrowlMessage("Tema escolhido: " + primefacesTheme);
		executeJavascript("reloadPage();");
	}

	public String getPrimefacesTheme() {
		return primefacesTheme;
	}

	public void setPrimefacesTheme(String primefacesTheme) {
		this.primefacesTheme = primefacesTheme;
	}

	public List<String> getPrimefacesThemes() {
		return primefacesThemes;
	}

	public void setPrimefacesThemes(List<String> primefacesThemes) {
		this.primefacesThemes = primefacesThemes;
	}

}
