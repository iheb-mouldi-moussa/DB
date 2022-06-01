package Tables;
import javax.persistence.Lob;

public class Articles {
    private int id;
    private String title;
    private int likes;
    private int author_id;
    @Lob
    private String content;
    @Lob
    private String summary;


    
    public Articles(int id, String title, String content, String summary, int likes, int author_id)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.likes = likes;
        this.author_id = author_id;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    public int getLikes()
    {
        return this.likes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
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

    @Lob
    public String getSummary()
    {
        return this.summary;
    }

    @Lob
    public void setSummary(String summary)
    {
        this.summary = summary;
    }


    @Override
	public String toString() {
		return String
				.format("Article [id=%s,  Title=%s, Content=%s, Summary=%s, Likes=%s, author_id=%s]",
						id, title, content, summary, likes, author_id);
	}

}
