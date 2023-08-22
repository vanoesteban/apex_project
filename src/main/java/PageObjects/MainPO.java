package PageObjects;

import Controllers.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class MainPO extends BasePO {


    //constructor
    public MainPO() throws Exception {
        super();
    }

    //WebElements
    @CacheLookup
    @FindBy(id = "mainSearchbar")
    private WebElement mainSearchbar;

    @CacheLookup
    @FindBy(xpath = "//button[@class='input-group-text']")
    private WebElement magnifyingGlass_Search;

    @CacheLookup
    @FindBy(xpath = "//span[@class='a-header__topLink']")
    private WebElement login_iniciar;

    @CacheLookup
    @FindBy(xpath = "//span[contains(@class, 'nav-desktop-menu-action')]")
    private WebElement categorias_menu;

    @FindBy(xpath = "//span[contains(@class, 'nav-desktop-menu-action')]")
    private WebElement categoias_dropdown_menu;

    @FindBy(xpath = "")
    private WebElement subcategorias_dropdown_menu;

    @FindBy(xpath = "//span[@class='a-header__topLink popover-session']")
    private WebElement session_name;

    @FindBy(xpath = "//div[@id='m-sessionPanel']//a[@class='a-header__sessionLink']")
    private List<WebElement> session_menu;

    public String get_session_name(){
        explicitWait_Visibility(session_name);
        return get_text(session_name);
    }

    public List<String> get_menu_list() {
        List<String> list = new ArrayList<String>();
        list.add("Mi cuenta");
        list.add("Mis Compras");
        list.add("Wishlist");
        list.add("Cupones");
        list.add("Mis Tarjetas");
        list.add("Cerrar sesión");

        return list;
    }

    // Methods

    /**
     * Search in the main bar with the string given
     *
     * @param text
     */
    public void search_in_main_bar(String text) {
        explicitWait_Visibility(mainSearchbar);
        inputText(mainSearchbar, text);
        mainSearchbar.sendKeys(Keys.ENTER);
    }

    public void search_in_main_bar_magnifying_glass(String text){
        explicitWait_Visibility(magnifyingGlass_Search);
        inputText(mainSearchbar, text);
        clickElement(magnifyingGlass_Search);
    }

    /**
     * Hover your mouse in categories to display the menu
     */
    public void hover_categorias_menu() throws InterruptedException {
            explicitWait_Visibility(categorias_menu);
            moveToAnElement(categorias_menu);
            Thread.sleep(3000);
    }

    /**
     * Select a ccategory and subcategory with the given strings
     * @param category
     * @param subcategory
     */
    public void select_subcategory_for_category(String category, String subcategory) throws InterruptedException {
            explicitWait_Visibility(categoias_dropdown_menu);
            WebElement category_menu = driver.findElement(By.xpath("//li//div/span[text() ='" + category + "']"));
            WebElement subcategory_menu = driver.findElement(By.xpath("//a[text() = '" + subcategory + "']"));
            moveToAnElement(category_menu);
            Thread.sleep(3000);
            clickElement(subcategory_menu);
    }

    public void click_iniciar_sesion(){
        explicitWait_Visibility(login_iniciar);
        clickElement(login_iniciar);
    }

    public List<String> get_information_iniciar_sesion_menn(){
        List<String> arraySessionMenu = new ArrayList<String>();

        for (int i=0; i<session_menu.size();i++){
            arraySessionMenu.add(session_menu.get(i).getText());
        }
        return arraySessionMenu;
    }

}
