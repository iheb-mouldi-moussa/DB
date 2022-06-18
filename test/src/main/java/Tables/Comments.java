package Tables;

import javax.persistence.Lob;

public class Comments {
   
    
    private int id;
    private String nickname;
    private int likes;
    private String mail;
    private int article_id;

    @Lob
    private String content;

    public Comments(int id, String nickname, int likes, String mail, String content, int article_id)
    {
        this.id  = id;
        this.nickname = nickname;
        this.likes = likes;
        this.mail = mail;
        this.content = content;
        this.article_id = article_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getArticle_id() {
        return this.article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    @Lob
    public String getContent()
    {
        return this.content;
    }

    @Lob
    public void setContent(String content)
    {
        this.content = content;
    }

}
