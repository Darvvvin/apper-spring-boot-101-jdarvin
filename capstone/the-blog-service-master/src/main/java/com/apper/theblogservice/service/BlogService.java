package com.apper.theblogservice.service;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) { this.blogRepository = blogRepository; }

    // Blog methods
    public Blog createBlog(String title, String body, String bloggerId) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setBloggerId(bloggerId);

        return blogRepository.save(blog);
    }

    public Blog updateBlog(String title, String body, String blogId) {
        Blog blog = new Blog();

        blog.setTitle(title);
        blog.setBody(body);
        blog.setId(blogId);

        // Retain create and blogger id
        blog.setCreatedAt(blogRepository.findById(blogId).get().getCreatedAt());
        blog.setBloggerId(blogRepository.findById(blogId).get().getBloggerId());

        return blogRepository.save(blog);
    }

    public Blog getBlog(String id) {
        Optional<Blog> blogResult = blogRepository.findById(id);

        return blogResult.get();
    }

    public Iterable<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Iterable<Blog> getAllBlogsByBloggerId(String bloggerId) {
        return blogRepository.findAllByBloggerId(bloggerId);
    }
}
