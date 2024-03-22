package org.jetbrains.assignment;

import org.jetbrains.assignment.dto.CoordinatesDto;
import org.jetbrains.assignment.service.ShortestPath;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShortestPathTest {

    @Test
    public void findShortestPath_returnsCorrectPathForThreePoints() {
        List<CoordinatesDto> points = Arrays.asList(
                new CoordinatesDto(0, 0),
                new CoordinatesDto(0, 1),
                new CoordinatesDto(1, 0)
        );
        ShortestPath shortestPath = new ShortestPath(points);

        List<CoordinatesDto> path = shortestPath.findShortestPath();

        assertThat(path).containsExactly(
                new CoordinatesDto(0, 0),
                new CoordinatesDto(0, 1),
                new CoordinatesDto(1, 0)
        );
    }

    @Test
    public void findShortestPath_returnsCorrectPathForFourPoints() {
        List<CoordinatesDto> points = Arrays.asList(
                new CoordinatesDto(0, 0),
                new CoordinatesDto(0, 2),
                new CoordinatesDto(2, 0),
                new CoordinatesDto(2, 2)
        );
        ShortestPath shortestPath = new ShortestPath(points);

        List<CoordinatesDto> path = shortestPath.findShortestPath();

        assertThat(path).containsExactly(
                new CoordinatesDto(0, 0),
                new CoordinatesDto(0, 2),
                new CoordinatesDto(2, 2),
                new CoordinatesDto(2, 0)
        );
    }

    @Test
    public void findShortestPath_returnsSinglePointForSingleInputPoint() {
        List<CoordinatesDto> points = Arrays.asList(
                new CoordinatesDto(0, 0)
        );
        ShortestPath shortestPath = new ShortestPath(points);

        List<CoordinatesDto> path = shortestPath.findShortestPath();

        assertThat(path).containsExactly(
                new CoordinatesDto(0, 0)
        );
    }

    @Test
    public void findShortestPath_returnsEmptyListForNoInputPoints() {
        List<CoordinatesDto> points = Arrays.asList();
        ShortestPath shortestPath = new ShortestPath(points);

        List<CoordinatesDto> path = shortestPath.findShortestPath();

        assertThat(path).isEmpty();
    }
}
