public class Point {
	private int X, Y;

    public Point(int a,int b){
        X=a;
        Y=b;
    }
    public Point(){
        X=0;
        Y=0;
    }
    public int getX() {
        return X;
    }
    public int getY() { return Y; }
    @Override
    public String toString() {
        return "("+X+","+Y+")";
    }
}
