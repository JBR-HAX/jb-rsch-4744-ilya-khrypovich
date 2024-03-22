package org.jetbrains.assignment.dto;

public record CoordinatesDto(int x, int y) {
    public double distanceTo(CoordinatesDto other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }
}
