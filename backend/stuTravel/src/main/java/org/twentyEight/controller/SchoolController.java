package org.twentyEight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.twentyEight.pojo.Result;
import org.twentyEight.service.MapUpdateService;


@RestController
@RequestMapping("/schoolMapUpdate")
public class SchoolController {
    @Autowired
    private MapUpdateService mapUpdateService;

    @PostMapping("/import")
    public Result importFiles() {
        mapUpdateService.importJsonFilesFromDisk(System.getenv("MAP_DATA") + "/map_exports_test");
        return Result.success();
    }
}
