package com.job5156.core.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;
import org.htmlparser.util.DefaultParserFeedback;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Html2MHTCompiler {
	/** 网页地址 */
	private URL strWeb = null;
	/** 本地文件名 */
    private String htmlStr = null;
    /** 网页编码 */
    private String strEncoding = "utf-8";
    /** 输出流对象 */
    private OutputStream os;
    
    public Html2MHTCompiler(String htmlstr, OutputStream os) {
        this.htmlStr = htmlstr;
        this.os = os;
    }

    public Html2MHTCompiler(String htmlstr) {
        this.htmlStr = htmlstr;
    }
  
    /**
     * 
    * @Title: compile 
    * @Description: 编译html为mht到指定的流中
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public boolean compile() {
        if (htmlStr == null || os == null || strEncoding == null)
            return false;
        HashMap<String, String> urlMap = Maps.newHashMap();
        NodeList nodes = new NodeList();
        try {
            Parser parser = createParser(htmlStr);
            parser.setEncoding(strEncoding);
            nodes = parser.parse(null);
        } catch (ParserException e) {
            e.printStackTrace();
        }
        extractAllScriptNodes(nodes);
        List<List<String>> urlScriptList = extractAllScriptNodes(nodes, urlMap);
        List<List<String>> urlImageList = extractAllImageNodes(nodes, urlMap);
        for (Iterator<Map.Entry<String, String>> iter = urlMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            htmlStr = JHtmlClear.replace(htmlStr, val, key);
        }
        try {
            createMhtArchive(htmlStr, urlScriptList, urlImageList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }  
  
    /** 
     * 
     * 
     * 方法说明：建立HTML parser 
     * 
     * 
     * 输入参数：inputHTML 网页文本内容 
     * 
     * 
     * 返回类型：HTML parser 
     * 
     */  
    private Parser createParser(String inputHTML) {  
        Lexer mLexer = new Lexer(new Page(inputHTML));
        return new Parser(mLexer, new DefaultParserFeedback(DefaultParserFeedback.QUIET));
    }
  
    /** 
     * 
     * 
     * 方法说明：抽取基础URL地址 
     * 
     * 
     * 输入参数：nodes 网页标签集合 
     * 
     * 
     * 返回类型： 
     * 
     */  
    private void extractAllScriptNodes(NodeList nodes) {  
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("BASE"), true);
        if (filtered != null && filtered.size() > 0) {
            Tag tag = (Tag) filtered.elementAt(0);
            String href = tag.getAttribute("href");
            if (href != null && href.length() > 0) {
                try {
                    strWeb = new URL(href);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  
    /** 
     * 
     * 
     * 方法说明：抽取网页包含的css,js链接 
     * 
     * 
     * 输入参数：nodes 网页标签集合; urlMap 已存在的url集合 
     * 
     * 
     * 返回类型：css,js链接的集合 
     * 
     */  
    private List<List<String>> extractAllScriptNodes(NodeList nodes, HashMap<String, String> urlMap) {  
        List<List<String>> urlList = Lists.newArrayList();
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter(
                "script"), true);
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String src = tag.getAttribute("src");
            // Handle external css file's url
            if (src != null && src.length() > 0) {
                String innerURL = src;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, innerURL);
                    List<String> urlInfo = Lists.newArrayList();
                    urlInfo.add(innerURL);
                    urlInfo.add(absoluteURL);
                    urlList.add(urlInfo);
                }
                tag.setAttribute("src", absoluteURL);
            }
        }
  
        filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("link"),true);
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String type = (tag.getAttribute("type"));
            String rel = (tag.getAttribute("rel"));
            String href = tag.getAttribute("href");
  
            boolean isCssFile = false;
            if (rel != null) {
                isCssFile = rel.indexOf("stylesheet") != -1;
            } else if (type != null) {
                isCssFile |= type.indexOf("text/css") != -1;
            }
            // Handle external css file's url  
            if (isCssFile && href != null && href.length() > 0) {
                String innerURL = href;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, innerURL);
                    List<String> urlInfo = Lists.newArrayList();
                    urlInfo.add(innerURL);
                    urlInfo.add(absoluteURL);
                    urlList.add(urlInfo);
                }
                tag.setAttribute("href", absoluteURL);
            }
        }
        return urlList;
    }
  
    /** 
     * 
     * 
     * 方法说明：抽取网页包含的图像链接 
     * 
     * 
     * 输入参数：nodes 网页标签集合; urlMap 已存在的url集合 
     * 
     * 
     * 返回类型：图像链接集合 
     * 
     */  
    private List<List<String>> extractAllImageNodes(NodeList nodes, HashMap<String, String> urlMap) {
        List<List<String>> urlList = Lists.newArrayList();
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("IMG"), true);
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String src = tag.getAttribute("src");
            // Handle external css file's url
            if (src != null && src.length() > 0) {
                String innerURL = src;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, innerURL);
                    List<String> urlInfo = Lists.newArrayList();
                    urlInfo.add(innerURL);
                    urlInfo.add(absoluteURL);
                    urlList.add(urlInfo);
                }
                tag.setAttribute("src", absoluteURL);
            }
        }
        return urlList;
    }  
  
    /** 
     * 
     * 
     * 方法说明：相对路径转绝对路径 
     * 
     * 
     * 输入参数：strWeb 网页地址; innerURL 相对路径链接 
     * 
     * 
     * 返回类型：绝对路径链接 
     * 
     */  
    public static String makeAbsoluteURL(URL strWeb, String innerURL) {  
  
        // 去除后缀  
        int pos = innerURL.indexOf("?");  
        if (pos != -1) {  
            innerURL = innerURL.substring(0, pos);  
        }  
        if (innerURL != null && innerURL.toLowerCase().indexOf("http") == 0) {  
            System.out.println(innerURL);  
            return innerURL;  
        }  
  
        URL linkUri = null;  
        try {  
            linkUri = new URL(strWeb, innerURL);  
        } catch (MalformedURLException e) {  
  
            e.printStackTrace();  
            return null;  
        }  
  
        String absURL = linkUri.toString();  
        absURL = JHtmlClear.replace(absURL, "../", "");  
        absURL = JHtmlClear.replace(absURL, "./", "");  
        System.out.println(absURL);  
        return absURL;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：创建mht文件 
     * 
     * 
     * 输入参数：content 网页文本内容; urlScriptList 脚本链接集合; urlImageList 图片链接集合 
     * 
     * 
     * 返回类型： 
     * 
     */  
    private void createMhtArchive(String content, List<List<String>> urlScriptList, 
    		List<List<String>> urlImageList) throws Exception {
        MimeMultipart mp = new MimeMultipart("related");
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setHeader("X-Mailer", "Code Manager .SWT");
        // 设置网页正文 
        MimeBodyPart bp = new MimeBodyPart();
        bp.setText(content, strEncoding);
        bp.addHeader("Content-Type", "text/html;charset=" + strEncoding);
        //bp.addHeader("Content-Location", "");
        mp.addBodyPart(bp);
        int urlCount = urlScriptList.size();
        for (int i = 0; i < urlCount; i++) {  
            bp = new MimeBodyPart();
            List<String> urlInfo = urlScriptList.get(i);
            // String url = urlInfo.get(0).toString();
            String absoluteURL = urlInfo.get(1).toString();
            bp.addHeader("Content-Location", javax.mail.internet.MimeUtility.
            		encodeWord(java.net.URLDecoder.decode(absoluteURL, strEncoding)));
            DataSource source = new AttachmentDataSource(absoluteURL, "text");
            bp.setDataHandler(new DataHandler(source));
            mp.addBodyPart(bp);
        }
  
        urlCount = urlImageList.size();
        for (int i = 0; i < urlCount; i++) {
            bp = new MimeBodyPart();
            List<String> urlInfo = urlImageList.get(i);
            // String url = urlInfo.get(0).toString();
            String absoluteURL = urlInfo.get(0).toString();
            System.out.println(urlInfo.get(0).toString() + " +++ " + urlInfo.get(1));
            bp.addHeader("Content-Location", javax.mail.internet.MimeUtility
            		.encodeWord(java.net.URLDecoder.decode(absoluteURL,  strEncoding)));
            DataSource source = new AttachmentDataSource(absoluteURL, "image");
            bp.setDataHandler(new DataHandler(source));
            mp.addBodyPart(bp);
        } 
        msg.setContent(mp);
        // write the mime multi part message to a file  
        msg.writeTo(os);
    }
  
    class AttachmentDataSource implements DataSource {
        private MimetypesFileTypeMap map = new MimetypesFileTypeMap();
        private String strUrl;
        private String strType;
        private byte[] dataSize = null;
  
        /** 
         * 
         * This is some content type maps. 
         * 
         */  
        private Map<String, String> normalMap = Maps.newHashMap();
        {  
            // Initiate normal mime type map
            // Images
            normalMap.put("image", "image/jpeg");
            normalMap.put("text", "text/plain");
        }  
  
        public AttachmentDataSource(String strUrl, String strType) {
            this.strType = strType;
            this.strUrl = strUrl;
 
            strUrl = strUrl.trim();
            strUrl = strUrl.replaceAll(" ", "%20");
            dataSize = JQuery.downBinaryFile(strUrl);
        }
  
        /** 
         * 
         * Returns the content type. 
         * 
         */  
        public String getContentType() {
            return getMimeType(getName());
        }
  
        public String getName() {
            char separator = File.separatorChar;
            if (strUrl.lastIndexOf(separator) >= 0)
                return strUrl.substring(strUrl.lastIndexOf(separator) + 1);
            return strUrl;
        }  
  
        private String getMimeType(String fileName) {
            String type = (String) normalMap.get(strType);
            if (type == null) {
                try {
                    type = map.getContentType(fileName);
                } catch (Exception e) {
  
                } 
                // Fix the null exception  
                if (type == null) {
                    type = "application/octet-stream";  
                }
            }  
  
            return type;
        }  
  
        public InputStream getInputStream() throws IOException {
  
            if (dataSize == null)
                dataSize = new byte[0];
            return new ByteArrayInputStream(dataSize);
        }
  
        public OutputStream getOutputStream() throws IOException {
            return new ByteArrayOutputStream();
        }
    }
}

