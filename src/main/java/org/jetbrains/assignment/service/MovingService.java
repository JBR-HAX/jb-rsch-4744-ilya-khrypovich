package org.jetbrains.assignment.service;

import org.jetbrains.assignment.dto.CoordinatesDto;
import org.jetbrains.assignment.dto.Direction;
import org.jetbrains.assignment.dto.MoveDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovingService {

    public CoordinatesDto move(CoordinatesDto coordinates, MoveDto move) {
        int steps = move.steps();
        Direction direction = move.direction();

        return switch (direction) {
            case NORTH -> new CoordinatesDto(coordinates.x(), coordinates.y() + steps);
            case SOUTH -> new CoordinatesDto(coordinates.x(), coordinates.y() - steps);
            case WEST -> new CoordinatesDto(coordinates.x() - steps, coordinates.y());
            case EAST -> new CoordinatesDto(coordinates.x() + steps, coordinates.y());
            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        };
    }

    public List<CoordinatesDto> makeTrace(CoordinatesDto startPoint, List<MoveDto> moves) {
        List<CoordinatesDto> trace = new ArrayList<>(List.of(startPoint));
        CoordinatesDto currentPoint = startPoint;

        for (MoveDto move : moves) {
            currentPoint = move(currentPoint, move);
            trace.add(currentPoint);
        }

        return trace;
    }

    public List<MoveDto> buildRoute(List<CoordinatesDto> points) {
        List<MoveDto> moves = new ArrayList<>();
        CoordinatesDto currentPoint = points.get(0);

        for (int i = 1; i < points.size(); i++) {
            CoordinatesDto nextPoint = points.get(i);
            int dx = nextPoint.x() - currentPoint.x();
            int dy = nextPoint.y() - currentPoint.y();

            if (dx != 0) {
                moves.add(new MoveDto(dx > 0 ? Direction.EAST : Direction.WEST, Math.abs(dx)));
            }

            if (dy != 0) {
                moves.add(new MoveDto(dy > 0 ? Direction.NORTH : Direction.SOUTH, Math.abs(dy)));
            }

            currentPoint = nextPoint;
        }
        return moves;
    }

    public List<MoveDto> buildShortestRoute(List<CoordinatesDto> points) {
        ShortestPath shortestPath = new ShortestPath(points);
        List<CoordinatesDto> path = shortestPath.findShortestPath();
        return buildRoute(path);
    }

}
