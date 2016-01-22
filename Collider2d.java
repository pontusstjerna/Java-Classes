import java.awt.image.BufferedImage;

/**
 * Created by Pontus on 2015-11-17.
 */
public class Collider2d {
    private int w, h, gTol;
    private BufferedImage img;

    public enum Side {left, right, up, down};

    public Collider2d(int w, int h, BufferedImage img, int gTol){
        this.w = w;
        this.h = h;
        this.img = img;
        this.gTol = gTol;
    }

    public boolean collision(int x, int y, Side side) {

        //0 = left, 1 = right, 2 = up, 3 = down

        int pixels[] = {0,0};
        boolean coll = true;

        try {
            switch (side){
                case left: //Get left-side pixels
                    pixels = img.getRGB(x, y, 1, h-gTol, null, 0, 1);
                    break;
                case right: //Get right-side pixels
                    pixels = img.getRGB(x + (w - 1), y, 1, h-gTol, null, 0, 1);
                    break;
                case up: //Get up-side pixels
                    pixels = img.getRGB(x, y, w, 1, null, 0, w);
                    break;
                case down: //Get down-side pixels
                    pixels = img.getRGB(x, (y+h), w, 1, null, 0, w);
                    break;
                default:
                    pixels = new int[1];
                    break;
            }

            int zeros = 0;
            //Check if all the elements have the RGB 0.
            for(int i = 0; i < pixels.length; i++) {
                if (pixels[i] == 0) zeros++;
            }

            coll = zeros != pixels.length;

        }catch(ArrayIndexOutOfBoundsException e){

            //If the rect is out of bounds, there is a collision
            coll = true;
        }
        return coll;
    }

    public int getWidth(){return w;}

    public int getHeight(){return h;}

    public int getGTol(){return gTol;}

    public BufferedImage getImg(){return img;}

    public void setWidth(int width){
        w = width;
    }

    public void setHeigth(int height){
        h = height;
    }

    public void setGTol(int gTol){
        this.gTol = gTol;
    }

    public void setImg(BufferedImage img){
        this.img = img;
    }

    public String toString(){

        String description = "Current width " + w +
                " Current height: " + h + " Current ground tolerance: " + gTol + "Img: " + img;
        return description;
    }
}
