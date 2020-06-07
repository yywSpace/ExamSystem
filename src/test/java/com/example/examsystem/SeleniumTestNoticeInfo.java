// Generated by Selenium IDE
package com.example.examsystem;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class SeleniumTestNoticeInfo {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\ws\\Desktop\\软件测试---谢谦\\Tools\\Selenium\\geckodriver-v0.26.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void examTest() {
        driver.get("http://47.96.152.133:8080/");
        driver.manage().window().setSize(new Dimension(1550, 838));
        driver.findElement(By.name("account")).click();
        driver.findElement(By.name("password")).sendKeys("111");
        driver.findElement(By.name("account")).sendKeys("teacher");
        driver.findElement(By.cssSelector(".layui-show .layui-btn")).click();
        driver.findElement(By.linkText("考前操作")).click();
        driver.findElement(By.linkText("通知管理")).click();
        driver.findElement(By.id("content")).click();
        driver.findElement(By.id("content")).sendKeys("还有十分钟就交卷咯");
        driver.findElement(By.cssSelector(".layui-btn")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".layui-btn"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.linkText("注销")).click();
        driver.findElement(By.cssSelector("li:nth-child(2)")).click();
        driver.findElement(By.name("id")).click();
        driver.findElement(By.name("id")).sendKeys("1");
        driver.findElement(By.name("name")).sendKeys("1");
        driver.findElement(By.cssSelector(".layui-show .layui-btn")).click();
        assertThat(driver.findElement(By.cssSelector("td > .laytable-cell-2-0-1 > span")).getText(),
                is("还有十分钟就交卷咯"));
    }
}