import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightBookingAutomation {
    public static void main(String[] args) throws InterruptedException {
        // Set the path of the WebDriver
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        
        try {
            // Navigate to the travel website
            driver.get("https://www.goindigo.in/");
            Thread.sleep(3000); // Wait for the page to load
            
            // Verify landing on the correct page
            String currentUrl = driver.getCurrentUrl();
            String pageTitle = driver.getTitle();
            System.out.println("URL: " + currentUrl);
            System.out.println("Title: " + pageTitle);
            
            // Click on "Book" dropdown and select "Flight"
            driver.findElement(By.xpath("//a[text()='Book']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[text()='Flights']")).click();
            
            // Click on "1 Pax" and increase Adult count to 2
            driver.findElement(By.id("passenger")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(@aria-label, 'Increase adults')]")).click();
            driver.findElement(By.xpath("//button[text()='Done']")).click();
            
            // Enter "Hyderabad" in From and "Delhi" in To fields
            WebElement fromField = driver.findElement(By.id("from"));
            fromField.clear();
            fromField.sendKeys("Hyderabad");
            driver.findElement(By.xpath("//li[contains(text(), 'Hyderabad')]")).click();
            
            WebElement toField = driver.findElement(By.id("to"));
            toField.clear();
            toField.sendKeys("Delhi");
            driver.findElement(By.xpath("//li[contains(text(), 'Delhi')]")).click();
            
            // Select travel date (1 month from today)
            driver.findElement(By.id("departure")).click();
            LocalDate futureDate = LocalDate.now().plusMonths(1);
            String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            driver.findElement(By.xpath("//td[@data-date='" + formattedDate + "']")).click();
            
            // Click "Search Flight"
            driver.findElement(By.xpath("//button[text()='Search']")).click();
            Thread.sleep(5000); // Wait for results
            
            // Select first recommendation and click "Next"
            driver.findElement(By.xpath("(//button[text()='Select'])[1]")).click();
            driver.findElement(By.xpath("//button[text()='Next']")).click();
            
            // Enter passenger details
            driver.findElement(By.xpath("//input[@id='male']")).click(); // Gender
            driver.findElement(By.id("firstName")).sendKeys("John");
            driver.findElement(By.id("lastName")).sendKeys("Doe");
            driver.findElement(By.id("dob")).sendKeys("01/01/1990");
            
            System.out.println("Flight booking automation completed successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Take a screenshot (optional)
            // Capture screenshot code here using TakesScreenshot
            
            // Close the browser
            driver.quit();
        }
    }
}
