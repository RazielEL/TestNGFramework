package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmpDetPage extends CommonMethods {

    @FindBy (id="profile-pic")
    public static WebElement EmpVerText;

    public EmpDetPage() {
        PageFactory.initElements(driver,this);
    }

}
