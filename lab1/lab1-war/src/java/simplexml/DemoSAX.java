/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexml;

//import java.io.File;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author il.lenskii
 */

public class DemoSAX extends DefaultHandler {

    private String tagName;
    private Boolean isFind = false;
    private ArrayList<String> results = new ArrayList<>();
    
    public void setTagContFind(String tag) {
        
        results.clear();

        tagName = tag;
    }

    public ArrayList<String> getResult() {
        
        return results;
    }

    @Override
    public void startElement(
      String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        
        if(tagName == null)
            return;
        
        isFind = qName.equals(tagName);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        
        if(tagName == null)
            return;

        if(qName.equals(tagName))
            isFind = false;
    }

   @Override
   public void characters(char ch[], int start, int length)
           throws SAXException {

        if(tagName == null || isFind == false)
            return;

        results.add(new String(ch, start, length));
   }
}
