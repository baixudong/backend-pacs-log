package com.radida.pacs.core.common.bean;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.radida.pacs.core.common.CommonUtils;
import com.radida.pacs.core.common.EnumResultCode;
import com.radida.pacs.core.common.IDomainObject;
import com.radida.pacs.core.common.JsonUtils;

public class ResultHelper {

	public static String RESULT_CODE = "code";
	public static String RESULT_INFO = "info";
	public static String RESULT_LIST = "list";
	public static String RESULT_PAGE = "page";
	public static String RESULT_TOTAL = "total";
	public static String RESULT_COUNT = "count";

	public static boolean checkRetSuccess(ResultDto ret) {
		if (ret == null) {
			return false;
		}

		if (EnumResultCode.SUCCESS.getCode().equals(ret.getResultcode())) {
			return true;
		}

		return false;
	}
	
	/**
	 * 验证参数中必填项是否有空（验证Map形式的参数）
	 * @param request 请求对象
	 * @param paramMap 参数
	 * @param requiredParamNames 必填参数数组
	 * @param callback 是否有callback，如果有，使用jsonp
	 * @return
	 */
	public static Object checkParamters(HttpServletRequest request,
			Map<?, ?> paramMap, String[] requiredParamNames, String callback, Logger logger) {

		if (requiredParamNames == null) {
			return null;
		}
		Map<String, Object> retMap = null;
		if (paramMap == null) {
			retMap = new HashMap<String, Object>();
			retMap.put(RESULT_CODE, EnumResultCode.ERROR_PARAM_EMPTY.getCode());
			retMap.put(RESULT_INFO, EnumResultCode.ERROR_PARAM_EMPTY.getDesc());
			return retMap;
		}

		for (String param : requiredParamNames) {
			Object pValue = paramMap.get(param);
			if (pValue == null) {
				retMap = new HashMap<String, Object>();
				retMap.put(RESULT_CODE,
						EnumResultCode.ERROR_PARAM_EMPTY.getCode());
				retMap.put(RESULT_INFO,
						EnumResultCode.ERROR_PARAM_EMPTY.getDesc() + ": ["
								+ param + "]");
				break;
			}
		}
		if (retMap != null) {
			if (logger != null) {
				try {
					logInfo(request, logger, "result: {}",
							JsonUtils.readMap2Json(retMap));
				} catch (Exception e) {
					logger.error("json error : " + e.getMessage());
				}
			}
			if (CommonUtils.isEmpty(callback)) {
				return retMap;
			} else {
				return new JSONPObject(callback, retMap);
			}
		}

		return null;
	}
	
	/**
	 * @param ret ResultDto
	 * @param proxyJson 协议Map, proxy.properties中的映射关系
	 * @param callback 如果为"no"为json, 否则为jsonp
	 * @param type 1,Object 2,List 3,Page 4,Map
	 * @param appendMap 附加的Map，加到retMap中
	 * @return
	 */
	public static Object procResult(HttpServletRequest request, ResultDto ret, String proxyJson, String callback,
			int type, Map<String, Object> appendMap, Logger logger) {
		Map<String, Object> proxyMap = null;
		if (!CommonUtils.isEmpty(proxyJson)) {
			try {
				proxyMap = JsonUtils.readJson2Map(proxyJson);
			} catch (Exception e) {
				logger.error("json error : " + e.getMessage());
			}
		}

		Map<String, Object> retMap = null;

		if (ret == null) {
			retMap = createErrorRet();
		} else {
			retMap = new HashMap<String, Object>();

			switch (type) {
			case 1:
				convertResultDtoObject(retMap, ret, proxyMap);
				break;
			case 2:
				convertResultDtoList(retMap, ret, proxyMap);
				break;
			case 3:
				convertResultDtoPage(retMap, ret, proxyMap);
				break;
			case 4:
				convertResultDtoMap(retMap, ret, proxyMap);
				break;
			}
		}

		if (appendMap != null && appendMap.size() > 0) {
			for (Iterator<Map.Entry<String, Object>> it = appendMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				retMap.put(key, value);
			}
		}

		if (logger != null) {
			try {
				logInfo(request, logger, "result: {}", JsonUtils.readMap2Json(retMap));
			} catch (Exception e) {
				logger.error("json error : " + e.getMessage());
			}
		}

		if (CommonUtils.isEmpty(callback)) {
			return retMap;
		}

		return new JSONPObject(callback, retMap);
	}

