import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FxClass extends Application {
    private static ArrayList<Edge> minSpanningTree;
    private static int graphSize;
    private static ArrayList<Point2D> vertexes = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        Group pane = new Group();
        Scene scene = new Scene(pane, 700, 700);
        stage.setScene(scene);
        stage.show();
        setVertexes();
        pane.getChildren().addAll(getMST());
    }

    private static ArrayList<Node> getMST() {
        ArrayList<Node> MST = new ArrayList<>();
        Line line;
        Point2D from, to;
        Label label;
        Circle circle;
        for (Point2D point : vertexes) {
            circle = new Circle(point.getX(), point.getY(), 15);
            MST.add(circle);
        }
        for (Edge edge : minSpanningTree) {
            System.out.println("From V" + edge.getStartVertex() + " To V" + edge.getEndVertex() + " Weight = " + edge.getWeight());
            from = vertexes.get(edge.getStartVertex() - 1);
            label = new Label("V" + edge.getStartVertex());
            label.setTextFill(Color.WHITE);
            label.setLayoutX(from.getX() - 10);
            label.setLayoutY(from.getY() - 10);
            MST.add(label);
            to = vertexes.get(edge.getEndVertex() - 1);
            label = new Label("V" + edge.getEndVertex());
            label.setLayoutX(to.getX() - 10);
            label.setLayoutY(to.getY() - 10);
            label.setTextFill(Color.WHITE);
            MST.add(label);
            line = new Line(from.getX(), from.getY(), to.getX(), to.getY());
            label = new Label(String.valueOf(edge.getWeight()));
            label.setLayoutX((from.getX() + to.getX()) / 2);
            label.setLayoutY((from.getY() + to.getY()) / 2);
            MST.add(label);
            MST.add(line);
        }
        return MST;
    }

    private static void setVertexes() {
        ArrayList<Point2D> points = new ArrayList<>();
        double prevX = 350, prevY = 50, D, E, B, C, A, x1, x2, y1, y2, delta;
        final double c = 350 * 350, r = 300 * 300, alpha = Math.PI * 2 / graphSize,
                d = Math.pow(2 * 350 * Math.sin(alpha / 2), 2);
        points.add(new Point2D(prevX, prevY));
        Point2D point;
        while (points.size() < graphSize) {
            D = (r - d + prevX * prevX + prevY * prevY - 2 * c) / (2 * (prevY - 350));
            E = (prevX - 350) / (prevY - 350);
            A = 1 + E * E;
            B = -2 * (350 + E * (D - 350));
            C = c + Math.pow(D - 350, 2) - r;
            delta = Math.sqrt(B * B - 4 * A * C);
            x1 = Math.ceil((-B + delta)) / (2 * A);
            y1 = Math.ceil(D - E * x1);
            x2 = Math.ceil((-B - delta) / (2 * A));
            y2 = Math.ceil(D - E * x2);
            if (points.size() == 1) {
                points.add(new Point2D(x1, y1));
                prevX = x1;
                prevY = y1;
            } else {
                point = new Point2D(x2, y2);
                if (!contains(points, point)) {
                    points.add(point);
                    prevX = x2;
                    prevY = y2;
                } else {
                    points.add(new Point2D(x1, y1));
                    prevX = x1;
                    prevY = y1;
                }
            }
        }
        vertexes = points;
    }

    private static boolean contains(ArrayList<Point2D> points, Point2D point) {
        for (Point2D point2D : points) {
            if (Math.abs(point2D.getY() - point.getY()) < 20 &&
                    Math.abs(point2D.getX() - point.getX()) < 20)
                return true;
        }
        return false;
    }

    public static void drawMinSpanningTree(String[] args, int size, ArrayList<Edge> mst) {
        minSpanningTree = mst;
        graphSize = size;
        launch(args);
    }
}
