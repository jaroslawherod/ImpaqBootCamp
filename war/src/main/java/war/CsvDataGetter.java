package war;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.impaq.app.Container;

public class CsvDataGetter
{
	public List<JsonObject> getjson(List<Container> people)
	{
		List<JsonObject> jsonlist = new ArrayList<JsonObject>();
		JsonObject jo;
		
		for(Container i : people)
		{
			jo = new JsonObject();
			jo.addProperty("name", i.getName() );
			jo.addProperty("id", i.getId() );
			jo.addProperty("address", i.getAddress() );
			jsonlist.add(jo);
		}
		
		return jsonlist;
	}
	
	
}
