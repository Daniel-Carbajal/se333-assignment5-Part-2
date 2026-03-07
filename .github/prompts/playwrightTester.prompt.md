Generate a Playwright test in Java using JUnit for the DePaul bookstore site. Use the Playwright MCP tools to automate the following test flow.
If you can not use Playwright MCP tools, do not continue and respond with "Playwright is not available".

Test flow:
- Go to https://depaul.bncollege.com/
- Search for "earbuds"
- Filter by Brand = JBL
- Filter by Color = Black
- Filter by Price = Over $50
- Open the JBL Quantum True Wireless Noise Cancelling Gaming Earbuds - Black product
- Assert product name, SKU, price, and description
- Add 1 item to cart
- Assert cart shows 1 item
- Open cart
- Assert shopping cart page, product name, quantity 1, and price $149.98
- Select FAST In-Store Pickup
- Assert subtotal $164.98, handling $3.00, taxes TBD, estimated total $167.98
- Enter promo code TEST and assert rejection message
- Proceed to checkout
- Assert Create Account page and proceed as guest
- Fill contact information
- Assert subtotal $164.98, handling $3.00, taxes TBD, total $167.98
- Continue
- Assert pickup information page with correct name, email, phone, pickup location, pickup person, item, and price
- Continue
- Assert payment information page with subtotal $164.98, handling $3.00, taxes $17.22, total $182.20
- Go back to cart
- Delete the item
- Assert cart is empty

Use Java Playwright assertions, JUnit test structure, and record test execution videos to videos/ directory. Add the generated code into the test/java/playwrightLLM directory.