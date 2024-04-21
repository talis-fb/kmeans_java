package imd.ufrn.br.kmeans;

import imd.ufrn.br.entities.Cluster;
import imd.ufrn.br.entities.Point;

import java.util.List;

public class KmeanCommon {
    public static int getIndexClosestCluster(Point point, List<Cluster> clusters) {
        double minDistance = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < clusters.size(); i++) {
            var cluster = clusters.get(i);
            var distance = point.euclideanDistance(cluster.getCenter());
            if (distance < minDistance) {
                minDistance = distance;
                index = i;
            }
        }
        return index;
    }

    public static boolean converged(List<Point> list1, List<Point> list2) {
        for (int i = 0; i < list1.size(); i++) {
            var p1 = list1.get(i);
            var p2 = list2.get(i);
            if (!p1.isEquals(p2))
                return false;
        }
        return true;
    }
}