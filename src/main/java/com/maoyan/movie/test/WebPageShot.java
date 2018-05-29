package com.maoyan.movie.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebPageShot {
    public static void pageShot(String falconUrl, String loginName, String passWord, String filePathAndName) {
        ChromeOptions options = new ChromeOptions();
        //headless设置chrome 后台运行
        //disable-infobars取消"Chrome正在受到自动软件的控制"提示
        options.addArguments("headless", "disable-infobars");
        options.addArguments("--start-maximized", "--start-fullscreen");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        //启动chrome实例
        WebDriver driver = new ChromeDriver(options);
        try {
            //全局隐式等待
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            //访问
            driver.get(falconUrl);
            //点击切换登录方式
            driver.findElement(By.id("form-img")).click();
            //显示等待控制对象
            WebDriverWait webDriverWait=new WebDriverWait(driver,10);
            //输入用户名和密码
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("login-username"))).sendKeys(loginName);
//            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("login-password"))).sendKeys(passWord);
            driver.findElement(By.id("login-username")).sendKeys(loginName);
            driver.findElement(By.id("login-password")).sendKeys(passWord);
            //点击提交
            driver.findElement(By.name("commit")).click();

            //等待1秒用于页面加载，保证Cookie响应全部获取。
            Thread.sleep(1000);

            WholePageShot.makeFullScreenshot(driver, filePathAndName);

            //指定了OutputType.FILE做为参数传递给getScreenshotAs()方法，其含义是将截取的屏幕以文件形式返回。
//            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件对象。
//            FileUtils.copyFile(srcFile, new File("/Users/huangchao/Downloads/screenshot.png"));
            //关闭浏览器
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<1; i++) {
//            pageShot("http://falcon.sankuai.com/screen/6368",
//                    "huangchao19",
//                    "1015__0824h",
//                    "/Users/huangchao/Downloads/fullscreenshot" + i + ".png"
//            );
            pageShot(args[0],
                    "huangchao19",
                    "1015__0824h",
                    args[1]
            );
        }
    }
}
