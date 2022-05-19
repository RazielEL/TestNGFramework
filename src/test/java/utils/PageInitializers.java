package utils;

import pages.*;

public class PageInitializers {

    public static LoginPage login;
    public static EmployeeSearchPage employeeSearchPage;
    public static AddEmployeePage addEmployeePage;
    public static EmpDetPage empDetPage;
    public static DashboardPage dashboardPage;


    public  static void initializePageObjects(){
        login = new LoginPage();
        employeeSearchPage = new EmployeeSearchPage();
        addEmployeePage = new AddEmployeePage();
        empDetPage = new EmpDetPage();
        dashboardPage = new DashboardPage();
    }
}
