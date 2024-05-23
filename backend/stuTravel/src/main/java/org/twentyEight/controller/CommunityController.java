package org.twentyEight.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twentyEight.pojo.Result;
import org.twentyEight.utils.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/community")
public class CommunityController {
    @GetMapping("/list")
    public Result<String> list() {
        return Result.success("所有的文章数据");
    }
}
