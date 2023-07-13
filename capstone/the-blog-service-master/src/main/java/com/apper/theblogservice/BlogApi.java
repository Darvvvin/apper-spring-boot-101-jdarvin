package com.apper.theblogservice;

import com.apper.theblogservice.exception.BlogDoesNotExistException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog")
public class BlogApi {
    private final BlogService blogService;

    public BlogApi(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) {
        System.out.println(request);

        Blog createdBlog = blogService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        CreateBlogResponse response = new CreateBlogResponse();

        response.setId(createdBlog.getId());
        response.setBloggerId(createdBlog.getBloggerId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdated(createdBlog.getLastUpdate());

        return response;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UpdateBlogResponse updateBlog(@RequestBody @Valid UpdateBlogRequest request, @PathVariable String id) throws BlogDoesNotExistException {

        if(!blogService.checkIfBlogExists(id)) {
            throw new BlogDoesNotExistException("Blog does not exist!");
        }

        System.out.println(request);

        Blog updatedBlog = blogService.updateBlog(request.getTitle(), request.getBody(), id);

        UpdateBlogResponse response = new UpdateBlogResponse();

        response.setId(id);
        response.setBloggerId(updatedBlog.getBloggerId());
        response.setCreatedAt(updatedBlog.getCreatedAt());
        response.setLastUpdated(updatedBlog.getLastUpdate());

        return response;
    }

    @GetMapping("{id}")
    public BlogDetails getBlog(@PathVariable String id) throws BlogDoesNotExistException {

        if(!blogService.checkIfBlogExists(id)) {
            throw new BlogDoesNotExistException("Blog does not exist!");
        }

        Blog blog = blogService.getBlog(id);

        BlogDetails blogDetails = new BlogDetails();

        blogDetails.setBloggerId(blog.getBloggerId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setUpdatedAt(blog.getLastUpdate());

        return blogDetails;
    }

    @GetMapping
    public Iterable<Blog> getAllBlogDetails() {return blogService.getAllBlogs();}

    @GetMapping("blogger/{id}")
    public Iterable<Blog> getAllBlogDetailsByBloggerId(@PathVariable String id) {
        return blogService.getAllBlogsByBloggerId(id);
    }
}
