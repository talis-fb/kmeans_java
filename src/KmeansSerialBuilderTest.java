import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KmeansSerialBuilderTest {
    @Test
    void testExecuteWithTwoPoints() {
        var values = new ArrayList<>(List.of(
                new Point(1, 2),
                new Point(5, 8)
        ));
        var K = 3;
        var initialCenters = values.stream().limit(K).toList();

        var kmeans = new KmeansSerialBuilder();
        kmeans.execute(values, K, initialCenters);

        var output = kmeans.execute(values, K, initialCenters);

        var expectedClusters = List.of(
                new Cluster(new Point(1, 2), List.of(new Point(1, 2))),
                new Cluster(new Point(5, 8), List.of(new Point(5, 8)))
        );

        var outputDisplaySet = new HashSet<>(output.stream().map(Cluster::toString).toList());
        var expectedClustersDisplaySet = new HashSet<>(expectedClusters.stream().map(Cluster::toString).toList());

        assertEquals(expectedClustersDisplaySet, outputDisplaySet);
    }

    @Test
    void testExecuteFewPoints() {
        var values = new ArrayList<>(List.of(
                new Point(1, 2),
                new Point(2, 3),
                new Point(8, 10),
                new Point(9, 11),
                new Point(10, 12)
        ));
        var K = 2;
        var initialCenters = values.stream().limit(K).toList();

        var kmeans = new KmeansSerialBuilder();
        kmeans.execute(values, K, initialCenters);

        var output = kmeans.execute(values, K, initialCenters);

        var expectedClusters = List.of(
                new Cluster(new Point(1, 2), List.of(
                        new Point(1, 2),
                        new Point(2, 3)
                )),
                new Cluster(new Point(9, 11), List.of(
                        new Point(8, 10),
                        new Point(9, 11),
                        new Point(10, 12)
                ))
        );

        var outputDisplaySet = new HashSet<>(output.stream().map(Cluster::toString).toList());
        var expectedClustersDisplaySet = new HashSet<>(expectedClusters.stream().map(Cluster::toString).toList());

        assertEquals(expectedClustersDisplaySet, outputDisplaySet);
    }

    @Test
    void testExecuteThreeClusters() {
        var values = new ArrayList<>(List.of(
                new Point(1, 1),
                new Point(2, 2),
                new Point(8, 8),
                new Point(9, 9),
                new Point(20, 20),
                new Point(21, 21)
        ));
        var K = 3;
        var initialCenters = values.stream().limit(K).toList();

        var kmeans = new KmeansSerialBuilder();
        kmeans.execute(values, K, initialCenters);

        var output = kmeans.execute(values, K, initialCenters);

        var expectedClusters = List.of(
                new Cluster(new Point(1, 1), List.of(
                        new Point(1, 1),
                        new Point(2, 2)
                )),
                new Cluster(new Point(8, 8), List.of(
                        new Point(8, 8),
                        new Point(9, 9)
                )),
                new Cluster(new Point(20, 20), List.of(
                        new Point(20, 20),
                        new Point(21, 21)
                ))
        );

        var outputDisplaySet = new HashSet<>(output.stream().map(Cluster::toString).toList());
        var expectedClustersDisplaySet = new HashSet<>(expectedClusters.stream().map(Cluster::toString).toList());

        assertEquals(expectedClustersDisplaySet, outputDisplaySet);
    }
}