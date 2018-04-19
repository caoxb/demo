package xbcao.demo.bean;

public class City {
    private String title;
    private String content;
    private String image;

    public City() {
    }

    public City(String title, String content, String image) {
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
        return "City{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
