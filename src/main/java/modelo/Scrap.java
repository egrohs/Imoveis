package modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Scrap {
	protected WebDriver driver;
	protected String siteURL;
	protected Document doc;

	public abstract String getUrl();

	// public abstract List<Imovel> getData(List<String> urls, Map<String,
	// String> campos) throws IOException;

	public Scrap(boolean needDriver) throws Exception {
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
		// imoveis = getData(imoveis);
		// JavaBeanToCsv.toCSV(politicos);// salva novos
		if (driver != null) {
			driver.close();
		}
	}

	public void getData(/*List<String> urls,*/ Map<String, String> campos) throws IOException {
		int paginas = Integer.parseInt(doc.select("div.pagination >span").get(0).text().replaceFirst("de ", ""));
		// System.out.println("$/m2 quartos vagas ano andares m2util m2total
		// valor condominio id rua bairro descr carcIM caracCond url temp");
		List<WebElement> urls = new ArrayList<WebElement>();
		//String url = getUrl().replaceFirst("\"?pagina\"?:\"?\\d+\"?", "\"pagina\":" + (pagina + 1));
		while (tem proximo) {
			// Elements sels = doc.select("div.list-cell > a[href*=\"http\"]");
			urls.addAll(driver.findElements(By.xpath(campos.get("url"))));
			// navega(proxima);
			// proxima pagina
			// vai montando as urls
		}
		for (WebElement url : urls) {
			// navega(url);
			for (String key : campos.keySet()) {
				String xpath = campos.get(key);
				String valor = driver.findElement(By.xpath(xpath)).getText();
				System.out.print(valor+"\t");
			}
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
	}
}
