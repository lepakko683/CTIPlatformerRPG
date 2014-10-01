package celestibytes.ctiplatformerrpg;

import java.util.Random;

public class Version {
	
	
	/**Only for visual debug-stuff*/
	public static final boolean DEBUG_ENABLED = true;
	
	
	public static final String title = "CTIPlatformer";
	
	// Version stuff
	public static final int version_buildNumber = -1;
	public static final int version_majorVersionNumber = 0;
	public static final int version_minorVersionNumber = 0;
	
	private static int motlId = -1;
	
	public static String getTitle() {
		return title + " " + version_majorVersionNumber + "." + version_minorVersionNumber + "." + (DEBUG_ENABLED ? "[DEV BUILD]" : version_buildNumber) + ": " + getMOTL();
	}
	
	/**MOTL - Message Of The Launch.*/
	public static String getMOTL() {
		if(motlId == -1) {
			Random rand = new Random(System.currentTimeMillis());
			motlId = rand.nextInt(motls.length);
		}
		return motls[motlId];
	}
	
	private static final String[] motls = new String[] {
		"(print (+ (* 10 10 10) (* 15 2 10) (/ 74 2)))",
		"Lots of Irritating Superfluous Parentheses",
		"MAJJICK!",
		"BAUIFELHMXIYEQDGNIFCSMHYQGCMJZXC.. (Cat walking on keyboard, caps-lock on, out-of-space, number keys not working, ..)",
		"But wait, there's more! W-NC5oaiTkKw",
		"Can I have a raspberry pie?",
		"NOPE!",
		"-PLACEHOLDER-",
		"Please, don't format my code.",
		"Ei koske, Poppaa!",
		"asdf",
		"\"password\" is a bad password.",
		"SPAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACE!",
		"When in doubt, HIT IT WITH THE...",
		"Kolmivaihevaihtovirtakilowattituntimittari",
		"Yaay!",
		"LISTEN!",
		"O< Quack!",
		"Can't sea #",
		"Microcraft",
		"Random text didn't end yet, have some more: TEYOETYSUTLFBSRHPN",
		"PCmustardRice",
		"P___E's worst enemy: GRVT",
		"SOLIDIFY!_024162",
		"418319",
		"._.",
		"C > C++",
		"GTK+ > Qt"
	};
	
}
