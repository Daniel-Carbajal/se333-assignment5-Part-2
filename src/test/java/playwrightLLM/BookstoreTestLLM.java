package playwrightLLM;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BookstoreTestLLM {
    @Test
    void testDePaulBookstoreEarbudPurchaseFlow() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setRecordVideoDir(Paths.get("videos/"))
                            .setRecordVideoSize(1280, 720)
            );
            Page page = context.newPage();

            // Navigate to DePaul bookstore
            page.navigate("https://depaul.bncollege.com/");

            // Search for "earbuds"
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search"))
                    .fill("earbuds");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search"))
                    .press("Enter");

            // Wait for search results to load
            page.waitForLoadState();

            // Filter by Brand = JBL
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("brand"))
                    .click();
            page.locator(".facet__list.js-facet-list.js-facet-top-values > li:nth-child(3) > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg")
                    .first()
                    .click();

            // Filter by Color = Black
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color"))
                    .click();
            page.locator("#facet-Color > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark")
                    .first()
                    .click();

            // Filter by Price = Over $50
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price"))
                    .click();
            page.locator("#facet-price > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg")
                    .click();

            // Open the JBL Quantum True Wireless Noise Cancelling Gaming Earbuds - Black product
            page.getByTitle("JBL Quantum True Wireless")
                    .first()
                    .click();

            // Wait for product page to load
            page.waitForLoadState();

            // Assert product name
            assertThat(page.locator("body"))
                    .containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds");

            // Assert SKU
            assertThat(page.locator("body"))
                    .containsText("668972707");

            // Assert price
            assertThat(page.locator("body"))
                    .containsText("$149.98");

            // Assert description
            assertThat(page.locator("body"))
                    .containsText("Gaming Earbuds");

            // Add 1 item to cart
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart"))
                    .click();

            // Wait for cart update
            page.waitForTimeout(1000);

            // Assert cart shows 1 item
            assertThat(page.locator("body"))
                    .containsText("1 items");

            // Open cart
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items"))
                    .click();

            // Wait for cart page to load
            page.waitForLoadState();

            // Assert shopping cart page
            assertThat(page.locator("body"))
                    .containsText("Your Shopping Cart");

            // Assert product name in cart
            assertThat(page.locator("body"))
                    .containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black");

            // Assert quantity 1
            assertThat(page.locator("body"))
                    .containsText("1");

            // Assert price $149.98 in cart
            assertThat(page.locator("body"))
                    .containsText("$149.98");

            // Select FAST In-Store Pickup
            page.getByText("FAST In-Store PickupDePaul")
                    .click();

            // Wait for pickup option to be selected
            page.waitForTimeout(500);

            // Assert subtotal $164.98
            assertThat(page.locator("body"))
                    .containsText("$164.98");

            // Assert handling $3.00
            assertThat(page.locator("body"))
                    .containsText("$3.00");

            // Assert taxes TBD
            assertThat(page.locator("body"))
                    .containsText("TBD");

            // Assert estimated total $167.98
            assertThat(page.locator("body"))
                    .containsText("$167.98");

            // Enter promo code TEST
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code"))
                    .fill("TEST");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Promo Code"))
                    .click();

            // Assert rejection message (Create Account prompt appears)
            page.waitForTimeout(500);
            assertThat(page.locator("body"))
                    .containsText("Create Account");

            // Proceed to checkout
            page.getByLabel("Proceed To Checkout")
                    .click();

            // Wait for checkout page
            page.waitForLoadState();

            // Assert Create Account page and proceed as guest
            assertThat(page.locator("body"))
                    .containsText("Create Account");

            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed As Guest"))
                    .click();

            // Wait for contact info page
            page.waitForLoadState();

            // Assert Contact Information page
            assertThat(page.locator("body"))
                    .containsText("Contact Information");

            // Fill contact information
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)"))
                    .fill("Dan");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)"))
                    .fill("Carbajal");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)"))
                    .fill("dan@example.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)"))
                    .fill("7085552765");

            // Assert pricing on contact info page - subtotal $164.98
            assertThat(page.locator("body"))
                    .containsText("$164.98");

            // Assert handling $3.00
            assertThat(page.locator("body"))
                    .containsText("$3.00");

            // Assert taxes TBD
            assertThat(page.locator("body"))
                    .containsText("TBD");

            // Assert total $167.98
            assertThat(page.locator("body"))
                    .containsText("$167.98");

            // Continue to next page
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"))
                    .click();

            // Wait for pickup information page
            page.waitForLoadState();

            // Assert pickup information page with correct name
            assertThat(page.locator("body"))
                    .containsText("Dan Carbajal");

            // Assert email
            assertThat(page.locator("body"))
                    .containsText("dan@example.com");

            // Assert phone
            assertThat(page.locator("body"))
                    .containsText("7085552765");

            // Assert pickup location
            assertThat(page.locator("body"))
                    .containsText("DePaul University Loop Campus & SAIC");

            // Assert pickup person
            assertThat(page.locator("body"))
                    .containsText("I'll pick them up");

            // Assert item
            assertThat(page.locator("body"))
                    .containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black");

            // Assert price $164.98
            assertThat(page.locator("body"))
                    .containsText("$164.98");

            // Continue to payment information
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"))
                    .click();

            // Wait for payment information page
            page.waitForLoadState();

            // Assert payment information page with subtotal $164.98
            assertThat(page.locator("body"))
                    .containsText("$164.98");

            // Assert handling $3.00
            assertThat(page.locator("body"))
                    .containsText("$3.00");

            // Assert taxes $17.22
            assertThat(page.locator("body"))
                    .containsText("$17.22");

            // Assert total $182.20
            assertThat(page.locator("body"))
                    .containsText("$182.20");

            // Go back to cart
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart"))
                    .click();

            // Wait for cart page
            page.waitForLoadState();

            // Delete the item from cart
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove product JBL Quantum"))
                    .click();

            // Wait for cart to update
            page.waitForTimeout(500);

            // Assert cart is empty
            assertThat(page.locator("body"))
                    .containsText("empty");

            // Clean up
            page.close();
            context.close();
            browser.close();
        }
    }
}
