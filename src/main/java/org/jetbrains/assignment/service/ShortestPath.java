package org.jetbrains.assignment.service;

import org.jetbrains.assignment.dto.CoordinatesDto;

import java.util.*;

public class ShortestPath {
    private final List<CoordinatesDto> points;
    private final int size;
    private final double[][] distance;
    private final double[][] dp;
    private final int[][] next;

    public ShortestPath(List<CoordinatesDto> points) {
        this.points = points;
        this.size = points.size();
        this.distance = new double[size][size];
        this.dp = new double[size][1 << size];
        this.next = new int[size][1 << size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], Double.MAX_VALUE / 2);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distance[i][j] = points.get(i).distanceTo(points.get(j));
            }
        }
    }

    public List<CoordinatesDto> findShortestPath() {

        List<CoordinatesDto> path = new ArrayList<>();
        if (points == null || points.isEmpty()) {
            return path;
        }
        if (points.size() == 1) {
            path.add(points.get(0));
            return path;
        }
        double res = tsp(0, 1);
        int curNode = 0;
        int mask = 1;
        while (true) {
            path.add(points.get(curNode));
            int nextNode = next[curNode][mask];
            mask |= (1 << nextNode);
            curNode = nextNode;
            if (mask == (1 << size) - 1) {
                path.add(points.get(curNode));
                break;
            }
        }

        return path;
    }

    private double tsp(int node, int mask) {
        if (mask == (1 << size) - 1) {
            return distance[node][0];
        }
        if (dp[node][mask] != Double.MAX_VALUE / 2) {
            return dp[node][mask];
        }

        for (int i = 0; i < size; i++) {
            if ((mask & (1 << i)) == 0) {
                double newDist = distance[node][i] + tsp(i, mask | (1 << i));
                if (newDist < dp[node][mask]) {
                    dp[node][mask] = newDist;
                    next[node][mask] = i;
                }
            }
        }

        return dp[node][mask];
    }
}