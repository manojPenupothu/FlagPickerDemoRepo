package com.flagpicker.FlagPickerDemo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagpicker.FlagPickerDemo.beans.Country;
import com.flagpicker.FlagPickerDemo.operations.service.FlagPickerDemoOpsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FlagPickerDemoApplication.class)
@AutoConfigureMockMvc
public class FlagPickerDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FlagPickerDemoOpsServiceImpl service;
	
	protected String mapToJson(Object obj)throws Exception{
		
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
		
		
	}
	
	protected <T> T mapFromJson(String json,Class<T> clazz) throws JsonParseException,JsonMappingException,IOException{
		
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.readValue(json, clazz);
		
	}
	
	@Test
	public void getCountries_sucess()throws Exception
	{
		
		Country country=new Country(1, "Brazil", "/dfsfdfdsgd", 0);
		
		Mockito.when(service.getAllCountries(Mockito.anyString())).thenReturn(Arrays.asList(country));
		
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/flagpicker/displayCountries/America").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Country[] countryArr=mapFromJson(result.getResponse().getContentAsString(), Country[].class);
		assertEquals(200,result.getResponse().getStatus());
		assertTrue(countryArr.length>0);
		}
	
	@Test
	public void getFlagByCountry_sucess()throws Exception
	{
		
		byte[] expectedArray=Base64.encodeBase64("/asfcvfe".getBytes());
		
		Mockito.when(service.getFlagByCountry(Mockito.anyString())).thenReturn(expectedArray);
		
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/flagpicker/displayFlag/America")).andReturn();
		
		byte[] actualArray=result.getResponse().getContentAsByteArray();
		assertEquals(200,result.getResponse().getStatus());
		assertArrayEquals(expectedArray,actualArray);
		}
	
	@Test
	public void getNumberOfViewsByCountry_sucess()throws Exception
	{
		
		Mockito.when(service.getNumberOfViewsByCountry(Mockito.anyString())).thenReturn(2);
		
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/flagpicker/displayNoOfHits/America").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		assertEquals(200,result.getResponse().getStatus());
		}

}
