package com.ajou.procoding.myweb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class MusicList {
    private Integer resultCount;
    private List<Map<String, Object>> results;
}