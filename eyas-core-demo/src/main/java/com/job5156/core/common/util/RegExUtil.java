package com.job5156.core.common.util;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function: 把包含特殊字符或HTML的字串清理，返回干净的text
 *
 * @autor:leeton
 * Date: 14-9-22
 * Time: 下午3:33
 */
public class RegExUtil {

        private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";    // 定义style的正则表达式
        private static final String regEx_html = "<[^>]+>";         // 定义HTML标签的正则表达式
        private static final String  regEx_space = "\\s*|\t|\r|\n";  //定义空格回车换行符
        private static final String regEx_chinese_abc = "^[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,20}";  //定义中文跟英文字母+数字


        /**
         * @param htmlStr
         * @return
         *  删除Html标签
         */
        public static String delHTMLTag(String htmlStr) {
            if(htmlStr == null || htmlStr.length() == 0 ) return "" ;
            Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
            Matcher m_space = p_space.matcher(htmlStr);
            htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
            htmlStr = htmlStr.replaceAll("&nbsp;", "");
            return htmlStr.trim(); // 返回文本字符串
        }

        public static String getTextFromHtml(String htmlStr){
            htmlStr = delHTMLTag(htmlStr);
            htmlStr = htmlStr.replaceAll("&nbsp;", "");
            return htmlStr;
        }

    /**
     * 输入的字串是否匹配到设置的关键字
     * @param list 关键字列表
     * @param inputWords  输入的字串
     * @return
     */
    public static boolean isFound(List<String> list,String inputWords)
    {
        createPattern(list);
        Iterator it = list.iterator();
        while(it.hasNext())
        {
            String patStr = (String)it.next();
            Pattern pattern = Pattern.compile(patStr);
            Matcher matcher = pattern.matcher(inputWords);
            if(matcher.find())return true;
        }
        return false;
    }

    /**
     *组装regex表达式
     * list 包含多个关键字的list
     * @param list
     */
    private static void createPattern(List<String> list)
    {
        if(null != list && list.size()>0)
        {
            int index = 0;
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                String str = (String) it.next();
                int length = str.length();
                //将字符加上正则表达式
                String temp = "";
                for (int i = 0; i < length; i++) {
                    temp += str.charAt(i);
                }
                temp = "\\s*"+ temp;
                list.set(index, temp);
                index ++;
            }
        }
    }

    public static boolean isCommonPosName(String inputWords) {

        Pattern pattern = Pattern.compile(regEx_chinese_abc);
        Matcher matcher = pattern.matcher(inputWords);
        return matcher.find();

    }

        public static void main(String[] args) {
            //String str = "<div style='text-align:center;'> 整治“四风”   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会</span><br/></div>";
           // System.out.println(getTextFromHtml("软件(助理)工程师"));
          //  System.out.println( isCommonPosName("\t@#$@结构工程师2名"));
           // System.out.println( isCommonPosName("软件(助理)工程师"));
        }
}
