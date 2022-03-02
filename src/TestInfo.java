import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class TestInfo {
	private static FileWriter file;
	
	
	public void write_json(Vector<PhaseInfo> phase_info_vec) {
		JSONObject json_obj = new JSONObject();
		int counter = 1;
		
		for(PhaseInfo phase : phase_info_vec) {
			System.out.println(phase.get_phase_name());
			//JSONArray phase_info_array = new JSONArray();
//			phase_info_array.add("Phase: " + phase.get_phase_name());
//			phase_info_array.add("URL: " + phase.get_url_name());
//			phase_info_array.add("Element: " + phase.get_element());
//			phase_info_array.add("Screenshot: " + phase.get_screenshot());
//			phase_info_array.add("Interaction: " + phase.get_interaction_type());
//			phase_info_array.add("Message: " + phase.get_message());
			JSONObject inner_obj = new JSONObject();
			inner_obj.put("Phase", phase.get_phase_name());
			inner_obj.put("URL", phase.get_url_name());
			inner_obj.put("Element", phase.get_element());
			inner_obj.put("Screenshot", phase.get_screenshot());
			inner_obj.put("Interaction", phase.get_interaction_type());
			inner_obj.put("Message", phase.get_message());
			
			json_obj.put(counter, inner_obj);
			++counter;
		}
		
		try {
			file = new FileWriter("C:\\Users\\adamn\\Desktop\\jsontest.txt");
			file.write(json_obj.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void load_json(String file, Vector<PhaseInfo> phase_vec) throws IOException, ParseException {
		Object obj;
		FileReader read_file = new FileReader("C:\\Users\\adamn\\Desktop\\jsontest.txt");
		obj = new JSONParser().parse(read_file);
		JSONObject json_obj = (JSONObject) obj;
		
		//System.out.println(json_obj);
//		json_obj.keySet().forEach(key -> {
//			Object value = json_obj.get(key);
//			System.out.println(value);
//		});
		for(Object key : json_obj.keySet()) {
			Object value = json_obj.get(key);
			JSONObject inner_json_obj = (JSONObject) value;
			PhaseInfo phase = new PhaseInfo();
			for(Object inner_key : inner_json_obj.keySet()) {
				Object inner_value = inner_json_obj.get(inner_key);
				String inner_key_string = (String) inner_key;
				String inner_value_string = (String) inner_value;
				System.out.println(inner_value_string);
				switch(inner_key_string) {
				case "Phase":
					phase.set_phase_name(inner_value_string);
					break;
				case "URL":
					phase.set_url(inner_value_string);
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
				default:
					break;
				
				}
				
			}
			phase_vec.add(phase);
		}
		
	}
	
}
