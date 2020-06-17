package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.model.LinkEntity;
import ro.ubb.catalog.core.service.LinkEntityService;

import java.util.ArrayList;

@RestController
@RequestMapping("/dawd")
public class LinkEntityController {
    @Autowired
    private LinkEntityService linkEntityService;


    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ArrayList<LinkEntity> get_all_linkEntities(){
        return linkEntityService.get_entities();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public LinkEntity get_all_linkEntities(@RequestBody LinkEntity linkEntity){
        this.linkEntityService.add_entity(linkEntity);
        return linkEntity;
    }

}