	public static Object createNoSessionRet(HttpServletRequest request, String callback, Logger logger) {

		Map<String, Object> retMap = new HashMap<String, Object>();

		retMap.put(RESULT_CODE, EnumResultCode.ERROR_SESSION_INVALID.getCode());
		retMap.put(RESULT_INFO, EnumResultCode.ERROR_SESSION_INVALID.getDesc());

		if (logger != null) {
			try {
				logInfo(request, logger, "result: {}", JsonUtils.readMap2Json(retMap));
			} catch (Exception e) {
				logger.error("json error : " + e.getMessage());
			}
		}

		if (CommonUtils.isEmpty(callback)) {
			return retMap;
		}

		return new JSONPObject(callback, retMap);
	}

	public static Object createSuccessRet(HttpServletRequest request, String callback, Logger logger) {

		Map<String, Object> retMap = new HashMap<String, Object>();

		retMap.put(RESULT_CODE, EnumResultCode.SUCCESS.getCode());
		retMap.put(RESULT_INFO, EnumResultCode.SUCCESS.getDesc());

		if (logger != null) {
			try {
				logInfo(request, logger, "result: {}", JsonUtils.readMap2Json(retMap));
			} catch (Exception e) {
				logger.error("json error : " + e.getMessage());
			}
		}

		if (CommonUtils.isEmpty(callback)) {
			return retMap;
		}

		return new JSONPObject(callback, retMap);
	}

	public static Object createErrorRet(HttpServletRequest request, String callback, Logger logger) {

		Map<String, Object> retMap = new HashMap<String, Object>();

		retMap.put(RESULT_CODE, EnumResultCode.ERROR_SERVICE.getCode());
		retMap.put(RESULT_INFO, EnumResultCode.ERROR_SERVICE.getDesc());

		if (logger != null) {
			try {
				logInfo(request, logger, "result: {}", JsonUtils.readMap2Json(retMap));
			} catch (Exception e) {
				logger.error("json error : " + e.getMessage());
			}
		}

		if (CommonUtils.isEmpty(callback)) {
			return retMap;
		}

		return new JSONPObject(callback, retMap);
	}

