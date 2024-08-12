
import javax.persistence.*;

@Entity
@Table(name = "validated_token")
public class ValidatedToken {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
