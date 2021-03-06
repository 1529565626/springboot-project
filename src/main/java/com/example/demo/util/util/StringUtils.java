package com.example.demo.util.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 常用字符串处理工具类
 * </p>
 *
 * @author Yang@Jowim.com
 * @version V1.0
 * @date 2018年1月16日 下午3:34:51
 * @see Copyright (c) 2018 泉州卓旻网络科技有限公司
 */
public class StringUtils {

    /**
     * 字符长度
     */
    public static final int CHAR_LEN = 1;
    /**
     * 字节长度
     */
    public static final int BYTE_LEN = 2;

    private StringUtils() {
    }

    /**
     * 数字字符串转化为整数
     *
     * @param intStr String 待转化的数字字符串
     * @param intDef int 当intStr为空或空字符串时返回的缺省值
     * @return int 返回由数字字符串转化成的数字，当intStr为空或空字符串时，返回缺省值intDef
     */
    public static int getInt(String intStr, int intDef) {
        if (null == intStr || "".equals(intStr.trim())) {
            return intDef;
        }

        int intRetu = intDef;

        Double db = new Double(intStr);
        intRetu = db.intValue();
        return intRetu;
    }

    /**
     * 数字字符串转化为整数，在转化时发生异常，则返回0
     *
     * @param intStr String 待转化的数字字符串
     * @return int 返回由数字字符串转化成的整数，当intStr为空或空字符串时，返回0
     */
    public static int getInt(String intStr) {
        return getInt(intStr, 0);
    }

    /**
     * 数字字符串转化为双精数字
     *
     * @param dbstr String 待转化的数字字符串
     * @param dbDef double 当dbstr为空或空字符串时返回的缺省值
     * @return double 返回由数字字符串转化成的数字，当dbstr为空或空字符串时，则返回缺省值dbDef
     */
    public static double getDouble(String dbstr, double dbDef) {

        if (null == dbstr || "".equals(dbstr.trim())) {
            return dbDef;
        }
        double dbRetu = dbDef;
        Double db = new Double(dbstr);
        dbRetu = db.doubleValue();
        return dbRetu;
    }

    /**
     * 数字字符串转化为双精数字，在转化时发生异常，则返回0
     *
     * @param dbstr String 待转化的数字字符串
     * @return double 返回由数字字符串转化成的数字，当dbstr为空或空字符串时，则返回0。
     */
    public static double getDouble(String dbstr) {
        return getDouble(dbstr, 0);
    }

    /**
     * 数字字符串转化为长整型
     *
     * @param longstr String 待转化的数字字符串
     * @param longDef long 当longstr为空或空字符串时返回的缺省值
     * @return long 返回由数字字符串转化成的数字，当longstr为空或空字符串时，则返回缺省值longDef
     */
    public static long getLong(String longstr, long longDef) {
        if (null == longstr || "".equals(longstr.trim())) {
            return longDef;
        }
        long longRetu = longDef;

        Double db = new Double(longstr);
        longRetu = db.longValue();

        return longRetu;
    }

    /**
     * 数字字符串转化为长整型，在转化时发生异常，则返回0
     *
     * @param longstr String 待转化的数字字符串
     * @return long 返回由数字字符串转化成的数字，当longstr为空或空字符串时，则返回0。
     */
    public static long getLong(String longstr) {
        return getLong(longstr, 0);
    }

    /**
     * 字符串转化为布尔型
     *
     * @param booleanstr String 待转化的字符串
     * @param booleanDef boolean 当字符串为空或为null时返回的值
     * @return boolean 返回转化化的布尔值，只有当booleanstr为字符串“true”(忽略大小写)时才返回true， <BR>
     * 如果在转化时发生异常（只有为null时），则返回缺省值booleanDef
     */
    public static boolean getBoolean(String booleanstr, boolean booleanDef) {
        if (null == booleanstr) {
            return booleanDef;
        }
        boolean booleanRetu = booleanDef;
        if ("true".equalsIgnoreCase(booleanstr.trim())) {
            booleanRetu = true;
        }

        return booleanRetu;
    }

    /**
     * 字符串转化为布尔型. <BR>
     * 只有当booleanstr为字符串“true”(忽略大小写)时才返回true，其它都返回false, 包括booleanstr为null
     *
     * @param booleanstr String 待转化的字符串
     * @return boolean 返回转化化的布尔值，
     */
    public static boolean getBoolean(String booleanstr) {
        return getBoolean(booleanstr, false);
    }

