package Day11;

public class Galaxy {
    private int x;
    private int y;

    public Galaxy(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void expand(int[] xlist, int[] ylist) {
        int xinc = 0;
        for (int x = 0; x < xlist.length; x++) {
            if (xlist[x] == 0 && this.x > x) {
                xinc++;
            }
        }

        int yinc = 0;
        for (int y = 0; y < ylist.length; y++) {
            if (ylist[y] == 0 && this.y > y) {
                yinc++;
            }
        }

        // part 1
        // this.x += xinc;
        // this.y += yinc;

        // part 2
        this.x += xinc * 999_999;
        this.y += yinc * 999_999;
    }

    // simple math
    public int lengthTo(Galaxy galaxy) {
        int xdiff = Math.abs(this.x - galaxy.x);
        int ydiff = Math.abs(this.y - galaxy.y);
        return xdiff + ydiff;
    }
}
