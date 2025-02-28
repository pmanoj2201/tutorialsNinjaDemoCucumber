package stepDefinitions;

import io.cucumber.java.en.*;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;

public class AccountRegistration 
{
	WebDriver driver;
	HomePage homePage;
	AccountRegistrationPage accPage;
	//MyAccountPage myAccPage;
	
	@Given("the user navigates to Register Account page")
	public void navigate_to_register_account_page() 
	{
		homePage= new HomePage(BaseClass.getDriver());
		homePage.clickMyAccount();
		homePage.clickRegister();
	}

	@When("the user enters the details into below fields")
	public void the_user_enters_the_details_into_below_fields(DataTable dataTable) 
	{
	    Map<String, String> dataMap= dataTable.asMap(String.class, String.class);
	    
	    accPage= new AccountRegistrationPage (BaseClass.getDriver());
	    accPage.setFirstName(dataMap.get("firstName"));
	    accPage.setLastName(dataMap.get("lastName"));
	    accPage.setEmail(BaseClass.randomAlphaNumeric()+ "@gmail.com");
	    accPage.setTelephone(dataMap.get("telephone"));
	    accPage.setPassword(dataMap.get("password"));
	    accPage.setConfPassword(dataMap.get("password"));
	        
	}

	@When("the user selects Privacy Policy")
	public void the_user_selects_privacy_policy() 
	{
	    accPage.setPrivacyPolicy();
	}

	@When("the user clicks on Continue button")
	public void the_user_clicks_on_continue_button() 
	{
	    accPage.clickContinue();
	}

	@Then("the user account should get created successfully")
	public void the_user_account_should_get_created_successfully() 
	{
		String confMsg= accPage.getConfirmationMsg();
		Assert.assertEquals(confMsg, "Your Account Has Been Created!");
	}

}
