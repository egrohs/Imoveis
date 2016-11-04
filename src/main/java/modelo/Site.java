package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Site {
	protected WebDriver driver;
	// private Document doc;
	protected String siteURL;
	protected List<Imovel> imoveis = new ArrayList<Imovel>();
	protected Document doc;

	public abstract String getUrl();

	public abstract List<Imovel> getData(List<Imovel> imoveis) throws IOException;

	public Site(boolean needDriver) throws Exception {
		if (needDriver) {
			// FirefoxProfile profile = new FirefoxProfile();
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			// WebDriver driver = new FirefoxDriver();
			// final WebDriver driver = new HtmlUnitDriver();
			// ((HtmlUnitDriver)driver).setJavascriptEnabled(true);
			driver = new ChromeDriver();
		}
		// List<Politico> politicos = JavaBeanToCsv.read();// le existentes
		navega(getUrl());
		imoveis = getData(imoveis);
		// JavaBeanToCsv.toCSV(politicos);// salva novos
		if (driver != null) {
			driver.close();
		}
	}

	protected Document navega(String url) {
		if (driver != null) {
			driver.get(url);
			driver.get(url);
		}
		espera();
		return lePagina();
	}

	protected Document lePagina() {
		if (driver != null) {
			doc = Jsoup.parse(driver.getPageSource());
			return doc;
		}
		try {
			doc = Jsoup.connect(getUrl())
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.get();
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void espera() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		// wait.until(ExpectedConditions.elementToBeClickable(this));
		wait.until(new ExpectedCondition<Boolean>() {
			// @Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		});
		wait.until(new ExpectedCondition<Boolean>() {
			// @Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		});
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// alt = Carregando... id("loadingBuscar")
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@alt='Carregando...'])[2]")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//*[@alt='Carregando...'])[2]")));
//		synchronized (this) {
//			try {
//				Thread.currentThread().wait(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		// e.click();
	}
}