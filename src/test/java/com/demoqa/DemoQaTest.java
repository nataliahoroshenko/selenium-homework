package com.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoQaTest {

    private static final By ELEMENTS_CARD = By.xpath("//div[@class='card-body']/h5[text()='Elements']");
    private static final By BUTTONS_MENU_ITEM = By.id("item-4");
    private static final By DOUBLE_CLICK_BUTTON = By.id("doubleClickBtn");
    private static final By MESSAGE_DOUBLE_CLICK_BUTTON = By.id("doubleClickMessage");
    private static final String EXPECTED_MESSAGE_DOUBLE_CLICK_BUTTON = "You have done a double click";
    private static final By RIGHT_CLICK_BUTTON = By.id("rightClickBtn");
    private static final By MESSAGE_RIGHT_CLICK_BUTTON = By.id("rightClickMessage");
    private static final String EXPECTED_MESSAGE_RIGHT_CLICK_BUTTON = "You have done a right click";
    private static final By SPAN_TEXT_RADIO_BUTTON = By.xpath("//span[text()='Radio Button']");
    private static final By LABEL_FOR_YES_RADIO = By.xpath("//label[@for='yesRadio']");
    private static final By RESULT_CHOICE_RADIO = By.className("text-success");
    private static final String EXPECTED_MESSAGE_YES_RADIO = "Yes";
    private static final By LABEL_FOR_NOTES_CHECKBOX = By.xpath("//label[@for='tree-node-notes']");
    private static final By HOME_EXPAND = By.className("rct-collapse-btn");
    private static final By DESKTOP_EXPAND = By.xpath("(//button[@class='rct-collapse rct-collapse-btn'])[2]");
    private static final By RESULT_CHOICE_CHECKBOX = By.className("text-success");
    private static final String EXPECTED_MESSAGE_NOTES_CHECKBOX = "notes";
    private static final By SPAN_TEXT_CHECKBOX = By.xpath("//span[text()='Check Box']");
    private static final By TEXT_BOX_MENU_ITEM = By.id("item-0");
    private static final By CLICK_ME_BUTTON = By.xpath("//button[text()='Click Me']");
    private static final By MESSAGE = By.id("dynamicClickMessage");
    private static final By TEXT_BOX_TITLE = By.className("text-center");
    private static final String EXPECTED_MESSAGE = "You have done a dynamic click";
    private static final By FULL_NAME_FIELD = By.id("userName");
    private static final By EMAIL_FIELD = By.id("userEmail");
    private static final By CURRENT_ADDRESS = By.id("currentAddress");
    private static final By PERMANENT_ADDRESS = By.id("permanentAddress");
    private static final By SUBMIT_BUTTON = By.id("submit");
    private static final By SUBMITTED_NAME = By.id("name");
    private static final String FULL_NAME = "Charlie Parker";
    private static final String EMAIL = "charile@gmail.com";
    private static final String ADDRESS = "New York, 45 Avenue";

    WebDriver driver;

    @Test
    void testButtonClick() {
        WebElement buttonsMenuItem = driver.findElement(BUTTONS_MENU_ITEM);
        buttonsMenuItem.click();

        WebElement clickMeButton = driver.findElement(CLICK_ME_BUTTON);
        clickMeButton.click();

        WebElement message = driver.findElement(MESSAGE);
        String actualMessage = message.getText();
        assertEquals(EXPECTED_MESSAGE, actualMessage);
    }

    @Test
    void testTextBox() {
        WebElement buttonsMenuItem = driver.findElement(TEXT_BOX_MENU_ITEM);
        buttonsMenuItem.click();

        WebElement textBoxTitle = driver.findElement(TEXT_BOX_TITLE);
        String actualText = textBoxTitle.getText();
        assertEquals("Text Box", actualText);

        WebElement fullNameField = driver.findElement(FULL_NAME_FIELD);
        fullNameField.sendKeys(FULL_NAME);

        WebElement emailField = driver.findElement(EMAIL_FIELD);
        emailField.sendKeys(EMAIL);

        WebElement currentAddress = driver.findElement(CURRENT_ADDRESS);
        currentAddress.sendKeys(ADDRESS);

        WebElement permanentAddress = driver.findElement(PERMANENT_ADDRESS);
        permanentAddress.sendKeys(ADDRESS);

        WebElement submitButton = driver.findElement(SUBMIT_BUTTON);
        JavascriptExecutor jsSubmitButton = (JavascriptExecutor) driver;
        jsSubmitButton.executeScript("arguments[0].scrollIntoView(true)", submitButton);
        submitButton.click();

        WebElement submittedName = driver.findElement(SUBMITTED_NAME);
        String actualSubmittedNameValue = submittedName.getText();
        assertTrue(actualSubmittedNameValue.contains(FULL_NAME));
    }

    @Test
    void testDoubleClickButton() {
        WebElement buttonsMenuItem = driver.findElement(BUTTONS_MENU_ITEM);
        buttonsMenuItem.click();

        WebElement doubleClickButton = driver.findElement(DOUBLE_CLICK_BUTTON);
        Actions action = new Actions(driver);
        action.doubleClick(doubleClickButton).perform();

        WebElement messageDoubleClickButton = driver.findElement(MESSAGE_DOUBLE_CLICK_BUTTON);
        String actualMessageDoubleClickButton = messageDoubleClickButton.getText();
        assertEquals(EXPECTED_MESSAGE_DOUBLE_CLICK_BUTTON, actualMessageDoubleClickButton);
    }

    @Test
    void testRightClickButton() {
        WebElement buttonsMenuItem = driver.findElement(BUTTONS_MENU_ITEM);
        buttonsMenuItem.click();

        WebElement rightClickButton = driver.findElement(RIGHT_CLICK_BUTTON);
        Actions action = new Actions(driver);
        action.contextClick(rightClickButton).perform();

        WebElement messageRightClickButton = driver.findElement(MESSAGE_RIGHT_CLICK_BUTTON);
        String actualMessageRightClickButton = messageRightClickButton.getText();
        assertEquals(EXPECTED_MESSAGE_RIGHT_CLICK_BUTTON, actualMessageRightClickButton);
    }

    @Test
    void testRadioButton() {
        WebElement radioButtonMenuItem = driver.findElement(SPAN_TEXT_RADIO_BUTTON);
        radioButtonMenuItem.click();
        WebElement yesRadio = driver.findElement(LABEL_FOR_YES_RADIO);
        yesRadio.click();
        WebElement resultChoice = driver.findElement(RESULT_CHOICE_RADIO);
        assertEquals(EXPECTED_MESSAGE_YES_RADIO, resultChoice.getText());
    }

    @Test
    void testCheckBox() {
        WebElement checkBoxMenuItem = driver.findElement(SPAN_TEXT_CHECKBOX);
        checkBoxMenuItem.click();
        WebElement homeExpand = driver.findElement(HOME_EXPAND);
        homeExpand.click();
        WebElement desktopExpand = driver.findElement(DESKTOP_EXPAND);
        desktopExpand.click();
        WebElement notesCheckbox = driver.findElement(LABEL_FOR_NOTES_CHECKBOX);
        notesCheckbox.click();
        WebElement resultChoice = driver.findElement(RESULT_CHOICE_CHECKBOX);
        assertEquals(EXPECTED_MESSAGE_NOTES_CHECKBOX, resultChoice.getText());
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement elementsCard = driver.findElement(ELEMENTS_CARD);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", elementsCard);
        elementsCard.click();
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}

