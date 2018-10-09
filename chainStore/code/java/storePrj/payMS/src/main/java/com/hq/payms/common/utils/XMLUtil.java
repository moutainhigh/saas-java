package com.hq.payms.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.ByteArrayInputStream;

public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map<String, Object> doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		InputStream in = null;
		try {
			Map<String, Object> m = new HashMap<String, Object>();
			in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(in);
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren();
			Iterator<Element> it = list.iterator();
			while (it.hasNext()) {
				Element e = it.next();
				String k = e.getName();
				String v = "";
				List<Element> children = e.getChildren();
				if (children.isEmpty()) {
					v = e.getTextNormalize();
				} else {
					v = XMLUtil.getChildrenText(children);
				}
				m.put(k, v);
			}
			return m;
		} finally {
			// 关闭流
			if (in != null)
				in.close();
		}

	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List<Element> children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator<Element> it = children.iterator();
			while (it.hasNext()) {
				Element e = it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<Element> list = e.getChildren();
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
	 * 获取xml编码字符集
	 * 
	 * @param strxml
	 * @return
	 * @throws IOException
	 * @throws JDOMException
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
		InputStream in = null;
		try {
			in = new ByteArrayInputStream(strxml.getBytes());
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(in);
			return (String) doc.getProperty("encoding");
		} finally {
			// 关闭流
			if (in != null)
				in.close();
		}
	}

	/**
	 * 接收支付成功通知时，返回给微信的参数
	 * 
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public static String createRespXML(String return_code, String return_msg) {

		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";

	}

	/**
	 * 将reqMap转换为xml格式
	 * 
	 * @param reqMap
	 * @return
	 */
	public static String createReqXML(Map<String, String> reqMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (Entry<String, String> entry : reqMap.entrySet()) {
			String k = entry.getKey();
			String v = entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

}