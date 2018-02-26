package cn.wwj.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;

/**
 * 工具�?
 * 
 * @Description:
 * @author wangchao
 * @date 2017-8-18下午4:59:09
 */
public class Tools {

	protected static Logger logger = Logger.getLogger(Tools.class);
	
	/**
	 * @Description: 判断字符串是否空
	 * @param cs
	 * @return �?true 非空false
	 * @author wangchao
	 * @date 2017-8-21下午4:52:51
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if ((cs == null) || ((strLen = cs.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @Description: 判断字符串是否空
	 * @param cs
	 * @return �?false 非空true
	 * @author wangchao
	 * @date 2017-8-21下午4:52:51
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !isBlank(cs);
	}
	
	public static String foarmatDateTime(Timestamp createDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(createDate);
	}
	
	public static String foarmatDates(Timestamp createDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM");// 设置日期格式
		return df.format(createDate);
	}
	
	public static String foarmatDatestr(Timestamp createDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");// 设置日期格式
		return df.format(createDate);
	}
	
	public static String foarmatDate(Timestamp createDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(createDate);
	}
	
	public static Date getUtilDate(String datestr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);// 定义日期格式
        Date date = null;
        try {
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

	/**
	 * @Description: 获取当前日期时间
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午4:59:41
	 */
	public static String getCurrTimeTag() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * @Description: 获取当前日期
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午4:59:41
	 */
	public static String getCurrDateTag() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());
	}

	public static String getCurrMonthTag() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM");// 设置日期格式
		return df.format(new Date());
	}
	
	/**
	 * @Description: 获取当前日期时间(带毫�?
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午5:02:58
	 */
	public static String getCurrTimeSecond() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 
	 * @Description: 获取当前日期时间(带毫�?
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午5:07:42
	 */
	public static String getCurrTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 
	 * @Description: 获取公共参数
	 * @param name
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午5:08:46
	 */
	public static String getProperValues(String name) {
		ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");
		return bundle.getString(name);
	}

	/**
	 * 获取客户端IP
	 * 
	 * @Description:
	 * @param request
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午5:12:14
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (Tools.isNotBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (Tools.isNotBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * @Description: 上传图片
	 * @param base64Str
	 * @param userID
	 * @param oldImages
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午5:12:05
	 */
	public static String loadImages(String base64Str, String userID,
			String oldImages) {
		String dirpath = System.getProperty("catalina.home")
				+ "/webapps/userImages";
		// linux下的路径
		BASE64Decoder decoder = new BASE64Decoder();
		if (!new File(dirpath).exists()) {
			new File(dirpath).mkdir();
		}
		if (Tools.isNotBlank(oldImages) && !oldImages.equals("null")) {
			// 如果老的头像存在则删�?
			String oldFileName = dirpath
					+ "/"
					+ oldImages.substring(oldImages.lastIndexOf("/"),
							oldImages.length());
			File file = new File(oldFileName);
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}
		String nowDate = Tools.getCurrTime();
		String fileName = userID + "_" + nowDate + ".jpg";
		String newFileName = dirpath + "/" + fileName;
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(base64Str.substring(
					base64Str.indexOf(",") + 1, base64Str.length())); // 解析base64不要
																		// 图片前缀“data:image/jpeg;base64,�?
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					// 调整异常数据
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(newFileName);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.getMessage();
			newFileName = null;
		}

		if (newFileName != null) {
			newFileName = Tools.getProperValues("serviceURL").substring(0,
					Tools.getProperValues("serviceURL").lastIndexOf("speed"))
					+ "/userImages/" + fileName;
		}
		return newFileName;
	}

	/**
	 * @Description: 校验是否为数�?
	 * @param num
	 * @return
	 * @author wangchao
	 * @date 2017-8-18下午5:11:26
	 */
	public static boolean checkNums(String num) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(num).matches();
	}

	/**
	 * @author wangchao
	 * @version 2016-9-19上午9:54:16
	 * @Description:获取4位随机验证码
	 * @return
	 */
	public static String getRandom() {
		int x;// 定义两变�?
		Random ne = new Random();// 实例化一个random的对象ne
		x = ne.nextInt(9000) + 1000;// 为变量赋随机�?000-9999
		return String.valueOf(x);
	}

	/**
	 * @Description:判断手机号长�?11,1�?��,全数�?
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		boolean result = false;
		if (checkNums(phone)) {
			String regex = "^[1][0-9]{10}$";
			if (phone.length() == 11 && phone.matches(regex)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 返回结果，及原因
	 * 
	 * @param result
	 * @param reason
	 * @return
	 */
	public static Map<String, Object> resultMap(Object result, Object reason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("reason", reason);
		return map;
	}
	
	public static String resultMapToString(Map<String, Object> map) {
		boolean result = (Boolean) map.get("result");
		String reason = (String) map.get("reason");
		String str = "jsonpCallback({'result':'"+result+"','reason':'"+reason+"'})";
		return str;
	}

	public static boolean checkNumber(String number) {
		boolean result = false;
		String regex = "^[京津沪渝�?��云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z0-9]{5}[A-Z0-9挂学警港澳]{1}$";
		if (number.length() == 7 && number.matches(regex)) {
			if (number.matches(regex)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 将Map转换为XML格式的字符串
	 * 
	 * @param data
	 *            Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String mapToXml(Map<String, String> data) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		org.w3c.dom.Document document = documentBuilder.newDocument();
		org.w3c.dom.Element root = document.createElement("xml");
		document.appendChild(root);
		for (String key : data.keySet()) {
			String value = data.get(key);
			if (value == null) {
				value = "";
			}
			value = value.trim();
			org.w3c.dom.Element filed = document.createElement(key);
			filed.appendChild(document.createTextNode(value));
			root.appendChild(filed);
		}
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);
		String output = writer.getBuffer().toString(); // .replaceAll("\n|\r",
														// "");
		try {
			writer.close();
		} catch (Exception ex) {
		}
		return output;
	}

	/**
	 * XML格式字符串转换为Map
	 * 
	 * @param strXML
	 *            XML字符�?
	 * @return XML数据转换后的Map
	 * @throws Exception
	 */
	public static Map<String, String> xmlToMap(String strXML) throws Exception 
	{
		try {
			Map<String, String> data = new HashMap<String, String>();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(
					strXML.getBytes("UTF-8"));
			Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			try {
				stream.close();
			} catch (Exception ex) {
				// do nothing
			}
			return data;
		} catch (Exception ex) {
			Logger.getRootLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}"
					+ ex.getMessage() + strXML);
			throw ex;
		}

	}
	
	/**
     * 生成 MD5
     *
     * @param data 待处理数�?
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception 
    {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
    
    /**
     * 生成 HMACSHA256
     * @param data 待处理数�?
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception 
    {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }
    
    /**
     * 获取当前时间戳，单位�?
     * @return
     */
    public static long getCurrentTimestamp() 
    {
        return System.currentTimeMillis()/1000;
    }
    
    /**
     * 获取当前时间戳，单位毫秒
     * @return
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }
    
    /**
     * 生成 uuid�?即用来标识一笔单，也用做 nonce_str
     * @return
     */
    public static String generateUUID() 
    {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
    
    /**
     * 计算日期，返回Date
     * @param dateTime
     * @param n
     * @return
     * @throws ParseException 
     */
    public static Date addAndSubtractDaysByCalendar(int n/*加减月数*/) throws ParseException{ 
        
        //日期格式 
    	Date date = new Date();//当前日期
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对�?
    	Calendar calendar = Calendar.getInstance();//日历对象
    	calendar.setTime(date);//设置当前日期
    	calendar.add(Calendar.MONTH, n);//月份�?
    	String str = sdf.format(calendar.getTime());//输出格式化的日期 
    	return sdf.parse(str);
      } 

    /**
     * 计算日期，返回Timestamp
     * @param n
     * @return
     * @throws ParseException
     */
    public static Timestamp addAndSubtractDaysByCalendarTimetamp(int n/*加减月数*/) throws ParseException{ 
        
        //日期格式 
    	Date date = new Date();//当前日期
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对�?
    	Calendar calendar = Calendar.getInstance();//日历对象
    	calendar.setTime(date);//设置当前日期
    	calendar.add(Calendar.MONTH, n);//月份�?
    	String str = sdf.format(calendar.getTime());//输出格式化的日期 
    	return Timestamp.valueOf(str);
      }
    
    /**
     * 当前时间返回Timestamp
     * @return
     */
    public static Timestamp getCurrentTimeStamp(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对�?
    	String str = sdf.format(new Date());//输出格式化的日期 
    	return Timestamp.valueOf(str);
    }
    
    /**
     * 计算日期，返回String
     * @param n
     * @return
     * @throws ParseException
     */
    public static String addAndSubtractDaysByCalendarString(int n/*加减月数*/) throws ParseException{ 
        
        //日期格式 
    	Date date = new Date();//当前日期
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对�?
    	Calendar calendar = Calendar.getInstance();//日历对象
    	calendar.setTime(date);//设置当前日期
    	calendar.add(Calendar.MONTH, n);//月份�?
    	String str = sdf.format(calendar.getTime());//输出格式化的日期 
    	return str;
      } 
    
    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse)
    {
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发�?成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数�?*/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }
    
    
    /**
     * 发�?消息（请求外部接口）
     * @param phone
     * @param code
     */
    public static void sendMessage(String phone,String code)
    {
    	JSONObject jsonParam = new JSONObject();
    	jsonParam.put("title", Tools.getProperValues("messageTitle"));
    	jsonParam.put("content", Tools.getProperValues("messageContent")+code);
    	jsonParam.put("isCron", 0);
    	jsonParam.put("typecode", "");
    	jsonParam.put("channels", "email");
    	jsonParam.put("channelIds", "id1");
    	jsonParam.put("extReceiver", phone);
    	
    	String url = Tools.getProperValues("sendMessageUrl")+"?accountID="+Tools.getProperValues("accountID")
    			+"&accountKey="+Tools.getProperValues("accountKey")+"&msgJson="+jsonParam.toString();
    	
    	JSONObject smallData = Tools.httpPost(url, null, false);
    	
    	System.out.println(smallData.toString());
    }
   
    
    public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        String regEx_other="&nbsp;"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        Pattern p_other=Pattern.compile(regEx_other,Pattern.CASE_INSENSITIVE); 
        Matcher m_other=p_other.matcher(htmlStr); 
        htmlStr=m_other.replaceAll(""); //过滤html标签 
        
        return htmlStr.trim(); //返回文本字符�?
    } 
    
    /**
     * 获取末年某月的天�?
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {  
    	  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }
    
    /**
     * 返回当前年份
     * @param month
     * @return
     */
    public static int dayYear(Date month){
    	Calendar cal = Calendar.getInstance();  
        cal.setTime(month);//month 为指定月份任意日�? 
        int year = cal.get(Calendar.YEAR);
        return year;
    }
    
    
    /**
     * 返回当前月份
     * @param month
     * @return
     */
    public static int dayMonth(Date month){
    	Calendar cal = Calendar.getInstance();  
        cal.setTime(month);//month 为指定月份任意日�? 
        int m = cal.get(Calendar.MONTH)+1; 
        return m;
    }
    
    /**
     * 遍历得到当前月的list
     * @param month
     */
    public static String[] dayReport(Date month) {  
    	  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(month);//month 为指定月份任意日�? 
        int year = cal.get(Calendar.YEAR);  
        int m = cal.get(Calendar.MONTH);  
        int dayNumOfMonth = getDaysByYearMonth(year, m);  
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开�? 
        String dateStr[] = new String[dayNumOfMonth];
        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {  
            Date d = cal.getTime();  
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
            String df = simpleDateFormat.format(d);  
            dateStr[i] = df;
       }  
//        Arrays.sort(dateStr, Collections.reverseOrder());
        return dateStr;
    }  
    
    public static double formatDouble(double num){
    	BigDecimal b = new BigDecimal(num);  
    	double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
    	return f1;
    }
}
