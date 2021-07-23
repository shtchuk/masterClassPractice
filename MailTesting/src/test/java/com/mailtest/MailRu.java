package com.mailtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MailRu
{
    private WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mail.ru/");
    }
    @Test
    public void testLogin()
    {
        WebElement loginField = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/input"));
        loginField.click();
        loginField.sendKeys("testovich.t@internet.ru");

        WebElement passwordButton = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/button[1]"));
        passwordButton.click();

        WebElement passField = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/input"));
        passField.sendKeys("passtest123");

        WebElement enterButton = driver.findElement((By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/button[2]")));
        enterButton.click();

        WebElement accountName = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[2]/span[2]"));
        boolean isLoggedin = accountName.getText().equalsIgnoreCase("testovich.t@internet.ru");
        Assert.assertTrue(isLoggedin);

        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/div[2]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[3]/div/div/a[3]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/button[1]")).getText().contains("Ввести пароль"));
    }

    @AfterClass
    public void reset()
    {
        driver.quit();
        driver = null;
    }
}
