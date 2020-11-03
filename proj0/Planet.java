public class Planet {
        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;

        public Planet(double xP, double yP, double xV, double yV, double m, String img){
          xxPos = xP;
          yyPos = yP;
          xxVel = xV;
          yyVel = yV;
          mass = m;
          imgFileName = img;
        }

        /**make a copy of Planet*/
        public Planet(Planet b){
          xxPos = b.xxPos;
          yyPos = b.yyPos;
          xxVel = b.xxVel;
          yyVel = b.yyVel;
          mass = b.mass;
          imgFileName = b.imgFileName;
        }

        public Double calcDistance (Planet c) { /** the double in method name means the return type */
          double Distance;
          double Distance_pow;
          Distance_pow = (c.xxPos - this.xxPos)*(c.xxPos - this.xxPos) + (c.yyPos - this.yyPos)*(c.yyPos - this.yyPos);
          Distance = Math.sqrt(Distance_pow);
          return Distance;

        }

        public Double calcForceExertedBy (Planet d){
          double G = 6.67e-11;
          double Force;
          Force = G*(this.mass * d.mass)/(this.calcDistance(d)*this.calcDistance(d));
          return Force;
        }

        public Double calcForceExertedByX (Planet e){
          double Force_x;
          Force_x = this.calcForceExertedBy(e)*(e.xxPos-this.xxPos)/this.calcDistance(e);
          return Force_x;
        }

        public Double calcForceExertedByY (Planet f){
          double Force_y;
          Force_y = this.calcForceExertedBy(f)*(f.yyPos-this.yyPos)/this.calcDistance(f);
          return Force_y;
        }

        public Double calcNetForceExertedByX (Planet [] m) {
          double Force_xNet = 0;  /**better to initialize the Force_xNet due to there may be no other planet*/
          for (Planet s: m){
            if (s.equals(this)){
              continue;
            }else{
              Force_xNet += this.calcForceExertedByX(s);
            }
          }
          return Force_xNet;
        }

        public Double calcNetForceExertedByY (Planet [] n) {
          double Force_yNet = 0;
          for (Planet s: n){
            if (s.equals(this)){
              continue;
            }else{
              Force_yNet += this.calcForceExertedByY(s);
            }
          }
          return Force_yNet;
        }

        public void update(double dt, double fx, double fy){
          double accurate_x = fx/this.mass;
          double accurate_y = fy/this.mass;
          this.xxVel += accurate_x * dt;
          this.yyVel += accurate_y * dt;
          this.xxPos += this.xxVel*dt;
          this.yyPos += this.yyVel*dt;
        }

        





}
