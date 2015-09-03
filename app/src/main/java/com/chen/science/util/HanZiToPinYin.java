package com.chen.science.util;

import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by chenchi_94 on 2015/9/3.
 */
public class HanZiToPinYin {
    /**
     * 返回一个字的拼音
     * @param hanzi
     * @return
     */
    public static String toPinYin(String hanzi){
        char[] ca = hanzi.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ca.length; i ++) {
            String pinyinString = HanZiToPinYin.toPinYin(ca[i]);
            sb.append(pinyinString);
        }
        return sb.toString();
    }

    public static String toPinYin(char hanzi){
        HanyuPinyinOutputFormat hanyuPinyin = new HanyuPinyinOutputFormat();
        hanyuPinyin.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyin.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyin.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        String[] pinyinArray=null;
        try {
            //是否在汉字范围内
            if(hanzi>=0x4e00 && hanzi<=0x9fa5){
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(hanzi, hanyuPinyin);
            }else
                return String.valueOf(hanzi);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        //将获取到的拼音返回
        String ret = pinyinArray[0];
        if(pinyinArray[0].contains("ü")) {
            ret = ret.replace("ü", "v");
            Log.d("info", "change ü");
        }
        return ret;
    }
}