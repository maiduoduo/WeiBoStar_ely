package cn.star.weibo.bean.list;

import com.google.gson.Gson;

import java.util.ArrayList;

import cn.star.weibo.bean.Token;

/**
 * Token数据结构
 * @author SINA
 * @see <a href="http://t.cn/zjM1a2W">常见返回Token数据结构</a>
 * @since 2013-11-22
 */
public class TokenList {
    public ArrayList<Token> tokenList = new ArrayList<Token>();
    public int total_number;
    public String current_uid;


    public static TokenList parse(String jsonString) {
        Gson gson = new Gson();
        TokenList tokenList = gson.fromJson(jsonString, TokenList.class);
        if (tokenList == null) {
            
        }
        return tokenList;
    }

}
