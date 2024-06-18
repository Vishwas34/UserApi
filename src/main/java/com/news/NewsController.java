package com.news;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class NewsController {
	private static final String UPLOAD_DIR = "uploads/";
            @Autowired
            public MyService repo;
            @PostMapping("/news")
            public ResponseEntity<Object> addNews(@RequestBody News news)
            {
                 try {
                	 News savenews=repo.save(news);
                	 return ResponseEntity.ok(savenews);
                 }catch(Exception e)
                 {
                	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding News: "+e.getMessage());
                 }
            }
            @GetMapping("/news")
            public ResponseEntity<Object> getAllNews()
            {
            	try {
            		List<News> news = repo.findAll();
            		return ResponseEntity.ok(news);
            	}catch(Exception e)
            	{
            		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving products: " + e.getMessage());
            	}
            }
            @DeleteMapping("/news/{id}")
            public ResponseEntity<Object> deleteNews(@PathVariable(value="id") int id)
            {
            	try {
            		repo.deleteById(id);
            		return ResponseEntity.ok().build();
            	}catch(Exception e)
            	{
            		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving products: " + e.getMessage());
            	}
            }
            @GetMapping("/news/{id}")
            public ResponseEntity<Object> getNewsById(@PathVariable(value="id") int id)
            {
            	try {
            		Optional<News> news = Optional.ofNullable(repo.findNewsById(id));
            		if(news.isPresent())
            		{
            			return ResponseEntity.ok(news.get());
            		}
            		else
            		{
            			return ResponseEntity.notFound().build();
            		}
            	}catch(Exception e)
            	{
            		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving products: " + e.getMessage());
            	}
            }
            
            @PutMapping("/news/{id}")
            public ResponseEntity<Object> updateNewsById(@RequestBody News news,@PathVariable(value="id") int id) 
            {
            	 try {
                     Optional<News> enews = Optional.ofNullable(repo.findNewsById(id));
                     if (!enews.isPresent()) {
                         return ResponseEntity.notFound().build();
                     }
                     news.setId(id);
                     repo.save(news);
                     return ResponseEntity.noContent().build();
                 } catch (Exception e) {
                     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product: " + e.getMessage());
                 }
            }
            @GetMapping("/news/title/{title}")
            public ResponseEntity<Object> findByTitle(@PathVariable("title") String title) {
                try {
                    List<News> news = repo.findByTitle(title);
                    return ResponseEntity.ok(news);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by price: " + e.getMessage());
                }
            }
            @GetMapping("/news/author/{author}")
            public ResponseEntity<Object> findByAuthor(@PathVariable("author") String author) {
                try {
                    List<News> news = repo.findByAuthor(author);
                    return ResponseEntity.ok(news);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by price: " + e.getMessage());
                }
            }
            @GetMapping("/news/category/{category}")
            public ResponseEntity<Object> findByCategory(@PathVariable("category") String category) {
                try {
                    List<News> news = repo.findByCategory(category);
                    return ResponseEntity.ok(news);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by price: " + e.getMessage());
                }
            }
            @GetMapping("/news/language/{language}")
            public ResponseEntity<Object> findByLanguage(@PathVariable("language") String language) {
                try {
                    List<News> news = repo.findByLanguage(language);
                    return ResponseEntity.ok(news);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by price: " + e.getMessage());
                }
            }
            @GetMapping("/news/pdate/{pdate}")
            public ResponseEntity<Object> findByPDate(@PathVariable("pdate") String pdate) {
                try {
                    List<News> news = repo.findByPDate(pdate);
                    return ResponseEntity.ok(news);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding products by price: " + e.getMessage());
                }
            }
            @PostMapping("/news/uploads")
            public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("title") String title,
                                                           @RequestParam("description") String description,
                                                           @RequestParam("url") String url,
                                                           @RequestParam("source") String source,
                                                           @RequestParam("author") String author,
                                                           @RequestParam("pdate") String pdate,
                                                           @RequestParam("content") String content,
                                                           @RequestParam("category") String category,
                                                           @RequestParam("entities") String entities,
                                                           @RequestParam("language") String language) {
                if (file.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
                }

                try {
                    // Validate file type
                    String contentType = file.getContentType();
                    if (contentType == null || !(contentType.startsWith("image/") || contentType.startsWith("video/"))) {
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File type not supported");
                    }

                    // Create the upload directory if it doesn't exist
                    Path uploadPath = Paths.get(UPLOAD_DIR);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // Save the file locally
                    Path filePath = uploadPath.resolve(file.getOriginalFilename());
                    Files.copy(file.getInputStream(), filePath);

                    // Create a new News entity and save file information
                    News news = new News();
                    news.setTitle(title);
                    news.setDescription(description);
                    news.setUrl(url);
                    news.setSource(source);
                    news.setAuthor(author);
                    news.setPdate(pdate);
                    news.setContent(content);
                    news.setCategory(category);
                    news.setEntities(entities);
                    news.setLanguage(language);
                    news.setFilePath(filePath.toString());
                    news.setFileType(contentType);
                    repo.save(news);

                    return ResponseEntity.ok("File uploaded successfully: " + filePath.toString());
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
                }
            }

            
            
            
           
}
