package com.chaedae.restdemo.controller;

import com.chaedae.restdemo.model.Post;
import com.chaedae.restdemo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository repository;

    /**
     * 모든 게시글을 조회
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getPostList() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    /**
     * 게시글 등록
     * @param post
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws Exception {
        repository.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    /**
     * 특정 게시글 조회
     * @param postNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{postNo}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPost(@PathVariable("postNo") long postNo) throws Exception {
        return new ResponseEntity<>(repository.findByPostNo(postNo), HttpStatus.OK);
    }

    /**
     * 게시글 수정
     * @param postNo
     * @param post
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{postNo}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePost(@PathVariable("postNo") long postNo, @RequestBody Post post) throws Exception {
        Post rtnPost = repository.findByPostNo(postNo);

        if (rtnPost == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        rtnPost.setPostNo(postNo);
        rtnPost.setTitle(post.getTitle());
        rtnPost.setContents(post.getContents());

        post.setPostNo(postNo);
        repository.save(post);

        return new ResponseEntity<>(rtnPost, HttpStatus.OK);
    }

    /**
     * 게시글 삭제
     * @param postNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{postNo}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deletePost(@PathVariable("postNo") long postNo) throws Exception {
        Post post = repository.findByPostNo(postNo);

        Map<String, Boolean> rtnMap = new HashMap<>();
        if (post == null) {
            rtnMap.put("deleted", Boolean.FALSE);
            return rtnMap;
        } else {
            repository.delete(post);
            rtnMap.put("deleted", Boolean.TRUE);
        }

        return rtnMap;
    }
}
