package fr.kdefombelle.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

	private static final String NEW_LINE = System.getProperty("line.separator");

    public String readFile(String filePath) throws IOException {
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
    	Reader reader = new InputStreamReader(is);
    	StringBuilder builder = new StringBuilder();
		try(BufferedReader br =  new BufferedReader(reader)) {
            String line = null;
            while ((line = br.readLine()) != null)
                builder.append(line).append(NEW_LINE);
        } 
        return builder.toString();
    }
    
    public List<String> readLines(String filePath) throws IOException {
    	List<String> lines = new ArrayList<>();
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
    	Reader reader = new InputStreamReader(is);
    	StringBuilder builder = new StringBuilder();
		try(BufferedReader br =  new BufferedReader(reader)) {
            String line = null;
            while ((line = br.readLine()) != null)
            	lines.add(line);
        } 
        return lines;
    }

}