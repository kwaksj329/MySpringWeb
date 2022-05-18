package com.ajou.procoding.myweb;

import com.ajou.procoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.procoding.myweb.dto.MusicList;
import com.ajou.procoding.myweb.entity.FavoriteMusic;
import com.ajou.procoding.myweb.repository.FavoriteRepository;
import com.ajou.procoding.myweb.service.MusicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@RestController
public class MywebApplication {
	public static void main(String[] args) {
		SpringApplication.run(MywebApplication.class, args);
	}
		@Autowired
		MusicService service;

		@GetMapping(value = "/musicSearch/{name}")
		public MusicList musicSearchByPath(@PathVariable String name){
			return service.searchMusic(name);
		}

		@GetMapping(value="/musicSearch")
		public MusicList musicSearchByParam(@RequestParam(value="term") String name) {
			return service.searchMusic(name);
		}

		@GetMapping(value="/likes")  //Get Favorite Music list from Database
		public List<FavoriteMusic> getLikes() {
			return service.getLikes();
		}

		@PostMapping(value="/likes")
		public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
			return service.saveFavorite(favorite);
		}
}
