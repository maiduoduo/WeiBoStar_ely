package cn.star.weibo.bean;

/**
 * 用户Token Domain
 */
public class Token {

    public String token;
    public String expiresIn;
    public String refresh_token;
    public String uid;

    public Token(String token, String expiresIn, String refresh_token, String uid) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.refresh_token = refresh_token;
        this.uid = uid;
    }

    
}