    /**
     * 双精数字格式化，返回字符串
     *
     * @param db  double 待格式化的双精数字
     * @param fmt String 格式化样式，参见类说明， <BR>
     *            fmt非法时，db将按原样转化为字符串后返回。
     * @return String 格式化后的数字，以字符串方式返回
     */
    public static String getNumFormat(double db, String fmt) {
        String retu = "";
        if (null == fmt || "".equals(fmt.trim())) {
            return Double.toString(db);
        }

        try {
            DecimalFormat decimalformat = new DecimalFormat(fmt);
            retu = decimalformat.format(db);
            decimalformat = null;
        } catch (IllegalArgumentException iaex) {
            // iaex.printStackTrace();
            retu = Double.toString(db);
        }
        return retu;
    }

    /**
     * 双精数字格式化，把入参中的双精数格式化为带两位小数的数字字符串
     *
     * @param db double 待格式化的双精数字
     * @return String 格式化为两位小数的数字字符串
     */
    public static String getNumFormat(double db) {
        return getNumFormat(db, "0.00");
    }

    /**
     * 数字字符串格式化，返回字符串
     *
     * @param numStr String 待格式化的数字字符串， <BR>
     *               如果numStr参数不是的数字则不进行格式化，按原样返回
     * @param fmt    String 格式化样式，参见类说明 <BR>
     *               fmt非法时，将把numStr按原样返回。
     * @return String 格式化后的字符串
     */
    public static String getNumFormat(String numStr, String fmt) {
        double db = getDouble(numStr, 0);
        return getNumFormat(db, fmt);
    }

    /**
     * 数字字符串格式化，把入参中的数字字符串格式化为带两位小数的数字字符串
     *
     * @param numStr String 待格式化的数字字符串， <BR>
     *               如果numStr参数不是的数字则不进行格式化，按原样返回
     * @return String 格式化为两位小数的数字字符串
     */
    public static String getNumFormat(String numStr) {
        return getNumFormat(numStr, "0.00");
    }

    /**
     * 普通字符串转化为网页中可显示的，如回车转化为&lt;br&gt;.
     *
     * @param str String 待转化的字符串
     * @return String 转化后的字符串
     */
    public static String htmlEncode(String str) {
        String retu = null;
        if (null == str || "".equals(str.trim())) {
            retu = str;
        } else {
            String html = str;
            // StringUtils tool = new StringUtils();
            html = replaceString(html, "&", "&amp;");
            html = replaceString(html, "<", "&lt;");
            html = replaceString(html, ">", "&gt;");
            html = replaceString(html, "\r\n", "\n");
            html = replaceString(html, "\n", "<br>");
            html = replaceString(html, "\t", "    ");
            html = replaceString(html, " ", "&nbsp;");
            html = replaceString(html, "\"", "&quot;");
            retu = html;
            html = null;
        }
        return retu;
    }

    /**
     * 字符串替换，把str字符串中的所有oldStr子串替换为newStr字串
     *
     * @param sourceStr    String 将被替换的字符串，为null时不执行任何替换操作
     * @param oldStr       String 将被替换的子串，为null或为空字符串时不执行替换操作
     * @param newStr       String 将被替换成的子串，为null时不执行替换操作
     * @param isIgnoreCase boolean 是否忽略大小写，true表忽略大小写，false表大小写敏感。
     * @return String 替换后的字符串
     */
    public static String replaceString(String sourceStr, String oldStr, String newStr, boolean isIgnoreCase) {
        if (sourceStr == null || oldStr == null || oldStr.equals("")) {
            return null;
        }
        String str_RetStr = sourceStr;
        int pos = str_RetStr.indexOf(oldStr);
        while (pos != -1) {
            str_RetStr = str_RetStr.substring(0, pos) + newStr + str_RetStr.substring(pos + oldStr.length());
            pos = str_RetStr.indexOf(oldStr, pos + newStr.length());
        }
        return str_RetStr;
    }

    /**
     * 字符串替换，把str字符串中的所有oldStr子串替换为newStr字串(大小写敏感)
     *
     * @param sourceStr String 将被替换的字符串，为null时不执行任何替换操作
     * @param oldStr    String 将被替换的子串，为null或为空字符串时不执行替换操作
     * @param newStr    String 将被替换成的子串，为null时不执行替换操作
     * @return String 替换后的字符串
     */
    public static String replaceString(String sourceStr, String oldStr, String newStr) {
        return replaceString(sourceStr, oldStr, newStr, false);
    }

