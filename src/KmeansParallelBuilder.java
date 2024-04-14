import java.util.List;

public class KmeansParallelBuilder implements Kmean {
    @Override
    public List<Cluster> execute(List<Point> values, int k, List<Point> initialCenters) {
        List<Cluster> clusters = initialCenters.stream().map(Cluster::build_with_center).toList();

        while (true) {
            List<Cluster> finalClusters = clusters;
            values.parallelStream().forEach(point -> {
                var index = KmeanCommon.getIndexClosestCluster(point, finalClusters);
                finalClusters.get(index).addPointSync(point);
            });

            var newCenters = clusters.stream().map(Cluster::calculateCenterPoint).toList();
            var oldCenters = clusters.stream().map(Cluster::getCenter).toList();

            if (KmeanCommon.converged(newCenters, oldCenters)) {
                return clusters;
            }

            clusters = newCenters.stream().map(Cluster::build_with_center).toList();
        }
    }

}
