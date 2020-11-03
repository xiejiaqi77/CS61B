public class NBody {

  public static double readRadius (String fileName){
    In in = new In(fileName);
    int numbers_Planet = in.readInt();
    double redius = in.readDouble();
    return redius;
  }

  public static Planet[] readPlanets (String fileName){
    In in = new In(fileName);
    int numbers_Planet = in.readInt();
    double redius = in.readDouble();
    Planet [] Planet_arr = new Planet [numbers_Planet];
    int i = 0;

    while (i < numbers_Planet){
      double xP = in.readDouble();
      double yP = in.readDouble();
      double xV = in.readDouble();
      double yV = in.readDouble();
      double mass = in.readDouble();
      String name = in.readString();
      Planet for_now = new Planet(xP, yP, xV, yV, mass, name);
      Planet_arr[i] = for_now;
      i ++;

    }
    return Planet_arr;

  }
}
