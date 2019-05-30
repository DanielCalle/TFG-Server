package es.ucm.fdi.tfg.app.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/{image}")
    @ResponseBody
    public ResponseEntity<byte[]> getImageAsByteArray(@PathVariable String image) throws IOException {
        // From this path, gets all image files
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/images/" + image);

        if (in != null) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(IOUtils.toByteArray(in));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}