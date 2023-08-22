package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ArticlesPO extends BasePO {

    public ArticlesPO() throws Exception {
        super();
    }

    //WEBELEMENTS
    @FindBy(xpath = "//article/h5[@class='card-title a-card-description']")
    private List<WebElement> articles_results_titles;

    @FindBy(xpath = "//p[@class='a-card-discount']")
    private List<WebElement> articles_results_prices;

    @FindBy(xpath = "//h1[@class='a-product__information--title']")
    private WebElement article_details_name;

    @FindBy(xpath = "//p[@class='a-product__paragraphDiscountPrice m-0 d-inline ']")
    private WebElement article_details_price;

    @FindBy(xpath = "//div[@class ='m-plp-cat-container d-none d-lg-block mb-5']")
    private WebElement articles_details_filters_section;

    @FindBy(xpath = "//a[@class ='a-link__viewMore']")
    private List<WebElement> ver_mas_links;

    @FindBy(xpath = "//p[@class ='a-plp-results-title']/span")
    private WebElement result_total_articles;

    //GETTERS
    public String get_article_details_name() {
        explicitWait_Visibility(article_details_name);
        return article_details_name.getText();
    }

    public String get_article_details_price() {
        explicitWait_Visibility(article_details_price);
        String articles_price = article_details_price.getText().replace("\n", "");
        String article_details_price_formatted =
                new StringBuilder(articles_price).insert(articles_price.length() - 2, ".").toString();
        return article_details_price_formatted;
    }

    public String get_result_total_articles() {
        explicitWait_Visibility(result_total_articles);
        return result_total_articles.getText();
    }

    //METHODS

    /**
     * @param text
     * @return Return the numbers of articles titles with the given string
     */
    public int validate_results(String text) {
        int count = 0;
        explicitWait_VisibilityofElements(articles_results_titles);

        // Loop to find results titles with the string parameters given
        for (WebElement articles : articles_results_titles) {
            if (articles.getText().toLowerCase().contains(text.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Click first articule with the given String and save the price of the article
     *
     * @param text
     * @return Return the price of the article selected
     */
    public String click_article_in_results_and_save_price(String text) throws Exception {
        explicitWait_VisibilityofElements(articles_results_prices);
        String article_price = "";
        String article_formated_price = "";

        // Loop to Click article with the string parameters given
        for (int x = 0; x < articles_results_titles.size(); x++) {
            if (articles_results_titles.get(x).getText().toLowerCase().contains(text.toLowerCase())) {
                article_price = articles_results_prices.get(x).getText();
                articles_results_titles.get(x).click();
                article_formated_price = new StringBuilder(article_price).insert(article_price.length() - 2, ".").toString();
                break;
            } else {
                throw new Exception("Product does not exists in the results");
            }
        }
        return article_formated_price;
    }

    /**
     * Click first article result with the given string
     *
     * @param text
     */
    public void click_article_in_the_results(String text) throws Exception {
        explicitWait_VisibilityofElements(articles_results_prices);

        for (WebElement article : articles_results_titles) {
            if (article.getText().toLowerCase().contains(text.toLowerCase())) {
                article.click();
                break;
            } else {
                throw new Exception("Product does not exists in the results");
            }
        }
    }

    /**
     * Validate filters sections exists using string given
     *
     * @param filter
     * @return return true if the filter exists
     */
    public Boolean validate_filter_exists(String filter) {
        explicitWait_VisibilityofElements(articles_results_titles);
        explicitWait_Visibility(articles_details_filters_section);
        explicitWait_Visibility(driver.findElement(By.xpath("//label[@title ='" + filter + "']")));
        return true;
    }

    /**
     * Click filter using given string
     *
     * @param filter
     */
    public void click_filter_by_name(String filter) throws InterruptedException {
        driver.findElement(By.xpath("//label[text() ='" + filter + "']//preceding::input[1]")).click();
        Thread.sleep(5000);
    }

    /**
     * CLick all the links with a given string
     *
     * @param linkText
     */
    public void click_linkS_by_text(String linkText) {
        explicitWait_Visibility(articles_details_filters_section);
        List<WebElement> links = driver.findElements(By.xpath("//a[contains(text(),'" + linkText + "')]"));
        for (WebElement link : links) {
            link.click();
        }
    }

    /**
     * Validate results are equals to the last filter applied, Input string for the last filter apply
     *
     * @param filter
     * @return total of articles filtered
     */
    public String validate_results_count_after_filter(String filter) {
        String total_articles = "";
        String total_articles_formatted = "";
        total_articles = driver.findElement(By.xpath("//label[text() ='" + filter + "']")).getText();
        total_articles_formatted = new StringBuilder(total_articles).
                substring(total_articles.length() - 4).replace(")", "").replace("(", "").replace(" ", "");
        return total_articles_formatted;
    }

}
