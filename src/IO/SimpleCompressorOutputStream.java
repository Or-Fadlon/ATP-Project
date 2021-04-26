package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {
        //TODO
        this.out.write(this.compress(b));
    }

    private byte[] compress(byte[] b) {
        ArrayList<Byte> holder = new ArrayList<>();
        int counter = 0, countingType = 1; //1-wall, 0-tile

        //insert meta data
        for (int i = 0; i < 12; i++)
            holder.add(b[i]);

        //compress maze
        for (int i = 12; i < b.length; i++) {
            if (b[i] == countingType)
                counter++;
            else {
                while (counter >= 0) {
                    if (counter > 255) {
                        holder.add((byte) 255);
                        holder.add((byte) 0);
                    } else
                        holder.add((byte) counter);
                    counter -= 255;
                }
                countingType = countingType == 1 ? 0 : 1;
                counter = 0;
            }
        }

        byte[] gridAsArray = new byte[holder.size()];
        for (int i = 0; i < gridAsArray.length; i++) {
            gridAsArray[i] = holder.get(i);
        }
        return gridAsArray;
    }
}
