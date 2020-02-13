package com.service.middleware.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Parser of Rest NGSI protocol parse rest to object
 * 
 * @author Herbertt Diniz
 */
public class ParserJson {

	public static Entity parseEntity(String s) {
		Entity e = new Entity();
		Gson gson = new Gson();
		List<Attributes> lAtt = new ArrayList<Attributes>();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
			JSONObject structure = (JSONObject) jsonObject.get("contextElement");
			Type listType = new TypeToken<ArrayList<Attributes>>() {
			}.getType();
			lAtt = gson.fromJson(structure.get("attributes").toString(), listType);

			e.setId(structure.get("id").toString());
			e.setType(structure.get("type").toString());
			e.setAttributes(lAtt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;

	}
	
	@SuppressWarnings("rawtypes")
	public static List<Entity> parseListEntity(String s) throws Exception {
		List<Entity> listEntity = new ArrayList<Entity>();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(s.trim());
		JSONArray lang = (JSONArray) jsonObject.get("contextResponses");
		if (lang != null) {
			Iterator i = lang.iterator();
			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				if (innerObj != null)
					listEntity.add(ParserJson.parseEntity(innerObj.toString()));
			}
		}
		return listEntity;

	}

	public static Entity parseMongo(String s) {
		Entity e = new Entity();
		Gson gson = new Gson();
		List<Attributes> lAtt = new ArrayList<Attributes>();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
			JSONObject structure = (JSONObject) jsonObject.get("contextElement");
			Type listType = new TypeToken<ArrayList<Attributes>>() {
			}.getType();
			lAtt = gson.fromJson(structure.get("attributes").toString(), listType);

			e.setId(structure.get("_id").toString());
			e.setType(structure.get("type").toString());
			e.setAttributes(lAtt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

}