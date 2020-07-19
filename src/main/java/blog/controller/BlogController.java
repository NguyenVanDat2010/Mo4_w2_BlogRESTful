package blog.controller;

import blog.model.Blog;
import blog.model.Category;
import blog.service.blog.IBlogService;
import blog.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping
    public ResponseEntity<List<Blog>> showAllBlog() {
        List<Blog> list = blogService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    public List<Blog> blogs() {
//        List<Blog> list;
//        for (Blog blog : blogService.findAll()) {
//            blog.se
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> editBlog(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
