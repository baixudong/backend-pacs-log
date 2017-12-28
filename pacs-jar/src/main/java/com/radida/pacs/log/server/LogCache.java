package com.radida.pacs.log.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * @author baixd
 */
public class LogCache {

    public static final LinkedList<LinkedList<Object[]>> list = new LinkedList<LinkedList<Object[]>>();
    public static Map<String, LinkedList<LinkedList<String>>> CACHE = new HashMap<String, LinkedList<LinkedList<String>>>();

    public static void exe(String info, Object... obj) {
        list.get(1).add(new Object[] { info, obj });
    }

    public static LinkedList<String> read(String key) {
    	if (CACHE.get(key) == null) {
        	LinkedList<String> list = new LinkedList<String>();
        	return list;
        }
        synchronized (CACHE.get(key)) {
            LinkedList<String> list = null;
            CACHE.get(key).add(new LinkedList<String>());
            list = CACHE.get(key).remove(0);
            return list;
        }
    }

    public static void put(String key, String values) {
        if (CACHE.get(key) == null) {
        	if (CACHE.get(key) == null) {
        		CACHE.put(key, new LinkedList<LinkedList<String>>());
        		CACHE.get(key).add(new LinkedList<String>());
        		CACHE.get(key).add(new LinkedList<String>());
            }
        }
        try {
            CACHE.get(key).getLast().add(values.toString());
        } catch (Error e) {
        	e.printStackTrace();
        } finally {

        }

    }
}
