public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  /*
   * Hint: In Java, there is no built in operator that does squaring or
   * exponentiation. We recommend simply multiplying a symbol by itself instead of
   * using Math.pow, which will result in slower code.
   */
  /* calculates the distance between two Planets */
  public double calcDistance(Planet target) {
    double distance = (target.xxPos - this.xxPos) * (target.xxPos - this.xxPos)
        + (target.yyPos - this.yyPos) * (target.yyPos - this.yyPos);
    return Math.sqrt(distance);
  }

  /*
   * takes in a planet, and returns a double describing the force exerted on this
   * planet by the given planet.
   */
  public double calcForceExertedBy(Planet target) {
    double force = G * this.mass * target.mass / (this.calcDistance(target) * this.calcDistance(target));
    return force;
  }

  /*
   * returns the total force, these two methods describe the force exerted in the
   * X and Y directions, respectively.
   */
  public double calcForceExertedByX(Planet target) {
    double forceX = this.calcForceExertedBy(target) * (target.xxPos - this.xxPos) / this.calcDistance(target);
    return forceX;
  }

  public double calcForceExertedByY(Planet target) {
    double forceY = calcForceExertedBy(target) * (target.yyPos - this.yyPos) / calcDistance(target);
    return forceY;
  }

  public double calcNetForceExertedByX(Planet[] allPlanets) {
    double allForceX = 0.0;
    for (Planet p : allPlanets) {
      if (p.equals(this)) {
        continue;
      }
      allForceX += this.calcForceExertedByX(p);
    }
    return allForceX;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double allForceY = 0.0;
    for (Planet p : allPlanets) {
      if (p.equals(this)) {
        continue;
      }
      allForceY += this.calcForceExertedByY(p);
    }
    return allForceY;
  }

  public void update(double dt, double forceX, double forceY) {
    double aX = forceX / this.mass;
    double aY = forceY / this.mass;
    this.xxVel += dt * aX;
    this.yyVel += dt * aY;
    this.xxPos += dt * this.xxVel;
    this.yyPos += dt * this.yyVel;
  }

  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }
}
