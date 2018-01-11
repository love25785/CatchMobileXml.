//先新增一個activity 命名為MyHandler，且在superclass: 打上 org.xml.sax.helpers.DefaultHandler  意思為其子類別
//因為要用到她的擷取資料的方法
package com.wl.a011005;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/10.
 */

public class MyHandler extends DefaultHandler {
    boolean isTitle = false;  // 這是sax 本身沒有的，是我們寫來專門只抓element 是title的，接著下面1~3也是
    boolean isItem=false;//用來抓isItem裡的isTitle   , 就可過濾掉網頁的的title  ，只抓到文章的title
    public ArrayList<String> titles=new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException//當遇到Element的頭 他會擷取
    {
        super.startElement(uri, localName, qName, attributes);
        //Log.d("NET", qName);
        if (qName.equals("title")) //1
        {
            isTitle = true;
        }
        if(qName.equals("item"))
        {
            isItem=true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException //當遇到Element的結束 他會擷取
    {
        super.endElement(uri, localName, qName);
        if (qName.equals("title"))  //2
        {
            isTitle = false;
        }
        if(qName.equals("item"))
        {
            isItem=false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException //其餘的字元都擷取
    {
        super.characters(ch, start, length);
        if (isTitle && isItem)  //3
        {
            Log.d("NET", new String(ch, start, length));
            titles.add(new String(ch,start,length));
        }

    }
}
