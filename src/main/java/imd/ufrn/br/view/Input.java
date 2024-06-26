package imd.ufrn.br.view;

import imd.ufrn.br.entities.Point;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    static public List<Point> read(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\n");

        var points = new ArrayList<Point>();

        String[] parts = null;
        String label = null;
        while (scanner.hasNext()) {
            parts = scanner.next().split(" ");
            label = parts[0];
            int red = Integer.parseInt(parts[1]);
            int green = Integer.parseInt(parts[2]);
            int blue = Integer.parseInt(parts[3]);
            points.add(new Point(label, red, green, blue));
        }

        return points;
    }
}
