package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmployeeSearchPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constans;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test
    public void addEmployee() {

        login.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeButton);

        sendText(addEmployeePage.firstNameField, "Majusz");
        sendText(addEmployeePage.lastNameField, "Jam");
        sendText(addEmployeePage.middleNameField, "Jezy");


        String empID = addEmployeePage.empIDLocator.getAttribute("value");
        click(addEmployeePage.saveButton);
        System.out.println(empID);

        click(dashboardPage.pimOption);
        click(dashboardPage.employeeListOption);

        sendText(employeeSearchPage.idField, empID);
        click(employeeSearchPage.searchButton);

        List<WebElement> rowData = employeeSearchPage.rowData;
        for (WebElement data : rowData) {
            String ActualID = data.getText();
            Assert.assertEquals(empID, ActualID);
        }

    }

    @Test
    public void addMultipleEmployee(String sheetName) throws InterruptedException {

        List<Map<String, String>> newEmployess = ExcelReader.excelIntoMap(Constans.TESTDATA_FILEPATH, sheetName);
        Iterator<Map<String, String>> itr = newEmployess.iterator();
        // it checks whether the next element exists or not
        while (itr.hasNext()) {
            // returns the key and value for employees
            Map<String, String> mapNewEmp = itr.next();
            System.out.println(mapNewEmp.get("FirstName"));
            System.out.println(mapNewEmp.get("MiddleName"));
            System.out.println(mapNewEmp.get("LastName"));

            // filling all the fields from the data coming from excel files
            sendText(addEmployeePage.firstNameField, mapNewEmp.get("FirstName"));
            sendText(addEmployeePage.middleNameField, mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastNameField, mapNewEmp.get("LastName"));

            String empIdValue = addEmployeePage.empIDLocator.getAttribute("value");
            sendText(addEmployeePage.photograph, mapNewEmp.get("Photograph"));
            if (!addEmployeePage.checkBox.isSelected()) {
                click(addEmployeePage.checkBox);
            }

            sendText(addEmployeePage.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployeePage.confirmPassword, mapNewEmp.get("Password"));
            click(addEmployeePage.saveButton);


            Thread.sleep(3000);

            //to verify, we will navbigate to employee list option
//            click(employeeSearchPage.empListOption);
//            sendText(employeeSearchPage.idField, empIdValue);
//            click(employeeSearchPage.searchButton);


            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);

                Assert.assertEquals(rowText,empIdValue);

                String expectedData = empIdValue + " " + mapNewEmp.get("FirstName") + " "
                        + mapNewEmp.get("MiddleName") + " " + mapNewEmp.get("LastName");
                org.junit.Assert.assertEquals(expectedData, rowText);
            }
            click(employeeSearchPage.addEmployeeOption);
            Thread.sleep(2000);




        }
    }
}
