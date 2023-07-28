package remove;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class proxy {
	String URL="https://www.flipkart.com/";
	String comName="flipkart";
	static final String ZAP_PROXY_ADDRESS ="localhost";
	static final int ZAP_PROXY_PORT =8080;
	static final String ZAP_API_KEY ="ln0rb35gmhjeqb53mtfabbvk6b";

	private  WebDriver d;
	private  ClientApi api;
//	private  ApiResponse response ;

	@BeforeMethod
	public void setup() {
		String proxySeruverUrl=ZAP_PROXY_ADDRESS +":"+ ZAP_PROXY_PORT;
		System.out.println("jhhjgfghgf"+proxySeruverUrl);

		Proxy proxy =new Proxy();//improt from selenium
		proxy.setHttpProxy(proxySeruverUrl);//http connection
		proxy.setSslProxy(proxySeruverUrl);//secured connection

	
//		ChromeOptions options=new ChromeOptions();
		FirefoxOptions options =new FirefoxOptions();
//		options.addArguments("--remote-allow-origins=*");
		options.setProxy(proxy);
		options.setAcceptInsecureCerts(true);//with out Quit the  driver it show the page not secure erroe .withwout error call his method

		System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\driver\\drivers\\geckodriver.exe");
		//		(or)

//		WebDriver d = WebDriverManager.chromedriver().create();  //creat driver instance in driver manger in 5 version in reposotory 
//		 WebDriver d = WebDriverManager.chromedriver().capabilities(options).create();

		d=new FirefoxDriver(options);

//		d.manage().window().maximize();	
		api=new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);

	}

	@Test
	public void flipkartSecurityTest() {
		d.get(URL);
		System.out.println(d.getTitle());
		//		Assert.assertTrue(d.getTitle().contains("Shopping"));
	} 

	@AfterMethod
	public void tearDown() {
		if (api != null) 	{
			String  title="flipkart ZAP Sevurity Report";
			String  template="traditonalt-html";
			String  description="This is flipkart ZAP Security Test Report";
			String  reportfilename="flipkart_zap_security_report.html";
			String  reportdir_TargetFold=System.getProperty("user.dir");
			String theme = "default";


			System.out.println(title+"\n"+template+"\n"+description+"\n"+reportfilename+"\n"+reportdir_TargetFold);



			try {
				ApiResponse	response = api.reports.generate(title,template,theme,description,null,null,null,null,null,reportfilename,null,reportdir_TargetFold,null);
				//		           	       api.reports.generate(title, template, theme, description, contexts, sites, sections, includedconfidences, includedrisks, reportfilename, reportfilenamepattern, reportdir, display)
				System.out.println("ZAP report generate at this location :" +response.toString());
			} catch (ClientApiException e) {
				e.printStackTrace();
			}

		}
		d.quit();
	}
}