    /**
     * 字符串按指定分隔符分解为一个数组（大小写敏感）. <BR>
     * 例子： <BR>
     * String[] array = StringUtils.splictString("AA/BBB/CCC//DDDD//" , "/" );
     * <BR>
     * 结果： <BR>
     * array[0]="AA" array[1]="BBB" array[2]="CCC" array[3]="DDDD"
     *
     * @param sourceStr String 源字符串
     * @param splitStr  String 分隔符
     * @return String[] 字符串数组, <BR>
     * 源字符串为null或为空字符串时，返回长度为1的数组，元素值为空字符串 <BR>
     * 分隔符为null或为空字符串时，返回长度为1的数组，元素值为源字符串
     */
    public static String[] splitString(String sourceStr, String splitStr, boolean isTrim) {
        if (sourceStr == null || splitStr == null) {
            return null;
        }

        if (isTrim) // 去掉开始和结束的分隔符
        {
            // 开头的间隔符去掉
            while (sourceStr.indexOf(splitStr) == 0) {
                sourceStr = sourceStr.substring(splitStr.length());
            }

            // 尾部的间隔符去掉
            while (sourceStr.indexOf(splitStr, sourceStr.length() - splitStr.length()) > -1) {
                sourceStr = sourceStr.substring(0, sourceStr.length() - splitStr.length());
            }
        }
        if (sourceStr.equals("") || splitStr.equals("")) {
            return null;
        }
        return splitString(sourceStr, splitStr);
    }

    /**
     * 功能简述 :把字符串放入一个数组
     *
     * @param sourceStr 要被放入的字符串
     * @param splitStr  间隔符
     * @return 转换后的数组, sourceStr或splitStr ＝ null 或 "" 返回null
     */
    public static String[] splitString(String sourceStr, String splitStr) {
        if (sourceStr == null || splitStr == null) {
            return null;
        }
        if (sourceStr.equals("") || splitStr.equals("")) {
            return null;
        }
        int int_ArraySize = subStringCount(sourceStr, splitStr);
        // 如果为-1则返回
        if (int_ArraySize == -1) {
            return null;
        }

        sourceStr += splitStr;

        String[] str_RetArr = new String[int_ArraySize + 1];
        int int_pos = sourceStr.indexOf(splitStr);
        int int_ArrayPos = 0;
        while (int_pos != -1) {
            str_RetArr[int_ArrayPos++] = sourceStr.substring(0, int_pos);
            sourceStr = sourceStr.substring(int_pos + splitStr.length());
            int_pos = sourceStr.indexOf(splitStr);
        }

        return str_RetArr;
    }

