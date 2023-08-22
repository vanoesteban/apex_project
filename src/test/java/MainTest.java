import Controllers.DataController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testHelpers.MyListeners;

@Listeners(MyListeners.class)
public class MainTest extends BaseTest {
    public static Logger LOG = LoggerFactory.getLogger(MainTest.class);

    public MainTest() throws Exception {
    }

    @Test()
    public void tc001_verify_tile_price_of_item() throws Exception {
        LOG.info("::: STEP 01: Search in Main Bar :::");
        //mainPO.search_in_main_bar("playstation");
        mainPO.search_in_main_bar_magnifying_glass("playstation");
        LOG.info("::: STEP 02: Validate results contains playstation consoles :::");
        Assert.assertTrue(articlesPO.validate_results("Consola PlayStation") > 0, "Did not find Playstation consoles");
        LOG.info("::: STEP 03: Validate results contains playstation 5 games :::");
        Assert.assertTrue(articlesPO.validate_results("para PlayStation 5") > 0, "Results did not display games for playstation 5");
        LOG.info("::: STEP 04: select a playstation 5, validate the title and price of the item in the page displayed. :::");
        String price = articlesPO.click_article_in_results_and_save_price("Consola PlayStation 5");
        Assert.assertEquals(articlesPO.get_article_details_price(), price, "Prices does not match");
        Assert.assertTrue(articlesPO.get_article_details_name().contains("Consola PlayStation 5"), "Title does not match");
    }

    @Test()
    public void tc002_verify_results_after_filter() throws Exception {
        LOG.info("::: STEP 01: Search in Main Bar :::");
        //mainPO.search_in_main_bar("smart tv");
        mainPO.search_in_main_bar_magnifying_glass("smart tv");
        LOG.info("::: STEP 02: Validate results contains smart tv:::");
        Assert.assertTrue(articlesPO.validate_results("Smart TV") > 0, "Did not find Smart TV");
        LOG.info("::: STEP 03: select a Smart TV, validate Size, Price and Brand Filters are displayed. :::");
        Assert.assertTrue(articlesPO.validate_filter_exists("Tamaño"), "Filter does not exists");
        Assert.assertTrue(articlesPO.validate_filter_exists("Precios"), "Filter does not exists");
        Assert.assertTrue(articlesPO.validate_filter_exists("Marcas"), "Filter does not exists");
        LOG.info("::: STEP 04: Filter the results by size: 55 inches, price: > 10,000, brand: sony. :::");
        articlesPO.click_linkS_by_text("Ver más");
        articlesPO.click_filter_by_name("55 pulgadas");
        articlesPO.click_filter_by_name("Mas de $10000.0");
        articlesPO.click_filter_by_name("SONY");
        LOG.info("::: STEP 05: Validate the results count. :::");
        Assert.assertEquals(articlesPO.validate_results_count_after_filter("SONY"), articlesPO.get_result_total_articles(), "Results count does not match");
    }

    @Test()
    public void tc003_verify_subcategory_and_filter_displayed_for_brand() throws InterruptedException {
        LOG.info("::: STEP 01: Expand Categorias Menu :::");
        mainPO.hover_categorias_menu();
        LOG.info("::: STEP 02: Select Perfumes de Hombre into Categoria Belleza :::");
        mainPO.select_subcategory_for_category("Belleza", "Perfumes Hombre");
        LOG.info("::: STEP 03: Filter the results by Brand :::");
        articlesPO.click_linkS_by_text("Ver más");
        articlesPO.click_filter_by_name("BENTLEY");
        Assert.assertEquals(articlesPO.validate_results_count_after_filter("BENTLEY"), articlesPO.get_result_total_articles(), "Results count does not match");
    }

}
