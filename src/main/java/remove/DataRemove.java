package remove;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class DataRemove {

	public static void main (String [] arg) throws InterruptedException 
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int height = dimension.height;
		int width = dimension.width;

		Playwright playwright = Playwright.create();
		Browser browser= playwright.firefox().launch(new BrowserType.LaunchOptions().setChannel("").setHeadless(false));

		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
		Page page = browserContext.newPage();
		page.navigate("https://chat.openai.com/");
		//		page.pause();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue with Google")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email or phone")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email or phone")).fill("baskar.s@theswipewire.com");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email or phone")).press("Enter");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter your password")).fill("SWTech@123");
		page.locator("#passwordNext").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Next")).click();



        page.waitForLoadState(LoadState.NETWORKIDLE);//wait for after redirect complete//Without redirect dont use networkidel
		//	      page.navigate("https://chat.openai.com/");
		Locator byRoleOkey = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Okay, let’s go"));
		if (byRoleOkey.isVisible()) {
			byRoleOkey.click();
		}
		
		Locator byRole = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
		if (byRole.isVisible()) {
			byRole.click();
		}
		Locator byRole2 = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
		if (byRole2.isVisible()) {
			byRole2.click();
		}
		Locator byRole3 = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Done"));
		if (byRole3.isVisible()) {
			byRole3.click();
		}
		TimeUnit.SECONDS.sleep(3);
		Locator locator = page.locator("(//nav[@aria-label='Chat history']//div[3])[2]");

		Object latheight = locator.evaluate("document.querySelector('.overflow-y-auto').scrollHeight");
		String string = latheight.toString();
		int lastHeight = Integer.parseInt(string);
		System.out.println(lastHeight);

		/**------------------Method 1 with java script for page down in Playwrite------------------------------------------**/
		/*	while (true) {
				page.waitForLoadState( LoadState.DOMCONTENTLOADED);
//						Thread.sleep(3000);
				((Page) locator).keyboard().down("End");
				TimeUnit.SECONDS.sleep(2); // wait for page to load
				Object neHeight = page.evaluate("document.getElementsByClassName('nav .overflow-y-auto').scrollHeight");
				int newHeight = Integer.parseInt(neHeight.toString());
				System.err.println(newHeight);
				if (newHeight == lastHeight) {
					System.out.println(newHeight + "==" + lastHeight);
					break;
				}
				lastHeight = newHeight;
			}*/

		/**------------------Method 1 with java script for page down in Playwrite------------------------------------------**/
/*		
		while (true) {
			page.evaluate("document.querySelector('.overflow-y-auto').scrollBy(0," + lastHeight + ")");
			TimeUnit.SECONDS.sleep(5); // wait for page to load
			Object neHeight = page.evaluate("document.querySelector('.overflow-y-auto').scrollHeight");
			int newHeight = Integer.parseInt(neHeight.toString());
			System.err.println(newHeight);
			if (newHeight == lastHeight) {
				System.out.println(newHeight + "==" + lastHeight);
				break;
			}
			lastHeight = newHeight;
		}


		Locator list = page.locator("div[class='flex-1 text-ellipsis max-h-5 overflow-hidden break-all relative']");

		System.out.println(list.count());
		for (int i = 0; i < list.count(); i++) {
			System.out.println(list.nth(i).innerText());

			Locator nth = list.nth(i);
            nth.click();
			page.locator("//button[@class='p-1 hover:text-white'][3]").click();
			page.locator("//button[@class='p-1 hover:text-white'][1]").click();
			
//	    page.getByRole(AriaRole.NAVIGATION,new Locator.LocatorOptions().setHasText("Greeting, Request Assistance"));
//			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().getClass()..setName(innerText)).getByRole(AriaRole.BUTTON).nth(2).click();
//			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(innerText)).getByRole(AriaRole.BUTTON).first().click();
			Thread.sleep(5000);
		}*/
		
		
		
		
		Locator firstEle = page.locator("ol li").first();
		Locator delIcon = page.locator("//button[@class='p-1 hover:text-white'][2]");
		Locator del = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"));
		
		
		while (firstEle.isEnabled()) {
			firstEle.click();
			delIcon.click();
			del.click();
			Thread.sleep(3000);
			
//			if (true!=firstEle.isEnabled()) {
//				break;
//			}
		}
		

		playwright.close();
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
