package fr.kdefombelle.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class FileReaderTest {
	
	private FileReader fileReader = new FileReader();
	
	@Test
	public void testReadFile() throws IOException  {
		String readFile = fileReader.readFile("fr/kdefombelle/util/file.xsl");
		assertNotNull(readFile);
	}
	

	@Test
	public void testReadLines() throws IOException  {
		List<String> lines = fileReader.readLines("fr/kdefombelle/util/lines.txt");
		assertEquals(3, lines.size());
		assertTrue(lines.contains("//line1"));
		assertTrue(lines.contains("//line2"));
	}
}
