package com.udemy.WebApplicationNoSQL.services;

import com.udemy.WebApplicationNoSQL.domain.Post;
import com.udemy.WebApplicationNoSQL.domain.User;
import com.udemy.WebApplicationNoSQL.dto.UserDTO;
import com.udemy.WebApplicationNoSQL.repositories.PostRepository;
import com.udemy.WebApplicationNoSQL.repositories.UserRepository;
import com.udemy.WebApplicationNoSQL.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> findAll(){
        return repo.findAll();
    }

    public Post findById(String id){
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }
    public List<Post> findbyTitle (String text){
       return repo.searchTitle(text);
        // return repo.findByTitleContainingIgnoreCase(text);
    }
    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime()+24*60*60*1000);
        return repo.fullSearch(text, minDate, maxDate);
    }
}
