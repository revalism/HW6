package Entity;

public class Article {
    private long id;
    private String title;
    private String brief;
    private String Text;
    private String content;
    private String createDate;
    private boolean isPublished;
    private long user_id;

    public Article(long id, String title, String brief, String text, String content, String createDate, boolean isPublished, long user_id) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.Text = text;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.user_id = user_id;
    }


    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Article() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id, long id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", isPublished=" + isPublished +
                ", user_id=" + user_id +
                '}';
    }
}
