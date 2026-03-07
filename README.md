<h1><a href="https://www.github.com/Daniel-Carbajal/se333-assignment5-Part-2">Assignment 5 Part 2 - Playwright UI Testing</a> Write-Up</h1>
<h4>Author: Daniel Carbajal</h4>
<h4>Course: SE333 Software Testing and Quality Assurance</h4>

---

[![SE333_CI](https://github.com/Daniel-Carbajal/se333-assignment5-Part-2/actions/workflows/playwright.yml/badge.svg)](https://github.com/Daniel-Carbajal/se333-assignment5-Part-2/actions/workflows/playwright.yml)
<h2>Project Overview</h2>
<p>This project implements automated UI testing for the DePaul University
Bookstore website using the Playwright framework with Java and JUnit.</p>
<p>It is the second part of an assignment that covers unit testing with mockito, integration testing, and ultimately UI testing with playwright both manually and automatically.</p>

<p>See part 1 of the assignment here: <a href="">se333-assignment5-Part-1</a></p>

The goal of this assignment is to compare two approaches to UI testing:
1. **Traditional Playwright Testing** -> <a href="https://github.com/Daniel-Carbajal/se333-assignment5-Part-2/tree/master/src/test/java/playwrightTraditional">BookstoreTest.java</a>
    - Tests werte written manually using Playwright code generation and manual refinement.
    - The tests automate a purchase workflow on the DePaul bookstore site
3. **AI-Assisted Playwright Testing** -> <a href="https://github.com/Daniel-Carbajal/se333-assignment5-Part-2/tree/master/src/test/java/playwrightLLM">BookstoreTestLLM.java</a>
    - Tests were generated using Playwright MCP and an AI agent through a natural language prompt defining test flow.
    - THe generated tests were then adjusted to compile and execute successfully in the project.

<p>Both approaches automate the same user scenario involving searching for earbuds, filtering products, adding an item to the cart, completing the checkout flow, and removing the item from the cart.</p>

---

<h2>GitHub Actions Workflow</h2>
<p>The project uses GitHub Actions to automatically run the Playwright UI tests whenever code is pushed to the repository. The workflow is defined in <a href="https://github.com/Daniel-Carbajal/se333-assignment5-Part-2/blob/master/.github/workflows/playwright.yml">playwright.yml</a></p>
<p>See workflow <a href="https://github.com/Daniel-Carbajal/se333-assignment5-Part-2/actions/workflows/playwright.yml">here</a></p>
The workflow performs the following steps:
<ol>
  <li>Checks out the repository</li>
  <li>Sets up Java</li>
  <li>Installs Playwright browser dependencies</li>
  <li>Runs the automated UI tests with Maven</li>
  <li>Uploades recorded test execution videos as artifacts</li>
</ol>

---

<h2>Traditional Playwright Test</h2>
<p>The 'playwrightTraditional' package contains tests written manually using Playwright and JUnit. The tests were initially generated using the Playwright code generation tool and then cleaned up and extended with assertions.</p>
The automated workflow verifies:
<ol>
  <li>Searching for earbuds</li>
  <li>Applying product filters (Brand, Color, Price)</li>
  <li>Opening a specific product page</li>
  <li>Verifying product information</li>
  <li>Adding item to the cart</li>
  <li>Verifying cart contents and pricing</li>
  <li>Completing checkout steps as a guest</li>
  <li>Verifying payment tools</li>
  <li>Returning to the cart and removing the item</li>
  
</ol>

---

<h2>AI-Assisted Playwright Test</h2>
<p>The 'playwrightLLM' package contains tests generated using Playwright MCP with an AI agent assistant. The natural language prompt 'playwrightTester.prompt.md' was used to describe the desired test scenario (same as manual scenario above) and the agent generated Playwright Java test code.</p>
<p>The generated code was then integrated into the project and adjusted to ensure the tests compiled and executed correctly.</p>
See prompt <a href="https://github.com/Daniel-Carbajal/se333-assignment5-Part-2/blob/master/.github/prompts/playwrightTester.prompt.md">here</a>

---

<h1>Reflection: Traditional vs AI-Assisted UI Testing</h1>

<h2>Ease of Writing and Running Tests</h2>
<p>The traditional Playwright approach required manually recording interactions using the Playwright code generation tool and then refining the generated code. While this required more effort initially, it provided a clear understanding of how the selectors and interactions worked.</p>
<p>The AI-assisted approach allowed tests to be generated quickly using natural language prompt. This significantly reduced the time required to produce and initial version of the test, as long as the prompt was accurate. </p>
<p>However, early attempts revealed that an unclear prompt does not suffice. If the testing workflow is clearly defined, with requirements, it seems producing a version of the test is very efficient and simple.</p>

<h2>Accuracy and Reliability of Generated Tests</h2>
<p>The traditional tests were generally more reliable because the selectors were chosen intentionally and verified during development. </p>
<p>The AI-generated tests sometimes chose selectors that caused strict-mode violations. However, these were very simple fixes that could be done manually or included into the agents context.</p>
<p>I think the manual tests were a little bit more reliable in this scenario.</p>

<h2>Maintenance Effort</h2>
<p>Manual Playwright tests seem to be easier to maintain over time because the tests generated with code generation seem easier to understand than that generated by AI-assisted. It is also easier as a developer to understand exactly how the tests interact with the UI as it was recorded manually.</p>
<p>AI-generated tests can accelerate initial development but a lot of the selectors and patterns seem much more complex. Hence they were harder to understand and likely harder to maintain as a result. Human oversight is still necessary to ensure maintainability.</p>

<h2>Limitations and Issues</h2>
<p>I encountered two main issues during development during both the manual and AI-assisted testing. Firstly, Playwright strict mode errors occured because selectors would match multiple elements on the page. Secondly, sometimes there were hidden duplicate elements in the DOM.</p>
<p>Despite these issues, I think the MCP agent was still very helpful for generating initial test structure quickly, especially if you have larger tests to scale. Manual refinement was still required, but mainly when prompts are not clear.</p>
<p>With clear requirements for testing workflows expressed in the prompt, it seems possible to reduce the need for majority of the manual refinement done.</p>