class JHtmlClear {
    public static String replace(String s, String s1, String s2) {
        return s.replace(s1, s2);
    }
}

class JQuery {
    public static String getHtmlText(String strUrl, String strEncoding) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            return new String(JQuery.getBytes(in), strEncoding);
        } catch (Exception e) {
            return "";
        }  
    }
    
    public static byte[] downBinaryFile(String s) {
        try {
            URL url = new URL(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            return JQuery.getBytes(in);
        } catch (Exception e) {
            return null;
        }
    }
  
    public static byte[] getBytes(InputStream is) throws Exception {
        byte[] data = null;
        Collection<byte[]> chunks = Lists.newArrayList();
        byte[] buffer = new byte[1024 * 64];
        int read = -1;
        int size = 0;
        while ((read = is.read(buffer)) != -1) {
            if (read > 0) {
                byte[] chunk = new byte[read];
                System.arraycopy(buffer, 0, chunk, 0, read);
                chunks.add(chunk);
                size += chunk.length;
            }
        }
        if (size > 0) {
            ByteArrayOutputStream bos = null;
            try {
                bos = new ByteArrayOutputStream(size);
                for (Iterator<byte[]> itr = chunks.iterator(); itr.hasNext();) {
                    byte[] chunk = (byte[]) itr.next();
                    bos.write(chunk);
                }
                data = bos.toByteArray();
            } finally {
                if (bos != null) {
                    bos.close();
                }
            }
        }
        return data;
    }
}
