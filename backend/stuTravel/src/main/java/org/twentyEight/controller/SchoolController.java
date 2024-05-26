package org.twentyEight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.twentyEight.pojo.Result;
import org.twentyEight.service.SchoolService;

@RestController
@RequestMapping("/schoolMapUpdate")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/import")
    public Result importFiles() {
        schoolService.importJsonFilesFromDisk(System.getenv("MAP_DATA") + "/map_exports_test");
        return Result.success();
    }
}
