package stepDefs;

import au.com.bytecode.opencsv.CSVReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.tools.ant.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyStepdefs {
    public WebDriver webDriver;


    @Given("The user is on Manage Employee Landing page")
    public void theUserIsOnManageEmployeeLandingPage() {
       // Project System;
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://sensedev.highcoordination.de:8080/hicotest/index.html");
    }

    @Then("The user input required information")
    public void theUserInputRequiredInformation() throws InterruptedException, IOException {
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\MARIG\\IdeaProjects\\untitled\\target\\Book1.csv"));

        String csvCell[];
        while((csvCell =reader.readNext()) != null){

            String Fname = csvCell[0];
            String Lname = csvCell[1];
            String Salutation = csvCell[2];
            String employeeNo = csvCell[3];
            String grossSalary = csvCell[4];

            webDriver.findElement(By.xpath("//input[@id='firstNames']")).sendKeys(Fname);
            webDriver.findElement(By.xpath("//input[@id='surname']")).sendKeys(Lname);
            webDriver.findElement(By.xpath("//select[@id='title']")).click();
            Thread.sleep(1000);
            Select drpSalutation = new Select(webDriver.findElement(By.xpath("//select[@id='title']")));
            drpSalutation.selectByValue(Salutation);


            if(Salutation ==("Mrs")){
               webDriver.findElement(By.xpath("//input[@id='formHorizontalRadiosGender2']")).click();
            } else if (Salutation ==("Ms")) {
                webDriver.findElement(By.xpath("//input[@id='formHorizontalRadiosGender2']")).click();
            } else if (Salutation ==("Mr")) {
                webDriver.findElement(By.xpath("//input[@id='formHorizontalRadiosGender1']")).click();
            } else {
                webDriver.findElement(By.xpath("//input[@id='formHorizontalRadiosGender3']")).click();
            }
            Thread.sleep(1000);
            webDriver.findElement(By.xpath("//input[@id='employeeNumber']")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@id='employeeNumber']")).clear();
            webDriver.findElement(By.xpath("//input[@id='employeeNumber']")).sendKeys(employeeNo);
            webDriver.findElement(By.xpath("//input[@id='salary']")).clear();
            webDriver.findElement(By.xpath("//input[@id='salary']")).sendKeys(grossSalary);

            webDriver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//button[contains(text(),'Add Employee')]")).click();

            Thread.sleep(5000);
           // webDriver.switchTo().alert().accept();

          //
        }
        webDriver.quit();
    }


    @And("Click Remove Employee Button")
    public void clickRemoveEmployeeButton() throws InterruptedException {
        webDriver.findElement(By.xpath("//button[contains(text(),'Remove Employee')]")).click();
        webDriver.close();
    }

    @And("Edit the details and click Add Employee")
    public void editTheDetailsAndClickAddEmployee() throws IOException, InterruptedException {

        webDriver.findElement(By.xpath("//input[@id='salary']")).clear();
        webDriver.findElement(By.xpath("//input[@id='salary']")).sendKeys("36000");
        webDriver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        webDriver.findElement(By.xpath("//input[@id='formHorizontalRadiosProfile1']")).click();
        Thread.sleep(5000);
        webDriver.quit();
    }

    @Then("User select employee on a table")
    public void userSelectEmployeeOnATable() throws InterruptedException {
        webDriver.findElement(By.xpath("//tbody/tr[1]")).click();

    }

    @And("cancel employee details")
    public void cancelEmployeeDetails() throws InterruptedException {
        try {
            new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Cancel')]")));
            System.out.println("Element is clickable");
        }
        catch(TimeoutException e) {
            System.out.println("Element is not clickable");
        }
        Thread.sleep(5000);
        webDriver.quit();
    }
}
