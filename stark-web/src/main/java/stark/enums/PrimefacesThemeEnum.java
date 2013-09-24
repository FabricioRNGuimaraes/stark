package stark.enums;

public enum PrimefacesThemeEnum {

	AFTERDARK("afterdark"), 
	AFTERNOON("afternoon"), 
	ARISTO("aristo"), 
	BLACKTIE("black-tie"), 
	BLITZER("blitzer"), 
	BLUESKY("bluesky"), 
	CASABLANCA("casablanca"),
	CRUZE("cruze"), 
	CUPERTINO("cupertino"),
	DARKHIVE("dark-hive"), 
	DELTA("delta"),
	DOTLUV("dot-luv"),
	EGGPLANT("eggplant"), 
	EXCITE_BIKE("excite-bike"),
	FLICK("flick"), 
	GLASSX("glass-x"), 
	HOME("home"), 
	HOT_SNEAKS("hot-sneaks"), 
	HUMANITY("humanity"), 
	LE_FROG("le-frog"),
	MIDNIGHT("midnight"),
	MINT_CHOC("mint-choc"), 
	OVERCAST("overcast"),
	PEPPER_GRINDER("pepper-grinder"),
	REDMOND("redmond"),
	ROCKET("rocket"),
	SAM("sam"), 
	SMOOTHNESS("smoothness"), 
	SOUTH_STREET("south-street"), 
	START("start"),
	SUNNY("sunny"), 
	SWANKY_PURSE("swanky-purse"),
	TRONTASTIC("trontastic"),
	TWITTER_BOOTSTRAP("bootstrap"),
	UI_DARKNESS("ui-darkness"),
	UI_LIGHTNESS("ui-lightness"), 
	VADER("vader");

	private final String name;

	private PrimefacesThemeEnum(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}
}