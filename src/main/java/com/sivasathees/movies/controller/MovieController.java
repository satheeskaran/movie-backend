package com.sivasathees.movies.controller;

import com.sivasathees.movies.model.Movie;
import com.sivasathees.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String add(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return "{\"success\":1}";
    }

    /*@PostMapping(value = "/addMovie", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile file) {
        logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
        return ResponseEntity.ok().build();
    }*/

    /*@PostMapping("/addMovie")
    public String add(@RequestParam("title") String title, @RequestParam("year") String year,
                      @RequestParam("length") String length, @RequestParam("category") String category,
                      @RequestParam("description") String description,
                      @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        movie.setImage(compressBytes(file.getBytes()));
        movieService.addMovie(movie);
        return "{\"success\":1}";
    }*/

    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping("/getMovie/{id}")
    public Movie getMovie(@PathVariable int id){
        return movieService.findMovieById(id);
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable int id){
        String re = movieService.deleteMovieById(id);
        if(re == "success"){
            return "{\"success\":1}";
        }
        else{
            return "{\"success\":0}";
        }
    }

    @PostMapping("/updateMovie")
    public String update(@RequestBody Movie movie){
        movieService.updateMovieById(movie);
        return "{\"success\":1}";
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
