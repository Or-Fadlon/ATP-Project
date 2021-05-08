package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    private static int decompress(byte[] bytes, byte[] target) throws IllegalArgumentException {
        ArrayList<Byte> holder = new ArrayList<>(); //placeholder for our return array

        //insert meta data
        for (int i = 0; i < 12; i++)
            holder.add(bytes[i]);

        //check target match the expected
        int expectedSize = (holder.get(0) + holder.get(1) * 127) * (holder.get(2) + holder.get(3) * 127) + 12;
        if (target.length < expectedSize)
            throw new IllegalArgumentException("the target byte Array given in too small");

        //compress maze
        for (int i = 12; i < bytes.length; i++) {
            int temp = bytes[i] > 0 ? bytes[i] : bytes[i] + 256;
            for (int j = 0; j < 8; j++) {
                holder.add((byte) (temp % 2));
                temp /= 2;
            }
        }


        for (int i = 0; i < target.length; i++)
            target[i] = holder.get(i);

        return 1;
    }

    @Override
    public int read() throws IOException {
        return 1;
    }

    @Override
    public int read(byte[] b) throws IOException, IllegalArgumentException {
        return decompress(in.readAllBytes(), b);
    }

    @Override
    public void close() throws IOException {
        this.in.close();
        super.close();
    }
}
