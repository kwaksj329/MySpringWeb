package com.ajou.procoding.myweb.service;

import com.ajou.procoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.procoding.myweb.dto.MusicList;
import com.ajou.procoding.myweb.entity.FavoriteMusic;
import com.ajou.procoding.myweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final FavoriteRepository albumsRepo;
    RestTemplate restTemplate = new RestTemplate();

    public MusicList searchMusic(@PathVariable String term) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject("https://itunes.apple.com/search?term="+term+"&entity=album", String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public List<FavoriteMusic> getLikes() {

        try {
            return albumsRepo.findAll();

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public int saveFavorite(@RequestBody FavoriteMusicRequestDto favorite) {
        FavoriteMusic music = albumsRepo.save(favorite.toEntity());
        if(music != null) {
            return 1;
        }
        else {
            return 0;
        }
    }
}