	private static Map<String, Object> createErrorRet() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put(RESULT_CODE, EnumResultCode.ERROR_SERVICE.getCode());
		retMap.put(RESULT_INFO, EnumResultCode.ERROR_SERVICE.getDesc());
		return retMap;
	}

	private static void convertResultDtoPage(Map<String, Object> retMap, ResultDto ret, Map<String, Object> proxyMap) {
		retMap.put(RESULT_CODE, ret.getResultcode());
		retMap.put(RESULT_INFO, EnumResultCode.getValueOf(ret.getResultcode()).getDesc());
		adaptPage(retMap, (Page) ret.getData(), proxyMap);
	}

	private static void convertResultDtoList(Map<String, Object> retMap, ResultDto ret, Map<String, Object> proxyMap) {
		retMap.put(RESULT_CODE, ret.getResultcode());
		retMap.put(RESULT_INFO, EnumResultCode.getValueOf(ret.getResultcode()).getDesc());
		adaptList(retMap, (List) ret.getData(), proxyMap);
	}

	private static void convertResultDtoObject(Map<String, Object> retMap, ResultDto ret,
			Map<String, Object> proxyMap) {
		retMap.put(RESULT_CODE, ret.getResultcode());
		retMap.put(RESULT_INFO, EnumResultCode.getValueOf(ret.getResultcode()).getDesc());
		adaptDomainObject(retMap, ret.getData(), proxyMap);
	}

	private static void convertResultDtoMap(Map<String, Object> retMap, ResultDto ret, Map<String, Object> proxyMap) {
		retMap.put(RESULT_CODE, ret.getResultcode());
		retMap.put(RESULT_INFO, EnumResultCode.getValueOf(ret.getResultcode()).getDesc());
		adaptMap(retMap, (Map<String, Object>) ret.getData(), proxyMap);
	}

	private static void adaptPage(Map<String, Object> retMap, Page page, Map<String, Object> proxyMap) {
		if (page == null || page.getData() == null || page.getData().size() < 1) {
			return;
		}

		if (proxyMap == null || proxyMap.isEmpty()) {
			return;
		}

		retMap.put(RESULT_TOTAL, page.getTotalResults() + "");
		retMap.put(RESULT_PAGE, page.getPageIndex() + "");
		retMap.put(RESULT_COUNT, page.getPageSize() + "");

		List<IDomainObject> list = page.getData();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (IDomainObject obj : list) {
			Map<String, Object> objMap = parseNameAndValue(obj, proxyMap);
			mapList.add(objMap);
		}
		retMap.put(RESULT_LIST, mapList);
	}

	private static void adaptList(Map<String, Object> retMap, List list, Map<String, Object> proxyMap) {
		if (list == null || list.size() < 1) {
			return;
		}
		if (proxyMap == null || proxyMap.isEmpty()) {
			return;
		}

		retMap.put(RESULT_COUNT, list.size() + "");

		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Object obj : list) {
			Map<String, Object> objMap = parseNameAndValue(obj, proxyMap);
			mapList.add(objMap);
		}
		retMap.put(RESULT_LIST, mapList);
	}

	private static void adaptDomainObject(Map<String, Object> retMap, Object obj, Map<String, Object> proxyMap) {
		if (obj == null) {
			return;
		}

		if (proxyMap == null || proxyMap.isEmpty()) {
			return;
		}

		Map<String, Object> tmpMap = parseNameAndValue(obj, proxyMap);

		if (tmpMap != null && tmpMap.size() > 0) {
			for (Iterator<Map.Entry<String, Object>> it = tmpMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				retMap.put(key, value);
			}
		}
	}

	private static void adaptMap(Map<String, Object> retMap, Map<String, Object> smap, Map<String, Object> proxyMap) {
		if (smap == null || smap.isEmpty()) {
			return;
		}

		if (proxyMap == null || proxyMap.isEmpty()) {
			return;
		}

		for (Iterator<Map.Entry<String, Object>> it = proxyMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			Object value = entry.getValue();

			if (smap.get(key) != null) {
				retMap.put(value.toString(), smap.get(key).toString());
			}
		}
	}

	/**
	 * 获取键值对 {"create_time":"create","list":{"type":"list", "name":"arr"
	 * "map":{"create_time":"create", "update_time":"create"}}
	 * 
	 * @param object
	 * @return
	 */
	private static Map<String, Object> parseNameAndValue(Object object, Map<String, Object> proxyMap) {
		Field[] fields = object.getClass().getDeclaredFields();
		fields = getSuperClassFields(object, fields);

		if (object.getClass().getSuperclass() != null) {
			Field[] superClassFields = object.getClass().getSuperclass().getDeclaredFields();
			List<Field> fieldsList = new ArrayList<Field>(Arrays.asList(fields));
			fieldsList.addAll(Arrays.asList(superClassFields));
			fields = fieldsList.toArray(fields);
		}

		Map<String, Object> nameAndValue = new HashMap<String, Object>();

		boolean hasKeyMap = false;
		if (proxyMap != null && proxyMap.size() > 0) {
			hasKeyMap = true;
		}

		for (int i = 0; i < fields.length; i++) {
			try {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				String varName = fields[i].getName();
				Object varValue = fields[i].get(object);
				if (hasKeyMap) {
					Object val = proxyMap.get(varName);
					if (!CommonUtils.isEmptyObj(val)) {
						if (!CommonUtils.isEmptyObj(varValue)) {
							String classType = fields[i].getType().getName();
							// if("java.util.Date".equals(classType)) {
							// varValue = new
							// SimpleDateFormat("yyyy-MM-dd
							// HH:mm:ss").format(varValue);
							// }
							switch (classType) {
							case "java.util.Date":
								varValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(varValue);
								nameAndValue.put(val.toString(), varValue.toString());
								break;
							case "java.util.List":
								Map<String, Object> clistMap = (Map<String, Object>) val;

								String name = (String) clistMap.get("name");
								Map<String, Object> clistProxyMap = (Map<String, Object>) clistMap.get("proxy");
								if (CommonUtils.isEmptyObj(name)) {
									name = varName;
								}

								if (clistProxyMap == null || clistProxyMap.isEmpty()) {
									continue;
								}

								List clist = (List) varValue;
								List<Map<String, Object>> cproxyList = new ArrayList<Map<String, Object>>();
								for (Object cobj : clist) {
									if (cobj != null) {
										Map<String, Object> cobjMap = parseNameAndValue(cobj, clistProxyMap);
										cproxyList.add(cobjMap);
									}
								}

								nameAndValue.put(name, cproxyList);

								break;
							default:
								nameAndValue.put(val.toString(), varValue.toString());
							}
						}
					}
				}
				fields[i].setAccessible(accessFlag);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return nameAndValue;
	}

	private static Field[] getSuperClassFields(Object object, Field[] fields) {
		Class<?> superClass = object.getClass().getSuperclass();
		if (superClass != null && superClass != Object.class) {
			Field[] superClassFields = superClass.getDeclaredFields();
			List<Field> fieldsList = new ArrayList<Field>(Arrays.asList(fields));
			fieldsList.addAll(Arrays.asList(superClassFields));
			fields = fieldsList.toArray(fields);
		} else {
			return fields;
		}
		object = superClass;
		getSuperClassFields(object, fields);
		return fields;
	}

	public static void logInfo(HttpServletRequest request, Logger logger, String msg) {
		if (request == null || logger == null || CommonUtils.isEmpty(msg)) {
			return;
		}

		String newMsg = "[{}] " + msg;
		logger.info(newMsg, request.getRequestURI());
	}

	public static void logInfo(HttpServletRequest request, Logger logger, String format, Object arg) {
		if (request == null || logger == null || CommonUtils.isEmpty(format)) {
			return;
		}

		String newMsg = "[{}] " + format;
		logger.info(newMsg, request.getRequestURI(), arg);
	}

	public static void logInfo(HttpServletRequest request, Logger logger, String format, Object[] argArray) {
		if (request == null || logger == null || CommonUtils.isEmpty(format)) {
			return;
		}

		String newMsg = "[{}] " + format;
		if (argArray != null && argArray.length > 0) {
			Object[] newArgArray = new Object[argArray.length + 1];
			newArgArray[0] = request.getRequestURI();
			for (int i = 0; i < argArray.length; i++) {
				newArgArray[i + 1] = argArray[i];
			}
			logger.info(newMsg, newArgArray);
		} else {
			logger.info(newMsg, request.getRequestURI());
		}
	}

	public static void logError(HttpServletRequest request, Logger logger, String msg) {
		if (request == null || logger == null || CommonUtils.isEmpty(msg)) {
			return;
		}

		String newMsg = "[{}] " + msg;
		logger.error(newMsg, request.getRequestURI());
	}

	public static void logError(HttpServletRequest request, Logger logger, String msg, Throwable t) {
		if (request == null || logger == null || CommonUtils.isEmpty(msg)) {
			return;
		}

		String newMsg = "[" + request.getRequestURI() + "] " + msg + "    exception : " + t;
		logger.error(newMsg, t);
	}

	public static void logError(HttpServletRequest request, Logger logger, String format, Object[] argArray) {
		if (request == null || logger == null || CommonUtils.isEmpty(format)) {
			return;
		}

		String newMsg = "[{}] " + format;
		if (argArray != null && argArray.length > 0) {
			Object[] newArgArray = new Object[argArray.length + 1];
			newArgArray[0] = request.getRequestURI();
			for (int i = 0; i < argArray.length; i++) {
				newArgArray[i + 1] = argArray[i];
			}
			logger.error(newMsg, newArgArray);
		} else {
			logger.error(newMsg, request.getRequestURI());
		}
	}
}
