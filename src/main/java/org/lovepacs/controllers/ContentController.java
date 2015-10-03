package org.lovepacs.controllers;

import org.lovepacs.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ContentController {

    @Autowired
    ContentRepository contentRepository;

    
}
