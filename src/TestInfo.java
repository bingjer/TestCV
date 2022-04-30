// This file TestInfo.java writes and reads JSON text files containing TestCV test information.

import java.util.Vector;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class TestInfo {
	private static FileWriter file;
	
	
	public void write_json(String file_name, Vector<PhaseInfo> phase_info_vec) throws IOException {
		JsonObject json_obj = new JsonObject();
		int counter = 1;
		
		for(PhaseInfo phase : phase_info_vec) {
			JsonObject inner_obj = new JsonObject();
			inner_obj.addProperty("Phase", phase.get_phase_name());
			inner_obj.addProperty("Element", phase.get_element());
			inner_obj.addProperty("Screenshot", phase.get_screenshot());
			inner_obj.addProperty("Interaction", phase.get_interaction_type());
			inner_obj.addProperty("Message", phase.get_message());
			inner_obj.addProperty("Time", phase.get_wait_time());
			System.out.println("here1");
			System.out.println(phase.get_screenshot());
			String string_counter = String.valueOf(counter);
			json_obj.add(string_counter, inner_obj);
			++counter;
		}
		//try {
			System.out.println("here1");
			System.out.println(json_obj.toString());
			System.out.println(file_name);
			file = new FileWriter(file_name);
			file.write(json_obj.toString());
		//} catch (IOException e) {
		//	e.printStackTrace();
		//	String opt_buttons[] = {"Ok"};
	    //    JOptionPane.showOptionDialog(null, "Could not write to file. Please try again.", "TestCV", 
	     //   		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
		//} finally {
		//	try {
				file.flush();
				file.close();
		//	} catch (IOException e) {
		//		e.printStackTrace();
		//		String opt_buttons[] = {"Ok"};
		//        JOptionPane.showOptionDialog(null, "Could not write to file. Please try again.", "TestCV", 
		    //    		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt_buttons, opt_buttons[0]);
		//	}
	//	}
	}
	
	public void load_json(String file_name, Vector<PhaseInfo> phase_vec) throws FileNotFoundException, JsonSyntaxException  {
		Object obj;
		FileReader read_file = new FileReader(file_name);
		obj = new JsonParser().parse(read_file);
		JsonObject json_obj = (JsonObject) obj;
		for(String key : json_obj.keySet()) {
			Object value = json_obj.get(key);
			JsonObject inner_json_obj = (JsonObject) value;
			PhaseInfo phase = new PhaseInfo();
			for(String inner_key : inner_json_obj.keySet()) {
				JsonElement inner_value = inner_json_obj.get(inner_key);
				System.out.println(inner_value);

				String inner_key_string = (String) inner_key;
				String inner_value_string = inner_value.toString();
				inner_value_string = inner_value_string.replace("\"", "");
				
				switch(inner_key_string) {
				case "Phase":
					phase.set_phase_name(inner_value_string);
					break;
				case "Element":
					String formated_element = inner_value_string.replace("\\\\", "\\");
					phase.set_element_path(formated_element);
					break;
				case "Screenshot":
					String formated_screenshot = inner_value_string.replace("\\\\", "\\");
					phase.set_expected_path(formated_screenshot);
					break;
				case "Interaction":
					phase.set_interaction_type(inner_value_string);
					break;
				case "Message":
					phase.set_message(inner_value_string);
					break;
				case "Time":
					phase.set_wait_time(Integer.parseInt(inner_value_string));
					break;
				default:
					break;
				
				}
				
			}
			phase_vec.add(phase);
		}
		
	}
	
}
