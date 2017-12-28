package com.radida.pacs.core.common;  
  
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.io.SAXReader;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;  
  
public class XMLUtil {  
  
    /** 
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。 
     *  
     * @param strxml 
     * @return 
     * @throws JDOMException 
     * @throws IOException 
     */  
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {  
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");  
  
        if (null == strxml || "".equals(strxml)) {  
            return null;  
        }  
  
        Map m = new HashMap();  
  
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));  
        SAXBuilder builder = new SAXBuilder();  
        Document doc = builder.build(in);  
        Element root = doc.getRootElement();  
        List list = root.getChildren();  
        Iterator it = list.iterator();  
        while (it.hasNext()) {  
            Element e = (Element) it.next();  
            String k = e.getName();  
            String v = "";  
            List children = e.getChildren();  
            if (children.isEmpty()) {  
                v = e.getTextNormalize();  
            } else {  
                v = XMLUtil.getChildrenText(children);  
            }  
  
            m.put(k, v);  
        }  
  
        // 关闭流  
        in.close();  
  
        return m;  
    }  
  
    /** 
     * 获取子结点的xml 
     *  
     * @param children 
     * @return String 
     */  
    public static String getChildrenText(List children) {  
        StringBuffer sb = new StringBuffer();  
        if (!children.isEmpty()) {  
            Iterator it = children.iterator();  
            while (it.hasNext()) {  
                Element e = (Element) it.next();  
                String name = e.getName();  
                String value = e.getTextNormalize();  
                List list = e.getChildren();  
                sb.append("<" + name + ">");  
                if (!list.isEmpty()) {  
                    sb.append(XMLUtil.getChildrenText(list));  
                }  
                sb.append(value);  
                sb.append("</" + name + ">");  
            }  
        }  
  
        return sb.toString();  
    }  
  
    /** 
     * 将xml通知结果转出成map 
     * @param request 
     * @return 
     * @throws Exception 
     */  
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {  
        // 解析结果存储在HashMap  
        Map<String, String> map = new HashMap<String, String>();  
        InputStream inputStream = request.getInputStream();  
        // 读取输入流  
        SAXReader reader = new SAXReader();  
        org.dom4j.Document document = reader.read(inputStream);  
        // 得到xml根元素  
        org.dom4j.Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        List<org.dom4j.Element> elementList = root.elements();  
        // 遍历所有子节点  
        for (org.dom4j.Element e : elementList)  
            map.put(e.getName(), e.getText());  
        // 释放资源  
        inputStream.close();  
        inputStream = null;  
        return map;  
    }  
    
    /**
     * תXMLmap
     * @author  
     * @param xmlBytes
     * @param charset
     * @return
     * @throws Exception
     */
    public static HashMap<String,String> toMap(byte[] xmlBytes,String charset) throws Exception{
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
        source.setEncoding(charset);
        org.dom4j.Document doc = reader.read(source);
        HashMap<String, String> params = toMap(doc.getRootElement());
        return params;
    }
    /**
     * תMAP
     * @author  
     * @param element
     * @return
     */
    public static HashMap<String, String> toMap(org.dom4j.Element element){
    	HashMap<String, String> rest = new HashMap<String, String>();
        List<org.dom4j.Element> els = element.elements();
        for(org.dom4j.Element el : els){
            rest.put(el.getName().toLowerCase(), el.getTextTrim());
        }
        return rest;
    }
  
}  