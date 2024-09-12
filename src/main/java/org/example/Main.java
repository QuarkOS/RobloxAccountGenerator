package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static String createUsername() {
        String[] usernameTemplates = {"QuackingQuark", "QuarkyQuack", "QuarkyQuacking", "EmilioQuacking", "QuantumQuark", "QuacktasticCoder",
                "QuarkyAdventurer", "QuackByte", "QuackFusion", "QuarkyDuckling", "Quacktivate", "QuarkyPixel", "Quackernaut"};
        Random random = new Random();
        String user = usernameTemplates[random.nextInt(usernameTemplates.length)] + random.nextInt(100);
        return user;
    }

    private static String createPassword() {
        Random random = new Random();
        char chartemplate[] = {'!', '@', '#', '$', '%', '^', '&', '*', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String pass = new String();
        for (int i = 0; i < 40; i++) {
            pass += chartemplate[random.nextInt(chartemplate.length)];
        }
        return pass;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Roblox Account Generator\n\n");

        // Create Webdriver
        WebDriver driver = new EdgeDriver();

        // Open Website
        driver.get("https://www.roblox.com/CreateAccount");

        // Loading Duration Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Output of Title Test
        System.out.println(driver.getTitle());

        // Find Specific Elements
        WebElement usernameField = driver.findElement(By.id("signup-username"));
        WebElement passwordField = driver.findElement(By.id("signup-password"));
        WebElement genderFieldMale = driver.findElement(By.id("MaleButton"));
        WebElement monthField = driver.findElement(By.id("MonthDropdown"));
        WebElement dayField = driver.findElement(By.id("DayDropdown"));
        WebElement yearField = driver.findElement(By.id("YearDropdown"));
        WebElement registerButton = driver.findElement(By.id("signup-button"));

        // Month Elements
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Random random = new Random();
        String randomMonth = months[random.nextInt(months.length)];
        WebElement randomMonthOption = driver.findElement(By.cssSelector("option[value='" + randomMonth + "']"));

        // Day Elements
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        String randomDay = days[random.nextInt(days.length)];
        if (randomMonth.equals("Feb")) {
            randomDay = days[random.nextInt(28)];
        }
        WebElement randomDayOption = driver.findElement(By.cssSelector("option[value='" + randomDay + "']"));

        // Year Elements
        String[] years = new String[16];
        for (int i = 0; i < 16; i++) {
            years[i] = String.valueOf(2005 - i);
        }
        String randomYear = years[random.nextInt(years.length)];
        WebElement randomYearOption = driver.findElement(By.cssSelector("option[value='" + randomYear + "']"));

        // Cookie Field Decline Button
        WebElement DeclineAllCookieButton = driver.findElement(By.xpath("//button[contains(@class, 'cookie-btn')]"));

        // Decline Cookie
        DeclineAllCookieButton.click();

        // Create Username and Password
        String username = createUsername();
        String password = createPassword();

        // Output of Username and Password
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("accounts.txt", true));
            writer.println("Username: " + username);
            writer.println("Password: " + password);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        genderFieldMale.click();
        monthField.click();
        randomMonthOption.click();
        dayField.click();
        randomDayOption.click();
        yearField.click();
        randomYearOption.click();
        registerButton.click();

        sc.next();

        driver.quit();
    }
}