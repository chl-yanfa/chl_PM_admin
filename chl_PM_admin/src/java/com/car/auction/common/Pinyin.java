package com.car.auction.common;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin {
	/**
     * 将字符串中的中文转化为拼音,其他字符不变
     * 
     * @param inputString
     * @return
     */
	
	
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
 
        char[] input = inputString.trim().toCharArray();
        String output = "";
 
        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }
    /**  
     * 获取汉字串拼音首字母，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音首字母  
     */  
    public static String getFirstSpell(String chinese) {
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {
                    if (arr[i] > 128) {
                            try {
                                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                                    if (temp != null) {   
                                            pybf.append(temp[0].charAt(0));   
                                    }   
                            } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                    e.printStackTrace();   
                            }   
                    } else {   
                            pybf.append(arr[i]);   
                    }   
            }   
            return pybf.toString().replaceAll("\\W", "").trim();   
    }   
    /**  
     * 获取汉字串拼音，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音  
     */  
    public static String getFullSpell(String chinese) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                    if (arr[i] > 128) {   
                            try {   
                                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);   
                            } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                    e.printStackTrace();   
                            }   
                    } else {   
                            pybf.append(arr[i]);   
                    }   
            }   
            return pybf.toString();   
    }
    
    //小写字母
  	public static final Map<String,String> LOWERCASE_LETTER_MAP = new LinkedHashMap<String, String>();
  	//大写字母
  	public static final Map<String,String> CAPITAL_LETTER_MAP = new LinkedHashMap<String, String>();
  	
  	static{
  		LOWERCASE_LETTER_MAP.put("A", "1");
  		LOWERCASE_LETTER_MAP.put("B", "2");
  		LOWERCASE_LETTER_MAP.put("C", "3");
  		LOWERCASE_LETTER_MAP.put("D", "4");
  		LOWERCASE_LETTER_MAP.put("E", "5");
  		LOWERCASE_LETTER_MAP.put("F", "6");
  		LOWERCASE_LETTER_MAP.put("G", "7");
  		LOWERCASE_LETTER_MAP.put("H", "8");
  		LOWERCASE_LETTER_MAP.put("I", "9");
  		LOWERCASE_LETTER_MAP.put("J", "10");
  		LOWERCASE_LETTER_MAP.put("K", "11");
  		LOWERCASE_LETTER_MAP.put("L", "12");
  		LOWERCASE_LETTER_MAP.put("M", "13");
  		LOWERCASE_LETTER_MAP.put("N", "14");
  		LOWERCASE_LETTER_MAP.put("O", "15");
  		LOWERCASE_LETTER_MAP.put("P", "16");
  		LOWERCASE_LETTER_MAP.put("Q", "17");
  		LOWERCASE_LETTER_MAP.put("R", "18");
  		LOWERCASE_LETTER_MAP.put("S", "19");
  		LOWERCASE_LETTER_MAP.put("T", "20");
  		LOWERCASE_LETTER_MAP.put("U", "21");
  		LOWERCASE_LETTER_MAP.put("V", "22");
  		LOWERCASE_LETTER_MAP.put("W", "23");
  		LOWERCASE_LETTER_MAP.put("X", "24");
  		LOWERCASE_LETTER_MAP.put("Y", "25");
  		LOWERCASE_LETTER_MAP.put("Z", "26");
  		
  		
  		CAPITAL_LETTER_MAP.put("a", "1");
  		CAPITAL_LETTER_MAP.put("b", "2");
  		CAPITAL_LETTER_MAP.put("c", "3");
  		CAPITAL_LETTER_MAP.put("d", "4");
  		CAPITAL_LETTER_MAP.put("e", "5");
  		CAPITAL_LETTER_MAP.put("f", "6");
  		CAPITAL_LETTER_MAP.put("g", "7");
  		CAPITAL_LETTER_MAP.put("h", "8");
  		CAPITAL_LETTER_MAP.put("i", "9");
  		CAPITAL_LETTER_MAP.put("j", "10");
  		CAPITAL_LETTER_MAP.put("k", "11");
  		CAPITAL_LETTER_MAP.put("l", "12");
  		CAPITAL_LETTER_MAP.put("m", "13");
  		CAPITAL_LETTER_MAP.put("n", "14");
  		CAPITAL_LETTER_MAP.put("o", "15");
  		CAPITAL_LETTER_MAP.put("p", "16");
  		CAPITAL_LETTER_MAP.put("q", "17");
  		CAPITAL_LETTER_MAP.put("r", "18");
  		CAPITAL_LETTER_MAP.put("s", "19");
  		CAPITAL_LETTER_MAP.put("t", "20");
  		CAPITAL_LETTER_MAP.put("u", "21");
  		CAPITAL_LETTER_MAP.put("v", "22");
  		CAPITAL_LETTER_MAP.put("w", "23");
  		CAPITAL_LETTER_MAP.put("x", "24");
  		CAPITAL_LETTER_MAP.put("y", "25");
  		CAPITAL_LETTER_MAP.put("z", "26");
  	}
  	
  	public static boolean isNumber(String validString){
        byte[] tempbyte=validString.getBytes();
        for(int i=0;i<validString.length();i++) {
            if((tempbyte[i]<48)||(tempbyte[i]>57)){
                return false;
            }
        }
        return true;
    }
  	
  	
    public static void main(String[] args) {  
//        String cnStr = "奔驰CLS级";
//        System.out.println(getFullSpell(cnStr));  
//        System.out.println(getFirstSpell(cnStr));
//        
//        System.out.println(CAPITAL_LETTER_MAP.get(getFullSpell(cnStr).substring(0, 1)));
        //System.out.println(isNumber("$"));
    	System.out.println("1111".substring(0, 1));
    } 
  
}  
