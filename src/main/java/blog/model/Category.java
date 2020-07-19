package blog.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;

    //Thêm fetch = FetchType.EAGER để fix lỗi ko hiển thị khi kết nối nhiều bảng , Tích hợp RESTful
    //,mappedBy = "category"
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Blog.class)
    private List<Blog> blogs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
