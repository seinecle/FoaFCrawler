/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.foafcrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import org.xml.sax.InputSource;

/**
 *
 * @author C. Levallois
 */
public class PageReader {

    private File currFile;

    public PageReader(File f) {
        this.currFile = f;
    }

    public void read() throws IOException {
        InputSource is = new InputSource(new StringReader(getFileContents(currFile)));
        FoafParser fp = new FoafParser(is);
        fp.parse();
    
    }

    private static String getFileContents(File file)
            throws IOException, FileNotFoundException {
        StringBuilder contents = new StringBuilder();

        BufferedReader input = new BufferedReader(new FileReader(file));

        try {
            String line;

            while ((line = input.readLine()) != null) {
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }
        } finally {
            input.close();
        }

        return contents.toString();
    }
}
