public class NBody {
  /**create the main method*/
  /**start of Initial of the Universe*/
  public static void main (String [] args){
    double T = Double.parseDouble (args[0]);
    double dt = Double.parseDouble (args[1]);
    String fileName = args[2];
    double radius = readRadius(fileName);
    Planet [] Planet_arr = readPlanets(fileName);
    String background = "images/starfield.jpg";

    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.picture(0, 0, background);  /**the picture method's x, y mean the center*/

    for (Planet s: Planet_arr){
      s.imgFileName = "images/" + s.imgFileName;
    }
    /**same with the background picture,
     *the address of planet need to be modified*/

    for (Planet s: Planet_arr){
      s.draw();
    }
    /**end of Initial of the Universe*/

    /**start of creating the Animation*/

    StdDraw.enableDoubleBuffering();

    double t = 0;
    double [] xForce = new double [Planet_arr.length];
    double [] yForce = new double [Planet_arr.length];
    int PlanetsLength = Planet_arr.length;
    while (t < T) {
      for (int i = 0; i < PlanetsLength; i ++){
        /************************POINT: a big bug of MINE *********************
        /**the int i must be written in the loop and must be initialized every time
          or, the Force for every planet will not by update every dt*/
        /**********************************************************************/


        xForce[i] = Planet_arr[i].calcNetForceExertedByX(Planet_arr);
        yForce[i] = Planet_arr[i].calcNetForceExertedByY(Planet_arr);
        /*** for debug
        System.out.println(yForce[i]);
        System.out.println(Planet_arr[i].imgFileName);**/
      }

        /**用过的i就不让再用了*/
      for(int j = 0; j < PlanetsLength; j ++){
        Planet_arr[j].update(dt, xForce[j], yForce[j]);
          /**j ++; 不能忘啊，忘了就不动了*/
      }

      StdDraw.picture(0, 0, background);

      for (Planet s: Planet_arr){
        /**s.imgFileName = "images/" + s.imgFileName;*/

        /**same with the background picture,
         *the address of planet need to be modified*/
        s.draw();
      }

      StdDraw.show();
      StdDraw.pause(10);

      /***  for debug
      StdOut.printf("%d\n", Planet_arr.length);
      StdOut.printf("%.2e\n", radius);
      for (int q = 0; q < Planet_arr.length; q++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                     Planet_arr[q].xxPos, Planet_arr[q].yyPos, Planet_arr[q].xxVel,
                     Planet_arr[q].yyVel, Planet_arr[q].mass, Planet_arr[q].imgFileName);
      }
        **/




      t += dt;

    }


    /**let the Planet'name go back to original name instead of the images/planet*/
    for (Planet s: Planet_arr){
      String name_back = s.imgFileName;
      s.imgFileName = name_back.substring(7,name_back.length());
      /***System.out.println(s.imgFileName)*/;
    }



    StdOut.printf("%d\n", Planet_arr.length);
    StdOut.printf("%.2e\n", radius);
    for (int q = 0; q < Planet_arr.length; q++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  Planet_arr[q].xxPos, Planet_arr[q].yyPos, Planet_arr[q].xxVel,
                  Planet_arr[q].yyVel, Planet_arr[q].mass, Planet_arr[q].imgFileName);
    }

  }


  /**The method for reading Radius of given universe
    *input: file (String); output: redius (double)*/
  public static double readRadius (String fileName){
    In in = new In(fileName);
    int numbers_Planet = in.readInt();
    double redius = in.readDouble();
    return redius;
  }


  /**The method for reading Planet_arr
   * input: fileName (String); output a array of Planet*/
  /**point: the return type for this method should be Planet [] instead of array*/
  public static Planet[] readPlanets (String fileName){
    In in = new In(fileName);
    int numbers_Planet = in.readInt();
    double redius = in.readDouble();
    Planet [] Planet_arr = new Planet [numbers_Planet];  /***/
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
