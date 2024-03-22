package org.jetbrains.assignment;

import org.jetbrains.assignment.dto.CoordinatesDto;
import org.jetbrains.assignment.dto.Direction;
import org.jetbrains.assignment.dto.MoveDto;
import org.jetbrains.assignment.service.MovingService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovingServiceTest {

    @Test
    public void move_changesCoordinatesCorrectly() {
        MovingService movingService = new MovingService();
        CoordinatesDto start = new CoordinatesDto(0, 0);
        MoveDto move = new MoveDto(Direction.NORTH, 5);

        CoordinatesDto result = movingService.move(start, move);

        assertThat(result).isEqualTo(new CoordinatesDto(0, 5));
    }

    @Test
    public void makeTrace_createsCorrectTrace() {
        MovingService movingService = new MovingService();
        CoordinatesDto start = new CoordinatesDto(0, 0);
        List<MoveDto> moves = Arrays.asList(
                new MoveDto(Direction.NORTH, 5),
                new MoveDto(Direction.EAST, 5)
        );

        List<CoordinatesDto> trace = movingService.makeTrace(start, moves);

        assertThat(trace).containsExactly(
                new CoordinatesDto(0, 0),
                new CoordinatesDto(0, 5),
                new CoordinatesDto(5, 5)
        );
    }

    @Test
    public void buildRoute_createsCorrectRoute() {
        MovingService movingService = new MovingService();
        List<CoordinatesDto> points = Arrays.asList(
                new CoordinatesDto(0, 0),
                new CoordinatesDto(0, 5),
                new CoordinatesDto(5, 5)
        );

        List<MoveDto> route = movingService.buildRoute(points);

        assertThat(route).containsExactly(
                new MoveDto(Direction.NORTH, 5),
                new MoveDto(Direction.EAST, 5)
        );
    }

    @Test
    public void buildShortestRoute_createsCorrectRoute() {
        MovingService movingService = new MovingService();
        List<CoordinatesDto> points = Arrays.asList(
                new CoordinatesDto(0, 0),
                new CoordinatesDto(0, 5),
                new CoordinatesDto(5, 5)
        );

        List<MoveDto> route = movingService.buildShortestRoute(points);

        assertThat(route).containsExactly(
                new MoveDto(Direction.NORTH, 5),
                new MoveDto(Direction.EAST, 5)
        );
    }
}
