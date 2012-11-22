package net.clementlevallois.foafcrawler;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;

/**
 * Hello world!
 *
 */
public class Controller {

    public static HashSet<NodeFoaf> setNodes;
    public static HashSet<Edge> setEdges;
    public static void main(String[] args) throws IOException {
        Iterator<File> it = FileUtils.iterateFiles(new File("D:\\Docs Pro Clement\\Dropbox\\eHG workshop november\\foafCrawler\\"), null, false);
        PageReader pr;
        setNodes = new HashSet();
        setEdges = new HashSet();
        
        while (it.hasNext()) {
            System.out.println("new file read");
            File currFile = it.next();
            System.out.println("currFile: "+currFile.getAbsolutePath());
            pr = new PageReader(currFile);
            pr.read();
        }
        
        GexfWriter gw = new GexfWriter();
        gw.createGraph();
    }
}
