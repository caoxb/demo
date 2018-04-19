package xbcao.demo.bean;

public class Eat {
    private String title;
    private String content;
    private String image;

    public Eat() {
    }

    public Eat(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Eat{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
