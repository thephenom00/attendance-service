package cz.cvut.fel.attendance.service.service;

import cz.cvut.fel.attendance.service.mappers.NewsMapper;
import cz.cvut.fel.attendance.service.model.News;
import cz.cvut.fel.attendance.service.repository.NewsRepository;
import cz.fel.cvut.attendance.service.model.NewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsMapper newsMapper;

    private final NewsRepository newsRepository;

    public NewsDto createNews(NewsDto newsDto) {
        News news = newsMapper.toEntity(newsDto);

        newsRepository.save(news);
        return newsMapper.toDto(news);
    }

    public List<NewsDto> getNews() {
        return newsMapper.toDtoList(newsRepository.findAll());
    }

    public NewsDto updateNews(Long id, NewsDto newsDto) throws Exception {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new Exception("News with ID " + id + " not found."));

        newsMapper.updateNewsFromDto(newsDto, news);

        newsRepository.save(news);
        return newsMapper.toDto(news);
    }

    public void deleteNews(Long id) throws Exception {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new Exception("News with ID " + id + " not found."));
        newsRepository.delete(news);
    }
}
