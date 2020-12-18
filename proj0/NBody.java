public class NBody {

  private static String starfield = "./images/starfield.jpg";

  public static double readRadius(String filename) {
    In in = new In(filename);
    int numberOfPlanets = in.readInt(); // read the first int
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String filename) {
    In in = new In(filename);
    int numberOfPlanets = in.readInt();
    double radius = in.readDouble();
    Planet[] planets = new Planet[numberOfPlanets];

    for (int line = 0; line < numberOfPlanets; line++) {
      planets[line] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
          in.readString());
    }
    return planets;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);

    StdDraw.setScale(-radius, radius);
    double timer = 0.0;

    while (timer < T) {
      double[] forceX = new double[planets.length];
      double[] forceY = new double[planets.length];

      for (int x = 0; x < planets.length; x++) {
        forceX[x] = planets[x].calcNetForceExertedByX(planets);
        forceY[x] = planets[x].calcNetForceExertedByY(planets);
      }

      for (int x = 0; x < planets.length; x++) {
        planets[x].update(dt, forceX[x], forceY[x]);
      }

      StdDraw.picture(0, 0, starfield);

      for (Planet p : planets) {
        p.draw();
      }

      StdDraw.show();
      StdDraw.pause(40);

      StdDraw.clear();

      timer = timer + dt;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);

    }
  }
}
