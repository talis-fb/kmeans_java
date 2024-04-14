import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class KmeansSerialBuilder implements Kmean {

    @Override
    public List<Cluster> execute(List<Point> values, int k, List<Point> initialCenters) {
        List<Cluster> clusters = initialCenters.stream().map(Cluster::build_with_center).toList();

        double i = 0;

        while (true) {
            // System.err.println("iter " + i);
            // System.err.println("CENT " + clusters.stream().map(e -> e.center.toString()).toList());

            clusters = assignPoints(values, clusters);

            var newCenters = clusters.stream().map(Cluster::calculateCenterPoint).toList();
            var oldCenters = clusters.stream().map(Cluster::getCenter).toList();

            if (converged(newCenters, oldCenters)) {
                return clusters;
            }

            clusters = newCenters.stream().map(Cluster::build_with_center).toList();

            i += 1;
        }
    }

    public static List<Cluster> assignPoints(List<Point> points, List<Cluster> clusters) {
        for (var point : points) {
            int index = KmeanCommon.getIndexClosestCluster(point, clusters);
            clusters.get(index).points.add(point);
        }
        return clusters;
    }

    public boolean converged(List<Point> list1, List<Point> list2) {
        for (int i = 0; i < list1.size(); i++) {
            var p1 = list1.get(i);
            var p2 = list2.get(i);
            if (!p1.isEquals(p2))
                return false;
        }
        return true;
    }

}
