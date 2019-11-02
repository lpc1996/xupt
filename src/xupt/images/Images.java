package xupt.images;

import java.awt.Image;
import java.awt.Toolkit;

public class Images {
	
	private Image schoolLogo;
	private Image loginBG;
	private Image background2;
	private Image failed;
	private Image woman;
	private Image man;
	private Image search;
	private Image successful;
	private Image user;
	private Image warring2;
	private Image yes2;
	private Image error2;
	private final String urlPre = "/xupt/images/";
	private Toolkit toolKit;
	
	public Images() {
		// TODO Auto-generated constructor stub
		toolKit = Toolkit.getDefaultToolkit();
	}

	public Image getSchoolLogo() {
		schoolLogo = toolKit.getImage( this.getClass().getResource(urlPre+"school_logo.jpg") );
		return schoolLogo;
	}

	public Image getLoginBG() {
		loginBG = toolKit.getImage(this.getClass().getResource(urlPre+"background1.jpg"));
		return loginBG;
	}
	
	public Image getBackground2() {
		background2 = toolKit.getImage(this.getClass().getResource(urlPre+"background2.jpg"));
		return background2;
	}

	public Image getFailed() {
		failed = toolKit.getImage(this.getClass().getResource(urlPre+"error2.png"));
		return failed;
	}

	public Image getWoman() {
		woman = toolKit.getImage(this.getClass().getResource(urlPre+"woman.gif"));
		return woman;
	}

	public Image getMan() {
		man = toolKit.getImage(this.getClass().getResource(urlPre+"man.gif"));
		return man;
	}

	public Image getSearch() {
		search = toolKit.getImage(this.getClass().getResource(urlPre+"search-1.png"));
		return search;
	}

	public Image getSuccessful() {
		successful = toolKit.getImage(this.getClass().getResource(urlPre+"yes-2.png"));
		return successful;
	}

	public Image getUser() {
		user = toolKit.getImage(this.getClass().getResource(urlPre+"user.gif"));
		return user;
	}

	public Image getWarring2() {
		warring2 = toolKit.getImage(this.getClass().getResource(urlPre+"warning-1.png"));
		return warring2;
	}

	public Image getYes2() {
		yes2 = toolKit.getImage(this.getClass().getResource(urlPre+"yes-2.png"));
		return yes2;
	}

	public Image getError2() {
		error2 = toolKit.getImage(this.getClass().getResource(urlPre+"error-2.png"));
		return error2;
	}
	
}
