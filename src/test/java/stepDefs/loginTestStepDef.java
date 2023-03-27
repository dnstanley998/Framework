package stepDefs;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboardPage;
import pages.loginPage;



public class loginTestStepDef extends Base{

    @Test
    public void TestBasicLoginFunctionality(){

        test = extent.createTest(loginTestStepDef.class.getName()).createNode("TestSuccessLoginFunctionality");
        loginPage loginpage = PageFactory.initElements(driver,loginPage.class);
        dashboardPage dashboardpage = PageFactory.initElements(driver, dashboardPage.class);
        loginpage.login("Admin","admin123");
        System.out.println(dashboardpage.getTitle());
        Assert.assertEquals(dashboardpage.getTitle(),"Dashboard","Error");


    }

//    @Test
    public void TestSuccessLoginFunctionality(){
        test = extent.createTest(loginTestStepDef.class.getName()).createNode("TestSuccessLoginFunctionality");

        loginPage loginpage = PageFactory.initElements(driver,loginPage.class);
        dashboardPage dashboardpage = PageFactory.initElements(driver,dashboardPage.class);


//        try{
            loginpage.login("Admin","admin1234");
            test.pass("login as Admin");
            Assert.assertEquals(dashboardpage.getTitle(),"Dashboard");
            test.pass("Validate that login is navigated to the Dashboard");
//        }
//        catch(Exception e){
//            test.fail("Login Not directed to the Dashboard");
////            test.fail(e);
//        }
//        extent.createTest("TestBasicLoginFunctionality")
//                .createNode("Child")
//                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'")
//                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'")
//                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'")
//                .info("info")
//                .pass("pass")
//                .warning("warn")
//                .skip("skip")
//                .fail("fail")
//                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild222'");
//        test = extent.createTest(loginTestStepDef.class.getName()).createNode("TestSuccessLoginFunctionalitya");
//        test.info("info one");
//        test = extent.createTest(loginTestStepDef.class.getName()).createNode("TestSuccessLoginFunctionalitya");
//        test.fail("fail");
//        test = extent.createTest(loginTestStepDef.class.getName()).createNode("TestSuccessLoginFunctionalitya");
//        test.warning("warning");
//        test = extent.createTest(loginTestStepDef.class.getName()).createNode("TestSuccessLoginFunctionalitya");
//        test.skip("skip");
    }




}
