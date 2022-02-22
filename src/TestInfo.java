import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class TestInfo {
	private Vector<PhaseInfo> phaseInfo;
	
	
	public void write_json(String file) {
		JSONObject json_obj = new JSONObject();
		json_obj.put("test", "this");
	}
	
	public void load_json(String file) throws IOException, ParseException {
		Object obj;
		FileReader read_file = new FileReader(file);
		obj = new JSONParser().parse(read_file);
		JSONObject json_obj = (JSONObject) obj;
	}
	
}
