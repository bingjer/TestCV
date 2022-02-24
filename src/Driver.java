import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Driver {
	
	
	public void navigate(String image_loc, String interaction_type) throws FindFailed {
        Screen s = new Screen();
        Pattern textbox = new Pattern("D:\\TestImages\\Capture.PNG");
        System.out.println("I am here 222222.");
        s.wait(textbox, 5000);
        s.type(textbox, "pooplava");
        
	}

}
