package com.studycow.web.planner;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner")
@Tag(name = "Planner", description = "플래너 기본 기능")
public class PlannerController {


}
