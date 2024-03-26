package basicStudy.GenericityTest.Genericity;

import org.apache.kafka.common.protocol.types.Field;

/**
 * @ClassName News
 * @Description
 * @Author SDY
 * @Date 2024/3/14 22:27
 **/
public class News {
    private String title;
    private String author;
    private String content;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
