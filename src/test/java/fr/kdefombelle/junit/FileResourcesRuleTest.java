package fr.kdefombelle.junit;

import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.kdefombelle.junit.FileResources;
import fr.kdefombelle.junit.FileResourcesRule;


/**
 * <p>Unit tests for the {@link FileResourcesRule} class.</p>
 *
 * @author kdefombelle
 */
public class FileResourcesRuleTest {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
    @Rule
    public FileResourcesRule files = new FileResourcesRule();

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @Test(expected = IllegalStateException.class)
    public void throwExceptionTest() {
        throw new IllegalStateException("verify that the rule does not swallow the exception");
    }

    @Test
    public void readByPathWithNoFileResourcesTest() {
    	thrown.expect(IllegalArgumentException.class);
    	thrown.expectMessage("The path [" + "path" + "] does not reference any file");
        files.read("path");
    }

    @Test
    public void readByIndexWithNoFileResourcesTest() {
    	int index = new Random().nextInt(10);
    	thrown.expect(IndexOutOfBoundsException.class);
    	thrown.expectMessage("The index [" + index + "] is out of range [0, 0]");
        files.read(index);
    }

    @Test
    public void nameByIndexWithNoFileResourcesTest() {
		int index = new Random().nextInt(10);
    	thrown.expect(IndexOutOfBoundsException.class);
    	thrown.expectMessage("The index [" + index + "] is out of range [0, 0]");
        files.name(index);
    }

    @Test
    @FileResources(files = "simpleFile.txt")
    public void readWithInvalidPathTest() {
    	thrown.expect(IllegalArgumentException.class);
    	thrown.expectMessage("The path [" + "path" + "] does not reference any file");
        files.read("path");
    }

    @Test
    @FileResources(files = "simpleFile.txt")
    public void readWithInvalidIndexTest() {
        int index = new Random().nextInt(10) + 1;
    	thrown.expect(IndexOutOfBoundsException.class);
    	thrown.expectMessage("The index [" + index + "] is out of range [0, 1]");
        files.read(index);
    }

    @Test
    @FileResources(files = "simpleFile.txt")
    public void nameWithInvalidIndexTest() {
        int index = new Random().nextInt(10) + 1;
        thrown.expect(IndexOutOfBoundsException.class);
    	thrown.expectMessage("The index [" + index + "] is out of range [0, 1]");
        files.name(index);
    }

    @Test
    @FileResources(files = "simpleFile.txt")
    public void readByPathTest() {
        files.read("simpleFile.txt").equals("simple test content");
    }

    @Test
    @FileResources(files = "simpleFile.txt")
    public void readByIndexTest() {
        files.read(0).equals("simple test content");
    }

    @Test
    @FileResources(files = { "simpleFile.txt", "directory/simpleFile.txt" })
    public void readByPathWithDirectoryTest() {
        files.read("simpleFile.txt").equals("simple test content");
        files.read("directory/simpleFile.txt").equals("simple test content in directory");
    }

    @Test
    @FileResources(files = { "simpleFile.txt", "directory/simpleFile.txt" })
    public void readByIndexWithDirectoryTest() {
        files.read(0).equals("simple test content");
        files.read(1).equals("simple test content in directory");
    }

    @Test
    @FileResources(files = { "simpleFile.txt", "directory/simpleFile.txt" })
    public void nameByIndexWithDirectoryTest() {
        files.name(0).equals("simpleFile.txt");
        files.name(1).equals("simpleFile.txt");
    }

    @Test
    @FileResources(files = "utf8File.txt", charset = "UTF8")
    public void readWithEncodingTest() {
        files.read(0).equals("français langue étrangère");
    }

}
