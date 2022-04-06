import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonElement;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
//import org.json.simple.parser.*;
import com.google.gson.stream.MalformedJsonException;

public class TestInfo {
	private static FileWriter file;
	
	
	public void write_json(String file_name, Vector<PhaseInfo> phase_info_vec) {
		JsonObject json_obj = new JsonObject();
		int counter = 1;
		
		for(PhaseInfo phase : phase_info_vec) {
			//System.out.println(phase.get_phase_name());
			//JSONArray phase_info_array = new JSONArray();
//			phase_info_array.add("Phase: " + phase.get_phase_name());
//			phase_info_array.add("URL: " + phase.get_url_name());
//			phase_info_array.add("Element: " + phase.get_element());
//			phase_info_array.add("Screenshot: " + phase.get_screenshot());
//			phase_info_array.add("Interaction: " + phase.get_interaction_type());
//			phase_info_array.add("Message: " + phase.get_message());
			//Map inner_obj = new LinkedHashMap();
			JsonObject inner_obj = new JsonObject();
			inner_obj.addProperty("Phase", phase.get_phase_name());
			inner_obj.addProperty("Element", phase.get_element());
			inner_obj.addProperty("Screenshot", phase.get_screenshot());
			inner_obj.addProperty("Interaction", phase.get_interaction_type());
			inner_obj.addProperty("Message", phase.get_message());
			inner_obj.addProperty("Time", phase.get_wait_time());
			
			//JSONObject inner_json_obj = new JSONObject();
			String string_counter = String.valueOf(counter);
			json_obj.add(string_counter, inner_obj);
			//System.out.println(obj);
			
			++counter;
		}
		//JsonObject json_obj = new JsonObject();
		try {
			file = new FileWriter(file_name);
			file.write(json_obj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			NotifyFrame nf = new NotifyFrame("Could not write to file. Please try again.");
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
				NotifyFrame nf = new NotifyFrame("Could not write to file. Please try again.");
			}
		}
	}
	
	public void load_json(String file_name, Vector<PhaseInfo> phase_vec) throws FileNotFoundException, JsonSyntaxException  {
		Object obj;
		FileReader read_file = new FileReader(file_name);
		//System.out.println(read_file);
		obj = new JsonParser().parse(read_file);
		//System.out.println(obj);
		JsonObject json_obj = (JsonObject) obj;
		//System.out.println(json_obj);
//		json_obj.keySet().forEach(key -> {
//			Object value = json_obj.get(key);
//			System.out.println(value);
//		});
		//System.out.println(json_obj);
		//System.out.println(inner_value_string);
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
					phase.set_element_path(inner_value_string);
					break;
				case "Screenshot":
					phase.set_expected_path(inner_value_string);
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
