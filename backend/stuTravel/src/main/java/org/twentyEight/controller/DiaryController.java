package org.twentyEight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.twentyEight.pojo.Diary;
import org.twentyEight.pojo.PageBean;
import org.twentyEight.pojo.Result;
import org.twentyEight.service.DiaryService;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping
    public Result add(@RequestBody Diary diary) {
        diaryService.add(diary);
        return Result.success();
    }



    @GetMapping("/myDiaries")
    public Result<PageBean<Diary>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer planId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Diary> pb = diaryService.list(pageNum, pageSize, planId, state);
        return Result.success(pb);
    }
}
