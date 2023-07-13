package com.apper.theblogservice;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.payload.CreateBlogRequest;
import com.apper.theblogservice.payload.CreateBlogResponse;
import com.apper.theblogservice.payload.UpdateBlogRequest;
import com.apper.theblogservice.payload.UpdateBlogResponse;
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
    public UpdateBlogResponse updateBlog(@RequestBody @Valid UpdateBlogRequest request, @PathVariable String id) {

        System.out.println(request);

        Blog updatedBlog = blogService.updateBlog(request.getTitle(), request.getBody(), id);

        UpdateBlogResponse response = new UpdateBlogResponse();

        response.setId(id);
        response.setBloggerId(updatedBlog.getBloggerId());
        response.setCreatedAt(updatedBlog.getCreatedAt());
        response.setLastUpdated(updatedBlog.getLastUpdate());

        return response;
    }

}