    /**
     * 功能简述 :把字符串放入一个数组
     *
     * @param sourceStr 要被放入的字符串
     * @param splitStr  间隔符
     * @return 转换后的List, sourceStr或splitStr ＝ null 或 "" 返回元素个数为0的List
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List splitString2List(String sourceStr, String splitStr) {
        String[] tempArr = splitString(sourceStr, splitStr);
        List resultList = new ArrayList();
        if (tempArr != null) {
            for (int i = 0; i < tempArr.length; i++) {
                resultList.add(tempArr[i]);
            }
        }
        return resultList;
    }

    /**
     * 功能简述 :把字符串放入一个数组
     *
     * @param sourceStr 要被放入的字符串
     * @param splitStr  间隔符
     * @return 转换后的List, sourceStr或splitStr ＝ null 或 "" 返回元素个数为0的List
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List splitString2List(String sourceStr, String splitStr, boolean isTrim) {
        String[] tempArr = splitString(sourceStr, splitStr, isTrim);
        List resultList = new ArrayList();
        if (tempArr != null) {
            for (int i = 0; i < tempArr.length; i++) {
                resultList.add(tempArr[i]);
            }
        }
        return resultList;
    }

    /**
     * list 转 string
     *
     * @param iterable
     * @param separator
     * @return
     */
    public static String join(Iterable<?> iterable, String separator) {
        return iterable == null ? null : join(iterable.iterator(), separator);
    }

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return Objects.toString(first, "");
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(first);
                }

                while (iterator.hasNext()) {
                    if (separator != null) {
                        buf.append(separator);
                    }

                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }

                return buf.toString();
            }
        }
    }

    /**
     * 功能简述 :在一个字符串中查找字符串个数(不区分大小写)
     *
     * @param sourceStr 要被查询的字符串
     * @param subStr    要查找的字符串
     * @return 找到的个数
     */
    public static int subStringCount(String sourceStr, String subStr) {
        // 如果是空指针则返回-1
        if (sourceStr == null || subStr == null) {
            return -1;
        }
        if (sourceStr.equals("") || subStr.equals("")) {
            return -1;
        }
        int result = 0;
        int int_pos = sourceStr.toUpperCase().indexOf(subStr.toUpperCase());
        while (int_pos != -1) {
            result++;
            int_pos = sourceStr.toUpperCase().indexOf(subStr.toUpperCase(), int_pos + subStr.length());
        }

        return result;
    }

    /**
     * 用指定的分隔符把字符串数组合并成一个字符串. 数组为null或长度为0时返回空字符串 <BR>
     * 例子： <BR>
     * String[] array = {"1",null,"","3"}; <BR>
     * StringUtils.arrayToString(array,"|"); <BR>
     * 返回结果：1|||3
     *
     * @param array    String[] 待合并的数组
     * @param splitStr String 数组各元素合并后，它们之间的分隔符，为null时用空字符串代替
     * @return String 合并后的字符串
     */
    public static String arrayToString(String[] array, String splitStr) {
        if (null == array || 0 == array.length) {
            return "";
        }
        if (null == splitStr) {
            splitStr = "";
        }
        StringBuffer strBuf = new StringBuffer("");
        int Len = array.length;
        for (int i = 0; i < Len - 1; i++) {
            strBuf.append((null == array[i]) ? "" : array[i]).append(splitStr);
        }
        strBuf.append((null == array[Len - 1]) ? "" : array[Len - 1]);

        return strBuf.toString();
    }

    /**
     * 用默认分隔符 | 把字符串数组合并成一个字符串. 数组为null或长度为0时返回空字符串
     *
     * @param array String[] 待合并的数组
     * @return String 合并后的字符串
     */
    public static String arrayToString(String[] array) {
        return arrayToString(array, "|");
    }

    /**
     * 判断字符串是否为null或为空字符串（包括只含空格的字符串）
     *
     * @param str String 待检查的字符串
     * @return boolean 如果为null或空字符串（包括只含空格的字符串）则返回true，否则返回false
     */
    public static boolean isNullBlank(String str) {
        return (null == str || "".equals(str.trim())) ? true : false;
    }

    /**
     * 从字符串中得到指定位置的子串（按分隔符分隔，大小写敏感）. <BR>
     * 例子： <BR>
     * StringUtils.decomposeString("a||b|c|d","|",1); <BR>
     * StringUtils.decomposeString("a||b|c|d","|",2); <BR>
     * StringUtils.decomposeString("a||b|c|d","|",3); <BR>
     * StringUtils.decomposeString("a||b|c|d","|",4); <BR>
     * StringUtils.decomposeString("a||b|c|d","|",5); <BR>
     * 返回结果： <BR>
     * a,空字符串,b,c,d
     *
     * @param sourceStr String 源字符串
     * @param splitStr  String 分隔符
     * @param pos       int 位置，从1开始
     * @return String 返回指定位置的子串。 <BR>
     * 当分隔符为null或为空字符串时返回源字符串； <BR>
     * 当源字符串为null或为空字符串时返回空字符串； <BR>
     * 当指定位置小于1或大于分隔符个数-1时返回空字符串。
     */
    public static String decomposeString(String sourceStr, String splitStr, int pos) {
        String retu = "";
        if (null == sourceStr || "".equals(sourceStr.trim())) {
            return "";
        }

        if (pos <= 0) {
            return "";
        }

        if (null == splitStr || "".equals(splitStr)) {
            return sourceStr;
        }

        sourceStr = sourceStr + splitStr;
        String tmpStr = sourceStr;

        int splitLen = splitStr.length();
        int tmpLen = tmpStr.length();

        for (int i = 0; i < pos - 1; i++) {
            int tmpPosi = tmpStr.indexOf(splitStr);
            if (tmpPosi < 0 || tmpPosi + splitLen >= tmpLen) {
                tmpStr = splitStr;
                break;
            } else {
                tmpStr = tmpStr.substring(tmpPosi + splitLen);
            }
        }
        retu = tmpStr.substring(0, tmpStr.indexOf(splitStr));
        return retu;
    }

    /**
     * 从字符串中得到指定位置的子串（按分隔符 | 分隔）.
     *
     * @param sourceStr String 源字符串
     * @param pos       int 位置，从1开始
     * @return String 返回指定位置的子串。 <BR>
     * 当分隔符为null或为空字符串时返回源字符串； <BR>
     * 当源字符串为null或为空字符串时返回空字符串； <BR>
     * 当指定位置小于1或大于分隔符个数-1时返回空字符串。
     */
    public static String decomposeString(String sourceStr, int pos) {
        return decomposeString(sourceStr, "|", pos);
    }

    /**
     * 删除指定的前导、后导子串（大小写敏感）. <br>
     * 例子： <br>
     * StringUtils.trim("and and username = '123' and password = 'abc' and
     * ","and "); <br>
     * 结果：username = '123' and password = 'abc'
     *
     * @param sourceStr  String 待删除的字符串
     * @param removeChar char 子串
     * @return String 处理后的字符串
     */
    public static String trim(String sourceStr, char removeChar) {
        if (sourceStr == null) {
            return null;
        }
        sourceStr = sourceStr.trim();
        int loInt_begin = 0, loInt_end = 0;
        int loInt_len = sourceStr.length();
        for (int i = 0; i < loInt_len; i++) {
            if (sourceStr.charAt(i) == removeChar) {
                loInt_begin++;
            } else {
                break;
            }
        }
        for (int i = 0; i < loInt_len; i++) {
            if (sourceStr.charAt(loInt_len - 1 - i) == removeChar) {
                loInt_end++;
            } else {
                break;
            }
        }
        return sourceStr.substring(loInt_begin, loInt_len - loInt_end);
    }

    /**
     * 删除指定的前导、后导子串（大小写敏感）. <br>
     * 例子： <br>
     * StringUtils.trim("and and username = '123' and password = 'abc' and
     * ","and "); <br>
     * 结果：username = '123' and password = 'abc'
     *
     * @param sourceStr  String 待删除的字符串
     * @param removeChar char 子串
     * @param end        true 只trim末尾，false 只trim头部
     * @return String 处理后的字符串
     */
    public static String trim(String sourceStr, char removeChar, boolean end) {
        if (sourceStr == null) {
            return null;
        }
        sourceStr = sourceStr.trim();
        int loInt_begin = 0, loInt_end = 0;
        int loInt_len = sourceStr.length();
        for (int i = 0; i < loInt_len; i++) {
            if (sourceStr.charAt(i) == removeChar) {
                loInt_begin++;
            } else {
                break;
            }
        }
        for (int i = 0; i < loInt_len; i++) {
            if (sourceStr.charAt(loInt_len - 1 - i) == removeChar) {
                loInt_end++;
            } else {
                break;
            }
        }
        if (end) {
            return sourceStr.substring(0, loInt_len - loInt_end);
        } else {
            return sourceStr.substring(loInt_begin);
        }
    }

    /**
     * <pre>
     * 将字符串截短到特定的长度,一个中文是两个字节的长度，但是是1个字符的长度，例：
     * 1)truncate("巨龙软件公司",2,CHAR_LEN) = "巨龙"
     * 2)truncate("巨龙软件公司",2,BYTE_LEN) = "巨"
     * 3)truncate("dragonsoft",2,CHAR_LEN) = "dr"
     * 4)truncate("dragonsoft",2,BYTE_LEN) = "dr"
     * </pre>
     *
     * @param str     String 需要截的字符串
     * @param len     int 截到的目标长度
     * @param lenType int 长度类型,字符长度{@link CHAR_LEN},字符长度{@link BYTE_LEN}
     * @return String
     */
    public static String truncate(String str, int len, int lenType) {
        if (str == null) {
            return str;
        }
        // 字符长度
        if (lenType == CHAR_LEN) {
            if (str.length() <= len) {
                return str;
            } else {
                return str.substring(0, len);
            }
        } else // 字节长度
        {
            StringBuffer sb = new StringBuffer();
            int temp = 0;

            for (int i = 0; i < str.length(); i++) {
                if (i < len) // 是否到指定的长度
                {
                    temp = ("" + str.charAt(i)).getBytes().length;
                    if (i + temp <= len) {
                        sb.append(str.charAt(i));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            return sb.toString();
        }
    }

    /**
     * 从源字符串中从第一个字符开始取出给定长度的字串. <BR>
     * 源字符串长度大于len时，尾巴追加一个appendStr串
     *
     * @param sourceStr String 源字符串
     * @param len       int 取出的长度
     * @param appendStr String 追加的字符串（常用的appendStr为...）
     * @return String 取出的子串
     */
    public static String substring(String sourceStr, int len, String appendStr) {
        if (null == sourceStr || "".equals(sourceStr)) {
            return sourceStr;
        }
        if (len <= 0) {
            return "";
        }

        if (null == appendStr) {
            appendStr = "";
        }

        int sourceLen = sourceStr.length();
        if (len >= sourceLen) {
            return sourceStr;
        } else {
            return sourceStr.substring(0, len) + appendStr;
        }
    }

    /**
     * 产生指定长度随机字符串（包括字母及数字）.
     *
     * @param length int 随机字符串长度
     * @return String 随机字符串
     */
    @SuppressWarnings("unused")
    public static String random(int length) {
        String retu = "";
        int d1, d2;
        char[] letters = initLetters();
        int[] numbers = initNumbers();

        for (int i = 0; i < length; i++) {
            d1 = ((int) (Math.random() * 10) % 2);
            if (d1 == 0) { // use a letter
                d2 = ((int) (Math.random() * 100) % 52);
                retu += letters[d2];
            } else if (d1 == 1) { // use a number
                retu += (int) (Math.random() * 10);
            }
        }
        return retu;
    }

    /**
     * 产生纯字母的随机字符串
     *
     * @param length int 随机字符串长度
     * @return String 随机字符串
     */
    public static String randomString(int length) {
        String retu = "";
        int d2;
        char[] letters = initLetters();

        for (int i = 0; i < length; i++) {
            d2 = ((int) (Math.random() * 100) % 52);
            retu += letters[d2];
        }
        return retu;
    }

    /**
     * 产生纯数字随机字符串
     *
     * @param length int 随机字符串长度
     * @return String 随机字符串
     */
    @SuppressWarnings("unused")
    public static String randomNumber(int length) {
        String retu = "";
        int[] numbers = initNumbers();

        for (int i = 0; i < length; i++) {
            retu += (int) (Math.random() * 10);
        }
        return retu;
    }

    /**
     * 内部方法，产生字母数组
     *
     * @return char[]
     */
    private static char[] initLetters() {
        char[] ca = new char[52];
        for (int i = 0; i < 26; i++) {
            ca[i] = (char) (65 + i);
        }
        for (int i = 26; i < 52; i++) {
            ca[i] = (char) (71 + i);
        }
        return ca;
    }

    /**
     * 内部方法，产生数字数组
     *
     * @return int[]
     */
    private static int[] initNumbers() {
        int[] na = new int[10];
        for (int i = 0; i < 10; i++) {
            na[i] = i;
        }
        return na;
    }

    /**
     * 对字符串进行加减密处理.
     *
     * @param source String 待加减密处理的字符串
     * @param flag   boolean true表进行加密，false表进行减密
     * @return String 处理后的字符串. <BR>
     * source为空则返回原字符串source， <BR>
     * 如果source中含有中文字符则对这一中文字符不处理.
     */
    public static String encrypt(String source, boolean flag) {
        if (null == source || "".equals(source.trim())) {
            return source;
        }
        String LS_KEY1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz~!@#$%^&*(),.;[]{}";
        String LS_KEY2 = "&*()AD;[BFLCGH]{PQ}EI!JKRYZ1MSTXNO23UV,.Wcd45ef6lm7g@h0a#$nij8ob%^ptvz9ku~qrswxy";

        char[] ch_source = source.toCharArray();

        StringBuffer strBuf = new StringBuffer(ch_source.length);

        int pos;
        for (int i = 0, Len = ch_source.length; i < Len; i++) {
            if (flag) { // 加密
                pos = LS_KEY1.indexOf(ch_source[i]);
                if (pos >= 0) {
                    strBuf.append(LS_KEY2.substring(pos, pos + 1));
                } else {
                    strBuf.append(ch_source[i]);
                }
            } else { // 减密
                pos = LS_KEY2.indexOf(ch_source[i]);
                if (pos >= 0) {
                    strBuf.append(LS_KEY1.substring(pos, pos + 1));
                } else {
                    strBuf.append(ch_source[i]);
                }
            }
        }

        return strBuf.toString();

    }

    /**
     * 将null字符串转换为空串，方便HTML的显示
     *
     * @param sourceStr 待处理的字符串
     * @return String 处理的的字符串
     */
    public static String toVisualString(String sourceStr) {
        if (sourceStr == null || sourceStr.equals("")) {
            return "";
        } else {
            return sourceStr;
        }
    }

    /**
     * 将字段填充到指定的长度
     *
     * @param sourceStr  String 操作字符串
     * @param length     int 指定长度
     * @param withChar   char 填充的字符
     * @param isPadToEnd boolean 是否填充在字符的尾部 true ：是 ,false:填充在头部
     * @return String
     */
    public static String pad(String sourceStr, int length, char withChar, boolean isPadToEnd) {
        if (sourceStr == null) {
            return null;
        }
        if (sourceStr.length() >= length) {
            return sourceStr;
        }

        StringBuffer sb = new StringBuffer(sourceStr);
        for (int i = 0; i < length - sourceStr.length(); i++) {
            if (isPadToEnd) {
                sb.append(withChar);
            } else {
                sb.insert(0, withChar);
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        // String aa = ",,,aa,aa,aa,,,,";
        // System.out.println("array:"+splitString(aa,",").length);
        // System.out.println("array:"+splitString(aa,",",true).length);
        System.out.println(truncate("a巨a龙asdf", 2, BYTE_LEN));
        System.out.println(truncate("a巨a龙asd", 4, CHAR_LEN));
        System.out.println(truncate("dragonasd", 6, BYTE_LEN));
        System.out.println(truncate("dragonasd", 6, CHAR_LEN));
    }

    /**
     * 将身份证号码转换为18位
     *
     * @param xIdCode String
     * @return String
     */
    public static String id_To_18(String xIdCode) {
        int IdLength = xIdCode.length();
        String newIdCode = "";
        switch (IdLength) {
            case 15:
                String serial = xIdCode.substring(IdLength - 3);
                if (serial.equals("996") || serial.equals("997") || serial.equals("998") || serial.equals("999")) {
                    newIdCode = xIdCode.substring(0, 6) + "18" + xIdCode.substring(IdLength - 9);
                } else {
                    newIdCode = xIdCode.substring(0, 6) + "19" + xIdCode.substring(IdLength - 9);
                }
                newIdCode = newIdCode + getIdCheckBit(newIdCode);
                break;
            case 17:
                newIdCode = xIdCode + getIdCheckBit(xIdCode);
                break;
            case 18:
                newIdCode = xIdCode;
                break;
            default:
                newIdCode = xIdCode;
        }
        return newIdCode;
    }

    /**
     * 将身份证号转换为15位
     *
     * @param xIdCode String
     * @return String
     */
    public static String id_to_15(String xIdCode) {
        String result = xIdCode;
        String temp = "";
        if (xIdCode.length() == 18) {
            temp = temp + xIdCode.substring(0, 6) + xIdCode.substring(8, 17);
            result = temp;
        }
        return result;
    }

    private static String getIdCheckBit(String xId) {
        String[] strCheckChar = new String[11];
        int[] nWeight = new int[18];
        strCheckChar[0] = "1";
        strCheckChar[1] = "0";
        strCheckChar[2] = "X";
        strCheckChar[3] = "9";
        strCheckChar[4] = "8";
        strCheckChar[5] = "7";
        strCheckChar[6] = "6";
        strCheckChar[7] = "5";
        strCheckChar[8] = "4";
        strCheckChar[9] = "3";
        strCheckChar[10] = "2";
        nWeight[0] = 7;
        nWeight[1] = 9;
        nWeight[2] = 10;
        nWeight[3] = 5;
        nWeight[4] = 8;
        nWeight[5] = 4;
        nWeight[6] = 2;
        nWeight[7] = 1;
        nWeight[8] = 6;
        nWeight[9] = 3;
        nWeight[10] = 7;
        nWeight[11] = 9;
        nWeight[12] = 10;
        nWeight[13] = 5;
        nWeight[14] = 8;
        nWeight[15] = 4;
        nWeight[16] = 2;
        nWeight[17] = 1;
        int SumMult = 0;
        for (int i = 0; i <= 16; i++) {
            String ch = xId.substring(i, i + 1);
            SumMult = SumMult + (Integer.parseInt(ch) * nWeight[i]);
        }
        int ResultMod = SumMult % 11;
        return strCheckChar[ResultMod];
    }

    /**
     * MD5加密算法
     *
     * @param s
     * @return
     * @更新时间:Apr 22, 2008
     * @更新作者:Ryan Wong
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得字符串中间的几位
     *
     * @param str_source
     * @param midlength
     * @return
     * @更新时间:Apr 22, 2008
     * @更新作者:Ryan Wong
     */
    public static String getMidString(String str_source, int midlength) {
        String str_mid = "";
        int length = str_source.length();
        if (midlength > length) {
            return null;
        }
        if (length % 2 == 0) {
            if (midlength % 2 != 0)
                return null;
            str_mid = str_source.substring(length / 2 - midlength / 2, length / 2 + midlength / 2);
        } else {
            if (midlength % 2 == 0)
                return null;
            str_mid = str_source.substring((length - 1) / 2 - (midlength - 1) / 2,
                    (length - 1) / 2 + (midlength - 1) / 2 + 1);
        }
        return str_mid;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0))
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 出去null和""
     *
     * @param src
     * @return
     */
    public static String formatNull(String src) {
        return (src == null || "null".equals(src)) ? "" : src;
    }

    /**
     * 判断字符串是否为空的正则表达式，空白字符对应的unicode编码
     */
    private static final String EMPTY_REGEX = "[\\s\\u00a0\\u2007\\u202f\\u0009-\\u000d\\u001c-\\u001f]+";

    /**
     * 验证字符串是否为空
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        return input == null || input.equals("") || input.matches(EMPTY_REGEX);
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    private static final String NUM_REG = "(\\+|\\-)?\\s*\\d+(\\.\\d+)?";

    /**
     * 判断是否数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }

        if (str.trim().matches(NUM_REG)) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否包含有乱码的数据,如果字符串中包含有替换字符就认为是乱码
     *
     * @param str
     * @return
     */
    public static boolean containUnreadableCode(String str) {
        return contain(str, "\\ufffd");
    }

    /**
     * 判读是否包含数字
     *
     * @param str
     * @return
     */
    public static boolean containNumber(String str) {
        return contain(str, "\\d");
    }

    /**
     * 判断是否包含a-zA-Z_0-9
     *
     * @param str
     * @return
     */
    public static boolean containWord(String str) {
        return contain(str, "\\w");
    }

    /**
     * 是否包含有标点符号
     *
     * @param str
     * @return
     */
    public static boolean containPunct(String str) {
        return contain(str, PUNCT_REG);
    }

    public static boolean contain(String str, String regex) {
        if (isEmpty(str) || isEmpty(regex)) {
            return false;
        }

        if (str.trim().matches(regex)) {
            return true;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }

        return false;
    }

    /**
     * 替换所有的（不区分大小写）
     *
     * @param input
     * @param regex
     * @param replacement
     * @return
     */
    public static String replaceAll(String input, String regex, String replacement) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(input).replaceAll(replacement);
    }

    /**
     * 移除所有的空格
     *
     * @param text
     * @return
     */
    public static String removeAllSpace(String text) {
        if (isEmpty(text)) {
            return text;
        }

        return text.replaceAll("[ ]+", "");
    }

    private static final String PUNCT_REG = "[^a-zA-Z0-9\\u4e00-\\u9fa5]";

    /**
     * 移除字符串中的所有的中英文标点符号
     *
     * @param str
     * @return
     */
    public static String removeAllPunct(String str) {
        if (isEmpty(str)) {
            return str;
        }

        return str.replaceAll(PUNCT_REG, "");
    }

    /**
     * 计算str中包含多少个子字符串sub
     *
     * @param str
     * @param sub
     * @return
     */
    public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }

        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }

        return count;
    }

    /**
     * 获得源字符串的一个子字符串
     *
     * @param str        ：源字符串
     * @param beginIndex ：开始索引（包括）
     * @param endIndex   ：结束索引（不包括）
     * @return
     */
    public static String substring(String str, int beginIndex, int endIndex) {
        if (isEmpty(str)) {
            return str;
        }

        int length = str.length();

        if (beginIndex >= length || endIndex <= 0 || beginIndex >= endIndex) {
            return null;
        }

        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (endIndex > length) {
            endIndex = length;
        }

        return str.substring(beginIndex, endIndex);
    }

    /**
     * 计算str中包含子字符串sub所在位置的前一个字符或者后一个字符和sub所组成的新字符串
     *
     * @param str
     * @param sub
     * @return
     */
    public static Set<String> substring(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return null;
        }

        Set<String> result = new HashSet<String>();
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            String temp = substring(str, idx - 1, idx + sub.length());
            if (!isEmpty(temp)) {
                temp = removeAllPunct(temp);
                if (!sub.equalsIgnoreCase(temp) && !containWord(temp)) {
                    result.add(temp);
                }

            }

            temp = substring(str, idx, idx + sub.length() + 1);
            if (!isEmpty(temp)) {
                temp = removeAllPunct(temp);
                if (!sub.equalsIgnoreCase(temp) && !containWord(temp)) {
                    result.add(temp);
                }
            }

            idx += sub.length();
        }

        return result;
    }

    /**
     * 过滤掉XML中无法解析的非法字符
     *
     * @param content
     * @return
     */
    public static String wrapXmlContent(String content) {
        if (isEmpty(content)) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if ((ch == '\t') || (ch == '\n') || (ch == '\r') || ((ch >= ' ') && (ch <= 55295))
                    || ((ch >= 57344) && (ch <= 65533)) || ((ch >= 65536) && (ch <= 1114111))) {
                result.append(ch);
            }
        }

        return result.toString();
    }

    /**
     * 判断字符串的长度
     *
     * @param str
     * @return
     */
    public static boolean overLength(String str) {
        if (isEmpty(str)) {
            return false;
        }

        return str.length() > 1 ? true : false;
    }

    /**
     * 字符串中含有特殊字符的处理
     *
     * @param str
     * @return
     */
    public static String specialStr(String str) {
        str = str.replaceAll("[^\\u4e00-\\u9fa5 | 0-9| a-zA-Z | \\.]+", " ").replaceAll("[\\.]{2,}", " ").trim();
        return str;
    }

    /**
     * 将特殊符号去掉，但是保留空格
     *
     * @param str
     * @return
     */
    public static String replaceInValidateChar(String str) {
        return str.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5\\s+]", " ");
    }

    /**
     * 返回字符串对应的unicode编码
     *
     * @param str
     * @return
     */
    public static String[] toHexString(String str) {
        char[] chars = str.toCharArray();

        String[] result = new String[chars.length];

        for (int i = 0; i < chars.length; i++) {
            result[i] = Integer.toHexString((int) chars[i]);
        }

        return result;
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    public static boolean isUrl(String src) {
        String regex = "http[s]?:\\/\\/([\\w-]+\\.[\\w-]+)(\\.[\\w-])+(:\\d{2,10})?.*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.matches();
    }

    /**
     * sql 查询转义
     *
     * @param str
     * @return
     */
    public static String escapeSql(String str) {
        if (StringUtils.isNotEmpty(str)) {
            StringBuffer strbuff = new StringBuffer();
            for (String s : str.split("")) {
                if (s.equals("%") || s.equals("_") || s.equals("\\")) {
                    strbuff.append("\\");
                }
                strbuff.append(s);
            }
            return strbuff.toString();
        }
        return str;
    }
}