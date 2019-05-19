package com.flagpicker.FlagPickerDemo.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagpicker.FlagPickerDemo.beans.Continent;
@Component
public class JsonToPojoHelper {

	
	private static final Logger logger=LoggerFactory.getLogger(JsonToPojoHelper.class);
	
	public  List<Continent> mapJsonToPojo(String fileName) throws IOException
	{
		
	        ClassLoader classLoader = new JsonToPojoHelper().getClass().getClassLoader();
	        File file = new File(classLoader.getResource(fileName).getFile());
	        List<Continent> countryList=new ArrayList<Continent>();
		ObjectMapper mapper = new ObjectMapper();
		JSONArray continentArrJson=new JSONArray(new String(Files.readAllBytes(file.toPath())));
        try {
		        	for(int i=0;i<continentArrJson.length();i++)
		        	{
		        		JSONObject obj=(JSONObject) continentArrJson.get(i);
		        		Continent continent = mapper.readValue(obj.toString(), Continent.class);
		        		countryList.add(continent);
		        	}
        	} 
        	catch (IOException e)
        	{
        			logger.error("Exception occured in mapJsonToPojo method of JsonToPojoHelper class"+e);
        	}
        	return countryList;
	}
	
}
