package playwrightTraditional;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.*;

import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

@UsePlaywright
public class BookstoreTest {
    @Test
    void testEarbudPurchasePathway(Page page) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("videos/"))
                    .setRecordVideoSize(1280, 720));

            page.navigate("https://depaul.bncollege.com/");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("earbuds");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).press("Enter");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("brand")).click();
            page.locator(".facet__list.js-facet-list.js-facet-top-values > li:nth-child(3) > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color")).click();
            page.locator("#facet-Color > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price")).click();
            page.locator("#facet-price > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").click();
            page.getByTitle("JBL Quantum True Wireless").first().click();

            // 1. Product assertions
            assertThat(page.locator("body")).containsText("JBL Quantum True Wireless Noise Cancelling");
            assertThat(page.locator("body")).containsText("668972707");
            assertThat(page.locator("body")).containsText("$164.98");
            assertThat(page.locator("body")).containsText("Gaming Earbuds");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();

            // Items in cart assertion
            page.waitForTimeout(2000);
            assertThat(page.locator("body")).containsText("1 items");

            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items")).click();

            // 2. Shopping cart assertions
            assertThat(page.locator("body")).containsText("Your Shopping Cart");
            assertThat(page.locator("body")).containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black");
            assertThat(page.locator("body")).containsText("1");
            assertThat(page.locator("body")).containsText("$164.98");

            page.getByText("FAST In-Store PickupDePaul").click();

            // sidebar assertions
            assertThat(page.locator("body")).containsText("$164.98");
            assertThat(page.locator("body")).containsText("$3.00");
            assertThat(page.locator("body")).containsText("TBD");
            assertThat(page.locator("body")).containsText("$167.98");

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).fill("TEST");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Promo Code")).click();

            // 3. Promo code assertion
            assertThat(page.locator("body")).containsText("Create Account");
            page.getByLabel("Proceed To Checkout").click();

            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed As Guest")).click();
            // 4. Contact Info Page Assertions
            assertThat(page.locator("body")).containsText("Contact Information");

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).fill("Dan");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).fill("Carbajal");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).fill("dan@example.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).fill("7085552765");

            // Info assertions
            assertThat(page.locator("body")).containsText("$164.98");
            assertThat(page.locator("body")).containsText("$3.00");
            assertThat(page.locator("body")).containsText("TBD");
            assertThat(page.locator("body")).containsText("$167.98");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

            // 5. Pickup Information assertions
            assertThat(page.locator("body")).containsText("Dan Carbajal");
            assertThat(page.locator("body")).containsText("dan@example.com");
            assertThat(page.locator("body")).containsText("7085552765");
            assertThat(page.locator("body")).containsText("DePaul University Loop Campus & SAIC");
            assertThat(page.locator("body")).containsText("I'll pick them up");
            assertThat(page.locator("body")).containsText("$164.98");
            assertThat(page.locator("body")).containsText("$3.00");
            assertThat(page.locator("body")).containsText("TBD");
            assertThat(page.locator("body")).containsText("$167.98");
            assertThat(page.locator("body")).containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

            // 6. Payment Information assertions
            assertThat(page.locator("body")).containsText("$164.98");
            assertThat(page.locator("body")).containsText("$3.00");
            assertThat(page.locator("body")).containsText("$17.22");
            assertThat(page.locator("body")).containsText("$185.20");
            assertThat(page.locator("body")).containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black");

            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove product JBL Quantum")).click();

            // 7. Shopping Cart assertion
            assertThat(page.locator("body")).containsText("empty");

            context.close();
            browser.close();
        }
    }
}
