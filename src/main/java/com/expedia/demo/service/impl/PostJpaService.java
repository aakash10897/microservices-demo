package com.expedia.demo.service.impl;

import com.expedia.demo.model.Post;
import com.expedia.demo.repository.PostRepository;
import com.expedia.demo.service.PostService;

public class PostJpaService implements PostService {

    private PostRepository postRepository;
    public PostJpaService(final PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
