package org.jetbrains.assignment.dto;


import lombok.Data;

public record MoveDto(Direction direction, int steps) {
}
