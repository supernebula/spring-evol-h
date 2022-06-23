package com.evol.utils;


import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * XML解析工具类
 *
 * @author phil
 * @date 2017年7月3日
 *
 */
public class XmlUtil {

    //private static final Logger logger = Logger.getLogger(XmlUtil.class);
    /**
     * 解析微信发来的请求（XML） xml示例 <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime> <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[CLICK]]></Event>
     * <EventKey><![CDATA[EVENTKEY]]></EventKey> </xml>
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXmlToMap(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 解析微信发来的请求（XML） xml示例 <xml> <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime> <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[CLICK]]></Event>
     * <EventKey><![CDATA[EVENTKEY]]></EventKey> </xml>
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXmlToMap(InputStream inputStream) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 使用dom4将xml文件中的数据转换成SortedMap<Object,Object>
     *
     * @param xml
     *            xml格式的字符串
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static SortedMap<Object, Object> parseXmlToSortedMap(String xml)
            throws ParserConfigurationException, IOException, SAXException {
        // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = HttpReqUtil.getStringStream(xml);
        org.w3c.dom.Document document = builder.parse(is);
        // 获取到document里面的全部结点
        org.w3c.dom.NodeList allNodes = document.getFirstChild().getChildNodes();
        org.w3c.dom.Node node;
        SortedMap<Object, Object> map = new TreeMap<Object, Object>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof org.w3c.dom.Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }

    /**
     * 使用dom4将xml文件中的数据转换成SortedMap<Object,Object>
     *
     * @param xml
     *            xml格式的字符串
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static TreeMap<Object, Object> parseXmlToTreeMap(String xml)
            throws ParserConfigurationException, IOException, SAXException {
        // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = HttpReqUtil.getStringStream(xml);
        org.w3c.dom.Document document = builder.parse(is);
        // 获取到document里面的全部结点
        org.w3c.dom.NodeList allNodes = document.getFirstChild().getChildNodes();
        org.w3c.dom.Node node;
        TreeMap<Object, Object> map = new TreeMap<Object, Object>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof org.w3c.dom.Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }

    /**
     * 使用dom4将xml文件中的数据转换成Map<Object,Object>
     *
     * @param xml
     *            xml格式的字符串
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Map<Object, Object> parseXmlToMap(String xml)
            throws ParserConfigurationException, IOException, SAXException {
        // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = HttpReqUtil.getStringStream(xml);
        org.w3c.dom.Document document = builder.parse(is);
        // 获取到document里面的全部结点
        org.w3c.dom.NodeList allNodes = document.getFirstChild().getChildNodes();
        org.w3c.dom.Node node;
        Map<Object, Object> map = new HashMap<Object, Object>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof org.w3c.dom.Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }
    //
    // /**
    // *
    // * @param obj
    // * @return
    // */
    // public static String parseToSplitXml(BasicXml obj) {
    // String result = "";
    // XStream xstream = new XStream(new DomDriver("UTF-8", new
    // XmlFriendlyNameCoder("-_", "_")));
    // xstream.alias("xml", obj.getClass());
    // result = xstream.toXML(obj);
    // return result;
    // }

    /**
     * 示例 <xml> <return_code><![CDATA[SUCCESS]]></return_code>
     * <return_msg><![CDATA[OK]]></return_msg>
     * <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     * <mch_id><![CDATA[10000100]]></mch_id>
     * <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
     * <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>
     * <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
     * <result_code><![CDATA[SUCCESS]]></result_code>
     * <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
     * <trade_type><![CDATA[JSAPI]]></trade_type> </xml>
     *
     * 将xml数据(<![CDATA[SUCCESS]]>格式)映射到java对象中
     *
     * @param xml
     *            待转换的xml格式的数据
     * @param t
     *            待转换为的java对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectFromXML(String xml, Class<T> t) {
        // 将从API返回的XML数据映射到Java对象
        XStream xstream = XStreamFactroy.init(true);
        xstream.alias("xml", t);
        xstream.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
        return (T) xstream.fromXML(xml);
    }

    /**
     * 将java对象转换为xml(<![CDATA[SUCCESS]]>格式)
     *
     * @param obj
     * @return
     */
    public static String toXml(Object obj) {
        String result = "";
        XStream xstream = XStreamFactroy.init(true);
        xstream.alias("xml", obj.getClass());
        result = xstream.toXML(obj);
        return result;
    }

