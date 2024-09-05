package com.stackroute.service;

import com.stackroute.domain.Blog;

import com.stackroute.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;


    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(int id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.orElse(null);
    }

    @Override
    public Blog deleteBlog(int id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            blogRepository.deleteById(id);
            return blog.get();
        }
        return null;
    }

    @Override
    public Blog updateBlog(Blog blog) {
        Optional<Blog> existingBlog = blogRepository.findById(blog.getBlogId());

        if (existingBlog.isPresent()) {
            Blog blogToUpdate = existingBlog.get();
            blogToUpdate.setBlogTitle(blog.getBlogTitle());
            blogToUpdate.setAuthorName(blog.getAuthorName());
            blogToUpdate.setBlogContent(blog.getBlogContent());

            return blogRepository.save(blogToUpdate);
        }

        return null;
    }

}

