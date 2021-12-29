/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexml;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author il.lenskii
 */
public class DemoDOM {
    
    private String tagName;
    private Document doc;
    private ArrayList<String> results = new ArrayList<>();
    
    public void setTagContFind(String tag) {
        
        results.clear();

        tagName = tag;
    }

    public ArrayList<String> getResult() {
        
        return results;
    }
    
    public void setDocument(Document doc) {

        this.doc = doc;
    }
    
    public void findTag() {

        if(doc == null || tagName == null)
            return;

        NodeList nList = doc.getElementsByTagName(tagName);

        if(nList.getLength() < 1)
            return;

        for (int i = 0; i < nList.getLength(); ++i) {
            Node nNode =  nList.item(i);

            results.add( nNode.getTextContent() );
        }
    }
}