    /**
     * 将java对象转换为xml文件,并去除 _ 应用场景是 去除实体中有_划线的实体, 默认会有两个_,调用该方法则会去除一个
     *
     * @param obj
     * @return
     */
    public static String toSplitXml(Object obj) {
        String result = "";
        XStream xstream = XStreamFactroy.initSplitLine();
        xstream.alias("xml", obj.getClass());
        result = xstream.toXML(obj);
        return result;
    }

    /**
     * 将java对象转换为xml文件,并去除 _ 应用场景是 去除实体中有_划线的实体, 默认会有两个_,调用该方法则会去除一个
     *
     * @param obj
     * @return
     */
    public static String toSplitXmlByClass(Object obj, Class classz) {
        String result = "";
        XStream xstream = XStreamFactroy.initSplitLine();
        xstream.processAnnotations(classz);
        xstream.alias("xml", obj.getClass());
        result = xstream.toXML(obj);
        return result;
    }

    /**
     * 将json格式的字符串转换为 MsgResult 对象
     *
     * @param data
     * @return
     */
//	public static MsgResult ConverResult(String data) {
//		MsgResult result = null;
//		if (data != null && !"".equals(data)) {
//			// result = JSONObject.parseObject(data, MsgResult.class);
//			result = new Gson().fromJson(data, MsgResult.class);
//		}
//		return result;
//	}

    /**
     * 包括detail的字段时进行特殊处理
     */

    public static void main(String[] args) throws Exception {
//		UnifiedOrderParams unifiedOrderParams = new UnifiedOrderParams();
//		unifiedOrderParams.setAppid(WeChatConfig.APP_ID);// 必须
//		unifiedOrderParams.setMch_id(WeChatConfig.MCH_ID);// 必须
//		unifiedOrderParams.setOut_trade_no(PayUtil.createNonceStr());
//		unifiedOrderParams.setTrade_type("NATIVE");// 必须
//		unifiedOrderParams.setNotify_url(WeChatConfig.NOTIFY_URL); //异步通知URL
//		// 统一下单 请求的Xml(除detail外不需要<![CDATA[product_001]]>格式)
//		// 假如 有detail
//		UnifiedOrderDetail unifiedOrderDetail = new UnifiedOrderDetail();
//		unifiedOrderDetail.setCost_price(6000);
//		UnifiedOrderGoodDetails d1 = new UnifiedOrderGoodDetails();
//		d1.setGoods_id("231");
//		d1.setGoods_name("大十字");
//		UnifiedOrderGoodDetails d2 = new UnifiedOrderGoodDetails();
//		d2.setGoods_id("1231");
//		d2.setGoods_name("莫安那");
//		List<UnifiedOrderGoodDetails> list = new ArrayList<UnifiedOrderGoodDetails>();
//		list.add(d1);
//		list.add(d2);
//		unifiedOrderDetail.setGoods_detail(list);
//		//UnifiedOrderDetail转成json
//		String detail = JsonUtil.toJson(unifiedOrderDetail);
//		unifiedOrderParams.setDetail(detail);//必填,抽象父类无法set
//		String sign = SignatureUtil.createSign(unifiedOrderParams, WeChatConfig.API_KEY, SystemConfig.CHARACTER_ENCODING);
//		unifiedOrderParams.setSign(sign);
//		unifiedOrderParams.setDetail(null);
//		String paramsXml = StringHelper.deleteCRLFOnce(XmlUtil.toSplitXml(unifiedOrderParams).replace("</xml>", XmlUtil.toXml(detail).replace("<xml>", "")));
//		System.out.println(paramsXml);


        Map<String,String> map = new HashMap<String,String>();
        map.put("1", "2");
        System.out.println(map.get("1"));


    }
}
