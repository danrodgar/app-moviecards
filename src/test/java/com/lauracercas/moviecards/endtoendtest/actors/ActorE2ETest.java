package com.lauracercas.moviecards.endtoendtest.actors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.lauracercas.moviecards.util.Messages.NEW_ACTOR_TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActorE2ETest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        // options.addArguments("--no-sandbox");
        // options.addArguments("--disable-dev-shm-usage");
        // System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_PATH"));

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void testPageLoad() {
        driver.get("http://localhost:9002/actors/new");
        assertEquals("FichasPeliculasApp | Aplicación de gestión de fichas de películas", driver.getTitle());

        assertTrue(driver.findElement(By.id("name")).isDisplayed());
        assertTrue(driver.findElement(By.id("birthDate")).isDisplayed());
        assertTrue(driver.findElement(By.id("country")).isDisplayed());

    }

    @Test
    public void testNewActorTitle() {
        driver.get("http://localhost:9002/actors/new");
        WebElement title = driver.findElement(By.className("title"));
        assertEquals(NEW_ACTOR_TITLE, title.getText());
    }

    @Test
    public void testListActors() {
        driver.get("http://localhost:9002/actors");
        WebElement title = driver.findElement(By.className("card-header"));
        assertEquals("Listado Actores", title.getText());

        WebElement table = driver.findElement(By.className("table-hover"));

        WebElement thead = table.findElement(By.tagName("thead"));
        assertTrue(thead.isDisplayed());

        WebElement headerRow = thead.findElement(By.tagName("tr"));
        assertEquals("Identificador", headerRow.findElements(By.tagName("th")).get(0).getText());
        assertEquals("Nombre", headerRow.findElements(By.tagName("th")).get(1).getText());
        assertEquals("Fecha Nacimiento", headerRow.findElements(By.tagName("th")).get(2).getText());
        assertEquals("Pais", headerRow.findElements(By.tagName("th")).get(3).getText());
        assertEquals("Editar", headerRow.findElements(By.tagName("th")).get(4).getText());

    }

}
