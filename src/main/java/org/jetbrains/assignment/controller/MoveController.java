package org.jetbrains.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.assignment.dto.CoordinatesDto;
import org.jetbrains.assignment.dto.MoveDto;
import org.jetbrains.assignment.service.MovingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MoveController {
    private final MovingService movingService;

    @PostMapping("/locations")
    public ResponseEntity<List<CoordinatesDto>> locations(@RequestBody List<MoveDto> moves) {
        return ResponseEntity.ok(movingService.makeTrace(new CoordinatesDto(0, 0), moves));
    }

    @PostMapping("/moves")
    public ResponseEntity<List<MoveDto>> moves(@RequestBody List<CoordinatesDto> locations) {
        return ResponseEntity.ok(movingService.buildRoute(locations));
    }

    @PostMapping("/moves-short")
    public ResponseEntity<List<MoveDto>> movesShortest(@RequestBody List<CoordinatesDto> locations) {
        return ResponseEntity.ok(movingService.buildShortestRoute(locations));
    }
}
