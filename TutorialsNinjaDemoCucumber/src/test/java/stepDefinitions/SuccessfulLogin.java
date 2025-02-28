package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class SuccessfulLogin 
{
	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	
	List<HashMap<String, String>> datamap; //Data driven
	
	@Given("the user is on TutorialsNinja login page")
	public void navigate_to_login_page() 
	{
		homePage= new HomePage(BaseClass.getDriver());
		homePage.clickMyAccount();
		homePage.clickLogin();
	}

	@When("the user enters valid credentials \\(username:{string} password:{string})")
	public void the_user_enters_valid_credentials(String email, String password) 
	{
		loginPage=new LoginPage(BaseClass.getDriver());
		loginPage.setEmailAddress(email);
		BaseClass.getLogger().info("entered email");
		loginPage.setPassword(password);
		BaseClass.getLogger().info("entered password");
	}

	@When("the user clicks on login button")
	public void the_user_clicks_on_login_button() 
	{
		loginPage.clickLogin();
		BaseClass.getLogger().info("clicked login");
	}

	@Then("the user should be redirected to MyAccount page")
	public void the_user_should_be_redirected_to_my_account_page() 
	{
		myAccountPage= new MyAccountPage(BaseClass.getDriver());
		boolean targetPage= myAccountPage.isMyAccountExist();
		Assert.assertEquals("Login Failed...", targetPage, true);
	}
	
	
	 //*******   Data Driven test **************
    @Then("the user should be redirected to the MyAccount Page by passing username and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_username_and_password_with_excel_data(String rows)
    {
        try 
        {
			datamap=DataReader.data(System.getProperty("user.dir")+"\\test Data\\TutorialsNinja_LoginData.xlsx", "Sheet1");
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}

        int index=Integer.parseInt(rows)-1;
        String username= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

        loginPage=new LoginPage(BaseClass.getDriver());
        loginPage.setEmailAddress(username);
        loginPage.setPassword(pwd);

        loginPage.clickLogin();
        myAccountPage=new MyAccountPage(BaseClass.getDriver());
        try
        {
            boolean targetpage=myAccountPage.isMyAccountExist();
            System.out.println("target page: "+ targetpage);
            if(exp_res.equalsIgnoreCase("Valid"))
            {
                if(targetpage==true)
                {
                    MyAccountPage myaccpage=new MyAccountPage(BaseClass.getDriver());
                    myaccpage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp_res.equalsIgnoreCase("Invalid"))
            {
                if(targetpage==true)
                {
                	myAccountPage.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }


        }
        catch(Exception e)
        {

            Assert.assertTrue(false);
        }
      }
 
}


