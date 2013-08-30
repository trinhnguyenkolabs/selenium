package org.openqa.selenium.qtwebkit.quick_tests.interactions;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.testing.Ignore;
import org.openqa.selenium.testing.JUnit4TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class BasicKeyboardInterfaceTest extends JUnit4TestBase {

    @Before
    public void setUp() throws Exception {
        driver.get(pages.basicKeyboardInterfaceTest);
    }

    @Test
    public void testBasicKeyboardInput() {

        WebElement inputElement = driver.findElement(By.id("enabledTextElement"));

        inputElement.sendKeys("abc def");
        assertThat(inputElement.getText(), is("abc def"));
    }

    @Test
    public void testSendingSingleKeyDownUp() {

        WebElement keysEventInput = driver.findElement(By.id("enabledTextElement"));
        WebElement resultElementDown = driver.findElement(By.id("resultIsKeyDown"));
        WebElement resultElementUp = driver.findElement(By.id("resultIsKeyUp"));
        WebElement resultElementModifiers = driver.findElement(By.id("resultModifier"));

        keysEventInput.sendKeys("a");
        assertThat(resultElementDown.getText(), is("Key Down"));
        assertThat(resultElementUp.getText(), is("Key Up"));
        assertThat(resultElementModifiers.getText(), is(""));
    }

    @Test
    public void testSendingKeysShiftOnly() {

        WebElement inputElement = driver.findElement(By.id("enabledTextElement"));
        WebElement resultElement = driver.findElement(By.id("resultModifier"));

        inputElement.sendKeys(Keys.SHIFT);
        assertThat(resultElement.getText(), containsString("Shift"));
        assertThat(inputElement.getText(), is(""));
    }

    @Test
    public void testSendingKeyCtrlOnly() {

        WebElement inputElement = driver.findElement(By.id("enabledTextElement"));
        WebElement resultElement = driver.findElement(By.id("resultModifier"));

        inputElement.sendKeys(Keys.CONTROL);
        assertThat(resultElement.getText(), containsString("Ctrl"));
        assertThat(inputElement.getText(), is(""));
    }

    @Test
    public void testSendingKeyAltOnly() {

        WebElement inputElement = driver.findElement(By.id("enabledTextElement"));
        WebElement resultElement = driver.findElement(By.id("resultModifier"));

        inputElement.sendKeys(Keys.ALT);
        assertThat(resultElement.getText(), containsString("Alt"));
        assertThat(inputElement.getText(), is(""));
    }

    @Test
    @Ignore(value = {Ignore.Driver.QTWEBKIT},
            reason = "won't fix in near future(hard to resolve without regressions)",
            issues = 721)
    public void testSendingKeysWithShiftPressed() {

        WebElement inputElement = driver.findElement(By.id("enabledTextElement"));
        WebElement resultElement = driver.findElement(By.id("resultModifier"));

        inputElement.sendKeys("ab" + Keys.SHIFT);
        assertThat(resultElement.getText(), containsString("Shift"));

        assertThat(inputElement.getText(), is("AB"));

    }

    @Test
    public void testSendingKeysWithCtrlPressed() {

        WebElement inputElement = driver.findElement(By.id("enabledTextElement"));
        WebElement resultElement = driver.findElement(By.id("resultModifier"));

        inputElement.sendKeys("www" + Keys.CONTROL);
        assertThat(resultElement.getText(), containsString("Ctrl"));
        assertThat(inputElement.getText(), is("www"));

    }

    @Test
    public void testSendingKeysWithAltPressed() {

        WebElement inputElement = driver.findElement(By.id("enabledTextElement"));
        WebElement resultElement = driver.findElement(By.id("resultModifier"));

        inputElement.sendKeys("nnn" + Keys.ALT);
        assertThat(resultElement.getText(), containsString("Alt"));
        assertThat(inputElement.getText(), is("nnn"));

    }

}
