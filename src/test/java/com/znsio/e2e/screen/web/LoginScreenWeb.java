package com.znsio.e2e.screen.web;

import com.znsio.e2e.screen.LoginScreen;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.znsio.e2e.tools.Wait.waitFor;

public class LoginScreenWeb extends LoginScreen {
    private final Driver driver;
    private final Visual visually;
    private final String screenName = LoginScreenWeb.class.getSimpleName();
    private final By userNameId = By.id("username");
    private final By passwordId = By.id("password");
    private final By loginButtonXpath = By.xpath("//button/i[contains(text(),\"Login\")]");
    private final By errorMessageId = By.id("flash");
    private final By dismissAlertXpath = By.xpath("//a[@href=\"#\"]");

    public LoginScreenWeb (Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.takeScreenshot(screenName, "Home screen");
    }

    @Override
    public LoginScreen enterLoginDetails (String username, String password) {
        waitFor(2);
        driver.findElement(userNameId).sendKeys(username);
        driver.findElement(passwordId).sendKeys(password);
        visually.takeScreenshot(screenName, "enterLoginDetails");
        visually.checkWindow(screenName, "entered login details");
        return this;
    }

    @Override
    public LoginScreen login () {
        driver.findElement(loginButtonXpath).click();
        waitFor(2);
        return this;
    }

    @Override
    public String getInvalidLoginError () {
        WebElement alertText = driver.waitForVisibilityOf(errorMessageId);
        visually.takeScreenshot(screenName, "Invalid Login alert");
        visually.checkWindow(screenName, "Invalid Login alert");
        return alertText.getText().trim();
    }

    @Override
    public LoginScreen dismissAlert () {
        waitFor(2);
        driver.waitForVisibilityOf(dismissAlertXpath).click();
        visually.takeScreenshot(screenName, "Invalid Login alert dismissed");
        return this;
    }
}
