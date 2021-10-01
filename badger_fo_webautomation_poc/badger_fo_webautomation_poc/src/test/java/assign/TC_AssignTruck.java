	package assign;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.BaseClass;
import config.Constants;
import config.DataUtils;
import pageObject.FODashBoardPage;
import utility.ExcelReader;
import utility.Util;

public class TC_AssignTruck extends BaseClass{
	
	public TC_AssignTruck() {
		testName = this.getClass().getSimpleName();
		xls = new ExcelReader(Constants.DATAFILE); // Loading the Excel Sheet 
	}

	@BeforeTest()
	public void setUpReport() throws IOException {
		if(spark==null)
			initiateReport();
		spark.start();
		test = report.createTest("FO | Assign Truck to Operator");
	}
	
	@BeforeMethod()
	public void verifyTestExecution() {
		if (DataUtils.isSkip(xls, testName)){
			test.log(Status.SKIP, "Skipping the test as runmode is NO in the Excel Sheet");
			skip = true;
			throw new SkipException("Skipping test case" + testName + " as runmode set to NO in excel");
		}
	}

	@Test(dataProvider = "getWebData")
	public void assignTruck(Hashtable<String,String> data) throws Exception {
		if (!data.get("RunMode").equalsIgnoreCase("N")){
			test.createNode(data.get("os")+" "+data.get("browser"));
			
			FODashBoardPage page = new FODashBoardPage(Constants.BadgerObjectRepository, test);
			page
			.setBrowserStackCap(data, "Badger")
			.launchInstance(data.get("browser"));
			page
			.openApplication(data.get("Url"))
			.getLogin(data)
			.postLogin(data);
			page
			.searchRegion(data.get("SearchInc"))
			.selectRegion(data.get("SelectRegion"), 0)
			.clearSearchRegion()
			.expandRegion(data.get("SelectRegion"))
			.sliptScreen()
			.assignTruckToOperator(data.get("Trucks"), data.get("Operator"))
			.updateActivity()
			.verifyAssigned(data.get("Trucks"), data.get("Operator"))
			.selectRegion(data.get("SelectRegion"), 1)
			.clickListView()
			.selectCalendar()
			.nonScheduled();
			data = page.verifyAssignedJob(data, page.dragFirstJob(data));
			if(page.verifyJobAndChangeTime(data, "12:00PM")) {
				page.verifyJobAndChangeTime(data, "2:00PM");
				page.verifyJobAndChangeTime(data, "4:00PM");
			}
			page.setUpRoute(data);
			page.closeInstance();
		}else {
			test.log(Status.SKIP, "Skipping the test as runmode is NO in the Excel Sheet");
		}
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		Util page = (Util) result.getAttribute("Util");
		
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = page.getScreenShot(page.getDriver(), result.getName());
			//To add it in the extent report 
			test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));
			page.updateBrowserStack("failed",result.getThrowable());
			page.getDriver().quit();
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		spark.stop();
		report.flush();
	}

	@DataProvider()
	public Object[][] getWebData(){
		return DataUtils.getData(xls, testName, "Web");
	}
}
