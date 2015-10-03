package org.lovepacs.controllers;

import org.lovepacs.json.BoxJson;
import org.lovepacs.json.ContentJson;
import org.lovepacs.models.Box;
import org.lovepacs.models.Content;
import org.lovepacs.repositories.BoxRepository;
import org.lovepacs.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    BoxRepository boxRepository;

    @Autowired
    ContentRepository contentRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    BoxJson getBox(@PathVariable("id") final int id) {
        Box box = boxRepository.findOne(id);

        BoxJson jsonBox = new BoxJson();
        jsonBox.setId(box.getId());
        jsonBox.setName(box.getName());
        jsonBox.setEnabled(box.isEnabled());

        List<ContentJson> jsonContents = new ArrayList<ContentJson>();

        List<Content> contents = contentRepository.findAllByBoxId(id);

        for(Content content : contents) {
            ContentJson contentJson = new ContentJson();
            contentJson.setId(content.getId());
            contentJson.setItemId(content.getItemId());
            contentJson.setQuantity(content.getQuantity());
            jsonContents.add(contentJson);
        }

        jsonBox.setContents(jsonContents);

        return jsonBox;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Box> getAllBoxes() {
        return (List<Box>)boxRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void disableBox(@PathVariable("id") final int id) {
        Box box = boxRepository.findOne(id);
        if (box != null) {
            box.setEnabled(false);
            boxRepository.save(box);
        }
    }

    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    void enableBox(@PathVariable("id") final int id) {
        Box box = boxRepository.findOne(id);
        if (box != null) {
            box.setEnabled(true);
            boxRepository.save(box);
        }
    }

    // Create a new Box and Contents
    @RequestMapping(value = "/", method = RequestMethod.POST)
    BoxJson createBox(@RequestBody BoxJson boxJson) {

        if(boxJson.getId() != null) {
            // This is an update instead
            return updateBox(boxJson);
        }

        Box box = new Box();
        box.setName(boxJson.getName());
        box.setEnabled(boxJson.getEnabled());

        box = boxRepository.save(box);
        boxJson.setId(box.getId());

        updateBoxContents(box.getId(), boxJson.getContents());

        return boxJson;
    }

    // Update an Existing Box
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    BoxJson updateBox(@RequestBody BoxJson boxJson) {

        if(boxJson.getId() == null) {
            // This is a create instead
            return createBox(boxJson);

        }

        Box box = boxRepository.findOne(boxJson.getId());

        if(box == null) {
            // TODO: bad stuff
        }

        box.setName(boxJson.getName());
        box.setEnabled(boxJson.getEnabled());

        box = boxRepository.save(box);

        updateBoxContents(box.getId(), boxJson.getContents());

        return boxJson;
    }


    private void updateBoxContents(Integer boxId, List<ContentJson> contents) {

        // Get the existing items. We may need to delete some
        List<Content> currentContents = contentRepository.findAllByBoxId(boxId);

        for(ContentJson content : contents) {

            if(content.getId() == null) {
                // New contents, so just insert
                Content myContent = new Content();
                myContent.setBoxId(boxId);
                myContent.setItemId(content.getItemId());
                myContent.setQuantity(content.getQuantity());
                myContent = contentRepository.save(myContent);
                content.setId(myContent.getId());

            } else {
                // An update
                if(contentRepository.exists(content.getId())) {
                    if(content.getQuantity() == 0) {
                        contentRepository.delete(content.getId());

                    } else {
                        Content myContent = contentRepository.findOne(content.getId());
                        myContent.setQuantity(content.getQuantity());
                        contentRepository.save(myContent);
                    }
                } else {
                    // We got an ID, but there isn't a row. Bad stuff.
                    // TODO: make more bad stuff happen
                }
            }
        }

        // Finally, compare the list of current contents to the contents from the JSON.
        // If we didn't get a row in contents that we had known about before, build a list
        // of IDs to delete.
        for(Content currentContent : currentContents) {

            boolean found = false;

            for(ContentJson jsonContent : contents) {
                if(jsonContent.getId() == currentContent.getId()) {
                    found = true;
                    break;
                }
            }

            if(!found) {
                contentRepository.delete(currentContent.getId());
            }
        }
    }

}
